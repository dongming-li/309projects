package com.example.mlscholl.volleytest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button JsonRequest = (Button) findViewById(R.id.JSONRequest);
        JsonRequest.setOnClickListener(this);

        Button ImageRequest = (Button) findViewById(R.id.ImageRequest);
        ImageRequest.setOnClickListener(this);

        Button StringRequest = (Button) findViewById(R.id.StringRequest);
        StringRequest.setOnClickListener(this);

        Button LoginRequest = (Button) findViewById(R.id.LoginRequest);
        LoginRequest.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.JSONRequest: {
                Toast toast = Toast.makeText(getApplicationContext(), "JSONRequest", Toast.LENGTH_SHORT);
                toast.show();

                Intent i = new Intent(MainActivity.this, JsonRequestActivity.class);
                startActivity(i);

                break;
            }

            case R.id.ImageRequest: {
                Toast toast = Toast.makeText(getApplicationContext(), "ImageRequest", Toast.LENGTH_SHORT);
                toast.show();

                Intent i = new Intent(MainActivity.this, ImageRequestActivity.class);
                startActivity(i);
                break;
            }
            case R.id.StringRequest: {
                Toast toast = Toast.makeText(getApplicationContext(), "StringRequest", Toast.LENGTH_SHORT);
                toast.show();

                Intent i = new Intent(MainActivity.this, StringRequestActivity.class);
                startActivity(i);
                break;
            }
            case R.id.LoginRequest: {
                Toast toast = Toast.makeText(getApplicationContext(), "LoginRequest", Toast.LENGTH_SHORT);
                toast.show();

                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
                break;
            }
        }
    }
}
