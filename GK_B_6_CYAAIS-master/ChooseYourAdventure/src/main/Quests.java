package main;

import java.util.ArrayList;


public class Quests {

	private PHPConnect connect = new PHPConnect();
	private PC pc;
	private ArrayList<int[]> quest;
	
	private int checkTasks[] = new int[100];
	private int questTasks[]= new int[100];
	
	
	
	public Quests(PC pc){
		this.pc = pc;
		quest = new ArrayList<int[]>();
		
		for(int i = 0; i < checkTasks.length; i++)
			checkTasks[i] = 0;
		for(int i = 0; i < questTasks.length; i++)
			questTasks[i] = 0;
	
	}
	
	
	
	// OLD CODE
	public void addQuest(ArrayList<int[]> quest){
		this.quest=quest;
	}
	public int[] currentQuest(int number){
		return quest.get(number);
	}
	public void printQuest(int number){
		System.out.println("Go to "+quest.get(number)[0]+","+quest.get(number)[1]);
	}
	public ArrayList<int[]> questLog(){
		return quest;
	}
	

	
	
	// NEW CODE
	public int calcQuestExp() {
		int sum = 0;
		
		ArrayList<String> expCol = new ArrayList<String>();
		ArrayList<String> exp = new ArrayList<String>();
		expCol.add("ExpEarned");	
		exp = connect.sendAndReceive("select ExpEarned from Quests qq inner join DoneQuests dq on qq.QuestID = dq.QuestID where dq.Username = \'" + PC.getUsername() + "\'", expCol);
		
		for(int i = 0; i < exp.size(); i++)
			sum += Integer.parseInt(exp.get(i));
	
		return sum;
	}
	
	
	public void addQuest(String questID) {
		connect.sendAndReceive("INSERT CurrentQuests (Username, QuestID) VALUES (\'" + PC.getUsername() + "\', " + questID + ")", null);
	}
	
	
	public void failedQuest(String questID) {
		connect.sendAndReceive("INSERT PendingQuests (Username, QuestID) VALUES (\'" + PC.getUsername() + "\', " + questID + ")", null);
		connect.sendAndReceive("delete from CurrentQuests where Username = \'" + PC.getUsername() + "\' AND QuestID = " + questID, null);
	}
	
	public void questUpdater(int questTask) {
		questTasks[questTask] += 1;
	}
	
