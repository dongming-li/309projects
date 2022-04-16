package gameMain;

import java.util.Random;

/**
 * The Class security.
 *
 * @author Grant Duncan
 */

/**
 * Security comes out at night and if you attack an NPC
 *
 */
public class security extends NPC {
	
	/** The walk speed. */
	private int walkSpeed;
	
	/** The run speed. */
	private int runSpeed;
	
	/** The is running. */
	private boolean isRunning;
	
	/** The has LOS. */
	private boolean hasLOS;
	
	/** The last known row. */
	private int lastKnownRow = 1;
	
	/** The last known col. */
	private int lastKnownCol = 1;
	
	/** The seeable dist. */
	private int seeableDist;
	
	/** The counter. */
	private int counter =30;
	
	/** The name. */
	// here are some fun variables I thought of that we may not use
	private String name;
	
	/** The K ocount. */
	private int KOcount;
	
	/** The cash. */
	private int cash;

	/**
	 * Instantiates a new security.
	 *
	 * @param row the row
	 * @param col the col
	 * @param map the map
	 */
	public security(int row, int col, map map) {
		super(row, col, map);
		super.setSymbol(mapNode.SECURITY);
		walkSpeed = super.getSpeed();
		runSpeed = 10;
		super.setHealth(500);
		super.setAtkRange(1);
		super.setAtkValue(5);
		Random rand= new Random();
		super.setCash(rand.nextInt(100));
		seeableDist = 10;
		hasLOS = false;
		isRunning = false;
		setTime(0);

	}

	/**
	 * Gets the run speed.
	 *
	 * @return the run speed
	 */
	public int getRunSpeed() {
		return runSpeed;
	}

	/**
	 * Sets the run speed.
	 *
	 * @param speed the new run speed
	 */
	public void setRunSpeed(int speed) {
		runSpeed = speed;
	}

	/**
	 * Run.
	 */
	public void run() {
		isRunning = true;
		super.setSpeed(runSpeed);
	}

	/**
	 * Walk.
	 */
	public void walk() {
		isRunning = false;
		super.setSpeed(walkSpeed);
	}
	
	/**
	 * Gets the counter.
	 *
	 * @return the counter
	 */
	public int getCounter(){
		return counter;
	}

	/**
	 * Check LOS.
	 *
	 * @param pc the pc
	 * @param map the map
	 * @return true, if successful
	 */
	public boolean checkLOS(PC pc, map map) {

		distanceMap npcdistance = new distanceMap(map, this);
		distanceMap ghostdistance = new distanceMap(map, this);
		int distancemap[][] = npcdistance.createNPCMap();
		int ghostMap[][] = ghostdistance.createGhostMap();

		int hypotenuse = (int) Math
				.sqrt(Math.pow(Math.abs(pc.getRow() - getRow()), 2) + Math.pow(Math.abs(pc.getCol() - getCol()), 2));

		// check if the distance is correct
		//
		if (distancemap[pc.getRow()][pc.getCol()] <= seeableDist
				&& distancemap[pc.getRow()][pc.getCol()] == ghostMap[pc.getRow()][pc.getCol()]) {
			hasLOS = true;
			lastKnownRow = pc.getRow();
			lastKnownCol = pc.getCol();
			run();
			move(pc.getRow(), pc.getCol(), map, pc);
			return true;
		} else {// it doesnt
			hasLOS = false;
			// go to last know location
			move(lastKnownRow, lastKnownCol, map,pc);
			// if there
			if (this.getRow() == lastKnownRow && getCol() == lastKnownCol)
				walk();
			// make its way to the nearest path and walk around
			return false;
		}
	}

	/**
	 * Attack PC.
	 *
	 * @param pc the pc
	 * @param map the map
	 */
	public void attackPC(PC pc, map map) {
		// if( PC and NPC are within atkRange)
		// do damage
		if (hasLOS) {
			distanceMap distance = new distanceMap(map, this);
			int distancemap[][] = distance.createNPCMap();
			if (distancemap[pc.getRow()][pc.getCol()] <= super.getAtkRange())
				pc.dealDamage(super.getAtkValue());
		}
	}

	/* (non-Javadoc)
	 * @see gameMain.NPC#move(int, int, gameMain.map, gameMain.PC)
	 */
	@Override
	public boolean move(int row, int col, map map,PC pc) {
		if (isRunning) {
			if(this.isKO())
				return false;
			// distanceMap npcDist= new distanceMap(map, this);
			distanceMap npcDist = new distanceMap(map, row, col);

			// int[] coord = super.lowestNeighbor(row, col,
			// npcDist.createNPCMap());
			int[] coord = super.lowestNeighbor(this.getRow(), this.getCol(), npcDist.createMap());
			
			System.out.println(coord[1]);
			if(coord[1] > pc.getCol()) {
				
				map.setGuardLeft(true);
			}
			else {
				map.setGuardLeft(false);
			}
			placeNPC(coord[0], coord[1]);
			
			setTime(0);
			return true;

		} else if (!isRunning) {// there will be a problem if the npc is on
								// grass...need to fix somehow
			
			if(col> pc.getCol()) {
				
				map.setGuardLeft(true);
			}
			else {
				map.setGuardLeft(false);
			}
			if (super.move(row, col, map,pc))
				return true;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see gameMain.NPC#update(gameMain.PC, gameMain.map)
	 */
	@Override
	public boolean update(PC pc, map map) {
		counter--;
		if(counter==0){
			map.setRowCol(this.getRow(), this.getCol(), this.getUnderNPC());
			return false;
		}
	
		if(this.isKO()) {
			
			map.setGuardHurtOnGrass(true);
			map.setRowCol(this.getRow(), this.getCol(), 'L');
			return false;
		}
		checkLOS(pc, map);
		attackPC(pc, map);
		return true;
	}

}
