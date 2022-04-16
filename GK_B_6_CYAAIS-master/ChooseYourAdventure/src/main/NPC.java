package main;

import java.io.Serializable;

/**
 * 
 * @author Grant Duncan
 *
 */
public abstract class NPC implements Serializable {
	private int row;
	private int col;
	private map map;
	private char underNPC;
	private char symbol;
	private int health;
	private int speed;
	private int atkRange;
	private int atkValue;
	private int time;

	public NPC(int row, int col, map map) {
		this.row = row;
		this.col = col;
		this.map = map;
		speed = 10;
		atkRange = 0;
		symbol = '?';
		underNPC = map.getRowCol(row, col); // I like this, need to change PC to
		time=0;									// same method.

	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public void placeNPC(int row, int col) {

		if (this.row != row && this.col != col) {
			map.setRowCol(this.row, this.col, underNPC);
			this.row = row;
			this.col = col;
			underNPC = map.getRowCol(row, col);
			map.setRowCol(row, col, this.symbol);
		}
	}

	public void setUnderNPC(char under) {
		underNPC = under;
	}

	public char getUnderNPC() {
		return underNPC;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) { // argument should be amount gained or
										// lost
		this.health = this.health + health;
	}

	public boolean isKO() {
		if (health <= 0)
			return true;
		return false;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getAtkRange() {
		return atkRange;
	}

	public void setAtkRange(int range) {
		atkRange = range;
	}

	public int getAtkValue() {
		return atkValue;
	}

	public void setAtkValue(int value) {
		atkValue = value;
	}

	public void setSymbol(char sym) {
		symbol = sym;
	}

	public char getSymbol() {
		return symbol;
	}
	public void addTime(int time){
		this.time+=time;
	}
	public void setTime(int time){
		this.time=time;
	}
	public int getTime(){
		return time;
	}

	public void move(int row, int col, map map) {
		// use dijkstras
		// distanceMap npcDist = new distanceMap(map, this);
		// npcDist.createStudentMap();
		//
		// Dijkstra dijk = new Dijkstra(this, row, col, map, npcDist);
		// dijk.getShortestPath();

		distanceMap npcDist = new distanceMap(map, row, col);
		int[][] distMap = npcDist.createStudentMap();
		int[] coord = lowestNeighbor(row, col, distMap);
		placeNPC(coord[0], coord[1]);
		setTime(0);

	}

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
				coord[0] = 10; // always sets to the same as col -1
				// coord[0] = row;
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

	public abstract void update(PC pc, map map);

}
