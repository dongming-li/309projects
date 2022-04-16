package main;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Inventory {
	
	private static ArrayList<String> items;
	private static JFrame frame = new JFrame("Inventory");
	private static JPanel mainPanel = new JPanel(new GridLayout(2, 4));
	private static JPanel applePanel = new ImagePanel(new ImageIcon("Images/Apple.jpg").getImage());
	private static JPanel donutPanel = new ImagePanel(new ImageIcon("Images/Donut.jpg").getImage());
	private static JPanel waterPanel = new ImagePanel(new ImageIcon("Images/Water.jpg").getImage());
	private static JPanel nerdPanel = new ImagePanel(new ImageIcon("Images/NerdG.jpg").getImage());
	private static JPanel sunPanel = new ImagePanel(new ImageIcon("Images/Sunglasses.jpg").getImage());
	private static JPanel shirtPanel = new ImagePanel(new ImageIcon("Images/Shirt.jpg").getImage());
	private static JPanel phonePanel = new ImagePanel(new ImageIcon("Images/Phone.jpg").getImage());
	private static JPanel bookPanel = new ImagePanel(new ImageIcon("Images/Book.jpg").getImage());
	private static JPanel dumbellPanel = new ImagePanel(new ImageIcon("Images/Dumbell.jpg").getImage());
	
	private static JPanel emptyPanel1 = new ImagePanel(new ImageIcon("Images/EmptyInv.png").getImage());
	private static JPanel emptyPanel2 = new ImagePanel(new ImageIcon("Images/EmptyInv.png").getImage());
	private static JPanel emptyPanel3 = new ImagePanel(new ImageIcon("Images/EmptyInv.png").getImage());
	private static JPanel emptyPanel4 = new ImagePanel(new ImageIcon("Images/EmptyInv.png").getImage());
	private static JPanel emptyPanel5 = new ImagePanel(new ImageIcon("Images/EmptyInv.png").getImage());
	private static JPanel emptyPanel6 = new ImagePanel(new ImageIcon("Images/EmptyInv.png").getImage());
	private static JPanel emptyPanel7 = new ImagePanel(new ImageIcon("Images/EmptyInv.png").getImage());
	private static JPanel emptyPanel8 = new ImagePanel(new ImageIcon("Images/EmptyInv.png").getImage());
	
	private static PHPConnect connect = new PHPConnect();
	
	public static void main(String[] args) {
		//updateInventory();
		showInventory();

	}
	
	public Inventory(){
		ArrayList<String> itemCol = new ArrayList<String>();
		itemCol.add("InventoryID");
		
		items = connect.sendAndReceive("select InventoryID from Inventory where username = \'b3altena\'", itemCol);	
	}
	
	public  void updateInventory() {
		ArrayList<String> itemCol = new ArrayList<String>();
		itemCol.add("InventoryID");
		
		items = connect.sendAndReceive("select InventoryID from Inventory where username = \'b3altena\'", itemCol);	

		for(int i = 0; i < items.size(); i++) {
			if(Integer.parseInt(items.get(i)) == 1) {
				mainPanel.add(nerdPanel);
			}
			if(Integer.parseInt(items.get(i)) == 2) {
				mainPanel.add(sunPanel);
			}
			if(Integer.parseInt(items.get(i)) == 3) {
				mainPanel.add(shirtPanel);
			}
			if(Integer.parseInt(items.get(i)) == 4) {
				mainPanel.add(phonePanel);
			}
			if(Integer.parseInt(items.get(i)) == 5) {
				mainPanel.add(dumbellPanel);
			}
			if(Integer.parseInt(items.get(i)) == 6) {
				mainPanel.add(bookPanel);
			}
			if(Integer.parseInt(items.get(i)) == 11) {
				mainPanel.add(applePanel);
			}
			if(Integer.parseInt(items.get(i)) == 12) {
				mainPanel.add(donutPanel);
			}
			if(Integer.parseInt(items.get(i)) == 13) {
				mainPanel.add(waterPanel);
			}
		}
		
		if(8 - items.size() == 8) {
			mainPanel.add(emptyPanel1);
			mainPanel.add(emptyPanel2);
			mainPanel.add(emptyPanel3);
			mainPanel.add(emptyPanel4);
			mainPanel.add(emptyPanel5);
			mainPanel.add(emptyPanel6);
			mainPanel.add(emptyPanel7);
			mainPanel.add(emptyPanel8);
		} else {
			if(8 - items.size() == 7) {
				mainPanel.add(emptyPanel1);
				mainPanel.add(emptyPanel2);
				mainPanel.add(emptyPanel3);
				mainPanel.add(emptyPanel4);
				mainPanel.add(emptyPanel5);
				mainPanel.add(emptyPanel6);
				mainPanel.add(emptyPanel7);
			} else {
				if(8 - items.size() == 6) {
					mainPanel.add(emptyPanel1);
					mainPanel.add(emptyPanel2);
					mainPanel.add(emptyPanel3);
					mainPanel.add(emptyPanel4);
					mainPanel.add(emptyPanel5);
					mainPanel.add(emptyPanel6);
				} else {
					if(8 - items.size() == 5) {
						mainPanel.add(emptyPanel1);
						mainPanel.add(emptyPanel2);
						mainPanel.add(emptyPanel3);
						mainPanel.add(emptyPanel4);
						mainPanel.add(emptyPanel5);
					} else {
						if(8 - items.size() == 4) {
							mainPanel.add(emptyPanel1);
							mainPanel.add(emptyPanel2);
							mainPanel.add(emptyPanel3);
							mainPanel.add(emptyPanel4);
						} else {
							if(8 - items.size() == 3) {
								mainPanel.add(emptyPanel1);
								mainPanel.add(emptyPanel2);
								mainPanel.add(emptyPanel3);
							} else {
								if(8 - items.size() == 2) {
									mainPanel.add(emptyPanel1);
									mainPanel.add(emptyPanel2);
								} else {
									if(8 - items.size() == 1) {
										mainPanel.add(emptyPanel1);
									} 
								}
							}
						}
					}
				}
			}
		}

	}
	
	
	public JPanel returnInventory() {
		return mainPanel;
	}
	
	public static void showInventory() {
		frame.add(mainPanel);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(250, 200));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
