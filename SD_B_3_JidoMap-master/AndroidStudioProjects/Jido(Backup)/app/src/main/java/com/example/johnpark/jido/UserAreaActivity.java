package com.example.johnpark.jido;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class UserAreaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        final EditText etName = (EditText) findViewById(R.id.etName);
      //  final TextView welcomemsg = (TextView) findViewById(R.id.welcomemsg);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        final String username = intent.getStringExtra("username");

        String message = "Welcome to JiDo Map";
       // welcomemsg.setText(message);
        etName.setText(name);
        etUsername.setText(username);
    }
}
