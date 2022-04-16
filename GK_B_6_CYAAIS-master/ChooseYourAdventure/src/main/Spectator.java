package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Spectator extends JFrame
{
	
	
    private static GraphicalStuff back;
    private static Inventory inventoryPanel;
    private static boolean action =false;
    private PC pc;
	private static map map;
	private Quests quest;
	private int pcX;
	private static int pcY;
	private char lastTile;
	private static JFrame mainFrame;
	private static Statistics statisticsPanel;
	private static PHPConnect phpc = new PHPConnect();
	private static String username;
	private int lastX;
	private int lastY;
	public Spectator(map map,PC pc, int pcX, int pcY, String username) throws IOException{
		mainFrame = new JFrame();
		this.map = map;
		this.pc = pc;
		this.pcX = pcX;
		this.pcY= pcY;
		this.username = username;
		inventoryPanel = new Inventory();
		//statisticsPanel = new Statistics();
		back = new GraphicalStuff(map, pc, pc.getRow(), pc.getCol());
		mainFrame.setLayout(null);
	    mainFrame.setSize(800,750);
		mainFrame.setLocationRelativeTo(null); //center the screen
	    mainFrame.setAlwaysOnTop(true);
	    mainFrame.setResizable(false);
	    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    //mainFrame.addKeyListener(this);
	   
	    back.setDoubleBuffered(true);
	    back.setBounds(180, 0, 480, 500);
	    mainFrame.add(back);
	     setupInventoryPanel();
	     setupStatisticsPanel();
	    mainFrame.setVisible(true);
	    
	 
		
	   
	   
	    runGame();
			
		}
public static void main(String[] args) throws IOException{
	map map = new map(214,204);
	new Spectator(map,new PC (1,1,map),1,1, "efg");
}

public void runGame() throws IOException {
	while (true) {
		ArrayList<String> test = new ArrayList<String>();
		test.add("fun");
		ArrayList<String> fun = phpc.sendAndReceive("woot", test);
		String[] coordinates = null;
		for(String s: fun){
			if(s.contains(username)){
				String[] info = s.split("=");
				 coordinates = info[1].split(",");
			}
		}
		if(Integer.parseInt(coordinates[0]) > lastX){
			keyPressed(3);
			keyReleased(3);
		}
		else if(Integer.parseInt(coordinates[0]) < lastX){
			keyPressed(0);
			keyReleased(0);
		}
		else if(Integer.parseInt(coordinates[1]) > lastY){
			keyPressed(1);
			keyReleased(1);
		}
		else if(Integer.parseInt(coordinates[1]) < lastY){
			keyPressed(2);
			keyReleased(2);
		}
		lastX = Integer.parseInt(coordinates[0]);
		lastY = Integer.parseInt(coordinates[1]);	
		back.render(map, pcX, pcY);
			action = false;
			mainFrame.setVisible(true);
			// back.fun(map, pc.getRow(), pc.getCol());

		}
	
	
}
public static void setupInventoryPanel() {
	//		Inventory.updateInventory();
		JPanel inventory = inventoryPanel.returnInventory();
		inventory.setBounds(280, 500, 250, 200);
	mainFrame.add(inventory );
}
public static void setupStatisticsPanel() {
	//Statistics.updateStatistics();
	JPanel statistics = statisticsPanel.returnStatistics();
	statistics.setBounds(0, 500, 250, 200);
	mainFrame.add(statistics);
	
}
	public void keyPressed(int i) {
		PHPConnect phpc = new PHPConnect();
		phpc.sendAndReceive("UPDATE Users SET LastX="+pc.getRow()+",LastY="+pc.getCol()+" WHERE Username='" + pc.getUsername() +"'", null);
		switch (i) {
			
		case 0: {
			
			map.setdirectionName("Up");
			if(map.getUpHeld()) {
			map.setRowCol(pc.getRow(), pc.getCol(), pc.getUnderPC());
			if (map.isWalkable(pc.getRow() - 1, pc.getCol())) {
				char mapTile = map.getRowCol(pc.getRow()-1, pc.getCol());
				 lastTile = mapTile;
				if (mapTile=='/') {
					map.setOnDoor(true);
				}
				if(map.getnumChange()<10 &map.getStayLeft()) {
					map.setOnLeft(true);
					map.setnumChange(map.getnumChange()+1);
					if(map.getnumChange()==9) {
						map.setnumChange(0);
						map.setStayLeft(false);
						
					}
				}
				else  if (map.getnumChange()!=5 & !map.getStayLeft()){
					map.setOnLeft(false);
					if(map.getnumChange()==4) {
		
						map.setStayLeft(true);
						
					}
					
					map.setnumChange(map.getnumChange()+1);
				}
				
				map.renderPCView(map, pc);
				pc.update();
				pc.printStats();
				pc.setUnderPC(map.getRowCol(pc.getRow() - 1, pc.getCol()));
			}
			else {
				if(lastTile=='/') {
					map.setOnDoor(true);
				}
			}
			
			if (map.placePC(pc.getRow() - 1, pc.getCol(), pc)) {
			} else
				map.setRowCol(pc.getRow(), pc.getCol(), '@');
			
			
			action = true;

			
			
			try {
				back.render(map, pc.getRow(), pc.getCol());
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			

		}
		}
			break;
		case 1: {
			map.setdirectionName("Right");
			map.setRowCol(pc.getRow(), pc.getCol(), pc.getUnderPC());
			if (map.isWalkable(pc.getRow(), pc.getCol() + 1)){
				if(map.getnumChange()==0) {
					map.setnumChange(1);
					
				}
				else {
					map.setnumChange(0);
				}
				map.renderPCView(map, pc);
				pc.update();
				pc.printStats();
			pc.setUnderPC(map.getRowCol(pc.getRow(), pc.getCol() + 1));
			
			
			}
			
				
			if (map.placePC(pc.getRow(), pc.getCol() + 1, pc)) {
			} else
				map.setRowCol(pc.getRow(), pc.getCol(), '@');

			
			action = true;

			
			try {
				back.render(map, pc.getRow(), pc.getCol());
			} catch (IOException e) {
				
				e.printStackTrace();
			}

		}
			break;
		case 2: {
			map.setdirectionName("Left");
			map.setRowCol(pc.getRow(), pc.getCol(), pc.getUnderPC());
			if (map.isWalkable(pc.getRow(), pc.getCol() - 1)) {
				
				
				char mapTile = map.getRowCol(pc.getRow(), pc.getCol()-1);
			
				lastTile = mapTile;
				if (mapTile=='/') {
					map.setOnDoor(true);
				}
				
				if(map.getnumChange()==0) {
					map.setnumChange(1);
					
				}
				else {
					map.setnumChange(0);
				}
				map.renderPCView(map, pc);
				pc.update();
				pc.printStats();
				pc.setUnderPC(map.getRowCol(pc.getRow(), pc.getCol() - 1));
				
				
			}
			
			else {
				if(lastTile=='/') {
					map.setOnDoor(true);
				}
			}
			
				
			if (map.placePC(pc.getRow(), pc.getCol() - 1, pc)) {
			} else
				map.setRowCol(pc.getRow(), pc.getCol(), '@');

			action = true;
			try {
				back.render(map, pc.getRow(), pc.getCol());
			} catch (IOException e) {
				
				e.printStackTrace();
			}

		}
			break;
		case 3: {
			map.setdirectionName("Down");
			map.setRowCol(pc.getRow(), pc.getCol(), pc.getUnderPC());
			if (map.isWalkable(pc.getRow() + 1, pc.getCol())) {
				
				if(map.getnumChange()==0) {
					map.setnumChange(1);
					
				}
				else {
					map.setnumChange(0);
				}
				
				map.renderPCView(map, pc);
				pc.update();
			pc.printStats();
			pc.setUnderPC(map.getRowCol(pc.getRow() + 1, pc.getCol()));
			
			}
				
			if (map.placePC(pc.getRow() + 1, pc.getCol(), pc)) {
			} else
				map.setRowCol(pc.getRow(), pc.getCol(), '@');

			
			action = true;

		
			try {
				back.render(map, pc.getRow(), pc.getCol());
			} catch (IOException e) {
				
				e.printStackTrace();
			}

		}
			break;
		
		}

	}

	public void keyReleased(int i) {
		

		if(i==0){
			map.setUpHeld(false);
			map.setdirectionName("UpOnce");
			map.setRowCol(pc.getRow(), pc.getCol(), pc.getUnderPC());
			if (map.isWalkable(pc.getRow() - 1, pc.getCol())) {
				
				if(map.getAlternateFeet()==0) {
					map.setAlternateFeet(1);
					
				}
				else {
					map.setAlternateFeet(0);
				}
				
				map.renderPCView(map, pc);
				pc.update();
				pc.printStats();
				pc.setUnderPC(map.getRowCol(pc.getRow() - 1, pc.getCol()));
			}
			else {
				if(lastTile=='/') {
					map.setOnDoor(true);
				}
			}
			
			if (map.placePC(pc.getRow() - 1, pc.getCol(), pc)) {
			} else
				map.setRowCol(pc.getRow(), pc.getCol(), '@');
			
			
			action = true;

			
			
			try {
				back.render(map, pc.getRow(), pc.getCol());
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
		
		

	}

	public void keyTyped(KeyEvent e) {
		

	
	 
             
         }


	
}