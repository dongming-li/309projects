package com.example.rajivbhoopala.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.all.All;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity
{
    /**
     * Setting up the login page, and button and also connect with the register
     * @param savedInstanceState
     */

    TextView txtStatus;
    LoginButton login_Button;
    CallbackManager callbackManager;
    boolean fb = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);
        callbackManager = CallbackManager.Factory.create();
        final TextView registerLink = findViewById(R.id.tvRegHere);

        FacebookSdk.sdkInitialize(getApplicationContext());
        initilaizeControls();
        loginWithFB();


        registerLink.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        findViewById(R.id.butLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonClick();
            }
        });


    }

    //@Override
    /*public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.login_button:
                LoginManager.getInstance().logInWithReadPermissions(
                        this,
                        Arrays.asList("user_friends", "email", "public_profile"));

                LoginManager.getInstance().registerCallback(callbackManager,
                        new FacebookCallback<LoginResult>() {
                            @Override
                            public void onSuccess(LoginResult loginResult) {
                                setFacebookData(loginResult);
                            }

                            @Override
                            public void onCancel() {
                            }

                            @Override
                            public void onError(FacebookException exception) {
                            }
                        });
                break;
        }
    }*/

    private void setFacebookData(final LoginResult loginResult) {
        GraphRequest request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        // Application code
                        try {
                            Log.i("Response", response.toString());

                            //String email = response.getJSONObject().getString("email");
                            String ID = response.getJSONObject().getString("id");
                            String firstName = response.getJSONObject().getString("first_name");
                            String lastName = response.getJSONObject().getString("last_name");
                            String gender = response.getJSONObject().getString("gender");

                            LogUserIn(ID, firstName, lastName);
                            Profile profile = Profile.getCurrentProfile();
                            String id = profile.getId();
                            String link = profile.getLinkUri().toString();
                            Log.i("Link", link);
                            if (Profile.getCurrentProfile() != null) {
                                Log.i("Login", "ProfilePic" + Profile.getCurrentProfile().getProfilePictureUri(200, 200));
                            }

                          //  Log.i("Login" + "Email", email);
                            Log.i("Login" + "FirstName", firstName);
                            Log.i("Login" + "LastName", lastName);
                            Log.i("Login" + "Gender", gender);


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,email,first_name,last_name,gender");
        request.setParameters(parameters);
        request.executeAsync();
    }

    private void LogUserIn(String IDD, String firstName, String lastName)
    {
        String URL = "http://proj-309-am-b-4.cs.iastate.edu/Validate_Login.php?username=" + IDD +
                "&password=Facebook";

        final String ID = IDD;
        final String first = firstName;
        final String last = lastName;

        StringRequest StringReq = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    //Function that says what to do when a response is given.
                    @Override
                    public void onResponse(String response) {
                        int status = Integer.parseInt(response);

                        Bundle bundle = new Bundle(); // This bundle gets the login status and passes it to main activity
                        Bundle storeUser = new Bundle(); // This bundle stores the username
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                        if (status == 0)
                            RegisterUser(ID, first, last);

                        else if (status == 1) {
                            storeUser.putString("username", ID);
                            intent.putExtras(storeUser);
                            bundle.putInt("login_status", 1);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            new Utility(LoginActivity.this).displayLongToast("Welcome " + first);
                        }
                        else if (status == 2) {
                            storeUser.putString("username", ID);
                            intent.putExtras(storeUser);
                            bundle.putInt("login_status", 2);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            new Utility(LoginActivity.this).displayLongToast("Welcome " + first);
                        }
                        else if(status == 3) {
                            storeUser.putString("username", ID);
                            intent.putExtras(storeUser);
                            bundle.putInt("login_status", 3);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            new Utility(LoginActivity.this).displayLongToast("Welcome " + first);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                    }
                });

        //Adds the request to the queue. If this wasnt here the string request would never be
        //processed
        MySingleton.getInstance(this).addToRequestQueue(StringReq);
    }

    private void RegisterUser(String ID, String first, String last)
    {
        String URL = "http://proj-309-am-b-4.cs.iastate.edu/Register_User.php?username=" + ID + "&password=" + "Facebook" + "&name="
                + first + " " + last + "&email=face@facebook.com";

        System.out.println(URL);
        StringRequest StringReq = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    //Function that says what to do when a response is given.
                    @Override
                    public void onResponse(String response) {

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
    }
    /*public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_main);
        return true;
    }*/

    /**
     * Setting up what happens when the button is clicked
     * Getting help from the server to store the login page and set up the three different users
     */
    public void onButtonClick()
    {
        EditText user_editText = findViewById(R.id.user);
        final String user_string = user_editText.getText().toString();
        EditText pass_editText = findViewById(R.id.thePass);
        String pass_string = pass_editText.getText().toString();

        String URL = "http://proj-309-am-b-4.cs.iastate.edu/Validate_Login.php?username=" + user_string +
                "&password=" + pass_string;

        StringRequest StringReq = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    //Function that says what to do when a response is given.
                    @Override
                    public void onResponse(String response) {
                        int status = Integer.parseInt(response);

                        Bundle bundle = new Bundle(); // This bundle gets the login status and passes it to main activity
                        Bundle storeUser = new Bundle(); // This bundle stores the username
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        if(fb) {
                            status = 3;

                            /*@Override
                                   public void onUserInfoFetched(GraphUser user){

                            }*/

                        }
                        if (status == 0)
                            new Utility(LoginActivity.this).displayLongToast("Invalid Credentials, please try again.");

                        else if (status == 1) {
                            storeUser.putString("username", user_string);
                            intent.putExtras(storeUser);
                            bundle.putInt("login_status", 1);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            new Utility(LoginActivity.this).displayLongToast("Welcome " + user_string);
                        }
                        else if (status == 2) {
                            storeUser.putString("username", user_string);
                            intent.putExtras(storeUser);
                            bundle.putInt("login_status", 2);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            new Utility(LoginActivity.this).displayLongToast("Welcome " + user_string);
                        }
                        else if(status == 3) {
                            storeUser.putString("username", user_string);
                            intent.putExtras(storeUser);
                            bundle.putInt("login_status", 3);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            new Utility(LoginActivity.this).displayLongToast("Welcome " + user_string);
                        }
                }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle error
                    }
                });

        //Adds the request to the queue. If this wasnt here the string request would never be
        //processed
        MySingleton.getInstance(this).addToRequestQueue(StringReq);
    }

    public void initilaizeControls(){
        callbackManager = CallbackManager.Factory.create();
        txtStatus = (TextView)findViewById(R.id.txtStatus);
        login_Button = (LoginButton)findViewById(R.id.login_button);

    }

    public void loginWithFB(){
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                fb = true;
                onButtonClick();
                txtStatus.setText("Login Success\n");
                setFacebookData(loginResult);
            }

            @Override
            public void onCancel() {
                txtStatus.setText("Login Cancelled");

            }

            @Override
            public void onError(FacebookException error) {
                txtStatus.setText("Login Error: " + error.getMessage());
            }
        });
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
