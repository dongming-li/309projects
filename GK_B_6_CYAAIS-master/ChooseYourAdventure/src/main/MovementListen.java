package main;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JPanel;

public class MovementListen extends JPanel implements KeyListener {
	private PC pc;
	private map map;
	private int number;
	public boolean action = false;
	static GraphicalStuff back;
	private char lastTile;
	private BufferedImage[] tiles;
	private int pcX;
	private int pcY;
	private boolean isMovement = false;
	private boolean hasMovement = false;
	public MovementListen() {
		 setBackground(Color.LIGHT_GRAY);
		   setBounds(50, 50, 420, 90);
		   
		  //setVisible(true);
		 
	}
	@Override
	public void keyPressed(KeyEvent ke) {
		switch (ke.getKeyCode()) {
			
		case KeyEvent.VK_UP: {
			hasMovement = true;
			System.out.println("UP");
		}
			
		

	}
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		

		
		

	}

	@Override
	public void keyTyped(KeyEvent e) {
		

	
	 
             
         }
	public boolean isMovement() {
		return isMovement;
	}
	public void setMovement(boolean isMovement) {
		this.isMovement = isMovement;
	}


}

