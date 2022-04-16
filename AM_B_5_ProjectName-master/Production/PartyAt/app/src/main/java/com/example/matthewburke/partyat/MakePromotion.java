package com.example.matthewburke.partyat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/** This class takes in input from the user and uses the input to create a new Promotion. **/
public class MakePromotion extends AppCompatActivity implements View.OnClickListener {

    //private EditText BusinessId;
    private EditText PromoTitle;
    private EditText PromoDescription;
    private EditText PromoDate;
    private EditText PromoDuration;
    private EditText StartDate;
    private EditText EndDate;
    private ScrollView scrollView;

    private Button InsertPromotion;

    SharedPreferences BUSINESSID;
    String businessID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_promotion);
        //BusinessId = (EditText) findViewById(R.id.BusinessId);
        PromoTitle = (EditText) findViewById(R.id.PromoTitle);
        PromoDescription = (EditText) findViewById(R.id.PromoDescription);
        PromoDate = (EditText) findViewById(R.id.PromoDate);
        PromoDuration = (EditText) findViewById(R.id.PromoDuration);
        StartDate = (EditText) findViewById(R.id.StartDate);
        EndDate = (EditText) findViewById(R.id.EndDate);
        InsertPromotion = (Button) findViewById(R.id.InsertPromotion);
        InsertPromotion.setOnClickListener(this);
        BUSINESSID = getApplicationContext().getSharedPreferences("preferences_file", Context.MODE_PRIVATE);
        businessID = BUSINESSID.getString("ID","null");


        scrollView = findViewById(R.id.scrollView);

        scrollView.setOnTouchListener(new OnSwipeTouchListener(MakePromotion.this) {
            public void onSwipeLeft() {
                Intent myIntent = new Intent(MakePromotion.this,
                        BusinessMenuScreen.class);
                startActivity(myIntent);
            }
        });

    }

    /** Send the request to the server to make sure all fields were entered correctly and completed. **/
    private void sendRequest(){
        StringRequest sendRequest = new StringRequest(Request.Method.POST,
                "http://proj-309-am-b-5.cs.iastate.edu/insertPromotions.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String Response) {
                        // get response
                        Log.d("Response", Response);
                        onSuccess(Response);
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
                params.put("BusinessID", businessID);
                params.put("PromoTitle", PromoTitle.getText().toString());
                params.put("PromoDescription", PromoDescription.getText().toString());
                params.put("PromoDate", PromoDate.getText().toString());
                params.put("PromoDuration", PromoDuration.getText().toString());
                params.put("PromoStartDate", StartDate.getText().toString());
                params.put("PromoEndDate", EndDate.getText().toString());
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(sendRequest);
    }

    /** If successful, a new promotion will be inserted into the Promotions table. **/
    public void onSuccess(String Response){
        if(Response.equals("1")){
            Intent myIntent = new Intent(MakePromotion.this,
                    BusinessMenuScreen.class);
            startActivity(myIntent);
        }
    }

    @Override
    public void onClick(View view){
        sendRequest();
    }
}
