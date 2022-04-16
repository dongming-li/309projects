package com.example.matthewburke.partyat;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
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
 * Created by Tyler Helmrich on 12/4/2017.
 */

/**
 * This Class is Responsible for receiving the pending friend requests the user may have
 */
public class FriendRequests {
    private final ListView LISTVIEW;
    private final String ID;
    private final Context CONTEXT;
    private final Activity ACTIVITY;
//    private final String[] FUNCTION = {"GFR", "DFR"};
    public FriendRequests(ListView listView, String id, Context context, Activity activity){
        this.LISTVIEW = listView;
        this.ID = id;
        this.CONTEXT = context;
        this.ACTIVITY = activity;
    }

    public void sendPendingRequest(final String function){
        //StringRequest stringRequest = new StringRequest(JSON_URL, listener, errorlistener);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://proj-309-am-b-5.cs.iastate.edu/insertFriends.php",
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        if(function.equals("GFR") && !response.equals("[null]")){
                            showPendingJSON(response);
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(CONTEXT, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            public Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("function", function);
                params.put("id", ID);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(CONTEXT);
        requestQueue.add(stringRequest);
    }

    /**
     * Displays the pending friend requests
     */
    private void showPendingJSON(String json){
        ParseFriendsJSON pend = new ParseFriendsJSON(json);
        pend.ParseJSON();
        PendingFriendsStrings pendStrings = new PendingFriendsStrings(CONTEXT,ID, ParseFriendsJSON.FirstNames, ParseFriendsJSON.LastNames, ParseFriendsJSON.UserNames);
        LISTVIEW.setAdapter(pendStrings);
//        final int i = 2;
//        Button deny = (Button) findViewById(i);
//        deny.setOnClickListener(new View.OnClickListener(){
//            public void onClick(View view){
//                sendPendingRequest(FUNCTION[1]);
//            }
//        });
    }

}

