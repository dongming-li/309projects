package server;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import game.VolleyCallback;
import mainmenu.StartMenuActivity;

/**
 * Created by mlscholl on 12/3/2017.
 */

/**
 * Service designed to take all the attribute values for each character held in the game from the server
 */
public class ServerService extends IntentService{

    private String[] attributeP1;
    private String[] attributeP2;
    private String[] attributeP3;
    private String[] attributeP4;

    private String usernameFromIntent;
    private String[] usernameList = new String[3];

    /**
     *
     */
    public ServerService() {
        this(ServerService.class.getName());
    }

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public ServerService(String name) {
        super(name);
        Log.v("Service", "In the service");

        usernameFromIntent = StartMenuActivity.mUsername;
        usernameList = StartMenuActivity.otherUsers.split(":");

    }

    /**
     *
     * @param intent an intent containing information from the file where the service is started
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        showToast("Game is loading.");
        try {
            Intent test = new Intent("Test");
            Bundle bundle = new Bundle();

            getAttributes(new VolleyCallback() {
                @Override
                public void onSuccess(String result) {
                    final String temp = result.trim();
                    Log.d("Temp", temp);
                    attributeP1 = temp.split(":");

                }
            }, usernameFromIntent, "game_one");

            Thread.sleep(1000);

            getAttributes(new VolleyCallback() {
                @Override
                public void onSuccess(String result) {
                    final String temp = result.trim();
                    Log.d("Temp", temp);
                    attributeP2 = temp.split(":");

                }
            }, usernameList[0], "game_one");

            Thread.sleep(1000);

            getAttributes(new VolleyCallback() {
                @Override
                public void onSuccess(String result) {
                    final String temp = result.trim();
                    Log.d("Temp", temp);
                    attributeP3 = temp.split(":");

                }
            }, usernameList[1], "game_one");

            Thread.sleep(1000);

            getAttributes(new VolleyCallback() {
                @Override
                public void onSuccess(String result) {
                    final String temp = result.trim();
                    Log.d("Temp", temp);
                    attributeP4 = temp.split(":");

                }
            }, usernameList[2], "game_one");

            Thread.sleep(2000);

            test.setAction("NOW");
            Log.d("ATTRIBUTE", attributeP2[0]);
            test.putExtra("attribute1", attributeP1);
            test.putExtra("attribute2", attributeP2);
            test.putExtra("attribute3", attributeP3);
            test.putExtra("attribute4", attributeP4);

            test.putExtras(bundle);
            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(test);

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        showToast("Game is ready to start please push start game again");
    }

    /**
     *
     * @param msg takes in a message that will be toasted in the main activity
     */
    protected void showToast(final String msg){
        //gets the main thread
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                // run this code in the main thread
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     *
     * @param callback
     * @param username
     * @param game_table
     */
    public void getAttributes(final VolleyCallback callback, String username, String game_table){

        String url = "http://proj-309-gp-b-2.cs.iastate.edu/player_attributes.php?username="+username+"&game_table="+game_table;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        callback.onSuccess(response);
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
}