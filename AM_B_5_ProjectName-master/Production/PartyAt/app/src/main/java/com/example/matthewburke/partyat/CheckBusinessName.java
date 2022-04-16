package com.example.matthewburke.partyat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

/** This class takes in a username from the user and checks to make sure the username is in the Businesses table.**/
public class CheckBusinessName extends AppCompatActivity implements View.OnClickListener{

    private EditText businessName;
    Button nextButton;
    SharedPreferences BUSINESSID;
    SharedPreferences.Editor editor;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_business_name);
        businessName= (EditText) findViewById(R.id.businessName);
        nextButton = (Button) findViewById(R.id.nextButton2);
        nextButton.setOnClickListener(this);
        BUSINESSID = getApplicationContext().getSharedPreferences(getString(R.string.PREFERENCES), Context.MODE_PRIVATE);
        editor = BUSINESSID.edit();
    }

    /** Send the request to the server to check if the username matches any in the database.**/
    private void sendRequest(){
        StringRequest sendRequest = new StringRequest(Request.Method.POST,
                "http://proj-309-am-b-5.cs.iastate.edu/BusinessesTableFunctions.php",
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
                params.put("userName", businessName.getText().toString());
                params.put("function", "checkUserName");
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(sendRequest);
    }

    /** When successful, save the ID and set user type to "b' **/
    public void onSuccess(String Response){
        if(!Response.equals("false")){
            Log.d("length", ""+Response.length());
            editor.putString("ID", Response.substring(0,Response.length()-1));
            editor.commit();
            editor.putString(getString(R.string.USERTYPE), "b");
            editor.commit();
            Intent myIntent = new Intent(CheckBusinessName.this,
                    CheckBusinessPassword.class); //change to CheckBusinessPassword
            startActivity(myIntent);
        }

    }


    public void onClick(View view){
        sendRequest();
    }

}
