package gameMain;



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

/**
 * The Class MainFrame.
 */
public class MainFrame extends JFrame implements KeyListener {

	/** The back. */
	private static GraphicalStuff back;
	
	/** The inventory. */
	private static Inventory inventory;
	
	/** The action. */
	private static boolean action = false;
	
	/** The pc. */
	public static PC pc;
	
	/** The map. */
	private static map map;
	
	/** The finished sending. */
	private static boolean finishedSending = true;
	
	/** The pc X. */
	private static int pcX;
	
	/** The finish update. */
	private static boolean finishUpdate;
	
	/** The pc Y. */
	private static int pcY;
	
	/** The last tile. */
	private char lastTile;
	
	/** The main frame. */
	private static JFrame mainFrame;
	
	/** The statistics panel. */
	private static Statistics statisticsPanel;
	
	/** The quests. */
	private static QuestBox quests;


	/** The sprite movement. */
	private  static SpriteMovement spriteMovement;

	/** The name. */
	private static JLabel name;
	
	/** The is game over. */
	private static boolean isGameOver = false;
	
	/** The position. */
	private static JPanel position = new JPanel(new GridLayout(1, 1));
	
	/** The statistics. */
	private static JPanel statistics;
	
	/** The inventory panel. */
	private static JPanel inventoryPanel;
	
	/** The quest panel. */
	private static JPanel questPanel;
	
	/** The chat panel. */
	private static JPanel chatPanel = new JPanel(new GridLayout(2, 1));
	
	/** The chat box. */
	private static ChatBox chatBox;
	
	/** The timer. */
	Timer timer;
	
	/** The time panel. */
	private static JPanel timePanel =  new JPanel();
	
	/** The time name. */
	private static JLabel timeName = new JLabel();
	
	/** The seconds. */
	private  static long seconds=0;
	
	/** The minutes. */
	private static long minutes =0;
	
	/** The hours. */
	private static long hours = 0;
	
	/** The time. */
	private static long time =0;
	
	/** The teleport. */
	private static JPanel  teleport = new JPanel();
	
	/** The tele. */
	private static JLabel tele = new JLabel("Press t for teleport");
	
	/** The npcs. */
	// dummy list of npcs
	public static ArrayList<NPC> npcs = new ArrayList<>();

	/** The quest. */
	private static Quests quest;
	
	/** The cool down in millis. */
	private long coolDownInMillis =50;
	
	/** The rotate. */
	private static long rotate = 0;
	
	/** The x. */
	private int x=0;
	
	/** The is held. */
	private static boolean isHeld;
	
	/** The time class. */
	private static Time timeClass;

/** The key code. */
private static int  keyCode;
	
	

	/**
	 * Instantiates a new main frame.
	 *
	 * @param map the map
	 * @param pc the pc
	 * @param pcX the pc X
	 * @param pcY the pc Y
	 * @param npcs the npcs
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InterruptedException the interrupted exception
	 */
	public MainFrame(map map, PC pc, int pcX, int pcY,ArrayList<NPC> npcs) throws IOException, InterruptedException {

		mainFrame = new JFrame();
		name = new JLabel(" x: " + pc.getRow() + "y: " + pc.getCol());
		timeClass= new Time(800,0,0);
		this.map = map;
		this.pc = pc;
		this.pcX = pcX;
		this.pcY = pcY;

		this.npcs = npcs;



		keyCode=0;



	
		quest = new Quests(pc);
		quests = new QuestBox(pc);
		inventory = new Inventory(pc);

		statisticsPanel = new Statistics(pc);
		chatBox = new ChatBox(pc);
		questPanel = quests.returnQuests();
		statistics = statisticsPanel.returnStatistics();
		inventoryPanel = inventory.returnInventory();
		spriteMovement= new SpriteMovement();
		back = new GraphicalStuff(map, pc, pc.getRow(), pc.getCol(),timeClass);
		back.updateImage(map, pc.getRow(),pc.getCol());
		mainFrame.setLayout(null);
		mainFrame.setSize(900, 780);
		mainFrame.setLocationRelativeTo(null); // center the screen
		mainFrame.setAlwaysOnTop(true);
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.addKeyListener(this);

		back.setDoubleBuffered(true);
		back.setBounds(250, 0, 650, 550);

		mainFrame.add(back);
		//showTeleport();
		showPosition();
		setupQuestsPanel();
		setupInventoryPanel();
		setupStatisticsPanel();
		setupChatPanel();
		showTime();
		
		mainFrame.setVisible(true);

		mainFrame.setFocusable(true);
		mainFrame.setIgnoreRepaint(true);



		



		this.npcs = npcs;

		




		//runGame();


		runGame2();

		//showPosition();



		//showPosition();



	}

