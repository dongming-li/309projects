package main;

public class security extends NPC {
	private int walkSpeed;
	private int runSpeed;
	private boolean isRunning;
	private boolean hasLOS;
	private boolean alerted;
	int lastKnownRow;
	int lastKnownCol;
	int seeableDist;

	public security(int row, int col, map map) {
		super(row, col, map);
		super.setSymbol(mapNode.SECURITY);
		walkSpeed = super.getSpeed();
		runSpeed = 20;
		seeableDist=20;
		super.setHealth(1000);
		super.setAtkRange(1);
		hasLOS = false;
		isRunning = false;
		alerted = false;
	}

	public int getRunSpeed() {
		return runSpeed;
	}

	public void setRunSpeed(int speed) {
		runSpeed = speed;
	}

	public void setAlerted() {
		if (alerted)
			alerted = false;
		else
			alerted = true;
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
		if (alerted) {
			distanceMap npcdistance = new distanceMap(map, this);
			distanceMap ghostdistance = new distanceMap(map, this);
			int distancemap[][] = npcdistance.createNPCMap();
			int ghostMap[][] = ghostdistance.createGhostMap();

			int hypotenuse = (int) Math.sqrt(
					Math.pow(Math.abs(pc.getRow() - getRow()), 2) + Math.pow(Math.abs(pc.getCol() - getCol()), 2));

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
				move(lastKnownRow, lastKnownCol, map);
				// if there
				if (this.getRow() == lastKnownRow && getCol() == lastKnownCol)
					walk();
				// make its way to the nearest path and walk around
				return false;
			}
		}
		return false;
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
	public void move(int row, int col, map map){
		if(alerted){
			distanceMap npcDist= new distanceMap(map, this);
			npcDist.createNPCMap();
		}
		if(!alerted){ //there will be a problem if the npc is on grass...need to fix somehow
			distanceMap npcDist= new distanceMap(map, this);
			npcDist.createStudentMap();
		}
	}
	@Override
	public void update(PC pc, map map){
		checkLOS(pc, map);
		attackPC(pc, map);
	}
}