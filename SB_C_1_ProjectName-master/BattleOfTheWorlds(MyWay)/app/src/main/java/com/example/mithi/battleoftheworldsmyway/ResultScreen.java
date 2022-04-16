package com.example.mithi.battleoftheworldsmyway;

/**
 * Created by Aaron Rees.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

/**
 * Class for the result screen in the game
 */
public class ResultScreen {

    private Rect resultScreenBoundaries;

    private Bitmap bitmap;

    private int resultX;
    private int resultY;

    private int eventX;
    private int eventY;

    private boolean displayResultScreen;

    /**
     * constructor for the result screen
     * @param context
     */
    public ResultScreen(Context context){
        bitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.results);

        resultX = 300;
        resultY = 200;

        resultScreenBoundaries = new Rect(resultX,resultY,bitmap.getWidth(),bitmap.getHeight());

        resultScreenBoundaries.left = resultX;
        resultScreenBoundaries.top = resultY;
        resultScreenBoundaries.right = resultX + bitmap.getWidth();
        resultScreenBoundaries.bottom = resultY + bitmap.getHeight();

        displayResultScreen = true;
    }

    //setters

    /**
     * method for determining when to display the result screen
     * @param yesNo
     * boolean for determining result screen display
     */
    public void setDisplayResultScreen(boolean yesNo){displayResultScreen = yesNo;}

    //getters

    /**
     * method for returning the x coordinate for the upper left corner of the result screen
     * @return
     * x coordinate for the upper left corner of the result screen
     */
    public int getResultX(){return resultX;}

    /**
     * method for returning the y coordinate for the upper left corner of the result screen
     * @return
     * y coordinate for the upper left corner of the result screen
     */
    public int getResultY(){return resultY;}

    /**
     * method for checking whether the result screen is displayed or not
     * @return
     * boolean for display of result screen
     */
    public boolean getDisplayResultScreen(){return displayResultScreen;}

    /**
     * method for returning the bitmap for the result screen
     * @return
     * bitmap for the result screen
     */
    public Bitmap getBitmap(){return bitmap;}
}
