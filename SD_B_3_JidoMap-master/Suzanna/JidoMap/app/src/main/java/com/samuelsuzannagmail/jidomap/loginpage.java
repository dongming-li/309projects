package com.samuelsuzannagmail.jidomap;


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


public class loginpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        final EditText etUsername = (EditText) findViewById(R.id.editText8);
        final EditText etFriendID = (EditText) findViewById(R.id.editText7);

        final Button bSignIn = (Button) findViewById(R.id.button9);
        Button b1=(Button)findViewById(R.id.button5);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),maphome.class);
                startActivity(i);
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
                loginpageRequest signinRequest = new loginpageRequest(username, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(loginpage.this);
                AlertDialog.Builder builder = new AlertDialog.Builder(loginpage.this);
                builder.setMessage("Hello "+ username +"! You are logged in!").setNegativeButton("Close", null).create().show();
                queue.add(signinRequest);

            }
        });





    }
}