	public void checkQuests() {
		ArrayList<String> questsCol = new ArrayList<String>();
		questsCol.add("QuestID");
		ArrayList<String> quests = new ArrayList<String>();		
		quests = connect.sendAndReceive("select QuestID from CurrentQuests where username = \'" + PC.getUsername() + "\'", questsCol);
		
		//Check each Quest
		if(quests.contains("1"))
			checkTutorial();
		if(quests.contains("2"))
			checkBasIntro();
		if(quests.contains("3"))
			checkHonIntro();
		if(quests.contains("4"))
			checkBreIntro();
		if(quests.contains("5"))
			checkEngIntro();
		if(quests.contains("6"))
			checkKinIntro();
		if(quests.contains("7"))
			checkCulIntro();
		if(quests.contains("8"))
			checkAcademic1();
		if(quests.contains("9"))
			checkFightJustice();
		if(quests.contains("10"))
			checkAcademic2();	
		if(quests.contains("11"))
			checkBas1();
		if(quests.contains("12"))
			checkBre1();
		if(quests.contains("13"))
			checkHon1();
		if(quests.contains("14"))
			checkBas2();
		if(quests.contains("15"))
			checkBre2();
		if(quests.contains("16"))
			checkHon2();
		if(quests.contains("17"))
			checkFinalComp();
		if(quests.contains("18"))
			checkKin3();
		if(quests.contains("19"))
			checkCul3();
		if(quests.contains("20"))
			checkEng3();
	}
	
	
	
	
	// CHECK QUESTS
	public void checkTutorial() {
		ArrayList<String> itemCol = new ArrayList<String>();
		itemCol.add("InventoryID");
		ArrayList<String> items = new ArrayList<String>();		
		items = connect.sendAndReceive("select InventoryID from Inventory where username = \'" + PC.getUsername() + "\'", itemCol);			

		/*
		 * If the player walked over the coordinates in front of the Caribou Cafe doors
		 * 		checkTasks[0] = 1;
		 */
		/*
		 * If the player walked on the coordinates for either of the doors
		 * 		checkTasks[1] = 1;
		 */
		if((pc.getRow() == 92 && pc.getCol() == 164) || (pc.getRow() == 89 && pc.getCol() == 169) ) {
			checkTasks[0] = 1;
			checkTasks[1] = 1;
		}
		

		
		/*
		 * If the player's inventory contains InventoryId = 11 (Apple)
		 * 		checkTasks[2] = 1;
		 */
		if(items.contains("11"))
			checkTasks[2] = 1;
		
		if(checkTasks[0] == 1 && checkTasks[1] == 1 && checkTasks[2] == 1) {
			connect.sendAndReceive("INSERT DoneQuests (Username, QuestID) VALUES (\'" + PC.getUsername() + "\', 1)", null);
			connect.sendAndReceive("delete from CurrentQuests where Username = \'" + PC.getUsername() + "\' AND QuestID = 1", null);
			connect.sendAndReceive("update Statistics set Experience = " + (PC.getXP() + 15) + " where Username = \'" + PC.getUsername() + "\'", null);
			pc.giveXP(15);
			
			if(PC.getMajor() == 1)
				addQuest("6");		// Fitness Intro
			if(PC.getMajor() == 2)
				addQuest("7");		// Culinary Arts Intro
			if(PC.getMajor() == 3)
				addQuest("5");		// Engineering Intro
			
			if(PC.getClub() == 1)
				addQuest("2");		// Basketball Intro
			if(PC.getClub() == 2)
				addQuest("4");		// Breakdance Intro
			if(PC.getClub() == 3)
				addQuest("3");		// Honors Intro
		}
	}
	
	
	public void checkEngIntro() {
		/*
		 * If the player walked over the coordinates in front of the Coover Hall doors
		 * 		checkTasks[10] = 1;
		 */
		if((pc.getRow() == 35 && pc.getCol() == 27) || (pc.getRow() == 35 && pc.getCol() == 74) ) {
			checkTasks[10] = 1;
		}
		
		/*
		 * If the player walked on the coordinates for either of the doors, and selected Go to class
		 * 		checkTasks[11] > 0;
		 */
		if(questTasks[11] == 1)
			checkTasks[11] = 1;
			
		
		
		if(checkTasks[10] == 1 && checkTasks[11] == 1) {
			connect.sendAndReceive("INSERT DoneQuests (Username, QuestID) VALUES (\'" + PC.getUsername() + "\', 5)", null);
			connect.sendAndReceive("delete from CurrentQuests where Username = \'" + PC.getUsername() + "\' AND QuestID = 5", null);
			connect.sendAndReceive("update Statistics set Experience = " + (PC.getXP() + 10) + " where Username = \'" + PC.getUsername() + "\'", null);
			pc.giveXP(10);
			
			addQuest("8");

		}
	}
	
	
	public void checkKinIntro() {
		/*
		 * If the player walked over the coordinates in front of the Beyer Hall doors
		 * 		checkTasks[20] = 1;
		 */
		if((pc.getRow() == 1 && pc.getCol() == 207)) {
			checkTasks[20] = 1;
		}
		
		/*
		 * If the player walked on the coordinates for either of the doors, and selected Go to class
		 * 		checkTasks[21] > 0;
		 */
		if(questTasks[21] == 1)
			checkTasks[21] = 1;
			
		
		
		if(checkTasks[20] == 1 && checkTasks[21] == 1) {
			connect.sendAndReceive("INSERT DoneQuests (Username, QuestID) VALUES (\'" + PC.getUsername() + "\', 6)", null);
			connect.sendAndReceive("delete from CurrentQuests where Username = \'" + PC.getUsername() + "\' AND QuestID = 6", null);
			connect.sendAndReceive("update Statistics set Experience = " + (PC.getXP() + 10) + " where Username = \'" + PC.getUsername() + "\'", null);
			pc.giveXP(10);

			addQuest("8");
		}
	}
	
	
	public void checkCulIntro() {
		/*
		 * If the player walked over the coordinates in front of the Howe Hall doors
		 * 		checkTasks[30] = 1;
		 */
		if((pc.getRow() == 1 && pc.getCol() == 111)) {
			checkTasks[30] = 1;
		}
		/*
		 * If the player walked on the coordinates for either of the doors, and selected Go to class
		 * 		checkTasks[31] > 0;
		 */
		if(questTasks[31] == 1)
			checkTasks[31] = 1;
		
		
		if(checkTasks[30] == 1 && checkTasks[31] == 1) {
			connect.sendAndReceive("INSERT DoneQuests (Username, QuestID) VALUES (\'" + PC.getUsername() + "\', 7)", null);
			connect.sendAndReceive("delete from CurrentQuests where Username = \'" + PC.getUsername() + "\' AND QuestID = 7", null);
			connect.sendAndReceive("update Statistics set Experience = " + (PC.getXP() + 10) + " where Username = \'" + PC.getUsername() + "\'", null);
			pc.giveXP(10);

			addQuest("8");
		}
	}
	
