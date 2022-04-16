package Players;

import Mapping.Node;

import java.util.HashMap;

import db.DB;
import db.Game;

/**
 * This class will be used to store attributes about the user playing as Jack the Ripper in the game.
 * Created by sauerbreiale on 10/2/2017.
 */
public class JackTheRipper extends PlayerRole{
    private boolean isAlleywayAccessActivated;
    private boolean isCarriageActivated;

    /**
     * Creates a new Jack at the position given
     * @param position - the starting position of this Jack
     */
    public JackTheRipper(Node position)
    {
        super(position);
        isAlleywayAccessActivated = false;
        isCarriageActivated = false;
    }

    @Override
    public void setPosition(Node position, String Username, DB db) throws InvalidMovementException {
        if(getMovableNodes(true, true).containsKey(position.getName()))
        {
        	//sets the position in the database of the Jack player
            db.updatePosition(Username, position.getName());
        }
        else if(Node.USER_INSPECTOR.equals(position.getUser()))
        {
            throw new InvalidMovementException("This is not the right type of node you can move to.");
        }
        else
        {
            throw new InvalidMovementException("This movement is invalid.");
        }
    }

    /**
     * Finds the movable nodes for the jack player based on its current position and whether any special movement tokens are activated
     * @param isAlleywayActivated - whether the StreetLamp token is activated
     * @param isCarriageActivated - whether the Carriage token is activated
     * @return a hashmap of the possible nodes Jack can move to
     */
    public HashMap<String, Node> getMovableNodes(boolean isAlleywayActivated, boolean isCarriageActivated)
    {
        return position.getWhiteNeighbors(isAlleywayActivated, isCarriageActivated);
    }

}
