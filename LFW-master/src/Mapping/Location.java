package Mapping;

/**
 * This class is used to store location information for nodes and places on the map.
 * Created by sauerbreiale on 9/11/2017.
 */
public class Location {
    private final double x;
    private final double y;

    /**
     * Constructor for a new location
     * @param x - the x coordinate of the location
     * @param y - the y coordinate of the location
     */
    public Location(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public double getXCoordinate()
    {
        return x;
    }
    public double getYCoordinate()
    {
        return y;
    }
}
