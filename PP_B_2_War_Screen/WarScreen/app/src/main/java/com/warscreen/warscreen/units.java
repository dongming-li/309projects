package com.warscreen.warscreen;


/**
 * Created by Isaac Holtkamp on 9/28/2017.
 */

public class units {
    private String name;
    //String type;
    private int base_attack;
    private int base_defense;
    private int speed;
    private int cost;
    private int manpower;
    private int losses;
    private int kills;
    private int build_time;
    private String type;

    public units(String type, String name, int base_attack, int base_defense, int speed, int cost, int manpower, int build_time){
        this.name = name;
        this.manpower = manpower;
        //this.type = type;
        this. base_attack = base_attack;
        this.base_defense = base_defense;
        this.speed = speed;
        this.cost = cost;
        this.build_time = build_time;
        this.type = type;
    }

    public String get_type(){return type;}

    public int get_build_time(){return build_time;}

    public void set_build_time(int build_time){this.build_time = build_time;}

    public int get_attack(){
        return base_attack;
    }

    public void set_attack(int attack) {base_attack = attack;};

    public int get_defense(){
        return base_defense;
    }

    public void setBase_defense(int defense){base_defense = defense;}

    public int get_speed(){
        return speed;
    }

    public void set_speed(int speed){this.speed = speed;}

    public int get_cost(){
        return cost;
    }

    public void set_cost(int cost){this.cost = cost;}

    public String get_name(){return name;}

    public void set_name(String name){this.name = name;}

    public int get_manpower(){return manpower;}

    public void set_manpower(int manpower){this.manpower = manpower;}

    public void set_losses(int losses){this.losses = losses;}

    public int get_losses(){return losses;}

    public void set_kills(int kills){this.kills = kills;}

    public int get_kills(){return kills;}

    //public String get_type(){return type;}

    //public void set_type(String type){this.type = type;}
}
