/**
 * 
 * @author Grant Duncan
 *
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class load {
	static ObjectInputStream oi;

	public load() throws IOException{
		
	}
	public static map loadMap() throws ClassNotFoundException, IOException{
		File file = new File("map.data");
		FileInputStream fi=new FileInputStream(file.getAbsolutePath());
		oi= new ObjectInputStream(fi);
		map map= (map) oi.readObject();
		oi.close();
		return map;
	}
	public static PC loadPC() throws IOException, ClassNotFoundException{
		File file = new File("pc.data");
		FileInputStream fi=new FileInputStream(file.getAbsolutePath());
		oi= new ObjectInputStream(fi);
		PC pc=  (PC) oi.readObject();
		oi.close();
		return pc;
	}
	public static quests loadQuests() throws IOException, ClassNotFoundException{
		File file = new File("quests.data");
		FileInputStream fi=new FileInputStream(file.getAbsolutePath());
		oi= new ObjectInputStream(fi);
		quests quest=  (quests) oi.readObject();
		oi.close();
		return quest;
	}
	
}
