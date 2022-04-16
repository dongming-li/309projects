package proj;

import Enemy.*;
import java.util.ArrayList;

public class RoomCreate implements Room {
    int numberofheroes = 0;
    int roomLevel = 0;
    int xpReward = 0;
    boolean isComplete = false;
    boolean isFailed = false;

    public RoomCreate(int heroesnum, int lvl, int xprew){
            numberofheroes = heroesnum;
            roomLevel = lvl;
            xpReward = xprew;
            isComplete = false;
            isFailed = false;
    }

    @Override
    public int numHeroes() {
            return numberofheroes;
    }

    @Override
    public int getXPReward() {
            return xpReward;
    }
    @Override
    public boolean isComplete() {
            return isComplete;
    }
    @Override
    public boolean isFailed() {
            return isFailed;
    }

    @Override
    public ArrayList<CharCreate> getCharacters() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<Enemy> getEnemies() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<Boss> getBoss() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
