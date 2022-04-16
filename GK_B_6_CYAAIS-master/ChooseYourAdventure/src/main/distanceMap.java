package main;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class distanceMap {
	private map Map;
	private PC pc;
	private NPC npc;
	private int distanceMap[][];
	private boolean touched[][];
	private int rows;
	private int cols;
	private int row;
	private int col;

	public distanceMap(map Map, PC pc) {
		this.Map = Map;
		this.pc = pc;
		rows = Map.getRow();
		cols = Map.getCol();
		distanceMap = new int[rows][cols];
		touched = new boolean[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				distanceMap[i][j] = Integer.MAX_VALUE;
				touched[i][j] = false;
				if (i == 0 || j == 0 || !Map.isWalkable(i, j)) {
					touched[i][j] = true;
					distanceMap[i][j] = -1;
				}
			}
		}
	}

	public distanceMap(map Map, NPC npc) {
		this.Map = Map;
		this.npc = npc;
		rows = Map.getRow();
		cols = Map.getCol();
		distanceMap = new int[rows][cols];
		touched = new boolean[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				distanceMap[i][j] = Integer.MAX_VALUE;
				touched[i][j] = false;
				if (i == 0 || j == 0 || !Map.isWalkable(i, j)) {
					touched[i][j] = true;
					distanceMap[i][j] = -1;
				}
			}
		}
	}

	public distanceMap(map Map, int row, int col) {
		this.Map = Map;
		rows = Map.getRow();
		cols = Map.getCol();
		this.row = row;
		this.col = col;
		distanceMap = new int[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				distanceMap[i][j] = Integer.MAX_VALUE;
				//touched[i][j] = false;
				if (i == 0 || j == 0 || !Map.isWalkable(i, j)) {
					//touched[i][j] = true;
					distanceMap[i][j] = -1;
				}
			}
		}

	}

	public void setNeighbors(int row, int col, int distance, ArrayList<Integer[]> list, ArrayList<Integer[]> list2) {

		// top left
		if (row == 0 && col == 0) {
			if (distanceMap[row][col + 1] > distance) {
				distanceMap[row][col + 1] = distance;
				Integer[] coord = { row, col + 1 };
				// list.add(coord);
				list2.add(coord);

			}
			if (distanceMap[row + 1][col + 1] > distance) {
				distanceMap[row + 1][col + 1] = distance;
				Integer[] coord = { row + 1, col + 1 };
				// list.add(coord);
				list2.add(coord);
			}
			if (distanceMap[row + 1][col] > distance) {
				distanceMap[row + 1][col] = distance;
				Integer[] coord = { row + 1, col };
				// list.add(coord);
				list2.add(coord);
			}

		}
		// top right
		else if (row == 0 && col == cols - 1) {
			// if(touched[row][col-1]&&touched[row+1][col-1]&&touched[row-1][col])
			// return true;
			if (distanceMap[row][col - 1] > distance) {
				distanceMap[row][col - 1] = distance;
				Integer[] coord = { row, col - 1 };
				// list.add(coord);
				list2.add(coord);
			}
			if (distanceMap[row + 1][col - 1] > distance) {
				distanceMap[row + 1][col - 1] = distance;
				Integer[] coord = { row + 1, col - 1 };
				// list.add(coord);
				list2.add(coord);
			}
			if (distanceMap[row - 1][col] > distance) {
				distanceMap[row - 1][col] = distance;
				Integer[] coord = { row - 1, col };
				// list.add(coord);
				list2.add(coord);
			}
		}
		// bottom left
		else if (row == rows - 1 && cols == 0) {
			// if (touched[row - 1][col] && touched[row - 1][col + 1] &&
			// touched[row][col + 1])
			// return true;
			if (distanceMap[row - 1][col] > distance) {
				distanceMap[row - 1][col] = distance;
				Integer[] coord = { row - 1, col };
				// list.add(coord);
				list2.add(coord);
			}
			if (distanceMap[row - 1][col + 1] > distance) {
				distanceMap[row - 1][col + 1] = distance;
				Integer[] coord = { row - 1, col + 1 };
				// list.add(coord);
				list2.add(coord);
			}
			if (distanceMap[row][col + 1] > distance) {
				distanceMap[row][col + 1] = distance;
				Integer[] coord = { row, col + 1 };
				// list.add(coord);
				list2.add(coord);
			}
		}
		// bottom right
		else if (row == rows - 1 && col == cols - 1) {
			// if (touched[row][col - 1] && touched[row - 1][col - 1] &&
			// touched[row - 1][col])
			// return true;
			if (distanceMap[row][col - 1] > distance) {
				distanceMap[row][col - 1] = distance;
				Integer[] coord = { row, col - 1 };
				// list.add(coord);
				list2.add(coord);
			}
			if (distanceMap[row - 1][col - 1] > distance) {
				distanceMap[row - 1][col - 1] = distance;
				Integer[] coord = { row - 1, col - 1 };
				// list.add(coord);
				list2.add(coord);
			}
			if (distanceMap[row - 1][col] > distance) {
				distanceMap[row - 1][col] = distance;
				Integer[] coord = { row - 1, col };
				// list.add(coord);
				list2.add(coord);
			}
		}
		// top
		else if (row == 0) {
			// if (touched[row][col - 1] && touched[row][col + 1] && touched[row
			// + 1][col - 1] && touched[row + 1][col]
			// && touched[row + 1][col + 1])
			// return true;
			if (distanceMap[row][col - 1] > distance) {
				distanceMap[row][col - 1] = distance;
				Integer[] coord = { row, col - 1 };
				// list.add(coord);
				list2.add(coord);
			}
			if (distanceMap[row][col + 1] > distance) {
				distanceMap[row][col + 1] = distance;
				Integer[] coord = { row, col + 1 };
				// list.add(coord);
				list2.add(coord);
			}
			if (distanceMap[row + 1][col - 1] > distance) {
				distanceMap[row + 1][col - 1] = distance;
				Integer[] coord = { row + 1, col - 1 };
				// list.add(coord);
				list2.add(coord);
			}
			if (distanceMap[row + 1][col] > distance) {
				distanceMap[row + 1][col] = distance;
				Integer[] coord = { row + 1, col };
				// list.add(coord);
				list2.add(coord);
			}
			if (distanceMap[row + 1][col + 1] > distance) {
				distanceMap[row + 1][col + 1] = distance;
				Integer[] coord = { row + 1, col + 1 };
				// list.add(coord);
				list2.add(coord);
			}

		}
		// bottom
		else if (row == rows - 1) {
			// if (touched[row][col - 1] && touched[row][col + 1] && touched[row
			// - 1][col - 1] && touched[row - 1][col]
			// && touched[row - 1][col + 1])
			// return true;
			if (distanceMap[row][col - 1] > distance) {
				distanceMap[row][col - 1] = distance;
				Integer[] coord = { row, col - 1 };
				// list.add(coord);
				list2.add(coord);
			}
			if (distanceMap[row][col + 1] > distance) {
				distanceMap[row][col + 1] = distance;
				Integer[] coord = { row, col + 1 };
				// list.add(coord);
				list2.add(coord);
			}
			if (distanceMap[row - 1][col - 1] > distance) {
				distanceMap[row - 1][col - 1] = distance;
				Integer[] coord = { row - 1, col - 1 };
				// list.add(coord);
				list2.add(coord);
			}
			if (distanceMap[row - 1][col] > distance) {
				distanceMap[row - 1][col] = distance;
				Integer[] coord = { row - 1, col };
				// list.add(coord);
				list2.add(coord);
			}
			if (distanceMap[row - 1][col + 1] > distance) {
				distanceMap[row - 1][col + 1] = distance;
				Integer[] coord = { row - 1, col + 1 };
				// list.add(coord);
				list2.add(coord);
			}
		}
		// left
		else if (col == 0) {
			// if (touched[row - 1][col] && touched[row - 1][col + 1] &&
			// touched[row][col + 1] && touched[row + 1][col + 1]
			// && touched[row + 1][col])
			// return true;
			if (distanceMap[row - 1][col] > distance) {
				distanceMap[row - 1][col] = distance;
				Integer[] coord = { row - 1, col };
				// list.add(coord);
				list2.add(coord);
			}
			if (distanceMap[row - 1][col + 1] > distance) {
				distanceMap[row - 1][col + 1] = distance;
				Integer[] coord = { row - 1, col + 1 };
				// list.add(coord);
				list2.add(coord);
			}
			if (distanceMap[row][col + 1] > distance) {
				distanceMap[row][col + 1] = distance;
				Integer[] coord = { row, col + 1 };
				// list.add(coord);
				list2.add(coord);
			}
			if (distanceMap[row + 1][col + 1] > distance) {
				distanceMap[row + 1][col + 1] = distance;
				Integer[] coord = { row + 1, col + 1 };
				// list.add(coord);
				list2.add(coord);
			}
			if (distanceMap[row + 1][col] > distance) {
				distanceMap[row + 1][col] = distance;
				Integer[] coord = { row + 1, col };
				// list.add(coord);
				list2.add(coord);
			}
		}
		// right
		else if (col == cols - 1) {
			// if (touched[row - 1][col] && touched[row + 1][col] && touched[row
			// - 1][col - 1] && touched[row][col - 1]
			// && touched[row + 1][col - 1])
			// return true;
			if (distanceMap[row - 1][col] > distance) {
				distanceMap[row - 1][col] = distance;
				Integer[] coord = { row - 1, col };
				// list.add(coord);
				list2.add(coord);
			}
			if (distanceMap[row + 1][col] > distance) {
				distanceMap[row + 1][col] = distance;
				Integer[] coord = { row + 1, col };
				// list.add(coord);
				list2.add(coord);
			}
			if (distanceMap[row - 1][col - 1] > distance) {
				distanceMap[row - 1][col - 1] = distance;
				Integer[] coord = { row - 1, col - 1 };
				// list.add(coord);
				list2.add(coord);
			}
			if (distanceMap[row][col - 1] > distance) {
				distanceMap[row][col - 1] = distance;
				Integer[] coord = { row, col - 1 };
				// list.add(coord);
				list2.add(coord);
			}
			if (distanceMap[row + 1][col - 1] > distance) {
				distanceMap[row + 1][col - 1] = distance;
				Integer[] coord = { row + 1, col - 1 };
				// list.add(coord);
				list2.add(coord);
			}
		}
		// middle
		else {
			// if (touched[row - 1][col - 1] && touched[row - 1][col] &&
			// touched[row - 1][col + 1] && touched[row][col - 1]
			// && touched[row][col + 1] && touched[row + 1][col - 1] &&
			// touched[row + 1][col]
			// && touched[row + 1][col + 1])
			// return true;
			if (distanceMap[row - 1][col - 1] > distance) {
				distanceMap[row - 1][col - 1] = distance;
				Integer[] coord = { row - 1, col - 1 };
				// list.add(coord);
				list2.add(coord);
			}
			if (distanceMap[row - 1][col] > distance) {
				distanceMap[row - 1][col] = distance;
				Integer[] coord = { row - 1, col };
				// list.add(coord);
				list2.add(coord);
			}
			if (distanceMap[row - 1][col + 1] > distance) {
				distanceMap[row - 1][col + 1] = distance;
				Integer[] coord = { row - 1, col + 1 };
				// list.add(coord);
				list2.add(coord);
			}
			if (distanceMap[row][col - 1] > distance) {
				distanceMap[row][col - 1] = distance;
				Integer[] coord = { row, col - 1 };
				// list.add(coord);
				list2.add(coord);
			}
			if (distanceMap[row][col + 1] > distance) {
				distanceMap[row][col + 1] = distance;
				Integer[] coord = { row, col + 1 };
				// list.add(coord);
				list2.add(coord);
			}
			if (distanceMap[row][col + 1] > distance) {
				distanceMap[row][col + 1] = distance;
				Integer[] coord = { row, col + 1 };
				// list.add(coord);
				list2.add(coord);
			}
			if (distanceMap[row + 1][col - 1] > distance) {
				distanceMap[row + 1][col - 1] = distance;
				Integer[] coord = { row + 1, col - 1 };
				// list.add(coord);
				list2.add(coord);
			}
			if (distanceMap[row + 1][col] > distance) {
				distanceMap[row + 1][col] = distance;
				Integer[] coord = { row + 1, col };
				// list.add(coord);
				list2.add(coord);
			}
			if (distanceMap[row + 1][col + 1] > distance) {
				distanceMap[row + 1][col + 1] = distance;
				Integer[] coord = { row + 1, col + 1 };
				// list.add(coord);
				list2.add(coord);
			}

		}

	}

	public int[][] createMap() {
		distanceMap[row][col] = 0;
		// renderRecur(pc.getRow(), pc.getCol(), 1);
		ArrayList<Integer[]> list = new ArrayList<>();
		//Integer[] coord = { pc.getRow(), pc.getCol() };
		Integer[] coord = { row, col };
		list.add(coord);
		ArrayList<Integer[]> list2 = new ArrayList<>();
		list2 = (ArrayList<Integer[]>) list.clone();
		int k = 1;
		while (!list.isEmpty()) {
			while (!list.isEmpty()) {
				setNeighbors(list.get(0)[0], list.get(0)[1], k, list, list2);
				if (!list.isEmpty())
					list.remove(0);
			}

			list = (ArrayList<Integer[]>) list2.clone();
			list2.clear();

			k++;
		}
		
		for(int i=0; i<distanceMap.length;i++){
			for(int j=0;j<distanceMap[0].length;j++){
				if(distanceMap[i][j]==-1)
					distanceMap[i][j]=Integer.MAX_VALUE;
				if(distanceMap[i][j]==0)
					distanceMap[i][j]=Integer.MAX_VALUE;
			}
		}

		return distanceMap;

	}

	/*
	 * This method creates a distance map from the PC's perspective
	 */
	public int[][] createPCMap() {
		// need to make circles around the pc or npc with distances
		distanceMap[pc.getRow()][pc.getCol()] = 0;
		// renderRecur(pc.getRow(), pc.getCol(), 1);
		ArrayList<Integer[]> list = new ArrayList<>();
		Integer[] coord = { pc.getRow(), pc.getCol() };
		list.add(coord);
		ArrayList<Integer[]> list2 = new ArrayList<>();
		list2 = (ArrayList<Integer[]>) list.clone();
		int k = 1;
		while (!list.isEmpty()) {
			while (!list.isEmpty()) {
				setNeighbors(list.get(0)[0], list.get(0)[1], k, list, list2);
				if (!list.isEmpty())
					list.remove(0);
			}

			list = (ArrayList<Integer[]>) list2.clone();
			list2.clear();

			k++;
		}

		return distanceMap;
	}

	/*
	 * This method creates a distance map from a bully or security guard's
	 * perspective (identical to the PC distance map, except from their
	 * viewpoint)
	 */
	public int[][] createNPCMap() {
		distanceMap[npc.getRow()][npc.getCol()] = 0;
		// renderRecur(pc.getRow(), pc.getCol(), 1);
		ArrayList<Integer[]> list = new ArrayList<>();
		Integer[] coord = { npc.getRow(), npc.getCol() };
		list.add(coord);
		ArrayList<Integer[]> list2 = new ArrayList<>();
		list2 = (ArrayList<Integer[]>) list.clone();
		int k = 1;
		while (!list.isEmpty()) {
			while (!list.isEmpty()) {
				setNeighbors(list.get(0)[0], list.get(0)[1], k, list, list2);
				if (!list.isEmpty())
					list.remove(0);
			}

			list = (ArrayList<Integer[]>) list2.clone();
			list2.clear();

			k++;
		}
//		//set -1s to max value
//		for(int i=0;i<rows;i++){
//			for(int j=0;i<cols;j++){
//				if(distanceMap[i][j]==-1)
//					distanceMap[i][j]=Integer.MAX_VALUE;
//			}
//		}

		return distanceMap;
	}
	/*
	 * This method creates a distance map from a student's perspective. It
	 * should also be used when a bully or security guard is not alerted
	 */

	public int[][] createStudentMap() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (!Map.isStudentWalkable(i, j)) {
					distanceMap[i][j] = -1;
				}
			}
		}
		distanceMap[row][col] = 0;
		// renderRecur(pc.getRow(), pc.getCol(), 1);
		ArrayList<Integer[]> list = new ArrayList<>();
		Integer[] coord = { row, col };
		list.add(coord);
		ArrayList<Integer[]> list2 = new ArrayList<>();
		list2 = (ArrayList<Integer[]>) list.clone();
		int k = 1;
		while (!list.isEmpty()) {
			while (!list.isEmpty()) {
				setNeighbors(list.get(0)[0], list.get(0)[1], k, list, list2);
				if (!list.isEmpty())
					list.remove(0);
			}

			list = (ArrayList<Integer[]>) list2.clone();
			list2.clear();

			k++;
		}
		for(int i=0; i<distanceMap.length;i++){
			for(int j=0;j<distanceMap[0].length;j++){
				if(distanceMap[i][j]==-1)
					distanceMap[i][j]=Integer.MAX_VALUE;
				
			}
		}

		return distanceMap;
	}

	/*
	 * This method creates a distance map from a ghost's perspective. A ghost
	 * does not have limitations on where they can walk. Although there is no
	 * ghost class, this method is useful for determining LOS
	 */
	public int[][] createGhostMap() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				if (i == 0 || j == 0)
					distanceMap[i][j] = -1;
				else
					distanceMap[i][j] = Integer.MAX_VALUE;
			}
		}
		distanceMap[npc.getRow()][npc.getCol()] = 0;
		// renderRecur(pc.getRow(), pc.getCol(), 1);
		ArrayList<Integer[]> list = new ArrayList<>();
		Integer[] coord = { npc.getRow(), npc.getCol() };
		list.add(coord);
		ArrayList<Integer[]> list2 = new ArrayList<>();
		list2 = (ArrayList<Integer[]>) list.clone();
		int k = 1;
		while (!list.isEmpty()) {
			while (!list.isEmpty()) {
				setNeighbors(list.get(0)[0], list.get(0)[1], k, list, list2);
				if (!list.isEmpty())
					list.remove(0);
			}

			list = (ArrayList<Integer[]>) list2.clone();
			list2.clear();

			k++;
		}

		return distanceMap;
	}

	public void renderMap() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				System.out.print(String.format("%1$3s", distanceMap[i][j]));
			}
			System.out.print("\n");
		}
	}
	
	public int getRows(){
		return rows;
	}
	public int getCols(){
		return cols;
	}
	

}