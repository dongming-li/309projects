package gameMain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * The Class interactableTile.
 */
public class interactableTile {
	
	/** The frame 1. */
	private static JFrame frame1 = new JFrame("Door Menu");
	
	/** The frame 2. */
	private static JFrame frame2 = new JFrame("Bus Menu");
	
	/** The frame 3. */
	private static JFrame frame3 = new JFrame("Store Menu");
	
	/** The panel. */
	private static JPanel panel = new JPanel();
	
	/** The connect. */
	private static PHPConnect connect = new PHPConnect();
	
	/** The major. */
	private static int major;
	
	/** The club. */
	private static int club;
	
	/** The cash. */
	private static int cash;
	
	/** The health. */
	private static int health;
	
	/** The stress. */
	private static int stress;
	
	/** The intellect. */
	private static int intellect;

	/** The quest. */
	private static Quests quest;
	
	/** The pc. */
	private PC pc;
	
	/** The time class. */
	private Time timeClass;
	
	/** The back. */
	private GraphicalStuff back;
	
	/** The last tile. */
	@SuppressWarnings("unused")
	private char lastTile;
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {		
		cash = 50;		//PC.getCash();
		health = 100;	//PC.getHealth();
		stress = 15;	//PC.getStress();
		intellect = 25;	//PC.getIntellect();
	
		major = 2;		//PC.getMajor();
		club = 3;		//PC.getClub();
		

		
		//CooverHall(900, 1);					// Coover - Engineering Major 
		//BlackEngineering(1400, 1);
		//MarstonHall(1400, 1);
		//HooverHall(1000, 1);
		
		//BeyerHall(1500, 1); 				// Beyer - Kinesiology Major
		//Armory(1000, 1);
		//DurhamHall(1000, 1);
		//GilmanHall(1200, 1);
		
		
		//HoweHall(900, 1);					// Culinary Arts Major
		//DesignHall(900, 1);
		//McKayHall(1000, 1);
		//BeardshearHall(1000, 1);
		
		
		//StateGym(1500, 1, 2);				// Basketball Club
		//SnedecorHall(1200, 1);

		
		//ParksLibrary(1500, 1, 2);			// Honors Club
		//SweenyHall(1500, 1);
		/*
		CarverHall(1500, 1, 2);				// Breakdance Club
		AtanasoffHall(1700, 1);
		
		PearsonHall(1300, 5, 2);			// Used by all majors
		CurtissHall(1200, 5, 6);
		
		*/
		//MemorialUnion(1000); 
		//CaribouCafe(1000);		
		
		//BusStop(1900, 5);
	}
	
	

	

