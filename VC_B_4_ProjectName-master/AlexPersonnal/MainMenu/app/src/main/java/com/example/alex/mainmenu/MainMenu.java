package com.example.alex.mainmenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Set;

public class MainMenu extends AppCompatActivity {


    Button Play,Settings,Map_Editor,Leaderboard,Friendslist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


        Play=(Button)findViewById(R.id.Play);
        Settings=(Button)findViewById(R.id.Settings);
        Friendslist=(Button)findViewById(R.id.Friends_List);
        Map_Editor=(Button)findViewById(R.id.Map_Editor);
        Leaderboard=(Button)findViewById(R.id.Lead_Board);


        Play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainMenu.this, Play.class);
                startActivity(i);
            }
            });

        Settings.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainMenu.this, Settings.class);
                startActivity(i);
            }
        });

        Map_Editor.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainMenu.this, Map_Editor.class);
                startActivity(i);
            }
        });


        Friendslist.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainMenu.this, Friends_List.class);
                startActivity(i);
            }
        });

        Leaderboard.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainMenu.this, Leader_Board.class);
                startActivity(i);
            }
        });

        }
    }