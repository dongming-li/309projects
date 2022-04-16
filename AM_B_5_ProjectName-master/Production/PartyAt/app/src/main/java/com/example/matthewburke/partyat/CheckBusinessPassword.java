package com.example.matthewburke.partyat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/** This class takes in a password from the user and checks to make sure the password matches the one for the ID.**/
public class CheckBusinessPassword extends AppCompatActivity implements View.OnClickListener{

    private EditText password;
    Button loginButton;
    SharedPreferences BUSINESSID;
    String businessID;

    /** This class takes in a password from the user and checks to make sure the password is the correct one for that ID. **/
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_business_password);
        password= (EditText) findViewById(R.id.password2);
        loginButton = (Button) findViewById(R.id.loginButton2);
        loginButton.setOnClickListener(this);
        BUSINESSID = getApplicationContext().getSharedPreferences("preferences_file", Context.MODE_PRIVATE);
        businessID = BUSINESSID.getString("ID","null");
        Log.d("ID",businessID);
    }

    /** Send the request to the server to check if the password matches the password for the ID.**/
    private void sendRequest(){
        StringRequest sendRequest = new StringRequest(Request.Method.POST,
                "http://proj-309-am-b-5.cs.iastate.edu/BusinessesTableFunctions.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String Response) {
                        // get response
                        Log.d("Password Response", Response);
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
                params.put("businessID", businessID);
                Log.d("puserid", businessID);
                params.put("password", password.getText().toString());
                params.put("function", "checkPassword");
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(sendRequest);
    }

    public void onSuccess(String Response){
        if(Response.equals(password.getText().toString())){
            Intent myIntent = new Intent(CheckBusinessPassword.this,
                    BusinessMenuScreen.class);
            startActivity(myIntent);
        }
    }


    public void onClick(View view){
        sendRequest();
    }
}
