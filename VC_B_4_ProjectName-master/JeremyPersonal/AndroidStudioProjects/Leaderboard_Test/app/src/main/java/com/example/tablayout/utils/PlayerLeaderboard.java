package com.example.tablayout.utils;

import com.example.tablayout.content;

/**
 * Created by johan on 08.10.2017.
 */


public class PlayerLeaderboard
{
    private final int numberWins;
    private final int numberLoss;
    private final String username;

    public PlayerLeaderboard(String username, int numberWins, int numberLoss){
        this.numberLoss = numberLoss;
        this.numberWins = numberWins;
        this.username = username;
    }

    public content toContent(){
        content ctn = new content(username,numberWins,numberLoss);
        return ctn;
    }

}
