package game;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.provider.Settings;
import android.util.Log;
import android.view.SurfaceHolder;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import enemy.*;
import rooms.RoomCreate;
import server.ServerService;
import server.ServerUpdates;

/**
 * Created by mlscholl on 10/16/17.
 */

/**
 * The mainthread for the project that runs the gameWindow / canvas
 */
public class MainThread extends Thread {

    public static final int MAX_FPS = 30;
    private double average_FPS;
    private SurfaceHolder surfaceHolder;
    private GameWindow gameWindow;
    private boolean running;
    public static Canvas canvas;

    public void setRunning(boolean running) {

        this.running = running;
    }

    /**
     * Initialization of the mainthread
     * @param surfaceHolder
     * @param gameWindow
     */
    public MainThread(SurfaceHolder surfaceHolder, GameWindow gameWindow) {
        super();

        this.surfaceHolder = surfaceHolder;
        this.gameWindow = gameWindow;
    }

    /**
     * This is the main gameloop that runs the surfaceview and canvas
     */
    @Override
    public void run() {
        long startTime;
        long timeMillis = 1000/MAX_FPS;
        long waitTime;
        int framecount = 0;
        long totalTime = 0;
        long targetTime = 1000/MAX_FPS;
        long pullTime = 0;

        /* MAIN GAME LOOP */
        while (running) {
            startTime = System.nanoTime();
            pullTime = pullTime + System.nanoTime();
            canvas = null;

            try {
                canvas = this.surfaceHolder.lockCanvas();
                if(canvas != null) {
                    synchronized (surfaceHolder) {
                        this.gameWindow.update();
                        this.gameWindow.draw(canvas);
                    }
                }
                else
                {
                    this.setRunning(false);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);

                    } catch (Exception e) {e.printStackTrace();}
                }
            }

            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - timeMillis;
            try {
                if (waitTime > 0) {
                    this.sleep(waitTime);
                }

            } catch (Exception e) {e.printStackTrace();}

            totalTime += System.nanoTime() - startTime;
            framecount++;
            /* Counting the frame rate and printing it out */
            if (framecount == MAX_FPS) {
                average_FPS = 1000 / (((totalTime / framecount) / 1000000));
                framecount = 0;
                totalTime = 0;
                System.out.print(average_FPS);
            }
        }
    }
}
