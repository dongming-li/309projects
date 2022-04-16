package com.example.mithi.battleoftheworldsmyway;

/**
 * Created by Aaron.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

/**
 * class for the player game object
 */
public class Player {

    //Bitmap to get character from image
    private Bitmap bitmap;

    private String character;

    private int score;

    private int coins;

    //coordinates
    private int x;
    private int y;

    private int minX;
    private int minY;
    private int maxX;
    private int maxY;

    private int health;

    private Bitmap healthBar;

    private int healthX;
    private int healthY;

    private Bitmap healthBarBorder;

    private int borderX;
    private int borderY;

    //collision mesh for the character
    private Rect detectCollision;

    /**
     * constructor for the player game object
     * @param context
     * @param mapX
     * @param mapY
     */
    public Player(Context context, int mapX, int mapY) {
        x = 850;
        y = 450;

        //Getting bitmap from drawable resource
        setCharacter(context);

        health = 100;

        healthBar = BitmapFactory.decodeResource(context.getResources(), R.drawable.health);

        healthX = x-15;
        healthY = y-40;

        healthBarBorder = BitmapFactory.decodeResource(context.getResources(),R.drawable.healthbarborder);

        borderX = x-15;
        borderY = y-40;


        //initializing Collision object
        detectCollision = new Rect(x,y,bitmap.getWidth(),bitmap.getHeight());

        character = "Berserker";

        score = 0;

        coins = 0;
    }

    /**
     * method for updating the state of the player
     */
    public void update(){

        detectCollision.left = x;
        detectCollision.top = y;
        detectCollision.right = x + bitmap.getWidth();
        detectCollision.bottom = y + bitmap.getHeight();
    }

    /**
     * method for calculating the damage for the player when struck by the enemy weapon
     * @param damage
     * damage the player takes upon collision with enemy weapon
     */
    public void takeDamage(int damage){
        health -= damage;
        healthBar = Bitmap.createScaledBitmap(healthBar,healthBar.getWidth()-(damage*2),healthBar.getHeight(),false);
    }

    //setters

    /**
     * method for changing the character bitmap for switching characters
     * @param character
     */
    private void setBitmap(Bitmap character){
        bitmap = character;
    }

    /**
     * method for setting the character in the game
     * @param context
     * character context needed for setting character bitmap
     */
    private void setCharacter(Context context){
        if(CharacterProfile.characterClass.equals("Berserker")){
            bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.berserker_character_bitmap);
            setBitmap(bitmap);
        }
        else if(CharacterProfile.characterClass.equals("Ki-Master")){
            bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.ki_master_character_bitmap);
            setBitmap(bitmap);
        }
        else{
            bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.galactic_ranger_character_bitmap);
            setBitmap(bitmap);
        }
    }

    /**
     * method for setting the score for the player
     * @param newScore
     * score that will be added to the player's current score
     */
    public void setScore(int newScore){score += newScore;}

    //getters

    /**
     * method for returning the collision rectangle for the player
     * @return
     * collision rectangle for the player
     */
    public Rect getDetectCollision(){
        return detectCollision;
    }

    /**
     * method for returning the character bitmap for the player
     * @return
     * collision rectangle for the player character
     */
    public Bitmap getBitmap(){
        return bitmap;
    }

    /**
     * method for returning the x coordinate for the upper left corner of the player bitmap
     * @return
     * x coordinate for the upper left corner of character bitmap
     */
    public int getX(){
        return x;
    }

    /**
     * method for returning the y coordinate for the upper left corner of the player bitmap
     * @return
     * y coordinate for the upper left corner of character bitmap
     */
    public int getY(){
        return y;
    }

    /**
     * returns the current health of the character
     * @return
     * health for the character
     */
    public int getHealth(){
        return health;
    }

    /**
     * method for returning the health bar bitmap for the player
     * @return
     * health bar bitmap
     */
    public Bitmap getHealthBar(){return healthBar;}

    /**
     * method for returning the x coordinate for the upper left corner of the health bar
     * @return
     * x coordinate for upper left corner of health bar
     */
    public int getHealthX(){return healthX;}

    /**
     * method for returning the y coordinate for the upper left corner of the health bar
     * @return
     * y coordinate for upper left corner of health bar
     */
    public int getHealthY(){return healthY;}

    /**
     * method for returning the bitmap for the border surrounding the health bar
     * @return
     * bitmap for border surrounding the health bar
     */
    public Bitmap getHealthBarBorder(){return healthBarBorder;}

    /**
     * method for returning the x coordinate for the upper left corner of the border
     * surrounding the health bar
     * @return
     * x coordinate for upper left corner of border surrounding health bar
     */
    public int getBorderX(){return borderX;}

    /**
     * method for returning the y coordinate for the upper right corner of the border
     * surrounding the health bar
     * @return
     * y coordinate for upper left corner of border surrounding health bar
     */
    public int getBorderY(){return borderY;}

    /**
     * method for returning the character class label for current character
     * @return
     * character class label for current character
     */
    public String getCharacter(){
        return character;
    }

    /**
     * method for returning the score of the player
     * @return
     * score of the player
     */
    public int getScore(){return score;}

    /**
     * method for returning the amount of coins for the player
     * @return
     * amount of coins for the player
     */
    public int getCoins(){return coins;}
}