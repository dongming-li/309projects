/**
 * 
 * @author Grant Duncan
 *
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class save implements java.io.Serializable {
 
	public save(map map, PC pc, quests quest) throws IOException{
		//saves the map
		File file = new File("map.data");
		if (!file.exists())
			file.createNewFile();
		
		FileOutputStream data= new FileOutputStream(file.getAbsolutePath());
		ObjectOutputStream ob = new ObjectOutputStream(data);
		
		ob.writeObject(map);
		ob.close();
		
		//save the pc
		file=new File("pc.data");
		if(!file.exists())
			file.createNewFile();
		data= new FileOutputStream(file.getAbsolutePath());
		ob = new ObjectOutputStream(data);
		ob.writeObject(pc);
		ob.close();
		
		//save the items
//		file=new File("items.data");
//		if(!file.exists())
//			file.createNewFile();
//		data= new FileOutputStream(file.getAbsolutePath());
//		ob = new ObjectOutputStream(data);
//		ob.writeObject(pc);
//		ob.close();
		
		//save the quests
		file=new File("quests.data");
		if(!file.exists())
			file.createNewFile();
		data= new FileOutputStream(file.getAbsolutePath());
		ob = new ObjectOutputStream(data);
		ob.writeObject(quest);
		ob.close();
	}
}
