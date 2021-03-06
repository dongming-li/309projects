package com.warscreen.warscreen;

import java.util.ArrayList;

/**
 * Created by Isaac Holtkamp on 9/29/2017.
 */

public class Hex_data {

    private int ID;
    private army hex_army;
    private double slow;
    private int bonus_defense;
    private User user;//player in control of hex
    private double GPT;//gold per turn
    private int MPT;//manpower per turn
    private ArrayList<buildings> build;
    private ArrayList<units> units_building;
    private int swords_made;
    private int archers_made;
    private int horses_made;
    private army empty;

    public Hex_data(int ID, army hex_army, double slow, double GPT, int MPT, ArrayList<buildings> buildings, User user){
        this.ID = ID;
        this.hex_army = hex_army;
        // display army stats in hexes
        this.slow = slow;
        this.GPT = GPT;
        this.MPT = MPT;
        this.user = user;
        this.build = buildings;
        swords_made = 0;
        archers_made = 0;
        horses_made = 0;

    }
    public void remove_Army(){
        this.hex_army=new army(new ArrayList<units>(2),new ArrayList<units>(2),new ArrayList<units>(2),this.ID,0);
    }
    public army select_move(int num_swords, int num_horses, int num_archers){
        army moving = new army(null, null, null, ID, hex_army.get_time_on_hex());
        for(int i = 0; i < num_swords; i++){
            moving.add_sword(hex_army.get_sword_at(0),i);
            moving.remove_swords(0);
        }
        for(int i = 0; i < num_horses; i++){
            moving.add_horses(hex_army.get_horse_at(0),i);
            moving.remove_horse(0);
        }
        for(int i = 0; i < num_archers; i++){
            moving.add_archers(hex_army.get_archer_at(0),i);
            moving.remove_archer(0);
        }
        return moving;
    }

    public void make_building(String type, int cost, int time){
        buildings b;
        if(user.get_gold() >= cost){
            switch(type){
                case "fort":
                    b = new buildings("Fort" +  ID, cost, time, 1, 0);
                    break;
                case "market":
                    b = new buildings("Market" + ID, cost, time);
                    break;
                case "barrack":
                    b = new buildings("Barrack" + ID, cost, time);
                    break;
                default:
                    return;
            }
        build.add(b);
        }
    }

    public ArrayList<buildings> get_buildings(){return build;}

    public void make_unit(String type){
        units u = null;
        switch(type){
            case "sword":
                if(10 < user.get_gold() && user.get_Tot_manpower() >= 1000) {
                    u = new units("sword", ID +"sword" + swords_made, 10, 10, 10, 10, 1000, 10);
                    units_building.add(u);
                    swords_made ++;
                }
                break;
            case "archer":
                if(10 < user.get_gold() && user.get_Tot_manpower() >= 1000) {
                    u = new units("archer", ID + "archer" + archers_made, 10, 10, 10, 10, 1000, 10);
                    units_building.add(u);
                    archers_made++;
                }
                break;
            case "horse":
                if(10 < user.get_gold() && user.get_Tot_manpower() >= 1000) {
                    u = new units("horse", ID + "horse" + horses_made, 10, 10, 10, 10, 1000, 10);
                    units_building.add(u);
                    horses_made++;
                }
                break;
            default:

        }
    }

    public int move_time(army moving_army){
        return (int) (moving_army.get_max_speed() * slow);
    }

    public ArrayList<units> get_units_building(){return units_building;}

    public int get_ID(){return ID;}

    public User get_User(){return user;}

    public void set_User(User user){
        this.user = user;
    }

    public double get_GPT(){return GPT;}

    public void set_GPT(double GPT){this.GPT = GPT;}

    public int get_MPT(){return MPT;}

    public void set_MPT(int GPT){this.MPT = MPT;}


    public double find_bonus_defense(){
        if(hex_army.get_time_on_hex() < 100){
            return ((hex_army.get_time_on_hex()/100) * bonus_defense);
        }
        return bonus_defense;
    }

    public army get_hex_army() {return hex_army;}

    public ArrayList<units> get_swords(){return hex_army.get_swords();}

    public void add_swords(units u){hex_army.add_sword(u, 0);}

    public ArrayList<units> get_archers(){return hex_army.get_archers();}

    public void add_archers(units u){hex_army.add_archers(u, 0);}

    public ArrayList<units> get_horses(){
        return hex_army.get_horses();
    }

    public void add_horses(units u){hex_army.add_horses(u, 0);}

    public void add_army(army a){

        for(units u : a.get_swords()){
            hex_army.add_sword(u, 0);
        }
        for(units u : a.get_horses()){
            hex_army.add_horses(u, 0);
        }
        for(units u : a.get_archers()){
            hex_army.add_archers(u, 0);
        }
       // hex_army.reorder_forces();
    }
}
