package com.example.nidhi.demo3;

/**
 * Created by Nidhi on 12/4/2017.
 */

        import com.android.volley.Request;
        import com.android.volley.Response;
        import com.android.volley.toolbox.StringRequest;

        import java.util.HashMap;
        import java.util.Map;


public class StudentRequest extends StringRequest{

    private static final String STUDENT_REQUEST_URL =  "http://proj-309-sd-b-3.cs.iastate.edu:8000/students.json";
    private Map<String, String> params;

    public StudentRequest(String rollno,String name, String marks, Response.Listener<String> listener){
        super(Request.Method.POST, STUDENT_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("userid", rollno);
        params.put("username", name);
        params.put("usermark", marks);
    }

    public Map<String, String> getParams() {
        return params;
    }
}
