package com.example.mithi.battleoftheworldsmyway;

/**
 * Created by Aaron Rees.
 */
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

//import com.github.nkzawa.socketio.client.IO;
//import com.github.nkzawa.socketio.client.Socket;
//import com.github.nkzawa.emitter.Emitter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

/**
 * This is the gameboard for the game. The run method is
 * repeatedly called to constantly update the game.
 * It implements Canvas, Surfaceholder and Paint
 * in order to draw all of the game elements on the board.
 */
public class GameView extends SurfaceView implements Runnable {

    //boolean variable to track if the game is playing or not
    volatile boolean playing;
    //public static Socket mSocket = MainActivity.returnSocket();

    //the game thread
    private Thread gameThread = null;

    //These objects will be used for drawing
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;

    Context context;
    int screenX;
    int screenY;

    //creating map variable
    private Map map;

    //creating player variable
    private Player player;

    //creating Enemy variable
    private ArrayList<Enemy> enemies;

    private Weapon weapon;

    private Door door;

    private Dpad dpad;

    private ResultScreen resultScreen;

    private String currentMap;

    private int numOfEnemies;

    //Class constructor
    public GameView(Context context, int screenX, int screenY) {
        super(context);

        this.context = context;
        this.screenX = screenX;
        this.screenY = screenY;

        //arrow buttons
        dpad = new Dpad(context, screenX, screenY);

        // creating new map
        map = new Map(context, screenX, screenY, dpad);

        //initializing player object
        player = new Player(context, map.getBitmap().getWidth(), map.getBitmap().getHeight());

        weapon = new Weapon(context, screenX, screenY);

        numOfEnemies = 1;

        enemies = new ArrayList<>();
        for (int a = 0; a < numOfEnemies; a++) {
            Random  rand = new Random();
            int xCoord = rand.nextInt(700)+100;
            int yCoord = rand.nextInt(700)+100;

            enemies.add(new Enemy(context,xCoord,yCoord,dpad));

        }

        door = new Door(context, dpad, 2650, 1350, 3050, 1450);

        resultScreen = new ResultScreen(context);

        //initializing drawing objects
        surfaceHolder = getHolder();
        paint = new Paint();
    }

    /**
     * method for constantly updating the game. It is repeatedly called to constantly
     * update and refresh the game state.
     */
    @Override
    public void run() {
        while (playing) {
            //update the frame
            update();
            //to  draw the frame
            draw();
            //to control
            control();
        }
    }

    /**
     * method for updating the state of all the game elements.
     */
    private void update() {

        //moving background with character
        map.update();

        player.update();

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update();
        }

        weapon.update();

        if(weapon.getAttacking()) {
            for (Enemy enemy : enemies) {
                if (Rect.intersects(enemy.getDetectCollision(), weapon.getDetectCollision())) {
                    enemy.takeDamage(weapon.getDamage());
                    enemy.isAttacking();
                    enemy.attack();
                    if(Rect.intersects(player.getDetectCollision(),enemy.getEnemyWeaponDetectCollision())){
                        player.takeDamage(1);
                    }
                }
            }
        }

        door.update();

