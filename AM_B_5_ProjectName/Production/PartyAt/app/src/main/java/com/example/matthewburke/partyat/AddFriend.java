package com.example.matthewburke.partyat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
 * Created by Tyler Helmrich on 11/28/2017.
 * The AddFriend Class handles making new friend requests to other users of the app
 */

public class AddFriend extends AppCompatActivity implements View.OnClickListener {

    private Button addFriendButton;
    private Button cancelButton;
    private EditText userName;
    private SharedPreferences USERID;
    private String ID;

    @Override
    /**
     * Inflates the layout and sets the listeners for the addfriend and cancel buttons
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        addFriendButton = (Button) findViewById(R.id.addNewFriendButton);
        cancelButton = (Button) findViewById(R.id.cancelFriendRequestButton);
        userName = (EditText) findViewById(R.id.addNewFriendEditText);

        USERID = getApplicationContext().getSharedPreferences(getString(R.string.PREFERENCES), Context.MODE_PRIVATE);
        ID = USERID.getString(getString(R.string.USERID),"null");


        cancelButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent myIntent = new Intent(AddFriend.this,
                        FriendsList.class);
                startActivity(myIntent);
            }
        });

        addFriendButton.setOnClickListener(this);
    }

    /**
     * Makes a network request to the insertfriend php file and add the users ID to the appropriate user
     * in the database
     */
    private void sendRequest(){
        StringRequest sendRequest = new StringRequest(Request.Method.POST,
                "http://proj-309-am-b-5.cs.iastate.edu/insertFriends.php",
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
                params.put("function", "MFR");
                params.put("name", userName.getText().toString());
                params.put("id", ID);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(sendRequest);
    }

    /**
     * Returns the User to the FriendsList screen if the friend request was successful
     */
    private void onSuccess(String Response){
        if(!Response.equals("Update Failed")){
            Intent myIntent = new Intent(AddFriend.this,
                    FriendsList.class);
            startActivity(myIntent);
        }
        else{
            Log.d("Failed Response", Response);
        }
    }

    public void onClick(View view){
        Log.d("uname: ", userName.getText().toString());
        sendRequest();}

}
