package com.samuelsuzannagmail.jidomap;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class maphome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maphome);

        // Link the menu item to the buddies main screen
        // Will have more screens within buddies
        Button b1=(Button)findViewById(R.id.buttonBuddies);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),main_buddies.class);
                startActivity(i);
            }
        });

        // Link the menu item to the Schedule screen
        Button b2=(Button)findViewById(R.id.buttonSchedule);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),main_schedule.class);
                startActivity(i);
            }
        });

        // Link the menu item to the groups main screen
        Button b3=(Button)findViewById(R.id.buttonGroups);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),main_groups.class);
                startActivity(i);
            }
        });

        // Link the menu item to the chats screen
        Button b4 = (Button)findViewById(R.id.buttonChats);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),main_chats.class);
                startActivity(i);
            }
        });

        // Link the menu item to the events screen
        Button b5 = (Button)findViewById(R.id.buttonEvents);
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),main_events.class);
                startActivity(i);
            }
        });

        // Link the menu item to the groups main screen
        Button b6=(Button)findViewById(R.id.buttonSettings);
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),main_settings.class);
                startActivity(i);
            }
        });

        // Link the menu item to the groups main screen
        Button b7=(Button)findViewById(R.id.buttonLogout);
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),logout.class);
                startActivity(i);
            }
        });


    }
}
