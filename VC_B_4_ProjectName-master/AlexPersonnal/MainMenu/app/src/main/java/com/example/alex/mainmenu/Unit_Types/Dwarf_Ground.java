package com.example.alex.mainmenu.Unit_Types;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;

import com.example.alex.mainmenu.DamageTypes;
import com.example.alex.mainmenu.GameObject;
import com.example.alex.mainmenu.Position;
import com.example.alex.mainmenu.Unit;

/**
 * Created by Alex on 11/2/2017.
 */

public class Dwarf_Ground extends Unit {


    private Rect rectangle = new Rect();
    private Color tempColor = new Color();
    private int color = tempColor.rgb(102,51,0);
    private int health = 25;

    public Dwarf_Ground(int speed, Position position, int ID, int health, DamageTypes unitType, Rect rectangle, int color) {
        super(speed, position, ID, health, unitType, rectangle, color);
    }


//    public Dwarf_Ground(Position position, int id){
//        self = new Unit(position, id, health,DamageTypes.FIRE,rectangle,color);
//    }

   // public Dwarf_Ground (Position position, int ID, int health, DamageTypes unitType, Rect rectangle, int color){
       // super(position, ID, health, unitType, rectangle, color);


    //}

    @Override
    public void draw(Canvas canvas) {
    }

    @Override
    public void update() {

    }

    @Override
    public void update(Point point) {

    }
}
