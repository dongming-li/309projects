package Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DB;

public class GameIDForUser extends HttpServlet{
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		DB db = new DB();
		
		response.setContentType("text/html");
	    PrintWriter HTMLout = response.getWriter();
	    
	    HTMLout.print(db.getGame(request.getParameter("username")));
	    db.deleteDB();
	    
	}

}
