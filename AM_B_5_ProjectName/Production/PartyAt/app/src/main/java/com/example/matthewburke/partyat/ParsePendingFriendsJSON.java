package com.example.matthewburke.partyat;



import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Tyler Helmrich on 10/8/17.
 */

/**
 * This Class is responsible for parsing the JSON received from the network response when retrieving a users pending friends
 */
public class ParsePendingFriendsJSON {
    public static  String[] FirstNames;
    public static  String[] LastNames;
    public static  String[] UserNames;

    public static final String Key_FirstName = "FirstName";
    public static final String Key_LastName = "LastName";
    public static final String Key_UserName = "UserName";

    private JSONArray users;
    private String json;

    public ParsePendingFriendsJSON(String json) {this.json = json;}

    protected void ParseJSON(){

        try{

            JSONObject user = new JSONObject(json);
            users = new JSONArray();
            users.put(user);
            FirstNames = new String[users.length()];
            LastNames = new String[users.length()];
            UserNames = new String[users.length()];


            for (int i = 0; i < users.length(); i++){
                JSONObject jo = users.getJSONObject(i);
                FirstNames[i] = jo.getString(Key_FirstName);
                LastNames[i] = jo.getString(Key_LastName);
                UserNames[i] = jo.getString(Key_UserName);
            }

        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }
}
