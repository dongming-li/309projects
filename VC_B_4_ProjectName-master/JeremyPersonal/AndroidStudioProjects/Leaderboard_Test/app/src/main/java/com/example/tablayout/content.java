package com.example.tablayout;

/**
 * Created by JeremyC on 10/3/2017.
 */

public final class content
{
    String name, time_taken,  position, rank, most_wins;
    private int numberWins;
    private int numberLoss;

    public content(){};
    public content(String username, int numberWins, int numberLoss)
    {
        this.name = username;
        this.numberWins = numberWins;
        this.numberLoss = numberLoss;
    }


    //Set player name
    public void setPlayerName(String name)
    {
        this.name = name;
    }

    //Get Player name
    public String getPlayerName()
    {
        return name;
    }

    // set Time taken
    public void setTimeTaken(String time)
    {
        this.time_taken = time;
    }

    //get time taken
    public String getTimeTaken()
    {
        return time_taken;
    }

    //set rank of players
    public void setRank(String r)
    {
        this.rank = r;
    }

    //gwt rank of a specific players
    public String getRank()
    {
        return rank;
    }

    //
    public void setPosition(String pos)
    {
        this.position = pos;
    }

    public String getPosition()
    {
        return position;
    }

    public void setWins(String wins)
    {
        this.most_wins = wins;
    }

    public String getnum_of_wins()
    {
        return most_wins;
    }

    public String getRatio() {
        return Double.toString(new Double(numberWins)/numberLoss);
    }



}
