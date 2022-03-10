package com.example.matthewkinnander.makepromotion;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText BusinessId;
    private EditText PromoTitle;
    private EditText PromoDescription;
    private EditText PromoDate;
    private EditText PromoDuration;
    private EditText StartDate;
    private EditText EndDate;

    private Button InsertPromotion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BusinessId = (EditText) findViewById(R.id.BusinessId);
        PromoTitle = (EditText) findViewById(R.id.PromoTitle);
        PromoDescription = (EditText) findViewById(R.id.PromoDescription);
        PromoDate = (EditText) findViewById(R.id.PromoDate);
        PromoDuration = (EditText) findViewById(R.id.PromoDuration);
        StartDate = (EditText) findViewById(R.id.StartDate);
        EndDate = (EditText) findViewById(R.id.EndDate);
        InsertPromotion = (Button) findViewById(R.id.InsertPromotion);
        InsertPromotion.setOnClickListener(this);
    }


//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        InsertPromotion = (Button) findViewById(R.id.InsertPromotion);
//        InsertPromotion.setOnClickListener(this);
//    }


//    private void sendRequest(final String BusinessId, final String PromoTitle, final String PromoDescription, final String PromoDate, final String PromoDuration,
//                             final String StartDate, final String EndDate)
//
private void sendRequest(){
        StringRequest sendRequest = new StringRequest(Request.Method.POST,
                "http://proj-309-am-b-5.cs.iastate.edu/insertPromotions.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String Response) {
                        // get response
                        Log.d("Response", Response);
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
                params.put("BusinessID", BusinessId.getText().toString());
                params.put("PromoTitle", PromoTitle.getText().toString());
                params.put("PromoDescription", PromoDescription.getText().toString());
                params.put("PromoDate", PromoDate.getText().toString());
                params.put("PromoDuration", PromoDuration.getText().toString());
                params.put("PromoStartDate", StartDate.getText().toString());
                params.put("PromoEndDate", EndDate.getText().toString());
                return params;
            }

        };
//        Volley.getInstance(this).addToRequestQueue(sendRequest);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(sendRequest);
    }


    @Override
    public void onClick(View view){
//        sendRequest(BusinessId.getText().toString(),PromoTitle.getText().toString(),PromoDescription.getText().toString(),
//                PromoDate.getText().toString(),PromoDuration.getText().toString(),StartDate.getText().toString(),EndDate.getText().toString());
        sendRequest();
    }
}
