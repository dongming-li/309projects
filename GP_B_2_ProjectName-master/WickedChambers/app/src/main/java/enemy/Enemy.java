package enemy;

import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author aaronhudson
 */
public class Enemy implements GenericEnemy {
    
    public int health;
    public int difficulty;
    public boolean alive;
    public int expReward; 
    public String enemyType;
    public String[] typeList = {"Skeleton", "Slime", "Goblin", "Zombie", "Bandit"};

    private static final int NUMBER_OF_VERTICAL_SQUARES = 4;
    private static final int NUMBER_OF_HORIZONTAL_SQUARES = 7;

    private Rect m1Hp, m2Hp, m3Hp, m4Hp;
    private int healthh = 100;
    private Bitmap enemy;
    private int monster = 0;
    private int canvasHeight, canvasWidth;
    private int healthScale = 0;
    private int test1 = 1, test2 = 1, test3 = 1, test4 = 1;


    /**
     * Creates a new enemy object
     * @param type enemy type
     * @param image enemy image
     * @param monster enemy index
     * @param scale scale of image
     * @param width width of image
     * @param height height of image
     * @throws IllegalArgumentException
     */
    public Enemy(String type, Bitmap image, int monster, float scale, int width, int height) throws IllegalArgumentException {
        boolean legal = false;
        for (String i : typeList) {
            if (type.equals(i)) {
                legal = true;
            }
        }
        if (!legal) throw new IllegalArgumentException("Enemy type not valid.");
        alive = true;
        this.health = 100;
        this.difficulty = 1;
        this.enemyType = type;

        enemy = Bitmap.createScaledBitmap(image, (int) (height * .25), (int) (height * .25), true);
        this.monster = monster;
        m1Hp = new Rect();
        m2Hp = new Rect();
        m3Hp = new Rect();
        m4Hp = new Rect();
    }

    /**
     * Updates the image with the given params.
     * @param image
     * @param height
     */
    public void updateImage(Bitmap image, int height) {
        enemy = Bitmap.createScaledBitmap(image, (int)(height*.25), (int)(height*.25), true);
    }

    /**
     * Creates a default enemy object based on the type string.
     * @param type
     * @throws IllegalArgumentException
     */
    public Enemy(String type) throws IllegalArgumentException {
        this.monster = monster;
        boolean legal = false;
        for (String i : typeList) {
            if (type.equals(i)) {
                legal = true;
            }
        }
        if (!legal) throw new IllegalArgumentException("Enemy type not valid.");
        alive = true;
        this.health = 100;
        this.difficulty = 1;
        this.enemyType = type;
    }

    /**
     * Creates an enemy object of the type parameter in the index of monster.
     * @param type
     * @param monster
     * @throws IllegalArgumentException
     */
    public Enemy(String type, int monster) throws IllegalArgumentException {
        this.monster = monster;
        boolean legal = false;
        for (String i : typeList) {
            if (type.equals(i)) {
                legal = true;
            }
        }
        if (!legal) throw new IllegalArgumentException("Enemy type not valid.");
        alive = true;
        this.health = 100;
        this.difficulty = 1;
        this.enemyType = type;

        m1Hp = new Rect();
        m2Hp = new Rect();
        m3Hp = new Rect();
        m4Hp = new Rect();
    }

