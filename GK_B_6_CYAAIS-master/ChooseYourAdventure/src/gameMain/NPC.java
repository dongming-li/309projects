package gameMain;

import java.io.Serializable;

/**
 * The Class NPC.
 *
 * @author Grant Duncan
 */
public abstract class NPC implements Serializable {
	
	/** The row. */
	private int row;
	
	/** The col. */
	private int col;
	
	/** The map. */
	private map map;
	
	/** The under NPC. */
	private char underNPC;
	
	/** The symbol. */
	private char symbol;
	
	/** The health. */
	private int health;
	
	/** The speed. */
	private int speed;
	
	/** The atk range. */
	private int atkRange;
	
	/** The atk value. */
	private int atkValue;
	
	/** The time. */
	private int time;
	
	/** The cash. */
	private int cash;

	/**
	 * Instantiates a new npc.
	 *
	 * @param row the row
	 * @param col the col
	 * @param map the map
	 */
	public NPC(int row, int col, map map) {
		this.row = row;
		this.col = col;
		this.map = map;
		speed = 5;
		atkRange = 0;
		symbol = '?';
		underNPC = map.getRowCol(row, col); // I like this, need to change PC to
		time=0;		
		// same method.

	}

	/**
	 * Gets the row.
	 *
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Gets the col.
	 *
	 * @return the col
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Place NPC.
	 *
	 * @param row the row
	 * @param col the col
	 */
	public void placeNPC(int row, int col) {

		if (this.row != row || this.col != col) {
			map.setRowCol(this.row, this.col, underNPC);
			this.row = row;
			this.col = col;
			if(map.getRowCol(row, col)!=mapNode.PC&&map.getRowCol(row, col)!=mapNode.BULLY&&map.getRowCol(row, col)!=mapNode.STUDENT&&map.getRowCol(row, col)!=mapNode.SECURITY){
			underNPC = map.getRowCol(row, col);
			}
			else if(map.getRowCol(row, col)==mapNode.PC){
				underNPC=main.pc.getUnderPC();
			}
			else if(map.getRowCol(row, col)==mapNode.STUDENT|| map.getRowCol(row, col)==mapNode.SECURITY ||map.getRowCol(row, col)==mapNode.BULLY){
				underNPC=map.getNPCOnNode(row, col).underNPC;
			}
			
			map.setRowCol(row, col, this.symbol);
		}
	}

	/**
	 * Sets the under NPC.
	 *
	 * @param under the new under NPC
	 */
	public void setUnderNPC(char under) {
		underNPC = under;
	}

	/**
	 * Gets the under NPC.
	 *
	 * @return the under NPC
	 */
	public char getUnderNPC() {
		return underNPC;
	}

	/**
	 * Gets the health.
	 *
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	/**
	 * Sets the health.
	 *
	 * @param health the new health
	 */
	public void setHealth(int health) { // argument should be amount gained or
										// lost
		this.health = this.health + health;
	}
	
	/**
	 * Gets the cash.
	 *
	 * @return the cash
	 */
	public int getCash(){
		return cash;
	}
	
	/**
	 * Sets the cash.
	 *
	 * @param cash the new cash
	 */
	public void setCash(int cash){
		this.cash=cash;
	}
	
	/**
	 * Checks if is ko.
	 *
	 * @return true, if is ko
	 */
	public boolean isKO() {
		if (health <= 0){
			
		
			return true;
		}
		return false;
	}

	/**
	 * Gets the speed.
	 *
	 * @return the speed
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * Sets the speed.
	 *
	 * @param speed the new speed
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}

	/**
	 * Gets the atk range.
	 *
	 * @return the atk range
	 */
	public int getAtkRange() {
		return atkRange;
	}

	/**
	 * Sets the atk range.
	 *
	 * @param range the new atk range
	 */
	public void setAtkRange(int range) {
		atkRange = range;
	}

	/**
	 * Gets the atk value.
	 *
	 * @return the atk value
	 */
	public int getAtkValue() {
		return atkValue;
	}

	/**
	 * Sets the atk value.
	 *
	 * @param value the new atk value
	 */
	public void setAtkValue(int value) {
		atkValue = value;
	}

	/**
	 * Sets the symbol.
	 *
	 * @param sym the new symbol
	 */
	public void setSymbol(char sym) {
		symbol = sym;
	}

