package jidomaps.jidomap;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

// Added for the spinner
import java.util.ArrayList;
import java.util.List;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;




public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener, OnItemSelectedListener {

    private GoogleMap mMap;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    LocationRequest mLocationRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Asks for permission to use location data if the android version doesn't match
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            checkLocationPermission();
        }

        //creates our map fragment
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Now to place a spinner over the map fragment
        // Spinner object
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);
        // Spinner Drop down elements
        List<String> categories = new ArrayList<>();
        categories.add("Home");
        categories.add("Jido Pals!");
        categories.add("Profile");
        categories.add("Events");
        categories.add("Chat");
        categories.add("Schedule");
        categories.add("Settings");

        //ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        // Initialize Google Play Services
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        }
        else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }
    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this).addConnectionCallbacks(this).addOnConnectionFailedListener(this).addApi(LocationServices.API).build();
        mGoogleApiClient.connect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }

        // Place a marker at the devices current location
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

        //Sends the new location data to the server
        sendLocation(mLastLocation);

        //options for the updated marker
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Current Position");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        mCurrLocationMarker = mMap.addMarker(markerOptions);

        // Allows the maps camera to me moved around and played with
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));

        // Stop location updates after each update
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // The request for location was cancelled and the arrays to store results are left empty
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission is granted and location services are accessable
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }
                } else { // permission is denied and the apps location functionality is disabled
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }
            // More app permissions can be added here as we need them in the form of cases
        }
    }

    public boolean checkLocationPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Explains itself and requests location permission from the user on startup
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
            } else {
                // No explanation but still requests user location
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String item = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    public void onNothingSelected(AdapterView<?> arg0) {

    }

    public void sendLocation(Location location) {
        double lat = location.getLatitude();
        double lng = location.getLongitude();
        final String sLat = Double.toString(lat);
        String sLng = Double.toString(lng);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("TAG", response);
            }
        };

        LatLngRequest locRequest = new LatLngRequest(sLat, sLng);
        RequestQueue queue = Volley.newRequestQueue(MapsActivity.this);
        queue.add(locRequest);
    }

    public void placePals() {

    }

    public LatLng palLocation() {

        return null;
    }
}
