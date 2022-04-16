package com.samuelsuzannagmail.demo2;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button b1=(Button)findViewById(R.id.button);
        Button b2=(Button)findViewById(R.id.button2);
        Button b3=(Button)findViewById(R.id.button3);
        Button b4= (Button)findViewById(R.id.button7);

        //Button to go to schedule activity from homepage
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),schedule.class);
                startActivity(i);
            }
        });

        //Button to go to list of students activity from homepage
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),students.class);
                startActivity(i);
            }
        });

        //Button to go to list of professors activity from homepage
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),professors.class);
                startActivity(i);
            }
        });

        b4.setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        TextView text = (TextView)findViewById(R.id.textView2);
                        text.setText("Goodbye!");
                        text.setTextColor(Color.RED);
                    }
                }        );



    }
}
