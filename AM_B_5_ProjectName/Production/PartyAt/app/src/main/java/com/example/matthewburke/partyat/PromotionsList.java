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
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/** This class retrieves all of the promotions from the Promotions table to be viewed **/
public class PromotionsList extends AppCompatActivity implements View.OnClickListener{
    private static final String JSON_URL = "http://proj-309-am-b-5.cs.iastate.edu/getPromotionsAll.php" ;

    private Button buttonGet;
    private ListView listView;
    SharedPreferences USERTYPE;
    String userType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promotions_list);

        sendRequest();
        buttonGet = (Button) findViewById(R.id.buttonGet);
        buttonGet.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.listView);
        USERTYPE = getApplicationContext().getSharedPreferences(getString(R.string.PREFERENCES), Context.MODE_PRIVATE);
        userType = USERTYPE.getString(getString(R.string.USERTYPE),"null");
        Log.d("usertype", userType);


        listView.setOnTouchListener(new OnSwipeTouchListener(PromotionsList.this) {
            public void onSwipeRight() {
                Intent myIntent;
                if(userType.compareTo("b") == 0) {
                    myIntent = new Intent(PromotionsList.this,
                            BusinessMenuScreen.class);
                }else{
                    myIntent = new Intent(PromotionsList.this,
                            MenuScreen.class);
                }
                startActivity(myIntent);
            }
        });

    }

    /** Send the request to the server to retrieve the promomtions. **/
    private void sendRequest(){
        //StringRequest stringRequest = new StringRequest(JSON_URL, listener, errorlistener);
        StringRequest stringRequest = new StringRequest(JSON_URL,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        showJSON(response);
                        Log.d("Respones", response);
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(PromotionsList.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    /** Send the response to ParseJSON class to be parsed and displayed **/
    private void showJSON(String json){
        ParseJSON pj = new ParseJSON(json);
        pj.parse();
        getPromoStrings ma = new getPromoStrings(this, pj.titles, pj.descriptions, pj.startdates, pj.enddates);
        listView.setAdapter(ma);
    }

    @Override
    public void onClick(View v){
        sendRequest();
    }
}