	public void checkBasIntro() {
		/*
		 * If the player walked over the coordinates in front of the Snedecor Hall doors
		 * 		checkTasks[40] = 1;
		 */
		if((pc.getRow() == 21 && pc.getCol() == 122) || (pc.getRow() == 36 && pc.getCol() == 111) ) {
			checkTasks[40] = 1;
		}
		/*
		 * If the player walked on the coordinates for either of the doors, and selected Practice Basketball
		 * 		checkTasks[41] > 0;
		 */
		if(questTasks[41] == 1)
			checkTasks[41] = 1;
		
		/*
		 * If the player walked on the coordinates for either of the doors, and selected Work-out
		 * 		checkTasks[42] > 0;
		 */
		if(questTasks[42] == 1)
			checkTasks[42] = 1;
			
		
		
		if(checkTasks[40] == 1 && checkTasks[41] == 1 && checkTasks[42] == 1 ) {
			connect.sendAndReceive("INSERT DoneQuests (Username, QuestID) VALUES (\'" + PC.getUsername() + "\', 2)", null);
			connect.sendAndReceive("delete from CurrentQuests where Username = \'" + PC.getUsername() + "\' AND QuestID = 2", null);
			connect.sendAndReceive("update Statistics set Experience = " + (PC.getXP() + 15) + " where Username = \'" + PC.getUsername() + "\'", null);
			pc.giveXP(15);

			addQuest("11");
		}
	}
	
	
	public void checkBreIntro() {
		/*
		 * If the player walked over the coordinates in front of the Atanasoff Hall doors
		 * 		checkTasks[50] = 1;
		 */
		if((pc.getRow() == 40 && pc.getCol() == 99) || (pc.getRow() == 49 && pc.getCol() == 97) ) {
			checkTasks[50] = 1;
		}
		/*
		 * If the player walked on the coordinates for either of the doors, and selected Study Dancing
		 * 		checkTasks[51] > 0;
		 */
		if(questTasks[51] == 1)
			checkTasks[51] = 1;
		
		/*
		 * If the player walked on the coordinates for either of the doors, and selected Dance for 4 hours
		 * 		checkTasks[52] > 0;
		 */
		if(questTasks[52] == 1)
			checkTasks[52] = 1;
			
		
		
		if(checkTasks[50] == 1 && checkTasks[51] == 1 && checkTasks[52] == 1 ) {
			connect.sendAndReceive("INSERT DoneQuests (Username, QuestID) VALUES (\'" + PC.getUsername() + "\', 4)", null);
			connect.sendAndReceive("delete from CurrentQuests where Username = \'" + PC.getUsername() + "\' AND QuestID = 4", null);
			connect.sendAndReceive("update Statistics set Experience = " + (PC.getXP() + 15) + " where Username = \'" + PC.getUsername() + "\'", null);
			pc.giveXP(15);

			addQuest("12");
		}
	}
	
	
	public void checkHonIntro() {
		/*
		 * If the player walked over the coordinates in front of the Sweeney Hall doors
		 * 		checkTasks[60] = 1;
		 */
		if((pc.getRow() == 74 && pc.getCol() == 34) || (pc.getRow() == 89 && pc.getCol() == 79) ) {
			checkTasks[60] = 1;
		}
		/*
		 * If the player walked on the coordinates for either of the doors, and selected Study for Honors
		 * 		checkTasks[61] > 0;
		 */
		if(questTasks[61] == 1)
			checkTasks[61] = 1;
		
		/*
		 * If the player walked on the coordinates for either of the doors, and selected Read a Book
		 * 		checkTasks[17] > 0;
		 */
		if(questTasks[62] == 1)
			checkTasks[62] = 1;
			
		
		if(checkTasks[60] == 1 && checkTasks[61] == 1 && checkTasks[62] == 1 ) {
			connect.sendAndReceive("INSERT DoneQuests (Username, QuestID) VALUES (\'" + PC.getUsername() + "\', 2)", null);
			connect.sendAndReceive("delete from CurrentQuests where Username = \'" + PC.getUsername() + "\' AND QuestID = 2", null);
			connect.sendAndReceive("update Statistics set Experience = " + (PC.getXP() + 15) + " where Username = \'" + PC.getUsername() + "\'", null);
			pc.giveXP(15);
			
			addQuest("13");

		}
	}
	
	
	
	
	public void checkAcademic1() {
		ArrayList<String> itemCol = new ArrayList<String>();
		itemCol.add("InventoryID");
		ArrayList<String> items = new ArrayList<String>();		
		items = connect.sendAndReceive("select InventoryID from Inventory where username = \'" + PC.getUsername() + "\'", itemCol);			

		
		checkTasks[3] = 0;
		checkTasks[4] = 0;
		checkTasks[5] = 0;
		/*
		 * If the player has attended 6 classes 
		 * 		checkTasks[3] = 1;
		 */
		if(questTasks[3] >= 6)
			checkTasks[3] = 1;
		
		/*
		 * If the player has talked to 3 or more teachers
		 * 		checkTasks[4] > 0;
		 */
		if(questTasks[4] >= 3)
			checkTasks[4] = 1;
		
		/*
		 * If the player has any item that can be bought from the MU
		 * 		checkTasks[5] > 0;
		 */
		if(items.contains("4") || items.contains("5") || items.contains("6") )
			checkTasks[5] = 1;
			
		
		
		if(checkTasks[3] == 1 && checkTasks[4] == 1 && checkTasks[5] == 1 ) {
			connect.sendAndReceive("INSERT DoneQuests (Username, QuestID) VALUES (\'" + PC.getUsername() + "\', 8)", null);
			connect.sendAndReceive("delete from CurrentQuests where Username = \'" + PC.getUsername() + "\' AND QuestID = 8", null);
			connect.sendAndReceive("update Statistics set Experience = " + (PC.getXP() + 20) + " where Username = \'" + PC.getUsername() + "\'", null);
			pc.giveXP(20);
			
			addQuest("9");
		}
	}
	