	/**
	 * Gets the symbol.
	 *
	 * @return the symbol
	 */
	public char getSymbol() {
		return symbol;
	}
	
	/**
	 * Adds the time.
	 *
	 * @param time the time
	 */
	public void addTime(int time){
		this.time+=time;
	}
	
	/**
	 * Sets the time.
	 *
	 * @param time the new time
	 */
	public void setTime(int time){
		this.time=time;
	}
	
	/**
	 * Gets the time.
	 *
	 * @return the time
	 */
	public int getTime(){
		return time;
	}

	/**
	 * Move.
	 *
	 * @param row the row
	 * @param col the col
	 * @param map the map
	 * @param pc the pc
	 * @return true, if successful
	 */
	public boolean move(int row, int col, map map,PC pc) {
		// use dijkstras
		// distanceMap npcDist = new distanceMap(map, this);
		// npcDist.createStudentMap();
		//
		// Dijkstra dijk = new Dijkstra(this, row, col, map, npcDist);
		// dijk.getShortestPath();
		if(this.isKO()){
			return false;
		}
		distanceMap npcDist = new distanceMap(map, row, col);
		int[][] distMap = npcDist.createStudentMap();
		int[] coord = lowestNeighbor(this.row, this.col, distMap);
		placeNPC(coord[0], coord[1]);
		setTime(0);
		return true;
	}

