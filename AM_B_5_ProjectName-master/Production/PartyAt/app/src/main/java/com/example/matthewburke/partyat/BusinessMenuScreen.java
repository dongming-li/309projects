package com.example.matthewburke.partyat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
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

/** This class is the main screen for the business user. Loads the business' promotions onto the main screen.
 * Allows user to swipe left to make a promotion, swipe right to see all promotions, and up to edit their profile. **/
public class BusinessMenuScreen extends AppCompatActivity {

    private static final String JSON_URL2 = "http://proj-309-am-b-5.cs.iastate.edu/getPromotionsId.php" ;
    ListView listView;
    SharedPreferences BUSINESSID;
    String businessID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_menu_screen);
        listView = (ListView) findViewById(R.id.businessMenuScreen);
        //listView2 = (ListView) findViewById(R.id.listView);
        BUSINESSID = getApplicationContext().getSharedPreferences("preferences_file", Context.MODE_PRIVATE);
        businessID = BUSINESSID.getString("ID", "null");
        Log.d("ID", businessID);
        sendRequest();


        listView.setOnTouchListener(new OnSwipeTouchListener(BusinessMenuScreen.this) {
            public void onSwipeRight() {
                Intent myIntent = new Intent(BusinessMenuScreen.this,
                        MakePromotion.class);
                startActivity(myIntent);
            }
            public void onSwipeLeft() {
                Intent myIntent = new Intent(BusinessMenuScreen.this,
                        PromotionsList.class);
                startActivity(myIntent);
            }
            public void onSwipeBottom() {
                Intent myIntent = new Intent(BusinessMenuScreen.this,
                        BusinessProfile.class);
                startActivity(myIntent);
            }
        });


    }
    /** Send the request to the server to get the business' promotions using their ID.**/
    private void sendRequest(){
        StringRequest sendRequest = new StringRequest(Request.Method.POST,
                JSON_URL2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String Response) {
                        // get response
                        Log.d("Response", Response);
                        showJSON2(Response);
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
                params.put("ID", businessID);
                Log.d("params", params.toString());
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(sendRequest);
    }

    /** Take the response from the server and use the ParseJSON class to parse the response and
     * format it to be displayed. **/
    private void showJSON2(String json2){
        if(json2.compareTo("[]") == 0)
        {
            String[] titles = {"none"};
            String[] none = {""};
            getPromoStrings ma2 = new getPromoStrings(this, titles,none ,none,none);
            listView.setAdapter(ma2);
        }
        else
        {
            ParseJSON pjs2 = new ParseJSON(json2);
            pjs2.parse();
            getPromoStrings ma2 = new getPromoStrings(this, pjs2.titles, pjs2.descriptions, pjs2.startdates, pjs2.enddates);
            listView.setAdapter(ma2);
        }

    }
}
