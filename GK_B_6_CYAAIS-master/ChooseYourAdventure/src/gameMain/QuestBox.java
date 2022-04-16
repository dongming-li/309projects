package gameMain;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

/**
 * The Class QuestBox.
 */
public class QuestBox {
	
	/** The quests. */
	private static ArrayList<String> quests;
	
	/** The frame. */
	private static JFrame frame = new JFrame("QuestBox");
	
	/** The main panel. */
	private static JPanel mainPanel = new JPanel(new GridLayout(2, 1));
	
	/** The pc. */
	private PC pc;
	
	/** The connect. */
	private static PHPConnect connect = new PHPConnect();
	
	
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		//updateQuests();
		showQuests();
	}
	
	/**
	 * Instantiates a new quest box.
	 *
	 * @param pc the pc
	 */
	public QuestBox(PC pc){
		this.pc = pc;
		
		ArrayList<String> questCol = new ArrayList<String>();
		questCol.addAll(Arrays.asList("Name", "Description", "Task1", "Task2", "Task3", "ExpEarned"));
		quests = connect.sendAndReceive("select Name, Description, Task1, Task2, Task3, ExpEarned from CurrentQuests cq inner join Quests qq on cq.QuestID = qq.QuestID where cq.Username = \'" + pc.getUsername() + "\'", questCol);	
		//quests = connect.sendAndReceive("select Name, Description, Task1, Task2, Task3, ExpEarned from CurrentQuests cq inner join Quests qq on cq.QuestID = qq.QuestID where cq.Username = \'b3altena\'", questCol);	
	}
	
	/**
	 * Update quests.
	 */
	public   void updateQuests() {
		ArrayList<String> questCol = new ArrayList<String>();
		questCol.addAll(Arrays.asList("Name", "Description", "Task1", "Task2", "Task3", "ExpEarned"));
		quests = connect.sendAndReceive("select Name, Description, Task1, Task2, Task3, ExpEarned from CurrentQuests cq inner join Quests qq on cq.QuestID = qq.QuestID where cq.Username = \'" + pc.getUsername() + "\'", questCol);	
		//quests = connect.sendAndReceive("select Name, Description, Task1, Task2, Task3, ExpEarned from CurrentQuests cq inner join Quests qq on cq.QuestID = qq.QuestID where cq.Username = \'b3altena\'", questCol);	

		
	    JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.append("Current Quests: " + "\n");
        for(int i = 0; i < (quests.size()); i+=6) {
        	if(quests.size()>1){
	//        	System.out.println("size: " + quests.size()+" i: " + (i));
	        	chatArea.append("\nQuest " + ((i / 6) + 1) +": " + quests.get(i) + " - (" + quests.get(i + 5) + " Exp)" + "\n");
	        	chatArea.append("Description: " + quests.get(i + 1) + "\n");
	
	        	if(!quests.get(i + 2).equals("")) 
	        		chatArea.append("Task 1: " + quests.get(i + 2) + "\n");
	        	 
	        	if(!quests.get(i + 3).equals("")) 
	        		chatArea.append("Task 2: " + quests.get(i + 3) + "\n");
	        	
	        	if(!quests.get(i + 4).equals(""))  
	        		chatArea.append("Task 3: " + quests.get(i + 4) + "\n");
        	}
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
	
	
	/**
	 * Return quests.
	 *
	 * @return the j panel
	 */
	public JPanel returnQuests() {
		return mainPanel;
	}
	
	/**
	 * Show quests.
	 */
	public static void showQuests() {
		frame.add(mainPanel);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(250, 400));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
