package com.example.johnpark.jido;

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

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etPassword = (EditText) findViewById(R.id.etPassword);

        final Button bSignIn = (Button) findViewById(R.id.bSignIn);
        final Button bSignUp = (Button) findViewById(R.id.bSignUp);

        bSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signupIntent = new Intent(SignInActivity.this, SignUpActivity.class);
                SignInActivity.this.startActivity(signupIntent);
            }
        });

        bSignIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String username = etUsername.getText().toString();
                final String password = etPassword.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if(success){
                                String name = jsonResponse.getString("name");

                                Intent intent = new Intent(SignInActivity.this, UserAreaActivity.class);
                                intent.putExtra("name", name);
                                intent.putExtra("username", username);

                                SignInActivity.this.startActivity(intent);
                            }else{
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignInActivity.this);
                                builder.setMessage("Sign In Failed").setNegativeButton("Retry", null).create().show();
                            }

                        } catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                };

                SignInRequest signinRequest = new SignInRequest(username, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(SignInActivity.this);
                queue.add(signinRequest);

            }
        });
    }
}
