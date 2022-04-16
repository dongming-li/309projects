package com.example.mithi.battleoftheworldsmyway;

/**
 * Created by Aaron Rees.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * Game object for the Enemy characters.
 */
public class Enemy {

    private Bitmap bitmap;
    private Bitmap healthBar;
    private Bitmap healthBarBorder;
    private Bitmap enemyWeaponBitmap;

    private int x;
    private int y;

    private int healthX;
    private int healthY;

    private int borderX;
    private int borderY;

    private int enemyWeaponX;
    private int enemyWeaponY;

    private int eventX;
    private int eventY;

    private int currentX;
    private int currentY;

    private int offsetMinX;
    private int offsetMinY;
    private int offsetMaxX;
    private int offsetMaxY;

    private int hp;

    private Rect detectCollision;
    private Rect enemyWeaponDetectCollision;

    private boolean enemyMovement;

    private int speed;

    private Dpad dpad;

    private boolean attacking;

    /**
     * Constructor for the Enemy class
     * @param context
     * @param xCoordinate
     * @param yCoordinate
     * @param dpad
     */
    public Enemy(Context context, int xCoordinate, int yCoordinate, Dpad dpad){

        x = xCoordinate;
        y = yCoordinate;

        currentX = xCoordinate;
        currentY = yCoordinate;

        offsetMinX = 3030;
        offsetMinY = 2500;
        offsetMaxX = 2000;
        offsetMaxY = 790;

        healthX = x - 15;
        healthY = y - 40;

        borderX = x - 15;
        borderY = y - 40;

        enemyWeaponX = x + 100;
        enemyWeaponY = y + 125;

        bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.enemy);
        healthBar = BitmapFactory.decodeResource(context.getResources(),R.drawable.health);
        healthBarBorder = BitmapFactory.decodeResource(context.getResources(),R.drawable.healthbarborder);
        enemyWeaponBitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.mace);

        detectCollision = new Rect(x,y,bitmap.getWidth(),bitmap.getHeight());
        enemyWeaponDetectCollision = new Rect(x,y,bitmap.getWidth(),bitmap.getHeight());

        hp = 100;

        enemyMovement = false;

        speed = -50;

        this.dpad = dpad;

        attacking = false;
    }

    /**
     * update method for updating the state of the enemy character.
     */
    public void update(){

        if (enemyMovement) {
            if (dpad.getRight().contains(eventX,eventY)) {
                goRight();
            }
            if (dpad.getLeft().contains(eventX,eventY)) {
                goLeft();
            }
            if (dpad.getUp().contains(eventX,eventY)) {
                goUp();
            }
            if (dpad.getDown().contains(eventX,eventY)) {
                goDown();
            }
        }
        //preventative measures so that enemy doesn't move when character tries to move offscreen
        if(y > (currentY + offsetMinY)){
            y = (currentY + offsetMinY);
            healthY = y - 40;
            borderY = y - 40;
            enemyWeaponY = y + 125;
        }
        if(y < (currentY -offsetMaxY)){
            y = (currentY -offsetMaxY);
            healthY = y - 40;
            borderY = y - 40;
            enemyWeaponY = y + 125;

        }
        if(x > (currentX + offsetMinX)){
            x = (currentX + offsetMinX);
            healthX = x - 15;
            borderX = x - 15;
            enemyWeaponX = x + 100;

        }
        if(x < (currentX -offsetMaxX)){
            x = (currentX -offsetMaxX);
            healthX = x - 15;
            borderX = x - 15;
            enemyWeaponX = x + 100;

        }

        attack();

        detectCollision.left = x;
        detectCollision.top = y;
        detectCollision.right = x + bitmap.getWidth();
        detectCollision.bottom = y + bitmap.getHeight();

        enemyWeaponDetectCollision.left = enemyWeaponX;
        enemyWeaponDetectCollision.top = enemyWeaponY;
        enemyWeaponDetectCollision.right = enemyWeaponX + enemyWeaponBitmap.getWidth();
        enemyWeaponDetectCollision.bottom = enemyWeaponY + enemyWeaponBitmap.getHeight();
    }

    /**
     * method for moving the enemy character, his health bar, and his weapon to the left
     */
    private void goLeft(){
        x -= speed;
        healthX -= speed;
        borderX -= speed;
        enemyWeaponX -= speed;
    }

    /**
     * method for moving the enemy character, his health bar, and his weapon to the right
     */
    private void goRight(){
        x += speed;
        healthX += speed;
        borderX += speed;
        enemyWeaponX += speed;

    }

    /**
     * method for moving the enemy character up, along with his health bar and his weapon
     */
    private void goUp(){
        y -= speed;
        healthY -= speed;
        borderY -= speed;
        enemyWeaponY -= speed;
    }

    /**
     * method for moving the enemy character down, along with his health and his weapon
     */
    private void goDown(){
        y += speed;
        healthY += speed;
        borderY += speed;
        enemyWeaponY += speed;

    }

    /**
     * method for determining if the enemy is attacking
     */
    public void isAttacking(){attacking = true;}

    /**
     * method for stopping the enemy from attacking
     */
    public void stopAttacking(){attacking = false;}

    /**
     * method for the attack animation for the enemy weapon
     */
    public void attack(){

        if(attacking){
            enemyWeaponX-=110;
        }
        else{
            enemyWeaponX+=110;
        }

        if(enemyWeaponX < x){
            enemyWeaponX = x;

        }
        if(enemyWeaponX > x + bitmap.getWidth()){
            enemyWeaponX = x + bitmap.getWidth();
        }
        if(enemyWeaponX == x){
            stopAttacking();
        }
    }

    /**
     * method for reducing the health of the enemy character.
     * @param damage
     * damage enemy takes upon invoke
     */
    public void takeDamage(int damage){
        hp -= damage;
        healthBar = Bitmap.createScaledBitmap(healthBar,healthBar.getWidth()-25,healthBar.getHeight(),false);
    }

    //setters
    /**
     * method for updating the value for the event x coordinate.
     * @param inputX
     * x coordinate set on gameview page
     */
    public void setEventX(int inputX){
        eventX = inputX;
    }

    /**
     * method for updating the value for the event y coordinate.
     * @param inputY
     * y coordinate set on gameview page
     */
    public void setEventY(int inputY){
        eventY = inputY;
    }

    /**
     * boolean method for determining and controlling the enemy movement.
     * @param yesOrNo
     * boolean value set on gameview page
     */
    public void setEnemyMovement(boolean yesOrNo){enemyMovement = yesOrNo;}

    /**
     * method for updating the x coordinate for the enemy character.
     * @param newX
     * new x value for the enemy coordinate
     */
    public void setX(int newX){
        x = newX;
    }

    /**
     * method for updating the y coordinate for the enemy character.
     * @param newY
     * new y coordinate for the enemy character
     */
    public void setY(int newY){
        y = newY;
    }

    /**
     * method for updating the x coordinate of the health bar above the enemy character.
     */
    public void setHealthX(){healthX = x -15;}

    /**
     * method for updating the coordinate of the health bar above the enemy character.
     */
    public void setHealthY(){healthY = y - 40;}

    /**
     * method for updating the x coordinate of the black border surrounding the health bar.
     */
    public void setBorderX(){borderX = x -15;}

    /**
     * method for updating the y coordinate of the black border surrounding the health bar.
     */
    public void setBorderY(){borderY = y - 40;}

    /**
     * method for controlling the  x value needed to control the boundaries necessary to prevent the
     * character from moving off the map.
     * @param newCurrent
     */
    public void setCurrentX(int newCurrent){currentX = newCurrent;}

    /**
     * method for controlling the y value needed to control the boundaries necessary to prevent the
     * character from moving off the map.
     * @param newCurrent
     */
    public void setCurrentY(int newCurrent){currentY = newCurrent;}

    /**
     * method for controlling the minimum x value for the offset needed for controlling the boundaries
     * to prevent the character from moving off the screen.
     * @param newMinX
     */
    public void setOffsetMinX(int newMinX){offsetMinX = newMinX;}

    /**
     * method for controlling the minimum y value for the offset needed for controlling the boundaries
     * to prevent the character from moving off the screen.
     * @param newMinY
     */
    public void setOffsetMinY(int newMinY){offsetMinY = newMinY;}

    /**
     * method for controlling the maximum x value for the offset needed for controlling the boundaries
     * to prevent the character from moving off the screen.
     * @param newMaxX
     */
    public void setOffsetMaxX(int newMaxX){offsetMaxX = newMaxX;}

    /**
     * method for controlling the maximum y value for the offset needed for controlling the boundaries
     * to prevent the character from moving off the screen.
     * @param newMaxY
     */
    public void setOffsetMaxY(int newMaxY){offsetMaxY = newMaxY;}

    //getters

    /**
     * method for returning the collision rectangle for the enemy game character.
     * @return
     * collision rectangle
     */
    public Rect getDetectCollision(){
        return detectCollision;
    }

    /**
     * method for returning the bitmap for the enemy character.
     * @return
     * bitmap for the enemy character
     */
    public Bitmap getBitmap(){
        return bitmap;
    }

    /**
     * method for returning the x value for the upper left corner of the character bitmap.
     * @return
     * x coordinate for the upper left corner of the enemy bitmap.
     */
    public int getX(){
        return x;
    }

    /**
     * method for returning the y value for the upper left corner of the character bitmap.
     * @return
     * y coordinate for the upper left corner of the enemy bitmap.
     */
    public int getY(){
        return y;
    }

    /**
     * method for returning the current health of the enemy character.
     * @return
     * health of the enemy character
     */
    public int getHp(){return hp;}

    /**
     *method for returning the bitmap for the health bar above the enemy character
     * @return
     * bitmap for the health bar
     */
    public Bitmap getHealthBar(){return healthBar;}

    /**
     * method for returning the x coordinate for the upper left corner of the health bar bitmap
     * @return
     * x coordinate for the upper left corner of the health bar
     */
    public int getHealthX(){return healthX;}

    /**
     * method for returning the y coordinate for the upper left corner of the health bar bitmap.
     * @return
     * y coordinate for the upper left coordinate of the health bar bitmap.
     */
    public int getHealthY(){return healthY;}

    /**
     * method for returning the border surrounding the health bar bitmap.
     * @return
     * bitmap for the border surrounding the health bar bitmap.
     */
    public Bitmap getHealthBarBorder(){return healthBarBorder;}

    /**
     * method for returning the x coordinate for the upper left corner of the border surrounding
     * the health bar.
     * @return
     * x coordinate for the upper left coordinate for the border.
     */
    public int getBorderX(){return borderX;}

    /**
     * method for returning the y coordinate for the upper left corner of the border surrounding
     * the health bar.
     * @return
     * y coordinate for the upper left coordinate for the border.
     */
    public int getBorderY(){return borderY;}

    /**
     * method for returning the enemy's weapon bitmap
     * @return
     * enemy's weapon bitmap
     */
    public Bitmap getWeaponBitmap(){return enemyWeaponBitmap;}

    /**
     * method for returning the x coordinate for the upper left corner
     * for the enemy's weapon bitmap
     * @return
     * x coordinate for the upper left corner for the enemy's weapon bitmap
     */
    public int getEnemyWeaponX(){return enemyWeaponX;}
    /**
     * method for returning the y coordinate for the upper left corner
     * for the enemy's weapon bitmap
     * @return
     * y coordinate for the upper left corner for the enemy's weapon bitmap
     */
    public int getEnemyWeaponY(){return enemyWeaponY;}

    /**
     * method for returning the collision rectangle for the enemy's weapon
     * @return
     * collision rectangle for the enemy weapon
     */
    public Rect getEnemyWeaponDetectCollision(){return enemyWeaponDetectCollision;}
}