	public void checkFightJustice() {
		checkTasks[7] = 0;
		
		/*
		 * A player defeats a bully
		 * 		checkTasks[7] = 1;
		 */
		if(pc.getBullyCount() > 0)
			checkTasks[7] = 1;
			
		
		if(checkTasks[7] == 1) {
			connect.sendAndReceive("INSERT DoneQuests (Username, QuestID) VALUES (\'" + PC.getUsername() + "\', 9)", null);
			connect.sendAndReceive("delete from CurrentQuests where Username = \'" + PC.getUsername() + "\' AND QuestID = 9", null);
			connect.sendAndReceive("update Statistics set Experience = " + (PC.getXP() + 15) + " where Username = \'" + PC.getUsername() + "\'", null);
			pc.giveXP(15);
			
			addQuest("10");

		}
	}
	
	
	
	public void checkAcademic2() {
		checkTasks[3] = 0;
		checkTasks[7] = 0;
		checkTasks[8] = 0;
		
		/*
		 * If the player has attended 8 more classes 
		 * 		checkTasks[3] = 1;
		 */
		if(questTasks[3] >= 14)
			checkTasks[3] = 1;
		
		/*
		 * If the player has beaten 3 or more bullies
		 * 		checkTasks[7] > 0;
		 */
		if(pc.getBullyCount() >= 4)
			checkTasks[7] = 1;
		
		/*
		 * If the player passed their first quiz
		 * 		checkTasks[8] > 0;
		 */
		if(questTasks[8] == 1)
			checkTasks[8] = 1;
			
		
		
		if(checkTasks[3] == 1 && checkTasks[4] == 1 && checkTasks[5] == 1 ) {
			connect.sendAndReceive("INSERT DoneQuests (Username, QuestID) VALUES (\'" + PC.getUsername() + "\', 10)", null);
			connect.sendAndReceive("delete from CurrentQuests where Username = \'" + PC.getUsername() + "\' AND QuestID = 10", null);
			connect.sendAndReceive("update Statistics set Experience = " + (PC.getXP() + 25) + " where Username = \'" + PC.getUsername() + "\'", null);
			pc.giveXP(25);
			
			if(PC.getMajor() == 1)	// Fitness 
				addQuest("18");		
			if(PC.getMajor() == 2)	// Culinary Arts 
				addQuest("19");		
			if(PC.getMajor() == 3)	// Engineering 
				addQuest("20");		
		}
		
		
	}
	
	
	
