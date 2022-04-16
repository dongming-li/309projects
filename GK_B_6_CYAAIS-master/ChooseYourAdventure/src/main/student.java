package main;

import java.util.ArrayList;

/**
@author Grant Duncan
*/

/**
 * This class is for a type of NPC called a student. A student walks from class to class and can only damage the player
 * if they are running (which will happen moments before class starts or if the pc attacks).
 *  Students can only walk on paths or crosswalks.
 *
 */

public class student extends NPC{
	
	private int walkSpeed;
	private int runSpeed;
	private int bikeSpeed;
	int cash;//might make this generic
	private ArrayList<int[]> coords= new ArrayList<int[]>();
	
	public student(int row, int col, map map, ArrayList<int[]> doors){
		super(row,col,map);
		super.setSymbol(mapNode.STUDENT);
		super.setHealth(10);
		walkSpeed=super.getSpeed();
		runSpeed=20;
		bikeSpeed=40;
		
		coords=doors;
	}
	@Override
	public void update(PC pc, map map){
		super.move(coords.get(0)[0], coords.get(0)[1], map);
	}
	
	
	
	
	
	
	
	
	
}