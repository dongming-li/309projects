package main;

import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JFrame;

public class SpriteMovement  {
	private char lastTile;
	
	public SpriteMovement()  {
	
	}

	
	public void updatePosition(int ke, map map , PC pc,GraphicalStuff back , JFrame mainFrame , boolean action) {
		switch (ke) {

		case KeyEvent.VK_UP: {

			map.setdirectionName("Up");

			map.setRowCol(pc.getRow(), pc.getCol(), pc.getUnderPC());
			if (map.isWalkable(pc.getRow() - 1, pc.getCol())) {
				char mapTile = map.getRowCol(pc.getRow() - 1, pc.getCol());
				lastTile = mapTile;
				if (mapTile == '/') {
					map.setOnDoor(true);
					mainFrame.setAlwaysOnTop(false);
				}
				if (mapTile == mapNode.GRASS) {
					map.setOnGrass(true);
				}
				if (map.getnumChange() == 0) {
					map.setnumChange(1);

				} else {
					map.setnumChange(0);
				}
				/*
				 * if(map.getnumChange()<10 &map.getStayLeft()) { map.setOnLeft(true);
				 * map.setnumChange(map.getnumChange()+1); if(map.getnumChange()==9) {
				 * map.setnumChange(0); map.setStayLeft(false);
				 * 
				 * } } else if (map.getnumChange()!=5 & !map.getStayLeft()){
				 * map.setOnLeft(false); if(map.getnumChange()==4) {
				 * 
				 * map.setStayLeft(true);
				 * 
				 * }
				 * 
				 * map.setnumChange(map.getnumChange()+1); }
				 */
				map.renderPCView(map, pc);
				pc.update();
				pc.printStats();
				pc.setUnderPC(map.getRowCol(pc.getRow() - 1, pc.getCol()));
			} else {
				if (lastTile == '/' || lastTile == mapNode.GRASS) {
					if (lastTile == mapNode.DOOR) {
						map.setOnDoor(true);
					}
					if (lastTile == mapNode.GRASS) {
						map.setOnGrass(true);
					}

				}
			}

			if (map.placePC(pc.getRow() - 1, pc.getCol(), pc)) {
			} else
				map.setRowCol(pc.getRow(), pc.getCol(), '@');

			action = true;

			try {
				back.updateImage(map,pc.getRow(),pc.getCol());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*
			try {

				back.render(map, pc.getRow(), pc.getCol());
			} catch (IOException e) {

				e.printStackTrace();
			}
			*/
			/*
			 * try {
			 * 
			 * back.render(map, pc.getRow(), pc.getCol()); } catch (IOException e) {
			 * 
			 * e.printStackTrace(); }
			 */

		}
			break;
		case KeyEvent.VK_RIGHT: {
			map.setdirectionName("Right");
			map.setRowCol(pc.getRow(), pc.getCol(), pc.getUnderPC());
			if (map.isWalkable(pc.getRow(), pc.getCol() + 1)) {
				char mapTile = map.getRowCol(pc.getRow(), pc.getCol() + 1);
				lastTile = mapTile;
				if (mapTile == '/') {
					map.setOnDoor(true);
				}
				if (mapTile == mapNode.GRASS) {
					map.setOnGrass(true);
				}
				if (map.getnumChange() == 0) {
					map.setnumChange(1);

				} else {
					map.setnumChange(0);
				}
				map.renderPCView(map, pc);
				pc.update();
				pc.printStats();
				pc.setUnderPC(map.getRowCol(pc.getRow(), pc.getCol() + 1));

			} else {
				if (lastTile == '/' || lastTile == mapNode.GRASS) {
					if (lastTile == mapNode.DOOR) {
						map.setOnDoor(true);
					}
					if (lastTile == mapNode.GRASS) {
						map.setOnGrass(true);
					}

				}
			}

			if (map.placePC(pc.getRow(), pc.getCol() + 1, pc)) {
			} else
				map.setRowCol(pc.getRow(), pc.getCol(), '@');

			action = true;
			try {
				back.updateImage(map,pc.getRow(),pc.getCol());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*
			try {

				back.render(map, pc.getRow(), pc.getCol());
			} catch (IOException e) {

				e.printStackTrace();
			}
			*/
			/*
			 * try { back.render(map, pc.getRow(), pc.getCol()); } catch (IOException e) {
			 * 
			 * e.printStackTrace(); }
			 */
		}
			break;
		case KeyEvent.VK_LEFT: {
			map.setdirectionName("Left");
			map.setRowCol(pc.getRow(), pc.getCol(), pc.getUnderPC());
			if (map.isWalkable(pc.getRow(), pc.getCol() - 1)) {

				char mapTile = map.getRowCol(pc.getRow(), pc.getCol() - 1);

				lastTile = mapTile;

				if (mapTile == '/') {
					map.setOnDoor(true);
				}
				if (mapTile == mapNode.GRASS) {
					map.setOnGrass(true);
				}

				if (map.getnumChange() == 0) {
					map.setnumChange(1);

				} else {
					map.setnumChange(0);
				}
				map.renderPCView(map, pc);
				pc.update();
				pc.printStats();
				pc.setUnderPC(map.getRowCol(pc.getRow(), pc.getCol() - 1));

			}

			else {
				if (lastTile == '/' || lastTile == mapNode.GRASS) {
					if (lastTile == mapNode.DOOR) {
						map.setOnDoor(true);
					}
					if (lastTile == mapNode.GRASS) {
						map.setOnGrass(true);
					}

				}
			}

			if (map.placePC(pc.getRow(), pc.getCol() - 1, pc)) {
			} else
				map.setRowCol(pc.getRow(), pc.getCol(), '@');

			action = true;
			try {
				back.updateImage(map,pc.getRow(),pc.getCol());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*
			try {

				back.render(map, pc.getRow(), pc.getCol());
			} catch (IOException e) {

				e.printStackTrace();
			}
			*/
			/*
			 * try { back.render(map, pc.getRow(), pc.getCol()); } catch (IOException e) {
			 * 
			 * e.printStackTrace(); }
			 */
		}
			break;
		case KeyEvent.VK_DOWN: {
			map.setdirectionName("Down");
			map.setRowCol(pc.getRow(), pc.getCol(), pc.getUnderPC());
			if (map.isWalkable(pc.getRow() + 1, pc.getCol())) {
				char mapTile = map.getRowCol(pc.getRow() + 1, pc.getCol());
				lastTile = mapTile;
				if (mapTile == '/') {
					map.setOnDoor(true);
				}
				if (mapTile == mapNode.GRASS) {
					map.setOnGrass(true);
				}
				if (map.getnumChange() == 0) {
					map.setnumChange(1);

				} else {
					map.setnumChange(0);
				}

				map.renderPCView(map, pc);
				pc.update();
				pc.printStats();
				pc.setUnderPC(map.getRowCol(pc.getRow() + 1, pc.getCol()));

			} else {
				if (lastTile == '/' || lastTile == mapNode.GRASS) {
					if (lastTile == mapNode.DOOR) {
						map.setOnDoor(true);
					}
					if (lastTile == mapNode.GRASS) {
						map.setOnGrass(true);
					}

				}
			}
			if (map.placePC(pc.getRow() + 1, pc.getCol(), pc)) {
			} else
				map.setRowCol(pc.getRow(), pc.getCol(), '@');

			action = true;
			try {
				back.updateImage(map,pc.getRow(),pc.getCol());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*
			try {

				back.render(map, pc.getRow(), pc.getCol());
			} catch (IOException e) {

				e.printStackTrace();
			}
			*/
			/*
			 * 
			 * try { back.render(map, pc.getRow(), pc.getCol()); } catch (IOException e) {
			 * 
			 * e.printStackTrace(); }
			 */
		}
			break;

		}

		
	}
	
	
	
}
