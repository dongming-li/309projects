package com.example.matthewburke.partyat;



import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

/** This class takes in input from the user and uses the input to create a new Business user. **/
public class InsertBusiness extends AppCompatActivity implements View.OnClickListener {
    private EditText userName;
    private EditText businessTitle;
    private EditText phone;
    private EditText email;
    private EditText password;
    private EditText address;
    private EditText city;

    Button insertBusiness;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_business);
        userName = (EditText) findViewById(R.id.userName);
        businessTitle = (EditText) findViewById(R.id.businessTitle);
        phone = (EditText) findViewById(R.id.phone);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        address = (EditText) findViewById(R.id.address);
        city = (EditText) findViewById(R.id.city);
        insertBusiness = (Button) findViewById(R.id.insertBusiness);
        insertBusiness.setOnClickListener(this);
    }

    /** Send the request to the server to make sure all fields were entered correctly and completed. **/
    private void sendRequest(){
        StringRequest sendRequest = new StringRequest(Request.Method.POST,
                "http://proj-309-am-b-5.cs.iastate.edu/BusinessesTableFunctions.php",
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
                params.put("userName", userName.getText().toString());
                params.put("businessTitle", businessTitle.getText().toString());
                params.put("phone", phone.getText().toString());
                params.put("email", email.getText().toString());
                params.put("password", password.getText().toString());
                params.put("address", address.getText().toString());
                params.put("city", city.getText().toString());
                params.put("function", "insertBusiness");
                return params;
            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(sendRequest);
    }

    /** If successful, a new business user will be inserted into the Businesses table. **/
    public void onSuccess(String Response){
        if(Response.equals("1")){
            Intent myIntent = new Intent(InsertBusiness.this,
                    HomeScreen.class);
            startActivity(myIntent);
        }
    }

    public void onClick(View view){
        sendRequest();
    }


}
