package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class interactableTile {
	
	private static JFrame frame1 = new JFrame("Door Menu");
	private static JFrame frame2 = new JFrame("Bus Menu");
	private static JFrame frame3 = new JFrame("Store Menu");
	private static JPanel panel = new JPanel();
	private static PHPConnect connect = new PHPConnect();
	private static int major;
	private static int club;
	private  int cash;
	private static int health;
	private static int stress;
	private static int intellect;

	//private static PC pc;
	private static Quests quest;
	

	//private static ArrayList<String> stats;
	private PC pc;
	//private map map;

	public static void main(String[] args) {		
		//cash = 50;		//PC.getCash();
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
		
		//CarverHall(1500, 1, 2);				// Breakdance Club
		//AtanasoffHall(1700, 1);
		
		//PearsonHall(1300, 5, 2);			// Used by all majors
		CurtissHall(1200, 5, 6);
		
		
		//MemorialUnion(1000); 
		//CaribouCafe(1000);		
		
		//BusStop(1900, 5);
		
		
		//frame1.setUndecorated(true);
		frame1.setPreferredSize(new Dimension(300, 400));
		frame1.pack();
		frame1.setLocationRelativeTo(null);
		frame1.setAlwaysOnTop(true);
		
		frame3.setUndecorated(true);
		frame3.setPreferredSize(new Dimension(600, 300));
		frame3.pack();
		frame3.setLocationRelativeTo(null);
		frame3.setAlwaysOnTop(true);
	}
	
	

	

	public interactableTile(PC pc) {	

		//interactableTile.pc = pc;
		quest  = new Quests(pc);

		this.pc =pc;
		this.cash = 100;		//PC.getCash();
		health = 100;	//PC.getHealth();
		stress = 15;	//PC.getStress();
		intellect = 25;	//PC.getIntellect();

	
		major = 3;		//PC.getMajor();
		club = 3;		//PC.getClub();

		
		cash = pc.getCash();		//PC.getCash();
		health = pc.getHealth();	//PC.getHealth();
		stress = pc.getStress();	//PC.getStress();
		intellect = pc.getIntellect();	//PC.getIntellect();
	
		major = PC.getMajor();		//PC.getMajor();
		club = PC.getClub();		//PC.getClub();
		
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
	}
	
	
	
	
	
	
	///////////////////////////              ENGINEERING
	public  void CooverHall(int hour, int day,map map) {
		JLabel buildingName = new JLabel("Coover Hall (Engineering)");
		JButton button1 = new JButton("Go to Programming class (MWF, 800 - 1000)");
		JButton button2 = new JButton("Talk to teacher for an hour (1200 - 1400)");
		JButton button3 = new JButton("Go out Door 1");
		JButton button4 = new JButton("Go out Door 2");
		JButton back = new JButton("Back");
		
		
		if(hour > 1000 || hour < 800 || major != 3 || !(day == 1 || day == 3 || day == 5))
			button1.setEnabled(false);
		if(hour > 1400 || hour < 1200 || major != 3)
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
				map.setRowCol(pc.getRow(), pc.getCol(), pc.getUnderPC());
				if (map.isWalkable(35, 75)) {
					
				//pc.placePC(35, 75);
				pc.setUnderPC(map.getRowCol(35,75));

				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
				if (map.placePC(35, 75, pc)) {
				} else
					map.setRowCol(35, 75, '@');

			}
		});
		
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				map.setRowCol(pc.getRow(), pc.getCol(), pc.getUnderPC());
				if (map.isWalkable(35, 26)) {
				//pc.placePC(35, 26);
				pc.setUnderPC(map.getRowCol(35, 26));
				//pc.setUnderPC('@');
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
				
			}
				if (map.placePC(35, 26, pc)) {
				} else
					map.setRowCol(35, 26, '@');

			
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
	
	public  void BlackEngineering(int hour, int day) {
		JLabel buildingName = new JLabel("Black Engineering");
		JButton button1 = new JButton("Go to Physics class (MWF, 1100 - 1300)");
		JButton button2 = new JButton("Talk to teacher for an hour (1200 - 1400)");
		JButton button3 = new JButton("Go out Door 1");
		JButton button4 = new JButton("Go out Door 2");
		JButton back = new JButton("Back");
		
		
		if(hour > 1300 || hour < 1100 || major != 3 || !(day == 1 || day == 3 || day == 5))
			button1.setEnabled(false);
		if(hour > 1400 || hour < 1200 || major != 3)
			button2.setEnabled(false);
		if(major != 3)
			button3.setEnabled(false);
		if(major != 3)
			button4.setEnabled(false);
		
		
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
	
	
	public  void MarstonHall(int hour, int day) {
		JLabel buildingName = new JLabel("Marston Hall (Engineering)");
		JButton button1 = new JButton("Go to Calculus class (TR, 800 - 1100)");
		JButton button2 = new JButton("Talk to teacher for an hour (1200 - 1400)");
		JButton button3 = new JButton("Go out Door 1");
		JButton button4 = new JButton("Go out Door 2");
		JButton back = new JButton("Back");
		
		
		if(hour > 1100 || hour < 800 || major != 3 || !(day == 2 || day == 4))
			button1.setEnabled(false);
		if(hour > 1400 || hour < 1200 || major != 3)
			button2.setEnabled(false);
		if(major != 3)
			button3.setEnabled(false);
		if(major != 3)
			button4.setEnabled(false);
		
		
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
	
	
	public  void HooverHall(int hour, int day) {
		JLabel buildingName = new JLabel("Hoover Hall (Engineering)");
		JButton button1 = new JButton("Go to Therodynamics class (TR, 1100 - 1400)");
		JButton button2 = new JButton("Talk to teacher for an hour (1200 - 1400)");
		JButton button3 = new JButton("Go out Door 1");
		JButton button4 = new JButton("Go out Door 2");
		JButton back = new JButton("Back");
		
		
		if(hour > 1400 || hour < 1100 || major != 3 || !(day == 2 || day == 4))
			button1.setEnabled(false);
		if(hour > 1400 || hour < 1200 || major != 3)
			button2.setEnabled(false);
		if(major != 3)
			button3.setEnabled(false);
		if(major != 3)
			button4.setEnabled(false);
		
		
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
	
	
	
	///////////////////////////              FITNESS
	public static void BeyerHall(int hour, int day) {
		JLabel buildingName = new JLabel("Beyer Hall (Kinesiology)");
		JButton button1 = new JButton("Go to Fitness class (MWF, 800 - 1000)");
		JButton button2 = new JButton("Talk to teacher for an hour (1200 - 1400)");
		JButton button3 = new JButton("Go out Door 1");
		JButton button4 = new JButton("Go out Door 2");
		JButton back = new JButton("Back");
		
		
		if(hour > 1000 || hour < 800 || major != 1 || !(day == 1 || day == 3 || day == 5))
			button1.setEnabled(false);
		if(hour > 1400 || hour < 1200 || major != 1)
			button2.setEnabled(false);
		if(major != 1)
			button3.setEnabled(false);
		if(major != 1)
			button4.setEnabled(false);
		
		
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
	
	
	public  void Armory(int hour, int day) {
		JLabel buildingName = new JLabel("Armory (Kinesiology)");
		JButton button1 = new JButton("Go to Biomed class (MWF, 1100 - 1300)");
		JButton button2 = new JButton("Talk to teacher for an hour (1200 - 1400)");
		JButton button3 = new JButton("Go out Door 1");
		JButton button4 = new JButton("Go out Door 2");
		JButton back = new JButton("Back");
		
		
		if(hour > 1300 || hour < 1100 || major != 1 || !(day == 1 || day == 3 || day == 5))
			button1.setEnabled(false);
		if(hour > 1400 || hour < 1200 || major != 1)
			button2.setEnabled(false);
		if(major != 1)
			button3.setEnabled(false);
		if(major != 1)
			button4.setEnabled(false);
		
		
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
	
	
	public  void DurhamHall(int hour, int day) {
		JLabel buildingName = new JLabel("Durham Hall (Kinesiology)");
		JButton button1 = new JButton("Go to Aerodynamics class (TR, 800 - 1100)");
		JButton button2 = new JButton("Talk to teacher for an hour (1200 - 1400)");
		JButton button3 = new JButton("Go out Door 1");
		JButton button4 = new JButton("Go out Door 2");
		JButton back = new JButton("Back");
		
		
		if(hour > 1100 || hour < 800 || major != 1 || !(day == 2 || day == 4))
			button1.setEnabled(false);
		if(hour > 1400 || hour < 1200 || major != 1)
			button2.setEnabled(false);
		if(major != 1)
			button3.setEnabled(false);
		if(major != 1)
			button4.setEnabled(false);
		
		
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
	
	
	public static void GilmanHall(int hour, int day) {
		JLabel buildingName = new JLabel("Gilman Hall (Kinesiology)");
		JButton button1 = new JButton("Go to Strategy class (TR, 1100 - 1400)");
		JButton button2 = new JButton("Talk to teacher for an hour (1200 - 1400)");
		JButton button3 = new JButton("Go out Door 1");
		JButton button4 = new JButton("Go out Door 2");
		JButton back = new JButton("Back");
		
		
		if(hour > 1400 || hour < 1100 || major != 1 || !(day == 2 || day == 4))
			button1.setEnabled(false);
		if(hour > 1400 || hour < 1200 || major != 1)
			button2.setEnabled(false);
		if(major != 1)
			button3.setEnabled(false);
		if(major != 1)
			button4.setEnabled(false);
		
		
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
	
	
	
	///////////////////////////              CULINARY ARTS
	public static void HoweHall(int hour, int day) {
		JLabel buildingName = new JLabel("Howe Hall (Culinary Arts)");
		JButton button1 = new JButton("Go to Nutrition class (MWF, 800 - 1000)");
		JButton button2 = new JButton("Talk to teacher for an hour (1200 - 1400)");
		JButton button3 = new JButton("Go out Door 1");
		JButton button4 = new JButton("Go out Door 2");
		JButton back = new JButton("Back");
		
		
		if(hour > 1000 || hour < 800 || major != 2 || !(day == 1 || day == 3 || day == 5))
			button1.setEnabled(false);
		if(hour > 1400 || hour < 1200 || major != 2)
			button2.setEnabled(false);
		if(major != 2)
			button3.setEnabled(false);
		if(major != 2)
			button4.setEnabled(false);
		
		
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quest.questUpdater(31);
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
	
	
	public static void DesignHall(int hour, int day) {
		JLabel buildingName = new JLabel("Design Hall (Culinary Arts)");
		JButton button1 = new JButton("Go to Baking class (MWF, 1100 - 1300)");
		JButton button2 = new JButton("Talk to teacher for an hour (1200 - 1400)");
		JButton button3 = new JButton("Go out Door 1");
		JButton button4 = new JButton("Go out Door 2");
		JButton back = new JButton("Back");
		
		
		if(hour > 1300 || hour < 1100 || major != 2 || !(day == 1 || day == 3 || day == 5))
			button1.setEnabled(false);
		if(hour > 1400 || hour < 1200 || major != 2)
			button2.setEnabled(false);
		if(major != 2)
			button3.setEnabled(false);
		if(major != 2)
			button4.setEnabled(false);
		
		
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
	
	
	public static void McKayHall(int hour, int day) {
		JLabel buildingName = new JLabel("McKay Hall (Culinary Arts)");
		JButton button1 = new JButton("Go to Pre-Calc class (TR, 800 - 1100)");
		JButton button2 = new JButton("Talk to teacher for an hour (1200 - 1400)");
		JButton button3 = new JButton("Go out Door 1");
		JButton button4 = new JButton("Go out Door 2");
		JButton back = new JButton("Back");
		
		
		if(hour > 1100 || hour < 800 || major != 2 || !(day == 2 || day == 4))
			button1.setEnabled(false);
		if(hour > 1400 || hour < 1200 || major != 2)
			button2.setEnabled(false);
		if(major != 2)
			button3.setEnabled(false);
		if(major != 2)
			button4.setEnabled(false);
		
		
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
	
	
	public  void BeardshearHall(int hour, int day) {
		JLabel buildingName = new JLabel("Beardshear Hall (Culinary Arts)");
		JButton button1 = new JButton("Go to Chemistry class (TR, 1100 - 1400)");
		JButton button2 = new JButton("Talk to teacher for an hour (1200 - 1400)");
		JButton button3 = new JButton("Go out Door 1");
		JButton button4 = new JButton("Go out Door 2");
		JButton back = new JButton("Back");
		
		
		if(hour > 1400 || hour < 1100 || major != 2 || !(day == 2 || day == 4))
			button1.setEnabled(false);
		if(hour > 1400 || hour < 1200 || major != 2)
			button2.setEnabled(false);
		if(major != 2)
			button3.setEnabled(false);
		if(major != 2)
			button4.setEnabled(false);
		
		
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
	
	
	
	
	///////////////////////////              BASKETBALL CLUB
	public static void StateGym(int hour, int day, int week) {
		JLabel buildingName = new JLabel("State Gym");
		JButton button1 = new JButton("Go to Basketball Club (R, 1400 - 1600)");
		JButton button2 = new JButton("Basketball Game (Week 2, F, 1700 - 1900)");
		JButton button3 = new JButton("Go out Door 1");
		JButton button4 = new JButton("Go out Door 2");
		JButton back = new JButton("Back");
		
		
		if(hour > 1600 || hour < 1400 || club != 1 || day != 4)
			button1.setEnabled(false);
		if(hour > 1900 || hour < 1700 || club != 1 || day != 5 || week != 2)
			button2.setEnabled(false);
		
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
	
	
	
	public  void SnedecorHall(int hour, int day) {
		JLabel buildingName = new JLabel("Snedecor Hall");
		JButton button1 = new JButton("Practice basketball (T, 1400 - 1600)");
		JButton button2 = new JButton("Workout for 4 hours (800 - 1700)");
		JButton button3 = new JButton("Go out Door 1");
		JButton button4 = new JButton("Go out Door 2");
		JButton back = new JButton("Back");
		
		
		if(hour > 1600 || hour < 1400 || club != 1 || day != 2)
			button1.setEnabled(false);
		if(hour > 1700 || hour < 800)
			button2.setEnabled(false);
		
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pc.setHealth(pc.getHealth() + 5);
				connect.sendAndReceive("update Statistics set Health = " + pc.getHealth() + " where Username = \'" + PC.getUsername() + "\'", null);
				quest.questUpdater(41);
				
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pc.setHealth(pc.getHealth() + 15);
				connect.sendAndReceive("update Statistics set Health = " + pc.getHealth() + " where Username = \'" + PC.getUsername() + "\'", null);
				quest.questUpdater(42);
				
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

	
	


	///////////////////////////              HONORS CLUB
	/*
	public  void ParksLibrary(int hour, int day, int week) {
		JLabel buildingName = new JLabel("Parks Library");
		JButton button1 = new JButton("Go to Honors Club (R, 1400 - 1600)");
		JButton button2 = new JButton("Honors Competition (Week 2, F, 1700 - 1900)");
		JButton button3 = new JButton("Go out Door 1");
		JButton button4 = new JButton("Go out Door 2");
		JButton back = new JButton("Back");
		
		
		if(hour > 1600 || hour < 1400 || club != 3 || day != 4)
			button1.setEnabled(false);
		if(hour > 1900 || hour < 1700 || club != 3 || day != 5 || week != 2)
			button2.setEnabled(false);
		
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
	*/
	/*
	public  void SweenyHall(int hour, int day) {
		JLabel buildingName = new JLabel("Sweeny Hall");
		JButton button1 = new JButton("Study for Honors (T, 1400 - 1600)");
		JButton button2 = new JButton("Read a book for 4 hours (800 - 1700)");
		JButton button3 = new JButton("Go out Door 1");
		JButton button4 = new JButton("Go out Door 2");
		JButton back = new JButton("Back");
		
		
		if(hour > 1600 || hour < 1400 || club != 3 || day != 2)
			button1.setEnabled(false);
		if(hour > 1700 || hour < 800)
			button2.setEnabled(false);
		
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
	
	

	*/
	///////////////////////////              BREAKDANCE CLUB
	public static void CarverHall(int hour, int day, int week) {
		JLabel buildingName = new JLabel("Carver Hall");
		JButton button1 = new JButton("Go to Breakdance Club (R, 1400 - 1600)");
		JButton button2 = new JButton("Breakdance Competition (Week 2, F, 1700 - 1900)");
		JButton button3 = new JButton("Go out Door 1");
		JButton button4 = new JButton("Go out Door 2");
		JButton back = new JButton("Back");
		
		
		if(hour > 1600 || hour < 1400 || club != 2 || day != 4)
			button1.setEnabled(false);
		if(hour > 1900 || hour < 1700 || club != 2 || day != 5 || week != 2)
			button2.setEnabled(false);
		
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
	
	
	
	public  void AtanasoffHall(int hour, int day) {
		JLabel buildingName = new JLabel("Carver Hall");
		JButton button1 = new JButton("Study dancing (T, 1400 - 1600)");
		JButton button2 = new JButton("Dance for 4 hours (800 - 1700)");
		JButton button3 = new JButton("Go out Door 1");
		JButton button4 = new JButton("Go out Door 2");
		JButton back = new JButton("Back");
		
		
		if(hour > 1600 || hour < 1400 || club != 2 || day != 2)
			button1.setEnabled(false);
		if(hour > 1700 || hour < 800)
			button2.setEnabled(false);
		
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pc.setStress(pc.getStress() + 5);
				connect.sendAndReceive("update Statistics set Stress = " + pc.getStress() + " where Username = \'" + PC.getUsername() + "\'", null);
				quest.questUpdater(51);
				
				frame1.dispose();
			}
		});
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pc.setStress(pc.getStress() + 15);
				connect.sendAndReceive("update Statistics set Stress = " + pc.getStress() + " where Username = \'" + PC.getUsername() + "\'", null);
				quest.questUpdater(52);
				
				frame1.dispose();
			}
		});
		
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
			}
		});
		
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
			}
		});
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
			}
		});
		
		displayDoor(buildingName, button1, button2, button3, button4, back);
	}
	
	
	
	
	///////////////////////////              HONORS CLUB
	public static void ParksLibrary(int hour, int day, int week) {
		JLabel buildingName = new JLabel("Parks Library");
		JButton button1 = new JButton("Go to Honors Club (R, 1400 - 1600)");
		JButton button2 = new JButton("Honors Competition (Week 2, F, 1700 - 1900)");
		JButton button3 = new JButton("Go out Door 1");
		JButton button4 = new JButton("Go out Door 2");
		JButton back = new JButton("Back");
		
		
		if(hour > 1600 || hour < 1400 || club != 3 || day != 4)
			button1.setEnabled(false);
		if(hour > 1900 || hour < 1700 || club != 3 || day != 5 || week != 2)
			button2.setEnabled(false);
		
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
	
	
	public  void SweenyHall(int hour, int day) {
		JLabel buildingName = new JLabel("Sweeny Hall");
		JButton button1 = new JButton("Study for Honors (T, 1400 - 1600)");
		JButton button2 = new JButton("Read a book for 4 hours (800 - 1700)");
		JButton button3 = new JButton("Go out Door 1");
		JButton button4 = new JButton("Go out Door 2");
		JButton back = new JButton("Back");
		
		
		if(hour > 1600 || hour < 1400 || club != 3 || day != 2)
			button1.setEnabled(false);
		if(hour > 1700 || hour < 800)
			button2.setEnabled(false);
		
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pc.setIntellect(pc.getIntellect() + 5);
				connect.sendAndReceive("update Statistics set Intellect = " + pc.getIntellect() + " where Username = \'" + PC.getUsername() + "\'", null);
				quest.questUpdater(61);
				
				frame1.dispose();
			}
		});
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pc.setIntellect(pc.getIntellect() + 15);
				connect.sendAndReceive("update Statistics set Intellect = " + pc.getIntellect() + " where Username = \'" + PC.getUsername() + "\'", null);
				quest.questUpdater(62);
				
				frame1.dispose();
			}
		});
		
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
			}
		});
		
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
			}
		});
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
			}
		});
		
		displayDoor(buildingName, button1, button2, button3, button4, back);
	}
	
	
	
	
	
	///////////////////////////              TESTING CENTERS
	public void PearsonHall(int hour, int day, int week) {
		JLabel buildingName = new JLabel("Pearson Hall - Testing Center");
		JButton button1 = new JButton("Take a Quiz (Week 2, F, 1000 - 1400)");
		JButton button2 = new JButton("Take an Exam (Week 4, F, 1000 - 1400)");
		JButton button3 = new JButton("Go out Door 1");
		JButton button4 = new JButton("Go out Door 2");
		JButton back = new JButton("Back");
		
		
		if(hour > 1400 || hour < 1000 || day != 5 || week != 2)
			button1.setEnabled(false);
		if(hour > 1400 || hour < 1000 || day != 5 || week != 4)
			button2.setEnabled(false);
		
		
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
			}
		});
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
	
	
	
	public static void CurtissHall(int hour, int day, int week) {
		JLabel buildingName = new JLabel("Curtiss Hall - Testing Center");
		
		/*
		 * Depending on club choose the building option 
		 */
		String clubFinal = "";
		if(PC.getMajor() == 1)
			clubFinal = "Strongest Student Competition (Week 4, F, 1800 - 2000)";
		if(PC.getMajor() == 2)
			clubFinal = "Best Chef Competition (Week 4, F, 1800 - 2000)";
		if(PC.getMajor() == 3)
			clubFinal = "Smartest Student Competition (Week 4, F, 1800 - 2000)";

		
		
		JButton button1 = new JButton(clubFinal);
		JButton button3 = new JButton("Go out Door 1");
		JButton back = new JButton("Back");
		
		
		if(hour > 2000 || hour < 1800 || day != 5 || week != 4)
			button1.setEnabled(false);
		
		
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				quest.questUpdater(99);
				
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
		
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame1.dispose();
				panel.removeAll();
				frame1.remove(panel);
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
	

	///////////////////////////              BUS STOP
	public static void BusStop(int hour, int day) {
		JLabel busName = new JLabel("Bus Stop");
		JButton button1 = new JButton("Go Home (MTWRF, 2000 - 0000)");
		JButton button2 = new JButton("Party this Weekend (F, 2000 - 0000)");
		JButton button3 = new JButton("Workout this Weekend (F, 2000 - 0000)");
		JButton button4 = new JButton("Study this Weekend (F, 2000 - 0000)");
		JButton back = new JButton("Back");
		

		if(hour > 2400 || hour < 2000)
			button1.setEnabled(false);
		if((hour > 2400 || hour < 2000) || day != 5)
			button2.setEnabled(false);
		if((hour > 2400 || hour < 2000) || day != 5)
			button3.setEnabled(false);
		if((hour > 2400 || hour < 2000) || day != 5)
			button4.setEnabled(false);
		
		
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame2.dispose();
			}
		});
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame2.dispose();
			}
		});
		
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame2.dispose();
			}
		});
		
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame2.dispose();
			}
		});
		
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame2.dispose();
			}
		});
		
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
		
		frame2.add(panel);
		frame2.setUndecorated(true);
		frame2.setPreferredSize(new Dimension(300, 300));
		frame2.pack();
		frame2.setLocationRelativeTo(null);
		frame2.setVisible(true);
	}
	
	
	
	///////////////////////////              STORES
	public  void MemorialUnion(int hour) {
		ArrayList<String> tempCol = new ArrayList<String>();
		tempCol.addAll(Arrays.asList("InventoryID", "Name", "Price", "HealthBonus", "StressBonus", "IntellectBonus"));
		ArrayList<String> items = connect.sendAndReceive("select * from Store where InventoryID > 3 AND InventoryID < 11", tempCol);			
		
		ArrayList<String> itemCol = new ArrayList<String>();
		itemCol.add("InventoryID");
		ArrayList<String> curItems = connect.sendAndReceive("select InventoryID from Inventory where username  = \'" + PC.getUsername() + "\'", itemCol);			

		
		JLabel storeLabel = new JLabel("Iowa State Store (One per person)");
		JButton button1 = new JButton("Buy");
		JButton button2 = new JButton("Buy");
		JButton button3 = new JButton("Buy");
		JButton quit = new JButton("Quit");
		

		if(hour > 1700 || hour < 800 || pc.getCash()< 75 || curItems.contains("4"))
			button1.setEnabled(false);
		if(hour > 1700 || hour < 800 || pc.getCash() < 20 || curItems.contains("5"))
			button2.setEnabled(false);
		if(hour > 1700 || hour < 800 || pc.getCash() < 25 || curItems.contains("6"))
			button3.setEnabled(false);
		
		//button1.setEnabled(true);
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect.sendAndReceive("update Statistics set stress = " + (stress - 20) + " where username = \'" + PC.getUsername() + "\'", null);
				connect.sendAndReceive("update Statistics set cash = " + (cash - 75) + " where username = \'" + PC.getUsername() + "\")", null);
				connect.sendAndReceive("INSERT Inventory (Username, InventoryID) VALUES (\'" + PC.getUsername() + "\', 4)", null);
				pc.setCash(pc.getCash()-75);
				pc.setStress(pc.getStress() - 20);
				
				frame3.dispose();
				MainFrame.refreshStatistics();
				panel.removeAll();
				frame3.remove(panel);
			}
		});
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect.sendAndReceive("update Statistics set health = " + (health + 10) + " where username = \'" + PC.getUsername() + "\'", null);
				connect.sendAndReceive("update Statistics set cash = " + (cash - 20) + " where username = \'" + PC.getUsername() + "\")", null);
				connect.sendAndReceive("INSERT Inventory (Username, InventoryID) VALUES (\'" + PC.getUsername() + "\', 5)", null);
				pc.setCash(pc.getCash() - 20);
				pc.setHealth(pc.getHealth() + 10);
				
				frame3.dispose();
				MainFrame.refreshStatistics();
				panel.removeAll();
				frame3.remove(panel);
			}
		});
		
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect.sendAndReceive("update Statistics set intellect = " + (intellect + 10) + " where username = \'" + PC.getUsername() + "\'", null);
				connect.sendAndReceive("update Statistics set cash = " + (cash - 25) + " where username = \'" + PC.getUsername() + "\")", null);
				connect.sendAndReceive("INSERT Inventory (Username, InventoryID) VALUES (\'" + PC.getUsername() + "\', 6)", null);
				pc.setCash(pc.getCash() - 25);
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
	// Potential Items: Undetectable ($50), Stat Boost ($100), Mission Pass($200)
	public  void CaribouCafe(int hour) {
		ArrayList<String> tempCol = new ArrayList<String>();
		tempCol.addAll(Arrays.asList("InventoryID", "Name", "Price", "HealthBonus", "StressBonus", "IntellectBonus"));
		ArrayList<String> items = connect.sendAndReceive("select * from Store where InventoryID > 10", tempCol);			
		
		ArrayList<String> itemCol = new ArrayList<String>();
		itemCol.add("InventoryID");
		ArrayList<String> curItems = connect.sendAndReceive("select InventoryID from Inventory where username  = \'" + PC.getUsername() + "\'", itemCol);			

		
		JLabel storeLabel = new JLabel("Caribou Cafe (One per person)");
		JButton button1 = new JButton("Buy");
		JButton button2 = new JButton("Buy");
		JButton button3 = new JButton("Buy");
		JButton quit = new JButton("Quit");
		

		if(hour > 1700 || hour < 800 || cash < 15 || curItems.contains("11"))
			button1.setEnabled(false);
		if(hour > 1700 || hour < 800 || cash < 15 || curItems.contains("12"))
			button2.setEnabled(false);
		if(hour > 1700 || hour < 800 || cash < 15 || curItems.contains("13"))
			button3.setEnabled(false);
		
		
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect.sendAndReceive("update Statistics set intellect = " + (intellect + 5) + " where username = \'" + PC.getUsername() + "\'", null);
				connect.sendAndReceive("update Statistics set cash = " + (cash - 15) + " where username = \'" + PC.getUsername() + "\")", null);
				connect.sendAndReceive("INSERT Inventory (Username, InventoryID) VALUES (\'" + PC.getUsername() + "\', 11)", null);
				pc.setCash(pc.getCash() - 15);
				pc.setIntellect(pc.getIntellect() + 5);
				
				frame3.dispose();
				MainFrame.refreshStatistics();
				panel.removeAll();
				frame3.remove(panel);
			}
		});
		
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect.sendAndReceive("update Statistics set stress = " + (stress - 5) + " where username = \'" + PC.getUsername() + "\'", null);
				connect.sendAndReceive("update Statistics set cash = " + (cash - 15) + " where username = \'" + PC.getUsername() + "\")", null);
				connect.sendAndReceive("INSERT Inventory (Username, InventoryID) VALUES (\'" + PC.getUsername() + "\', 12)", null);
				pc.setCash(pc.getCash() - 15);
				pc.setStress(pc.getStress() - 5);
				
				frame3.dispose();
				MainFrame.refreshStatistics();
				panel.removeAll();
				frame3.remove(panel);
			}
		});
		
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect.sendAndReceive("update Statistics set health = " + (health + 5) + " where username = \'" + PC.getUsername() + "\'", null);
				connect.sendAndReceive("update Statistics set cash = " + (cash - 15) + " where username = \'" + PC.getUsername() + "\")", null);
				connect.sendAndReceive("INSERT Inventory (Username, InventoryID) VALUES (\'" + PC.getUsername() + "\', 13)", null);
				pc.setCash(pc.getCash() - 15);
				pc.setHealth(pc.getHealth() + 5);
				
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
		
		
		
		this.storeDisplay(items, storeLabel, hour, button1, button2, button3, quit);
		
	}
	
	
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
	
}
