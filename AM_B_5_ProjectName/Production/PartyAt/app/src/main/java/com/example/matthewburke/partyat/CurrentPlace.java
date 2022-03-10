package com.example.matthewburke.partyat;

import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.OnMapReadyCallback;



import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;



import java.util.List;

/** This was intended to continuously track the user's location, so that they would automatically be checked in to any location they went, but due to an uresolved
 * gradle build error involving the proguard, this was never implemented and there is no way to get to this screen from the app.
 * @author Matthew Burke */
public class CurrentPlace extends AppCompatActivity //implements OnMapReadyCallback
        { //implements OnConnectionFailedListener, ConnectionCallbacks
    private final String TAG = CurrentPlace.class.getSimpleName();;
    private final int RESOLVE_CONNECTION_REQUEST_CODE = 1000;

    //private GoogleApiClient mGoogleApiClient;
    private GeoDataClient mGeoDataClient;
    private PlaceDetectionClient mPlaceDetectionClient;
    private FusedLocationProviderClient mFusedLocationProviderClient;


    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private boolean mLocationPermissionGranted = false;
    private Location mLastKnownLocation;
    private static final String KEY_LOCATION = "location";

    // Use this variable to determine the most likely place where the
    // user's device is currently located
    private PlaceLikelihood mostLikelyPlace = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mLastKnownLocation = savedInstanceState.getParcelable(KEY_LOCATION);
            //mCameraPosition = savedInstanceState.getParcelable(KEY_CAMERA_POSITION);
        }

        setContentView(R.layout.activity_current_place);


        mGeoDataClient = Places.getGeoDataClient(this, null);
        mPlaceDetectionClient = Places.getPlaceDetectionClient(this, null);
        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        /* Build the GoogleApiClient and connect to the Places API
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
                */



        findViewById(R.id.btnGetCurPlace).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick (View v) {
                        getCurrentPlaceData();
                    }
                });
    }

    /**
     * Saves the state of the map when the activity is paused. Do I need this?
     *
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (mMap != null) {
            //outState.putParcelable(KEY_CAMERA_POSITION, mMap.getCameraPosition());
            outState.putParcelable(KEY_LOCATION, mLastKnownLocation);
            super.onSaveInstanceState(outState);
        }
    }
*/

    // Called when the user clicks on the Get Current Place Information
    // button in the main Activity UI. This function calls the
    // getCurrentPlace API and loops through the list of results to
    // determine which place the user is most likely located in
    protected void getCurrentPlaceData() {
        mostLikelyPlace = null;
        getLocationPermission();

        if (mLocationPermissionGranted) {
            try {
                // getCurrentPlace, like many other Play Services APIs, returns
                // a PendingResult, which is then handled by setting a callback
                //PendingResult<PlaceLikelihoodBuffer> result;

                /*
                result = Places.PlaceDetectionApi.getCurrentPlace(mGoogleApiClient,null);

                result.setResultCallback(new ResultCallback<PlaceLikelihoodBuffer>() {

                    // the onResult function of the callback is given a list of
                    // PlaceLikelihood objects, from which we can determine what places
                    // the user is near and mostly likely located
                    @Override
                    public void onResult(@NonNull PlaceLikelihoodBuffer placeLikelihoods) {
                        for (PlaceLikelihood placeLikelihood : placeLikelihoods) {
                            // Log out the place name and likelihood
                            Log.i(TAG, String.format("Place '%s' has likelihood: %g",
                                    placeLikelihood.getPlace().getName(),
                                    placeLikelihood.getLikelihood()));

                                    */
                @SuppressWarnings("MissingPermission") final
                Task<PlaceLikelihoodBufferResponse> placeResult = mPlaceDetectionClient.getCurrentPlace(null);
                placeResult.addOnCompleteListener(new OnCompleteListener<PlaceLikelihoodBufferResponse>() {
                    @Override
                    public void onComplete(@NonNull Task<PlaceLikelihoodBufferResponse> task) {
                        PlaceLikelihoodBufferResponse likelyPlaces = task.getResult();
                        for (PlaceLikelihood placeLikelihood : likelyPlaces) {
                            Log.i(TAG, String.format("Place '%s' has likelihood: %g",
                                    placeLikelihood.getPlace().getName(),
                                    placeLikelihood.getLikelihood()));
                        //}

                        /*
                        likelyPlaces.release();
                    }
                });
                */

                            //  Determine the most likely place where the device is
                            if (mostLikelyPlace == null) {
                                mostLikelyPlace = placeLikelihood;
                            }
                            else {
                                // if this place is more likely than the current one, store
                                // it in our holding variable.
                                if (placeLikelihood.getLikelihood() > mostLikelyPlace.getLikelihood()) {
                                    mostLikelyPlace = placeLikelihood;
                                }
                            }
                        }

                        // if we've found a most likely place, get some information about it and
                        // set the content of the TextView in the Activity
                        if (mostLikelyPlace != null) {
                            Place tempPlace = mostLikelyPlace.getPlace();
                            String str = tempPlace.getName() + "\n"
                                    + tempPlace.getAddress() + "\n"
                                    + tempPlace.getPhoneNumber() + "\n";

                            if (tempPlace.getWebsiteUri() != null)
                                str += tempPlace.getWebsiteUri() + "\n";

                            List<Integer> typeList = tempPlace.getPlaceTypes();
                            str += "Types: " + typeList.toString();

                            TextView tvPlace = (TextView)findViewById(R.id.tvPlaceInfo);
                            tvPlace.setText(str);
                        }

                        // According to the Places API docs, we must display attributions
                        // to third parties if their content is used. This method retrieves
                        // any attributions, which are in HTML format. If there are any,
                        // they are displayed in a small TextView.
                        final CharSequence attribs = likelyPlaces.getAttributions(); //changed from placeLikelihoods
                        if (attribs != null && attribs.length() > 0) {
                            String attrStr = attribs.toString();
                            TextView tvAttribs = (TextView)findViewById(R.id.tvAttributions);
                            tvAttribs.setText(Html.fromHtml(attrStr));
                            // make the link clickable
                            tvAttribs.setMovementMethod(LinkMovementMethod.getInstance());
                        }

                        // IMPORTANT: always release the result buffer to avoid memory leaks
                        likelyPlaces.release(); //changed from placeLikelihoods
                    }
                });
            }
            catch (SecurityException e) {
                Log.d(TAG, e.toString());
            }
        }
    }

    /**
     * Standard Android Activity lifecycle methods
     */

