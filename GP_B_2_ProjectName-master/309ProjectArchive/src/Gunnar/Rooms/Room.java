package proj;

import java.util.ArrayList;
import Enemy.*;

public interface Room {
	
    int numHeroes();
	
    int getXPReward();
	
    boolean isComplete();

    boolean isFailed();
        
    //New version of the room interface
    ArrayList<CharCreate> getCharacters();
    
    ArrayList<Enemy> getEnemies();
    
    ArrayList<Boss> getBoss();
}
