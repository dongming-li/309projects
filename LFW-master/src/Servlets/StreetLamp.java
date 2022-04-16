package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Mapping.BoardMap;
import Players.JackTheRipper;

import db.*;

/**
 * Servlet used to get the movable nodes if a StreetLamp token is activated by jack
 * @author sauerbreiale
 *
 */
public class StreetLamp extends HttpServlet{
	
	//Holds the map for logic to be done on the nodes of the map
	BoardMap map = new BoardMap(BoardMap.serverpath + "board.xml");
	
	/**
	 * Method used to show the nodes a Jack player is able to move to when a streetlamp is activated
	 * 
	 * @param request - the HTTPRequest from the client that holds the information for the logic
	 * @param response - the HTTPResponse that holds how to communicate back to the client
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		//Establishes a database connection
		DB db = new DB();
		
		//Gets the game that the current user is in
		Game currentGame = db.getGame(request.getParameter("username"));
		//gets the position of the player in the current game
		String playerPosition = currentGame.getPosition();
		
		//Sets the content type for writing back to the client
		response.setContentType("text/html");
	    PrintWriter HTMLout = response.getWriter();
		
	    //create a new jack object that is at the position in the database
		JackTheRipper jack = new JackTheRipper(map.getNode(playerPosition));
		//gets the movable nodes that the player can move to
		Set<String> moveableNodes= jack.getMovableNodes(true, false).keySet();
		//prints out the movable nodes as buttons for HTML
		for(String node : moveableNodes)
		{
			HTMLout.print("<button id=\""+node+"\" type=\"button\" class=\"btn btn-danger\" onclick=\"activateJ(this)\">"+node+"</button>");
		}
		
		//closes the database connection
		db.deleteDB();
	}

}
