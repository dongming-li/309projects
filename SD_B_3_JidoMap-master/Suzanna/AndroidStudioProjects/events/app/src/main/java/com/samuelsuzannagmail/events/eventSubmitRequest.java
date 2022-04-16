package com.samuelsuzannagmail.events;

/**
 * Created by Suzanna Gudivada on 11/28/2017.
 */

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class eventSubmitRequest {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText eventName = (EditText) findViewById(R.id.editText);
        final EditText eventLocation = (EditText) findViewById(R.id.editText2);
        final EditText eventTime = (EditText) findViewByID(R.id.editText3);
        final EditText eventDate = (EditText) findViewByID(R.id.editText4);

        final Button submit = (Button) findViewById(R.id.button1);


        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signupIntent = new Intent(eventSubmitRequest.this, eventSubmitRequest.class);
                eventSubmitRequest.this.startActivity(signupIntent);
            }
        });

        bSignIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String username = etUsername.getText().toString();
                final String password = etFriendID.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                };

                MainActivity signinRequest = new MainActivity(hostID, eventName, eventLocation, eventDate, eventTime, approved, responseListener);
                RequestQueue queue = Volley.newRequestQueue(eventSubmitRequest.this);
                AlertDialog.Builder builder = new AlertDialog.Builder(eventSubmitRequest.this);
                builder.setMessage(hostID +"," + eventName).setNegativeButton("Retry", null).create().show();
                queue.add(signinRequest);

            }
        });
    }





}
