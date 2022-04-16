package com.example.mithi.battleoftheworldsmyway;

/**
 * Created by Aaron Rees.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

/**
 * Class for the Door game object for transitioning from map to map
 */
public class Door {

    private Bitmap bitmap;

    private int x;
    private int y;
    private int dx;
    private int dy;

    private int currentX;
    private int currentY;

    private int offsetMinX;
    private int offsetMinY;
    private int offsetMaxX;
    private int offsetMaxY;

    private int eventX;
    private int eventY;

    private int speed;

    private Rect detectCollision;

    private Dpad dpad;

    boolean movement;

    /**
     * constructor for the door game object
     * @param context
     * @param dpad
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    public Door(Context context,Dpad dpad,int left, int top,int right, int bottom){
        x = 2650;
        y = 1350;
        dx = right;
        dy = bottom;

        currentX = 2650;
        currentY = 1350;

        bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.door);

        detectCollision = new Rect(x,y,dx,dy);

        movement = false;

        speed = -50;

        offsetMinX = 425;
        offsetMinY = 15;
        offsetMaxX = 1830;
        offsetMaxY = 1790;

        this.dpad = dpad;
    }

    /**
     * method for updating the position and tracking collision of the door
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

        //preventative measures so door won't move unless character is moving
        if(y > (currentY + offsetMinY)){
            y = (currentY + offsetMinY);
        }
        if(y < (currentY -offsetMaxY)){
            y = (currentY -offsetMaxY);
        }
        if(x > (currentX + offsetMinX)){
            x = (currentX + offsetMinX);
        }
        if(x < (currentX -offsetMaxX)){
            x = (currentX -offsetMaxX);
        }

        detectCollision.left = x;
        detectCollision.top = y;
        detectCollision.right = x + bitmap.getWidth();
        detectCollision.bottom = y + bitmap.getHeight();
    }

    /**
     * method for making the door move left on the screen
     */
    public void goLeft(){
        x -= speed;
    }

    /**
     * method for making the door move right on the screen
     */
    public void goRight(){
        x += speed;
    }

    /**
     * method for making the door move up on the screen
     */
    public void goUp(){
        y -= speed;
    }

    /**
     * method for making the door move down on the screen
     */
    public void goDown(){
        y += speed;
    }

    //setters

    /**
     * method for setting the x coordinate of the finger press on the screen by the user
     * @param inputX
     * x coordinate from the game view screen
     */
    public void setEventX(int inputX){
        eventX = inputX;
    }

    /**
     * method for setting the y coordinate for the finger press on the screen by the user
     * @param inputY
     * y coordinate from the game view screen
     */
    public void setEventY(int inputY){
        eventY = inputY;
    }

    /**
     * method for determining whether the door is moving or not
     * @param yesOrNo
     * boolean set at the game view screen
     */
    public void setMovement(boolean yesOrNo){movement = yesOrNo;}

    /**
     * method for setting the x coordinate of the upper left hand corner of the door
     * @param newX
     * x coordinate of the upper left hand corner of the door
     */
    public void setX(int newX){x = newX;}

    /**
     * method for setting the y coordinate of the upper left hand corner of the door
     * @param newY
     * y coordinate of the upper left hand corner of the door
     */
    public void setY(int newY){y = newY;}

    /**
     * method for setting the x value to correct for the offset of the door
     * @param newX
     * x value for correction of offset
     */
    public void setCurrentX(int newX){currentX = newX;}

    /**
     * method for setting the y value to correct for the offset of the door
     * @param newY
     * y value for the correction of offset
     */
    public void setCurrentY(int newY){currentY = newY;}

    /**
     * method for setting the minimum x value offset for the door
     * @param newOffset
     * x value for the minimum offset
     */
    public void setOffsetMinX(int newOffset){
        offsetMinX = newOffset;
    }

    /**
     * method for setting the minimum y value offset for the door
     * @param newOffset
     * y value for the minimum offset
     */
    public void setOffsetMinY(int newOffset){
        offsetMinY = newOffset;
    }

    /**
     * method for setting the maximum x value offset for the door
     * @param newOffset
     * x value for the maximum offset
     */
    public void setOffsetMaxX(int newOffset){
        offsetMaxX = newOffset;
    }

    /**
     * method for setting the maximum y value offset for the door
     * @param newOffset
     * y value for the maximum offset
     */
    public void setOffsetMaxY(int newOffset){
        offsetMaxY = newOffset;
    }

    //getters

    /**
     * method for returning the collision rectangle for the door
     * @return
     * collision rectangle for door
     */
    public Rect getDetectCollision(){
        return detectCollision;
    }

    /**
     * method for returning the bitmap for the door
     * @return
     * bitmap for the door
     */
    public Bitmap getBitmap(){
        return bitmap;
    }

    /**
     * method for returning the x coordinate for the upper left corner of the door
     * @return
     * x coordinate for the upper left corner of the door
     */
    public int getX(){
        return x;
    }

    /**
     * method for returning the y coordinate for the upper left corner of the door
     * @return
     * y coordinate for the upper left corner of the door
     */
    public int getY(){
        return y;
    }

}