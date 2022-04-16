package Mapping;

import java.io.IOException;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import Players.*;

@Deprecated
public class GameState {
	
	public static JackTheRipper jack;
	public static Inspector inBlue;
	public static Inspector inRed;
	public static Inspector inYellow;
	public static Inspector inGreen;
	public static Inspector inBrown;
	public static BoardMap map;
	private String gameName;
	public static HashMap<String, PlayerRole> userRoleMap;
	
	
	public GameState(String lobbyName) throws ParserConfigurationException, SAXException, IOException
	{
		//TODO change all of the nulls
		gameName = lobbyName;
		jack = new JackTheRipper(null);
		inBlue = new Inspector(null);
		inRed = new Inspector(null);
		inYellow = new Inspector(null);
		inGreen = new Inspector(null);
		inBrown = new Inspector(null);
		map = new BoardMap("src\\Mapping\\board.xml");
		userRoleMap = new HashMap<>();
	}
	
	public void setJackUser(String userName)
	{
		userRoleMap.put(userName, jack);
	}
	
	public void setBlueInspectorUser(String userName)
	{
		userRoleMap.put(userName, inBlue);
	}
	
	public void setRedInspectorUser(String userName)
	{
		userRoleMap.put(userName, inRed);
	}
	
	public void setYellowInspectorUser(String userName)
	{
		userRoleMap.put(userName, inYellow);
	}
	
	public void setGreenInspectorUser(String userName)
	{
		userRoleMap.put(userName, inGreen);
	}
	
	public void setBrownInspectorUser(String userName)
	{
		userRoleMap.put(userName, inBrown);
	}

}