	/**
	 * Run game.
	 */
	public static void runGame() {
		
		moveNPC npcMove;

		double time = 0;
		while (true) {
			int i=0;
			while (!getGameOver()) {
				
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
						//map.renderPCView(map, pc);
						////System.out.println("\n"+i);
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
				//System.out.println("\n"+i);
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

	/**
	 * Run game 2.
	 *
	 * @throws InterruptedException the interrupted exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void runGame2() throws InterruptedException, IOException {
//		long //startTme = System.currentTimeMillis() % 1000;
		long startTime = System.currentTimeMillis(); 
		long startUpdateTime = System.currentTimeMillis();
//		long //endTime = 0;

		long duration = 0;
				 
		//double time = 0; 
		rotate=startTime;
		moveNPC npcMove;
		/*
			Runnable runGame = new Runnable() {
				public void run() {
					try {
						back.render(map, pc.getRow(), pc.getCol());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			*/
		while (!getGameOver()) {
			if(pc.gameOver()){
				System.exit(0);
			}
			while(!action) {
				//Thread.yield();
				Thread.sleep(10);
				//startTme = System.currentTimeMillis() % 1000;
				long currTime = System.currentTimeMillis()-startTime;
				if(currTime>500){
					startTime=System.currentTimeMillis();
					
					npcMove=new moveNPC(map, pc, npcs, 1);
				}
				try {
					back.render(map, pc.getRow(), pc.getCol());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//runGame.run();
				//timeClass.updateTime();
			
				//endTime = System.currentTimeMillis() % 1000;
				//System.out.println("render"+//endTime +" "  +//startTme);
				
				quest.checkQuests();
				refreshStatistics();
				refreshPostion();
				//refreshChatPanel();
				refreshInventoryPanel();
				refreshQuestsPanel();
				refreshTime();
				mainFrame.revalidate();
				//mainFrame.repaint();
				//startTme = System.currentTimeMillis() % 1000;
				
				Runnable myrunnable = new Runnable() {
				    public void run() {
				    	updateNPCPositions();  
				    	}
				};
				long updateTime = System.currentTimeMillis()-startUpdateTime;
				if(updateTime>1000){
					startUpdateTime=System.currentTimeMillis();
					new Thread(myrunnable).start();
				}
				
				Runnable runTime = new Runnable() {
					public void run() {
						updateTime();
					}
				};
				new Thread(runTime).start();
				//endTime = System.currentTimeMillis() % 1000;
				//System.out.println("updateNPC"+(//endTime - //startTme));
				//ERROR//startTme = System.currentTimeMillis() % 1000;
				//ERROR//new Thread(myrunnable).start();
				//endTime = System.currentTimeMillis() % 1000;
				//System.out.println("runthread"+(//endTime - //startTme));
				//startTme = System.currentTimeMillis() % 1000;
			}
			//endTime = System.currentTimeMillis() % 1000;
			//System.out.println("actionLoop"+(//endTime - //startTme));
			//startTme = System.currentTimeMillis() % 1000;
			//timeClass.updateTime();
			//endTime = System.currentTimeMillis() % 1000;
			//System.out.println("updateTime"+(//endTime - //startTme));
			//startTme = System.currentTimeMillis() % 1000;
			spriteMovement.updatePosition(keyCode, map, pc,  back ,mainFrame,  action);
			//endTime = System.currentTimeMillis() % 1000;
			//System.out.println("updatePostion"+(//endTime - //startTme));
			//startTme = System.currentTimeMillis() % 1000;
			refreshStatistics();
			//endTime = System.currentTimeMillis() % 1000;
			//System.out.println("stats"+(//endTime - //startTme));
			//startTme = System.currentTimeMillis() % 1000;
			refreshPostion();
			//endTime = System.currentTimeMillis() % 1000;
			//System.out.println("position" +(//endTime - //startTme));
			//startTme = System.currentTimeMillis() % 1000;
			refreshChatPanel();
			//endTime = System.currentTimeMillis() % 1000;
			//System.out.println("chat" +(//endTime - //startTme));
			//startTme = System.currentTimeMillis() % 1000;
			refreshInventoryPanel();
			//endTime = System.currentTimeMillis() % 1000;
			//System.out.println("inventory" +(//endTime - //startTme));
			//startTme = System.currentTimeMillis() % 1000;
			setupQuestsPanel();
			//endTime = System.currentTimeMillis() % 1000;
			//System.out.println("quests" +(//endTime - //startTme));
			//startTme = System.currentTimeMillis() % 1000;
			refreshTime();
			//endTime = System.currentTimeMillis() % 1000;
			//System.out.println("refreshtime" +(//endTime - //startTme));
			//startTme = System.currentTimeMillis() % 1000;
			quest.checkQuests();
			//endTime = System.currentTimeMillis() % 1000;
			//System.out.println("checkquests" +(//endTime - //startTme));
			//startTme = System.currentTimeMillis() % 1000;
			mainFrame.revalidate();
			//endTime = System.currentTimeMillis() % 1000;
			//System.out.println("revalidate" +(//endTime - //startTme));
			//startTme = System.currentTimeMillis() % 1000;
			mainFrame.repaint();
			//endTime = System.currentTimeMillis() % 1000;
			//System.out.println("repaint" +(//endTime - //startTme));
			
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

	// //System.out.println(pc.getRow() + " "+ pc.getCol());
	// back.render(map, pc.getRow(), pc.getCol());
	// Inventory.updateInventory();
	// SStatistics.updateStatistics();
	// action = false;
	// mainFrame.setVisible(true);

	// showPosition();
	// //System.out.println(statisticsPanel.s);
	// mainFrame.remove(statistics);

	// mainFrame.repaint();
	// statisticsPanel = new Statistics();
	// Statistics.updateStatistics();

	// statistics=statisticsPanel.returnStatistics();
	// setupStatisticsPanel();
	// mainFrame.add(statistics);
	// mainFrame.repaint();
	// //System.out.println(pc.getRow() + " "+ pc.getCol());
	// back.render(map, pc.getRow(), pc.getCol());
	// Inventory.updateInventory();
	// SStatistics.updateStatistics();
	// action = false;
	// mainFrame.setVisible(true);

	/**
		 * Update NPC positions.
		 */
		// back.fun(map, pc.getRow(), pc.getCol());
	public static void updateNPCPositions(){
		if(finishedSending){
			finishedSending = false;
			int i = 0;
			int type = 0;
			for(NPC n: npcs){
				if(n.getSymbol()==mapNode.BULLY){
					type = 1;
				}
				else if(n.getSymbol() == mapNode.SECURITY){
					type = 2;
				}
				else{
					type = 0;
				}
				
				new PHPConnect().sendAndReceive("update NPCs set CoordX = " +n.getRow()+",Coordy = " + n.getCol() + ",Image = " + type + " where ID = " + i, null);
				i++;
			}
		}
		finishedSending = true;
	}
	
	/**
	 * Update time.
	 */
	public static void updateTime() {
		timeClass.updateTime();
	}
	
	/**
	 * Show teleport.
	 */
	public static void showTeleport(){
		
		teleport.add(tele);
		teleport.setBounds(800,100, 200, 200);
		
		mainFrame.add(teleport);
		
	}
	
	/**
	 * Show time.
	 */
	public static void showTime() {
/*
		if(seconds>10) {
			seconds=0;
			minutes++;
		}
		if(minutes>59) {
			minutes=0;
			hours+=100;
		}
		*/
		timeClass.calculateTime();
		timeName = new JLabel("Week " + timeClass.getWeek()+ " : " +timeClass.getNameDate() + " : " + timeClass.getHours()+ " : " + timeClass.getMinutes()+ "   ");
		timePanel.add(timeName);
		timePanel.setBounds(50, 100, 180, 50);

		mainFrame.add(timePanel);
		

	}
	
	/**
	 * Refresh time.
	 */
	public static void refreshTime(){
		timePanel.removeAll();
		mainFrame.remove(timePanel);
		showTime();
		
		
		mainFrame.revalidate();
	}
	
	/**
	 * Show position.
	 */
	public static void showPosition() {

		name = new JLabel("x: " + pc.getRow() + "y: " + pc.getCol());
		
		position.add(name);
		position.setBounds(80, 0, 150, 50);

		mainFrame.add(position);


	}

	/**
	 * Refresh postion.
	 */
	public static void refreshPostion() {
		position.removeAll();
		mainFrame.remove(position);
		showPosition();
		mainFrame.revalidate();
		// mainFrame.repaint();
	}

	/**
	 * Setup inventory panel.
	 */
	public static void setupInventoryPanel() {
		inventory.updateInventory();
		 inventoryPanel = inventory.returnInventory();
		inventoryPanel.setBounds(380, 550, 250, 200);
		mainFrame.add(inventoryPanel);
	}




	/**
	 * Refresh inventory panel.
	 */
	public static void refreshInventoryPanel() {
		inventoryPanel.removeAll();
        mainFrame.remove(inventoryPanel);
		setupInventoryPanel();
		mainFrame.revalidate();
		
	}
	
	/**
	 * Setup quests panel.
	 */
	public static void setupQuestsPanel() {
		quests.updateQuests();
		questPanel = quests.returnQuests();
		questPanel.setBounds(0, 150, 250, 400);
		mainFrame.add(questPanel);

	}

	/**
	 * Refresh quests panel.
	 */
	public static void refreshQuestsPanel() {
		questPanel.removeAll();
		mainFrame.remove(questPanel);
		setupQuestsPanel();
		questPanel.revalidate();
		mainFrame.revalidate();
		// mainFrame.repaint();
	}
	
	/**
	 * Setup statistics panel.
	 */
	public static void setupStatisticsPanel() {
		statisticsPanel.updateStatistics();
		statistics = statisticsPanel.returnStatistics();
		statistics.setBounds(0, 550, 200, 150);
		mainFrame.add(statistics);

	}

	/**
	 * Refresh statistics.
	 */
	public static void refreshStatistics() {
		statistics.removeAll();
		mainFrame.remove(statistics);
		setupStatisticsPanel();
		mainFrame.revalidate();
		// mainFrame.repaint();
	}

	
	/**
	 * Setup chat panel.
	 */
	public static void setupChatPanel() {
		chatBox.updateChat("Player");
		chatPanel = chatBox.returnChat();
		chatPanel.setBounds(650, 550, 250, 200);
		// chatPanel.setFocusable(false);
		mainFrame.add(chatPanel);

	}

	/**
	 * Refresh chat panel.
	 */
	public static void refreshChatPanel() {
		chatPanel.removeAll();
		mainFrame.remove(chatPanel);
		setupChatPanel();
		mainFrame.revalidate();
		mainFrame.requestFocusInWindow();

		// mainFrame.setAutoRequestFocus(true);
	}


	
	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent ke) {
		
		
		
			
		keyCode =ke.getKeyCode();
		action=true;
		
			
		PHPConnect phpc = new PHPConnect();
		Runnable myrunnable = new Runnable() {
		    public void run() {
		    	if(pc.getUsername()!=null){
		    		phpc.sendAndReceive("UPDATE Users SET LastX=" + pc.getRow() + ",LastY=" + pc.getCol() + " WHERE Username='"+pc.getUsername()+"'",
		    				null);
		    		}
		    		else
		    		{
		    			phpc.sendAndReceive("UPDATE Users SET LastX=" + pc.getRow() + ",LastY=" + pc.getCol() + " WHERE Username='b3altena'",
		    					null);
		    		}
		    }
		};

		new Thread(myrunnable).start();
		
		
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

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
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

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {

	}

	/**
	 * Gets the game over.
	 *
	 * @return the game over
	 */
	public static boolean getGameOver() {
		return isGameOver;
	}

	/**
	 * Sets the game over.
	 *
	 * @param isGameOver the new game over
	 */
	public static void setGameOver(boolean isGameOver) {
		MainFrame.isGameOver = isGameOver;
	}

}
