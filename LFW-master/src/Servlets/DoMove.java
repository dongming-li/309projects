package Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Mapping.BoardMap;
import Players.Inspector;
import Players.JackTheRipper;
import Players.PlayerRole;
import Players.PlayerRole.InvalidMovementException;
import db.DB;
import db.Game;

/**
 * This Servlet is used to perform a move operation for the game for either a Jack or Inspector player role
 * @author sauerbreiale
 *
 */
public class DoMove extends HttpServlet{
	
	//Holds the map for logic to be done on the nodes of the map
	BoardMap map = new BoardMap(BoardMap.serverpath + "board.xml");
	
	/**
	 * Method used to do the actual movement of the player in the database in the current game
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
		
		//Gets the player's role and position in the game
		String playerRole = currentGame.getRole();
		String playerPosition = currentGame.getPosition();
		
		//Sets the content type for writing back to the client
		response.setContentType("text/html");
	    PrintWriter HTMLout = response.getWriter();
		
		if("jack".equalsIgnoreCase(playerRole))
		{
			//If the player is jack, create a new jack object that is at the position in the database
			JackTheRipper jack = new JackTheRipper(map.getNode(playerPosition));
			try{
				//Try to make a move on the board and see if it is valid
				jack.setPosition(map.getNode(request.getParameter("newPosition")), currentGame.getUsername() ,db);
				//Update the night's move number in the database
				db.updateMove(currentGame.getUsername());
				
				db.updateTurn(currentGame.getGameID(),db.getUserFromGameIDAndColor(currentGame.getGameID(), PlayerRole.colors[1]));
				//give response to the user
				HTMLout.print("move done successfully");
			}
			catch(InvalidMovementException e)
			{
				//if invalid move, print out the specified exception message to the user
				HTMLout.print(e.getMessage());
			}
		}
		else if("inspector".equalsIgnoreCase(playerRole))
		{
			//If the player is inspector, create a new inspector object that is at the position in the database
			Inspector ins = new Inspector(map.getNode(playerPosition));
			try{
				//Try to make a move on the board and see if it is valid
				ins.setPosition(map.getNode(request.getParameter("newPosition")), currentGame.getUsername() ,db);
				
				int i;
				for(i=0;i<PlayerRole.colors.length; i++)
				{
					if(PlayerRole.colors[i].equals(currentGame.getColor()))
					{
						db.updateTurn(currentGame.getGameID(), db.getUserFromGameIDAndColor(currentGame.getGameID(), PlayerRole.colors[i+1]));
						break;
					}
				}
				if(i>=PlayerRole.colors.length)
				{
					db.updateTurn(currentGame.getGameID(), db.getJackFromGameID(currentGame.getGameID()));
				}
				
				
				//give response to the user
				HTMLout.print("move done successfully");
			}
			catch(InvalidMovementException e)
			{
				//if invalid move, print out the specified exception message to the user
				HTMLout.print(e.getMessage());
			}
		}
		else
		{
			//Shouldn't happen, but if it does, throw an exception
			throw new IOException("Something is misspelled or something somewhere.");
		}
		//close the database connection
		db.deleteDB();
		
	}

}
