package gameMain;

import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * The Class Spectator.
 *
 * @author EFG
 */
public class Spectator extends JFrame implements KeyListener {
	
	/** The back. */
	private static GraphicalStuff back;
	
	/** The inventory. */
	private static Inventory inventory;
	
	/** The action. */
	private static boolean action = false;
	
	/** The pc. */
	private static PC pc;
	
	/** The map. */
	private static map map;
	
	/** The phpc. */
	private static PHPConnect phpc = new PHPConnect();
	
	/** The pc X. */
	private static int pcX;
	
	/** The last X. */
	private static int lastX;
	
	/** The last Y. */
	private static int lastY;
	
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
	
	/** The is admin. */
	private static boolean isAdmin;
	
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
	private static ArrayList<NPC> npcs = new ArrayList<>();
	
	/** The username. */
	private static String username;
	
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
	 * Instantiates a new spectator.
	 *
	 * @param map the map
	 * @param pc the pc
	 * @param pcX the pc X
	 * @param pcY the pc Y
	 * @param username the username
	 * @param isAdmin the is admin
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InterruptedException the interrupted exception
	 */
	public Spectator(map map, PC pc, int pcX, int pcY, String username, boolean isAdmin) throws IOException, InterruptedException {

		pc.setUsername(username);
		//pc.setMajor(3);
		mainFrame = new JFrame();
		name = new JLabel(" x: " + pc.getRow() + "y: " + pc.getCol());
		timeClass= new Time(800,0,0);
		this.map = map;
		this.pc = pc;
		this.pcX = pcX;
		this.pcY = pcY;
		this.npcs = npcs;
		this.isAdmin = isAdmin;
		this.username = username;

		keyCode=0;


		String fun2 = " WHERE Username='"+pc.getUsername()+"'";
	
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
		showTeleport();
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
		runGame2();
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws InterruptedException the interrupted exception
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		map map = new map(214, 204);
		username = "efg";
		ArrayList<String> fun = phpc.sendAndReceive("woot", null);
		String[] coordinates = null;
		for (String s : fun) {
			if (s.contains(username)) {
				String[] pcLoc = s.split("NPCs");
				String[] info = pcLoc[0].split("=");
				coordinates = info[1].split(",");
			}
		}
		new Spectator(map, new PC(Integer.parseInt(coordinates[0]), Integer.parseInt(coordinates[1]), map), 1, 1,
				username, false);
	}

	/**
	 * Run game 2.
	 *
	 * @throws InterruptedException the interrupted exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void runGame2() throws InterruptedException, IOException {
		double time = 0;
		long startTime = System.currentTimeMillis(); 
		rotate=startTime;
		while (!isGameOver) {
			
			// while(!action) {
			// Thread.yield();
			// Thread.sleep(16);

			long currTime = System.currentTimeMillis()-startTime;
			if(currTime>10000){
				startTime=System.currentTimeMillis();
				refreshChatPanel();
				//npcMove=new moveNPC(map, pc, npcs, 1);
			}
			timeClass.updateTime();
			try {

				back.render(map, pc.getRow(), pc.getCol());
			} catch (IOException e) {

				e.printStackTrace();
			}
			map = new map(214,204);
			// }
			ArrayList<String> fun = phpc.sendAndReceive("woot", null);
			main bob = new main();
			String[] coordinates = null;
			String[] NPCCoordinates = null;
			for (String s : fun) {
				if (s.contains(username)) {
					String[] pcLoc = s.split("NPCs");
					String[] info = pcLoc[0].split("=");
					coordinates = info[1].split(",");
					NPCCoordinates = pcLoc[1].split(",");
					ArrayList<NPC> tempNPC = new ArrayList<NPC>();

					for (int i = 0; i < NPCCoordinates.length; i += 3) {
						int bully = Integer.parseInt(NPCCoordinates[i]);
						int tempRow = Integer.parseInt(NPCCoordinates[i+1]);
						int tempCol = Integer.parseInt(NPCCoordinates[i+2]);
						if(tempRow >204 || tempCol > 204){
							int fine = 01;
						}
						if(bully == 0){
						 map.setRowCol(Integer.parseInt(NPCCoordinates[i+1]), Integer.parseInt(NPCCoordinates[i+2]), mapNode.STUDENT);
						}
						else if(bully == 1){
							map.setRowCol(Integer.parseInt(NPCCoordinates[i+1]), Integer.parseInt(NPCCoordinates[i+2]), mapNode.BULLY);
						}
						else{
							map.setRowCol(Integer.parseInt(NPCCoordinates[i+1]), Integer.parseInt(NPCCoordinates[i+2]), mapNode.SECURITY);
						}
					}
					npcs = tempNPC;
				}
			}
			pcX = Integer.parseInt(coordinates[0]);
			pcY = Integer.parseInt(coordinates[1]);
			int test1 = pcX;
			int test2 = pcY;
			char before = map.getRowCol(pcX, pcY);
			pc.placePC(pcX, pcY);
			if (Integer.parseInt(coordinates[0]) > lastX) {
				//keyCode = KeyEvent.VK_DOWN;
				map.setdirectionName("Down");
			} else if (Integer.parseInt(coordinates[0]) < lastX) {
				//keyCode = KeyEvent.VK_UP;
				map.setdirectionName("Up");
			} else if (Integer.parseInt(coordinates[1]) > lastY) {
				//keyCode = KeyEvent.VK_RIGHT;
				map.setdirectionName("Right");
			} else if (Integer.parseInt(coordinates[1]) < lastY) {
				//keyCode = KeyEvent.VK_LEFT;
				map.setdirectionName("Left");
			}
			//map.setdirectionName("Down");
			char what = pc.getUnderPC();
			if(before == mapNode.GRASS){
				map.setOnGrass(true);
			}
			else{
				map.setOnGrass(false);
			}
			back.updateImage(map, pcX, pcY);
			timeClass.updateTime();
			map.setRowCol(pcX, pcY, '@');
			refreshStatistics();
			refreshPostion();
			//refreshChatPanel();
			refreshInventoryPanel();
			refreshQuestsPanel();
			refreshTime();
			lastX = Integer.parseInt(coordinates[0]);
			lastY = Integer.parseInt(coordinates[1]);
			action = true;
			keyCode = 0;
		}
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
		timeName = new JLabel("Time "+ timeClass.getHours()+ " : " + timeClass.getMinutes()+ "   ");
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
		mainFrame.revalidate();
		// mainFrame.repaint();
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
		if(isAdmin){
			chatBox.updateChat("Admin");
		}
		else{
			chatBox.updateChat("Spectator");
		}
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
	
	/**
	 * Show teleport.
	 */
	public static void showTeleport(){
		
		teleport.add(tele);
		teleport.setBounds(800,100, 200, 200);
		
		mainFrame.add(teleport);
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent ke) {
		// keyCode =ke.getKeyCode();
		// action=true;
		// PHPConnect phpc = new PHPConnect();
		//
		// if(pc.getUsername()!=null){
		// phpc.sendAndReceive("UPDATE Users SET LastX=" + pc.getRow() +
		// ",LastY=" + pc.getCol() + " WHERE Username='"+pc.getUsername()+"'",
		// null);
		// }
		// else
		// {
		// phpc.sendAndReceive("UPDATE Users SET LastX=" + pc.getRow() +
		// ",LastY=" + pc.getCol() + " WHERE Username='efg'",
		// null);
		// }
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent ke) {

	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {

	}

}
