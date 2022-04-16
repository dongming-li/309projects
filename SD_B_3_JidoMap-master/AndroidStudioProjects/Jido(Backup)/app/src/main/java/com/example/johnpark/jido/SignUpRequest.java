package com.example.johnpark.jido;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.Response;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by John Park on 10/16/2017.
 */

public class SignUpRequest extends StringRequest {

    private static final String SIGNUP_REQUEST_URL =  "http://proj-309-sd-b-3.cs.iastate.edu/signup.php";
    private Map<String, String> params;

    public SignUpRequest(String name, String username, String password, Response.Listener<String> listener){
        super(Method.POST, SIGNUP_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("username", username);
        params.put("password", password);
    }

    public Map<String, String> getParams() {
        return params;
    }


}
