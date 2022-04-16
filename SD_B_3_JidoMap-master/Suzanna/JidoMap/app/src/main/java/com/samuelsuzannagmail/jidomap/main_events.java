package com.samuelsuzannagmail.jidomap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class main_events extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_events);

        //Submit event request
        Button b1=(Button)findViewById(R.id.button1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(getApplicationContext(),event_submiteventrequest.class);
                startActivity(j);
            }
        });

        //Create event after approval
        Button b2=(Button)findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(getApplicationContext(),event_submiteventrequest.class);
                startActivity(j);
            }
        });

        //Invite friends
        Button b3=(Button)findViewById(R.id.button7);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(getApplicationContext(),event_submiteventrequest.class);
                startActivity(j);
            }
        });

        //Edit event
        Button b4=(Button)findViewById(R.id.button6);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(getApplicationContext(),event_submiteventrequest.class);
                startActivity(j);
            }
        });

        //Delete event
        Button b5=(Button)findViewById(R.id.button8);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j = new Intent(getApplicationContext(),event_submiteventrequest.class);
                startActivity(j);
            }
        });


    }
}
