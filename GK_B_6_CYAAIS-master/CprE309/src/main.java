/**
 * 
 * @author Grant Duncan
 *
 */
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
	static PC pc = new PC(1, 1);
	static quests quest = new quests();
	static items item = new items();

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Scanner in = new Scanner(System.in);
		String ans = "";
		System.out.println("Select an option");
		System.out.println("1: Continue");
		System.out.println("2: Load new map");
		System.out.println("3: Create map");
		ans = in.nextLine();

		if (ans.equals("1")) {
			load load= new load();
			map=load.loadMap();
			pc= load.loadPC();
			quest=load.loadQuests();

		} else if (ans.equals("2")) {
			map = new map(50, 50);

			// random building creation
			int randx, randy, randw, randh;
			for (int i = 0; i < 4; i++) {
				do {
					randx = Math.abs(rand.nextInt(map.getRow()));
					randy = Math.abs(rand.nextInt(map.getCol()));
					randw = Math.abs(rand.nextInt(10) + 4);
					randh = Math.abs(rand.nextInt(10) + 4);
				} while (map.createBuilding(randx, randy, randh, randw, quest) == false);
			}
			// random pc place
			while (map.placePC(Math.abs(rand.nextInt(map.getRow())), Math.abs(rand.nextInt(map.getCol())), pc) == false)
				;
		}
		if (!ans.equals("3")) {
			map.saveMap(map);

			pc.printStats();

			while (quest.questLog().size() != 0) {
				int nextQuest = Math.abs(rand.nextInt(quest.questLog().size()));

				// while the user has not reached the quest objective, has not
				// died, had a mental breakdown, or failed out of college
				while (((pc.getRow() != quest.currentQuest(nextQuest)[0])
						|| (pc.getCol() != quest.currentQuest(nextQuest)[1])) && pc.getHealth() > 0
						&& pc.getStress() < 100 && pc.getGPA() > 2.0) {

					movePC move = new movePC(map, pc, quest, nextQuest);
					pc.update();
					pc.printStats();

				}
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
				} else if (pc.getGPA() == 2.0) {
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
						map.createBuilding(row, col, hi, wid, quest);
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
