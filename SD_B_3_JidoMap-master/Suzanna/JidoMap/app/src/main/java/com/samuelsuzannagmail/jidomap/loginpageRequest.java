package com.samuelsuzannagmail.jidomap;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by Suzanna Gudivada on 12/6/2017.
 */

public class loginpageRequest extends StringRequest {
    private static final String Loginpage_URL =  "http://proj-309-sd-b-3.cs.iastate.edu:8000/api/users/login";
    private Map<String, String> params;

    public loginpageRequest(String emailID, String password, Response.Listener<String> listener){
        super(Request.Method.POST, Loginpage_URL, listener, null);
        params = new HashMap<>();
        params.put("email", emailID);
        params.put("password", password);
    }

    public Map<String, String> getParams() {
        return params;
    }


}