	public void checkBas1() {
		checkTasks[41] = 0;
		checkTasks[42] = 0;
		
		/*
		 * If the player walked on the coordinates for either of the doors, and selected go to Basketball Club twice
		 * 		checkTasks[43] >= 2;
		 */
		if(questTasks[43] >= 2)
			checkTasks[43] = 1;

		/*
		 * If the player walked on the coordinates for either of the doors, and selected Practice Basketball 1 more time
		 * 		checkTasks[41] >= 2;
		 */
		if(questTasks[41] >= 2)
			checkTasks[41] = 1;
		
		/*
		 * If the player walked on the coordinates for either of the doors, and selected Work-out 3 more times
		 * 		checkTasks[42] > 0;
		 */
		if(questTasks[42] >= 4)
			checkTasks[42] = 1;
			
		
		
		if(checkTasks[43] == 1 && checkTasks[41] == 1 && checkTasks[42] == 1 ) {
			connect.sendAndReceive("INSERT DoneQuests (Username, QuestID) VALUES (\'" + PC.getUsername() + "\', 11)", null);
			connect.sendAndReceive("delete from CurrentQuests where Username = \'" + PC.getUsername() + "\' AND QuestID = 11", null);
			connect.sendAndReceive("update Statistics set Experience = " + (PC.getXP() + 20) + " where Username = \'" + PC.getUsername() + "\'", null);
			pc.giveXP(20);

			addQuest("14");
		}
	}
	
	
	
	public void checkBre1() {
		checkTasks[51] = 0;
		checkTasks[52] = 0;
		
		/*
		 * If the player walked on the coordinates for either of the doors, and selected go to Breakdance Club twice
		 * 		checkTasks[53] >= 2;
		 */
		if(questTasks[53] >= 2)
			checkTasks[53] = 1;

		/*
		 * If the player walked on the coordinates for either of the doors, and selected Study Dancing 1 more time
		 * 		checkTasks[51] >= 2;
		 */
		if(questTasks[51] >= 2)
			checkTasks[51] = 1;
		
		/*
		 * If the player walked on the coordinates for either of the doors, and selected Dance for Fun 3 more times
		 * 		checkTasks[52] > 0;
		 */
		if(questTasks[52] >= 4)
			checkTasks[52] = 1;
			
		
		
		if(checkTasks[53] == 1 && checkTasks[51] == 1 && checkTasks[52] == 1 ) {
			connect.sendAndReceive("INSERT DoneQuests (Username, QuestID) VALUES (\'" + PC.getUsername() + "\', 12)", null);
			connect.sendAndReceive("delete from CurrentQuests where Username = \'" + PC.getUsername() + "\' AND QuestID = 12", null);
			connect.sendAndReceive("update Statistics set Experience = " + (PC.getXP() + 20) + " where Username = \'" + PC.getUsername() + "\'", null);
			pc.giveXP(20);
			
			addQuest("15");

		}
	}
	
	
	
