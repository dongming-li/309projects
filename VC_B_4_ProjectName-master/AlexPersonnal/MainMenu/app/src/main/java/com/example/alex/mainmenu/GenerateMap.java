package com.example.alex.mainmenu;

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.ArrayList;

/**
 * Created by Alex on 10/26/2017.
 */



public class GenerateMap {


    static int SQUARE_WIDTH;
    static int road_color;


    static {
        SQUARE_WIDTH= 100;
        //cannot resolve symbol rgb idk why
        //road_color = new Color.rgb(255,228,196);

    }


    public ArrayList<TerrainSquare> map = new ArrayList<TerrainSquare>();

    public GenerateMap(){
        fill_list();

    }


    private void fill_list(){
        int i=0;
        int j=0;
        Point point = new Point();
        point.set(0,0);

//        map.add(new TerrainSquare(new Rect(100,100,200,200), Color.GREEN));
//            point.set(500,(100)+ SQUARE_WIDTH);
//            map.get(0).update(point);
//            point.set(500,(200)+ SQUARE_WIDTH);
//            map.get(1).update(point);


        Color terrain_Type;
        //terrain_Type = Color();
        while(map.size() != 40){

            //take the string or array of numbers and parse as an enum
//            if(GRASS){
//
//            }
//            else if (PATH){
//
//            }
//            else if (WATER){
//
//            }
//            else if (ROCK){
//
//            }
//            else if (SPAWN){
//
//            }
//            else if (BASE){
//
//            }
//
//            else{
//                GRASS
//            }
            map.add(new TerrainSquare(new Rect(100,100,200,200), Color.rgb(255,228,196)));
            //point.set(600,(100 + SQUARE_WIDTH)*j);
            point.set(400,(100)*j);
            map.get(i).update(point);
            map.add(new TerrainSquare(new Rect(100,100,200,200), Color.rgb(255,228,196)));
            //point.set(600+SQUARE_WIDTH,(100 + SQUARE_WIDTH)*j);
            point.set(400+SQUARE_WIDTH,(100)*j);
            map.get(i+1).update(point);
            i+=2;
            j++;
        }

    }

    public void parse_by_enum(){
        //take in a string of 0's,1's,2's,3's output an array or arraylist of enums representing
        //roads, dirt, lava, etc
        //the fill list method will read this array and parse

    }


    public void poll_map(){
        //pulls map from server/database
    }
}
