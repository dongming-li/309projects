/**
 * 
 * @author Grant Duncan
 *
 */
package gameMain;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * The Class load.
 */
public class load {
	
	/** The oi. */
	static ObjectInputStream oi;

	/**
	 * Instantiates a new load.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public load() throws IOException{
		
	}
	
	/**
	 * Load map.
	 *
	 * @return the map
	 * @throws ClassNotFoundException the class not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static map loadMap() throws ClassNotFoundException, IOException{
		File file = new File("map.data");
		FileInputStream fi=new FileInputStream(file.getAbsolutePath());
		oi= new ObjectInputStream(fi);
		map map= (map) oi.readObject();
		oi.close();
		return map;
	}
	
	/**
	 * Load PC.
	 *
	 * @return the pc
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException the class not found exception
	 */
	public static PC loadPC() throws IOException, ClassNotFoundException{
		File file = new File("pc.data");
		FileInputStream fi=new FileInputStream(file.getAbsolutePath());
		oi= new ObjectInputStream(fi);
		PC pc=  (PC) oi.readObject();
		oi.close();
		return pc;
	}
	/*public static quests loadQuests() throws IOException, ClassNotFoundException{
		File file = new File("quests.data");
		FileInputStream fi=new FileInputStream(file.getAbsolutePath());
		oi= new ObjectInputStream(fi);
		quests quest=  (quests) oi.readObject();
		oi.close();
		return quest;
	}*/
	
}
