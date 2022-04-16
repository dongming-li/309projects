package server;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mlscholl.wickedchambers.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gunnar on 10/9/2017.
 */

public class ConnectionActivity extends AppCompatActivity {
    EditText usernameET;
    EditText passwordET;
    Button subButton;
    Button sendButton;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        sendButton = (Button)findViewById(R.id.btnSignUp);
        subButton = (Button)findViewById(R.id.btnLogin);
        usernameET = (EditText)findViewById(R.id.aUser);
        passwordET = (EditText)findViewById(R.id.aPass);
        final String username = usernameET.getText().toString().trim();
        final String password = passwordET.getText().toString().trim();
    }

    public void RetrieveInfo(View view){
        String url = "http://proj-309-gp-b-2.cs.iastate.edu/connection.php";
        Map<String, String> params = new HashMap<String, String>();
        //params.put("name", "Droider");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //what are you doing with response
                        TextView t = (TextView)findViewById(R.id.textView2);
                        t.setText(response.toString());
                        t.setVisibility(View.VISIBLE);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();

                //params.put("username",);
                //params.put(KEY_EMAIL, email);
                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void SendInfo(View view){
        String url = "http://proj-309-gp-b-2.cs.iastate.edu/push.php";
        Map<String, String> params = new HashMap<String, String>();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //response.toString();
                        TextView t = (TextView)findViewById(R.id.textView2);
                        t.setText(response.toString());
                        t.setVisibility(View.VISIBLE);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
                ){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();

                params.put("username", usernameET.getText().toString().trim());
                params.put("password", passwordET.getText().toString().trim());
                params.put("charactername", "billy");
                params.put("health", "12");
                params.put("intellect", "12");
                params.put("strength", "12");
                params.put("agility", "12");
                params.put("level", "12");
                params.put("exp", "12");

                return params;
            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
