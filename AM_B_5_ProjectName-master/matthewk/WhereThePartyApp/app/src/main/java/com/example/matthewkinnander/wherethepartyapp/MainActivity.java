package com.example.matthewkinnander.wherethepartyapp;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String JSON_URL = "http://proj-309-am-b-5.cs.iastate.edu/getPromotionsAll.php" ;

    private Button buttonGet;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonGet = (Button) findViewById(R.id.buttonGet);
        buttonGet.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.listView);
    }


    private void sendRequest(){
        //StringRequest stringRequest = new StringRequest(JSON_URL, listener, errorlistener);
        StringRequest stringRequest = new StringRequest(JSON_URL,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        showJSON(response);
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void showJSON(String json){
        ParseJSON pj = new ParseJSON(json);
        pj.ParseJSON();
        myApplication ma = new myApplication(this, ParseJSON.titles, ParseJSON.descriptions, ParseJSON.startdates, ParseJSON.enddates);
        listView.setAdapter(ma);
    }

    @Override
    public void onClick(View v){
        sendRequest();
    }
}
