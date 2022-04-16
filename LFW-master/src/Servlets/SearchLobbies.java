package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.*;

public class SearchLobbies extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		DB db = new DB();
		
		response.setContentType("text/html");
	    PrintWriter HTMLout = response.getWriter();
		
		int timer = Integer.parseInt(request.getParameter("timer"));
		boolean isPrivate = Boolean.parseBoolean("isPrivate");
		
		List<Lobby> lobbies = db.searchLobby(timer,isPrivate);
		
		for(Lobby l:lobbies)
		{
			int GameID = l.getGameID();
			List<Game> games = db.getGameFromID(GameID);
			if(6<=games.size())
			{
				for(Game g: games)
				{
					if("jack".equals(g.getRole()))
					{
						HTMLout.print("<div>" + l.getLobbyName() + "\t" + g.getUsername() + "</div>");
						break;
					}
				}
			}
			
		}
		db.deleteDB();
	}
}
