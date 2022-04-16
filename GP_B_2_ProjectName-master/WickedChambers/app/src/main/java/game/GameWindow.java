package game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.example.mlscholl.wickedchambers.R;

import characters.CharCreate;
import enemy.Boss;
import enemy.Enemy;

/**
 * Created by mlscholl on 10/16/17.
 */

/**
 * GameWindow is the main method that draws our surface so we can interact with it
 * GameWindow contains everything for surfaceview because our main layout activity contains a surfaceview
 * The surface created has a canvas inside of it and then the draw function uses that canvas
 * Also within gameWindow the main thread is created that runs the window and the surfaceview
 */
public class GameWindow extends SurfaceView implements SurfaceHolder.Callback {

    private MainThread thread;

    private static final int NUMBER_OF_VERTICAL_SQUARES = 4;
    private static final int NUMBER_OF_HORIZONTAL_SQUARES = 7;

    private CharCreate player1, player2, player3, player4;
    private Enemy enemy1, enemy2, enemy3, enemy4;
    public int p1Hp = 100, p2Hp = 100, p3Hp = 100, p4Hp = 100;
    private Bitmap scaledbg;
    private int canvasWidth, canvasHeight, squareWidth, squareHeight;

    private int whoseTurn;
    private int playerNum;
    private int count = 0;
    private int start = 0;

    int target;
    int damage;

    private Toast toast = null;

    private int totalPlayers = 4, totalEnemies = 4;

    /**
     * Constructor to pass values into the initialize method
     * @param context the context for the window being created
     */
    public GameWindow(Context context) {
        super(context);
        init(context);
    }

