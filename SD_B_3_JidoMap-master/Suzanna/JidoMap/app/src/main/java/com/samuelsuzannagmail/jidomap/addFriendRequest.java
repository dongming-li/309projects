package com.samuelsuzannagmail.jidomap;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;


public class addFriendRequest extends StringRequest{

    private static final String SIGNIN_REQUEST_URL =  "http://proj-309-sd-b-3.cs.iastate.edu/friends";
    private Map<String, String> params;

    public addFriendRequest(String friendID, String userID, Response.Listener<String> listener){
        super(Request.Method.POST, SIGNIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("friendID", friendID);
        params.put("userID", userID);
    }

    public Map<String, String> getParams() {
        return params;
    }

}
