package com.example.matthewkinnander.wherethepartyapp;

import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by matthewkinnander on 10/8/17.
 */

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

    protected void ParseJSON(){
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
