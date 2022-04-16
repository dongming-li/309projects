package com.example.matthewburke.partyat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

//import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.HashMap;
import java.util.Map;


/** This class is used to retrieve the users information and to send it back with any changes that the user wants to be made.
 * Also lets the user check in their location.**/

public class UserProfile extends AppCompatActivity
        implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks{

    ConstraintLayout conLayout;

    private final String JSON_FETCH_URL = "http://proj-309-am-b-5.cs.iastate.edu/fetchUser.php";
    private final String JSON_UPDATE_URL = "http://proj-309-am-b-5.cs.iastate.edu/updateUser.php";

    SharedPreferences USERNAME;
    SharedPreferences.Editor editor;



    /*Place Picker Necessities*/
    private final static String TAG = "USER_PROFILE_ACTIVITY";

    private final int RESOLVE_CONNECTION_REQUEST_CODE = 1000;
    private final int  PLACE_PICKER_REQUEST = 1001;

    protected GoogleApiClient mGoogleApiClient;
    protected GeoDataClient mGeoDataClient;
    private boolean mHaveLocPerm = false;
    private boolean mPlacePicked = false;

    private Place chosenPlace;

    /** Launches the Google API intent PlacePicker for result
     */
    private void pickAPlace() {
        if (mHaveLocPerm) {
            try {
                // to the onActivityResult function below
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                Intent intent = builder.build(this);

                startActivityForResult(intent, PLACE_PICKER_REQUEST);
            }
            catch (Exception e) {
                Log.e(TAG, e.toString());
            }
        }
    }

    /**
     * Uses the place information to update the textviews containing user profile information
     * @param chosenPlace Place object containing the place information from the place selected in
     *                    the place picker.
     */
    private void updateUI(Place chosenPlace) {
        String placeName = chosenPlace.getName() + "";
        String placeAddress = chosenPlace.getAddress() + "";


        ((TextView)findViewById(R.id.tvLocationNameTxt)).setText(placeName);
        ((TextView)findViewById(R.id.tvLocationAddrTxt)).setText(placeAddress);
    }

    /**
     * Called from onResponse method to update EditText texts with profile info.
     * If the user already has a place ID as a location then the place information
     * is retrieved using GeoDataClient.getPlaceById()
     * @param response String containing the json information from the server
     */
    private void updateTextFields(String response){
        Log.d("updateTextFields", response);
        /*Update EditText text fields with response information*/
        try {
            JSONObject jObject = (JSONObject)new JSONTokener(response).nextValue();
            ((EditText)findViewById(R.id.etUserName)).setText(jObject.getString("Username"), TextView.BufferType.EDITABLE);
            ((EditText)findViewById(R.id.etFirstName)).setText(jObject.getString("FirstName"), TextView.BufferType.EDITABLE);
            ((EditText)findViewById(R.id.etLastName)).setText(jObject.getString("LastName"), TextView.BufferType.EDITABLE);
            ((EditText)findViewById(R.id.etPswd)).setText(jObject.getString("Password"), TextView.BufferType.EDITABLE);
            ((EditText)findViewById(R.id.etPhone)).setText(jObject.getString("Phone"), TextView.BufferType.EDITABLE);
            ((EditText)findViewById(R.id.etEmail)).setText(jObject.getString("Email"), TextView.BufferType.EDITABLE);
            String userLocation = jObject.getString("Location");
            if(userLocation.compareTo("") != 0) {
                mGeoDataClient.getPlaceById(userLocation).addOnCompleteListener(new OnCompleteListener<PlaceBufferResponse>() {
                    @Override
                    public void onComplete(@NonNull Task<PlaceBufferResponse> task) {
                        if (task.isSuccessful()) {
                            PlaceBufferResponse places = task.getResult();
                            chosenPlace = places.get(0);
                            mPlacePicked = true;
                            Log.i(TAG, "Place found: " + chosenPlace.getName());
                            updateUI(chosenPlace);

                            editor.putFloat("LATITUDE", (float) chosenPlace.getLatLng().latitude);
                            editor.commit();
                            editor.putFloat("LONGITUDE", (float) chosenPlace.getLatLng().longitude);
                            editor.commit();

                            places.release();
                        } else {
                            Log.e(TAG, "Place not found.");
                        }
                    }
                });
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        USERNAME = getApplicationContext().getSharedPreferences(getString(R.string.PREFERENCES), Context.MODE_PRIVATE);

        editor = USERNAME.edit();

        conLayout  = findViewById(R.id.clUser);

        // Build the GoogleApiClient and connect to the Places API
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();

        mGeoDataClient = Places.getGeoDataClient(this, null);

        /*
        firstName = findViewById(R.id.etFirstName);
        lastName = findViewById(R.id.etLastName);
        password = findViewById(R.id.etPswd);
        phone = findViewById(R.id.etPhone);
        email = findViewById(R.id.etEmail);
        */

        /**
         * Send request to retrieve profile information from server on startup so text
         * fields are populated when the activity loads.
         */
        StringRequest stringRequest = new StringRequest(Request.Method.POST, JSON_FETCH_URL,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        updateTextFields(response);
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(UserProfile.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("userName", USERNAME.getString(getString(R.string.USERID),"null"));
                Log.d("params", USERNAME.getString(getString(R.string.USERID),"null"));
                //params.put("function", "checkPassword");
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        /*Store profile information into Profile object*/
        //Profile user = new Profile();
        //user.setUserName("example");

        /*Set the edit texts with the preexisting user profile information*/
        //userName.setText(user.getUserName());

        /*Initialize button on click listeners*/
        findViewById(R.id.btnCheckIn).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pickAPlace();
                    }
                }
        );

        findViewById(R.id.btnSaveProfile).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendRequest();
                    }
                }
        );

        findViewById(R.id.btnLogOut).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent myIntent = new Intent(UserProfile.this,
                                HomeScreen.class);
                        startActivity(myIntent);
                    }
                }
        );


       conLayout.setOnTouchListener(new OnSwipeTouchListener(UserProfile.this) {
            public void onSwipeTop() {
                Intent myIntent = new Intent(UserProfile.this,
                        MenuScreen.class);
                startActivity(myIntent);
            }
        });
    }

    /**
     * If successful, send user back to their menu screen.
     */
    public void onSuccess(String Response){
        if(Response.equals("1")){
            Intent myIntent = new Intent(UserProfile.this,
                    MenuScreen.class); //change to CheckBusinessPassword
            startActivity(myIntent);
        }
        else
            Toast.makeText(UserProfile.this, "Response: " + Response, Toast.LENGTH_LONG).show();
    }



    /** Send request to update profile information. Takes information currently in edit text
     * fields and concatenates them into the parameters of the html request.
     */
    private void sendRequest(){
        StringRequest sendRequest = new StringRequest(Request.Method.POST,
                JSON_UPDATE_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String Response) {
                        // get response
                        Log.d("Response", Response);
                        onSuccess(Response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError e) {
                        e.printStackTrace();
                    }
                })
        {
            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("userName", ((EditText)findViewById(R.id.etUserName)).getText().toString());
                params.put("firstName", ((EditText)findViewById(R.id.etFirstName)).getText().toString());
                params.put("lastName", ((EditText)findViewById(R.id.etLastName)).getText().toString());
                params.put("password", ((EditText)findViewById(R.id.etPswd)).getText().toString());
                params.put("phone", ((EditText)findViewById(R.id.etPhone)).getText().toString());
                params.put("email", ((EditText)findViewById(R.id.etEmail)).getText().toString());
                if(mPlacePicked)
                    params.put("location", chosenPlace.getId());
                else
                    params.put("location", "");
                Log.d("params", params.toString());
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(sendRequest);
    }

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
            // This code is passed when the user has resolved whatever connection
            // problem there was with the Google Play Services library
            case RESOLVE_CONNECTION_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    mGoogleApiClient.connect();
                }
                break;

            case PLACE_PICKER_REQUEST:
                if (resultCode == RESULT_OK) {
                    chosenPlace = PlacePicker.getPlace(this, data);
                    mPlacePicked = true;
                    updateUI(chosenPlace);
                }
                break;
        }
    }

    /**
     * Called when the user has been prompted at runtime to grant permissions
     */
    @Override
    public void onRequestPermissionsResult(int reqCode, String[] perms, int[] results){
        if (reqCode == 1) {
            if (results.length > 0 && results[0] == PackageManager.PERMISSION_GRANTED) {
                mHaveLocPerm = true;
            }
        }
    }

    /**
     * Standard Google Play Services lifecycle callbacks
     */
    @Override
    public void onConnected(Bundle connectionHint) {
        Log.d(TAG, "Connected to Places API");

        // If we're running on API 23 or above, we need to ask permissions at runtime
        // In this case, we need Fine Location access to use Place Detection
        int permCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (permCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
        else {
            mHaveLocPerm = true;
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult result) {
        Log.d(TAG, "Connection failed: ConnectionResult.getErrorCode() = " + result.getErrorCode());
        if (result.hasResolution()) {
            try {
                result.startResolutionForResult(this, RESOLVE_CONNECTION_REQUEST_CODE);
            }
            catch (IntentSender.SendIntentException e) {
                // Unable to resolve, message user appropriately
            }
        }
        else {
            GoogleApiAvailability gAPI = GoogleApiAvailability.getInstance();
            gAPI.getErrorDialog(this, result.getErrorCode(), 0).show();
        }
    }

    @Override
    public void onConnectionSuspended(int cause) {
        Log.d(TAG, "Connection was suspended for some reason");
        mGoogleApiClient.connect();
    }
}
