package server;

/**
 * Created by mlscholl on 12/3/2017.
 */

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;
import game.MainActivity;

/**
 * ServerUpdates is run in the background of our game to call information regarding the turn and health of each player
 */
public class ServerUpdates extends Service {

    private int turn;

    private static String TAG = ServerUpdates.class.getSimpleName();
    private MyThread mythread;
    public boolean isRunning = false;

    /**
     *
     * @param arg0
     * @return
     */
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    /**
     *
     *  Starting the thread
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
        mythread  = new MyThread();
    }

    /**
     *
     * Stopping the thread when the service is destroyed
     */
    @Override
    public synchronized void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        if(!isRunning){
            mythread.interrupt();
            mythread.stop();
        }
    }

    /**
     *
     * @param intent Intent coming into the service with possible information
     * @param startId
     */
    @Override
    public synchronized void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.d(TAG, "onStart");
        if(!isRunning){
            mythread.start();
            isRunning = true;
        }
    }

    /**
     * Calls the whoturn php script
     */
    public void getTurn() {

        String url = "http://proj-309-gp-b-2.cs.iastate.edu/whoturn.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                         MainActivity.whoseTurn = Integer.parseInt(response.trim());

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    /**
     * Calls the heal_calc php script
     */
    public void healthUpdate(){
        String url = "http://proj-309-gp-b-2.cs.iastate.edu/heal_calc.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        final String temp[] = response.trim().split(":");
                        MainActivity.player1.health = Integer.parseInt(temp[0]);
                        MainActivity.player2.health = Integer.parseInt(temp[1]);
                        MainActivity.player3.health = Integer.parseInt(temp[2]);
                        MainActivity.player4.health = Integer.parseInt(temp[3]);
                        MainActivity.enemy1.health = Integer.parseInt(temp[4]);
                        MainActivity.enemy2.health = Integer.parseInt(temp[5]);
                        MainActivity.enemy3.health = Integer.parseInt(temp[6]);
                        MainActivity.enemy4.health = Integer.parseInt(temp[7]);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error){
                    }
                }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    /**
     * Runs the thread for calling the methods that pull information from the server
     */
    class MyThread extends Thread{
        static final long DELAY = 5000;
        @Override
        public void run(){
            while(isRunning){
                Log.d(TAG,"Running");
                try {
                    getTurn();
                    healthUpdate();
                    Thread.sleep(DELAY);
                } catch (InterruptedException e) {
                    isRunning = false;
                    e.printStackTrace();
                }
            }
        }

    }

}