    /**
     * Constructor to pass values into the initialize method
     * @param context the context for the window being created
     * @param attrs attributes for that window
     */
    public GameWindow(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * Constructor to pass values into the initialize method
     * @param context the context for the window being created
     * @param attrs attributes for that window
     * @param defStyle style for that window
     */
    public GameWindow(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    /**
     * Initializing the surfaceview and canvas
     * This also initializes a lot of the characters and objects we use later on
     * @param context the context for the window being created
     */
    private void init(Context context) {
        getHolder().addCallback(this);

        int width = context.getResources().getDisplayMetrics().widthPixels;
        int height = context.getResources().getDisplayMetrics().heightPixels;

        whatAmI(MainActivity.player1, 1, height);
        whatAmI(MainActivity.player2, 2, height);
        whatAmI(MainActivity.player3, 3, height);
        whatAmI(MainActivity.player4, 4, height);

        player1 = MainActivity.player1;
        player2 = MainActivity.player2;
        player3 = MainActivity.player3;
        player4 = MainActivity.player4;

        if (true) {

            whatAmIEnmY(MainActivity.enemy1, 1, height);
            whatAmIEnmY(MainActivity.enemy2, 2, height);
            whatAmIEnmY(MainActivity.enemy3, 3, height);
            whatAmIEnmY(MainActivity.enemy4, 4, height);

            enemy1 = MainActivity.enemy1;
            enemy2 = MainActivity.enemy2;
            enemy3 = MainActivity.enemy3;
            enemy4 = MainActivity.enemy4;
        }
        else {

        }

        MainActivity.player1.loadResources(context);
        MainActivity.player2.loadResources(context);
        MainActivity.player3.loadResources(context);
        MainActivity.player4.loadResources(context);
        Bitmap bg = BitmapFactory.decodeResource(getResources(), R.drawable.dungeon_background);
        scaledbg = Bitmap.createScaledBitmap(bg, width, height, true);

        thread = new MainThread(getHolder(), this);

        whoseTurn = MainActivity.whoseTurn;
        playerNum = MainActivity.player;

        setFocusable(true);
    }

    /**
     * If our surface was changed to a new canvas or surface this would be used
     * @param holder
     * @param format
     * @param width
     * @param height
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    /**
     * Starts the thread for the surfaceview
     * @param holder surface holding the canvas
     */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new MainThread(getHolder(), this);

        thread.setRunning(true);
        thread.start();


    }

    /**
     * Takes the surface and hecks to see if it was destroyed and stops the thread if so
     * @param holder surface holding the canvas
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();

                retry = false;
            }
            catch(Exception e) {e.printStackTrace();}
        }
    }

    /**
     * Looks for any touch events that are acted upon on the screen and from there finds the object you were picking with that touch
     * @param event touch event
     * @return
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        double x = 0;
        double y = 0;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
        }
        if (whoseTurn == playerNum) {
        /* Player 1 on the canvas */
            if ((x > (canvasWidth*.7) && x < ((canvasWidth*.7) + squareWidth)) &&
                    (y > (canvasHeight*.25) && y < ((canvasHeight*.25) + squareWidth))) {
                if (toast != null) toast.cancel();
                toast = Toast.makeText(this.getContext(), player1.getUsername() + " selected", Toast.LENGTH_SHORT);
                start = 1;
            }
        /* Player 2 on the canvas */
            if ((x > (canvasWidth*.85) && x < ((canvasWidth*.85) + squareWidth)) &&
                    (y > (canvasHeight*.35) && y < ((canvasHeight*.35) + squareWidth))) {
                if (toast != null) toast.cancel();
                toast = Toast.makeText(this.getContext(), player2.getUsername() + " selected", Toast.LENGTH_SHORT);
            }
        /* Player 3 on the canvas */
            if ((x > (canvasWidth*.7) && x < ((canvasWidth*.7) + squareWidth)) &&
                    (y > (canvasHeight*.65) && y < ((canvasHeight*.65) + squareWidth))) {
                if (toast != null) toast.cancel();
                toast = Toast.makeText(this.getContext(), player3.getUsername() + " selected", Toast.LENGTH_SHORT);
            }
        /* Player 4 on the canvas */
            if ((x > (canvasWidth*.85) && x < ((canvasWidth*.85) + squareWidth)) &&
                    (y > (canvasHeight*.75) && y < ((canvasHeight*.75) + squareWidth))) {
                if (toast != null) toast.cancel();
                toast = Toast.makeText(this.getContext(), player4.getUsername() + " selected", Toast.LENGTH_SHORT);
            }


        /* Monster 1 on the canvas */
            if ((x > (canvasWidth*.05) && x < ((canvasWidth*.05) + squareWidth)) &&
                    (y > (canvasHeight*.25) && y < ((canvasHeight*.25) + squareWidth))) {
                if (toast != null) toast.cancel();
                toast = Toast.makeText(this.getContext(), enemy1.type(), Toast.LENGTH_SHORT);
                MainActivity.target = 1;
            }
        /* Monster 2 on the canvas */
            if ((x > (canvasWidth*.2) && x < ((canvasWidth*.2) + squareWidth)) &&
                    (y > (canvasHeight*.35) && y < ((canvasHeight*.35) + squareWidth))) {
                if (toast != null) toast.cancel();
                toast = Toast.makeText(this.getContext(), enemy2.type(), Toast.LENGTH_SHORT);
                MainActivity.target = 2;
            }
        /* Monster 3 on the canvas */
            if ((x > (canvasWidth*.05) && x < ((canvasWidth*.05) + squareWidth)) &&
                    (y > (canvasHeight*.65) && y < ((canvasHeight*.65) + squareWidth))) {
                if (toast != null) toast.cancel();
                toast = Toast.makeText(this.getContext(), enemy3.type(), Toast.LENGTH_SHORT);
                MainActivity.target = 3;
            }
        /* Monster 4 on the canvas */
            if ((x > (canvasWidth*.2) && x < ((canvasWidth*.2) + squareWidth)) &&
                    (y > (canvasHeight*.75) && y < ((canvasHeight*.75) + squareWidth))) {
                if (toast != null) toast.cancel();
                toast = Toast.makeText(this.getContext(), enemy4.type(), Toast.LENGTH_SHORT);
                MainActivity.target = 4;
            }
            else {

            }
        }

