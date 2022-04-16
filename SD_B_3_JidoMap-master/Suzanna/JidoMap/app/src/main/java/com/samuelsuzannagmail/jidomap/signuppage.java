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

public class signuppage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signuppage);

        final EditText etusername = (EditText) findViewById(R.id.editText6);
        final EditText etemail = (EditText) findViewById(R.id.editText9);
        final EditText etpassword = (EditText) findViewById(R.id.editText12);


        final Button bSignIn = (Button) findViewById(R.id.buttonSignUp);

//        Button b1=(Button)findViewById(R.id.button5);
//
//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(),maphome.class);
//                startActivity(i);
//            }
//        });

        bSignIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String username = etusername.getText().toString();
                final String email = etemail.getText().toString();
                final String password = etpassword.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                };

                signuppageRequest signuppageRequest = new signuppageRequest(username, email, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(signuppage.this);
                AlertDialog.Builder builder = new AlertDialog.Builder(signuppage.this);
                builder.setMessage("Hello "+ username +"! You are signed in!").setNegativeButton("Close and log in!", null).create().show();
                queue.add(signuppageRequest);

            }
        });


    }
}
