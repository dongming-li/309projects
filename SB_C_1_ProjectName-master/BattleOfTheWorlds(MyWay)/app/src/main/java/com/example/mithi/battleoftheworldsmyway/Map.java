package com.example.mithi.battleoftheworldsmyway;

/**
 * Created by Aaron.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * class for the map game object
 */
public class Map {

    private Bitmap bitmap;

    private int mapX;
    private int mapY;
    private int speed;


    private int eventX;
    private int eventY;

    private int mapSetX;
    private int mapSetY;

    private int offsetMinX;
    private int offsetMinY;
    private int offsetMaxX;
    private int offsetMaxY;

    private boolean movement;

    private String currentMap;

    private Dpad dpad;

    /**
     * constructor for the map game object
     * @param context
     * @param screenX
     * @param screenY
     * @param dpad
     */
    public Map(Context context, int screenX, int screenY, Dpad dpad){
        bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.bedroom);

        currentMap = "bedroom";

        mapX = 370;
        mapY = -10;

        mapSetX = 370;
        mapSetY = -10;

        speed = -50;
        movement = false;

        offsetMinX = 425;
        offsetMinY = 15;
        offsetMaxX = 1830;
        offsetMaxY = 1790;

        this.dpad = dpad;
    }

    /**
     * method for updating the map object's game state
     */
    public void update(){
        if(movement){
            if(dpad.getRight().contains(eventX,eventY)){
                goRight();
            }
            if(dpad.getLeft().contains(eventX,eventY)) {
                goLeft();
            }
            if(dpad.getUp().contains(eventX,eventY)){
                goUp();
            }
            if(dpad.getDown().contains(eventX,eventY)){
                goDown();
            }
        }

        //preventative measures so character won't go off the map
        if(mapY > (mapSetY + offsetMinY)){
            mapY = (mapSetY + offsetMinY);
        }
        if(mapY < (mapSetY -offsetMaxY)){
            mapY = (mapSetY -offsetMaxY);
        }
        if(mapX > (mapSetX + offsetMinX)){
            mapX = (mapSetX + offsetMinX);
        }
        if(mapX < (mapSetX -offsetMaxX)){
            mapX = (mapSetX -offsetMaxX);
        }
    }

    /**
     * method for moving the map to the left
     */
    private void goLeft(){
        mapX -= speed;
    }

    /**
     * method for moving the map to the right
     */
    private void goRight(){
        mapX += speed;
    }

    /**
     * method for moving the map up
     */
    private void goUp(){
        mapY -= speed;
    }

    /**
     * method for moving the map down
     */
    private void goDown(){
        mapY += speed;
    }

    //setters

    /**
     * method for setting the map bitmap for changing maps
     * @param newBitmap
     * Bitmap for changing the map
     */
    public void setBitmap(Bitmap newBitmap){
        bitmap = newBitmap;
    }

    /**
     * method for setting the map's x coordinate when changing maps
     * @param newX
     * the new x value for the map
     */
    public void setMapX(int newX){mapX = newX;}

    /**
     * method for setting the map's y coordinate when changing maps
     * @param newY
     * the new y value for the map
     */
    public void setMapY(int newY){mapY = newY;}

    /**
     * method for updating the event x coordinate
     * @param inputX
     * x value for the event from the gameview screen
     */
    public void setEventX(int inputX){
        eventX = inputX;
    }

    /**
     * method for updating the event y coordinate
     * @param inputY
     * y value for the event from the gameview screen
     */
    public void setEventY(int inputY){
        eventY = inputY;
    }

    /**
     * method for determining and controlling map movement
     */
    public void setMovementTrue(){
        movement = true;
    }

    /**
     * method for stopping movement of the map. Do not use for stopping the character.
     */
    public void setMovementFalse(){
        movement = false;
    }

    /**
     * method for labeling the current map
     * @param newMap
     * String value stating the current map
     */
    public void setCurrentMap(String newMap){currentMap = newMap;}

    /**
     * method for controlling the movement of the map so that
     * the player character does not go off the map.
     * @param newSetX
     * x value for controlling the boundaries
     */
    public void setMapCurrentX(int newSetX){
        mapSetX = newSetX;
    }

    /**
     * method for controlling the movement of the map so that
     * the player character does not go off the map.
     * @param newSetY
     * y value for controlling the boundaries
     */
    public void setMapCurrentY(int newSetY){
        mapSetY = newSetY;
    }

    /**
     * method for controlling the minimum offset x value for preventing the character
     * from going off the map.
     * @param newOffset
     * new minimum x value for offset
     */
    public void setOffsetMinX(int newOffset){
        offsetMinX = newOffset;
    }

    /**
     * method for controlling the minimum offset y value for preventing the character
     * from going off the map.
     * @param newOffset
     * new minimum y value for offset
     */
    public void setOffsetMinY(int newOffset){
        offsetMinY = newOffset;
    }

    /**
     * method for controlling the maximum offset x value for preventing the character
     * from going off the map.
     * @param newOffset
     * new maximum x value for offset
     */
    public void setOffsetMaxX(int newOffset){
        offsetMaxX = newOffset;
    }

    /**
     * method for controlling the maximum offset y value for preventing the character
     * from going off the map.
     * @param newOffset
     * new maximum y value for offset
     */
    public void setOffsetMaxY(int newOffset){
        offsetMaxY = newOffset;
    }

    //getters
    /**
     * method for returning the bitmap for the map
     * @return
     * bitmap object for the map
     */
    public Bitmap getBitmap(){
        return bitmap;
    }

    /**
     * method for returning the x coordinate for the upper left hand corner of the map.
     * @return
     * x coordinate for the map
     */
    public int getMapX(){return mapX;}

    /**
     * method for returning the y coordinate for the upper left hand corner of the map.
     * @return
     * y coordinate for the map
     */
    public int getMapY(){return mapY;}

    /**
     * returns the label for the current map
     * @return
     * string label for the current map
     */
    public String getCurrentMap(){return currentMap;}

}