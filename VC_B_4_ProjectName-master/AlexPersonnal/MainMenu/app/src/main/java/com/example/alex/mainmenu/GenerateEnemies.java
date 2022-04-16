package com.example.alex.mainmenu;

import android.graphics.Color;
import android.graphics.Rect;

import java.util.ArrayList;

/**
 * Created by Alex on 10/31/2017.
 */

public class GenerateEnemies {


    private int spawn_x;
    private int spawn_y;
    private int offset = 150;

    public ArrayList<Unit> enemyUnits = new ArrayList();

    public GenerateEnemies (int spawn_x, int spawn_y){

        this.spawn_x = spawn_x;
        this.spawn_y = spawn_y;
        fill_list();
    }

    private void fill_list(){


        //fill the arraylist enemyUnits with enemy units
        //whose initial position will be at the spawn point
        int i =0;
        Unit a;

        while(i < 20){

           // a = new Unit(new Position(450,100 + (150*i)), i, 25, com.example.alex.mainmenu.DamageTypes.FIRE, new Rect(500,500,600,600), Color.rgb(128,128,128));

            //TODO make a function that calculates how far back the enemies need to be spawned off screen to enter just as the round starts
            //TODO make a function that calculates where the actual spawn needs to be

            a = new Unit(5, new Position(spawn_x ,spawn_y + (offset*i)), i, 25, com.example.alex.mainmenu.DamageTypes.FIRE, new Rect(500,500,600,600), Color.RED);
            enemyUnits.add(a);

            i++;
        }


    }



}
