package gameMain;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.text.DefaultCaret;

/**
 * The Class ChatBox.
 */
public class ChatBox {
	
	/** The chat. */
	private static ArrayList<String> chat;
	
	/** The frame. */
	private static JFrame frame = new JFrame("ChatBox");
	
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
		//updateChat("Player");
		//showChat();
	}
	
	/**
	 * Instantiates a new chat box.
	 *
	 * @param pc the pc
	 */
	public ChatBox(PC pc){
		this.pc = pc;
		
		ArrayList<String> chatCol = new ArrayList<String>();
		chatCol.addAll(Arrays.asList("Username", "Type", "Message"));
		chat = connect.sendAndReceive("select Username, Type, Message from Chat where username = \'"+ pc.getUsername() +"\'", chatCol);	
	}
	
	/**
	 * Update chat.
	 *
	 * @param role the role
	 */
	public void updateChat(String role) {
		ArrayList<String> chatCol = new ArrayList<String>();
		chatCol.addAll(Arrays.asList("Type", "Message"));
		chat = connect.sendAndReceive("select Type, Message from Chat where username = \'"+ pc.getUsername() +"\'", chatCol);	

		
		
	    JButton sendMessage = new JButton("Send");
	    JTextField message = new JTextField(30);
	    JTextArea chatArea = new JTextArea();
	    JScrollPane sPane = new JScrollPane(chatArea);
	    
	    DefaultCaret caret = (DefaultCaret)chatArea.getCaret();
	    caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
		
		for(int i = 0; i < (chat.size()); i+=2) {
			if(chat.get(i).equals("S")) {
				if(i < chat.size() -1)
				chatArea.append("Spectator: " + chat.get(i+1) + "\n");
			} else {
				if(chat.get(i).equals("P")&&i<chat.size()-1) {
					chatArea.append("Player: " + chat.get(i+1) + "\n");
				} else {
					if(chat.get(i).equals("A")) {
						chatArea.append("Admin: " + chat.get(i+1) + "\n");
					}
				}
			}
		}
		
		
		
		sendMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(role.equals("Player")) {
					if(!(message.getText().contains("!"))) {
						connect.sendAndReceive("INSERT into Chat (Username, Type, Message) VALUES (\'" + pc.getUsername() + "\', \'P\', \'" + message.getText() + "\')", null);
						chatArea.append("Player: " + message.getText() + "\n");
					} else {
						/*
						 * Spectator Commands
						 * 
						 *  !use Item(lower) - for eating food items
						 *  	Ex: !use Apple
						 *  
						 *  !help - pulls up help pop-up menu
						 *  	Ex: !help
						 *  
						 *  !exit - isGameOver = true, update Users table
						 *  	Ex: !exit
						 *  	
						 */
						Scanner in = new Scanner(message.getText());

						if(message.getText().contains("!use")) {
							String foodItem = "";
							ArrayList<String> items = new ArrayList<String>();
							ArrayList<String> itemCol = new ArrayList<String>();
							itemCol.add("Name");
							
							in.next();
							if(in.hasNext())
								foodItem = in.next();
							else
								infoBox("Please enter a valid food item in your inventory", "Error Message");
							
							items = connect.sendAndReceive("select Name from Inventory inner join Store on Inventory.InventoryID = Store.InventoryID where username = \'" + pc.getUsername() + "\'", itemCol);	
							
							if(items.contains(foodItem)) {
									connect.sendAndReceive("delete from Inventory where InventoryID = (select InventoryID from Store where Name = '"+ foodItem +"') AND username = \'" + pc.getUsername() + "\'", null);	
							} else {
								infoBox("Please enter a valid food \nitem in your inventory\n\nItems are upper case", "Error Message");
							}
						}
						in.close();
						
						
						if(message.getText().equals("!help")) {
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

						}

						
						if(message.getText().equals("!exit")) {
							connect.sendAndReceive("update Users set LastX = " + pc.getCol() + ", LastY = " + pc.getRow() + ", isActv = 0 where username = \'" + pc.getUsername() + "\'", null);	
	
							System.exit(0);
						}

						
					}
				} else {
					if(role.equals("Spectator")) {
						connect.sendAndReceive("INSERT into Chat (Username, Type, Message) VALUES (\'" + pc.getUsername() + "\', \'S\', \'" + message.getText() + "\')", null);
						chatArea.append("Spectator: " + message.getText() + "\n");
					} else {
						if(role.equals("Admin")) {
							if(!(message.getText().contains("!"))) {
								connect.sendAndReceive("INSERT into Chat (Username, Type, Message) VALUES (\'" + pc.getUsername() + "\', \'A\', \'" + message.getText() + "\')", null);
								chatArea.append("Admin: " + message.getText() + "\n");
							} else {
								/*
								 * Admin Commands
								 * 
								 * Teleport, Row, Col - Teleport player to tile if valid
								 * 		Ex: teleport, 3, 100
								 * 
								 * 
								 * 
								 * 			CHANGE STATISTICS
								 * addHealth, X - Give player X amount of Health
								 * 		Ex: addHealth, 50
								 * 
								 * lowerStress, X - Lower stress for player by X amount
								 * 		Ex: lowerStress, 50
								 *
								 * addIntellect, X - Give player X amount of Intellect
								 * 		Ex: addIntellect, 50
								 * 
								 * addCash, X - Give player X amount of Cash
								 * 		Ex: addCash, 50
								 * 
								 * addExp, X - Give player X amount of Experience
								 * 		Ex: addExp, 50
								 * 
								 */
								
								if(message.getText().equals("!help")) {
									infoBox(  "Chat Commands\n"
											+ "!Teleport, Row, Col\n     - Teleport player to tile if valid\n"
											+ "Change Statistics\n"
											+ "!addHealth, X\n     - Give player X amount of Health\n"
											+ "!lowerStress, X\n     - Lower stress for player by X amount"
											+ "!addIntellect, X\n     - Give player X amount of Intellect\n"
											+ "!addCash, X\n     - Give player X amount of Cash\n"
											+ "!addExp, X\n     - Give player X amount of Experience\n"
											, "Help Message");

								}
								
								Scanner in = new Scanner(message.getText());
								int testRow = -1, testCol = -1, testStat = -1;
								
								if(message.getText().contains("!teleport")) {
									if(in.hasNextInt()) { 
										testRow = in.nextInt();
										
										if(in.hasNextInt())
											testCol = in.nextInt();
										else {
											infoBox("Please enter a value \nfor Column", "Error Message");
										}
									}
									else {
										infoBox("Please enter a value \nfor Row and Column", "Error Message");
									}
									
									if(testRow >=0 && testCol >= 0)
										teleport(testRow, testCol);
									else 
										infoBox("Please enter positive numbers", "Error Message");
								} else if(message.getText().contains("!addHealth")) {
									if(in.hasNextInt()) 
										testStat = in.nextInt();
									else {
										infoBox("Please enter a number \nto increase Health", "Error Message");
									}
									
									if(testStat >= 0)
										addHealth(testStat);
									else {
										infoBox("Please enter positive \nnumbers", "Error Message");
									}
								}
								else if(message.getText().contains("!lowerStress")) {
									if(in.hasNextInt()) 
										testStat = in.nextInt();
									else {
										infoBox("Please enter a number \nto decrease Stress", "Error Message");
									}
									
									if(testStat >= 0)
										lowerStress(testStat);
									else {
										infoBox("Please enter positive \nnumbers", "Error Message");
									}
								}
								else if(message.getText().contains("!addIntellect")) {
									if(in.hasNextInt()) 
										testStat = in.nextInt();
									else {
										infoBox("Please enter a number \nto increase Intellect", "Error Message");
									}
									
									if(testStat >= 0)
										addIntellect(testStat);
									else {
										infoBox("Please enter positive \nnumbers", "Error Message");
									}
								}
								else if(message.getText().contains("!addCash")) {
									if(in.hasNextInt()) 
										testStat = in.nextInt();
									else {
										infoBox("Please enter a number \nto increase Cash", "Error Message");
									}
									
									if(testStat >= 0)
										addCash(testStat);
									else {
										infoBox("Please enter positive \nnumbers", "Error Message");
									}
								}
								else if(message.getText().contains("!addExp")) {
									if(in.hasNextInt()) 
										testStat = in.nextInt();
									else {
										infoBox("Please enter a number \nto increase Experience", "Error Message");
									}
									
									if(testStat >= 0)
										addExp(testStat);
									else {
										infoBox("Please enter positive \nnumbers", "Error Message");
									}
								}
								
								in.close();
							}
						}
					}
				}

				
				message.setText("");
				updateChat(role);
				frame.dispose();
				mainPanel.removeAll();
				frame.remove(mainPanel);
				MainFrame.refreshChatPanel();
							
			}
		});
		

		GroupLayout layout = new GroupLayout(mainPanel);
		mainPanel.setLayout(layout);
		
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
						.addComponent(sPane))
				.addGroup(layout.createSequentialGroup()
						.addComponent(message, 0, 150, 250)
						.addComponent(sendMessage, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(sPane, 0, 5, 200))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(message, 0, 20, 20)
						.addComponent(sendMessage)));
	
	}
	
	
	/**
	 * Return chat.
	 *
	 * @return the j panel
	 */
	public JPanel returnChat() {
			mainPanel.setFocusable(true);
		return mainPanel;
	}
	
	/**
	 * Show chat.
	 */
	public static void showChat() {
		frame.add(mainPanel);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(250, 200));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	
	/**
	 * Teleport.
	 *
	 * @param row the row
	 * @param col the col
	 */
	private void teleport(int row, int col) {
		main.map.setRowCol(pc.getRow(), pc.getCol(), pc.getUnderPC());
		if (main.map.isWalkable(row, col)) {
			pc.setUnderPC(main.map.getRowCol(row, col));
		}
		
		if (main.map.placePC(row, col, pc)) {
		} else
			main.map.setRowCol(row, col, '@');				
	}
	
	/**
	 * Adds the health.
	 *
	 * @param amount the amount
	 */
	private void addHealth(int amount) {
		pc.setHealth(pc.getHealth() + 15);
		connect.sendAndReceive("update Statistics set Health = " + pc.getHealth() + " where Username = \'" + pc.getUsername() + "\'", null);
	}
	
	/**
	 * Lower stress.
	 *
	 * @param amount the amount
	 */
	private void lowerStress(int amount) {
		pc.setStress(pc.getStress() + 15);
		connect.sendAndReceive("update Statistics set Stress = " + pc.getStress() + " where Username = \'" + pc.getUsername() + "\'", null);
	}
	
	/**
	 * Adds the intellect.
	 *
	 * @param amount the amount
	 */
	private void addIntellect(int amount) {
		pc.setIntellect(pc.getIntellect() + 15);
		connect.sendAndReceive("update Statistics set Intellect = " + pc.getIntellect() + " where Username = \'" + pc.getUsername() + "\'", null);
	}
	
	/**
	 * Adds the cash.
	 *
	 * @param amount the amount
	 */
	private void addCash(int amount) {
		pc.setCash(pc.getCash() + 15);
		connect.sendAndReceive("update Statistics set Cash = " + pc.getCash() + " where Username = \'" + pc.getUsername() + "\'", null);
	}
	
	/**
	 * Adds the exp.
	 *
	 * @param amount the amount
	 */
	private void addExp(int amount) {
		connect.sendAndReceive("update Statistics set Experience = " + (pc.getXP() + amount) + " where Username = \'" + pc.getUsername() + "\'", null);
		pc.giveXP(amount);
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
