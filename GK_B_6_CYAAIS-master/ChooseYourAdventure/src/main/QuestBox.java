package main;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

public class QuestBox {
	
	private static ArrayList<String> quests;
	private static JFrame frame = new JFrame("QuestBox");
	private static JPanel mainPanel = new JPanel(new GridLayout(2, 1));
	@SuppressWarnings("unused")
	private PC pc;
	private static PHPConnect connect = new PHPConnect();
	
	
	
	public static void main(String[] args) {
		updateQuests();
		showQuests();
	}
	
	public QuestBox(PC pc){
		this.pc = pc;
		
		ArrayList<String> questCol = new ArrayList<String>();
		questCol.addAll(Arrays.asList("Name", "Description", "Task1", "Task2", "Task3", "ExpEarned"));
		quests = connect.sendAndReceive("select Name, Description, Task1, Task2, Task3, ExpEarned from CurrentQuests cq inner join Quests qq on cq.QuestID = qq.QuestID where cq.Username = \'b3altena\'", questCol);	
		//quests = connect.sendAndReceive("select Name, Description, Task1, Task2, Task3, ExpEarned from CurrentQuests cq inner join Quests qq on cq.QuestID = qq.QuestID where cq.Username = \'b3altena\'", questCol);	
	}
	
	public static  void updateQuests() {
		ArrayList<String> questCol = new ArrayList<String>();
		questCol.addAll(Arrays.asList("Name", "Description", "Task1", "Task2", "Task3", "ExpEarned"));
		quests = connect.sendAndReceive("select Name, Description, Task1, Task2, Task3, ExpEarned from CurrentQuests cq inner join Quests qq on cq.QuestID = qq.QuestID where cq.Username = \'b3altena\'", questCol);	
		//quests = connect.sendAndReceive("select Name, Description, Task1, Task2, Task3, ExpEarned from CurrentQuests cq inner join Quests qq on cq.QuestID = qq.QuestID where cq.Username = \'b3altena\'", questCol);	

		
	    JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.append("Current Quests: " + "\n");
        
        for(int i = 0; i < (quests.size()); i+=6) {
        	chatArea.append("\nQuest " + ((i / 6) + 1) +": " + quests.get(i) + " - (" + quests.get(i + 5) + " Exp)" + "\n");
        	chatArea.append("Description: " + quests.get(i + 1) + "\n");

        	if(!quests.get(i + 2).equals("")) 
        		chatArea.append("Task 1: " + quests.get(i + 2) + "\n");
        	 
        	if(!quests.get(i + 3).equals("")) 
        		chatArea.append("Task 2: " + quests.get(i + 3) + "\n");
        	
        	if(!quests.get(i + 4).equals(""))  
        		chatArea.append("Task 3: " + quests.get(i + 4) + "\n");
        	
        }
	
		

		GroupLayout layout = new GroupLayout(mainPanel);
		mainPanel.setLayout(layout);
		
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
						.addComponent(chatArea, 0, 150, 250)));
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(chatArea, 0, 5, 400)));
	}
	
	
	public JPanel returnQuests() {
		return mainPanel;
	}
	
	public static void showQuests() {
		frame.add(mainPanel);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(250, 400));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
