package com.example.alex.mainmenu;

import com.google.gson.Gson;

import static com.android.volley.Request.Method.HEAD;

/**
 * Expresses a gamestate.
 * This allows it to be encoded/decoded into a JSON string more easily
 *
 * Now only holds x , y coordinate but in the future will be able to add any characteristic
 * /!\ IF YOU ADD ANY CHARACTERISTIC CLIENT SIDE NEED TO TELL ME ( JOHAN ) SO I CAN ALSO ADD IT IN SERVER SIDE /!\
 * Created by johan on 08.10.2017.
 */

public final class Gamestate {
    //variables...
    private int x;
    private int y;
    private boolean ready = false;

    /**
     * Constructor. Sets x and y to the parameters.
     * @param x
     * @param y
     */
    public Gamestate(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * Constructs a game state based on the gson string.
     * @param json
     */
    public Gamestate(String json){
        Gson gson = new Gson();
        Gamestate gs = gson.fromJson(json,Gamestate.class);
        this.x = gs.x;
        this.y = gs.y;

    }

    /**
     * Updates the gamestate based on a response (encoded in JSON)
     * @param response
     */
    public void update(String response) {
        Gson gson = new Gson();
        Gamestate gs = gson.fromJson(response,Gamestate.class);
        this.x = gs.x;
        this.y = gs.y;
        ready = true;
    }


    public Gamestate get(){
        int i =0;
        while(!ready|| i<2){

        while(!ready){
            try {
                Thread.sleep(100);
                } catch (InterruptedException e) {
                e.printStackTrace();
                }

            i++;

        };

        }
        return this;
    }
    /**
     *
     * @return current x
     */
    public int getX() {
        return x;
    }

    /**
     *
     * @return current y
     */
    public int getY() {
        return y;
    }


    public final class GamestateBuilder{
        private int x;
        private int y;

        public GamestateBuilder(){
            //empty contructor..
        }



        public GamestateBuilder setX(int x){
            this.x = x;
            return this;
        }

        public GamestateBuilder setY(int y){
            this.y = y;
            return this;
        }

        public Gamestate build(){
            return new Gamestate(x,y);
        }


    }

}
