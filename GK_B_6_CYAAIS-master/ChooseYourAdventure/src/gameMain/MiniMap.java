package gameMain;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The Class MiniMap.
 */
public class MiniMap implements KeyListener{
	
	/** The frame. */
	private static JFrame frame = new JFrame("MiniMap");
	
	/** The main panel. */
	private static JPanel mainPanel ;
	
	/** The pc. */
	private PC pc;
	
	/** The map. */
	private map map;
	
	/** The is open. */
	private  boolean isOpen;
	
	/** The image dir. */
	private final  String imageDir = System.getProperty("user.dir") + "\\Images";
	
	/**
	 * Instantiates a new mini map.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public MiniMap() throws IOException{
		
	
		 mainPanel = new JPanel();
		frame = new JFrame();
			frame.addKeyListener(this);
		frame.setBounds(100, 100, 500, 900);
        frame.setUndecorated(true);
       frame.setAlwaysOnTop(true);
       frame.setLocationRelativeTo(null);
      frame.setFocusable(true);
      
	}
	
	/**
	 * Creates the map.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public  void createMap() throws IOException {
		
		
       
        
	BufferedImage myPicture = ImageIO.read(new File(imageDir +"\\txtMap.png"));
	JLabel picLabel = new JLabel(new ImageIcon(myPicture));
	mainPanel.add(picLabel);
	frame.add(mainPanel);
	frame.setVisible(true);
	isOpen=true;
	}

/**
 * Close map.
 */
public  void closeMap() {
	//frame.setVisible(false);
	frame.dispose();
	
}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws IOException {
		
		//MiniMap.createMap(true);
		//MiniMap.closeMap();
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent ke) {
		if(ke.getKeyCode() == KeyEvent.VK_M) {
			closeMap();
		}
		// TODO Auto-generated method stub
		
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent ke) {
		// TODO Auto-generated method stub
		
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent ke) {
		// TODO Auto-generated method stub
		
	}

}
