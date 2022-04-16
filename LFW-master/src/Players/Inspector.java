package Players;

import Mapping.Node;

import java.awt.*;
import java.util.HashMap;

import db.DB;

/**
 * This class will be used to store attributes about the users playing as inspectors in the game.
 * Created by sauerbreiale on 10/2/2017.
 */
public class Inspector extends PlayerRole{

    /**
     * Creates a new Jack at the position given
     * @param position - the starting position of this Jack
     */
    public Inspector(Node position)
    {
        super(position);
    }
    
    @Override
    public void setPosition(Node position, String Username, DB db) throws InvalidMovementException {
        if(getMovableNodes().containsKey(position.getName()))
        {
        	db.updatePosition(Username, position.getName());
        }
        else if(Node.USER_JACK.equals(position.getUser()))
        {
            throw new InvalidMovementException("This is not the right type of node you can move to.");
        }
        else
        {
            throw new InvalidMovementException("This movement is invalid.");
        }
    }

    /**
     * Finds the movable nodes for the inspector player based on its current position
     * @return a hashmap of the possible nodes the inspector can move to
     */
    public HashMap<String, Node> getMovableNodes()
    {
        return position.getBlackNeighbors();
    }

    /**
     * Finds the inspectable nodes for the inspector player based on its current position
     * @return a hashmap of the possible nodes the inspector can move to
     */
    public HashMap<String, Node> getInspectableNodes()
    {
        //for inspectors, isAlleywayAccessActivated and isCarriageActive should always be false.
        return position.getWhiteNeighbors(false, false);
    }

    /**
     * Inspects a node to see if it is occupied by Jack
     * @param inspectedNode - node the inspector wants to inspect
     * @return - whether Jack was at that node
     * @throws InvalidInspectionException
     */
    public boolean inspectNode(Node inspectedNode) throws InvalidInspectionException {
        if(getInspectableNodes().containsKey(inspectedNode.getName()))
        {
        	//If the node is inspectable, return whether Jack is there
            return inspectedNode.isOccupied();
        }
        else
        {
            throw new InvalidInspectionException("You cannot inspect this node.");
        }
    }
}
