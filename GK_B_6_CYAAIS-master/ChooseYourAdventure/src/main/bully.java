package main;

/**
@author Grant Duncan
*/

/**
 * This class is for a type of NPC called a bully. A bully wanders around and
 * doesn't go to class. Also, if he gains line of sight on the PC, he will chase
 * him and try to knock him out and steal some of his money.
 *
 */
public class bully extends NPC {
	private int walkSpeed;
	private int runSpeed;
	private boolean isRunning;
	private boolean hasLOS;
	private int lastKnownRow = 1;
	private int lastKnownCol = 1;
	private int seeableDist;

	// here are some fun variables I thought of that we may not use
	private String name;
	private int KOcount;
	private int cash;

	public bully(int row, int col, map map) {
		super(row, col, map);
		super.setSymbol(mapNode.BULLY);
		walkSpeed = super.getSpeed();
		runSpeed = 20;
		super.setHealth(50);
		super.setAtkRange(1);
		super.setAtkValue(10);
		seeableDist = 20;
		hasLOS = false;
		isRunning = false;

	}

	public int getRunSpeed() {
		return runSpeed;
	}

	public void setRunSpeed(int speed) {
		runSpeed = speed;
	}

	public void run() {
		isRunning = true;
		super.setSpeed(runSpeed);
	}

	public void walk() {
		isRunning = false;
		super.setSpeed(walkSpeed);
	}

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
			move(pc.getRow(), pc.getCol(), map);
			return true;
		} else {// it doesnt
			hasLOS = false;
			// go to last know location
			if (lastKnownRow != 1 && lastKnownCol != 1) {
				move(lastKnownRow, lastKnownCol, map);
			}
			// if there
			if (this.getRow() == lastKnownRow && getCol() == lastKnownCol)
				walk();
			// make its way to the nearest path and walk around
			return false;
		}
	}

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

	@Override
	public void move(int row, int col, map map) {
		if (isRunning) {
			// distanceMap npcDist= new distanceMap(map, this);
			distanceMap npcDist = new distanceMap(map, row, col);

			// int[] coord = super.lowestNeighbor(row, col,
			// npcDist.createNPCMap());
			int[] coord = super.lowestNeighbor(this.getRow(), this.getCol(), npcDist.createMap());
			placeNPC(coord[0], coord[1]);
			setTime(0);

		} else if (!isRunning) {// there will be a problem if the npc is on
								// grass...need to fix somehow
			super.move(row, col, map);
		}
	}

	@Override
	public void update(PC pc, map map) {
		checkLOS(pc, map);
		attackPC(pc, map);
	}

}