package com.example.settingsmenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.media.AudioManager;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity
{

    SeekBar sound;
    AudioManager audioManager;
    Button button, button2, button3, button4;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sound = (SeekBar) findViewById(R.id.seekBar);
        button = (Button) findViewById(R.id.button1) ;
        button2 = (Button) findViewById(R.id.button2) ;
        button3 = (Button) findViewById(R.id.button3) ;
        button4 = (Button) findViewById(R.id.button4) ;
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent myIntent = new Intent(MainActivity.this, submitMenu.class);
                startActivity(myIntent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(), "Currently unavailable. T.T", Toast.LENGTH_LONG).show();
            }
        });

        sound.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_NOTIFICATION)); //??
        sound.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                audioManager.setStreamVolume(AudioManager.STREAM_ALARM, i, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {
                //empty
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                //empty
            }
        });

        button3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent myIntent = new Intent(MainActivity.this, submitMenu.class);
                startActivity(myIntent);
            }
        });

        button4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               finish();
                System.exit(0);
            }
        });



    }

}
