/**
 * 
 * @author Grant Duncan
 *
 */
package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class map implements java.io.Serializable {

	/*
	 * '*' denote the outer edge '0' denotes a building '^' denotes a grass '/'
	 * denotes a door
	 */

	// private char[][] map;
	private mapNode[][] map;
	private int row;
	private int col;
	public boolean onDoor = false;
	private ArrayList<int[]> doors = new ArrayList();
	private int numChange = 0;
	private String directionName = null;
	private boolean stayLeft= false;
	private boolean onLeft = false;
	private int alternateFeet =0;
	private boolean upHeld =false;
	private boolean onGrass = false;
	public map() {

	}






	public boolean isOnGrass() {
		return onGrass;
	}






	public void setOnGrass(boolean onGrass) {
		this.onGrass = onGrass;
	}






	public boolean getUpHeld() {
		return upHeld;
	}
	public void setUpHeld(boolean upHeld) {
		this.upHeld = upHeld;
	}
	public int getAlternateFeet() {
		return alternateFeet;
	}
	public void setAlternateFeet(int alternateFeet) {
		this.alternateFeet = alternateFeet;
	}
	public boolean getOnLeft() {
		return onLeft;
	}
	public void setOnLeft(boolean onLeft) {
		this.onLeft = onLeft;
	}
	public boolean getStayLeft() {
		return stayLeft;
	}

	public void setStayLeft(boolean stayLeft) {
		this.stayLeft = stayLeft;
	}

	public String getdirectionName() {
		return directionName;
	}

	public void setdirectionName(String name) {
		directionName = name;
	}

	public int getnumChange() {
		return numChange;
	}

	public void setnumChange(int value) {
		numChange = value;
	}

	public void setOnDoor(boolean value) {
		onDoor = value;
	}

	public boolean getOnDoor() {
		return onDoor;
	}

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

	public void setRowCol(int row, int col, char c) {
		map[row][col].setSymb(c);
	}

	public char getRowCol(int row, int col) {
		return map[row][col].getSymbol();
	}

	public boolean isWalkable(int row, int col) {
		return map[row][col].isWalkable();
	}

	public boolean isStudentWalkable(int row, int col) {
		return map[row][col].isStudentWalkable();
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

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

	public void renderMap(map map) throws IOException {

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(map.getRowCol(i, j) + " ");
			}
			System.out.print("\n");
		}
	}

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

}
