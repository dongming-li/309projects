package gameMain;

import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.JFrame;

import main.MainFrame;

/**
 * The Class SpriteMovement.
 */
public class SpriteMovement {
	
	/** The last tile. */
	private char lastTile;
	
	/** The is open. */
	public boolean isOpen;
	
	/** The mini. */
	private MiniMap mini;

	/**
	 * Instantiates a new sprite movement.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public SpriteMovement() throws IOException {
		lastTile='0';
		isOpen = false;
		mini = new MiniMap();
	}

	/**
	 * Update position.
	 *
	 * @param ke the ke
	 * @param map the map
	 * @param pc the pc
	 * @param back the back
	 * @param mainFrame the main frame
	 * @param action the action
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void updatePosition(int ke, map map, PC pc, GraphicalStuff back, JFrame mainFrame, boolean action)
			throws IOException {
		switch (ke) {

		case KeyEvent.VK_UP: {

			map.setdirectionName("Up");
			if (map.isPCWalkable(pc.getRow() - 1, pc.getCol())) {
				map.setRowCol(pc.getRow(), pc.getCol(), pc.getUnderPC());
				if (map.isPCWalkable(pc.getRow() - 1, pc.getCol())) {
					char mapTile = map.getRowCol(pc.getRow() - 1, pc.getCol());
					lastTile = mapTile;
					if (mapTile == '/') {
						map.setOnDoor(true);
						mainFrame.setAlwaysOnTop(false);
					}
					if (mapTile == mapNode.GRASS) {
						map.setOnGrass(true);
					}
					if(mapTile == mapNode.BUSSTOP) {
						map.setOnBusStop(true);
					}
					if(mapTile==mapNode.EDGE_DOOR) {
						map.setOnEdgeDoor(true);
					}
					if(mapTile==mapNode.ROAD) {
						map.setOnRoad(true);
					}
					if (map.getnumChange() == 0) {
						map.setnumChange(1);

					} else {
						map.setnumChange(0);
					}
					/*
					 * if(map.getnumChange()<10 &map.getStayLeft()) {
					 * map.setOnLeft(true);
					 * map.setnumChange(map.getnumChange()+1);
					 * if(map.getnumChange()==9) { map.setnumChange(0);
					 * map.setStayLeft(false);
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
					//map.renderPCView(map, pc);
					pc.update();
					//pc.printStats();
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
			}
			if (lastTile == '/' || lastTile == mapNode.GRASS) {
				if (lastTile == mapNode.DOOR) {
					map.setOnDoor(true);
				}
				if (lastTile == mapNode.GRASS) {
					map.setOnGrass(true);
				}
			}
			action = true;

			try {
				back.updateImage(map, pc.getRow(), pc.getCol());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*
			 * try {
			 * 
			 * back.render(map, pc.getRow(), pc.getCol()); } catch (IOException
			 * e) {
			 * 
			 * e.printStackTrace(); }
			 */

		}
			break;
		case KeyEvent.VK_RIGHT: {
			map.setdirectionName("Right");
			if (map.isPCWalkable(pc.getRow(), pc.getCol() + 1)) {
				map.setRowCol(pc.getRow(), pc.getCol(), pc.getUnderPC());
				if (map.isPCWalkable(pc.getRow(), pc.getCol() + 1)) {
					char mapTile = map.getRowCol(pc.getRow(), pc.getCol() + 1);
					lastTile = mapTile;
					if (mapTile == '/') {
						map.setOnDoor(true);
					}
					if (mapTile == mapNode.GRASS) {
						map.setOnGrass(true);
					}
					if(mapTile == mapNode.BUSSTOP) {
						map.setOnBusStop(true);
					}
					if(mapTile==mapNode.EDGE_DOOR) {
						map.setOnEdgeDoor(true);
					}
					if(mapTile==mapNode.ROAD) {
						map.setOnRoad(true);
					}
					if (map.getnumChange() == 0) {
						map.setnumChange(1);

					} else {
						map.setnumChange(0);
					}
					//map.renderPCView(map, pc);
					pc.update();
					//pc.printStats();
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
			}
			if (lastTile == '/' || lastTile == mapNode.GRASS) {
				if (lastTile == mapNode.DOOR) {
					map.setOnDoor(true);
				}
				if (lastTile == mapNode.GRASS) {
					map.setOnGrass(true);
				}
			}
			action = true;
			try {
				back.updateImage(map, pc.getRow(), pc.getCol());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*
			 * try { back.render(map, pc.getRow(), pc.getCol()); } catch
			 * (IOException e) {
			 * 
			 * e.printStackTrace(); }
			 */
		}
			break;
		case KeyEvent.VK_LEFT: {
			map.setdirectionName("Left");
			if (map.isPCWalkable(pc.getRow(), pc.getCol() - 1)) {
				map.setRowCol(pc.getRow(), pc.getCol(), pc.getUnderPC());
				if (map.isPCWalkable(pc.getRow(), pc.getCol() - 1)) {

					char mapTile = map.getRowCol(pc.getRow(), pc.getCol() - 1);

					lastTile = mapTile;

					if (mapTile == '/') {
						map.setOnDoor(true);
					}
					if (mapTile == mapNode.GRASS) {
						map.setOnGrass(true);
					}
					if(mapTile == mapNode.BUSSTOP) {
						map.setOnBusStop(true);
					}
					if(mapTile==mapNode.EDGE_DOOR) {
						map.setOnEdgeDoor(true);
					}
					if(mapTile==mapNode.ROAD) {
						map.setOnRoad(true);
					}

					if (map.getnumChange() == 0) {
						map.setnumChange(1);

					} else {
						map.setnumChange(0);
					}
					//map.renderPCView(map, pc);
					pc.update();
					//pc.printStats();
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
			}
			if (lastTile == '/' || lastTile == mapNode.GRASS) {
				if (lastTile == mapNode.DOOR) {
					map.setOnDoor(true);
				}
				if (lastTile == mapNode.GRASS) {
					map.setOnGrass(true);
				}
			}
			action = true;
			try {
				back.updateImage(map, pc.getRow(), pc.getCol());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*
			 * try { back.render(map, pc.getRow(), pc.getCol()); } catch
			 * (IOException e) {
			 * 
			 * e.printStackTrace(); }
			 */
		}
			break;
		case KeyEvent.VK_DOWN: {
			map.setdirectionName("Down");
			if (map.isPCWalkable(pc.getRow() + 1, pc.getCol())) {
				map.setRowCol(pc.getRow(), pc.getCol(), pc.getUnderPC());
				if (map.isPCWalkable(pc.getRow() + 1, pc.getCol())) {
					char mapTile = map.getRowCol(pc.getRow() + 1, pc.getCol());
					lastTile = mapTile;
					if (mapTile == '/') {
						map.setOnDoor(true);
					}
					if (mapTile == mapNode.GRASS) {
						map.setOnGrass(true);
					}
					if(mapTile == mapNode.BUSSTOP) {
						map.setOnBusStop(true);
					}
					if(mapTile==mapNode.EDGE_DOOR) {
						map.setOnEdgeDoor(true);
					}
					if(mapTile==mapNode.ROAD) {
						map.setOnRoad(true);
					}
					if (map.getnumChange() == 0) {
						map.setnumChange(1);

					} else {
						map.setnumChange(0);
					}

					//map.renderPCView(map, pc);
					pc.update();
					//pc.printStats();
					pc.setUnderPC(map.getRowCol(pc.getRow() + 1, pc.getCol()));

				} 
				else {
					System.out.println(lastTile);
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
			}
			if (lastTile == '/' || lastTile == mapNode.GRASS) {
				if (lastTile == mapNode.DOOR) {
					map.setOnDoor(true);
				}
				if (lastTile == mapNode.GRASS) {
					map.setOnGrass(true);
				}
			}
			action = true;
			try {
				back.updateImage(map, pc.getRow(), pc.getCol());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/*
			 * 
			 * try { back.render(map, pc.getRow(), pc.getCol()); } catch
			 * (IOException e) {
			 * 
			 * e.printStackTrace(); }
			 */
		}
			break;
		case KeyEvent.VK_T: {
			//Teleport teleport = new Teleport(map, pc, back);

		}
			break;
		case KeyEvent.VK_M: {

			mini.createMap();

		}
			break;
		case KeyEvent.VK_F:{
			pc.attack(map.getdirectionName(),map);
			if (lastTile == '/' || lastTile == mapNode.GRASS) {
				if (lastTile == mapNode.DOOR) {
					map.setOnDoor(true);
				}
				if (lastTile == mapNode.GRASS) {
					map.setOnGrass(true);
				}
			}
			
			if(map.getdirectionName()=="Up") {
			try {
				back.updateImage(map,pc.getRow()-1,pc.getCol());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			else if(map.getdirectionName().equals("Down")){
				try {
					back.updateImage(map,pc.getRow()+1,pc.getCol());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else if(map.getdirectionName().equals("Left")){
				
			try {
				back.updateImage(map,pc.getRow(),pc.getCol()-1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
			else if(map.getdirectionName().equals("Right")){
				try {
					back.updateImage(map,pc.getRow(),pc.getCol()+1);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			
			}
		break;
		}

	}

}
