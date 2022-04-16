package com.samuelsuzannagmail.demo2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class professors extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professors);

        //Button to return from this screen to homepage
        Button b1=(Button)findViewById(R.id.button6);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
    }
}