	/**
	 * Instantiates a new interactable tile.
	 *
	 * @param pc the pc
	 * @param timeClass the time class
	 * @param back the back
	 */
	public interactableTile(PC pc,Time timeClass,GraphicalStuff back) {	

		//interactableTile.pc = pc;
		quest  = new Quests(pc);
		this.pc =pc;
		this.timeClass=timeClass;
		this.back=back;
		/*
		this.cash = 100;	//PC.getCash();
		health = 100;		//PC.getHealth();
		stress = 15;		//PC.getStress();
		intellect = 25;		//PC.getIntellect();

	
		major = 3;			//PC.getMajor();
		club = 3;			//PC.getClub();
		*/
		
		cash = pc.getCash();		//PC.getCash();
		health = pc.getHealth();	//PC.getHealth();
		stress = pc.getStress();	//PC.getStress();
		intellect = pc.getIntellect();	//PC.getIntellect();
	
		major = pc.getMajor();		//PC.getMajor();
		club = pc.getClub();		//PC.getClub();
		
		frame1.setUndecorated(true);
		frame1.setPreferredSize(new Dimension(300, 400));
		frame1.pack();
		frame1.setLocationRelativeTo(null);
		frame1.setAlwaysOnTop(true);
		
		frame3.setUndecorated(true);
		frame3.setPreferredSize(new Dimension(600, 300));
		frame3.pack();
		frame3.setLocationRelativeTo(null);
		frame3.setAlwaysOnTop(true);
		
		
		frame2.setUndecorated(true);
		frame2.setPreferredSize(new Dimension(300, 300));
		frame2.pack();
		frame2.setLocationRelativeTo(null);
		frame2.setAlwaysOnTop(true);
	}
	
	
	
	
	
	
	/**
	 * Coover hall.
	 *
	 * @param hour the hour
	 * @param day the day
	 * @param map the map
	 */
	///////////////////////////              ENGINEERING
	public  void CooverHall(int hour, int day,map map) {
		JLabel buildingName = new JLabel("Coover Hall (Engineering)");
		JButton button1 = new JButton("Go to Programming class (MWF, 800 - 1000)");
		JButton button2 = new JButton("Talk to teacher for an hour (1200 - 1400)");
		JButton button3 = new JButton("Go out Door 1");
		JButton button4 = new JButton("Go out Door 2");
		JButton back = new JButton("Back");
		
		
		if(timeClass.getHours() > 1000 || timeClass.getHours() < 800 || major != 3 || !(timeClass.getDay() == 1 || timeClass.getDay() == 3 || timeClass.getDay() == 5))
			button1.setEnabled(false);
		if(timeClass.getHours() > 1400 || timeClass.getHours() < 1200 || major != 3)
			button2.setEnabled(false);
		if(major != 3)
			button3.setEnabled(false);
		if(major != 3)
			button4.setEnabled(false);
		button4.setEnabled(true);
		button3.setEnabled(true);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quest.questUpdater(11);
				quest.questUpdater(3);
				
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quest.questUpdater(4);
				
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teleportToDoor( frame1,  panel, map , pc , 35, 75);
			}
		});
		
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teleportToDoor( frame1,  panel, map , pc , 35, 26);
			
			}
		});
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		displayDoor(buildingName, button1, button2, button3, button4, back);
	}
	
	/**
	 * Black engineering.
	 *
	 * @param hour the hour
	 * @param day the day
	 * @param map the map
	 */
	public  void BlackEngineering(int hour, int day,map map) {
		JLabel buildingName = new JLabel("Black Engineering");
		JButton button1 = new JButton("Go to Physics class (MWF, 1100 - 1300)");
		JButton button2 = new JButton("Talk to teacher for an hour (1200 - 1400)");
		JButton button3 = new JButton("Go out Door 1");
		JButton button4 = new JButton("Go out Door 2");
		JButton back = new JButton("Back");
		
		
		if(timeClass.getHours() > 1300 || timeClass.getHours() < 1100 || major != 3 || !(timeClass.getDay() == 1 || timeClass.getDay() == 3 || timeClass.getDay() == 5))
			button1.setEnabled(false);
		if(timeClass.getHours() > 1400 || timeClass.getHours() < 1200 || major != 3)
			button2.setEnabled(false);
		if(major != 3)
			button3.setEnabled(false);
		if(major != 3)
			button4.setEnabled(false);
		button4.setEnabled(true);
		button3.setEnabled(true);
		
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quest.questUpdater(3);
				
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quest.questUpdater(4);
				
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teleportToDoor( frame1,  panel, map , pc , 190, 32);
			}
		});
		
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teleportToDoor( frame1,  panel, map , pc , 167, 60);
			}
		});
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		displayDoor(buildingName, button1, button2, button3, button4, back);
	}
	
	
	/**
	 * Marston hall.
	 *
	 * @param hour the hour
	 * @param day the day
	 * @param map the map
	 */
	public  void MarstonHall(int hour, int day,map map) {
		JLabel buildingName = new JLabel("Marston Hall (Engineering)");
		JButton button1 = new JButton("Go to Calculus class (TR, 800 - 1100)");
		JButton button2 = new JButton("Talk to teacher for an hour (1200 - 1400)");
		JButton button3 = new JButton("Go out Door 1");
		JButton button4 = new JButton("Go out Door 2");
		JButton back = new JButton("Back");
		
		
		if(timeClass.getHours() > 1100 || timeClass.getHours() < 800 || major != 3 || !(timeClass.getDay() == 2 || timeClass.getDay() == 4))
			button1.setEnabled(false);
		if(timeClass.getHours() > 1400 || timeClass.getHours() < 1200 || major != 3)
			button2.setEnabled(false);
		if(major != 3)
			button3.setEnabled(false);
		if(major != 3)
			button4.setEnabled(false);
		
		button4.setEnabled(true);
		button3.setEnabled(true);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quest.questUpdater(3);
				
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quest.questUpdater(4);
				
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teleportToDoor( frame1,  panel, map , pc , 139, 99);
			}
		});
		
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teleportToDoor( frame1,  panel, map , pc , 124, 111);
			}
		});
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		displayDoor(buildingName, button1, button2, button3, button4, back);
	}
	
	
	/**
	 * Hoover hall.
	 *
	 * @param hour the hour
	 * @param day the day
	 * @param map the map
	 */
	public  void HooverHall(int hour, int day,map map) {
		JLabel buildingName = new JLabel("Hoover Hall (Engineering)");
		JButton button1 = new JButton("Go to Therodynamics class (TR, 1100 - 1400)");
		JButton button2 = new JButton("Talk to teacher for an hour (1200 - 1400)");
		JButton button3 = new JButton("Go out Door 1");
		JButton button4 = new JButton("Go out Door 2");
		JButton back = new JButton("Back");
		
		button4.setEnabled(true);
		button3.setEnabled(true);
		if(timeClass.getHours() > 1400 || timeClass.getHours() < 1100 || major != 3 || !(timeClass.getDay() == 2 || timeClass.getDay() == 4))
			button1.setEnabled(false);
		if(timeClass.getHours() > 1400 || timeClass.getHours() < 1200 || major != 3)
			button2.setEnabled(false);
		if(major != 3)
			button3.setEnabled(false);
		if(major != 3)
			button4.setEnabled(false);
		
		button4.setEnabled(true);
		button3.setEnabled(true);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quest.questUpdater(3);
				
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quest.questUpdater(4);
				
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teleportToDoor( frame1,  panel, map , pc , 138, 77);
			}
		});
		
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teleportToDoor( frame1,  panel, map , pc , 127, 13);
			}
		});
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		displayDoor(buildingName, button1, button2, button3, button4, back);
	}
	
	
	
	/**
	 * Beyer hall.
	 *
	 * @param hour the hour
	 * @param day the day
	 * @param map the map
	 */
	///////////////////////////              FITNESS
	public  void BeyerHall(int hour, int day,map map) {
		JLabel buildingName = new JLabel("Beyer Hall (Kinesiology)");
		JButton button1 = new JButton("Go to Fitness class (MWF, 800 - 1000)");
		JButton button2 = new JButton("Talk to teacher for an hour (1200 - 1400)");
		JButton button3 = new JButton("Go out Door 1");
		JButton button4 = new JButton("Go out Door 2");
		JButton back = new JButton("Back");
		
		
		if(timeClass.getHours() > 1000 || timeClass.getHours() < 800 || major != 1 || !(timeClass.getDay() == 1 || timeClass.getDay() == 3 || timeClass.getDay() == 5))
			button1.setEnabled(false);
		if(timeClass.getHours() > 1400 || timeClass.getHours() < 1200 || major != 1)
			button2.setEnabled(false);
		if(major != 1)
			button3.setEnabled(false);
		if(major != 1)
			button4.setEnabled(false);
		button4.setEnabled(true);
		button3.setEnabled(true);
		
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quest.questUpdater(21);
				quest.questUpdater(3);
				
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quest.questUpdater(4);
				
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		displayDoor(buildingName, button1, button2, button3, button4, back);
	}
	
	
	/**
	 * Armory.
	 *
	 * @param hour the hour
	 * @param day the day
	 * @param map the map
	 */
	public  void Armory(int hour, int day,map map) {
		JLabel buildingName = new JLabel("Armory (Kinesiology)");
		JButton button1 = new JButton("Go to Biomed class (MWF, 1100 - 1300)");
		JButton button2 = new JButton("Talk to teacher for an hour (1200 - 1400)");
		JButton button3 = new JButton("Go out Door 1");
		JButton button4 = new JButton("Go out Door 2");
		JButton back = new JButton("Back");
		
		
		if(timeClass.getHours() > 1300 || timeClass.getHours() < 1100 || major != 1 || !(timeClass.getDay() == 1 || timeClass.getDay() == 3 || timeClass.getDay() == 5))
			button1.setEnabled(false);
		if(timeClass.getHours() > 1400 || timeClass.getHours() < 1200 || major != 1)
			button2.setEnabled(false);
		if(major != 1)
			button3.setEnabled(false);
		if(major != 1)
			button4.setEnabled(false);
		
		button4.setEnabled(true);
		button3.setEnabled(true);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quest.questUpdater(3);
				
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quest.questUpdater(4);
				
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				
				
				
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		displayDoor(buildingName, button1, button2, button3, button4, back);
	}
	
	
	/**
	 * Durham hall.
	 *
	 * @param hour the hour
	 * @param day the day
	 * @param map the map
	 */
	public  void DurhamHall(int hour, int day,map map) {
		JLabel buildingName = new JLabel("Durham Hall (Kinesiology)");
		JButton button1 = new JButton("Go to Aerodynamics class (TR, 800 - 1100)");
		JButton button2 = new JButton("Talk to teacher for an hour (1200 - 1400)");
		JButton button3 = new JButton("Go out Door 1");
		JButton button4 = new JButton("Go out Door 2");
		JButton back = new JButton("Back");
		
		
		if(timeClass.getHours() > 1100 || timeClass.getHours() < 800 || major != 1 || !(timeClass.getDay() == 2 || timeClass.getDay() == 4))
			button1.setEnabled(false);
		if(timeClass.getHours() > 1400 || timeClass.getHours() < 1200 || major != 1)
			button2.setEnabled(false);
		if(major != 1)
			button3.setEnabled(false);
		if(major != 1)
			button4.setEnabled(false);
		
		button4.setEnabled(true);
		button3.setEnabled(true);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quest.questUpdater(3);
				
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quest.questUpdater(4);
				
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teleportToDoor( frame1,  panel, map , pc , 94, 112);
			}
		});
		
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teleportToDoor( frame1,  panel, map , pc , 65, 120);
			}
		});
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		displayDoor(buildingName, button1, button2, button3, button4, back);
	}
	
	
	/**
	 * Gilman hall.
	 *
	 * @param hour the hour
	 * @param day the day
	 * @param map the map
	 */
	public  void GilmanHall(int hour, int day, map map) {
		JLabel buildingName = new JLabel("Gilman Hall (Kinesiology)");
		JButton button1 = new JButton("Go to Strategy class (TR, 1100 - 1400)");
		JButton button2 = new JButton("Talk to teacher for an hour (1200 - 1400)");
		JButton button3 = new JButton("Go out Door 1");
		JButton button4 = new JButton("Go out Door 2");
		JButton back = new JButton("Back");
		
		
		if(timeClass.getHours() > 1400 || timeClass.getHours() < 1100 || major != 1 || !(timeClass.getDay() == 2 || timeClass.getDay() == 4))
			button1.setEnabled(false);
		if(timeClass.getHours() > 1400 || timeClass.getHours() < 1200 || major != 1)
			button2.setEnabled(false);
		if(major != 1)
			button3.setEnabled(false);
		if(major != 1)
			button4.setEnabled(false);
		
		button4.setEnabled(true);
		button3.setEnabled(true);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quest.questUpdater(3);
				
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quest.questUpdater(4);
				
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		displayDoor(buildingName, button1, button2, button3, button4, back);
	}
	
	
	
	/**
	 * Howe hall.
	 *
	 * @param hour the hour
	 * @param day the day
	 * @param map the map
	 */
	///////////////////////////              CULINARY ARTS
	public  void HoweHall(int hour, int day,map map) {
		JLabel buildingName = new JLabel("Howe Hall (Culinary Arts)");
		JButton button1 = new JButton("Go to Nutrition class (MWF, 800 - 1000)");
		JButton button2 = new JButton("Talk to teacher for an hour (1200 - 1400)");
		JButton button3 = new JButton("Go out Door 1");
		JButton button4 = new JButton("Go out Door 2");
		JButton back = new JButton("Back");
		
		
		if(timeClass.getHours() > 1000 || timeClass.getHours() < 800 || major != 2 || !(timeClass.getDay() == 1 || timeClass.getDay() == 3 || timeClass.getDay() == 5))
			button1.setEnabled(false);
		if(timeClass.getHours() > 1400 || timeClass.getHours() < 1200 || major != 2)
			button2.setEnabled(false);
		if(major != 2)
			button3.setEnabled(false);
		if(major != 2)
			button4.setEnabled(false);
		
		button4.setEnabled(true);
		button3.setEnabled(true);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quest.questUpdater(31);
				quest.questUpdater(3);
				
				teleportToDoor( frame1,  panel, map , pc , 112, 1);
			}
		});
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quest.questUpdater(4);
				
				teleportToDoor( frame1,  panel, map , pc , 112, 1);
			}
		});
		
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teleportToDoor( frame1,  panel, map , pc , 112, 1);
			}
		});
		
		displayDoor(buildingName, button1, button2, button3, button4, back);
	}
	
	
	/**
	 * Design hall.
	 *
	 * @param hour the hour
	 * @param day the day
	 * @param map the map
	 */
	public  void DesignHall(int hour, int day,map map) {
		JLabel buildingName = new JLabel("Design Hall (Culinary Arts)");
		JButton button1 = new JButton("Go to Baking class (MWF, 1100 - 1300)");
		JButton button2 = new JButton("Talk to teacher for an hour (1200 - 1400)");
		JButton button3 = new JButton("Go out Door 1");
		JButton button4 = new JButton("Go out Door 2");
		JButton back = new JButton("Back");
		
		
		if(timeClass.getHours() > 1300 || timeClass.getHours() < 1100 || major != 2 || !(timeClass.getDay() == 1 || timeClass.getDay() == 3 || timeClass.getDay() == 5))
			button1.setEnabled(false);
		if(timeClass.getHours() > 1400 || timeClass.getHours() < 1200 || major != 2)
			button2.setEnabled(false);
		if(major != 2)
			button3.setEnabled(false);
		if(major != 2)
			button4.setEnabled(false);
		
		button4.setEnabled(true);
		button3.setEnabled(true);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quest.questUpdater(3);
				
				teleportToDoor( frame1,  panel, map , pc , 13, 1);
			}
		});
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quest.questUpdater(4);
				
				teleportToDoor( frame1,  panel, map , pc , 13, 1);
			}
		});
		
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teleportToDoor( frame1,  panel, map , pc , 13, 1);
			}
		});
		
		displayDoor(buildingName, button1, button2, button3, button4, back);
	}
	
	
	/**
	 * Mc kay hall.
	 *
	 * @param hour the hour
	 * @param day the day
	 * @param map the map
	 */
	public  void McKayHall(int hour, int day,map map) {
		JLabel buildingName = new JLabel("McKay Hall (Culinary Arts)");
		JButton button1 = new JButton("Go to Pre-Calc class (TR, 800 - 1100)");
		JButton button2 = new JButton("Talk to teacher for an hour (1200 - 1400)");
		JButton button3 = new JButton("Go out Door 1");
		JButton button4 = new JButton("Go out Door 2");
		JButton back = new JButton("Back");
		
		
		if(timeClass.getHours() > 1100 || timeClass.getHours() < 800 || major != 2 || !(timeClass.getDay() == 2 || timeClass.getDay() == 4))
			button1.setEnabled(false);
		if(timeClass.getHours() > 1400 || timeClass.getHours() < 1200 || major != 2)
			button2.setEnabled(false);
		if(major != 2)
			button3.setEnabled(false);
		if(major != 2)
			button4.setEnabled(false);
		
		button4.setEnabled(true);
		button3.setEnabled(true);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quest.questUpdater(3);
				
				teleportToDoor( frame1,  panel, map , pc , 13, 201);
			}
		});
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quest.questUpdater(4);
				
				teleportToDoor( frame1,  panel, map , pc , 13, 201);
			}
		});
		
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teleportToDoor( frame1,  panel, map , pc , 13, 201);
			}
		});
		
		displayDoor(buildingName, button1, button2, button3, button4, back);
	}
	
	
	/**
	 * Beardshear hall.
	 *
	 * @param hour the hour
	 * @param day the day
	 * @param map the map
	 */
	public  void BeardshearHall(int hour, int day,map map) {
		JLabel buildingName = new JLabel("Beardshear Hall (Culinary Arts)");
		JButton button1 = new JButton("Go to Chemistry class (TR, 1100 - 1400)");
		JButton button2 = new JButton("Talk to teacher for an hour (1200 - 1400)");
		JButton button3 = new JButton("Go out Door 1");
		JButton button4 = new JButton("Go out Door 2");
		JButton back = new JButton("Back");
		
		
		if(timeClass.getHours() > 1400 || timeClass.getHours() < 1100 || major != 2 || !(timeClass.getDay() == 2 || timeClass.getDay() == 4))
			button1.setEnabled(false);
		if(timeClass.getHours() > 1400 || timeClass.getHours() < 1200 || major != 2)
			button2.setEnabled(false);
		if(major != 2)
			button3.setEnabled(false);
		if(major != 2)
			button4.setEnabled(false);
		button4.setEnabled(true);
		button3.setEnabled(true);
		
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quest.questUpdater(3);
				
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quest.questUpdater(4);
				
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teleportToDoor( frame1,  panel, map , pc , 110, 183);
			}
		});
		
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teleportToDoor( frame1,  panel, map , pc , 160, 160);
			}
		});
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		displayDoor(buildingName, button1, button2, button3, button4, back);
	}
	
	
	
	
	/**
	 * State gym.
	 *
	 * @param hour the hour
	 * @param day the day
	 * @param week the week
	 * @param map the map
	 */
	///////////////////////////              BASKETBALL CLUB
	public  void StateGym(int hour, int day, int week, map map) {
		JLabel buildingName = new JLabel("State Gym");
		JButton button1 = new JButton("Go to Basketball Club (R, 1400 - 1600)");
		JButton button2 = new JButton("Basketball Game (Week 2, F, 1700 - 1900)");
		JButton button3 = new JButton("Go out Door 1");
		JButton button4 = new JButton("Go out Door 2");
		JButton back = new JButton("Back");
		
		
		if(timeClass.getHours() > 1600 || timeClass.getHours() < 1400 || club != 1 || timeClass.getDay() != 4)
			button1.setEnabled(false);
		if(timeClass.getHours() > 1900 || timeClass.getHours() < 1700 || club != 1 || timeClass.getDay() != 5 || timeClass.getWeek() != 2)
			button2.setEnabled(false);
		button4.setEnabled(true);
		button3.setEnabled(true);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pc.setHealth(pc.getHealth() + 10);
				connect.sendAndReceive("update Statistics set Health = " + pc.getHealth() + " where Username = \'" + pc.getUsername() + "\'", null);
				quest.questUpdater(43);
				
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pc.setHealth(pc.getHealth() + 20);
				connect.sendAndReceive("update Statistics set Health = " + pc.getHealth() + " where Username = \'" + pc.getUsername() + "\'", null);
				quest.questUpdater(45);
				
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		displayDoor(buildingName, button1, button2, button3, button4, back);
	}
	
	
	
	/**
	 * Snedecor hall.
	 *
	 * @param hour the hour
	 * @param day the day
	 * @param map the map
	 */
	public  void SnedecorHall(int hour, int day,map map) {
		JLabel buildingName = new JLabel("Snedecor Hall");
		JButton button1 = new JButton("Practice basketball (T, 1400 - 1600)");
		JButton button2 = new JButton("Workout for 4 hours (800 - 1700)");
		JButton button3 = new JButton("Go out Door 1");
		JButton button4 = new JButton("Go out Door 2");
		JButton back = new JButton("Back");
		
		
		if(timeClass.getHours() > 1600 || timeClass.getHours() < 1400 || club != 1 || timeClass.getDay() != 2)
			button1.setEnabled(false);
		if(timeClass.getHours() > 1700 || timeClass.getHours() < 800)
			button2.setEnabled(false);
		button4.setEnabled(true);
		button3.setEnabled(true);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pc.setHealth(pc.getHealth() + 5);
				connect.sendAndReceive("update Statistics set Health = " + pc.getHealth() + " where Username = \'" + pc.getUsername() + "\'", null);
				quest.questUpdater(41);
				
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pc.setHealth(pc.getHealth() + 15);
				connect.sendAndReceive("update Statistics set Health = " + pc.getHealth() + " where Username = \'" + pc.getUsername() + "\'", null);
				quest.questUpdater(42);
				
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teleportToDoor( frame1,  panel, map , pc , 37, 111);
			}
			
		});
		
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teleportToDoor( frame1,  panel, map , pc , 21, 123);
				
			}
		});
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		displayDoor(buildingName, button1, button2, button3, button4, back);
	}

	
	
	
	/**
	 * Carver hall.
	 *
	 * @param hour the hour
	 * @param day the day
	 * @param week the week
	 * @param map the map
	 */
	///////////////////////////              BREAKDANCE CLUB
	public  void CarverHall(int hour, int day, int week,map map) {
		JLabel buildingName = new JLabel("Carver Hall");
		JButton button1 = new JButton("Go to Breakdance Club (R, 1400 - 1600)");
		JButton button2 = new JButton("Breakdance Competition (Week 2, F, 1700 - 1900)");
		JButton button3 = new JButton("Go out Door 1");
		JButton button4 = new JButton("Go out Door 2");
		JButton back = new JButton("Back");
		
		
		if(timeClass.getHours() > 1600 || timeClass.getHours() < 1400 || club != 2 || timeClass.getDay() != 4)
			button1.setEnabled(false);
		if(timeClass.getHours() > 1900 || timeClass.getHours() < 1700 || club != 2 || timeClass.getDay() != 5 || timeClass.getWeek() != 2)
			button2.setEnabled(false);
		button4.setEnabled(true);
		button3.setEnabled(true);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pc.setStress(pc.getStress() - 10);
				connect.sendAndReceive("update Statistics set Stress = " + pc.getStress() + " where Username = \'" + pc.getUsername() + "\'", null);
				quest.questUpdater(53);
				
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pc.setStress(pc.getStress() - 20);
				connect.sendAndReceive("update Statistics set Stress = " + pc.getStress() + " where Username = \'" + pc.getUsername() + "\'", null);
				quest.questUpdater(55);
				
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		displayDoor(buildingName, button1, button2, button3, button4, back);
	}
	
	
	
	/**
	 * Atanasoff hall.
	 *
	 * @param hour the hour
	 * @param day the day
	 * @param map the map
	 */
	public  void AtanasoffHall(int hour, int day,map map) {
		JLabel buildingName = new JLabel("Carver Hall");
		JButton button1 = new JButton("Study dancing (T, 1400 - 1600)");
		JButton button2 = new JButton("Dance for 4 hours (800 - 1700)");
		JButton button3 = new JButton("Go out Door 1");
		JButton button4 = new JButton("Go out Door 2");
		JButton back = new JButton("Back");
		
		
		if(timeClass.getHours() > 1600 || timeClass.getHours() < 1400 || club != 2 || timeClass.getDay() != 2)
			button1.setEnabled(false);
		if(timeClass.getHours() > 1700 || timeClass.getHours() < 800)
			button2.setEnabled(false);
		button4.setEnabled(true);
		button3.setEnabled(true);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pc.setStress(pc.getStress() - 5);
				connect.sendAndReceive("update Statistics set Stress = " + pc.getStress() + " where Username = \'" + pc.getUsername() + "\'", null);
				quest.questUpdater(51);
				
				frame1.dispose();
			}
		});
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pc.setStress(pc.getStress() - 15);
				connect.sendAndReceive("update Statistics set Stress = " + pc.getStress() + " where Username = \'" + pc.getUsername() + "\'", null);
				quest.questUpdater(52);
				
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			teleportToDoor( frame1,  panel, map , pc , 49, 96);
			}
		});
				
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teleportToDoor( frame1,  panel, map , pc , 39, 99);
			}
		});
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		displayDoor(buildingName, button1, button2, button3, button4, back);
	}
	
	
	
	/**
	 * Parks library.
	 *
	 * @param hour the hour
	 * @param day the day
	 * @param week the week
	 * @param map the map
	 */
	///////////////////////////              HONORS CLUB
	public  void ParksLibrary(int hour, int day, int week ,map map) {
		JLabel buildingName = new JLabel("Parks Library");
		JButton button1 = new JButton("Go to Honors Club (R, 1400 - 1600)");
		JButton button2 = new JButton("Honors Competition (Week 2, F, 1700 - 1900)");
		JButton button3 = new JButton("Go out Door 1");
		JButton button4 = new JButton("Go out Door 2");
		JButton back = new JButton("Back");
		
		
		if(timeClass.getHours() > 1600 || timeClass.getHours() < 1400 || club != 3 || timeClass.getDay() != 4)
			button1.setEnabled(false);
		if(timeClass.getHours() > 1900 || timeClass.getHours() < 1700 || club != 3 || timeClass.getDay() != 5 || timeClass.getWeek() != 2)
			button2.setEnabled(false);
		button4.setEnabled(true);
		button3.setEnabled(true);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pc.setIntellect(pc.getIntellect() + 10);
				connect.sendAndReceive("update Statistics set Intellect = " + pc.getIntellect() + " where Username = \'" + pc.getUsername() + "\'", null);
				quest.questUpdater(63);
				
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pc.setIntellect(pc.getIntellect() + 20);
				connect.sendAndReceive("update Statistics set Intellect = " + pc.getIntellect() + " where Username = \'" + pc.getUsername() + "\'", null);
				quest.questUpdater(65);
				
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		displayDoor(buildingName, button1, button2, button3, button4, back);
	}
	
	
	/**
	 * Sweeny hall.
	 *
	 * @param hour the hour
	 * @param day the day
	 * @param map the map
	 */
	public  void SweenyHall(int hour, int day, map map) {
		JLabel buildingName = new JLabel("Sweeny Hall");
		JButton button1 = new JButton("Study for Honors (T, 1400 - 1600)");
		JButton button2 = new JButton("Read a book for 4 hours (800 - 1700)");
		JButton button3 = new JButton("Go out Door 1");
		JButton button4 = new JButton("Go out Door 2");
		JButton back = new JButton("Back");
		
		
		if(timeClass.getHours() > 1600 || timeClass.getHours() < 1400 || club != 3 || timeClass.getDay() != 2)
			button1.setEnabled(false);
		if(timeClass.getHours() > 1700 || timeClass.getHours() < 800)
			button2.setEnabled(false);
		button4.setEnabled(true);
		button3.setEnabled(true);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pc.setIntellect(pc.getIntellect() + 5);
				connect.sendAndReceive("update Statistics set Intellect = " + pc.getIntellect() + " where Username = \'" + pc.getUsername() + "\'", null);
				quest.questUpdater(61);
				
				frame1.dispose();
			}
		});
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pc.setIntellect(pc.getIntellect() + 15);
				connect.sendAndReceive("update Statistics set Intellect = " + pc.getIntellect() + " where Username = \'" + pc.getUsername() + "\'", null);
				quest.questUpdater(62);
				
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teleportToDoor( frame1,  panel, map , pc , 89, 80);
			}
		});
		
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teleportToDoor( frame1,  panel, map , pc , 75, 34);
			}
		});
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		displayDoor(buildingName, button1, button2, button3, button4, back);
	}
	
	
	
	
	
	/**
	 * Pearson hall.
	 *
	 * @param hour the hour
	 * @param day the day
	 * @param week the week
	 * @param map the map
	 */
	///////////////////////////              TESTING CENTERS
	public void PearsonHall(int hour, int day, int week,map map) {
		JLabel buildingName = new JLabel("Pearson Hall - Testing Center");
		JButton button1 = new JButton("Take a Quiz (Week 2, F, 1000 - 1400)");
		JButton button2 = new JButton("Take an Exam (Week 4, F, 1000 - 1400)");
		JButton button3 = new JButton("Go out Door 1");
		JButton button4 = new JButton("Go out Door 2");
		JButton back = new JButton("Back");
		
		
		if(timeClass.getHours() > 1400 || timeClass.getHours() < 1000 || timeClass.getDay() != 5 || timeClass.getWeek() != 2)
			button1.setEnabled(false);
		if(timeClass.getHours() > 1400 || timeClass.getHours() < 1000 || timeClass.getDay() != 5 || timeClass.getWeek() != 4)
			button2.setEnabled(false);
		button4.setEnabled(true);
		button3.setEnabled(true);
		
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quest.questUpdater(8);
				
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quest.questUpdater(9);
				
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teleportToDoor( frame1,  panel, map , pc , 171, 95);
			}
		});
		
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teleportToDoor( frame1,  panel, map , pc , 159, 112);
			}
		});
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		displayDoor(buildingName, button1, button2, button3, button4, back);
	}
	
	
	
	/**
	 * Curtiss hall.
	 *
	 * @param hour the hour
	 * @param day the day
	 * @param week the week
	 * @param map the map
	 */
	public  void CurtissHall(int hour, int day, int week,map map) {
		JLabel buildingName = new JLabel("Pearson Hall - Testing Center");
		
		/*
		 * Depending on club choose the building option 
		 */
		String clubFinal = "";
		if(pc.getMajor() == 1)
			clubFinal = "Strongest Student Competition (Week 4, F, 1800 - 2000)";
		if(pc.getMajor() == 2)
			clubFinal = "Best Chef Competition (Week 4, F, 1800 - 2000)";
		if(pc.getMajor() == 3)
			clubFinal = "Smartest Student Competition (Week 4, F, 1800 - 2000)";

		
		JButton button1 = new JButton(clubFinal);
		JButton button3 = new JButton("Go out Door 1");
		JButton back = new JButton("Back");
		
		
		if(timeClass.getHours() > 2000 || timeClass.getHours() < 1800 || timeClass.getDay() != 5 || timeClass.getWeek() != 4)
			button1.setEnabled(false);
		
		
		
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quest.questUpdater(99);
				
				teleportToDoor( frame1,  panel, map , pc , 112, 201);
			}
		});
		
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teleportToDoor( frame1,  panel, map , pc , 112, 201);
			}
		});
		
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				teleportToDoor( frame1,  panel, map , pc , 112, 201);
			}
		});

		
		frame1.add(panel);
		frame1.revalidate();
		panel.setLayout(new GridLayout(4,1));
		buildingName.setFont(new Font("Comic Sans", Font.BOLD, 20));
		buildingName.setForeground(Color.RED);
		buildingName.setHorizontalAlignment(SwingConstants.CENTER);
		panel.setAlignmentX(SwingConstants.CENTER);
		panel.add(buildingName);
		panel.add(button1);
		panel.add(button3);
		panel.add(back);
		
		frame1.revalidate();
		
		
		frame1.setVisible(true);
	}
	
	
	
	/**
	 * Display door.
	 *
	 * @param buildingName the building name
	 * @param button1 the button 1
	 * @param button2 the button 2
	 * @param button3 the button 3
	 * @param button4 the button 4
	 * @param back the back
	 */
	private static void displayDoor(JLabel buildingName, JButton button1, JButton button2, JButton button3, JButton button4, JButton back) {
		frame1.add(panel);
		frame1.revalidate();
		panel.setLayout(new GridLayout(6,1));
		buildingName.setFont(new Font("Comic Sans", Font.BOLD, 20));
		buildingName.setForeground(Color.RED);
		buildingName.setHorizontalAlignment(SwingConstants.CENTER);
		panel.setAlignmentX(SwingConstants.CENTER);
		panel.add(buildingName);
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		panel.add(button4);
		panel.add(back);
		
		frame1.revalidate();
		
		
		frame1.setVisible(true);
	}
	

	/**
	 * Bus stop.
	 *
	 * @param hour the hour
	 * @param day the day
	 * @param map the map
	 * @param name the name
	 */
	///////////////////////////              BUS STOP
	public  void BusStop(int hour, int day,map map,String name) {
		JLabel busName = new JLabel("Bus Stop");
		JButton button1 = new JButton("Go Home (MTWRF, 1800 - 2200)");
		JButton button2 = new JButton("Workout this Weekend (F, 1800 - 2200)");
		JButton button3 = new JButton("Party this Weekend (F, 1800 - 2200)");
		JButton button4 = new JButton("Study this Weekend (F, 1800 - 2200)");
		JButton back = new JButton("Back");
		

		if(timeClass.getHours() > 2200 || timeClass.getHours() < 1800)
			button1.setEnabled(false);
		if((timeClass.getHours() > 2200 || timeClass.getHours() < 1800) || timeClass.getDay() != 5)
			button2.setEnabled(false);
		if((timeClass.getHours() > 2200 || timeClass.getHours() < 1800) || timeClass.getDay() != 5)
			button3.setEnabled(false);
		if((timeClass.getHours() > 2200 || timeClass.getHours() < 1800) || timeClass.getDay() != 5)
			button4.setEnabled(false);
		
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				if(name=="North") {
					teleportToDoor( frame2,  panel, map , pc , 6, 102);
					}
					else if(name=="West") {
					teleportToDoor( frame2,  panel, map , pc , 107, 7);
					}
					else if(name=="South") {
						teleportToDoor( frame2,  panel, map , pc , 207, 102);
						}
					else if(name=="East") {
						teleportToDoor( frame2,  panel, map , pc , 107, 195);
						}			
			}
		});
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pc.setHealth(pc.getHealth() + 5);
				connect.sendAndReceive("update Statistics set Health = " + pc.getHealth() + " where Username = \'" + pc.getUsername() + "\'", null);			
				pc.setStress(pc.getStress() + 5);
				connect.sendAndReceive("update Statistics set Stress = " + pc.getStress() + " where Username = \'" + pc.getUsername() + "\'", null);
				
				quest.questUpdater(47);
				
				if(name=="North") {
					teleportToDoor( frame2,  panel, map , pc , 6, 102);
					}
					else if(name=="West") {
					teleportToDoor( frame2,  panel, map , pc , 107, 7);
					}
					else if(name=="South") {
						teleportToDoor( frame2,  panel, map , pc , 207, 102);
						}
					else if(name=="East") {
						teleportToDoor( frame2,  panel, map , pc , 107, 195);
						}			
			}
		});
		
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pc.setStress(pc.getStress() - 5);
				connect.sendAndReceive("update Statistics set Stress = " + pc.getStress() + " where Username = \'" + pc.getUsername() + "\'", null);			
				pc.setIntellect(pc.getIntellect() - 5);
				connect.sendAndReceive("update Statistics set Intellect = " + pc.getIntellect() + " where Username = \'" + pc.getUsername() + "\'", null);
				quest.questUpdater(57);
				
				
				timeClass.setDay(Time.MONDAY);
				timeClass.setNameDate(Time.MON);
				timeClass.setHours(100);
				timeClass.setMinutes(0);
				//map.setOnBusStop(false);
				//map.setOnDoor(false);
				//map.setOnGrass(false);
				if(name=="North") {
				teleportToDoor( frame2,  panel, map , pc , 6, 102);
				}
				else if(name=="West") {
				teleportToDoor( frame2,  panel, map , pc , 107, 7);
				}
				else if(name=="South") {
					teleportToDoor( frame2,  panel, map , pc , 207, 102);
					}
				else if(name=="East") {
					teleportToDoor( frame2,  panel, map , pc , 107, 195);
					}
							
			}
		});
		
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pc.setIntellect(pc.getIntellect() + 5);
				connect.sendAndReceive("update Statistics set Intellect = " + pc.getIntellect() + " where Username = \'" + pc.getUsername() + "\'", null);
				pc.setHealth(pc.getHealth() - 5);
				connect.sendAndReceive("update Statistics set Health = " + pc.getHealth() + " where Username = \'" + pc.getUsername() + "\'", null);
				quest.questUpdater(67);

				if(name=="North") {
					teleportToDoor( frame2,  panel, map , pc , 6, 102);
					}
					else if(name=="West") {
					teleportToDoor( frame2,  panel, map , pc , 107, 7);
					}
					else if(name=="South") {
						teleportToDoor( frame2,  panel, map , pc , 207, 102);
						}
					else if(name=="East") {
						teleportToDoor( frame2,  panel, map , pc , 107, 195);
						}
			}
		});
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(name=="North") {
					teleportToDoor( frame2,  panel, map , pc , 6, 102);
					}
					else if(name=="West") {
					teleportToDoor( frame2,  panel, map , pc , 107, 7);
					}
					else if(name=="South") {
						teleportToDoor( frame2,  panel, map , pc , 207, 102);
						}
					else if(name=="East") {
						teleportToDoor( frame2,  panel, map , pc , 107, 195);
						}
				
			}
				
			
		});
		frame2.add(panel);
		frame2.revalidate();
		panel.setLayout(new GridLayout(6,1));
		busName.setFont(new Font("Comic Sans", Font.BOLD, 20));
		busName.setForeground(Color.RED);
		busName.setHorizontalAlignment(SwingConstants.CENTER);
		panel.setAlignmentX(SwingConstants.CENTER);
		panel.add(busName);
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		panel.add(button4);
		panel.add(back);
		
		frame2.revalidate();
		frame2.setVisible(true);
	}
	
	
	
	/**
	 * Memorial union.
	 *
	 * @param hour the hour
	 * @param map the map
	 */
	///////////////////////////              STORES
	public  void MemorialUnion(int hour,map map) {
		cash = pc.getCash();		//PC.getCash();
		health = pc.getHealth();	//PC.getHealth();
		stress = pc.getStress();	//PC.getStress();
		intellect = pc.getIntellect();	//PC.getIntellect();
	
		major = pc.getMajor();		//PC.getMajor();
		club = pc.getClub();		//PC.getClub();
		
		
		ArrayList<String> tempCol = new ArrayList<String>();
		tempCol.addAll(Arrays.asList("InventoryID", "Name", "Price", "HealthBonus", "StressBonus", "IntellectBonus"));
		ArrayList<String> items = connect.sendAndReceive("select * from Store where InventoryID > 3 AND InventoryID < 11", tempCol);			
		
		ArrayList<String> itemCol = new ArrayList<String>();
		itemCol.add("InventoryID");
		ArrayList<String> curItems = connect.sendAndReceive("select InventoryID from Inventory where username  = \'" + pc.getUsername() + "\'", itemCol);			

		
		JLabel storeLabel = new JLabel("Iowa State Store (One per person)");
		JButton button1 = new JButton("Buy");
		JButton button2 = new JButton("Buy");
		JButton button3 = new JButton("Buy");
		JButton quit = new JButton("Quit");
		

		if(timeClass.getHours() > 1700 || timeClass.getHours() < 800 || pc.getCash()< 75 || curItems.contains("4"))
			button1.setEnabled(false);
		if(timeClass.getHours() > 1700 || timeClass.getHours() < 800 || pc.getCash() < 20 || curItems.contains("5"))
			button2.setEnabled(false);
		if(timeClass.getHours() > 1700 || timeClass.getHours() < 800 || pc.getCash() < 25 || curItems.contains("6"))
			button3.setEnabled(false);
		

		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect.sendAndReceive("update Statistics set stress = " + (stress - 20) + " where username = \'" + pc.getUsername() + "\'", null);
				connect.sendAndReceive("update Statistics set cash = " + (cash - 75) + " where username = \'" + pc.getUsername() + "\")", null);
				connect.sendAndReceive("INSERT Inventory (Username, InventoryID) VALUES (\'" + pc.getUsername() + "\', 4)", null);
				pc.setCash(-75);
				pc.setStress(pc.getStress() - 20);
				
				frame3.dispose();
				MainFrame.refreshStatistics();
				panel.removeAll();
				frame3.remove(panel);
			}
		});
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect.sendAndReceive("update Statistics set health = " + (health + 10) + " where username = \'" + pc.getUsername() + "\'", null);
				connect.sendAndReceive("update Statistics set cash = " + (cash - 20) + " where username = \'" + pc.getUsername() + "\")", null);
				connect.sendAndReceive("INSERT Inventory (Username, InventoryID) VALUES (\'" + pc.getUsername() + "\', 5)", null);
				pc.setCash(-20);
				pc.setHealth(pc.getHealth() + 10);
				
				frame3.dispose();
				MainFrame.refreshStatistics();
				panel.removeAll();
				frame3.remove(panel);
			}
		});
		
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect.sendAndReceive("update Statistics set intellect = " + (intellect + 10) + " where username = \'" + pc.getUsername() + "\'", null);
				connect.sendAndReceive("update Statistics set cash = " + (cash - 25) + " where username = \'" + pc.getUsername() + "\")", null);
				connect.sendAndReceive("INSERT Inventory (Username, InventoryID) VALUES (\'" + pc.getUsername() + "\', 6)", null);
				pc.setCash(-25);
				pc.setIntellect(pc.getIntellect() + 10);
				
				frame3.dispose();
				MainFrame.refreshStatistics();
				panel.removeAll();
				frame3.remove(panel);
			}
		});
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame3.dispose();
			}
		});
		
		
		
		storeDisplay(items, storeLabel, hour, button1, button2, button3, quit);
		
	}
	
	// Potential Secret Menu off hours, only available at night when guards are out
	/**
	 * Caribou cafe.
	 *
	 * @param hour the hour
	 * @param map the map
	 */
	// Potential Items: Undetectable ($50), Stat Boost ($100), Mission Pass($200)
	public  void CaribouCafe(int hour,map map) {
		cash = pc.getCash();		//PC.getCash();
		health = pc.getHealth();	//PC.getHealth();
		stress = pc.getStress();	//PC.getStress();
		intellect = pc.getIntellect();	//PC.getIntellect();
	
		major = pc.getMajor();		//PC.getMajor();
		club = pc.getClub();		//PC.getClub();
		
		
		ArrayList<String> tempCol = new ArrayList<String>();
		tempCol.addAll(Arrays.asList("InventoryID", "Name", "Price", "HealthBonus", "StressBonus", "IntellectBonus"));
		ArrayList<String> items = connect.sendAndReceive("select * from Store where InventoryID > 10", tempCol);			
		
		ArrayList<String> itemCol = new ArrayList<String>();
		itemCol.add("InventoryID");
		ArrayList<String> curItems = connect.sendAndReceive("select InventoryID from Inventory where username  = \'" + pc.getUsername() + "\'", itemCol);			

		
		JLabel storeLabel = new JLabel("Caribou Cafe (One per person)");
		JButton button1 = new JButton("Buy");
		JButton button2 = new JButton("Buy");
		JButton button3 = new JButton("Buy");
		JButton quit = new JButton("Quit");
		
		
		if(timeClass.getHours() > 1700 || timeClass.getHours() < 800 || cash < 15 || curItems.contains("11"))
			button1.setEnabled(false);
		if(timeClass.getHours() > 1700 || timeClass.getHours() < 800 || cash < 15 || curItems.contains("12"))
			button2.setEnabled(false);
		if(timeClass.getHours() > 1700 || timeClass.getHours() < 800 || cash < 15 || curItems.contains("13"))
			button3.setEnabled(false);
		
		
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pc.setCash(-15);
				pc.setIntellect(pc.getIntellect() + 5);
				connect.sendAndReceive("INSERT into Inventory (Username, InventoryID) VALUES (\'" + pc.getUsername() + "\', 11)", null);
				connect.sendAndReceive("update Statistics set intellect = " + (pc.getIntellect() + 5) + " where username = \'" + pc.getUsername() + "\'", null);
				connect.sendAndReceive("update Statistics set cash = " + (pc.getCash()) + " where username = \'" + pc.getUsername() + "\")", null);

				
				frame3.dispose();
				MainFrame.refreshStatistics();
				MainFrame.refreshInventoryPanel();
				panel.removeAll();
				frame3.remove(panel);
			}
		});
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pc.setCash(-15);
				pc.setStress(pc.getStress() - 5);
				connect.sendAndReceive("update Statistics set stress = " + (stress - 5) + " where username = \'" + pc.getUsername() + "\'", null);
				connect.sendAndReceive("update Statistics set cash = " + (cash - 15) + " where username = \'" + pc.getUsername() + "\")", null);
				connect.sendAndReceive("INSERT Inventory (Username, InventoryID) VALUES (\'" + pc.getUsername() + "\', 12)", null);

				
				frame3.dispose();
				MainFrame.refreshStatistics();
				MainFrame.refreshInventoryPanel();
				panel.removeAll();
				frame3.remove(panel);
			}
		});
		
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pc.setCash(-15);
				pc.setHealth(pc.getHealth() + 5);
				connect.sendAndReceive("update Statistics set health = " + (health + 5) + " where username = \'" + pc.getUsername() + "\'", null);
				connect.sendAndReceive("update Statistics set cash = " + (cash - 15) + " where username = \'" + pc.getUsername() + "\")", null);
				connect.sendAndReceive("INSERT Inventory (Username, InventoryID) VALUES (\'" + pc.getUsername() + "\', 13)", null);

				
				frame3.dispose();
				MainFrame.refreshStatistics();
				MainFrame.refreshInventoryPanel();
				panel.removeAll();
				frame3.remove(panel);
			}
		});
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame3.dispose();
				panel.removeAll();
				frame3.remove(panel);
			}
		});
		
		
		
		this.storeDisplay(items, storeLabel, hour, button1, button2, button3, quit);
		
	}
	
	
	/**
	 * Store display.
	 *
	 * @param items the items
	 * @param storeLabel the store label
	 * @param hour the hour
	 * @param button1 the button 1
	 * @param button2 the button 2
	 * @param button3 the button 3
	 * @param quit the quit
	 */
	public  void storeDisplay(ArrayList<String> items, JLabel storeLabel, int hour, JButton button1, JButton button2, JButton button3, JButton quit) {
		frame3.add(panel);
		frame3.revalidate();
		storeLabel.setFont(new Font("Comic Sans", Font.BOLD, 20));
		storeLabel.setForeground(Color.RED);
		
		JLabel nameLabel = new JLabel("Name");
		JLabel priceLabel = new JLabel("Price");
		JLabel healthLabel = new JLabel("Health Bonus");
		JLabel stressLabel = new JLabel("Stress Bonus");
		JLabel intellectLabel = new JLabel("Intellect Bonus");
		JLabel buy = new JLabel("Can Afford");
		
		JLabel stat = new JLabel("Current Stats");
		JLabel cashStat = new JLabel("Cash: $" + pc.getCash());
		JLabel healthStat = new JLabel("Health: " + pc.getHealth());
		JLabel stressStat = new JLabel("Stress: " + pc.getStress());
		JLabel intellectStat = new JLabel("Intellect: " + pc.getIntellect());
		
		JLabel firstName = new JLabel(items.get(1));
		JLabel firstPrice = new JLabel("$" + items.get(2));
		JLabel firstHealth = new JLabel(items.get(3));
		JLabel firstStress = new JLabel(items.get(4));
		JLabel firstIntellect = new JLabel(items.get(5));
		JLabel secondName = new JLabel(items.get(7));
		JLabel secondPrice = new JLabel("$" + items.get(8));
		JLabel secondHealth = new JLabel(items.get(9));
		JLabel secondStress = new JLabel(items.get(10));
		JLabel secondIntellect = new JLabel(items.get(11));
		JLabel thirdName = new JLabel(items.get(13));
		JLabel thirdPrice = new JLabel("$" + items.get(14));
		JLabel thirdHealth = new JLabel(items.get(15));
		JLabel thirdStress = new JLabel(items.get(16));
		JLabel thirdIntellect = new JLabel(items.get(17));

		
		
		GroupLayout layout = new GroupLayout(panel);
		panel.setLayout(layout);

		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
						.addComponent(storeLabel))
				.addGroup(layout.createSequentialGroup()
						.addComponent(nameLabel, 0, 5, 200)
						.addComponent(priceLabel, 0, 5, 100)
						.addComponent(healthLabel, 0, 5, 100)
						.addComponent(stressLabel, 0, 5, 100)
						.addComponent(intellectLabel, 0, 5, 100)
						.addComponent(buy, 0, 5, 100))
				.addGroup(layout.createSequentialGroup()
						.addComponent(firstName, 0, 5, 200)
						.addComponent(firstPrice, 0, 5, 100)
						.addComponent(firstHealth, 0, 5, 100)
						.addComponent(firstStress, 0, 5, 100)
						.addComponent(firstIntellect, 0, 5, 100)
						.addComponent(button1, 0, 5, 100))
				.addGroup(layout.createSequentialGroup()
						.addComponent(secondName, 0, 5, 200)
						.addComponent(secondPrice, 0, 5, 100)
						.addComponent(secondHealth, 0, 5, 100)
						.addComponent(secondStress, 0, 5, 100)
						.addComponent(secondIntellect, 0, 5, 100)
						.addComponent(button2, 0, 5, 100))
				.addGroup(layout.createSequentialGroup()
						.addComponent(thirdName, 0, 5, 200)
						.addComponent(thirdPrice, 0, 5, 100)
						.addComponent(thirdHealth, 0, 5, 100)
						.addComponent(thirdStress, 0, 5, 100)
						.addComponent(thirdIntellect, 0, 5, 100)
						.addComponent(button3, 0, 5, 100))
				.addGroup(layout.createSequentialGroup()
						.addComponent(stat, 0, 5, 100)
						.addComponent(cashStat, 0, 5, 100)
						.addComponent(healthStat, 0, 5, 100)
						.addComponent(stressStat, 0, 5, 100)
						.addComponent(intellectStat, 0, 5, 100))
				.addGroup(layout.createSequentialGroup()
						.addComponent(quit, 0, 5, 100)));

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(storeLabel, 0, 5, 50))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(nameLabel, 0, 5, 30)
						.addComponent(priceLabel, 0, 5, 30)
						.addComponent(healthLabel, 0, 5, 30)
						.addComponent(stressLabel, 0, 5, 30)
						.addComponent(intellectLabel, 0, 5, 30)
						.addComponent(buy, 0, 5, 30))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(firstName, 0, 5, 20)
						.addComponent(firstPrice, 0, 5, 20)
						.addComponent(firstHealth, 0, 5, 20)
						.addComponent(firstStress, 0, 5, 20)
						.addComponent(firstIntellect, 0, 5, 20)
						.addComponent(button1, 0, 5, 20))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(secondName, 0, 5, 20)
						.addComponent(secondPrice, 0, 5, 20)
						.addComponent(secondHealth, 0, 5, 20)
						.addComponent(secondStress, 0, 5, 20)
						.addComponent(secondIntellect, 0, 5, 20)
						.addComponent(button2, 0, 5, 20))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(thirdName, 0, 5, 20)
						.addComponent(thirdPrice, 0, 5, 20)
						.addComponent(thirdHealth, 0, 5, 20)
						.addComponent(thirdStress, 0, 5, 20)
						.addComponent(thirdIntellect, 0, 5, 20)
						.addComponent(button3, 0, 5, 20))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(stat, 0, 5, 50)
						.addComponent(cashStat, 0, 5, 50)
						.addComponent(healthStat, 0, 5, 50)
						.addComponent(stressStat, 0, 5, 50)
						.addComponent(intellectStat, 0, 5, 50))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(quit, 0, 5, 25)));
		frame3.revalidate();
		
		


		//frame3.add(panel);
		//frame3.setUndecorated(true);
		//frame3.setAlwaysOnTop(true);
		//frame3.setAutoRequestFocus(true);
		//frame3.setPreferredSize(new Dimension(600, 300));
		//frame3.pack();
		//frame3.setLocationRelativeTo(null);

		frame3.setVisible(true);
	}
	
	
	
	
	/**
	 * Teleport to door.
	 *
	 * @param frame the frame
	 * @param panel the panel
	 * @param map the map
	 * @param pc the pc
	 * @param x the x
	 * @param y the y
	 */
	public void teleportToDoor(JFrame frame, JPanel panel, map map ,PC pc , int x, int y) {
		map.setRowCol(pc.getRow(), pc.getCol(), pc.getUnderPC());
		if (map.isWalkable(x, y)) {
			map.setdirectionName("Down");
			char mapTile = map.getRowCol(x, y);
			lastTile = mapTile;
			if (mapTile == '/') {
				map.setOnDoor(true);
			}
			if (mapTile == mapNode.GRASS) {
				map.setOnGrass(true);
			}
			if(mapTile==mapNode.BUSSTOP) {
				map.setOnBusStop(true);
			}
			if(mapTile==mapNode.EDGE_DOOR) {
				map.setOnEdgeDoor(true);
			}
			if (map.getnumChange() == 0) {
				map.setnumChange(1);

			} else {
				map.setnumChange(0);
			}
		//pc.placePC(35, 75);
		pc.setUnderPC(map.getRowCol(x,y));

		frame.dispose();
		panel.removeAll();
		frame.remove(panel);
	}
		if (map.placePC(x, y, pc)) {
		} else
			map.setRowCol(x, y, '@');
		//map.setdirectionName("Down");
	
	
		try {
			back.updateImage(map,pc.getRow(),pc.getCol());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	/**
	 * Holdup.
	 *
	 * @param x the x
	 */
	static void holdup(int x) {
		try {
			Thread.sleep(x);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
