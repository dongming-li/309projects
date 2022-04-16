package mainmenu;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mlscholl.wickedchambers.R;

import java.util.HashMap;
import java.util.Map;

import characters.CharCreate;


/**
 *  The LoginActivity is the way in which the user will log into the game using their pre-existing username and password. Attempting to log in will query the database for the username
 *  provided and will then check if the password is correct. If so, the app will allow the user to continue to the main game. The user can also click a button to go to the signup screen,
 *  which will allow them to create a new account.
 */
public class LoginActivity extends Activity
{
    private String notTruth = "false";
    private String truth = "true";
    private String responsechecker = "";
    private String  TAG = LoginActivity.class.getSimpleName();
    private Button buttonLogin;
    private Button signupButton;
    private ProgressDialog pDialog;
    private String tag_string_req = "string_req";
    EditText editTextUsername, editTextPassword;
    public CharCreate currentCharacter;
    private SharedPreferences muhPreferences;

    private String sendResponse;

    /**
     * Creates the view for the Login Screen by linking on screen widgets and buttons to their layouts in the xml file.
     * @param savedInstanceState saved appliation data
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);

        buttonLogin = (Button) findViewById(R.id.btnLogin);
        signupButton = (Button) findViewById(R.id.btnSignUp);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);
        editTextUsername = (EditText) findViewById(R.id.aUser);
        editTextPassword = (EditText) findViewById(R.id.aPass);

        muhPreferences = getSharedPreferences("User", MODE_PRIVATE);

        buttonLogin.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View view)
           {
               userLogin();
           }
        });
        signupButton.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View view)
           {
               Intent signupActivity = new Intent(getApplicationContext(), SignupActivity.class);
               startActivity(signupActivity);
           }
        });
    }

    /**
     * Shows a progress bar to indicate a process is being done
     */
    private void showProgressDialog()
    {
        if (!pDialog.isShowing())
        {
            pDialog.show();
        }
    }

    /**
     * Hides a progress bar to indicate a process is completed
     */
    private void hideProgressDialog()
    {
        if (pDialog.isShowing())
        {
            pDialog.hide();
            pDialog.dismiss();
        }
    }

    /**
     * Queries the database for username information, then checks to see if the provided password is also correct. If so, the user is sent to the MainMenuActivity screen
     */
    private void userLogin()
    {
        final String username = editTextUsername.getText().toString();
        final String password = editTextPassword.getText().toString();



        if (TextUtils.isEmpty(username))
        {
            editTextUsername.setError("Please enter your username");
            editTextUsername.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password))
        {
            editTextPassword.setError("Please enter your password");
            editTextPassword.requestFocus();
            return;
        }
        //showProgressDialog();
        String url = "http://proj-309-gp-b-2.cs.iastate.edu/verify_login.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response){

                        TextView t = (TextView) findViewById(R.id.textViewResult);
                        t.setText(response.toString().trim());
                        t.setVisibility(View.VISIBLE);
                        if (response.toString().contains("f")) {
                            Toast.makeText(LoginActivity.this, "Invalid login credentials", Toast.LENGTH_LONG).show();
                        }
                        else if(response.toString().contains("t")){
                            Intent intent = new Intent(getBaseContext(), StartMenuActivity.class);
                            intent.putExtra("USERNAME", username);
                            startActivity(intent);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }
    // not used right now
    public boolean verify_login(){
        String url = "http://proj-309-gp-b-2.cs.iastate.edu/verify_login.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response){
                        //TextView t = (TextView) findViewById(R.id.textViewResult);
                        //t.setText(response.toString());
                        //t.setVisibility(View.VISIBLE);
                        responsechecker = response.toString().trim();


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", editTextUsername.getText().toString().trim());
                params.put("password", editTextPassword.getText().toString().trim());
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        TextView t = (TextView) findViewById(R.id.textViewResult);
        t.setText(responsechecker);
        t.setVisibility(View.VISIBLE);
        if (responsechecker.isEmpty()) {
            return false;
        }
        else{
            return true;
        }
        //return true;
    }

}
