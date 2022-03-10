package com.example.matthewburke.partyat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

/** Creates a list showing the 20 nearest location names and icons as well as how many of the user's friends are at each location.
 * @author Matthew Burke */
public class NearbyEstablishments extends AppCompatActivity {
    /** Location of file on server*/
    private static final String JSON_URL = "http://proj-309-am-b-5.cs.iastate.edu/NearbyLocRequest.php" ;
    /** Location of file on server*/
    private static final String JSON_URL2 = "http://proj-309-am-b-5.cs.iastate.edu/GetFriendsListWithLoc.php" ;

    SharedPreferences LATITUDE;
    SharedPreferences LONGITUDE;
    private String latitude = "42.022339";
    private String longitude = "-93.650413";
    private String functionToCall = "getAll";
    private ListView locList;
    private SharedPreferences USERID;
    private String ID;
    private String[] friendsLocs;

    /** Retrieves latitude and longitude values from shared preferences, calls sendRequestFriends, and allows swiping back to MenuScreen */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_establishments);

        USERID = getApplicationContext().getSharedPreferences(getString(R.string.PREFERENCES), Context.MODE_PRIVATE);
        ID = USERID.getString(getString(R.string.USERID), "null");

        locList = (ListView) findViewById(R.id.locList);

        LATITUDE = getApplicationContext().getSharedPreferences(getString(R.string.PREFERENCES), Context.MODE_PRIVATE);
        LONGITUDE = getApplicationContext().getSharedPreferences(getString(R.string.PREFERENCES), Context.MODE_PRIVATE);

        latitude = "" + LATITUDE.getFloat("LATITUDE",(float) 42.022339);
        longitude = "" + LONGITUDE.getFloat("LONGITUDE",(float) -93.650413);

        sendRequestFriends();

        locList.setOnTouchListener(new OnSwipeTouchListener(NearbyEstablishments.this) {
            public void onSwipeBottom() {
                Intent myIntent = new Intent(NearbyEstablishments.this,
                        MenuScreen.class);
                startActivity(myIntent);
            }
        });
    }


    /** Makes HTTP request to server to run NearbyLocRequest with latitude and longitude variables, and calls showJSON with the response*/
    private void sendRequest(){
        StringRequest sendRequest = new StringRequest(Request.Method.POST,
                JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String Response) {
                        // get response
                        Log.d("Response", Response);
                        //sendRequestFriends();
                        showJSON(Response, friendsLocs);
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
                params.put("latitude", latitude);
                params.put("longitude", longitude);
                params.put("function", functionToCall);
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(sendRequest);
    }



    /** Uses ParseLocJSON to parse the json returned from NearbyLocRequest and display all the informatin in a list*/
    private void showJSON(String json, String[] friends){
        ParseLocJSON pjAll = new ParseLocJSON(json);
        pjAll.ParseIntoStrArr();
        getLocStrings ma = new getLocStrings(this, pjAll.getStringArrayNames(), pjAll.getStringArrayIcons(), pjAll.getStringArrayPlaceIds(), friends );
        locList.setAdapter(ma);
    }


    /** Makes request to the server to run GetFriendsListWithLoc, getting all of the locations of the user's friends and calling showJSONFriends with the response*/
    private void sendRequestFriends(){
        //StringRequest stringRequest = new StringRequest(JSON_URL, listener, errorlistener);
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                JSON_URL2,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        showJSONFriends(response);
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(NearbyEstablishments.this, error.getMessage(), Toast.LENGTH_LONG).show();
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

    /** Uses ParseFriendsLocJSON to parse all of the friends' locations into a string array and then calls sendRequest*/
    private void showJSONFriends(String json){
        ParseFriendsLocJSON pjf = new ParseFriendsLocJSON(json);
        pjf.ParseJSON();
        friendsLocs = pjf.getPlaceIds();
        sendRequest();
    }


}