	public void checkHon1() {
		checkTasks[61] = 0;
		checkTasks[62] = 0;
		
		/*
		 * If the player walked on the coordinates for either of the doors, and selected go to Honors Club twice
		 * 		checkTasks[63] >= 2;
		 */
		if(questTasks[63] >= 2)
			checkTasks[63] = 1;

		/*
		 * If the player walked on the coordinates for either of the doors, and selected Study for Honors 1 more time
		 * 		checkTasks[61] >= 2;
		 */
		if(questTasks[61] >= 2)
			checkTasks[61] = 1;
		
		/*
		 * If the player walked on the coordinates for either of the doors, and selected Read a Book more times
		 * 		checkTasks[62] > 0;
		 */
		if(questTasks[62] >= 4)
			checkTasks[62] = 1;
			
		
		
		if(checkTasks[63] == 1 && checkTasks[61] == 1 && checkTasks[62] == 1 ) {
			connect.sendAndReceive("INSERT DoneQuests (Username, QuestID) VALUES (\'" + PC.getUsername() + "\', 13)", null);
			connect.sendAndReceive("delete from CurrentQuests where Username = \'" + PC.getUsername() + "\' AND QuestID = 13", null);
			connect.sendAndReceive("update Statistics set Experience = " + (PC.getXP() + 20) + " where Username = \'" + PC.getUsername() + "\'", null);
			pc.giveXP(20);
			
			addQuest("16");

		}
	}
	
	
	
	public void checkBas2() {	
		/*
		 * Obtain a health level of 125
		 * 		checkTasks[44] = 1;
		 */
		if(pc.getHealth() >= 125)
			checkTasks[44] = 1;

		/*
		 * Win the Basketball Game!
		 * 		checkTasks[45] = 1;
		 */
		if(questTasks[45] == 1)
			checkTasks[45] = 1;	
		
		
		if(checkTasks[45] == 1 && checkTasks[44] == 1) {
			connect.sendAndReceive("INSERT DoneQuests (Username, QuestID) VALUES (\'" + PC.getUsername() + "\', 14)", null);
			connect.sendAndReceive("delete from CurrentQuests where Username = \'" + PC.getUsername() + "\' AND QuestID = 14", null);
			connect.sendAndReceive("update Statistics set Experience = " + (PC.getXP() + 40) + " where Username = \'" + PC.getUsername() + "\'", null);
			pc.giveXP(40);
		}
	}
	
	
	
	public void checkBre2() {
		/*
		 * Obtain a stress level below 25
		 * 		checkTasks[54] = 1;
		 */
		if(pc.getStress() <= 25)
			checkTasks[54] = 1;

		/*
		 * Win the Breakdance Competition!
		 * 		checkTasks[55] = 1;
		 */
		if(questTasks[55] == 1)
			checkTasks[55] = 1;	
		
		
		if(checkTasks[55] == 1 && checkTasks[54] == 1) {
			connect.sendAndReceive("INSERT DoneQuests (Username, QuestID) VALUES (\'" + PC.getUsername() + "\', 15)", null);
			connect.sendAndReceive("delete from CurrentQuests where Username = \'" + PC.getUsername() + "\' AND QuestID = 15", null);
			connect.sendAndReceive("update Statistics set Experience = " + (PC.getXP() + 40) + " where Username = \'" + PC.getUsername() + "\'", null);
			pc.giveXP(40);
		}
	}
	
	
	
	public void checkHon2() {
		/*
		 * Obtain a intellect level of 75
		 * 		checkTasks[64] = 1;
		 */
		if(pc.getIntellect() >= 75)
			checkTasks[64] = 1;

		/*
		 * Win the Honors Competition!
		 * 		checkTasks[65] = 1;
		 */
		if(questTasks[65] == 1)
			checkTasks[65] = 1;	
		
		
		if(checkTasks[65] == 1 && checkTasks[64] == 1) {
			connect.sendAndReceive("INSERT DoneQuests (Username, QuestID) VALUES (\'" + PC.getUsername() + "\', 16)", null);
			connect.sendAndReceive("delete from CurrentQuests where Username = \'" + PC.getUsername() + "\' AND QuestID = 16", null);
			connect.sendAndReceive("update Statistics set Experience = " + (PC.getXP() + 40) + " where Username = \'" + PC.getUsername() + "\'", null);
			pc.giveXP(40);
		}
	}
	
	
	
