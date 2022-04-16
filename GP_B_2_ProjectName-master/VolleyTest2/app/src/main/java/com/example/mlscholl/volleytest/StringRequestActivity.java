package com.example.mlscholl.volleytest;

/**
 * Created by mlscholl on 10/3/2017.
 */

import com.example.mlscholl.volleytest.app.AppController;
import com.example.mlscholl.volleytest.utils.Const;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;


public class StringRequestActivity extends Activity {
    private String TAG = StringRequestActivity.class.getSimpleName();
    private Button btnStringReq;
    private TextView msgResponse;
    private ProgressDialog pDialog;
    private String tag_string_req = "string_req";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_string);

        btnStringReq = (Button) findViewById(R.id.string_request);
        msgResponse = (TextView) findViewById(R.id.string_response);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);

        btnStringReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeStringReq();
            }
        });

        Log.d("Tag", "Made it to StringRequestActivity");
    }

    private void showProgressDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }
    private void hideProgressDialog() {
        if (pDialog.isShowing())
            pDialog.hide();
    }
    /**
     * Making json object request
     * */
    private void makeStringReq() {

        showProgressDialog();
        StringRequest strReq = new StringRequest(Method.GET,
                Const.URL_STRING_REQ, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                msgResponse.setText(response.toString());
                hideProgressDialog();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hideProgressDialog();
            }
        });

        Log.d("Tag", "Made it to makeStringReq");
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }
}