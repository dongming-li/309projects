package com.example.alex.mainmenu;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by Alex on 10/21/2017.
 */


    /**
     * Created by Alex on 10/21/2017.
     */

    public class Unit implements GameObject {

        public Position position;
        public int ID;
        public int health;
        private DamageTypes unitType;
        private Rect rectangle;
        private int color;
        //TODO speed of unit add to code make stuff work
        private int speed;



        public Unit (int speed, Position position, int ID, int health, DamageTypes unitType, Rect rectangle, int color){
            this.health = health;
            this.ID = ID;
            this.position = position;
            this.unitType = unitType;
            this.rectangle = rectangle;
            this.color= color;
            update(position);

        }

        public void calculate_Damage(int damage, DamageTypes type){

            if(type == unitType){
                damage = damage/2;
            }
            health = health - damage;
            //TODO is the unit killed?

        }





        @Override
        public void draw(Canvas canvas) {
            Paint paint = new Paint();
            paint.setColor(color);
            canvas.drawRect(rectangle, paint);
        }

        @Override
        public void update() {

        }

        @Override
        public void update(Point point) {

        }

        public void update(Position point){
            //l,t,r,b
            rectangle.set(point.getX() - rectangle.width()/2, point.getY()-rectangle.height()/2, point.getX() + rectangle.width()/2, point.getY()+rectangle.height()/2);
        }

        public Position getPosition(){
            return position;
        }


    }
