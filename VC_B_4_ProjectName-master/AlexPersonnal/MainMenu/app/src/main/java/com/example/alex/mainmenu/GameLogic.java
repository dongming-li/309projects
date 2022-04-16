package com.example.alex.mainmenu;

/**
 * Created by Alex on 11/2/2017.
 */


        import java.util.ArrayList;
        import java.util.Map;
        import com.example.alex.mainmenu.Turret;



 /* This class represents the internal game logic seperate of any UI
 * Will contain the map, players, units, turrets , etc
 */
public class GameLogic {

    private GameMode gameMode;

    private Map map;
    private ArrayList<Turret> turrets;
    public static ArrayList<Unit> units;

    public GameLogic(Map map, GameMode gm){
        gameMode = gm;
        this.map = map;
        turrets = new ArrayList<>();
        units = new ArrayList<>();
    }


    //moves the game forward one tick
    public void tick(){

    }

}
