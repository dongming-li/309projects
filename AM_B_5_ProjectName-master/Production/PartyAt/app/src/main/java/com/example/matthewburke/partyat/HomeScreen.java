package com.example.matthewburke.partyat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.view.MotionEvent;
import android.support.v4.view.MotionEventCompat;


/*
public class MainActivity extends AppCompatActivity {

    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn);


        btn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                Intent myIntent = new Intent(MainActivity.this,
                        StringRequestActivity.class);
                startActivity(myIntent);
            }
        });
    }
 */
/** This class is the first screen users see when they open the app. It prompts them to log in or create an account. **/
public class HomeScreen extends AppCompatActivity {

    Button login;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        login = (Button)findViewById(R.id.login);
        register = (Button)findViewById(R.id.register);


        login.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                Intent myIntent = new Intent(HomeScreen.this,
                        UserType.class);
                startActivity(myIntent);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(HomeScreen.this,
                        AccountType.class);
                startActivity(myIntent);
            }
        });


    }

    @Override
    public void onBackPressed() {
    }

}
