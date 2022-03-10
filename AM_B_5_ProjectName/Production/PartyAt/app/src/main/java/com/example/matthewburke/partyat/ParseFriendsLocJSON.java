package com.example.matthewburke.partyat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by MatthewBurke on 12/5/17.
 */

/** Converts the json recieved from GetFriendsWithLoc and converts it into string arrays of first names, last names, usernames, and the placeId of where they are located.*/
public class ParseFriendsLocJSON {
    public static  String[] FirstNames;
    public static  String[] LastNames;
    public static  String[] UserNames;
    public static  String[] PlaceIds;

    public static final String Key_FirstName = "FirstName";
    public static final String Key_LastName = "LastName";
    public static final String Key_UserName = "UserName";
    public static final String Key_Location = "Location";

    private JSONArray users;
    private String json;

    public ParseFriendsLocJSON(String json) {this.json = json;}

    protected void ParseJSON(){

        try{

            users = new JSONArray(json);
            FirstNames = new String[users.length()];
            LastNames = new String[users.length()];
            UserNames = new String[users.length()];
            PlaceIds = new String[users.length()];


            for (int i = 0; i < users.length(); i++){
                JSONObject jo = users.getJSONObject(i);
                FirstNames[i] = jo.getString(Key_FirstName);
                LastNames[i] = jo.getString(Key_LastName);
                UserNames[i] = jo.getString(Key_UserName);
                PlaceIds[i] = jo.getString(Key_Location);

            }

        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }

    public String[] getFirstNames() {
        return FirstNames;
    }
    public String[] getLastNames() {
        return LastNames;
    }
    public String[] getUserNames() {
        return UserNames;
    }
    public String[] getPlaceIds() {
        return PlaceIds;
    }
}
