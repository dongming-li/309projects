package gameMain;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * The Class PHPConnect.
 */
public class PHPConnect {

	/**
	 * Send and receive.
	 *
	 * @param param1 the param 1
	 * @param cols the cols
	 * @return the array list
	 */
	public ArrayList<String> sendAndReceive(String param1, ArrayList<String> cols) {
		ArrayList<String> output = new ArrayList<String>();
		boolean isReceive = false;
		boolean isUpdate = false;
		param1 = param1.replace(" ", "%20");
		URL url;
		String[] retValues = null;
		String columns = "";
		int i = 1;
		if (cols != null) {
			for (String s : cols) {
				columns += "&col" + i + "=" + s;
				i++;
			}
		}
		try {
			if (param1.contains("select") || param1.contains("SELECT")) {
				url = new URL("http://proj-309-gk-b-6.cs.iastate.edu/select_data_sql.php?param1=" + param1 + columns);
				isReceive = true;
			} else if (param1.contains("insert") || param1.contains("INSERT") || param1.contains("update")
					|| param1.contains("UPDATE") || param1.contains("delete from") || param1.contains("DELETE%20FROM")) {
				url = new URL("http://proj-309-gk-b-6.cs.iastate.edu/insert_data_sql.php?param1=" + param1);
			//	isReceive = true;
				if(param1.contains("efg")){
				isUpdate = true;
				}
			} else {
				url = new URL("http://proj-309-gk-b-6.cs.iastate.edu/test.php");
				isReceive = true;
			}

			URLConnection uc = url.openConnection();
			if (isReceive) {
				BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
				String inputLine;
				String retLine = "";
				while ((inputLine = in.readLine()) != null)
					retLine += inputLine;
				in.close();
				if (retLine.length() > 0) {
					retValues = retLine.split("\\|");
				}
//				if(isUpdate){
//					System.out.println(retLine);
//				}
				in.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (isReceive) {
			for (int j = 0; j < retValues.length; j++)
				output.add(retValues[j]);
		}
	
		return output;
	}

	/**
	 * Send PC.
	 *
	 * @param pc the pc
	 */
	public void sendPC(PC pc) {
		URL url;
		try {
			url = new URL("http://proj-309-gk-b-6.cs.iastate.edu/update_pc.php?pcX=" + pc.getRow() + "&" + "pcY="
					+ pc.getCol() + "&" + "user=" + "efg");
			@SuppressWarnings("unused")
			URLConnection uc = url.openConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Inits the NP cs.
	 *
	 * @param username the username
	 * @param npcs the npcs
	 */
	public void initNPCs(String username, ArrayList<NPC> npcs) {
		for (int i = 0; i < npcs.size(); i++) {
			new PHPConnect().sendAndReceive(
					"insert into NPCs (Username, Image, CoordX, CoordY, ID) values('"+ username +"', 'whocares', 1," + i + ","
							+ i + ");",
					null);
		}
	}
}