	public void checkFinalComp() {
		/*
		 * If the user won their respective competition
		 * 		checkTasks[99] = 1;
		 */
		if(questTasks[99] == 1)
			checkTasks[99] = 1;	
		
		
		if(checkTasks[99] == 1) {
			connect.sendAndReceive("INSERT DoneQuests (Username, QuestID) VALUES (\'" + PC.getUsername() + "\', 17)", null);
			connect.sendAndReceive("delete from CurrentQuests where Username = \'" + PC.getUsername() + "\' AND QuestID = 17", null);
			connect.sendAndReceive("update Statistics set Experience = " + (PC.getXP() + 40) + " where Username = \'" + PC.getUsername() + "\'", null);
			pc.giveXP(40);
		}
	}
	
	
	
	public void checkKin3() {	
		/*
		 * Obtain a health level of 100
		 * 		checkTasks[46] = 1;
		 */
		if(pc.getHealth() >= 100)
			checkTasks[46] = 1;

		/*
		 * Workout for 2 weekends
		 * 		checkTasks[45] = 1;
		 */
		if(questTasks[47] >= 2)
			checkTasks[47] = 1;	
		
		
		if(checkTasks[46] == 1 && checkTasks[47] == 1) {
			connect.sendAndReceive("INSERT DoneQuests (Username, QuestID) VALUES (\'" + PC.getUsername() + "\', 18)", null);
			connect.sendAndReceive("delete from CurrentQuests where Username = \'" + PC.getUsername() + "\' AND QuestID = 18", null);
			connect.sendAndReceive("update Statistics set Experience = " + (PC.getXP() + 30) + " where Username = \'" + PC.getUsername() + "\'", null);
			pc.giveXP(30);
			
			addQuest("17");
		}
	}
	
	
	
	
	public void checkCul3() {
		/*
		 * Obtain a stress level below 25
		 * 		checkTasks[56] = 1;
		 */
		if(pc.getStress() <= 35)
			checkTasks[56] = 1;

		/*
		 * Party for at least 2 weekends
		 * 		checkTasks[57] = 1;
		 */
		if(questTasks[57] == 1)
			checkTasks[57] = 1;	
		
		
		if(checkTasks[56] == 1 && checkTasks[57] == 1) {
			connect.sendAndReceive("INSERT DoneQuests (Username, QuestID) VALUES (\'" + PC.getUsername() + "\', 19)", null);
			connect.sendAndReceive("delete from CurrentQuests where Username = \'" + PC.getUsername() + "\' AND QuestID = 19", null);
			connect.sendAndReceive("update Statistics set Experience = " + (PC.getXP() + 30) + " where Username = \'" + PC.getUsername() + "\'", null);
			pc.giveXP(30);
			
			addQuest("17");
		}
	}
	
	
	
	public void checkEng3() {
		/*
		 * Obtain a intellect level of 60
		 * 		checkTasks[66] = 1;
		 */
		if(pc.getIntellect() >= 60)
			checkTasks[66] = 1;

		/*
		 * Study for at least 2 weekends
		 * 		checkTasks[67] = 1;
		 */
		if(questTasks[67] == 1)
			checkTasks[67] = 1;	
		
		
		if(checkTasks[66] == 1 && checkTasks[67] == 1) {
			connect.sendAndReceive("INSERT DoneQuests (Username, QuestID) VALUES (\'" + PC.getUsername() + "\', 16)", null);
			connect.sendAndReceive("delete from CurrentQuests where Username = \'" + PC.getUsername() + "\' AND QuestID = 16", null);
			connect.sendAndReceive("update Statistics set Experience = " + (PC.getXP() + 30) + " where Username = \'" + PC.getUsername() + "\'", null);
			pc.giveXP(30);
			
			addQuest("17");
		}
	}
}
