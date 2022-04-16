package gameMain;

import java.util.ArrayList;
import java.util.Random;

/**
 * The Class student.
 *
 * @author Grant Duncan
 */

/**
 * This class is for a type of NPC called a student. A student walks from class
 * to class and can only damage the player if they are running (which will
 * happen moments before class starts or if the pc attacks). Students can only
 * walk on paths or crosswalks.
 *
 */

public class student extends NPC {

	/** The walk speed. */
	private int walkSpeed;
	
	/** The run speed. */
	private int runSpeed;
	
	/** The bike speed. */
	private int bikeSpeed;
	
	/** The cash. */
	int cash;// might make this generic
	
	/** The coords. */
	private ArrayList<int[]> coords = new ArrayList<int[]>();
	
	/** The curr obj. */
	private int[] currObj;

	/**
	 * Instantiates a new student.
	 *
	 * @param row the row
	 * @param col the col
	 * @param map the map
	 * @param doors the doors
	 */
	public student(int row, int col, map map, ArrayList<int[]> doors) {
		super(row, col, map);
		super.setSymbol(mapNode.STUDENT);
		super.setHealth(10);
		walkSpeed = super.getSpeed();
		runSpeed = 20;
		bikeSpeed = 40;
		Random rand= new Random();
		super.setCash(rand.nextInt(100));

		currObj = new int[2];

		coords = doors;
	}

	/* (non-Javadoc)
	 * @see gameMain.NPC#update(gameMain.PC, gameMain.map)
	 */
	@Override
	public boolean update(PC pc, map map) {
		if(this.isKO()) {
			
			
			int thisrow= this.getRow();
			int thiscol=this.getCol();
			char under = this.getUnderNPC();
			map.setStudentHurt(true);
			map.setRowCol(this.getRow(), this.getCol(), 'M');
			Runnable r = new Runnable(){
				public void run(){
					long starttime = System.currentTimeMillis();
					long currTime=0;
					while (currTime<15000){
						currTime=System.currentTimeMillis()-starttime;
					}
					map.setRowCol(thisrow, thiscol, under );
				}
				
			};
		
			new Thread(r).start();
			return false;
			
		}
		if ((currObj[0] == 0 && currObj[1] == 0) || (super.getRow() == currObj[0] && super.getCol() == currObj[1])) {
			Random rand = new Random();

			int x = Math.abs(rand.nextInt(coords.size()));

			placeNPC(coords.get(x)[0], coords.get(x)[1]);

			x = Math.abs(rand.nextInt(coords.size()));

			currObj[0] = coords.get(x)[0];
			currObj[1] = coords.get(x)[1];

			if (super.move(currObj[0], currObj[1], map,pc))
				return true;
			else {
				return false;
			}
		} else {
			if (super.move(currObj[0], currObj[1], map,pc))
				return true;
			else
				return false;
		}
	}

}