    /**
     * Draws the monster to the canvas
     * @param canvas
     */
    @Override
    public void draw(Canvas canvas) {

        Paint redPaint = new Paint();
        redPaint.setStrokeWidth(0);
        redPaint.setColor(Color.RED);
        redPaint.setMaskFilter(new BlurMaskFilter(5, BlurMaskFilter.Blur.INNER));

        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();

        int squareWidth = canvasWidth / NUMBER_OF_HORIZONTAL_SQUARES;
        int squareHeight = canvasHeight / NUMBER_OF_VERTICAL_SQUARES;


        healthScale = squareWidth;

        Rect p1Rect = new Rect();
        Rect p2Rect = new Rect();
        Rect p3Rect = new Rect();
        Rect p4Rect = new Rect();

        if (monster == 1) {
            p1Rect.set(0, 0, squareWidth, squareHeight);
            p1Rect.offsetTo((int)(canvasWidth*.05), (int)(canvasHeight*.25));
            canvas.drawBitmap(enemy, null, p1Rect, null);
            if (test1 == 1) {
                m1Hp.set((int)(canvasWidth*.05), (int)(canvasHeight*.2), (int)(canvasWidth*.05 + squareWidth), (int)(canvasHeight*.25));
                test1 = 0;
            }
            canvas.drawRect(m1Hp, redPaint);

        }
        else if (monster == 2) {
            p2Rect.set(0, 0, squareWidth, squareHeight);
            p2Rect.offsetTo((int)(canvasWidth*.2), (int)(canvasHeight*.35));
            canvas.drawBitmap(enemy, null, p2Rect, null);
            if (test2 == 1) {
                m2Hp.set((int)(canvasWidth*.2), (int)(canvasHeight*.3), (int)(canvasWidth*.2 + squareWidth), (int)(canvasHeight*.35));
                test2 = 0;
            }
            canvas.drawRect(m2Hp, redPaint);
        }
        else if (monster == 3) {
            p3Rect.set(0, 0, squareWidth, squareHeight);
            p3Rect.offsetTo((int)(canvasWidth*.05), (int)(canvasHeight*.65));
            canvas.drawBitmap(enemy, null, p3Rect, null);
            if (test3 == 1) {
                m3Hp.set((int)(canvasWidth*.05), (int)(canvasHeight*.6), (int)(canvasWidth*.05 + squareWidth), (int)(canvasHeight*.65));
                test3 = 0;
            }
            canvas.drawRect(m3Hp, redPaint);
        }
        else if (monster == 4) {
            p4Rect.set(0, 0, squareWidth, squareHeight);
            p4Rect.offsetTo((int)(canvasWidth*.2), (int)(canvasHeight*.75));
            canvas.drawBitmap(enemy, null, p4Rect, null);
            if (test4 == 1) {
                m4Hp.set((int)(canvasWidth*.2), (int)(canvasHeight*.7), (int)(canvasWidth*.2 + squareWidth), (int)(canvasHeight*.75));
                test4 = 0;
            }
            canvas.drawRect(m4Hp, redPaint);
        }
        else {

        }


    }

    @Override
    public void update() {

    }

    /**
     * Updates the stats of the given monster
     * @param monster
     */
    public void updateStats(int monster) {


        //Log.d("UpdateStats", "Made it to update stats!!!!!!!");

        double hpPixels = ((double)this.health / 100) * healthScale;

        if (monster == 1) {
            m1Hp.set((int)(canvasWidth*.05), (int)(canvasHeight*.2), (int)(canvasWidth*.05 + hpPixels), (int)(canvasHeight*.25));
        }
        else if (monster == 2) {
            m2Hp.set((int)(canvasWidth*.2), (int)(canvasHeight*.3), (int)(canvasWidth*.2 + hpPixels), (int)(canvasHeight*.35));
        }
        else if (monster == 3) {
            m3Hp.set((int)(canvasWidth*.05), (int)(canvasHeight*.6), (int)(canvasWidth*.05 + hpPixels), (int)(canvasHeight*.65));
        }
        else if (monster == 4) {
            m4Hp.set((int)(canvasWidth*.2), (int)(canvasHeight*.7), (int)(canvasWidth*.2 + hpPixels), (int)(canvasHeight*.75));
        }
        else {

        }

    }

    @Override
    public String type() {
        return this.enemyType;
    }

    @Override
    public int getHealth() {
        return this.health;
    }

    /**
     * Calculates the health of the enemy object based on the damage taken. Also updates the alive status.
     * @param damage the amount of damage the enemy is taking
     */
    @Override
    public void takeDamage(int damage) {
        if (damage > this.health) {
            damage = this.health;
            alive = false;
        }
        this.health -= damage;
        System.out.println(type() + " takes " + damage + " damage!");
    }

    @Override
    public int getDifficultyLevel() {
        return this.difficulty;
    }

    @Override
    public void setDifficultyLevel() {
    }

    /**
     * Causes the enemy object to make an attack based on their Type.
     * @return damage the enemy does
     */
    @Override
    public int attack() {
        Random r = new Random();

        int damage = 0;
        switch (type()){
            case "Skeleton":
                System.out.println(type() + " attacks!");
                damage = r.nextInt(7) + 3;
                return damage;
            case "Slime":
                System.out.println(type() + " attacks!");
                damage = r.nextInt(5) + 3;
                return damage;
            case "Goblin":
                System.out.println(type() + " attacks!");
                damage = r.nextInt(4) + 1;
                return damage;
            case "Zombie":
                System.out.println(type() + " attacks!");
                damage = r.nextInt(8) + 6;
                return damage;
            case "Bandit":
                System.out.println(type() + " attacks!");
                damage = r.nextInt(20) + 15;
                return damage;
            default:
                break;
        }
        return damage;
    }

    /**
     * Checks the alive status of the enemy.
     */
    @Override
    public void checkAlive() {
        if (this.health == 0){
            this.alive = false;
            System.out.println("The " + type() + " has been defeated!");
            System.out.println("You gain " + this.expReward + " experience.");
        }
    }

    @Override
    public void setExpReward() {
        this.expReward = 10*getDifficultyLevel();
    }
    
}
