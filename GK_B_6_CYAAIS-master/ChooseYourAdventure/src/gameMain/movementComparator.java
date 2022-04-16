package gameMain;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * I think I need this when I create the priorty queue... but we will see
 */

/**
 * The Class movementComparator.
 */
public class movementComparator implements Comparator<Integer>{
	
	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	public int compare(Integer x, Integer y){
		
		if(x > y)
			return x;
		else
			return y;
		
	}	
	
}