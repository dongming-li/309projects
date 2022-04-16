package com.samuelsuzannagmail.demo1aa;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b1=(Button)findViewById(R.id.button);
        Button b2=(Button)findViewById(R.id.button2);
        Button b3=(Button)findViewById(R.id.button3);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Main3Activity.class);
                startActivity(i);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Main4Activity.class);
                startActivity(i);
            }
        });


        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Main5Activity.class);
                startActivity(i);
            }
        });




    }
}
