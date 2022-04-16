package main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PHPConnect {
	public static void main(String[] args) {
		// =Select%20*%20From%20Users&col1=Username"
		ArrayList<String> fun = new ArrayList<>();
		// UPDATE MyGuests SET lastname='Doe' WHERE id=2"
		map map = new map();
		PC pc= new PC(1,1,map);
		// fun.add("LastX");
		fun.add("LastY");
		PHPConnect test = new PHPConnect();
		/*String[] saget = test.sendAndReceive("UPDATE Users SET LastX=" + pc.getRow()+" WHERE Username='efg'", null);
		for (String s : saget) {
			System.out.println(s);
		}*/
	}

	public ArrayList<String> sendAndReceive(String param1, ArrayList<String> cols) {
		ArrayList<String> output = new ArrayList<String>();
		
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
			} else if (param1.contains("insert") || param1.contains("INSERT") || param1.contains("update")
					|| param1.contains("UPDATE")|| param1.contains("delete from") || param1.contains("DELETE FROM")) {
				url = new URL("http://proj-309-gk-b-6.cs.iastate.edu/insert_data_sql.php?param1=" + param1);
			} else {
				// String timeStamp = new
				// SimpleDateFormat("HH.mm.ss").format(new Date());
				url = new URL("http://proj-309-gk-b-6.cs.iastate.edu/send_chat.php?param1=" + param1);
			}
			URLConnection uc = url.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
			String inputLine;
			String retLine = "";
			while ((inputLine = in.readLine()) != null)
				retLine += inputLine;
			in.close();
			if (retLine.length() > 0) {
				retValues = retLine.split("\\|");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for(int j = 0; j < retValues.length; j++)
			output.add(retValues[j]);
		
		return output;
	}

	public void sendPC(PC pc) {
		URL url;
		try {
			url = new URL("http://proj-309-gk-b-6.cs.iastate.edu/update_pc.php?pcX=" + pc.getRow() + "&" + "pcY="
					+ pc.getCol() + "&" + "user=" + "efg");
			URLConnection uc = url.openConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}