package com.example.matthewburke.partyat;

/**
 * Created by MatthewBurke on 10/9/17.
 */

import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by matthewkinnander on 10/8/17.
 */

/** This class takes in a string and parses into data for the title, description, start date, and end date for the promotion. **/
public class ParseJSON {
    public static  String[] titles;
    public static  String[] descriptions;
    public static  String[] startdates;
    public static  String[] enddates;

    public static final String Key_Title = "PromoTitle";
    public static final String Key_Description = "PromoDescription";
    public static final String Key_StartDate = "PromoStartDate";
    public static final String Key_EndDate = "PromoEndDate";

    private JSONArray users;
    private String json;

    public ParseJSON(String json) {this.json = json;}

    /** Parse the string putting the information into the correct array **/
    protected void parse(){
        JSONObject jsonObject = null;

        try{
            jsonObject = new JSONArray(json).getJSONObject(0);

            users = new JSONArray(json);
            titles = new String[users.length()];
            descriptions = new String[users.length()];
            startdates = new String[users.length()];
            enddates = new String[users.length()];


            for (int i = 0; i < users.length(); i++){
                JSONObject jo = users.getJSONObject(i);
                titles[i] = jo.getString(Key_Title);
                descriptions[i] = jo.getString(Key_Description);
                startdates[i] = jo.getString(Key_StartDate);
                enddates[i] = jo.getString(Key_EndDate);
            }

        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }
}
