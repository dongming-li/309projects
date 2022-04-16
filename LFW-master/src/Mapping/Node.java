package Mapping;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * This class is used to hold data about nodes for the map of the game board.
 * Created by sauerbreiale on 9/11/2017.
 */
public class Node {

    //holds the string for if the user is jack
    public static final String USER_JACK = "jack";
    //holds the string for if the user is an inspector
    public static final String USER_INSPECTOR = "inspector";
    //stores the name of the node
    private String name;
    //stores a hashmap of the neighbors
    private HashMap<String, Node> neighbors;
    //stores a hashmap of the alleyway accesses
    private HashMap<String, Node> alleywayAccess;
    //stores the color of the node
    private char color;
    //stores the location of the node
    private Location location;
    //stores whether the node is occupied
    private boolean isOccupied = false;

    /**
     * Constructor for a node for the game board
     * @param name - the name of the node to be created
     * @param color - the color of the node to be created
     * @param location - the pixel location of where the node is
     */
    public Node(String name, char color, Location location) {
        setName(name);
        setColor(color);
        setLocation(location);
    }

    /**
     * Constructor for a node for the game board
     * @param name - the name of the node to be created
     * @param color - the color of the node to be created
     * @param xCoordinate - the x coordinate of the node to be created
     * @param yCoordinate - the y coordinate of the node to be created
     */
    public Node(String name, char color, double xCoordinate, double yCoordinate) {
        setName(name);
        setColor(color);
        setLocation(xCoordinate, yCoordinate);
    }

    /**
     * Gets the name of the node
     * @return the name of the node
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the node
     * @param name - new name of the node
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the color of the node
     * @return the color of the node as a char
     */
    public char getColor() {
        return color;
    }

    /**
     * Sets the color of the node
     * @param color - new color of the node
     */
    public void setColor(char color) {
        this.color = color;
    }

    /**
     * Gets the neighbors of this node
     * @return a hashmap of the neighbors of the node
     */
    public HashMap<String, Node> getNeighbors() {
        return neighbors;
    }

    /**
     * Sets the neighbors of the node to the hashmap given
     * @param neighbors - the new neighbors of the node
     */
    public void setNeighbors(HashMap<String, Node> neighbors) {
        this.neighbors = neighbors;
    }

    /**
     * Adds a neighbor to the neighbors hashmap
     * @param n - the node to be added as a neighbor
     */
    public void addNeighbor(Node n) {
        neighbors.put(n.getName(), n);
    }

    /**
     * Checks to see if the node is a neighbor of this node
     * @param node - the node name to be checked as a neighbor
     * @return boolean of whether the node is a neighbor of this one
     */
    public boolean isNeighbor(String node) {
        return neighbors.containsKey(node);
    }

    /**
     * Tells which user/s can use this node for movement
     * @return the name of the user that can use this node
     */
    public String getUser() {
        String user = null;
        if (color == 'b' || color == 'y') {
            user = USER_INSPECTOR;
        }
        else if(color == 'w' || color == 'r') {
           user = USER_JACK;
        }
        return user;
    }

    /**
     * Gets the location of the node
     * @return the location of the node
     */
    public Location getLocation() {
        return location;
    }

    public double getXCoordinate()
    {
        return location.getXCoordinate();
    }

    public double getYCoordinate()
    {
        return location.getYCoordinate();
    }

    /**
     * Sets the location of the node
     * @param location - the new location of the node
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Sets the location of the node
     * @param xCoordinate - the new x coordinate of the node
     * @param yCoordinate - the new y coordinate of the node
     */
    public void setLocation(double xCoordinate, double yCoordinate) {
        this.location = new Location(xCoordinate, yCoordinate);
    }

    /**
     * Gets the nodes that can be accessed through an alleyway
     * @return a hashmap of nodes that Jack can access through alleyways
     */
    public HashMap<String, Node> getAlleywayAccess() {
        return alleywayAccess;
    }

    /**
     * Adds a node that can be access through alleyways
     * @param n - the node to be added as alleyway acess
     */
    public void addAlleywayAccess(Node n)
    {
        alleywayAccess.put(n.getName(), n);
    }

    /**
     * Sets the nodes that can be accessed through alleyways
     * @param alleywayAccess - hashmap of the nodes that can be accessed through alleyways
     */
    public void setAlleywayAccess(HashMap<String, Node> alleywayAccess){this.alleywayAccess = alleywayAccess;}

    /**
     * Determines if a node is accessible through an alleyway
     * @param node - the node that is in question
     * @return whether it can be accessed through an alleyway
     */
    public boolean hasAlleywayAccess(String node)
    {
        return alleywayAccess.containsKey(node);
    }

