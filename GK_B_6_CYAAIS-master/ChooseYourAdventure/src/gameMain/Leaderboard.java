package gameMain;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * The Class Leaderboard.
 */
public class Leaderboard {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame("Spectator Menu");
		JLabel title = new JLabel("Choose your Adventure at Iowa State");
		JButton quit = new JButton("Quit");
		JLabel usernameLabel = new JLabel("Username");
		JLabel majorLabel = new JLabel("Major");
		JLabel clubLabel = new JLabel("Club");
		JLabel healthLabel = new JLabel("Health");
		JLabel stressLabel = new JLabel("Stress");
		JLabel intellectLabel = new JLabel("Intellect");
		JLabel cashLabel = new JLabel("Cash");
		JLabel experienceLabel = new JLabel("Experience");
		PHPConnect connect = new PHPConnect();
		ArrayList<JPanel> tempList = new ArrayList<JPanel>();
		
		ArrayList<String> statCol = new ArrayList<String>();
		statCol.addAll(Arrays.asList("Username", "MName", "Name", "Health", "Stress", "Intellect", "Cash", "Experience"));
		ArrayList<String> stats = connect.sendAndReceive(
				"select Users.Username, Majors.MName, Clubs.Name, Health, Stress, Intellect, Cash, Experience from Users inner join Statistics on Users.Username = Statistics.Username  inner join Majors on Majors.MajorID = Users.MajorChoice inner join Clubs on Clubs.ClubID = Users.ClubChoice where Users.isDone = 1 order by Experience DESC"
				, statCol);

		
		for(int i = 0; i < (stats.size()); i+=8) {
			JLabel username = new JLabel(((i + 8)/8) + ". " + stats.get(i));
			JLabel major = new JLabel(stats.get(i + 1));
			JLabel club = new JLabel(stats.get(i + 2));
			JLabel health = new JLabel(stats.get(i + 3));
			JLabel stress = new JLabel(stats.get(i + 4));
			JLabel intellect = new JLabel(stats.get(i + 5));
			JLabel cash = new JLabel(stats.get(i + 6));
			JLabel experience = new JLabel(stats.get(i + 7));
			
			JPanel tempPanel = new JPanel(new GridLayout(1, 8));
			tempPanel.add(username);
			tempPanel.add(major);
			tempPanel.add(club);
			tempPanel.add(health);
			tempPanel.add(stress);
			tempPanel.add(intellect);
			tempPanel.add(cash);
			tempPanel.add(experience);

			tempList.add(tempPanel);
		}
		JPanel holder = new JPanel(new GridLayout(tempList.size() + 1, 1));
		
		JPanel tempPanel = new JPanel(new GridLayout(1, 8));
		tempPanel.add(usernameLabel);
		tempPanel.add(majorLabel);
		tempPanel.add(clubLabel);
		tempPanel.add(healthLabel);
		tempPanel.add(stressLabel);
		tempPanel.add(intellectLabel);
		tempPanel.add(cashLabel);
		tempPanel.add(experienceLabel);
		
		holder.add(tempPanel);
		for(int i = 0; i < tempList.size(); i++)
			holder.add(tempList.get(i));
		
		

		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();			
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
						.addComponent(holder, 0, 100, Short.MAX_VALUE))
				.addGroup(layout.createSequentialGroup()
						.addComponent(quit, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(title, 0, 5, 225))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(holder, 0, 100, 150))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(quit)));

		frame.add(mainPanel);

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(700, 500));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	/**
	 * Instantiates a new leaderboard.
	 */
	public Leaderboard() {		
		JFrame frame = new JFrame("Spectator Menu");
		JLabel title = new JLabel("Choose your Adventure at Iowa State");
		JButton quit = new JButton("Quit");
		JLabel usernameLabel = new JLabel("Username");
		JLabel majorLabel = new JLabel("Major");
		JLabel clubLabel = new JLabel("Club");
		JLabel healthLabel = new JLabel("Health");
		JLabel stressLabel = new JLabel("Stress");
		JLabel intellectLabel = new JLabel("Intellect");
		JLabel cashLabel = new JLabel("Cash");
		JLabel experienceLabel = new JLabel("Experience");
		PHPConnect connect = new PHPConnect();
		ArrayList<JPanel> tempList = new ArrayList<JPanel>();
		
		ArrayList<String> statCol = new ArrayList<String>();
		statCol.addAll(Arrays.asList("Users.Username", "Majors.Name", "Clubs.Name", "Health", "Stress", "Intellect", "Cash", "Experience"));
		ArrayList<String> stats = connect.sendAndReceive(
				"select Users.Username, Majors.Name, Clubs.Name, Health, Stress, Intellect, Cash, Experience from Users inner join Statistics on Users.Username = Statistics.Username  inner join Majors on Majors.MajorID = Users.MajorChoice inner join Clubs on Clubs.ClubID = Users.ClubChoice where Users.isDone = 1 order by Experience"
				, statCol);
		
		
		
		for(int i = 0; i < (stats.size()); i+=8) {
			JLabel username = new JLabel(((i + 8)/8) + ". " + stats.get(i));
			JLabel major = new JLabel(stats.get(i + 1));
			JLabel club = new JLabel(stats.get(i + 2));
			JLabel health = new JLabel(stats.get(i + 3));
			JLabel stress = new JLabel(stats.get(i + 4));
			JLabel intellect = new JLabel(stats.get(i + 5));
			JLabel cash = new JLabel(stats.get(i + 6));
			JLabel experience = new JLabel(stats.get(i + 7));
			
			JPanel tempPanel = new JPanel(new GridLayout(1, 8));
			tempPanel.add(username);
			tempPanel.add(major);
			tempPanel.add(club);
			tempPanel.add(health);
			tempPanel.add(stress);
			tempPanel.add(intellect);
			tempPanel.add(cash);
			tempPanel.add(experience);

			tempList.add(tempPanel);
		}
		JPanel holder = new JPanel(new GridLayout(tempList.size(), 1));
		for(int i = 0; i < tempList.size(); i++)
			holder.add(tempList.get(i));
		
		

		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();			
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
						.addComponent(usernameLabel)
						.addComponent(majorLabel)
						.addComponent(clubLabel)
						.addComponent(healthLabel)
						.addComponent(stressLabel)
						.addComponent(intellectLabel)
						.addComponent(cashLabel)
						.addComponent(experienceLabel))
				.addGroup(layout.createSequentialGroup()
						.addComponent(holder))
				.addGroup(layout.createSequentialGroup()
						.addComponent(quit, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(title, 0, 5, 200))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(usernameLabel)
						.addComponent(majorLabel)
						.addComponent(clubLabel)
						.addComponent(healthLabel)
						.addComponent(stressLabel)
						.addComponent(intellectLabel)
						.addComponent(cashLabel)
						.addComponent(experienceLabel))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(holder))
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
						.addComponent(quit)));

		frame.add(mainPanel);

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(700, 500));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
