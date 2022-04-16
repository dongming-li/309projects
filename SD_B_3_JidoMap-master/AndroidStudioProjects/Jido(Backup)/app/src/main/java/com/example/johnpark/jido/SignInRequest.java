package com.example.johnpark.jido;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by John Park on 10/20/2017.
 */

public class SignInRequest extends StringRequest{

    private static final String SIGNIN_REQUEST_URL =  "http://proj-309-sd-b-3.cs.iastate.edu/Hello.php";
    private Map<String, String> params;

    public SignInRequest(String username, String password, Response.Listener<String> listener){
        super(Request.Method.POST, SIGNIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("user", username);
        //params.put("password", password);
    }

    public Map<String, String> getParams() {
        return params;
    }
}
