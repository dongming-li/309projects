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
/**
 * Created by Tyler Helmrich on 10/23/2017.
 */

/** This class takes in a username from the user and checks to make sure the username is in the User table.**/
public class CheckUserName extends AppCompatActivity implements View.OnClickListener {
    //public static final String PREF = "UserPreference";
    private EditText userName;
    Button nextButton;
    SharedPreferences USERID;
    SharedPreferences.Editor editor;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_username_login);
        userName= (EditText) findViewById(R.id.userName);
        nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(this);
        USERID = getApplicationContext().getSharedPreferences(getString(R.string.PREFERENCES), Context.MODE_PRIVATE);
        editor = USERID.edit();
    }

    /** Send the request to the server to check if the username matches any in the database.**/
    private void sendRequest(){
        StringRequest sendRequest = new StringRequest(Request.Method.POST,
                "http://proj-309-am-b-5.cs.iastate.edu/UsersTableFunctions.php",
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
                params.put("userName", userName.getText().toString());
                params.put("function", "checkUserName");
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(sendRequest);
    }

    /** When successful, save the ID and set user type to the one retrieved from the table. **/
    public void onSuccess(String Response){
        if(!Response.equals("false")){
            editor.putString(getString(R.string.USERID), Response.substring(0,Response.length()-2));
            editor.commit();
            editor.putString(getString(R.string.USERTYPE), ""+Response.charAt(Response.length()-1));
            editor.commit();
            Intent myIntent = new Intent(CheckUserName.this,
                    CheckUserPassword.class);
            startActivity(myIntent);
        }

    }


    public void onClick(View view){
        sendRequest();
    }
}
