package com.samuelsuzannagmail.events;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


    public class MainActivity extends StringRequest{
        private static final String EVENT_REQUEST_URL =  "http://proj-309-sd-b-3.cs.iastate.edu:8000/events";
        private Map<String, String> params;

        public MainActivity(String hostID,String eventName, String eventLocation,String eventDate, String eventTime,boolean approved, Response.Listener<String> listener){
            super(Request.Method.POST, EVENT_REQUEST_URL, listener, null);
            params = new HashMap<>();
            params.put("Event HostID: ",        hostID);
            params.put("Event Name: ",        eventName);
            params.put("Event Location: ",    eventLocation);
            params.put("Event Date: ",        eventDate);
            params.put("Event Time: ",       eventTime);

        }


    public Map<String, String> getParams() {
        return params;
    }

}
