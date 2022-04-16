package com.example.alex.mainmenu;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.constraint.solver.widgets.Rectangle;

import com.example.alex.mainmenu.GameObject;
import com.example.alex.mainmenu.Position;
import com.example.alex.mainmenu.Unit;

import java.util.ArrayList;


/**
 * Created by Alex on 10/16/2017.
 */

public class Turret implements GameObject {

    private Rect rectangle;
    private Position position;
    private int enemyID;
    private int target_Position;
    private Unit target_Unit;
    private int color;
    private int range;


    public Turret(Rect rectangle, int color, int range, Position position) {
        this.rectangle = rectangle;
        this.color = color;
        this.range = range;
        this.position = position;
    }


    @Override
    public void draw(Canvas canvas) {

        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle, paint);
    }

    @Override
    public void update() {
        logic();
    }


    public void update(Point point) {
        //l,t,r,b
        rectangle.set(point.x - rectangle.width() / 2, point.y - rectangle.height() / 2, point.x + rectangle.width() / 2, point.y + rectangle.height() / 2);
    }

    private void logic() {
        //do I have a target

        if (target_Unit!=null) {
            //if so shoot at it
            shoot();


        } else {
            //if not aquire one
            //static reference right now,
            //definitly should change this in the future
            aquire_target(GameLogic.units);
        }


    }

    /**
     * returns true if a target was aquired
     * @param units
     * @return
     */
    public boolean aquire_target(ArrayList<Unit> units) {
        //always target the first unit that is in the lead and is in range
        for (Unit u : units) {
            if (inRange(u)) {
                target_Unit = u;
                return true;
            }
        }
        target_Unit = null;
        return false;
    }

    private boolean inRange(Unit u) {
        return Position.distance(position, u.getPosition()) <= range;
    }

    public void find_targets_position() {
        //use ID to find it in array, tree, or whatever data structure we're using

    }

    public void shoot() {
        //use the coordinates of the target
        //create a projectile that moves really quickly
        //and send that projectile at the enemy's position
        //deal damage to enemy
        //enemy object calculates damage appropriately

    }


}
