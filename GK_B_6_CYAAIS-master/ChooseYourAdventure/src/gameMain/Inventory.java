package gameMain;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * The Class Inventory.
 */
public class Inventory {
	
	/** The items. */
	private static ArrayList<String> items;
	
	/** The frame. */
	private static JFrame frame = new JFrame("Inventory");
	
	/** The pc. */
	private PC pc;
	
	/** The main panel. */
	private static JPanel mainPanel = new JPanel(new GridLayout(2, 4));
	
	/** The apple panel. */
	private static JPanel applePanel = new ImagePanel(new ImageIcon("Images/Apple.jpg").getImage());
	
	/** The donut panel. */
	private static JPanel donutPanel = new ImagePanel(new ImageIcon("Images/Donut.jpg").getImage());
	
	/** The water panel. */
	private static JPanel waterPanel = new ImagePanel(new ImageIcon("Images/Water.jpg").getImage());
	
	/** The nerd panel. */
	private static JPanel nerdPanel = new ImagePanel(new ImageIcon("Images/NerdG.jpg").getImage());
	
	/** The sun panel. */
	private static JPanel sunPanel = new ImagePanel(new ImageIcon("Images/Sunglasses.jpg").getImage());
	
	/** The shirt panel. */
	private static JPanel shirtPanel = new ImagePanel(new ImageIcon("Images/Shirt.jpg").getImage());
	
	/** The phone panel. */
	private static JPanel phonePanel = new ImagePanel(new ImageIcon("Images/Phone.jpg").getImage());
	
	/** The book panel. */
	private static JPanel bookPanel = new ImagePanel(new ImageIcon("Images/Book.jpg").getImage());
	
	/** The dumbell panel. */
	private static JPanel dumbellPanel = new ImagePanel(new ImageIcon("Images/Dumbell.jpg").getImage());
	
	/** The empty panel 1. */
	private static JPanel emptyPanel1 = new ImagePanel(new ImageIcon("Images/EmptyInv.png").getImage());
	
	/** The empty panel 2. */
	private static JPanel emptyPanel2 = new ImagePanel(new ImageIcon("Images/EmptyInv.png").getImage());
	
	/** The empty panel 3. */
	private static JPanel emptyPanel3 = new ImagePanel(new ImageIcon("Images/EmptyInv.png").getImage());
	
	/** The empty panel 4. */
	private static JPanel emptyPanel4 = new ImagePanel(new ImageIcon("Images/EmptyInv.png").getImage());
	
	/** The empty panel 5. */
	private static JPanel emptyPanel5 = new ImagePanel(new ImageIcon("Images/EmptyInv.png").getImage());
	
	/** The empty panel 6. */
	private static JPanel emptyPanel6 = new ImagePanel(new ImageIcon("Images/EmptyInv.png").getImage());
	
	/** The empty panel 7. */
	private static JPanel emptyPanel7 = new ImagePanel(new ImageIcon("Images/EmptyInv.png").getImage());
	
	/** The empty panel 8. */
	private static JPanel emptyPanel8 = new ImagePanel(new ImageIcon("Images/EmptyInv.png").getImage());
	
	/** The connect. */
	private static PHPConnect connect = new PHPConnect();
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		//updateInventory();
		showInventory();

	}
	
	/**
	 * Instantiates a new inventory.
	 *
	 * @param pc the pc
	 */
	public Inventory(PC pc){
		this.pc = pc;
		ArrayList<String> itemCol = new ArrayList<String>();
		itemCol.add("InventoryID");
		
		items = connect.sendAndReceive("select InventoryID from Inventory where username = \'" + pc.getUsername() + "\'", itemCol);	
	}
	
	/**
	 * Update inventory.
	 */
	public  void updateInventory() {
		ArrayList<String> itemCol = new ArrayList<String>();
		itemCol.add("InventoryID");
		if(pc.getUsername()!=null){
		items = connect.sendAndReceive("select InventoryID from Inventory where username = \'" + pc.getUsername() + "\'", itemCol);	
		}
		else{
			items = connect.sendAndReceive("select InventoryID from Inventory where username = \'" + pc.getUsername() + "\'", itemCol);	
		}
		for(int i = 0; i < items.size(); i++) {
			if(Integer.parseInt(items.get(i)) == 1) {
				mainPanel.add(nerdPanel);
			}
			else if(Integer.parseInt(items.get(i)) == 2) {
				mainPanel.add(sunPanel);
			}
			else if(Integer.parseInt(items.get(i)) == 3) {
				mainPanel.add(shirtPanel);
			}
			else if(Integer.parseInt(items.get(i)) == 4) {
				mainPanel.add(phonePanel);
			}
			else if(Integer.parseInt(items.get(i)) == 5) {
				mainPanel.add(dumbellPanel);
			}
			else if(Integer.parseInt(items.get(i)) == 6) {
				mainPanel.add(bookPanel);
			}
			else if(Integer.parseInt(items.get(i)) == 11) {
				mainPanel.add(applePanel);
			}
			else if(Integer.parseInt(items.get(i)) == 12) {
				mainPanel.add(donutPanel);
			}
			else if(Integer.parseInt(items.get(i)) == 13) {
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
	
	
	/**
	 * Return inventory.
	 *
	 * @return the j panel
	 */
	public JPanel returnInventory() {
		return mainPanel;
	}
	
	/**
	 * Show inventory.
	 */
	public static void showInventory() {
		frame.add(mainPanel);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(250, 200));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