	/**
	 * Lowest neighbor.
	 *
	 * @param row the row
	 * @param col the col
	 * @param map the map
	 * @return the int[]
	 */
	public int[] lowestNeighbor(int row, int col, int[][] map) {

		int[] coord = new int[2];

		// top left
		if (row == 0 && col == 0) {
			if (map[this.row][this.col] > map[row][col + 1]) {
				coord[0] = row;
				coord[1] = (col + 1);
				return coord;
			}
			if (map[this.row][this.col] > map[row + 1][col]) {
				coord[0] = (row + 1);
				coord[1] = col;
				return coord;
			}
			if (map[this.row][this.col] > map[row + 1][col + 1]) {
				coord[0] = (row + 1);
				coord[1] = (col + 1);
				return coord;
			}
		}
		// top right
		else if (row == 0 && col == map[0].length - 1) {
			if (map[this.row][this.col] > map[row][col - 1]) {
				coord[0] = row;
				coord[1] = col - 1;
				return coord;
			} else if (map[this.row][this.col] > map[row + 1][col]) {
				coord[0] = row + 1;
				coord[1] = col;
				return coord;
			} else if (map[this.row][this.col] > map[row + 1][col - 1]) {
				coord[0] = row + 1;
				coord[1] = col - 1;
				return coord;
			}
		}
		// bottom left
		else if (row == map.length - 1 && col == 0) {
			if (map[this.row][this.col] > map[row - 1][col]) {
				coord[0] = row - 1;
				coord[1] = col;
				return coord;
			} else if (map[this.row][this.col] > map[row][col + 1]) {
				coord[0] = row;
				coord[1] = col + 1;
				return coord;
			} else if (map[this.row][this.col] > map[row - 1][col + 1]) {
				coord[0] = row - 1;
				coord[1] = col + 1;
				return coord;
			}
		}
		// bottom right
		else if (row == map.length - 1 && col == map[0].length - 1) {
			if (map[this.row][this.col] > map[row][col - 1]) {
				coord[0] = row;
				coord[1] = col - 1;
				return coord;

			} else if (map[this.row][this.col] > map[row - 1][col]) {
				coord[0] = row - 1;
				coord[1] = col;
				return coord;
			} else if (map[this.row][this.col] > map[row - 1][col - 1]) {
				coord[0] = row - 1;
				coord[1] = col - 1;
				return coord;
			}
		}
		// top
		else if (row == 0) {
			if (map[this.row][this.col] > map[row][col - 1]) {
				coord[0] = row;
				coord[1] = col - 1;
				return coord;
			} else if (map[this.row][this.col] > map[row][col + 1]) {
				coord[0] = row;
				coord[1] = col + 1;
				return coord;
			} else if (map[this.row][this.col] > map[row + 1][col]) {
				coord[0] = row + 1;
				coord[1] = col;
				return coord;
			} else if (map[this.row][this.col] > map[row + 1][col - 1]) {
				coord[0] = row + 1;
				coord[1] = col - 1;
				return coord;
			} else if (map[this.row][this.col] > map[row + 1][col + 1]) {
				coord[0] = row + 1;
				coord[1] = col + 1;
				return coord;
			}
		}
		// bottom
		else if (row == map.length - 1) {
			if (map[this.row][this.col] > map[row][col - 1]) {
				coord[0] = row;
				coord[1] = col - 1;
				return coord;
			} else if (map[this.row][this.col] > map[row][col + 1]) {
				coord[0] = row;
				coord[1] = col + 1;
				return coord;
			} else if (map[this.row][this.col] > map[row - 1][col]) {
				coord[0] = row - 1;
				coord[1] = col;
				return coord;
			} else if (map[this.row][this.col] > map[row - 1][col - 1]) {
				coord[0] = row - 1;
				coord[1] = col - 1;
				return coord;
			} else if (map[this.row][this.col] > map[row - 1][col + 1]) {
				coord[0] = row - 1;
				coord[1] = col + 1;
				return coord;
			}
		}
		// left
		else if (col == 0) {
			if (map[this.row][this.col] > map[row - 1][col]) {
				coord[0] = row - 1;
				coord[1] = col;
				return coord;
			} else if (map[this.row][this.col] > map[row + 1][col]) {
				coord[0] = row + 1;
				coord[1] = col;
				return coord;
			} else if (map[this.row][this.col] > map[row][col + 1]) {
				coord[0] = row;
				coord[1] = col + 1;
				return coord;
			} else if (map[this.row][this.col] > map[row - 1][col + 1]) {
				coord[0] = row - 1;
				coord[1] = col + 1;
				return coord;
			} else if (map[this.row][this.col] > map[row + 1][col + 1]) {
				coord[0] = row + 1;
				coord[1] = col + 1;
				return coord;
			}
		}
		// right
		else if (col == map[0].length - 1) {
			if (map[this.row][this.col] > map[row - 1][col]) {
				coord[0] = row - 1;
				coord[1] = col;
				return coord;
			} else if (map[this.row][this.col] > map[row + 1][col]) {
				coord[0] = row + 1;
				coord[1] = col;
				return coord;
			} else if (map[this.row][this.col] > map[row][col - 1]) {
				coord[0] = row;
				coord[1] = col - 1;
				return coord;
			} else if (map[this.row][this.col] > map[row - 1][col - 1]) {
				coord[0] = row - 1;
				coord[1] = col - 1;
				return coord;
			} else if (map[this.row][this.col] > map[row + 1][col - 1]) {
				coord[0] = row + 1;
				coord[1] = col - 1;
				return coord;
			}
		}
		//middle
		else {
			if (map[this.row][this.col] > map[row - 1][col]) {
				coord[0] = (row - 1);
				coord[1] = col;
				return coord;
			} else if (map[this.row][this.col] > map[row][col - 1]) {
				// move pc to 4,2 and watch...
				//coord[0] = 10; // always sets to the same as col -1
				coord[0] = row;
				coord[1] = (col - 1);
				return coord;
			} else if (map[this.row][this.col] > map[row][col + 1]) {
				coord[0] = row;
				coord[1] = (col + 1);
				return coord;
			} else if (map[this.row][this.col] > map[row + 1][col]) {
				coord[0] = (row + 1);
				coord[1] = col;
				return coord;
			} else if (map[this.row][this.col] > map[row - 1][col - 1]) {
				coord[0] = (row - 1);
				coord[1] = (col - 1);
				return coord;
			} else if (map[this.row][this.col] > map[row - 1][col + 1]) {
				coord[0] = (row - 1);
				coord[1] = (col + 1);
				return coord;
			} else if (map[this.row][this.col] > map[row + 1][col - 1]) {
				coord[0] = (row + 1);
				coord[1] = (col - 1);
				return coord;
			} else if (map[this.row][this.col] > map[row + 1][col + 1]) {
				coord[0] = (row + 1);
				coord[1] = (col + 1);
				return coord;
			} else {
				coord[0] = row;
				coord[1] = col;
				return coord;
			}

		}
		return coord;
	}

	/**
	 * Update.
	 *
	 * @param pc the pc
	 * @param map the map
	 * @return true, if successful
	 */
	public abstract boolean update(PC pc, map map);

}
