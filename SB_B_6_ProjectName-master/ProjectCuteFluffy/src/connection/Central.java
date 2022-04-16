package connection;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Central")
public class Central extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static String[] namers = null;
    public Central() {
        super();
    }
    
    public enum Command {Select, Confirm, Home, Leaderboard, Lobby, Matchmaker, Play, Profile, Store, Info,}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		Command command = Command.valueOf(action);
		switch(command) {
		case Select:
			break;
		case Confirm:
			break;
		case Home:
			homeAction(request, response);
			break;
		case Leaderboard:
			break;
		case Lobby:
			lobbyAction(request, response);
			break;
		case Matchmaker:
			matchmakerAction(request, response);
			break;
		case Play:
			playAction(request, response);
			break;
		case Profile:
			profileAction(request, response);
			break;
		case Store:
			storeAction(request, response);
			break;
		case Info:
			infoAction(request, response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		leaderboardAction(request, response);
	}
	
	public void homeAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();
		
		printWriter.println("<html>");
		printWriter.println("<head>");
		printWriter.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"StoneageStyle.css\">");
		printWriter.println("</head>");
		printWriter.println("<body class=home>");
		printWriter.println("<div>");
		printWriter.println("<h1>This is the homepage</h1>");
		printWriter.println("<p>This is the response from server<br>");
		printWriter.println("This homepage is still in development phase.<br>");
		printWriter.println("It is limited in functions.<br>");
		printWriter.println("All Webpages do ABSOLUTELY NOTHING but respond. Except Play.<br>");
		printWriter.println("Please be patience and wait. This is our Progress!<br>");
		printWriter.println("Initial Testing: HTML/Servlet Connection<br>");
		printWriter.println("In Progress: Database Connection<br>");
		printWriter.println("In Progress: GameMechanics<br>");
		printWriter.println("In Progress: JSP Connection<br>");
		printWriter.println("For future, please contact Developers.</p><br>");
		printWriter.println("</div>");
		printWriter.println("</body>");
		printWriter.println("</html>");
		
		printWriter.println();
		printWriter.close();
	}
	
	public void infoAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();
		
		printWriter.println("<html>");
		printWriter.println("<head>");
		printWriter.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"StoneageStyle.css\">");
		printWriter.println("</head>");
		printWriter.println("<body class=info>");
		printWriter.println("<div>");
		printWriter.println("<p>This is response from server!<br>");
		printWriter.println("GameName:	Stoneage<br>");
		printWriter.println("GameType:	Turn-Based: Card-Based: Strategic: Upgrade: Square-Terrain: etc:<br>");
		printWriter.println("GameProg:	Informational Phase - Initial Construction Phase<br>");
		printWriter.println("GameDev :	Cody Lakin, Dongkyun Kim, Chan Woong Moon, Johnson Phan</p><br>");
		printWriter.println("</div>");
		printWriter.println("</body>");
		printWriter.println("</html>");
		
		printWriter.println();
		printWriter.close();
	}
	
	public void leaderboardAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("Codes Received:");
		/*if(namers == null) {
			namers = new String[1];
			namers[0] = request.getParameter("name");
		}
		else {
			String[] temp = new String[namers.length + 1];
			for(int x=0;x<namers.length;x++) temp[x] = namers[x];
			temp[temp.length - 1] = request.getParameter("name");
			namers = temp;
		}*/
		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();
		
		printWriter.println("<html>");
		printWriter.println("<head>");
		printWriter.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"StoneageStyle.css\">");
		printWriter.println("</head>");
		printWriter.println("<body class=leaderboard>");
		printWriter.println("<p>");
		printWriter.println("Server Response:");
		printWriter.println("</p><br>");
		//for(int x=0;x<namers.length;x++) printWriter.println(namers[x] + "<br>");
		printWriter.println("</body>");
		printWriter.println("</html>");
		
		printWriter.println();
		printWriter.close();
	}
	
	public void lobbyAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();
		
		printWriter.println();
		printWriter.close();
	}
	
	public void matchmakerAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();
		
		printWriter.println();
		printWriter.close();
	}
	
	public void playAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();
		
		printWriter.println();
		printWriter.close();
	}
	
	public void profileAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();
		
		printWriter.println();
		printWriter.close();
	}
	
	public void storeAction(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter printWriter = response.getWriter();
		
		printWriter.println();
		printWriter.close();
	}

}
