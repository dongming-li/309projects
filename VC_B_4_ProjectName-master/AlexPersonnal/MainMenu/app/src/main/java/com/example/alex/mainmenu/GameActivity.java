package com.example.alex.mainmenu;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.alex.mainmenu.VolleySingleton.SERVER_ADDRESS;

/**
 * Created by Alex on 10/7/2017.
 */

public class GameActivity extends Activity {

    private static final String ERR_MESSAGE = "ERR" ;
    private Context context;

    public GameActivity(Context context){
        this.context = context;

    }

       @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

        }

//TODO lock screen in horizonatl position
    public void updatePosition(int x, int y,int playerID,int adversaryID) {
        Gamestate gamestate = new Gamestate(x,y);
//        Gson  gson = new Gson();
//        JSONObject gsString = null;
//        try {
//            gsString = new JSONObject(gson.toJson(gamestate,Gamestate.class));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        String url = SERVER_ADDRESS+"/game/gamestate_update.php?"+"playerID="+playerID+"&"+ "adversaryID="+adversaryID;
//        Log.d("gamestate", gsString.toString());
//        Log.d("url", url);
//
//        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url, gsString,new Response.Listener<JSONObject>() {
//
//
//                    @Override
//                    public void onResponse(JSONObject response) {
//
//                        Gson gson = new Gson();
//                        StatusResponse status = gson.fromJson(response.toString(),StatusResponse.class);
//                        if(status.isValid()) {
//                            Log.d("CLIENT_SERVER", "Position updated on the server");
//                        }else{
//                            Log.d("CLIENT_SERVER", "Error on updatig position. ");
//                        }
//
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(context, "Error Volley :"+error.getMessage(),
//                        Toast.LENGTH_SHORT).show();
//            }
//
//        });
//
//        //now we send
//        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);

    }

//      public Gamestate pollPosition(int playerID, int adversaryID){
//        String url = SERVER_ADDRESS+"/game/game_polling.php?"+"playerID="+playerID+"&"+"adversaryID="+adversaryID;
//        final Gamestate gs = new Gamestate(0,0);
//        JsonObjectRequest stringRequest = new JsonObjectRequest(Request.Method.POST, url,null, new Response.Listener<JSONObject>() {
//
//
//            @Override
//            public void onResponse(JSONObject response) {
//                if(!response.equals(ERR_MESSAGE)){
//                    String msg = response.toString();
//                    gs.update(msg);
//                }else{
//                    Toast.makeText(context,"Error",Toast.LENGTH_SHORT).show();
//                }
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(context, "Error Volley :"+error.getMessage(),
//                        Toast.LENGTH_SHORT).show();
//            }
//
//        });
//        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
//
//        return gs.get();
//
//    }



}
