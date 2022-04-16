package com.example.matthewburke.partyat;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/**
 * This Class is responsible for displaying all of the friends and pending friends the user has
 */
public class FriendsList extends AppCompatActivity implements View.OnClickListener{
    private static final String JSON_URL = "http://proj-309-am-b-5.cs.iastate.edu/getFriendsList.php" ;
    private static final String PENDING_JSON_URL = "http://proj-309-am-b-5.cs.iastate.edu/insertFriends.php";
    private static final String[] FUNCTION = {"GFR","DFR"};

    private Button syncButton;
    private Button addFriendButton;
    private LinearLayout friendList;
    private ListView friendListView;
    private ListView pendingFriendListView;
    private SharedPreferences USERID;
    private String ID;


    @Override
    /**
     * Sends the requests for the users friends and pending friends
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);

        syncButton = (Button) findViewById(R.id.syncButton);
        addFriendButton = (Button) findViewById(R.id.addFriendButton);
        syncButton.setOnClickListener(this);
        friendList = findViewById(R.id.llFriendsList);
        friendListView = findViewById(R.id.friendListView);
        pendingFriendListView = findViewById(R.id.pendingFriendListView);
        USERID = getApplicationContext().getSharedPreferences(getString(R.string.PREFERENCES), Context.MODE_PRIVATE);
        ID = USERID.getString(getString(R.string.USERID), "null");
        FriendRequests friendrequest = new FriendRequests(pendingFriendListView,ID,this,this);
        friendrequest.sendPendingRequest(FUNCTION[0]);
        //sendPendingRequest(FUNCTION[0]);
        sendRequest();
        Log.d("ID",ID);


        //sendRequest();

        friendList.setOnTouchListener(new OnSwipeTouchListener(FriendsList.this) {
            public void onSwipeLeft() {
                Intent myIntent = new Intent(FriendsList.this,
                        MenuScreen.class);
                startActivity(myIntent);
            }
        });

        //sendRequest();



        addFriendButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                Intent myIntent = new Intent(FriendsList.this,
                        AddFriend.class);
                startActivity(myIntent);
            }
        });
 //sendRequest();

    }


    private void sendRequest(){
        //StringRequest stringRequest = new StringRequest(JSON_URL, listener, errorlistener);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                JSON_URL,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        showJSON(response);
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(FriendsList.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("ID", ID);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json){
        ParseFriendsJSON pj = new ParseFriendsJSON(json);
        pj.ParseJSON();
        FriendsStrings ma = new FriendsStrings(this, ParseFriendsJSON.FirstNames, ParseFriendsJSON.LastNames, ParseFriendsJSON.UserNames);
        friendListView.setAdapter(ma);
    }

//    private void sendPendingRequest(final String function){
//        //StringRequest stringRequest = new StringRequest(JSON_URL, listener, errorlistener);
//        StringRequest stringRequest = new StringRequest(Request.Method.POST,
//                PENDING_JSON_URL,
//                new Response.Listener<String>(){
//                    @Override
//                    public void onResponse(String response){
//                        if(function.equals("GFR")){
//                            showPendingJSON(response);
//                        }
//                        else if(function.equals("DFR")){
//                            finish();
//                            startActivity(getIntent());
//                        }
//                    }
//                },
//                new Response.ErrorListener(){
//                    @Override
//                    public void onErrorResponse(VolleyError error){
//                        Toast.makeText(FriendsList.this, error.getMessage(), Toast.LENGTH_LONG).show();
//                    }
//                })
//        {
//            @Override
//            public Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<>();
//                params.put("function", function);
//                params.put("id", ID);
//                return params;
//            }
//
//        };
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
//    }
//
//    private void showPendingJSON(String json){
//        ParseFriendsJSON pend = new ParseFriendsJSON(json);
//        pend.ParseJSON();
//        PendingFriendsStrings pendStrings = new PendingFriendsStrings(this, ParseFriendsJSON.FirstNames, ParseFriendsJSON.LastNames, ParseFriendsJSON.UserNames);
//        pendingFriendListView.setAdapter(pendStrings);
//        final int i = 2;
//        Button deny = (Button) findViewById(i);
//        deny.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View view){
//                sendPendingRequest(FUNCTION[1]);
//            }
//        });
//    }

    @Override
    public void onClick(View v){
        sendRequest();
    }
}
