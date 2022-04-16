package com.example.tablelist;

/**
 * Created by JeremyC on 10/1/2017.
 */

public class content
{
    String id, name, time_taken,  position;
    int  rank, most_wins;

    public void setId(String id)
    {
        this.id = id;
    }

    public String getId()
    {
        return id;
    }

    public void setPlayerName(String name)
    {
        this.name = name;
    }

    public String getPlayerName()
    {
        return name;
    }

    public void setTimeTaken(String time)
    {
        this.time_taken = time;
    }

    public String getTimeTaken()
    {
        return time_taken;
    }

    public void setRank(int r)
    {
        this.rank = r;
    }

    //
    public int getRank()
    {
        return rank;
    }

    public void setPostion(String pos)
    {
        this.position = pos;
    }

    public String getPosition()
    {
        return position;
    }

    public void setWins(int wins)
    {
        this.most_wins = wins;
    }

    public int getnum_of_wins()
    {
        return most_wins;
    }



}
