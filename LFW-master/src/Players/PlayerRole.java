package Players;

import Mapping.Node;

import java.util.HashMap;

import db.DB;
import db.Game;

/**
 * Interface for the roles that a user can take on.
 * Created by sauerbreiale on 10/4/2017.
 */
public abstract class PlayerRole {
	
    protected Node position;
    public static final String[] colors= {"blue","brown","green","red","yellow"};

    /**
     * Creates a PlayerRole with the given position
     * @param position - starting position for the PlayerRole
     */
    public PlayerRole(Node position)
    {
        this.position = position;
    }

    /**
     * Gets the current position of the PlayerRole
     * @return - the position of the PlayerRole as a Node
     */
    public Node getPosition()
    {
        return position;
    }

    /**
     * Sets the position of the PlayerRole object
     * @param position - the position the PlayerRole is moving to
     * @param Username - username of the PlayerRole
     * @param db - the database connection being used
     * @throws InvalidMovementException
     */
    public abstract void setPosition(Node position, String Username, DB db) throws InvalidMovementException;

    /**
     * An Exception thrown when there is an invalid movement by a PlayerRole
     * @author sauerbreiale
     *
     */
    public class InvalidMovementException extends Exception{
        public InvalidMovementException(String message)
        {
            super(message);
        }
    }

    /**
     * An Exception thrown when there is an invalid inspection by a PlayerRole
     * @author sauerbreiale
     *
     */
    public class InvalidInspectionException extends Exception {
        public InvalidInspectionException(String message)
        {
            super(message);
        }
    }

}
