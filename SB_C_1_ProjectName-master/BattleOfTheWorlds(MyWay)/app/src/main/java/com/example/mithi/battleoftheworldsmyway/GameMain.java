package com.example.mithi.battleoftheworldsmyway;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;

/**
 * Created by Mithil Shah and Aaron Rees on 12/4/2017.
 */

/**
 * Game Activity for instantiating the Game View
 */
public class GameMain extends Activity
{
    private GameView gameView;

    /**
     * main method for executing the game view
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Display display = getWindowManager().getDefaultDisplay();

        Point size = new Point();
        display.getSize(size);

        gameView = new GameView(this, size.x, size.y);

        setContentView(gameView);
    }

    /**
     * method for pausing the thread
     */
    @Override
    protected void onPause(){
        super.onPause();
        gameView.pause();
    }

    /**
     * method for resuming the thread after a pause
     */
    @Override
    protected void onResume(){
        super.onResume();
        gameView.resume();
    }
}