/*
    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        switch (requestCode) {
            // If there was a problem connecting to the Play Services library and
            // we were able to resolve it, this code will passed in and
            // we can then connect to the library
            case RESOLVE_CONNECTION_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    mGoogleApiClient.connect();
                }
                break;
        }
    } */


    private void getDeviceLocation() {
    /*
     * Get the best and most recent location of the device, which may be null in rare
     * cases when a location is not available.
     */
        try {
            if (mLocationPermissionGranted) {
                Task locationResult = mFusedLocationProviderClient.getLastLocation();
                locationResult.addOnCompleteListener(this, new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            // Set the map's camera position to the current location of the device.
                            mLastKnownLocation = (Location) task.getResult();  //casting is ok??
                            //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                                    //new LatLng(mLastKnownLocation.getLatitude(),
                                            //mLastKnownLocation.getLongitude()), DEFAULT_ZOOM));
                        } else {
                            Log.d(TAG, "Current location is null. Using defaults.");
                            Log.e(TAG, "Exception: %s", task.getException());
                            //mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mDefaultLocation, DEFAULT_ZOOM));
                            //mMap.getUiSettings().setMyLocationButtonEnabled(false);
                        }
                    }
                });
            }
        } catch(SecurityException e)  {
            Log.e("Exception: %s", e.getMessage());
        }
    }

    /**
     * Called when the user has been prompted at runtime to grant permissions
     */
 /*   @Override
    public void onRequestPermissionsResult(int reqCode, String[] perms, int[] results){
        if (reqCode == 1) {
            if (results.length > 0 && results[0] == PackageManager.PERMISSION_GRANTED) {
                mPermissionLocationGranted = true;
            }
        }
    }
    */


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[],
                                           @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
        }
        //updateLocationUI();
    }


    private void getLocationPermission() {
    /*
     * Request location permission, so that we can get the location of the
     * device. The result of the permission request is handled by a callback,
     * onRequestPermissionsResult.
     */
        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mLocationPermissionGranted = true;
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        }
    }




    /**
     * Standard Google Play Services lifecycle callback methods
     */
/*    @Override
    public void onConnected(Bundle connectionHint) {
        Log.d(TAG, "Connected to Places API");

        // If we're running on API 23 or above, we need to ask permissions at runtime
        // In this case, we need Fine Location access to use Place Detection
        int permCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (permCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        else {
            mPermissionLocationGranted = true;
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult result) {
        Log.d(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());

        // Check to see if there's a way to resolve this problem. Usually the user can just
        // update Play Services and be on their way.
        if (result.hasResolution()) {
            try {
                result.startResolutionForResult(this, RESOLVE_CONNECTION_REQUEST_CODE);
            }
            catch (IntentSender.SendIntentException e) {
                // Unable to resolve - use this opportunity to message user appropriately
                Log.e(TAG, "Could not connect to Play Services");
            }
        }
        else {
            // If there's no resolution available to the problem, give the user an error dialog
            // (which will typically tell them how to fix what's wrong)
            GoogleApiAvailability gAPI = GoogleApiAvailability.getInstance();
            gAPI.getErrorDialog(this, result.getErrorCode(), 0).show();
        }
    }

    @Override
    public void onConnectionSuspended(int cause) {
        Log.d(TAG, "Connection was suspended for some reason: " + cause);
       // mGoogleApiClient.connect();
    }
    */
}