        if (Rect.intersects(player.getDetectCollision(), door.getDetectCollision()) && map.getCurrentMap() == "bedroom") {
            map.setBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.hallway));
            map.setMapX(-1100);
            map.setMapY(-1350);
            map.setMapCurrentX(-1100);
            map.setMapCurrentY(-1350);
            map.setOffsetMinX(1880);
            map.setOffsetMinY(1050);
            map.setOffsetMaxX(380);
            map.setOffsetMaxY(2300);
            door.setX(-1000);
            door.setY(-200);
            door.setCurrentX(-1000);
            door.setCurrentY(-200);
            door.setOffsetMinX(1880);
            door.setOffsetMinY(1050);
            door.setOffsetMaxX(380);
            door.setOffsetMaxY(2300);
            map.setCurrentMap("hallway");
        } else if (Rect.intersects(player.getDetectCollision(), door.getDetectCollision()) && map.getCurrentMap() == "hallway") {
            map.setBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.kitchen));
            map.setMapX(-800);
            map.setMapY(0);
            map.setMapCurrentX(-800);
            map.setMapCurrentY(0);
            map.setOffsetMinX(1530);
            map.setOffsetMinY(70);
            map.setOffsetMaxX(200);
            map.setOffsetMaxY(790);
            door.setX(-100);
            door.setY(100);
            door.setCurrentX(-100);
            door.setCurrentY(100);
            door.setOffsetMinX(1530);
            door.setOffsetMinY(70);
            door.setOffsetMaxX(200);
            door.setOffsetMaxY(790);
            map.setCurrentMap("kitchen");
        } else if (Rect.intersects(player.getDetectCollision(), door.getDetectCollision()) && map.getCurrentMap() == "kitchen") {
            map.setBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.frontyard));
            map.setMapX(-2200);
            map.setMapY(-2050);
            map.setMapCurrentX(-2200);
            map.setMapCurrentY(-2050);
            map.setOffsetMinX(3030);
            map.setOffsetMinY(2500);
            map.setOffsetMaxX(2000);
            map.setOffsetMaxY(790);
            door.setX(-20000);
            door.setY(-10000);
            door.setCurrentX(-20000);
            door.setCurrentY(-10000);
            door.setOffsetMinX(3030);
            door.setOffsetMinY(2500);
            door.setOffsetMaxX(2000);
            door.setOffsetMaxY(790);
            map.setCurrentMap("frontyard");
        }

        if(enemies.size() == 0 && !resultScreen.getDisplayResultScreen()){
            numOfEnemies +=3;
            for (int a = 0; a < numOfEnemies; a++) {
                Random  rand = new Random();
                int xCoord = rand.nextInt(700)+100;
                int yCoord = rand.nextInt(700);
                enemies.add(new Enemy(context,xCoord,yCoord,dpad));
            }
        }
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).getHp() <= 0) {
                enemies.remove(i);
                player.setScore(10);
            }
        }

        if(player.getHealth()<=0){
            Looper.prepare();
            Toast.makeText(context, "You lost", Toast.LENGTH_SHORT).show();
            context.startActivity(new Intent(context,CharacterProfile.class));
        }
    }

    /**
     * method for drawing the game elements on the game board.
     */
    private void draw() {

        //checking if surface is valid
        if (surfaceHolder.getSurface().isValid()) {
            //locking the canvas
            canvas = surfaceHolder.lockCanvas();

            canvas.drawColor(Color.BLACK);
            //drawing the map
            canvas.drawBitmap(map.getBitmap(),
                    map.getMapX(),
                    map.getMapY(),
                    paint);

            //drawing the player
            canvas.drawBitmap(player.getBitmap(),
                    player.getX(),
                    player.getY(),
                    paint);

            //drawing the border around the health bar
            canvas.drawBitmap(player.getHealthBarBorder(),
                    player.getBorderX(),
                    player.getBorderY(),
                    paint);

            //drawing the healthbar
            canvas.drawBitmap(player.getHealthBar(),
                    player.getHealthX(),
                    player.getHealthY(),
                    paint);

            //drawing the weapon for the character
            canvas.drawBitmap(weapon.getBitmap(),
                    weapon.getX(),
                    weapon.getY(),
                    paint);

            canvas.drawBitmap(door.getBitmap(), door.getX(), door.getY(), paint);

            if (map.getCurrentMap() == "frontyard") {
                for (int i = 0; i < enemies.size(); i++) {
                    canvas.drawBitmap(enemies.get(i).getBitmap(),
                            enemies.get(i).getX(),
                            enemies.get(i).getY(),
                            paint);

                    canvas.drawBitmap(enemies.get(i).getWeaponBitmap(),
                                      enemies.get(i).getEnemyWeaponX(),
                                      enemies.get(i).getEnemyWeaponY(),
                                      paint);

                    canvas.drawBitmap(enemies.get(i).getHealthBarBorder(),
                            enemies.get(i).getBorderX(),
                            enemies.get(i).getBorderY(),
                            paint);

                    canvas.drawBitmap(enemies.get(i).getHealthBar(),
                            enemies.get(i).getHealthX(),
                            enemies.get(i).getHealthY(),
                            paint);
                }
            }

            canvas.drawBitmap(dpad.getUpBitmap(),
                    dpad.getUpX(),
                    dpad.getUpY(),
                    paint);

            canvas.drawBitmap(dpad.getDownBitmap(),
                    dpad.getDownX(),
                    dpad.getDownY(),
                    paint);

            canvas.drawBitmap(dpad.getLeftBitmap(),
                    dpad.getLeftX(),
                    dpad.getLeftY(),
                    paint);

            canvas.drawBitmap(dpad.getRightBitmap(),
                    dpad.getRightX(),
                    dpad.getRightY(),
                    paint);

            canvas.drawBitmap(dpad.getAttackButtonBitmap(),
                    dpad.getAttackX(),
                    dpad.getAttackY(),
                    paint);

            if(enemies.size() == 0){
                resultScreen.setDisplayResultScreen(true);
                canvas.drawBitmap(resultScreen.getBitmap(),
                        resultScreen.getResultX(),
                        resultScreen.getResultY(),
                        paint);

                paint.setColor(Color.WHITE);
                paint.setTextSize(100);
                paint.setTypeface(Typeface.create("Exocet",Typeface.NORMAL));
                String experience = String.valueOf(player.getScore());
                String coin = String.valueOf(player.getCoins());
                canvas.drawText(experience,700,670,paint);
                canvas.drawText(coin,850,530,paint);
            }
            //unlocking the canvas
            surfaceHolder.unlockCanvasAndPost(canvas);
            invalidate();
        }
    }

    /**
     * Suspends the thread for a 17 milliseconds.
     */
    private void control(){
        try{
            gameThread.sleep(17);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    /**
     * method for pausing the game.
     */
    public void pause(){
        //when the game is paused
        //setting up the variable to false
        playing = false;
        try{
            //stopping the thread
            gameThread.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    /**
     * method for resuming the game thread run method after a pause.
     */
    public void resume(){
        //when game is resumed
        //starting the thread again
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * On Touch Event controller that tells when a user touches the screen of the device,
     * as well as executing various game state changes when a user touches the device.
     * @param event
     * @return
     * boolean event that returns true when user touches screen.
     */
    @Override
    public boolean onTouchEvent(MotionEvent event){

        int action  = event.getActionMasked();

        switch (action){

            case MotionEvent.ACTION_UP:
                //depress on screen
                for(Enemy enemy: enemies) {
                    enemy.setEnemyMovement(false);
                }
                map.setMovementFalse();
                door.setMovement(false);
                break;

            case MotionEvent.ACTION_DOWN:
                int x = (int)event.getX();
                int y = (int)event.getY();
                map.setEventX(x);
                map.setEventY(y);
                for(Enemy enemy:enemies) {
                    enemy.setEventX(x);
                    enemy.setEventY(y);
                }
                door.setEventX(x);
                door.setEventY(y);
                map.setMovementTrue();
                for(Enemy enemyMove:enemies) {
                    enemyMove.setEnemyMovement(true);
                }
                door.setMovement(true);
                if (dpad.getAttackButton().contains(x,y)) {
                    weapon.isAttacking();

//                    try {
//                        JSONObject json = new JSONObject();
//                        json.putOpt("inputId", "attack");
//                        json.putOpt("state", true);
//                        mSocket.emit("keyPress", json);
//                    } catch (JSONException e) {
//                        e.getStackTrace();
//                    }
                }
                resultScreen.setDisplayResultScreen(false);
                break;

            case MotionEvent.ACTION_OUTSIDE:
                //action not in screen
                break;
        }
        return true;
    }

    /**
     * method for returning the upper lefthand corner of the map coordinates.
     * @return
     * coordinates for the upperleft hand corner of the screen.
     */
    public int[] mapMovementCoord(){
        int[] coord = {map.getMapX(),map.getMapY()};
        return coord;
    }

    /**
     * returns coordinates for the upperleft corner of the players bitmap.
     * @return
     */
    public int[] playerCoord(){
        int [] coord ={player.getX(),player.getY()};
        return coord;
    }
}