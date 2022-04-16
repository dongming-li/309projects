package gameMain;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;

import javax.swing.JTextField;

/**
 * The Class Teleport.
 */
public class Teleport {

/** The frame. */
private static JFrame frame = new JFrame("Teleport");

/** The main panel. */
private static JPanel mainPanel ;

/** The pc. */
private PC pc;

/** The map. */
private map map;

/** The last tile. */
private char lastTile;
 
 /** The message. */
 JTextField message ;
	   
   	/** The message 2. */
   	JTextField message2 ;
	
	/**
	 * Instantiates a new teleport.
	 *
	 * @param map the map
	 * @param pc the pc
	 * @param back the back
	 */
	public Teleport(map map , PC pc,GraphicalStuff back){
		
		JLabel xInput = new JLabel("Enter X coordinate");
		JLabel yInput  =new  JLabel("Enter Y coordinate");
		
		 JButton sendMessage = new JButton("Teleport");
		JButton sendMessage2 = new JButton("Send");
	  
	    //yInput.setBounds(0, 60, 200, 100);
	   // message2.setBounds(110, 100, 50, 20);
	   // sendMessage2.setBounds(180,95,150,27);
        //chatArea.setEditable(false);
      //  chatArea.setLineWrap(true);
        
 // . xInput.setPreferredSize(new Dimension(10,10));
	//	message.setPreferredSize(new Dimension(1,1));
	//	message2.setPreferredSize(new Dimension(100,5));
		//mainPanel.add(message2);
		//mainPanel.add(yInput);
		//mainPanel.add(sendMessage2);
		//mainPanel.setLayout(new GridBagLayout()); // This should be set earlier
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   frame.setBounds(100, 100, 450, 300);
       // frame.setUndecorated(true);
        frame.setAlwaysOnTop(true);
        frame.setLocationRelativeTo(null);
        mainPanel = new JPanel();
        mainPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        frame.setContentPane(mainPanel);
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[] {100, 0};
        gbl_contentPane.rowHeights = new int[]{0, 0, 0};
        gbl_contentPane.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
       // gbl_contentPane.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
        mainPanel.setLayout(gbl_contentPane);

        JLabel lblNewLabel = new JLabel("Enter X Coordinate");
        GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
        gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
        gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel.gridx = 0;
        gbc_lblNewLabel.gridy = 0;
        mainPanel.add(lblNewLabel, gbc_lblNewLabel);

       message = new JTextField();
        GridBagConstraints gbc_textField = new GridBagConstraints();
        gbc_textField.insets = new Insets(0, 0, 5, 0);
        gbc_textField.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField.gridx = 1;
        gbc_textField.gridy = 0;
        mainPanel.add(message, gbc_textField);
        message.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Enter Y Coordinate");
        GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
        gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
        gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
        gbc_lblNewLabel_1.gridx = 0;
        gbc_lblNewLabel_1.gridy = 1;
        mainPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);

        message2= new JTextField();
        GridBagConstraints gbc_textField_1 = new GridBagConstraints();
        gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
        gbc_textField_1.gridx = 1;
        gbc_textField_1.gridy = 1;
        mainPanel.add(message2, gbc_textField_1);
        message2.setColumns(10);
      
        gbc_textField_1.gridx = 0;
        gbc_textField_1.gridy = 3;
        
        mainPanel.add(sendMessage,gbc_textField_1);
        
        
        
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                   
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
        
        sendMessage.addActionListener(new ActionListener() {
			@SuppressWarnings("resource")
			public void actionPerformed(ActionEvent e) {
				
				String xValue = message.getText();
				String yValue = message2.getText();
				message.setText("");
				message.setText("");
				Scanner scan = new Scanner(xValue);
				int x =  pc.getRow();
				if(scan.hasNextInt()) {
				 x = scan.nextInt();
				
				}
				int y = pc.getCol();
			scan = new Scanner(yValue);
			if(scan.hasNextInt()) {
			 y =scan.nextInt();
			}
			
			scan.close();
			frame.dispose();
			teleportTo(map,pc,x,y ,back);
			
				}
    });
	
}

		//mainPanel.add(sendMessage);
		
/**
		 * Teleport to.
		 *
		 * @param map the map
		 * @param pc the pc
		 * @param x the x
		 * @param y the y
		 * @param back the back
		 */
		public void teleportTo( map map, PC pc,int x , int y ,GraphicalStuff back) {
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
		if (mapTile == mapNode.BUSSTOP) {
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
	//pc.placePC(35, 26);
	pc.setUnderPC(map.getRowCol(x, y));
	//pc.setUnderPC('@');
	
	
}
	if (map.placePC(x, y, pc)) {
	} else
		map.setRowCol(x, y, '@');
	
	
	
	try {
		back.updateImage(map,pc.getRow(),pc.getCol());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
//map.setdirectionName("down");

}



	

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		//Teleport test = new Teleport();

	}

}
