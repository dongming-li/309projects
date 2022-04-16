package com.samuelsuzannagmail.jidomap;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Suzanna Gudivada on 12/6/2017.
 */

public class signuppageRequest extends StringRequest {

    private static final String signuppage_URL =  "http://proj-309-sd-b-3.cs.iastate.edu:8000/api/users";
    private Map<String, Map> objects;
    private Map<String, String> params;
    public signuppageRequest(String username, String email, String password, Response.Listener<String> listener){
        super(Request.Method.POST, signuppage_URL, listener, null);
        params = new HashMap<>();
        params.put("email", email);
        params.put("password", password);
        params.put("username", username);
    }




    public Map<String, String> getParams() {

        return params;
    }
}
