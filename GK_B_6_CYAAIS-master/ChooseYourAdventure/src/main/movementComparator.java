package main;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * I think I need this when I create the priorty queue... but we will see
 */

public class movementComparator implements Comparator<Integer>{
	
	public int compare(Integer x, Integer y){
		
		if(x > y)
			return x;
		else
			return y;
		
	}	
	
}