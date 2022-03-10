package com.example.rajivbhoopala.myapplication;

import android.view.View;
import android.widget.EditText;

import  android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class RegisterActivity extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main_register);

            findViewById(R.id.butRegister).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onSignUpClick();
                }
            });
        }

    /**
     * Upon sign-up button click, user data is sent to database so user can now login.
     */
    public void onSignUpClick() {
                EditText theEmail = findViewById(R.id.theEmail);
                EditText theName = findViewById(R.id.theName);
                EditText theUsername = findViewById(R.id.user);
                EditText password = findViewById(R.id.thePass);
                EditText password2 = findViewById(R.id.thePass);

                String name = theName.getText().toString();
                String username = theUsername.getText().toString();
                String pass = password.getText().toString();
                String pass2 = password2.getText().toString();
                String email = theEmail.getText().toString();

            if ((pass.equals(pass2)) && (!name.matches("")) || (!username.matches(""))) {
                String URL = "http://proj-309-am-b-4.cs.iastate.edu/Register_User.php?username=" + username + "&password=" + pass + "&name="
                        + name + "&email=" + email;

                System.out.println(URL);
                StringRequest StringReq = new StringRequest(Request.Method.GET, URL,
                        new Response.Listener<String>() {
                            //Function that says what to do when a response is given.
                            @Override
                            public void onResponse(String response) {
                                //TODO Go to the user Activity.
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Handle error
                            }
                        });


                //Adds the request to the queue. If this wasnt here the string request would never be
                //processd
                MySingleton.getInstance(this).addToRequestQueue(StringReq);

                new Utility(this).displayLongToast("Registration Successful!");
                new Utility(this).launchActivity(this, VitalsRegistration.class);
            }
            else
                System.out.println("Password Mismatch");
        }
    }

