/**
 * 
 * @author Grant Duncan
 *
 */
package main;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class main {
	static Random rand = new Random();
	static map map = new map();
	//static PC pc = new PC(1, 1);
	static PC pc;
	static Quests quest = new Quests(pc);
	static GraphicalStuff back;

	//static MainFrame mainFrame;

	


	public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
		Scanner in = new Scanner(System.in);
		String ans = "";
		PHPConnect phpc = new PHPConnect();
		System.out.println("Select an option");
		System.out.println("1: Continue");
		System.out.println("2: Load new map");
		System.out.println("3: Create map");
		ans = in.nextLine();

		if (ans.equals("1")) {
			load load = new load();
			Start start = new Start();
			map = load.loadMap();
			pc = load.loadPC();
			
			pc.setName(start.getName());
			pc.setUsername(start.getUsername());
			pc.setGender(start.getGender());
			pc.setMajor(start.getMajor());
			pc.setClub(start.getClub());
			pc.setClothes(start.getClothes());
			map = load.loadMap();
			//quest = load.loadQuests();
			back = new GraphicalStuff(map, pc, pc.getRow(), pc.getCol());

		} else if (ans.equals("2")) {
			map = new map(214, 204);

			// random building creation

			// int randx, randy, randw, randh;
			// for (int i = 0; i < 4; i++) {
			// do {
			// randx = Math.abs(rand.nextInt(map.getRow()));
			// randy = Math.abs(rand.nextInt(map.getCol()));
			// randw = Math.abs(rand.nextInt(10) + 4);
			// randh = Math.abs(rand.nextInt(10) + 4);
			// } while (map.createBuilding(randx, randy, randh, randw, quest) ==
			// false);
			// }
			// // random pc place
			// while (map.placePC(Math.abs(rand.nextInt(map.getRow())),
			// Math.abs(rand.nextInt(map.getCol())), pc) == false)
			// ;
			// back = new GraphicalStuff(map, pc.getRow(), pc.getCol());

			// random pc place
			
				;
			 //back = new GraphicalStuff(map, pc.getRow(), pc.getCol());

		}
		if (!ans.equals("3")) {
			// map.saveMap(map);
			ArrayList<int[]> tempQuestList = new ArrayList<>();
			int[] tempQuest = new int[2];
			tempQuest[0]=50;
			tempQuest[1]=50;
			tempQuestList.add(tempQuest);
			quest.addQuest(tempQuestList);
			
			
			//add the doors coords here
			ArrayList<int[]> doors = new ArrayList<>();
			int[] tempDoor= {4,7};
			doors.add(tempDoor);
			tempDoor[0]=9;
			tempDoor[1]=7;
			doors.add(tempDoor);
			
			
			
			//all the npcs
			ArrayList<NPC> npcs = new ArrayList<>();
		
			//npcs.add(new student(20,7,map,doors));
			//npcs.add(new student(9,7,map,doors));       //<-- wtf it always skips this and adds the bully
			npcs.add(new bully(5,100,map));
			
			
			
			
			
			pc= new PC(1,1,map);
			phpc.sendAndReceive("UPDATE Users SET LastX="+pc.getRow()+" WHERE Username='efg'", null);
			pc.printStats();

			// for testing bully and distance map

//			bully bully = new bully(1, 2, map);
//			map.setRowCol(1, 1, mapNode.BULLY);
			// distanceMap dist = new distanceMap(map, pc);
			// dist.createPCMap();
			// dist.renderMap();
			// dist = new distanceMap(map, bully);
			// dist.createNPCMap();
			// dist.renderMap();
			// if (bully.checkLOS(pc, map))
			// bully.attackPC(pc, map);

			while (quest.questLog().size() != 0) {

				int nextQuest = Math.abs(rand.nextInt(quest.questLog().size()));

				// while the user has not reached the quest objective, has not
				// died, had a mental breakdown, or failed out of college
				while (((pc.getRow() != quest.currentQuest(nextQuest)[0]) // Needs
																			// to
																			// be
																			// re-implemented
																			// in
																			// movePC
																			// class
						|| (pc.getCol() != quest.currentQuest(nextQuest)[1])) && pc.getHealth() > 0
						&& pc.getStress() < 100 && pc.getIntellect() > 0) {

					MainFrame main = new MainFrame(map, pc , pc.getRow(), pc.getCol(), npcs);
					movePC move = new movePC(map, pc, quest, nextQuest);
					//bully.update(pc, map);
					pc.update();
					pc.printStats();
					phpc.sendAndReceive("UPDATE Users SET LastX="+pc.getRow()+" WHERE Username='efg'", null);
					
					//mainFrame = new MainFrame(map, pc, quest,nextQuest);
					//movePC move = new movePC(map, pc, quest, nextQuest);
					//pc.update();
					//pc.printStats();
					back = new GraphicalStuff(map, pc, pc.getRow(), pc.getCol());
					//back.render(map, pc.getRow(), pc.getCol());

				}
				phpc.sendAndReceive("UPDATE Users SET LastX="+pc.getRow()+" WHERE Username='efg'", null);
				map.renderPCView(map, pc);
				if (pc.getUnderPC() == '/') {
					System.out.println("You have reached a cheakpoint");
					quest.questLog().remove(nextQuest);
					// this gives a default amount of 5 xp per quest...probably
					// needs updated
					pc.giveXP(5);
					save save = new save(map, pc, quest);
					if (quest.questLog().isEmpty())
						System.out.println("Congratulations, you have passed!");
				} else if (pc.getHealth() == 0) {
					System.out.println("You have died");
					break;
				} else if (pc.getStress() == 100) {
					System.out.println("You have had a mental breakdown");
					break;
				} else if (pc.getIntellect() < 0) {
					System.out.println("You have failed out of college");
					break;
				}
			}

		}
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

	}

}
