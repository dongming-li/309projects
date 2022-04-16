package com.example.nidhi.demo2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android. view.View;
import android. content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnButtonClick(View v){
        if(v.getid == R.id.BDisplay){

      Intent i = new Intent(MainActivity.this,Display.class);
            startActivity(i);

        }


    }
}
