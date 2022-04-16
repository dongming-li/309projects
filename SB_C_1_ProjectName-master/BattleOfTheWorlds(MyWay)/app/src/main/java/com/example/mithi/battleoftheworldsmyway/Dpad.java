package com.example.mithi.battleoftheworldsmyway;

/**
 * Created by Aaron.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

/**
 * class for the directional pad implemented in the game's user interface
 */
public class Dpad {
    private Rect left;
    private Rect right;
    private Rect up;
    private Rect down;

    private Rect attackButton;

    private Bitmap leftBitmap;
    private Bitmap rightBitmap;
    private Bitmap upBitmap;
    private Bitmap downBitmap;

    private Bitmap attackButtonBitmap;

    private int attackX;
    private int attackY;

    private int leftX;
    private int leftY;

    private int rightX;
    private int rightY;

    private int downX;
    private int downY;

    private int upX;
    private int upY;

    /**
     * constructor for the directional pad
     * @param context
     * @param screenX
     * @param screenY
     */
    public Dpad(Context context, int screenX, int screenY){
        leftX = 1350;
        leftY = 760;

        rightX = 1620;
        rightY = 760;

        downX = 1470;
        downY = 910;

        upX = 1470;
        upY = 640;

        attackX = 1475;
        attackY = 770;

        leftBitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.leftarrow);
        left = new Rect(leftX,leftY,leftBitmap.getWidth(),leftBitmap.getHeight());

        rightBitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.rightarrow);
        right = new Rect(rightX,rightY,rightBitmap.getWidth(),rightBitmap.getHeight());

        upBitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.uparrow);
        up = new Rect(upX,upY,upBitmap.getWidth(),upBitmap.getHeight());

        downBitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.downarrow);
        down = new Rect(downX,downY,downBitmap.getWidth(),downBitmap.getHeight());

        attackButtonBitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.attackbutton);
        attackButton = new Rect(attackX,attackY,attackButtonBitmap.getWidth(),attackButtonBitmap.getHeight());

        left.left = leftX;
        left.top = leftY;
        left.right = leftX + leftBitmap.getWidth();
        left.bottom = leftY + leftBitmap.getHeight();

        right.left = rightX;
        right.top = rightY;
        right.right = rightX + rightBitmap.getWidth();
        right.bottom = rightY + rightBitmap.getHeight();

        up.left = upX;
        up.top = upY;
        up.right = upX + upBitmap.getWidth();
        up.bottom =upY + upBitmap.getHeight();

        down.left = downX;
        down.top = downY;
        down.right = downX + downBitmap.getWidth();
        down.bottom = downY + downBitmap.getHeight();

        attackButton.left = attackX;
        attackButton.top = attackY;
        attackButton.right = attackX + attackButtonBitmap.getWidth();
        attackButton.bottom = attackY + attackButtonBitmap.getHeight();
    }

    /**
     * method for returning the boundaries for detecting motion events
     * within the left directional button
     * @return
     * the boundaries for detecting finger presses on the left directional button
     */
    public Rect getLeft(){
        return left;
    }

    /**
     * method for returning the boundaries for detecting motion events
     * within the right directional button
     * @return
     * the boundaries for detecting finger presses on the right directional button
     */
    public Rect getRight(){
        return right;
    }

    /**
     * method for returning the boundaries for detecting motion events
     * within the up directional button
     * @return
     * the boundaries for detecting finger presses on the up directional button
     */
    public Rect getUp(){
        return up;
    }

    /**
     * method for returning the boundaries for detecting motion events
     * within the down directional button
     * @return
     * the boundaries for detecting finger presses on the down directional button
     */
    public Rect getDown(){
        return down;
    }

    /**
     * method for returning the boundaries for detecting presses on the attack button
     * @return
     * boundaries for detection in the attack button
     */
    public Rect getAttackButton(){return attackButton;}

    /**
     * method for returning the bitmap for the left directional button
     * @return
     * bitmap for the left directional button
     */
    public Bitmap getLeftBitmap(){return leftBitmap;}

    /**
     * method for returning the bitmap for the right directional button
     * @return
     * bitmap for the right directional button
     */
    public Bitmap getRightBitmap(){return rightBitmap;}

    /**
     * method for returning the bitmap for the up directional button
     * @return
     * bitmap for the up directional button
     */
    public Bitmap getUpBitmap(){return upBitmap;}

    /**
     * method for returning the bitmap for the down directional button
     * @return
     * bitmap for the down directional button
     */
    public Bitmap getDownBitmap(){return downBitmap;}

    /**
     * method for returning the bitmap for the attack button
     * @return
     * bitmap for the attack button
     */
    public Bitmap getAttackButtonBitmap(){return attackButtonBitmap;}
    /**
     * method for returning the x coordinate for
     * the upper left corner of the down directional button bitmap
     * @return
     * x coordinate for the upper left corner of the down directional button
     */
    public int getDownX() {
        return downX;
    }

    /**
     * method for returning the y coordinate for
     * the upper left corner of the down directional button bitmap
     * @return
     * y coordinate for the upper left corner of the down directional button
     */
    public int getDownY(){
        return downY;
    }

    /**
     * method for returning the x coordinate for
     * the upper left corner of the up directional button bitmap
     * @return
     * x coordinate for the upper left corner of the up directional button
     */
    public int getUpX(){
        return upX;
    }

    /**
     * method for returning the y coordinate for
     * the upper left corner of the up directional button bitmap
     * @return
     * y coordinate for the upper left corner of the up directional button
     */
    public int getUpY(){
        return upY;
    }

    /**
     * method for returning the x coordinate for
     * the upper left corner of the left directional button bitmap
     * @return
     * y coordinate for the upper right corner of the left directional button
     */
    public int getLeftX(){
        return leftX;
    }

    /**
     * method for returning the y coordinate for
     * the upper left corner of the left directional button bitmap
     * @return
     * y coordinate for the upper right corner of the left directional button
     */
    public int getLeftY(){
        return leftY;
    }

    /**
     * method for returning the x coordinate for
     * the upper left corner of the right directional button bitmap
     * @return
     * x coordinate for the upper left corner of the right directional button
     */
    public int getRightX(){
        return rightX;
    }

    /**
     * method for returning the y coordinate for
     * the upper left corner of the right directional button bitmap
     * @return
     * y coordinate for the upper left corner of the right directional button
     */
    public int getRightY(){
        return rightY;
    }

    /**
     * method for returning the x coordinate for the upper left corner of the attack button
     * @return
     * x coordinate for the upper left corner of the attack button
     */
    public int getAttackX(){return  attackX;}

    /**
     * method for returning the y coordinate for the upper left corner of the attack button
     * @return
     * y coordinate for the upper left corner of the attack button
     */
    public int getAttackY(){return  attackY;}
}