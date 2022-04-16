package com.example.matthewburke.partyat;

/**
 * Created by MatthewBurke on 11/6/17.
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by MatthewBurke on 10/9/17.
 */

import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/** Turns the json array revieved from NearbyLocRequest into string arrays of names, icons, and placeIds*/

public class ParseLocJSON {
    private String[] strngArrNames;
    private String[] strngArrIcons;
    private String[] strngArrPlaceIds;

    private JSONArray jsonArr;
    private String json;

    public ParseLocJSON(String json) {this.json = json;}

    protected void ParseIntoStrArr(){


        try{
            jsonArr = new JSONArray(json);
            strngArrNames = new String[20];
            strngArrIcons = new String[20];
            strngArrPlaceIds = new String[20];

            for (int i = 0; i < jsonArr.length(); i++) {
                strngArrNames[i] = jsonArr.getJSONArray(i).getString(0);
                strngArrIcons[i] = jsonArr.getJSONArray(i).getString(1);
                strngArrPlaceIds[i] = jsonArr.getJSONArray(i).getString(2);
            }

        }
        catch (JSONException e){
            e.printStackTrace();
        }
    }

    public String[] getStringArrayNames(){

        return this.strngArrNames;
    }
    public String[] getStringArrayIcons(){

        return this.strngArrIcons;
    }
    public String[] getStringArrayPlaceIds(){

        return this.strngArrPlaceIds;
    }



}
