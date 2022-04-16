package gameMain;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.JPanel;

/**
 * The Class MovementListen.
 */
public class MovementListen extends JPanel implements KeyListener {
	
	/** The pc. */
	private PC pc;
	
	/** The map. */
	private map map;
	
	/** The number. */
	private int number;
	
	/** The action. */
	public boolean action = false;
	
	/** The back. */
	static GraphicalStuff back;
	
	/** The last tile. */
	private char lastTile;
	
	/** The tiles. */
	private BufferedImage[] tiles;
	
	/** The pc X. */
	private int pcX;
	
	/** The pc Y. */
	private int pcY;
	
	/** The is movement. */
	private boolean isMovement = false;
	
	/** The has movement. */
	private boolean hasMovement = false;
	
	/**
	 * Instantiates a new movement listen.
	 */
	public MovementListen() {
		 setBackground(Color.LIGHT_GRAY);
		   setBounds(50, 50, 420, 90);
		   
		  //setVisible(true);
		 
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent ke) {
		switch (ke.getKeyCode()) {
			
		case KeyEvent.VK_UP: {
			hasMovement = true;
			System.out.println("UP");
		}
			
		

	}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent ke) {
		

		
		

	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {
		

	
	 
             
         }
	
	/**
	 * Checks if is movement.
	 *
	 * @return true, if is movement
	 */
	public boolean isMovement() {
		return isMovement;
	}
	
	/**
	 * Sets the movement.
	 *
	 * @param isMovement the new movement
	 */
	public void setMovement(boolean isMovement) {
		this.isMovement = isMovement;
	}


}

