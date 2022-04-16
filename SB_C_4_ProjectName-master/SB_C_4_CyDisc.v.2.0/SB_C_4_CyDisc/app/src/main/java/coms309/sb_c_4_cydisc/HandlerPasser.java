package coms309.sb_c_4_cydisc;

/**
 * @author Pierce Adajar
 * An interface for passing a LocationHandler object
 * from an Activity to a Fragment.
 */
public interface HandlerPasser {

    /**
	 * Returns a LocationHandler object.
	 * @return LocationHandler a LocationHandler object
	 */
    public LocationHandler getLocationHandler();

}
