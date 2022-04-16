/**
 * 
 * @author Grant Duncan
 *
 */
package gameMain;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Class map.
 */
public class map implements java.io.Serializable {

	/*
	 * '*' denote the outer edge '0' denotes a building '^' denotes a grass '/'
	 * denotes a door
	 */

	/** The map. */
	// private char[][] map;
	private mapNode[][] map;
	
	/** The row. */
	private int row;
	
	/** The col. */
	private int col;
	
	/** The on door. */
	public boolean onDoor = false;
	
	/** The doors. */
	private ArrayList<int[]> doors = new ArrayList();
	
	/** The num change. */
	private int numChange = 0;
	
	/** The direction name. */
	private String directionName = null;
	
	/** The stay left. */
	private boolean stayLeft = false;
	
	/** The on left. */
	private boolean onLeft = false;
	
	/** The alternate feet. */
	private int alternateFeet = 0;
	
	/** The up held. */
	private boolean upHeld = false;
	
	/** The on grass. */
	private boolean onGrass = false;
	
	/** The on bus stop. */
	private boolean onBusStop = false;
	
	/** The on edge door. */
	private boolean onEdgeDoor = false;
	
	/** The on road. */
	private boolean onRoad = false;
	
	/** The bully on side walk. */
	private boolean bullyOnSideWalk;
	
	/** The bully hurt on grass. */
	private boolean bullyHurtOnGrass;
	
	/** The guard hurt on grass. */
	private boolean guardHurtOnGrass;
	
	/** The guard left. */
	private boolean guardLeft = false;
	
	/** The student hurt. */
	private boolean studentHurt= false;
	
	/**
	 * Instantiates a new map.
	 */
	public map() {

	}

	/**
	 * Checks if is on grass.
	 *
	 * @return true, if is on grass
	 */
	public boolean isOnGrass() {
		return onGrass;
	}

	/**
	 * Sets the on grass.
	 *
	 * @param onGrass the new on grass
	 */
	public void setOnGrass(boolean onGrass) {
		this.onGrass = onGrass;
	}

	/**
	 * Gets the up held.
	 *
	 * @return the up held
	 */
	public boolean getUpHeld() {
		return upHeld;
	}

	/**
	 * Sets the up held.
	 *
	 * @param upHeld the new up held
	 */
	public void setUpHeld(boolean upHeld) {
		this.upHeld = upHeld;
	}

	/**
	 * Gets the alternate feet.
	 *
	 * @return the alternate feet
	 */
	public int getAlternateFeet() {
		return alternateFeet;
	}

	/**
	 * Sets the alternate feet.
	 *
	 * @param alternateFeet the new alternate feet
	 */
	public void setAlternateFeet(int alternateFeet) {
		this.alternateFeet = alternateFeet;
	}

	/**
	 * Gets the on left.
	 *
	 * @return the on left
	 */
	public boolean getOnLeft() {
		return onLeft;
	}

	/**
	 * Sets the on left.
	 *
	 * @param onLeft the new on left
	 */
	public void setOnLeft(boolean onLeft) {
		this.onLeft = onLeft;
	}

	/**
	 * Gets the stay left.
	 *
	 * @return the stay left
	 */
	public boolean getStayLeft() {
		return stayLeft;
	}

	/**
	 * Sets the stay left.
	 *
	 * @param stayLeft the new stay left
	 */
	public void setStayLeft(boolean stayLeft) {
		this.stayLeft = stayLeft;
	}

	/**
	 * Gets the direction name.
	 *
	 * @return the direction name
	 */
	public String getdirectionName() {
		return directionName;
	}

	/**
	 * Sets the direction name.
	 *
	 * @param name the new direction name
	 */
	public void setdirectionName(String name) {
		directionName = name;
	}

	/**
	 * Gets the num change.
	 *
	 * @return the num change
	 */
	public int getnumChange() {
		return numChange;
	}

	/**
	 * Sets the num change.
	 *
	 * @param value the new num change
	 */
	public void setnumChange(int value) {
		numChange = value;
	}

	/**
	 * Sets the on door.
	 *
	 * @param value the new on door
	 */
	public void setOnDoor(boolean value) {
		onDoor = value;
	}

	/**
	 * Gets the on door.
	 *
	 * @return the on door
	 */
	public boolean getOnDoor() {
		return onDoor;
	}

