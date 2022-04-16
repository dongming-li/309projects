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

/** This class takes in a password from the user and checks to make sure the password matches the one for the ID.**/
public class CheckUserPassword extends AppCompatActivity implements View.OnClickListener {
    private EditText password;
    Button loginButton;
    SharedPreferences USERID;
    String userID;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_login);
        password= (EditText) findViewById(R.id.password);
        loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(this);
        USERID = getApplicationContext().getSharedPreferences(getString(R.string.PREFERENCES), Context.MODE_PRIVATE);
        userID = USERID.getString(getString(R.string.USERID),"null");
        Log.d("userID",userID);
    }

    /** Send the request to the server to check if the password matches the password for the ID.**/
    private void sendRequest(){
        StringRequest sendRequest = new StringRequest(Request.Method.POST,
                "http://proj-309-am-b-5.cs.iastate.edu/UsersTableFunctions.php",
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
                params.put("userID", userID);
                Log.d("puserid", userID);
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
            Intent myIntent = new Intent(CheckUserPassword.this,
                    MenuScreen.class);
            startActivity(myIntent);
        }
    }


    public void onClick(View view){
        sendRequest();
    }
}
