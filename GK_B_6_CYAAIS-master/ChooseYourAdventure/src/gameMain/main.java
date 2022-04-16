package gameMain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * The Class main.
 */
public class main {
	
	/** The rand. */
	static Random rand = new Random();
	
	/** The map. */
	static map map = new map();
	
	/** The pc. */
	static PC pc;
	
	/** The npcs. */
	static ArrayList<NPC> npcs;
	
	/** The quest. */
	static Quests quest = new Quests(pc);
	
	/** The back. */
	static GraphicalStuff back;

	// static MainFrame mainFrame;

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException the class not found exception
	 * @throws InterruptedException the interrupted exception
	 */
	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		PHPConnect phpc = new PHPConnect();

		Start start = new Start();
		map = new map(214, 204);
		pc = new PC(start.getLaxtX(), start.getLastY(), map);

		pc.setName(start.getName());
		pc.setUsername(start.getUsername());
		pc.setGender(start.getGender());
		pc.setMajor(start.getMajor());
		pc.setClub(start.getClub());
		
		if(start.getSpec() > 0) {
			new Spectator(map, pc, 1, 1, pc.getUsername(), start.isAdmin()>0);
		}
		
		// add the doors coords here
		ArrayList<int[]> doors = addDoors();
		// all the npcs
		npcs = new ArrayList<>();

		Random rand = new Random();
		int startingpt;
		int numStudents = 100;
		npcs.add(new student(1, 12, map, doors));

		for (int i = 0; i < numStudents; i++) {
			startingpt = rand.nextInt(doors.size());
			npcs.add(new student(doors.get(startingpt)[0], doors.get(startingpt)[1], map, doors));
			
		}
		int numBullys= 5;
		for(int i=0;i<numBullys;i++){
			startingpt = rand.nextInt(doors.size());
			npcs.add(new bully(doors.get(startingpt)[0],doors.get(startingpt)[1],map));
		}
		// npcs.add(new bully(4,26,map));
		npcs.add(new bus(107,5,map));

		for (int i = 0; i < npcs.size(); i++) {
			npcs.get(i).placeNPC(npcs.get(i).getRow(), npcs.get(i).getCol());
		}
		ArrayList<String> username = new ArrayList<String>();
		username.add("Username");
		ArrayList<String> isThere = phpc.sendAndReceive("select Username from NPCs where Username = '"+pc.getUsername()+"'", username);
//		for(String s: isThere){
//			System.out.println(s);
//		}
//		System.out.println(isThere.size());
		if(isThere.size()<=1 ){

			 for (int i = 0; i < npcs.size(); i++) {
				 phpc.sendAndReceive("insert into NPCs (Username, Image, CoordX, CoordY, ID) values('"+pc.getUsername()+"', 'whocares', 1," + i+ "," + i + ");",null);
			 }
		}
			pc.printStats();


		phpc.sendAndReceive("UPDATE Users SET LastX=" + pc.getRow() + " WHERE Username = 'efg'", null);
		pc.printStats();
 

