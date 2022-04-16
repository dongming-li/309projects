package main;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class ChatBox {
	
	private static ArrayList<String> chat;
	private static JFrame frame = new JFrame("ChatBox");
	private static JPanel mainPanel = new JPanel(new GridLayout(2, 1));
	@SuppressWarnings("unused")
	private PC pc;
	private static PHPConnect connect = new PHPConnect();
	
	
	
	public static void main(String[] args) {
		//updateChat("Player");
		//showChat();
	}
	
	public ChatBox(PC pc){
		this.pc = pc;
		
		ArrayList<String> chatCol = new ArrayList<String>();
		chatCol.addAll(Arrays.asList("Username", "Type", "Message"));
		chat = connect.sendAndReceive("select Username, Type, Message from Chat where username = \'b3altena\'", chatCol);	
		//chat = connect.sendAndReceive("select Username, Type, Message from Chat where username = \'"+ pc.getUsername() +"\'", chatCol);	
	}
	
	public   void updateChat(String role) {
		ArrayList<String> chatCol = new ArrayList<String>();
		chatCol.addAll(Arrays.asList("Type", "Message"));
		chat = connect.sendAndReceive("select Type, Message from Chat where username = \'b3altena\'", chatCol);	
		//chat = connect.sendAndReceive("select Username, Type, Message from Chat where username = \'"+ pc.getUsername() +"\'", chatCol);	

		
		
	    JButton sendMessage = new JButton("Send");
	    JTextField message = new JTextField(30);
	    JTextArea chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
		
		for(int i = 0; i < (chat.size()); i+=2) {
			if(chat.get(i).equals("S")) {
				chatArea.append("Spectator: " + chat.get(i+1) + "\n");
			} else {
				if(chat.get(i).equals("P")) {
					chatArea.append("Player: " + chat.get(i+1) + "\n");
				}
			}
		}
		
		
		
		sendMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(role.equals("Player")) {
					connect.sendAndReceive("INSERT Chat (Username, Type, Message) VALUES (\'" + "b3altena" + "\', \'P\', \'" + message.getText() + "\')", null);
					chatArea.append("Player: " + message.getText() + "\n");
				} else {
					if(role.equals("Spectator")) {
						connect.sendAndReceive("INSERT Chat (Username, Type, Message) VALUES (\'" + "b3altena" + "\', \'S\', \'" + message.getText() + "\')", null);
						chatArea.append("Spectator: " + message.getText() + "\n");
					}
				}

				
				message.setText("");
				updateChat(role);
				frame.dispose();
				mainPanel.removeAll();
				frame.remove(mainPanel);
				MainFrame.refreshChatPanel();
				
				
/*				if(role.equals("Player"))
					connect.sendAndReceive("INSERT Chat (Username, Type, Message) VALUES (\'" + pc.getUsername() + "\', P, " + messageBox.getText() + ")", null);
				if(role.equals("Spectator"))
					connect.sendAndReceive("INSERT Chat (Username, Type, Message) VALUES (\'" + pc.getUsername() + "\', S, " + messageBox.getText() + ")", null);			
*/			}
		});
		

		GroupLayout layout = new GroupLayout(mainPanel);
		mainPanel.setLayout(layout);
		
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
						.addComponent(chatArea))
				.addGroup(layout.createSequentialGroup()
						.addComponent(message, 0, 150, 250)
						.addComponent(sendMessage, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(chatArea, 0, 5, 200))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(message, 0, 20, 20)
						.addComponent(sendMessage)));
	
	}
	
	
	public JPanel returnChat() {
			mainPanel.setFocusable(true);
		return mainPanel;
	}
	
	public static void showChat() {
		frame.add(mainPanel);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(250, 200));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