	/**
	 * Instantiates a new map.
	 *
	 * @param row the row
	 * @param col the col
	 */
	public map(int row, int col) {
		this.row = row;
		this.col = col;
		map = new mapNode[this.row][this.col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				map[i][j] = new mapNode();
			}
		}
		getTextMap();
		// initMap(col, row);
	}

	/**
	 * Sets the row col.
	 *
	 * @param row the row
	 * @param col the col
	 * @param c the c
	 */
	public void setRowCol(int row, int col, char c) {
		map[row][col].setSymb(c);
	}

	/**
	 * Gets the row col.
	 *
	 * @param row the row
	 * @param col the col
	 * @return the row col
	 */
	public char getRowCol(int row, int col) {
		return map[row][col].getSymbol();
	}

	/**
	 * Checks if is walkable.
	 *
	 * @param row the row
	 * @param col the col
	 * @return true, if is walkable
	 */
	public boolean isWalkable(int row, int col) {
		return map[row][col].isWalkable();
	}
	
	/**
	 * Checks if is PC walkable.
	 *
	 * @param row the row
	 * @param col the col
	 * @return true, if is PC walkable
	 */
	public boolean isPCWalkable(int row, int col) {
		return map[row][col].isPCWalkable();
	}

	/**
	 * Checks if is student walkable.
	 *
	 * @param row the row
	 * @param col the col
	 * @return true, if is student walkable
	 */
	public boolean isStudentWalkable(int row, int col) {
		return map[row][col].isStudentWalkable();
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
	 * Inits the map.
	 *
	 * @param row the row
	 * @param col the col
	 */
	public void initMap(int row, int col) {

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				if (i == 0 || j == 0 || i == (row - 1) || j == (col - 1)) {
					setRowCol(i, j, '*');
				} else
					setRowCol(i, j, '^');
			}
		}
	}

	/**
	 * Gets the text map.
	 *
	 * 
	 */
	public void getTextMap() {
		try {
			File charmap = new File(System.getProperty("user.dir") + "\\charmap.txt");
			Scanner in = new Scanner(charmap);
			int row = 0;
			while (in.hasNextLine()) {
				String content = in.nextLine();
				for (int i = 0; i < content.length(); i++) {
					setRowCol(row, i, content.charAt(i));
				}
				row++;
			}
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Creates the building.
	 *
	 * @param row the row
	 * @param col the col
	 * @param height the height
	 * @param width the width
	 * @return true, if successful
	 */
	public boolean createBuilding(int row, int col, int height, int width) {

		if (row + width > this.row) {
			System.out.println("Out of bounds");
			return false;
		} else if (col + height > this.col) {
			System.out.println("Out of bounds");
			return false;
		}

		for (int l = row; l < row + width + 1; l++) {
			for (int k = col; k < col + height + 1; k++) {
				// building goes out of bounds
				if (getRowCol(k, l) == '*') {
					System.out.println("building failed: building out of bounds");
					return false;
				}
				// building overlaps another
				if (getRowCol(k, l) == '0' || getRowCol(k, l) == '/') {
					System.out.println("building failed: building touches another");
					return false;
				}
				// building overlaps another
				if (getRowCol(k - 1, l - 1) == '0' || getRowCol(k - 1, l - 1) == '/') {
					System.out.println("building failed: building touches another");
					return false;
				}
				// building overlaps another
				if (getRowCol(k - 1, l) == '0' || getRowCol(k - 1, l) == '/') {
					System.out.println("building failed: building touches another");
					return false;
				}
				// building overlaps another
				if (getRowCol(k, l - 1) == '0' || getRowCol(k, l - 1) == '/') {
					System.out.println("building failed: building touches another");
					return false;
				}
				// building overlaps another
				if (getRowCol(k + 1, l) == '0' || getRowCol(k + 1, l) == '/') {
					System.out.println("building failed: building touches another");
					return false;
				}
				// building overlaps another
				if (getRowCol(k, l + 1) == '0' || getRowCol(k, l + 1) == '/') {
					System.out.println("building failed: building touches another");
					return false;
				}
				// building overlaps another
				if (getRowCol(k + 1, l + 1) == '0' || getRowCol(k + 1, l + 1) == '/') {
					System.out.println("building failed: building touches another");
					return false;
				}

			}
		}

		for (int i = row; i < row + width + 1; i++) {
			for (int j = col; j < col + height + 1; j++) {
				setRowCol(i, j, '0');
			}
		}
		setRowCol(row + width, col + height / 2, '/');
		int[] temp = { row + width, col + height / 2 };
		doors.add(temp);
		return true;
	}

	/**
	 * Place PC.
	 *
	 * @param row the row
	 * @param col the col
	 * @param pc the pc
	 * @return true, if successful
	 */
	public boolean placePC(int row, int col, PC pc) {
		if (getRowCol(row, col) == mapNode.BUILDING)
			return false;
		else if (getRowCol(row, col) == mapNode.EDGE)
			return false;
		else {
			setRowCol(row, col, mapNode.PC);
			pc.placePC(row, col);
			return true;
		}
	}

	/**
	 * Render map.
	 *
	 * @param map the map
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void renderMap(map map) throws IOException {

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(map.getRowCol(i, j) + " ");
			}
			System.out.print("\n");
		}
	}

	/**
	 * Render PC view.
	 *
	 * @param map the map
	 * @param pc the pc
	 */
	public void renderPCView(map map, PC pc) {
		int row = pc.getRow();
		int col = pc.getCol();
		// change this to change the player view
		int viewSize = 18;
		int i, j;
		// pc is in the middle
		if (row - viewSize > 0 && row + viewSize < map.row && col - viewSize > 0 && col + viewSize < map.col) {
			for (i = row - viewSize; i < row + viewSize; i++) {
				for (j = col - viewSize; j < col + viewSize; j++) {
					System.out.print(map.getRowCol(i, j) + "  ");
				}
				System.out.print("\n");
			}
		}
		// top left corner
		else if (row - viewSize < 0 && col - viewSize < 0) {
			for (i = 0; i < row + viewSize + (Math.abs(row - viewSize)); i++) {
				for (j = 0; j < col + viewSize + (Math.abs(col - viewSize)); j++) {
					System.out.print(map.getRowCol(i, j) + "  ");
				}
				System.out.print("\n");
			}
		}
		// bottom left corner
		else if (row + viewSize > map.row && col - viewSize < 0) {
			for (i = row - viewSize - (Math.abs(row + viewSize - map.row)); i < map.row; i++) {
				for (j = 0; j < col + viewSize + (Math.abs(col - viewSize)); j++) {
					System.out.print(map.getRowCol(i, j) + "  ");
				}
				System.out.print("\n");
			}
		}
		// top right corner
		else if (row - viewSize < 0 && col + viewSize > map.col) {
			for (i = 0; i < row + viewSize + (Math.abs(row - viewSize)); i++) {
				for (j = col - viewSize - (Math.abs(col + viewSize - map.col)); j < map.col; j++) {
					System.out.print(map.getRowCol(i, j) + "  ");
				}
				System.out.print("\n");
			}
		}
		// bottom right corner
		else if (row + viewSize > map.row && col + viewSize > map.col) {
			for (i = row - viewSize - (Math.abs(row + viewSize - map.row)); i < map.row; i++) {
				for (j = col - viewSize - (Math.abs(col + viewSize - map.col)); j < map.col; j++) {
					System.out.print(map.getRowCol(i, j) + "  ");
				}
				System.out.print("\n");
			}
		}
		// top edge
		else if (row - viewSize < 0) {
			for (i = 0; i < row + viewSize + (Math.abs(row - viewSize)); i++) {
				for (j = col - viewSize; j < col + viewSize; j++) {
					System.out.print(map.getRowCol(i, j) + "  ");
				}
				System.out.print("\n");
			}
		}
		// bottom edge
		else if (row + viewSize > map.row) {
			for (i = row - viewSize - (Math.abs(row + viewSize - map.row)); i < map.row; i++) {
				for (j = col - viewSize; j < col + viewSize; j++) {
					System.out.print(map.getRowCol(i, j) + "  ");
				}
				System.out.print("\n");
			}
		}
		// left edge
		else if (col - viewSize < 0) {
			for (i = row - viewSize; i < row + viewSize; i++) {
				for (j = 0; j < col + viewSize + (Math.abs(col - viewSize)); j++) {
					System.out.print(map.getRowCol(i, j) + "  ");
				}
				System.out.print("\n");
			}
		}
		// right edge
		else if (col + viewSize > map.col) {
			for (i = row - viewSize; i < row + viewSize; i++) {
				for (j = col - viewSize - (Math.abs(col + viewSize - map.col)); j < map.col; j++) {
					System.out.print(map.getRowCol(i, j) + "  ");
				}
				System.out.print("\n");
			}
		}
		pc.PCLoc();
	}

	/**
	 * Save map.
	 *
	 * @param map the map
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void saveMap(map map) throws IOException {
		File file = new File("coms309.txt");
		if (!file.exists())
			file.createNewFile();

		FileWriter fw = new FileWriter(file.getAbsolutePath());
		BufferedWriter bw = new BufferedWriter(fw);

		for (int i = 0; i < map.getCol(); i++) {
			for (int j = 0; j < map.getRow(); j++) {
				bw.write(map.getRowCol(i, j));
			}
			bw.newLine();
		}

		System.out.println(file.getAbsolutePath());
		bw.close();
	}

	/**
	 * Load map.
	 *
	 * @return the map
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public map loadMap() throws IOException {
		ArrayList<String> mapload = new ArrayList();
		File file = new File("coms309.txt");
		if (!file.exists())
			file.createNewFile();

		FileReader in = new FileReader(file.getAbsolutePath());
		BufferedReader br = new BufferedReader(in);
		String line = new String();
		while (line != null) {
			line = br.readLine();
			if (line != null)
				mapload.add(line);
		}

		row = mapload.get(0).length();
		col = mapload.size();
		map = new mapNode[this.row][this.col];

		for (int i = 0; i < mapload.size(); i++) {
			for (int j = 0; j < mapload.get(0).length(); j++) {
				setRowCol(i, j, mapload.get(i).charAt(j));
			}
		}
		return this;
	}

	/**
	 * Gets the NPC on node.
	 *
	 * @param row the row
	 * @param col the col
	 * @return the NPC on node
	 */
	public NPC getNPCOnNode(int row, int col){
		for(int i=0; i<main.npcs.size();i++){
			if(main.npcs.get(i).getRow()==row&&main.npcs.get(i).getCol()==col)
				return main.npcs.get(i);
		}
		return null;
	}

	/**
	 * Checks if is on bus stop.
	 *
	 * @return true, if is on bus stop
	 */
	public boolean isOnBusStop() {
		return onBusStop;
	}

	/**
	 * Sets the on bus stop.
	 *
	 * @param onBusStop the new on bus stop
	 */
	public void setOnBusStop(boolean onBusStop) {
		this.onBusStop = onBusStop;
	}

	/**
	 * Checks if is on edge door.
	 *
	 * @return true, if is on edge door
	 */
	public boolean isOnEdgeDoor() {
		return onEdgeDoor;
	}

	/**
	 * Sets the on edge door.
	 *
	 * @param onEdgeDoor the new on edge door
	 */
	public void setOnEdgeDoor(boolean onEdgeDoor) {
		this.onEdgeDoor = onEdgeDoor;
	}

	/**
	 * Checks if is on road.
	 *
	 * @return true, if is on road
	 */
	public boolean isOnRoad() {
		return onRoad;
	}

	/**
	 * Sets the on road.
	 *
	 * @param onRoad the new on road
	 */
	public void setOnRoad(boolean onRoad) {
		this.onRoad = onRoad;
	}

	/**
	 * Checks if is bully on side walk.
	 *
	 * @return true, if is bully on side walk
	 */
	public boolean isBullyOnSideWalk() {
		return bullyOnSideWalk;
	}

	/**
	 * Sets the bully on side walk.
	 *
	 * @param bullyOnSideWalk the new bully on side walk
	 */
	public void setBullyOnSideWalk(boolean bullyOnSideWalk) {
		this.bullyOnSideWalk = bullyOnSideWalk;
	}

	/**
	 * Checks if is bully hurt on grass.
	 *
	 * @return true, if is bully hurt on grass
	 */
	public boolean isBullyHurtOnGrass() {
		return bullyHurtOnGrass;
	}

	/**
	 * Sets the bully hurt on grass.
	 *
	 * @param bullyHurtOnGrass the new bully hurt on grass
	 */
	public void setBullyHurtOnGrass(boolean bullyHurtOnGrass) {
		this.bullyHurtOnGrass = bullyHurtOnGrass;
	}

	/**
	 * Checks if is guard left.
	 *
	 * @return true, if is guard left
	 */
	public boolean isGuardLeft() {
		return guardLeft;
	}

	/**
	 * Sets the guard left.
	 *
	 * @param guardLeft the new guard left
	 */
	public void setGuardLeft(boolean guardLeft) {
		this.guardLeft = guardLeft;
	}

	/**
	 * Checks if is guard hurt on grass.
	 *
	 * @return true, if is guard hurt on grass
	 */
	public boolean isGuardHurtOnGrass() {
		return guardHurtOnGrass;
	}

	/**
	 * Sets the guard hurt on grass.
	 *
	 * @param guardHurtOnGrass the new guard hurt on grass
	 */
	public void setGuardHurtOnGrass(boolean guardHurtOnGrass) {
		this.guardHurtOnGrass = guardHurtOnGrass;
	}

	/**
	 * Checks if is student hurt.
	 *
	 * @return true, if is student hurt
	 */
	public boolean isStudentHurt() {
		return studentHurt;
	}

	/**
	 * Sets the student hurt.
	 *
	 * @param studentHurt the new student hurt
	 */
	public void setStudentHurt(boolean studentHurt) {
		this.studentHurt = studentHurt;
	}

}
