package com.samuelsuzannagmail.jidomap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class signup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Button b1=(Button)findViewById(R.id.button10);
        Button b2 = (Button)findViewById(R.id.buttonsignup);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),loginpage.class);
                startActivity(i);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),signuppage.class);
                startActivity(i);
            }
        });
    }
}
