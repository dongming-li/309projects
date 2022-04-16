package com.example.alex.mainmenu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static com.example.alex.mainmenu.MainThread.canvas;
import static com.example.alex.mainmenu.VolleySingleton.SERVER_ADDRESS;

/**
 * Created by Alex on 10/2/2017.
 */

public final class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private MainThread thread;

    //received from opposing player at start of each round
  //  public ArrayList<Unit> enemy_units= new ArrayList<Unit>(200);


    private int spawn_x = 450;
    private int spawn_y = -3500;

    private GenerateEnemies enemiesList = new GenerateEnemies(spawn_x, spawn_y);

    public ArrayList<GameObject> my_turrets = new ArrayList<GameObject>(200);



    private final int playerID = 2;
    private final int adversaryID = 1;

    private Turret turret;
    private Point turret_point;

    private Unit unit;
    private Point unit_Point;

    private final GameActivity gameActivity;
    private GenerateMap game_map = new GenerateMap();



    private Point enemy_point;



    public GamePanel(Context context){
        super(context);
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);

        gameActivity= new GameActivity(context);
        initialize(context);


        setFocusable(true);

    }


    private void initialize(Context context){

        turret = new Turret(new Rect(100, 100, 200, 200), Color.YELLOW, 200, new Position(250, 250));
        my_turrets.add(turret);
        turret_point=new Point(600,600);


//        enemy_point = new Point(spawn_x,spawn_y);
//        Unit g = new Unit(new Position(enemy_point.x, enemy_point.y), 1, 10, com.example.alex.mainmenu.DamageTypes.FIRE, new Rect(500,500,600,600), Color.rgb(128,128,128));
//        enemy_units.add(g);
//        enemy_units.get(0).update(enemy_point);


    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height){

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder){

        thread = new MainThread(getHolder(), this);

        thread.setRunning(true);
        thread.start();

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (true) {
            try {
                thread.setRunning(false);
                thread.join();

            } catch (Exception e) {
                e.printStackTrace();
            }
            retry = false;


        }
        }

        @Override
        public boolean onTouchEvent(MotionEvent event){

            Point p = new Point((int)event.getX(),(int)event.getY());
            p.set((int)event.getX(),(int)event.getY());

//            turret_point.set((int)event.getX(),(int)event.getY());
//            if(turret_point.x < 200 && turret_point.x > 100 && turret_point.y < 200 && turret_point.y > 100 ){
//                my_turrets.clear();
//            }

            if(p.x < 500 && p.x > 400 && p.y < 500 && p.y > 400){
                //who cares
                Turret a = new Turret(new Rect(100, 100, 200, 200), Color.BLUE, 200, new Position(p.x, p.y));
                my_turrets.add(a);
                my_turrets.get(my_turrets.size() - 1).update(turret_point);
            }

            else {
//                Turret a = new Turret(new Rect(100, 100, 200, 200), Color.BLUE);
//                my_turrets.add(a);
//                my_turrets.get(my_turrets.size() - 1).update(turret_point);


                //drawing app
//                Unit a = new Unit (new Rect(100, 100, 200, 200), Color.BLACK);
//                my_turrets.add(a);
//                my_turrets.get(my_turrets.size() - 1).update(turret_point);


                ///updates enemy position on touch event
                //    enemy_point.set(enemy_point.x, enemy_point.y + 50);
              //      enemy_units.get(0).update(enemy_point);


           //     unit_Point.set((int)event.getX(),(int)event.getY());
              //  unit_Point.set((int) unit_Point.x, 20);
             //   unit.update(unit_Point);
            }
           // playerPoint.set((int)event.getX(),(int)event.getY());
            this.update();
            return true;
        }

    /**
     * This method updates the position to the server.
     * First updates on the canvas the poisition of the player.
     *
     * Then It sends the current player position.
     *
     */
    public void update(){

        Unit temp;
        Position temp_Position;
        for(int i =0; i< enemiesList.enemyUnits.size(); i++){
            temp = enemiesList.enemyUnits.get(i);
            temp_Position = temp.getPosition();
            temp_Position.set(temp_Position.getX(), temp_Position.getY()+15);
            temp.update(temp_Position);

        }
//        Point temp = turret_point;
//
//
//        for(int i =0; i<my_turrets.size(); i++){
//
//            temp.set(temp.x+i, temp.y+i);
//            my_turrets.get(i).update(temp);
//
//        }



        //turret.update(turret_point);
         //   player.update(playerPoint);
         //   gameActivity.updatePosition(playerPoint.x,playerPoint.y,playerID,adversaryID);
       gameActivity.updatePosition(turret_point.x,turret_point.y,playerID,adversaryID);
     //   gameActivity.updatePosition(unit_Point.x,unit_Point.y,playerID,adversaryID);

        }

     @Override
     public void draw(Canvas canvas){
            super.draw(canvas);
            canvas.drawColor(Color.rgb(0,204,0));


        //    enemy_player.draw(canvas);
       //     player.draw(canvas);
       //     turret.draw(canvas);

         for(int i =0; i<game_map.map.size(); i++){
             game_map.map.get(i).draw(canvas);
         }


         for(int i =0; i<my_turrets.size(); i++){
             my_turrets.get(i).draw(canvas);
         }

         for(int i =0; i< enemiesList.enemyUnits.size(); i++){
             //if the unit is more than one block width off screen do not draw it
             if(enemiesList.enemyUnits.get(i).position.getX() < -100 || enemiesList.enemyUnits.get(i).position.getX() > 1500 || enemiesList.enemyUnits.get(i).position.getY() < -100 || enemiesList.enemyUnits.get(i).position.getY() > 1500 ){
                //Do nothing
             }
             else{
                 enemiesList.enemyUnits.get(i).draw(canvas);
             }

         }

            update();
        unit.draw(canvas);
     }


    /**
     * This method polls the server to grab information about the opponents position.
     */
    public void poll() {
     //   Gamestate gs = gameActivity.pollPosition(playerID,adversaryID);
    //    enemyPoint.set(gs.getX(), gs.getY());
     //   enemy_player.update(enemyPoint);


    }

}