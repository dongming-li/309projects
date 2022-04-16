package com.example.alex.mainmenu;

/**
 * Created by Alex on 11/2/2017.
 */

public class Position {

    private int x,y;

    public Position(int x, int y){
        set(x,y);
    }

    public int getX(){
        return x;
    }

    public int getY() {
        return y;
    }

    public void set(int x,int y){
        this.x=x;
        this.y=y;
    }

    public static float distance(Position a,Position b){
        return (float)Math.sqrt(pow2(a.getX() - b.getX()) + pow2(Math.abs(a.getY() - b.getY())));
    }

    private static int pow2(int b){
        return b*b;
    }
}