        else {
            if (toast != null) {
                toast.cancel();
            }
            toast = Toast.makeText(this.getContext(), "Please wait for your turn", Toast.LENGTH_SHORT);
        }
        toast.show();
        return true;
    }

    /**
     * Update is a constantly running method where values call be called and updated
     */
    public void update() {

        /* This is always running and can be taken as the main loop for updating the characters */

        whoseTurn = MainActivity.whoseTurn;
        playerNum = MainActivity.player;

        player1.updateStats();
        player2.updateStats();
        player3.updateStats();
        player4.updateStats();

        enemy1.updateStats(1);
        enemy2.updateStats(2);
        enemy3.updateStats(3);
        enemy4.updateStats(4);

        if (!enemy1.alive && !enemy2.alive && !enemy3.alive && !enemy4.alive) {

            Log.d("GameEnd", "Game has ended");
            player1.inventory.receiveRandomItem();
            player2.inventory.receiveRandomItem();
            player3.inventory.receiveRandomItem();
            player4.inventory.receiveRandomItem();

            player1.charXP++;
            player2.charXP++;
            player3.charXP++;
            player4.charXP++;

            //TODO
            /* Update all four players to database */

            thread.setRunning(false);

        }

        if (!player1.isAlive && !player2.isAlive && !player3.isAlive && !player4.isAlive) {


            thread.setRunning(false);
        }

    }

    /**
     * The draw function takes everything that is being drawn and draws it to the canvas
     * Character and enemy objects are drawn here as well by calling their individual draw functions
     * @param canvas the main canvas at which everything is being drawn
     */
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        canvasWidth = canvas.getWidth();
        canvasHeight = canvas.getHeight();
        squareWidth = canvasWidth / NUMBER_OF_HORIZONTAL_SQUARES;
        squareHeight = canvasHeight / NUMBER_OF_VERTICAL_SQUARES;

        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(scaledbg, 0, 0, null);


        player1.draw(canvas);
        player2.draw(canvas);
        player3.draw(canvas);
        player4.draw(canvas);

        if (true) {
            enemy1.draw(canvas);
            enemy2.draw(canvas);
            enemy3.draw(canvas);
            enemy4.draw(canvas);
        }
        else {

        }
    }

    /**
     * This function takes in the character object and then passes the image for said character to the object
     * @param player the character object
     * @param num the player number of the character object being passed
     * @param height the height taken from the window size that needs to be passed for resizing the image
     */
    public void whatAmI(CharCreate player, int num, int height) {

        if (num == 1) {
            if (player.getType().equals("Warrior")) {
                MainActivity.player1.updateImage(BitmapFactory.decodeResource(getResources(), R.drawable.warrior), height);
            }
            else if (player.getType().equals("Mage")) {
                MainActivity.player1.updateImage(BitmapFactory.decodeResource(getResources(), R.drawable.mage), height);
            }
            else {
                MainActivity.player1.updateImage(BitmapFactory.decodeResource(getResources(), R.drawable.rogue), height);
            }
        }
        else if (num == 2) {
            if (player.getType().equals("Warrior")) {
                MainActivity.player2.updateImage(BitmapFactory.decodeResource(getResources(), R.drawable.warrior), height);
            }
            else if (player.getType().equals("Mage")) {
                MainActivity.player2.updateImage(BitmapFactory.decodeResource(getResources(), R.drawable.mage), height);
            }
            else {
                MainActivity.player2.updateImage(BitmapFactory.decodeResource(getResources(), R.drawable.rogue), height);
            }
        }
        else if (num == 3) {
            if (player.getType().equals("Warrior")) {
                MainActivity.player3.updateImage(BitmapFactory.decodeResource(getResources(), R.drawable.warrior), height);
            }
            else if (player.getType().equals("Mage")) {
                MainActivity.player3.updateImage(BitmapFactory.decodeResource(getResources(), R.drawable.mage), height);
            }
            else {
                MainActivity.player3.updateImage(BitmapFactory.decodeResource(getResources(), R.drawable.rogue), height);
            }
        }

        else if (num == 4) {
            if (player.getType().equals("Warrior")) {
                MainActivity.player4.updateImage(BitmapFactory.decodeResource(getResources(), R.drawable.warrior), height);
            }
            else if (player.getType().equals("Mage")) {
                MainActivity.player4.updateImage(BitmapFactory.decodeResource(getResources(), R.drawable.mage), height);
            }
            else {
                MainActivity.player4.updateImage(BitmapFactory.decodeResource(getResources(), R.drawable.rogue), height);
            }
        }
        else {

        }
    }

    /**
     * This function takes in the enemy object and then passes the image for said enemy to the object
     * @param enemy the enemy object
     * @param num the player number of the enemy object being passed
     * @param height the height taken from the window size that needs to be passed for resizing the image
     */
    public void whatAmIEnmY(Enemy enemy, int num, int height) {


        if (num == 1) {
            if (enemy.enemyType.equals("Skeleton")) {
                MainActivity.enemy1.updateImage(BitmapFactory.decodeResource(getResources(), R.drawable.skeletal_warrior), height);
            }
            else if (enemy.enemyType.equals("Slime")) {
                MainActivity.enemy1.updateImage(BitmapFactory.decodeResource(getResources(), R.drawable.slime), height);
            }
            else if (enemy.enemyType.equals("Goblin")) {
                MainActivity.enemy1.updateImage(BitmapFactory.decodeResource(getResources(), R.drawable.goblin), height);
            }
            else if (enemy.enemyType.equals("Zombie")) {
                MainActivity.enemy1.updateImage(BitmapFactory.decodeResource(getResources(), R.drawable.zombie), height);
            }
            else if (enemy.enemyType.equals("Bandit")) {
                MainActivity.enemy1.updateImage(BitmapFactory.decodeResource(getResources(), R.drawable.bandit), height);
            }
        }
        else if (num == 2) {
            if (enemy.enemyType.equals("Skeleton")) {
                MainActivity.enemy2.updateImage(BitmapFactory.decodeResource(getResources(), R.drawable.skeletal_warrior), height);
            }
            else if (enemy.enemyType.equals("Slime")) {
                MainActivity.enemy2.updateImage(BitmapFactory.decodeResource(getResources(), R.drawable.slime), height);
            }
            else if (enemy.enemyType.equals("Goblin")) {
                MainActivity.enemy2.updateImage(BitmapFactory.decodeResource(getResources(), R.drawable.goblin), height);
            }
            else if (enemy.enemyType.equals("Zombie")) {
                MainActivity.enemy2.updateImage(BitmapFactory.decodeResource(getResources(), R.drawable.zombie), height);
            }
            else if (enemy.enemyType.equals("Bandit")) {
                MainActivity.enemy2.updateImage(BitmapFactory.decodeResource(getResources(), R.drawable.bandit), height);
            }
        }
        else if (num == 3) {
            if (enemy.enemyType.equals("Skeleton")) {
                MainActivity.enemy3.updateImage(BitmapFactory.decodeResource(getResources(), R.drawable.skeletal_warrior), height);
            }
            else if (enemy.enemyType.equals("Slime")) {
                MainActivity.enemy3.updateImage(BitmapFactory.decodeResource(getResources(), R.drawable.slime), height);
            }
            else if (enemy.enemyType.equals("Goblin")) {
                MainActivity.enemy3.updateImage(BitmapFactory.decodeResource(getResources(), R.drawable.goblin), height);
            }
            else if (enemy.enemyType.equals("Zombie")) {
                MainActivity.enemy3.updateImage(BitmapFactory.decodeResource(getResources(), R.drawable.zombie), height);
            }
            else if (enemy.enemyType.equals("Bandit")) {
                MainActivity.enemy3.updateImage(BitmapFactory.decodeResource(getResources(), R.drawable.bandit), height);
            }
        }
        else if (num == 4) {
            if (enemy.enemyType.equals("Skeleton")) {
                MainActivity.enemy4.updateImage(BitmapFactory.decodeResource(getResources(), R.drawable.skeletal_warrior), height);
            }
            else if (enemy.enemyType.equals("Slime")) {
                MainActivity.enemy4.updateImage(BitmapFactory.decodeResource(getResources(), R.drawable.slime), height);
            }
            else if (enemy.enemyType.equals("Goblin")) {
                MainActivity.enemy4.updateImage(BitmapFactory.decodeResource(getResources(), R.drawable.goblin), height);
            }
            else if (enemy.enemyType.equals("Zombie")) {
                MainActivity.enemy4.updateImage(BitmapFactory.decodeResource(getResources(), R.drawable.zombie), height);
            }
            else if (enemy.enemyType.equals("Bandit")) {
                MainActivity.enemy4.updateImage(BitmapFactory.decodeResource(getResources(), R.drawable.bandit), height);
            }
        }
        else {

        }
    }

    /**
     * This function takes in the boss object and then passes the image for said boss to the object
     * @param boss the boss object
     * @param height the height taken from the window size that needs to be passed for resizing the image
     */
    public void whatAmIBoss(Boss boss, int height) {
        //MainActivity.boss1.updateImage(BitmapFactory.decodeResource(getResources(), R.drawable.bandit), height);
    }
}
