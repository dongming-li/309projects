package com.example.johnpark.jido;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUpActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] Classifications = {"Professor", "Student", "Vendor"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        final EditText etUUsername = (EditText) findViewById(R.id.etUUsername);
        final EditText etUFirstName = (EditText) findViewById(R.id.etUFirstName);
        final EditText etULastName = (EditText) findViewById(R.id.etULastName);
        final EditText etUEmailAdress = (EditText) findViewById(R.id.etUEmailAdress);
        final EditText etUPhoneNumber = (EditText) findViewById(R.id.etUPhoneNumber);
        final EditText etUPassword = (EditText) findViewById(R.id.etUPassword);
        final EditText etURPassword = (EditText) findViewById(R.id.etURPassword);

        final Spinner sClassification = (Spinner) findViewById(R.id.sUClassification);

        final Button bUSignUp = (Button) findViewById(R.id.bUSignUp);

        bUSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = etUFirstName.getText().toString() + " " + etULastName.getText().toString();
                final String username = etUUsername.getText().toString();
                final String password = etUPassword.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);

                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                                SignUpActivity.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);
                                builder.setMessage("Sign Up Failed").setNegativeButton("Retry", null).create().show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                };

                SignUpRequest signupRequest = new SignUpRequest(name, username, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(SignUpActivity.this);
                queue.add(signupRequest);
            }
        });

        sClassification.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the classification list
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, Classifications);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        sClassification.setAdapter(aa);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), Classifications[position], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
