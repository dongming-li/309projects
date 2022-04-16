package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * @author b3altena
 *
 */
public class StartMenu {

	private String username = "";
	private String password = "";
	private String name = "";
	private int genderID = 0;
	private int check = 0;
	private int specChoice = -1;
	private int clubChoice = -1;
	private int majorChoice = -1;
	private int clothingChoice = -1;
	private static JFrame frame1 = new JFrame("Start Menu");
	private static JFrame frame2 = new JFrame("Signup Menu");
	private static JFrame frame3 = new JFrame("Signup Menu");
	private static JFrame frame4 = new JFrame("Spectator Menu");
	private PHPConnect connect = new PHPConnect();
	@SuppressWarnings("unused")
	private int lastX = -1;
	@SuppressWarnings("unused")
	private int lastY = -1;
	@SuppressWarnings("unused")
	private int isActv = -1;
	ArrayList<String> colUser = new ArrayList<String>();
	ArrayList<String> colPass = new ArrayList<String>();
	ArrayList<String> colName = new ArrayList<String>();
	ArrayList<String> colGender = new ArrayList<String>();
	ArrayList<String> colMajor = new ArrayList<String>();
	ArrayList<String> colClub = new ArrayList<String>();
	ArrayList<String> colClo = new ArrayList<String>();
	ArrayList<String> colX = new ArrayList<String>();
	ArrayList<String> colY = new ArrayList<String>();
	ArrayList<String> colActv = new ArrayList<String>();

	/**
	 * e
	 */
	public StartMenu() {
		colUser.add("Username");
		colPass.add("Password");
		colName.add("Name");
		colGender.add("GenderID");
		colMajor.add("MajorChoice");
		colClub.add("ClubChoice");
		colClo.add("InventoryID");
		colX.add("LastX");
		colY.add("LastY");
		colActv.add("isActv");
		
		loginScreen();
	}