		infoBox(  "Controls\n"
				+ "Arrow Up - Move Up\n"
				+ "Arrow Right - Move Right\n"
				+ "Arrow Down - Move Down\n"
				+ "Arrow Left - Move Left\n"
				+ "F - Fight (Attack Bullies)\n"
				+ "M - Popup Map\n\n"
				+ "Chat Commands\n"
				+ "!use item(upper case)\n"
				+ "     Ex: !use Apple\n"
				+ "!help - Pulls up help message\n"
				+ "!exit - Log out\n"
				+ "Instructions\n\n"
				+ "Do Quests to earn Experience\n"
				+ "Fight Bullies to earn cash and exp\n"
				+ "Fighting students will lead to\n"
				+ "     high speed security chases\n"
				+ "Every choice will affect the outcome.\n"
				+ "     Choose Wisely."
				, "Help Message");
			
		
				@SuppressWarnings("unused")
				MainFrame main = new MainFrame(map, pc, pc.getRow(), pc.getCol(), npcs);
		
	
		
	} 
	/*
	// map editor mode
		else {
			System.out.println("Wecome to map editor mode");
			System.out.println("1: Work on last map");
			System.out.println("2: Create blank map");
			ans = in.nextLine();
			if (ans.equals("1")) {
				map.loadMap();
				map.renderMap(map);
				while (true) {
					System.out.println("1: Add a building");
					System.out.println("2: Save and Exit");
					ans = in.nextLine();
					if (ans.equals("1")) {
						System.out.println("Enter row");
						int row = in.nextInt();
						System.out.println("Enter collumn");
						int col = in.nextInt();
						System.out.println("Enter height");
						int hi = in.nextInt();
						System.out.println("Enter width");
						int wid = in.nextInt();
						map.createBuilding(row, col, hi, wid);
						map.renderMap(map);
					} else if (ans.equals("2")) {
						map.saveMap(map);
						break;
					}
				}
			} else {

			}
		
		}
	
	} */

	/**
	 * Adds the doors.
	 *
	 * @return the array list
	 */
	public static ArrayList<int[]> addDoors() {
		ArrayList<int[]> doors = new ArrayList<>();
		int[] tempDoor = { 21, 122 };
		doors.add(tempDoor);
		tempDoor = new int[2];
		tempDoor[0] = 36;
		tempDoor[1] = 111;
		doors.add(tempDoor);
		tempDoor = new int[2];
		tempDoor[0] = 35;
		tempDoor[1] = 27;
		doors.add(tempDoor);
		tempDoor = new int[2];
		tempDoor[0] = 35;
		tempDoor[1] = 74;
		doors.add(tempDoor);
		tempDoor = new int[2];
		tempDoor[0] = 40;
		tempDoor[1] = 99;
		doors.add(tempDoor);
		tempDoor = new int[2];
		tempDoor[0] = 49;
		tempDoor[1] = 97;
		doors.add(tempDoor);
		tempDoor = new int[2];
		tempDoor[0] = 49;
		tempDoor[1] = 179;
		doors.add(tempDoor);
		tempDoor = new int[2];
		tempDoor[0] = 71;
		tempDoor[1] = 155;
		doors.add(tempDoor);
		tempDoor = new int[2];
		tempDoor[0] = 65;
		tempDoor[1] = 119;
		doors.add(tempDoor);
		tempDoor = new int[2];
		tempDoor[0] = 94;
		tempDoor[1] = 111;
		doors.add(tempDoor);
		tempDoor = new int[2];
		tempDoor[0] = 74;
		tempDoor[1] = 34;
		doors.add(tempDoor);
		tempDoor = new int[2];
		tempDoor[0] = 89;
		tempDoor[1] = 79;
		doors.add(tempDoor);
		tempDoor = new int[2];
		tempDoor[0] = 127;
		tempDoor[1] = 14;
		doors.add(tempDoor);
		tempDoor = new int[2];
		tempDoor[0] = 137;
		tempDoor[1] = 77;
		doors.add(tempDoor);
		tempDoor = new int[2];
		tempDoor[0] = 167;
		tempDoor[1] = 59;
		doors.add(tempDoor);
		tempDoor = new int[2];
		tempDoor[0] = 189;
		tempDoor[1] = 32;
		doors.add(tempDoor);
		tempDoor = new int[2];
		tempDoor[0] = 159;
		tempDoor[1] = 111;
		doors.add(tempDoor);
		tempDoor = new int[2];
		tempDoor[0] = 171;
		tempDoor[1] = 96;
		doors.add(tempDoor);
		tempDoor = new int[2];
		tempDoor[0] = 124;
		tempDoor[1] = 110;
		doors.add(tempDoor);
		tempDoor = new int[2];
		tempDoor[0] = 139;
		tempDoor[1] = 100;
		doors.add(tempDoor);
		tempDoor = new int[2];
		tempDoor[0] = 92;
		tempDoor[1] = 164;
		doors.add(tempDoor);
		tempDoor = new int[2];
		tempDoor[0] = 89;
		tempDoor[1] = 169;
		doors.add(tempDoor);
		tempDoor = new int[2];
		tempDoor[0] = 160;
		tempDoor[1] = 161;
		doors.add(tempDoor);
		tempDoor = new int[2];
		tempDoor[0] = 160;
		tempDoor[1] = 182;
		doors.add(tempDoor);
		tempDoor = new int[2];
		tempDoor[0] = 13;
		tempDoor[1] = 1;
		doors.add(tempDoor);
		tempDoor = new int[2];
		tempDoor[0] = 164;
		tempDoor[1] = 1;
		doors.add(tempDoor);
		tempDoor = new int[2];
		tempDoor[0] = 1;
		tempDoor[1] = 12;
		doors.add(tempDoor);
		tempDoor = new int[2];
		tempDoor[0] = 201;
		tempDoor[1] = 12;
		doors.add(tempDoor);
		tempDoor = new int[2];
		tempDoor[0] = 1;
		tempDoor[1] = 111;
		doors.add(tempDoor);
		tempDoor = new int[2];
		// tempDoor[0]=201;
		// tempDoor[1]=111;
		// doors.add(tempDoor);
		// tempDoor[0]=1;
		// tempDoor[1]=207;
		// doors.add(tempDoor);
		// tempDoor[0]=201;
		// tempDoor[1]=111;
		// doors.add(tempDoor);
		// tempDoor[0]=1;
		// tempDoor[1]=207;
		// doors.add(tempDoor);
		// tempDoor[0]=201;
		// tempDoor[1]=111;
		// doors.add(tempDoor);
		// tempDoor[0]=14;
		// tempDoor[1]=212;
		// doors.add(tempDoor);
		// tempDoor[0]=163;
		// tempDoor[1]=212;
		// doors.add(tempDoor);
		return doors;
	}
	
	/**
	 * Info box.
	 *
	 * @param infoMessage the info message
	 * @param titleBar the title bar
	 */
	static void infoBox(String infoMessage, String titleBar) {	
		JOptionPane pane = new JOptionPane(infoMessage);
		JDialog d = pane.createDialog((JFrame)null, titleBar);
		d.setLocation(0,0);
		d.setVisible(true);
	}
}