    /**
     * Gets the white neighbors of the current node.
     * For Jack, this means any node he can move to in this turn, even through black nodes.
     * For inspectors, this means the nodes that they can inspect or, in other words, directly next to them.
     * @param isAlleywayAccessActivated whether the StreetLamp token is activated
     * @param isCarriageActive whether the Carriage token is activated
     * @return hashmap of the white neighbors for the given node
     */
    public HashMap<String, Node> getWhiteNeighbors(boolean isAlleywayAccessActivated, boolean isCarriageActive)
    {
        HashMap<String, Node> whiteNeighbors = new HashMap<>();
        //if the user of the node is jack
        if (USER_JACK.equals(getUser()))
        {
            Set<String> visited = new HashSet<>();
            //go into the recursive method to find the nodes jack can move to
            findAccessibleWhiteNeighbors(this, whiteNeighbors, visited,isCarriageActive);
            //if alleyway access is activated, add those to the nodes that jack can move to
            if(isAlleywayAccessActivated)
            {
                whiteNeighbors.putAll(alleywayAccess);
            }

        }
        //if the user of the nod is an inspector
        else if (USER_INSPECTOR.equals(getUser()))
        {
            Set<String> s = neighbors.keySet();
            for(String nodeName: s)
            {
                //if the neighbor is white, add it to the neighbors the inspector can investigate
                if(USER_JACK.equals(neighbors.get(nodeName).getUser()))
                {
                    whiteNeighbors.put(nodeName, neighbors.get(nodeName));
                }
            }
        }
        return whiteNeighbors;
    }

    /**
     * A recursive helper method to find the nodes that Jack can currently move to
     * @param n - node that is being inspected for white neighbors
     * @param whiteNeighbors - the hashmap of the current white neighbors to be added to
     * @param visited - the set of the nodes that have already been visited in this recursion
     */
    private void findAccessibleWhiteNeighbors(Node n, HashMap<String, Node> whiteNeighbors, Set<String> visited, boolean isCarriageActive)
    {
        //first add the current node as visited
        visited.add(n.getName());
        //get the neighbors of this node
        HashMap<String, Node> nNeighbors = n.getNeighbors();
        if(nNeighbors != null) {
            Set<String> neighborNames = nNeighbors.keySet();
            //for all of the neighbors of n
            for (String neighbor : neighborNames) {
                //if the neighbor is a white or red one, return it to be added
                if (USER_JACK.equals(nNeighbors.get(neighbor).getUser()) && !whiteNeighbors.containsKey(neighborNames)) {
                    whiteNeighbors.put(neighbor, nNeighbors.get(neighbor));
                }
                //otherwise, if the neighbor is a black or yellow one and it has not been visited and it is not occupied by an inspector
                else if (USER_INSPECTOR.equals(nNeighbors.get(neighbor).getUser()) && !visited.contains(neighbor)&& (!nNeighbors.get(neighbor).isOccupied() || isCarriageActive)) {
                    findAccessibleWhiteNeighbors(nNeighbors.get(neighbor), whiteNeighbors, visited, isCarriageActive);
                }
            }
        }
    }

    /**
     * Gets the black neighbors of the current node
     * @return the black neighbors of the current node
     */
    public HashMap<String, Node> getBlackNeighbors()
    {
    	//creates a new neighbors hashmap
        HashMap<String, Node> blackNeighbors = new HashMap<>();
        if(USER_JACK.equals(getUser()))
        {
        	//if the user is Jack, then get the set of nodes
            Set<String> s = neighbors.keySet();
            for(String nodeName: s)
            {
            	//if the neighbor node is an inspector's node, add it to the hashmap
                if(USER_INSPECTOR.equals(neighbors.get(nodeName).getUser()))
                {
                    blackNeighbors.put(nodeName, neighbors.get(nodeName));
                }
            }
        }
        else if(USER_INSPECTOR.equals(getUser()))
        {
            //if the user is an Inspector, create a set of nodes to hold visited nodes
            Set<String> visited = new HashSet<>();
            //call a recursive method to find the black nodes
            findAccessibleBlackNeighbors(this, blackNeighbors, visited);

        }
        return blackNeighbors;
    }

    /**
     * Recursive helper method to find the black neighbors of black nodes
     * @param n - node that black neighbors are wanted for
     * @param blackNeighbors - the hashmap of the current black neighbors
     * @param visited - the set of the nodes already visited
     */
    private void findAccessibleBlackNeighbors(Node n, HashMap<String, Node> blackNeighbors, Set<String> visited) {
        //first add the current node as visited
        visited.add(n.getName());
        //get the neighbors of this node
        HashMap<String, Node> nNeighbors = n.getNeighbors();
        if(nNeighbors != null) {
            Set<String> neighborNames = nNeighbors.keySet();
            //for all of the neighbors of n
            for (String neighbor : neighborNames) {
                //if the neighbor is a black or yellow one, return it to be added
                if (USER_INSPECTOR.equals(nNeighbors.get(neighbor).getUser()) && !blackNeighbors.containsKey(neighborNames)) {
                    blackNeighbors.put(neighbor, nNeighbors.get(neighbor));
                }
                //otherwise, if the neighbor is a black or yellow one and it has not been visited and it is not occupied by an inspector
                else if (USER_JACK.equals(nNeighbors.get(neighbor).getUser()) && !visited.contains(neighbor)) {
                    findAccessibleBlackNeighbors(nNeighbors.get(neighbor), blackNeighbors, visited);
                }
            }
        }
    }

    /**
     * Checks whether the current node is occupied
     * @return whether the current node is occupied
     */
    public boolean isOccupied()
    {
        return isOccupied;
    }

    /**
     * Sets whether the current node is occupied
     * @param isOccupied - whether the current nod is occupied
     */
    public void setOccupied(boolean isOccupied)
    {
        this.isOccupied = isOccupied;
    }

    @Override
    public String toString()
    {
        return getName();
    }
}