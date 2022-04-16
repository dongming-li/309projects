package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Mapping.BoardMap;
import Players.Inspector;
import Players.JackTheRipper;
import db.DB;
import db.Game;

/**
 * Servlet used to get the movable nodes by a player
 * @author sauerbreiale
 *
 */
public class DoShow extends HttpServlet{
	
	//Holds the map for logic to be done on the nodes of the map
	BoardMap map = new BoardMap(BoardMap.serverpath + "board.xml");
	
	/**
	 * Method used to show the nodes a player is able to move to
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
		
		//Gets the player's current role and position
		String playerRole = currentGame.getRole();
		String playerPosition = currentGame.getPosition();
		
		//Sets the content type for writing back to the client
		response.setContentType("text/html");
		PrintWriter HTMLout = response.getWriter();
		
		if ("jack".equalsIgnoreCase(playerRole))
		{
			//If the player is jack, create a new jack object that is at the position in the database
			JackTheRipper jack = new JackTheRipper(map.getNode(playerPosition));
			//get the list of moveable nodes when neither token is activated
			Set<String> moveableNodes= jack.getMovableNodes(false, false).keySet();
			//prints out the movable nodes as buttons for HTML
			for(String node : moveableNodes)
			{
				HTMLout.print("<button id=\""+node+"\" type=\"button\" class=\"btn btn-danger\" onclick=\"activateJ(this)\">"+node+"</button>");
			}
		}
		
		else if("inspector".equalsIgnoreCase(playerRole))
		{
			//If the player is inspector, create a new inspector object that is at the position in the database
			Inspector inspector = new Inspector(map.getNode(playerPosition));
			//get the list of moveable nodes for an inspector
			Set<String> moveableNodes= inspector.getMovableNodes().keySet();
			//prints out the movable nodes as buttons for HTML
			for(String node : moveableNodes)
			{
				HTMLout.print("<button id=\""+node+"\" type=\"button\" class=\"btn btn-danger\" onclick=\"activateI(this)\">"+node+"</button>");
			}
		}
		
		else{
			//if invalid move, print out the specified exception message to the user
			throw new IOException("Something is misspelled or something somewhere.");
		}

		//closes the database connection
		db.deleteDB();
	}

}
