package main;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Statistics {
	
	private static ArrayList<String> stats;
	private static JFrame frame = new JFrame("Statistics");
	private static JPanel mainPanel = new JPanel(new GridLayout(1, 2));
	private PC pc;
	private static PHPConnect connect = new PHPConnect();
	
	
	
	public static void main(String[] args) {
		//updateStatistics();
		showStatistics();

	}
	
	public Statistics(PC pc){
		this.pc =pc;
		
		ArrayList<String> statCol = new ArrayList<String>();
		statCol.addAll(Arrays.asList("Health", "Stress", "Intellect", "Cash", "Experience"));
		
		stats = connect.sendAndReceive("select Health, Stress, Intellect, Cash, Experience from Statistics where username = \'b3altena\'", statCol);	
	}
	
	public  void updateStatistics() {
		JPanel picPanel = new JPanel();
		JPanel statPanel = new JPanel(new GridLayout(5, 1));
		
		ArrayList<String> statCol = new ArrayList<String>();
		statCol.addAll(Arrays.asList("Health", "Stress", "Intellect", "Cash", "Experience"));
		
		stats = connect.sendAndReceive("select Health, Stress, Intellect, Cash, Experience from Statistics where username = \'b3altena\'", statCol);	
		/*
		JLabel healthLabel = new JLabel("Health: " + stats.get(0) + " / 100");
		JLabel stressLabel = new JLabel("Stress: " + stats.get(1) + " / 100");
		JLabel intellectLabel = new JLabel("Intellect: " + stats.get(2) + " / 100");
		JLabel cashLabel = new JLabel("Cash: " + stats.get(3));
		JLabel expLabel = new JLabel("Experience: " + stats.get(4));
		*/
		JLabel healthLabel = new JLabel("Health: " + pc.getHealth()+ " / 100");
		JLabel stressLabel = new JLabel("Stress: " + pc.getStress() + " / 100");
		JLabel intellectLabel = new JLabel("Intellect: " + pc.getIntellect() + " / 100");
		JLabel cashLabel = new JLabel("Cash: " + pc.getCash());
		JLabel expLabel = new JLabel("Experience: " + stats.get(4));
		
		JLabel AvatarPic1 = new JLabel(new ImageIcon("Images/Avatar1.jpg"));
		JLabel AvatarPic2 = new JLabel(new ImageIcon("Images/Avatar2.jpg"));
		JLabel AvatarPic3 = new JLabel(new ImageIcon("Images/Avatar3.jpg"));
		JLabel AvatarPic4 = new JLabel(new ImageIcon("Images/Avatar4.jpg"));
		JLabel AvatarPic5 = new JLabel(new ImageIcon("Images/Avatar5.jpg"));
		JLabel AvatarPic6 = new JLabel(new ImageIcon("Images/Avatar6.jpg"));
		
		ArrayList<String> genCol = new ArrayList<String>();
		genCol.add("GenderID");
		int genderChoice = Integer.parseInt(connect.sendAndReceive("select GenderID from Users where username = \'b3altena\'", genCol).get(0));
		
		if(genderChoice == 1)
			picPanel.add(AvatarPic1);
		else 
			if(genderChoice == 2)
				picPanel.add(AvatarPic2);
			else
				if(genderChoice == 3)
					picPanel.add(AvatarPic3);
				else
					if(genderChoice == 4)
						picPanel.add(AvatarPic4);
					else
						if(genderChoice == 5)
							picPanel.add(AvatarPic5);
						else
							if(genderChoice == 6)
								picPanel.add(AvatarPic6);
		
		statPanel.add(healthLabel);
		statPanel.add(stressLabel);
		statPanel.add(intellectLabel);
		statPanel.add(cashLabel);
		statPanel.add(expLabel);

		mainPanel.add(picPanel);
		mainPanel.add(statPanel);
	}
	
	
	public JPanel returnStatistics() {
		return mainPanel;
	}
	
	public static void showStatistics() {
		frame.add(mainPanel);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(250, 200));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