	void loginScreen() {
		JLabel title = new JLabel("Choose your Adventure at Iowa State");
		JLabel userLabel = new JLabel("Username:");
		JLabel passLabel = new JLabel("Password:");
		JTextField user = new JTextField(15);
		JPasswordField pass = new JPasswordField(15);
		JButton login = new JButton("Login");
		JButton signup = new JButton("Signup");
		JButton spectator = new JButton("Spectator");

		login.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				username = user.getText();
				password = pass.getText();

				if (connect.sendAndReceive("select Username from Users where username = \'" + username + "\'", colUser).contains(username)) {
					if (connect.sendAndReceive("select Password from Users where username = \'" + username + "\'", colPass).contains(password)) {
						infoBox("Welcome Back " + username, "Returning Player");
						frame1.dispose();
						check = 1;

						connect.sendAndReceive("update Users set isActv = 1 where username = \"" + username + "\"", null);
						
						return;

					} else {
						infoBox("Wrong Password", "Error Message");

						pass.setText("");
						password = "";
					}
				} else {
					infoBox("No such username exists\nNew Users are encouraged to signup", "Error Message");

					user.setText("");
					username = "";
					pass.setText("");
					password = "";
				}

			}
		});

		signup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				signupScreen1();
			}
		});
		
		spectator.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				spectatorScreen();
			}
		});

		title.setFont(new Font("Comic Sans", Font.BOLD, 32));
		title.setForeground(Color.RED);
		userLabel.setForeground(Color.RED);
		passLabel.setForeground(Color.RED);
		JPanel mainPanel = new ImagePanel(new ImageIcon("Images/ISUCampus2.jpg").getImage());

		GroupLayout layout = new GroupLayout(mainPanel);
		mainPanel.setLayout(layout);

		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
						.addComponent(title))
				.addGroup(layout.createSequentialGroup()
						.addComponent(userLabel, 0, 5, 100)
						.addComponent(user, 0, 5, 250))
				.addGroup(layout.createSequentialGroup()
						.addComponent(passLabel, 0, 5, 100)
						.addComponent(pass, 0, 5, 250))
				.addGroup(layout.createSequentialGroup()
						.addComponent(login, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(signup, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(spectator, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(title, 0, 5, 200))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(userLabel, 0, 5, 10)
						.addComponent(user, 0, 5, 20))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(passLabel, 0, 5, 75)
						.addComponent(pass, 0, 5, 20))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(login)
						.addComponent(signup)
						.addComponent(spectator)));

		frame1.add(mainPanel);

		frame1.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame1.setPreferredSize(new Dimension(700, 500));
		frame1.pack();
		frame1.setLocationRelativeTo(null);
		frame1.setVisible(true);
	}

	
	void spectatorScreen() {
		JLabel title = new JLabel("Choose your Adventure at Iowa State");
		JButton submit = new JButton("Submit");
		JButton back = new JButton("Back");
		
		ArrayList<String> temp = connect.sendAndReceive("select Username from Users where isActv = 1", colUser);
        String[] listData = new String[10];
        
        for(int i = 0; i < temp.size(); i++)
        	listData[i] = temp.get(i);
        
		@SuppressWarnings({ "unchecked", "rawtypes" })
		JList list = new JList(listData);
		
        ListSelectionModel lsm = list.getSelectionModel();
        lsm.addListSelectionListener(new SharedListSelectionHandler());
		JScrollPane listPane = new JScrollPane(list);

		JPanel listContainer = new JPanel(new GridLayout(1,1));
        listContainer.setBorder(BorderFactory.createTitledBorder("Users Online"));
        listContainer.add(listPane);
  
        
        
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (specChoice == -1) {
					infoBox("No user was selected", "Error Message");
	            } else {	
	                check = 1;
	        		frame4.dispose();
	        		return;
	            }
			}
		});

		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame4.dispose();
				loginScreen();
			}
		});

		
		title.setFont(new Font("Comic Sans", Font.BOLD, 32));
		title.setForeground(Color.RED);
		JPanel mainPanel = new ImagePanel(new ImageIcon("Images/ISUCampus2.jpg").getImage());

		GroupLayout layout = new GroupLayout(mainPanel);
		mainPanel.setLayout(layout);

		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
						.addComponent(title))
				.addGroup(layout.createSequentialGroup()
						.addComponent(listContainer))
				.addGroup(layout.createSequentialGroup()
						.addComponent(back, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(submit, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(title, 0, 5, 200))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(listContainer, 0, 5, 150))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(back)
						.addComponent(submit)));

		frame4.add(mainPanel);

		frame4.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame4.setPreferredSize(new Dimension(700, 500));
		frame4.pack();
		frame4.setLocationRelativeTo(null);
		frame4.setVisible(true);
	}
	
		
	
	/**
	 * 
	 */
	void signupScreen1() {
		JLabel title = new JLabel("Choose your Adventure at Iowa State");
		JLabel userLabel = new JLabel("Username:");
		JLabel passLabel = new JLabel("Password:");
		JLabel passLabelCon = new JLabel("Confirm Password:");
		JTextField user = new JTextField(15);
		JPasswordField pass = new JPasswordField(15);
		JPasswordField passCon = new JPasswordField(15);
		JLabel AvatarPic1 = new JLabel(new ImageIcon("Images/Avatar1.jpg"));
		JLabel AvatarPic2 = new JLabel(new ImageIcon("Images/Avatar2.jpg"));
		JLabel AvatarPic3 = new JLabel(new ImageIcon("Images/Avatar3.jpg"));
		JLabel AvatarPic4 = new JLabel(new ImageIcon("Images/Avatar4.jpg"));
		JLabel AvatarPic5 = new JLabel(new ImageIcon("Images/Avatar5.jpg"));
		JLabel AvatarPic6 = new JLabel(new ImageIcon("Images/Avatar6.jpg"));
		JButton Avatar1 = new JButton("Select");
		JButton Avatar2 = new JButton("Select");
		JButton Avatar3 = new JButton("Select");
		JButton Avatar4 = new JButton("Select");
		JButton Avatar5 = new JButton("Select");
		JButton Avatar6 = new JButton("Select");

		Avatar1.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				username = user.getText();
				password = pass.getText();
				String confirmPass = passCon.getText();

				// TO DO: Check if username is in database, DO this for all avatar action listeners

				if (username.length() > 0) {
					if (password.length() > 0) {
						if (confirmPass.length() > 0) {
							if (password.equals(confirmPass)) {
								
								genderID = 1;
								frame2.dispose();
								signupScreen2();
							} else {
								infoBox("Passwords do not match", "Error Message");

								pass.setText("");
								password = "";
								passCon.setText("");
								confirmPass = "";
							}
						} else { infoBox("Please confirm your password", "Error Message"); }
					} else { infoBox("Please enter a password", "Error Message"); }
				} else { infoBox("Please enter a username", "Error Message"); }
			}
		});

		Avatar2.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				username = user.getText();
				password = pass.getText();
				String confirmPass = passCon.getText();

				if (username.length() > 0) {
					if (password.length() > 0) {
						if (confirmPass.length() > 0) {
							if (password.equals(confirmPass)) {
								
								genderID = 2;
								frame2.dispose();
								signupScreen2();
							} else {
								infoBox("Passwords do not match", "Error Message");

								pass.setText("");
								password = "";
								passCon.setText("");
								confirmPass = "";
							}
						} else { infoBox("Please confirm your password", "Error Message"); }
					} else { infoBox("Please enter a password", "Error Message"); }
				} else { infoBox("Please enter a username", "Error Message"); }
			}
		});

		Avatar3.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				username = user.getText();
				password = pass.getText();
				String confirmPass = passCon.getText();

				if (username.length() > 0) {
					if (password.length() > 0) {
						if (confirmPass.length() > 0) {
							if (password.equals(confirmPass)) {
								
								genderID = 3;
								frame2.dispose();
								signupScreen2();
							} else {
								infoBox("Passwords do not match", "Error Message");

								pass.setText("");
								password = "";
								passCon.setText("");
								confirmPass = "";
							}
						} else { infoBox("Please confirm your password", "Error Message"); }
					} else { infoBox("Please enter a password", "Error Message"); }
				} else { infoBox("Please enter a username", "Error Message"); }
			}
		});

		Avatar4.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				username = user.getText();
				password = pass.getText();
				String confirmPass = passCon.getText();

				if (username.length() > 0) {
					if (password.length() > 0) {
						if (confirmPass.length() > 0) {
							if (password.equals(confirmPass)) {
								
								genderID = 4;
								frame2.dispose();
								signupScreen2();
							} else {
								infoBox("Passwords do not match", "Error Message");

								pass.setText("");
								password = "";
								passCon.setText("");
								confirmPass = "";
							}
						} else { infoBox("Please confirm your password", "Error Message"); }
					} else { infoBox("Please enter a password", "Error Message"); }
				} else { infoBox("Please enter a username", "Error Message"); }
			}
		});

		Avatar5.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				username = user.getText();
				password = pass.getText();
				String confirmPass = passCon.getText();

				if (username.length() > 0) {
					if (password.length() > 0) {
						if (confirmPass.length() > 0) {
							if (password.equals(confirmPass)) {
								
								genderID = 5;
								frame2.dispose();
								signupScreen2();
							} else {
								infoBox("Passwords do not match", "Error Message");

								pass.setText("");
								password = "";
								passCon.setText("");
								confirmPass = "";
							}
						} else { infoBox("Please confirm your password", "Error Message"); }
					} else { infoBox("Please enter a password", "Error Message"); }
				} else { infoBox("Please enter a username", "Error Message"); }
			}
		});

		Avatar6.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				username = user.getText();
				password = pass.getText();
				String confirmPass = passCon.getText();

				if (username.length() > 0) {
					if (password.length() > 0) {
						if (confirmPass.length() > 0) {
							if (password.equals(confirmPass)) {
								
								genderID = 6;
								frame2.dispose();
								signupScreen2();
							} else {
								infoBox("Passwords do not match", "Error Message");

								pass.setText("");
								password = "";
								passCon.setText("");
								confirmPass = "";
							}
						} else { infoBox("Please confirm your password", "Error Message"); }
					} else { infoBox("Please enter a password", "Error Message"); }
				} else { infoBox("Please enter a username", "Error Message"); }
			}
		});
		
		
		JPanel one = new JPanel();
		one.setLayout(new BoxLayout(one, BoxLayout.Y_AXIS));
		one.add(AvatarPic1);
		one.add(Avatar1);
		
		JPanel two = new JPanel();
		two.setLayout(new BoxLayout(two, BoxLayout.Y_AXIS));
		two.add(AvatarPic2);
		two.add(Avatar2);
		
		JPanel three = new JPanel();
		three.setLayout(new BoxLayout(three, BoxLayout.Y_AXIS));
		three.add(AvatarPic3);
		three.add(Avatar3);
		
		JPanel four = new JPanel();
		four.setLayout(new BoxLayout(four, BoxLayout.Y_AXIS));
		four.add(AvatarPic4);
		four.add(Avatar4);
		
		JPanel five = new JPanel();
		five.setLayout(new BoxLayout(five, BoxLayout.Y_AXIS));
		five.add(AvatarPic5);
		five.add(Avatar5);
		
		JPanel six = new JPanel();
		six.setLayout(new BoxLayout(six, BoxLayout.Y_AXIS));
		six.add(AvatarPic6);
		six.add(Avatar6);
		
		title.setFont(new Font("Comic Sans", Font.BOLD, 32));
		title.setForeground(Color.RED);
		userLabel.setForeground(Color.RED);
		passLabel.setForeground(Color.RED);
		passLabelCon.setForeground(Color.RED);
		JPanel mainPanel = new ImagePanel(new ImageIcon("Images/ISUCampus2.jpg").getImage());
		
		
		GroupLayout layout = new GroupLayout(mainPanel);
		mainPanel.setLayout(layout);

		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
						.addComponent(title))
				.addGroup(layout.createSequentialGroup()
						.addComponent(userLabel, 0, 5, 100)
						.addComponent(user, 0, 5, 250))
				.addGroup(layout.createSequentialGroup()
						.addComponent(passLabel, 0, 5, 100)
						.addComponent(pass, 0, 5, 250))
				.addGroup(layout.createSequentialGroup()
						.addComponent(passLabelCon, 0, 5, 150)
						.addComponent(passCon, 0, 5, 250))
				.addGroup(layout.createSequentialGroup()
						.addComponent(one, 0, 5, 150)
						.addComponent(two, 0, 5, 150)
						.addComponent(three, 0, 5, 150)
						.addComponent(four, 0, 5, 150)
						.addComponent(five, 0, 5, 150)
						.addComponent(six, 0, 5, 150)));

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(title, 0, 5, 200))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(userLabel, 0, 5, 20)
						.addComponent(user, 0, 5, 20))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(passLabel, 0, 5, 30)
						.addComponent(pass, 0, 5, 20))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(passLabelCon, 0, 5, 40)
						.addComponent(passCon, 0, 5, 20))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(one)
						.addComponent(two)
						.addComponent(three)
						.addComponent(four)
						.addComponent(five)
						.addComponent(six)));
		
		frame2.add(mainPanel);
		
		frame2.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame2.setPreferredSize(new Dimension(700, 500));
		frame2.pack();
		frame2.setLocationRelativeTo(null);
		frame2.setVisible(true);
	}

	/**
	 * 
	 */
	void signupScreen2() {
		JLabel title = new JLabel("Choose your Adventure at Iowa State");
		JLabel AvatarPic = new JLabel(new ImageIcon("Images/Avatar"+ genderID + ".jpg"));
		JLabel nameLabel = new JLabel("In Game Name: ");
		JLabel majorLabel = new JLabel("Major (3 choices)");
		JLabel clubLabel = new JLabel("Club (3 choices)");
		JLabel clothingLabel = new JLabel("Clothing Choice");
		JTextField nameField = new JTextField(15);
		JButton signup = new JButton("Signup");
		
		
		
		// MAJOR OPTIONS - GIT
		JRadioButton engButton = new JRadioButton("Engineering");
		engButton.setMnemonic(KeyEvent.VK_B);
		engButton.setActionCommand("Engineering");
		engButton.setBackground(Color.YELLOW);
		JRadioButton culButton = new JRadioButton("Culinary Arts");
		culButton.setMnemonic(KeyEvent.VK_C);
		culButton.setActionCommand("Culinary Arts");
		culButton.setBackground(Color.YELLOW);
		JRadioButton kinButton = new JRadioButton("Kinesiology (Fitness)");
		kinButton.setMnemonic(KeyEvent.VK_D);
		kinButton.setActionCommand("Kinesiology (Fitness)");
		kinButton.setBackground(Color.YELLOW);

		// CLUB OPTIONS
		JRadioButton basButton = new JRadioButton("Basketball");
		basButton.setMnemonic(KeyEvent.VK_B);
		basButton.setActionCommand("Basketball");
		basButton.setBackground(Color.YELLOW);
		JRadioButton breButton = new JRadioButton("Breakdance");
		breButton.setMnemonic(KeyEvent.VK_C);
		breButton.setActionCommand("Breakdance");
		breButton.setBackground(Color.YELLOW);
		JRadioButton honButton = new JRadioButton("Honors");
		honButton.setMnemonic(KeyEvent.VK_D);
		honButton.setActionCommand("Honors");
		honButton.setBackground(Color.YELLOW);

		// CLOTHING OPTIONS
		JRadioButton larButton = new JRadioButton("Large Glasses");
		larButton.setMnemonic(KeyEvent.VK_B);
		larButton.setActionCommand("Large Glasses");
		larButton.setBackground(Color.YELLOW);
		JRadioButton sunButton = new JRadioButton("SunGlasses");
		sunButton.setMnemonic(KeyEvent.VK_C);
		sunButton.setActionCommand("SunGlasses");
		sunButton.setBackground(Color.YELLOW);
		JRadioButton tigButton = new JRadioButton("Tight Shirt");
		tigButton.setMnemonic(KeyEvent.VK_D);
		tigButton.setActionCommand("Tight Shirt");
		tigButton.setBackground(Color.YELLOW);

		// Group the radio buttons.
		ButtonGroup groupMajor = new ButtonGroup();
		groupMajor.add(engButton);
		groupMajor.add(culButton);
		groupMajor.add(kinButton);

		ButtonGroup groupClub = new ButtonGroup();
		groupClub.add(basButton);
		groupClub.add(breButton);
		groupClub.add(honButton);

		ButtonGroup groupClothing = new ButtonGroup();
		groupClothing.add(larButton);
		groupClothing.add(sunButton);
		groupClothing.add(tigButton);

		engButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				majorChoice = 3;
			}
		});
		culButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				majorChoice = 2;
			}
		});
		kinButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				majorChoice = 1;
			}
		});
		basButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clubChoice = 1;
			}
		});
		breButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clubChoice = 2;
			}
		});
		honButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clubChoice = 3;
			}
		});
		larButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clothingChoice = 1;
			}
		});
		sunButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clothingChoice = 2;
			}
		});
		tigButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clothingChoice = 3;
			}
		});

		signup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name = nameField.getText();

				if (name.equals("")) {
					infoBox("Please enter a name", "Error Message");
				} else {
					if (engButton.isSelected() || culButton.isSelected() || kinButton.isSelected()) {
						if (basButton.isSelected() || breButton.isSelected() || honButton.isSelected()) {
							if (larButton.isSelected() || sunButton.isSelected() || tigButton.isSelected()) {
								check = 1;
								frame3.dispose();
								
//TO DO: Insert into CYA_Users, Statistics, Inventory, Pending/Current/Done Quests
								
								connect.sendAndReceive("INSERT Users (Username, Password, Name, GenderID, MajorChoice, ClubChoice, LastX, LastY, isActv) VALUES (\'" + username + "\', \'" + password + "\', \'"+ name + "\', " + genderID + ", "+ majorChoice + ",  "+ clubChoice + ", 0, 0, 1)", null);
								
								int health = 100;
								int stress = 15;
								int intellect = 25;
							
								if(majorChoice == 1)
									intellect += 5;
								else if(majorChoice == 2)
									stress -= 5;
								else if(majorChoice == 3)
									health += 5;
								
								if(clubChoice == 1)
									health += 5;
								else if(clubChoice == 2)
									stress -= 5;
								else if(clubChoice == 3)
									intellect += 5;
								
								if(clothingChoice == 1)
									intellect += 5;
								else if(clothingChoice == 2)
									stress -= 5;
								else if(clothingChoice == 3)
									health += 5;
								
								connect.sendAndReceive("INSERT Statistics (Username, Health, Stress, Intellect, Cash, Experience) VALUES (\'" + username + "\', " + health + ", "+ stress + ",  "+ intellect + ", 100, 0)", null);
								connect.sendAndReceive("INSERT Inventory (Username, InventoryID) VALUES (\'" + username + "\', " + clothingChoice + ")", null);
								
								//connect.insertUpdate("INSERT PendingQuests (Username, QuestID) VALUES (\"" + username + "\", " + clothingChoice + ")");
								//connect.insertUpdate("INSERT CurrentQuests (Username, QuestID) VALUES (\"" + username + "\", " + clothingChoice + ")");
								
								
								
							} else {
								infoBox("Please choose an article of clothing", "Error Message");
							}
						} else {
							infoBox("Please choose a club", "Error Message");
						}
					} else {
						infoBox("Please choose a major", "Error Message");
					}
				}
			}
		});

		JPanel majorPanel = new JPanel();
		majorPanel.add(engButton);
		majorPanel.add(culButton);
		majorPanel.add(kinButton);
		JPanel clubPanel = new JPanel();
		clubPanel.add(basButton);
		clubPanel.add(breButton);
		clubPanel.add(honButton);
		JPanel clothingPanel = new JPanel();
		clothingPanel.add(larButton);
		clothingPanel.add(sunButton);
		clothingPanel.add(tigButton);

		
		JPanel picHolder = new JPanel();
		picHolder.add(AvatarPic);
		
		title.setFont(new Font("Comic Sans", Font.BOLD, 32));
		title.setForeground(Color.RED);
		nameLabel.setForeground(Color.RED);
		majorLabel.setForeground(Color.RED);
		clubLabel.setForeground(Color.RED);
		clothingLabel.setForeground(Color.RED);
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.YELLOW);
		picHolder.setBackground(Color.YELLOW);
		majorPanel.setBackground(Color.YELLOW);
		clubPanel.setBackground(Color.YELLOW);
		clothingPanel.setBackground(Color.YELLOW);
		
		GroupLayout layout = new GroupLayout(mainPanel);
		mainPanel.setLayout(layout);

		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
						.addComponent(title))
				.addGroup(layout.createSequentialGroup()
						.addComponent(picHolder)
						.addComponent(nameLabel, 0, 5, 100)
						.addComponent(nameField, 0, 5, 250))
				.addGroup(layout.createSequentialGroup()
						.addComponent(majorLabel, 0, 5, 150)
						.addComponent(majorPanel))
				.addGroup(layout.createSequentialGroup()
						.addComponent(clubLabel, 0, 5, 150)
						.addComponent(clubPanel))
				.addGroup(layout.createSequentialGroup()
						.addComponent(clothingLabel, 0, 5, 250)
						.addComponent(clothingPanel)
						.addComponent(signup, 0, 5, 150)));

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(title, 0, 5, 200))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(picHolder)
						.addComponent(nameLabel, 0, 5, 30)
						.addComponent(nameField, 0, 5, 20))	
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(majorLabel, 0, 5, 30)
						.addComponent(majorPanel))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(clubLabel, 0, 5, 30)
						.addComponent(clubPanel))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(clothingLabel, 0, 5, 30)
						.addComponent(clothingPanel)
						.addComponent(signup)));
		
		frame3.add(mainPanel);
		
		
		frame3.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame3.setPreferredSize(new Dimension(700, 500));
		frame3.pack();
		frame3.setLocationRelativeTo(null);
		frame3.setVisible(true);
	}


	
	public String getUsername() {
		return connect.sendAndReceive("select Username from Users where username = \'" + username + "\'", colUser).get(0);
	}

	public String getPassword() {
		return connect.sendAndReceive("select Password from Users where username = \'" + username + "\'", colPass).get(0);
	}

	public String getName() {
		return connect.sendAndReceive("select Name from Users where username = \'" + username + "\'", colName).get(0);
	}

	public int getGenderID() {
		return Integer.parseInt(connect.sendAndReceive("select GenderID from Users where username = \'" + username + "\'", colGender).get(0));
	}

	public int getMajorChoice() {
		return Integer.parseInt(connect.sendAndReceive("select MajorChoice from Users where username = \'" + username + "\'", colMajor).get(0));
	}

	public int getClubChoice() {
		return Integer.parseInt(connect.sendAndReceive("select ClubChoice from Users where username = \'" + username + "\'", colClub).get(0));
	}

	public int getClothingChoice() {
		return Integer.parseInt(connect.sendAndReceive("select InventoryID from Inventory where username = \'" + username + "\'", colClo).get(0));
	}
	
	public int getLastX() {
		return Integer.parseInt(connect.sendAndReceive("select LastX from Users where username = \'" + username + "\'", colX).get(0));
	}
	
	public int getLastY() {
		return Integer.parseInt(connect.sendAndReceive("select LastY from Users where username = \'" + username + "\'", colY).get(0));
	}
	
	public int getActv() {
		return Integer.parseInt(connect.sendAndReceive("select isActv from Users where username = \'" + username + "\'", colActv).get(0));
	}

	public int getCheck() {
		System.out.print(check);
		return check;
	}
	
	public int getSpecChoice() {
		return specChoice;
	}

	public static void infoBox(String infoMessage, String titleBar) {
		JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
	}
	
	
    class SharedListSelectionHandler implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) { 
            ListSelectionModel lsm = (ListSelectionModel)e.getSource();
            if (lsm.isSelectionEmpty()) {
            	
            } else {
                // Find out which indexes are selected.
                int minIndex = lsm.getMinSelectionIndex();
                int maxIndex = lsm.getMaxSelectionIndex();
                for (int i = minIndex; i <= maxIndex; i++) {
                    if (lsm.isSelectedIndex(i)) {
                    	specChoice = i + 1;
                    }
                }
            }
        }
    }
}

@SuppressWarnings("serial")
class ImagePanel extends JPanel {

	  private Image img;

	  public ImagePanel(String img) {
	    this(new ImageIcon(img).getImage());
	  }

	  public ImagePanel(Image img) {
	    this.img = img;
	    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
	    setPreferredSize(size);
	    setMinimumSize(size);
	    setMaximumSize(size);
	    setSize(size);
	    setLayout(null);
	  }

	  public void paintComponent(Graphics g) {
	    g.drawImage(img, 0, 0, null);
	  }
}




