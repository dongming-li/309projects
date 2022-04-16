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
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.HashMap;
import java.util.Map;

/** This class is used to retrieve the users information and to send it back with any changes that the user wants to be made. **/
public class BusinessProfile extends AppCompatActivity
        {
            //implements GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks

    private static final String JSON_FETCH_URL = "http://proj-309-am-b-5.cs.iastate.edu/fetchBusiness.php";
    private static final String JSON_UPDATE_URL = "http://proj-309-am-b-5.cs.iastate.edu/updateBusiness.php";

    SharedPreferences BUSINESSID;
    String businessID;

    ConstraintLayout businessLayout;


    /*Called from onResponse method to update EditText texts with profile info*/
    private void updateTextFields(String response){
        Log.d("updateTextFields", response);
        /*Update EditText text fields with response information*/
        try {
            JSONObject jObject = (JSONObject)new JSONTokener(response).nextValue();
            ((EditText)findViewById(R.id.etUserNameB)).setText(jObject.getString("Username"), TextView.BufferType.EDITABLE);
            ((EditText)findViewById(R.id.etBusinessTitle)).setText(jObject.getString("BusinessTitle"), TextView.BufferType.EDITABLE);
            ((EditText)findViewById(R.id.etPhoneB)).setText(jObject.getString("Phone"), TextView.BufferType.EDITABLE);
            ((EditText)findViewById(R.id.etEmailB)).setText(jObject.getString("Email"), TextView.BufferType.EDITABLE);
            ((EditText)findViewById(R.id.etPswdB)).setText(jObject.getString("Password"), TextView.BufferType.EDITABLE);
            ((EditText)findViewById(R.id.etAddress)).setText(jObject.getString("Address"), TextView.BufferType.EDITABLE);
            ((EditText)findViewById(R.id.etCity)).setText(jObject.getString("City"), TextView.BufferType.EDITABLE);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_profile);
        BUSINESSID = getApplicationContext().getSharedPreferences("preferences_file", Context.MODE_PRIVATE);
        businessID = BUSINESSID.getString("ID", "null");
        businessLayout = (ConstraintLayout) findViewById(R.id.clBusiness);


        /**Send request to retrieve profile information from server**/
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
                        Toast.makeText(BusinessProfile.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ){
            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("ID", businessID);
                Log.d("params", businessID);
                //params.put("function", "checkPassword");
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
        Log.d("From the Fetch", stringRequest.toString());



        findViewById(R.id.btnSaveProfileb).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        sendRequest();
                    }
                }
        );


        findViewById(R.id.btnLogOutb).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent myIntent = new Intent(BusinessProfile.this,
                                HomeScreen.class);
                        startActivity(myIntent);
                    }
                }
        );

        businessLayout.setOnTouchListener(new OnSwipeTouchListener(BusinessProfile.this) {
            public void onSwipeTop() {
                Intent myIntent = new Intent(BusinessProfile.this,
                            BusinessMenuScreen.class);
                startActivity(myIntent);
            }
        });
    }

    /** If successful, send user back to their menu scree. **/
    public void onSuccess(String Response){
        if(Response.equals("1")){
            Intent myIntent = new Intent(BusinessProfile.this,
                    BusinessMenuScreen.class);
            startActivity(myIntent);
        }

    };

    /** Send request to update profile information **/
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
                params.put("ID", businessID);
                params.put("username", ((EditText)findViewById(R.id.etUserNameB)).getText().toString());
                params.put("businessTitle", ((EditText)findViewById(R.id.etBusinessTitle)).getText().toString());
                params.put("phone", ((EditText)findViewById(R.id.etPhoneB)).getText().toString());
                params.put("email", ((EditText)findViewById(R.id.etEmailB)).getText().toString());
                params.put("password", ((EditText)findViewById(R.id.etPswdB)).getText().toString());
                params.put("address", ((EditText)findViewById(R.id.etAddress)).getText().toString());
                params.put("city", ((EditText)findViewById(R.id.etCity)).getText().toString());
                Log.d("params", params.toString());
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(sendRequest);
    }



}
