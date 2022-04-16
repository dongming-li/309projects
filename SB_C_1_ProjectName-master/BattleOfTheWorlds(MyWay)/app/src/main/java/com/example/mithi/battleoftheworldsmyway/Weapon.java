package com.example.mithi.battleoftheworldsmyway;

/**
 * Created by Aaron Rees.
 */
import android.app.Notification;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.util.Log;

/**
 * class for the weapon game object
 */
public class Weapon {

    private Bitmap bitmap;

    private Bitmap laser;

    private int x;
    private int y;


    private int damage;

    private String weaponType;

    private Rect detectCollision;

    private boolean attacking;

    /**
     * constructor for the weapon game object
     * @param context
     * @param screenX
     * @param screenY
     */
    public Weapon(Context context, int screenX, int screenY){

        x = 840;
        y = 603;

        damage = 10;

        setWeapon(context);

//        laser = BitmapFactory.decodeResource(context.getResources(),R.drawable.lazer);

        attacking = false;

        detectCollision = new Rect(x,y,bitmap.getWidth(),bitmap.getHeight());
    }

    /**
     * method for updating the state of the weapon
     */
    public void update(){
        attack();

        detectCollision.left = x;
        detectCollision.top = y;
        detectCollision.right = x + bitmap.getWidth();
        detectCollision.bottom = y + bitmap.getHeight();
    }

    /**
     * method for changing the weapon based on current character class selected
     * @param context
     */
    public void setWeapon(Context context){
        if(CharacterProfile.characterClass.equals("Berserker")) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.fistweapon);
            weaponType = "fist weapon";
        }
        else if(CharacterProfile.characterClass.equals("Ki-Master")) {
            bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.staff);
            weaponType = "staff";
        }
        else{
            bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.blaster);
            weaponType = "blaster";
        }
    }

    /**
     * method for determining if the character is attacking or not
     */
    public void isAttacking(){
        attacking = true;
    }

    /**
     * method for returning the character back to it's previous state of not attacking
     */
    public void stopAttacking(){
        attacking = false;
    }

    /**
     * method for executing the attack animation for the weapon object
     */
    public void attack(){
        if(attacking){
            x += 100;
        }
        else{
            x -= 100;
        }

        //bounds for the weapon
        //lower bound
        if(x < 840){
            x = 840;
        }
        //upperbound
        if(x > 1055){
            x = 1055;
        }
        if(x == 1055){
            stopAttacking();
        }


        if(weaponType == "blaster"){

        }
    }

    /**
     * method for returning the bitmap for the weapon object
     * @return
     * bitmap for the weapon
     */
    public Bitmap getBitmap(){
        return bitmap;
    }

    /**
     * method for returning the x coordinate of the upper left corner of the weapon bitmap
     * @return
     * x coordinate for the upper left corner of the weapon bitmap
     */
    public int getX(){
        return x;
    }

    /**
     * method for returning the y coordinate for the upper left corner of the weapon bitmap
     * @return
     */
    public int getY(){
        return y;
    }

    /**
     * method for returning the damage value the weapon deals to enemy characters
     * @return
     * damage value for the weapon
     */
    public int getDamage(){
        return damage;
    }

    /**
     * method for returning the collion rectangle for the weapon object
     * @return
     * collision rectangle for the weapon object
     */
    public Rect getDetectCollision(){
        return detectCollision;
    }

    public boolean getAttacking(){return attacking;}
}
