package main;



import java.awt.Color;
import java.awt.Dimension;


import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MainFrame extends JFrame implements KeyListener {

	private static GraphicalStuff back;
	private static Inventory inventory;
	private static boolean action = false;
	private static PC pc;
	private static map map;

	private static int pcX;

	private static int pcY;
	private char lastTile;
	private static JFrame mainFrame;
	private static Statistics statisticsPanel;



	private  static SpriteMovement spriteMovement;

	private static JLabel name;
	
	private static boolean isGameOver = false;
	private static JPanel position = new JPanel(new GridLayout(1, 1));
	private static JPanel statistics;
	private static JPanel inventoryPanel;
	private static JPanel chatPanel = new JPanel(new GridLayout(2, 1));
	private static ChatBox chatBox;
	Timer timer;



	 private long lastPressProcessed = 0;



	// dummy list of npcs
	private static ArrayList<NPC> npcs = new ArrayList<>();

	

private static int  keyCode;
	
	

	public MainFrame(map map, PC pc, int pcX, int pcY,ArrayList<NPC> npcs) throws IOException, InterruptedException {

		mainFrame = new JFrame();
		name = new JLabel(" x: " + pc.getRow() + "y: " + pc.getCol());
		
		this.map = map;
		this.pc = pc;
		this.pcX = pcX;
		this.pcY = pcY;

		this.npcs = npcs;



		keyCode=0;



	


		inventory = new Inventory();

		statisticsPanel = new Statistics(pc);
		chatBox = new ChatBox(pc);
		inventoryPanel = 
		statistics = statisticsPanel.returnStatistics();
		inventoryPanel = inventory.returnInventory();
		spriteMovement= new SpriteMovement();
		back = new GraphicalStuff(map, pc, pc.getRow(), pc.getCol());
		back.updateImage(map, pc.getRow(),pc.getCol());
		mainFrame.setLayout(null);
		mainFrame.setSize(800, 750);
		mainFrame.setLocationRelativeTo(null); // center the screen
		mainFrame.setAlwaysOnTop(true);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.addKeyListener(this);

		back.setDoubleBuffered(true);
		back.setBounds(180, 0, 480, 500);

		mainFrame.add(back);
		showPosition();
		setupInventoryPanel();
		setupStatisticsPanel();
		setupChatPanel();
		
		mainFrame.setVisible(true);

		mainFrame.setFocusable(true);
		mainFrame.setIgnoreRepaint(true);



		



		this.npcs = npcs;

		




		//runGame();


		runGame2();

		//showPosition();



		//showPosition();



	}

	public static void runGame() {
		
		moveNPC npcMove;

		double time = 0;
		while (true) {
			int i=0;
			while (!isGameOver) {
				long startTime = System.currentTimeMillis(); 

				while (!action) {
					long currTime = System.currentTimeMillis()-startTime;
					if(currTime>1000){
						startTime=System.currentTimeMillis();
						
						npcMove=new moveNPC(map, pc, npcs, 1);
						try {
							back.render(map, pc.getRow(), pc.getCol());
						} catch (IOException e) {

							e.printStackTrace();
						}
						map.renderPCView(map, pc);
						System.out.println("\n"+i);
						i++;
						
					}
					
					Thread.yield();
					

				}
				i=0;
//				time = 100 / pc.getSpeed();
//				try {
//					npcMove = new moveNPC(map, pc, npcs, time);
				
//				} catch (IOException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				} // does it get to here?
				refreshStatistics();
				refreshPostion();
				try {
					back.render(map, pc.getRow(), pc.getCol());
				} catch (IOException e) {

					e.printStackTrace();
				}
				action = false;
				i++;
				System.out.println("\n"+i);
			}


		mainFrame.setVisible(true);
		mainFrame.setFocusable(true);
		mainFrame.setIgnoreRepaint(true);
		// dummy bully
		bully bully = new bully(4, 6, map);
		npcs.add(bully);

		runGame();

	}
	}

	public static void runGame2() throws InterruptedException {

		double time = 0;
		moveNPC npcMove;
		
		try {
			back.render(map, pc.getRow(), pc.getCol());
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		while (!isGameOver) {
			long startTime = System.currentTimeMillis(); 
			while(!action) {
				
				
				
				Thread.yield();
				Thread.sleep(16);
				
				try {

					back.render(map, pc.getRow(), pc.getCol());
				} catch (IOException e) {

					e.printStackTrace();
				}
				
				refreshPostion();
				
			}
			spriteMovement.updatePosition(keyCode, map, pc,  back ,mainFrame,  action);
			
			refreshStatistics();
			
			refreshChatPanel();
			refreshInventoryPanel();
			/*
			try {

				back.render(map, pc.getRow(), pc.getCol());
			} catch (IOException e) {

				e.printStackTrace();
			}
			*/
			action=false;

		}
	}


		// mainFrame.remove(position);

		// name = new JLabel("Position x: " + pc.getRow() + " Position y: " +
		// pc.getCol() );
		// position.add(name);
		// position.setBounds(0,0,150,100);
		// mainFrame.add(position);
		// mainFrame.revalidate();
		//
		// back.render(map, pcY, pcY);

		// mainFrame.setVisible(true);

		// back.fun(map, pc.getRow(), pc.getCol());

		// showPosition();

		// showPosition();

	

	// time=100/pc.getSpeed();
	/*
	 * try { moveNPC npcMove = new moveNPC(map,pc,npcs,time); } catch (IOException
	 * e1) { // TODO Auto-generated catch block e1.printStackTrace(); }//does it get
	 * to here?
	 */

	/*
	 * try { back.render(map, pc.getRow(), pc.getCol()); } catch (IOException e) {
	 * 
	 * e.printStackTrace(); } action = false; }
	 * 
	 */

	// mainFrame.remove(position);

	// name = new JLabel("Position x: " + pc.getRow() + " Position y: " +
	// pc.getCol() );
	// position.add(name);
	// position.setBounds(0,0,150,100);
	// mainFrame.add(position);
	// mainFrame.revalidate();
	//
	// back.render(map, pcY, pcY);

	// mainFrame.setVisible(true);

	// showPosition();

	// System.out.println(pc.getRow() + " "+ pc.getCol());
	// back.render(map, pc.getRow(), pc.getCol());
	// Inventory.updateInventory();
	// SStatistics.updateStatistics();
	// action = false;
	// mainFrame.setVisible(true);

	// showPosition();
	// System.out.println(statisticsPanel.s);
	// mainFrame.remove(statistics);

	// mainFrame.repaint();
	// statisticsPanel = new Statistics();
	// Statistics.updateStatistics();

	// statistics=statisticsPanel.returnStatistics();
	// setupStatisticsPanel();
	// mainFrame.add(statistics);
	// mainFrame.repaint();
	// System.out.println(pc.getRow() + " "+ pc.getCol());
	// back.render(map, pc.getRow(), pc.getCol());
	// Inventory.updateInventory();
	// SStatistics.updateStatistics();
	// action = false;
	// mainFrame.setVisible(true);

	// back.fun(map, pc.getRow(), pc.getCol());

	public static void showPosition() {

		name = new JLabel("x: " + pc.getRow() + "y: " + pc.getCol());
		
		position.add(name);
		position.setBounds(0, 0, 150, 500);

		mainFrame.add(position);


	}

	public static void refreshPostion() {
		position.removeAll();
		mainFrame.remove(position);
		showPosition();
		mainFrame.revalidate();
		// mainFrame.repaint();
	}

	public static void setupInventoryPanel() {
		inventory.updateInventory();
		 inventoryPanel = inventory.returnInventory();
		inventoryPanel.setBounds(280, 500, 250, 200);
		mainFrame.add(inventoryPanel);
	}




	public static void refreshInventoryPanel() {
		inventoryPanel.removeAll();
		mainFrame.remove(inventoryPanel);
		setupInventoryPanel();
		mainFrame.revalidate();
		
	}

	public static void setupStatisticsPanel() {
		statisticsPanel.updateStatistics();
		statistics = statisticsPanel.returnStatistics();
		statistics.setBounds(0, 500, 250, 200);
		mainFrame.add(statistics);

	}

	public static void refreshStatistics() {
		statistics.removeAll();
		mainFrame.remove(statistics);
		setupStatisticsPanel();
		mainFrame.revalidate();
		// mainFrame.repaint();
	}

	
	public static void setupChatPanel() {
		chatBox.updateChat("Player");
		chatPanel = chatBox.returnChat();
		chatPanel.setBounds(550, 500, 250, 200);
		// chatPanel.setFocusable(false);
		mainFrame.add(chatPanel);

	}

	public static void refreshChatPanel() {
		chatPanel.removeAll();
		mainFrame.remove(chatPanel);
		setupChatPanel();
		mainFrame.revalidate();
		mainFrame.requestFocusInWindow();

		// mainFrame.setAutoRequestFocus(true);
	}


	
	@Override
	public void keyPressed(KeyEvent ke) {
		/*
		 if(System.currentTimeMillis() - lastPressProcessed > 500) {
	            //Do your work here...
			
			 
	            lastPressProcessed = System.currentTimeMillis();
	        }           
		*/
		PHPConnect phpc = new PHPConnect();





		 keyCode =ke.getKeyCode();
			 action=true;

		phpc.sendAndReceive("UPDATE Users SET LastX=" + pc.getRow() + ",LastY=" + pc.getCol() + " WHERE Username='efg'",
				null);
		/*
		switch (ke.getKeyCode()) {

		case KeyEvent.VK_UP: {

			map.setdirectionName("Up");

			map.setRowCol(pc.getRow(), pc.getCol(), pc.getUnderPC());
			if (map.isWalkable(pc.getRow() - 1, pc.getCol())) {
				char mapTile = map.getRowCol(pc.getRow() - 1, pc.getCol());
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
				/*
				 * if(map.getnumChange()<10 &map.getStayLeft()) {
				 * map.setOnLeft(true); map.setnumChange(map.getnumChange()+1);
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
		/*
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



			/*
			 * try {
			 * 
			 * back.render(map, pc.getRow(), pc.getCol()); } catch (IOException
			 * e) {
			 * 
			 * e.printStackTrace(); }
			 */
/*
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

			/*
			 * try { back.render(map, pc.getRow(), pc.getCol()); } catch
			 * (IOException e) {
			 * 
			 * e.printStackTrace(); }
			 */
		//}
	/*
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
			/*
			 * try { back.render(map, pc.getRow(), pc.getCol()); } catch
			 * (IOException e) {
			 * 
			 * e.printStackTrace(); }
			 */
	/*
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
			/*
			 * 
			 * try { back.render(map, pc.getRow(), pc.getCol()); } catch
			 * (IOException e) {
			 * 
			 * e.printStackTrace(); }
			 */
		//}
			//break;

		//}

		
		


	}

	@Override
	public void keyReleased(KeyEvent ke) {
		/*
		 * 
		 * if(ke.getKeyCode()==KeyEvent.VK_UP){ //map.setUpHeld(true);
<<<<<<< HEAD
		 * map.setdirectionName("UpOnce"); map.setRowCol(pc.getRow(),
		 * pc.getCol(), pc.getUnderPC()); if (map.isWalkable(pc.getRow() - 1,
		 * pc.getCol())) { char mapTile = map.getRowCol(pc.getRow()-1,
		 * pc.getCol()); lastTile = mapTile; if (mapTile=='/') {
		 * map.setOnDoor(true); }
		 * 
		 * if(map.getAlternateFeet()==0) { map.setAlternateFeet(1);
		 * 
		 * } else { map.setAlternateFeet(0); }
		 * 
		 * map.renderPCView(map, pc); pc.update(); pc.printStats();
		 * pc.setUnderPC(map.getRowCol(pc.getRow() - 1, pc.getCol())); } else {
		 * if(lastTile=='/') { map.setOnDoor(true); } }
		 * 
		 * if (map.placePC(pc.getRow() - 1, pc.getCol(), pc)) { } else
		 * map.setRowCol(pc.getRow(), pc.getCol(), '@');
		 * 
		 * 
		 * action = true;
		 * 
		 * 
		 * 
		 * try { back.render(map, pc.getRow(), pc.getCol()); } catch
		 * (IOException e) {
=======
		 * map.setdirectionName("UpOnce"); map.setRowCol(pc.getRow(), pc.getCol(),
		 * pc.getUnderPC()); if (map.isWalkable(pc.getRow() - 1, pc.getCol())) { char
		 * mapTile = map.getRowCol(pc.getRow()-1, pc.getCol()); lastTile = mapTile; if
		 * (mapTile=='/') { map.setOnDoor(true); }
		 * 
		 * if(map.getAlternateFeet()==0) { map.setAlternateFeet(1);
		 * 
		 * } else { map.setAlternateFeet(0); }
		 * 
		 * map.renderPCView(map, pc); pc.update(); pc.printStats();
		 * pc.setUnderPC(map.getRowCol(pc.getRow() - 1, pc.getCol())); } else {
		 * if(lastTile=='/') { map.setOnDoor(true); } }
		 * 
		 * if (map.placePC(pc.getRow() - 1, pc.getCol(), pc)) { } else
		 * map.setRowCol(pc.getRow(), pc.getCol(), '@');
		 * 
		 * 
		 * action = true;
		 * 
		 * 
		 * 
		 * try { back.render(map, pc.getRow(), pc.getCol()); } catch (IOException e) {
>>>>>>> refs/remotes/origin/Nathan3
		 * 
		 * e.printStackTrace(); } }
		 * 
		 */

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
