package rooms;
//THIS IS NO LONGER NEEDED
import java.util.ArrayList;
import enemy.*;
import characters.*;

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
