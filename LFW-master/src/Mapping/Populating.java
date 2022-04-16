package Mapping;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
/**
 * Helper class that was used to populate the xml file with nodes and neighbor relations
 * @author sauerbreiale
 *
 */
@Deprecated
public class Populating {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			String data = " This is new content";

			File file = new File("board.xml");

			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			// true = append file
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			//TODO for loop for writing stuff
			for(int i = 1; i<=7; i++)
			{
				bw.write("<node id=\"Y"+i +"\" color=\"y\" user=\"inspectors\"/>\n");
			}

			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
	}

}
}
