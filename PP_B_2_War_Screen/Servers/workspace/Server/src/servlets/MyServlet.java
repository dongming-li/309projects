package servlets;

import java.io.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Servlet implementation class MyServlet
 */
@WebServlet("/MyServlet")
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String UserWaiting = null;
	private int lastHexIndex = 8;
	private String match = "match";
    /**
     * Default constructor. 
     */
    public MyServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<html><body>");
		out.println("<h3>Hello World</h3>");
		out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
        String reqType = request.getParameter("reqType");
        
        String responseMessage = "init";
        
        WSDatabase Database = new WSDatabase();
        //Database.StartConnection();
        try {
			Database.StartConnection();
		} catch (Exception e) {
			e.printStackTrace();
			out.println("database offline");
			return;
		}
        
        switch(reqType){
        	case "register":
        		responseMessage = register(request, Database);
        		break;
        	case "login":
                responseMessage = login(request, Database);
        		break;
        	case "newGame":
        		responseMessage = newGame(request, Database);
        		break;
        	case "chatUpdate":
        		responseMessage = GetChatUpdate(request, Database);
        		break;
        	case "chatPost":
        		responseMessage = ChatPost(request, Database);
        		break;
        	case "leaveGame":
        		responseMessage = leaveGame(request, Database);
        		break;
        	case "buildUnits":
        		responseMessage = buildUnits(request, Database);
        		break;
        	case "moveUnits":
        		responseMessage = moveUnits(request, Database);
        		break;
        	case "updateGame":
        		
        		//is there an actual game or is first user waiting?
        		if(UserWaiting != null && request.getParameter("username").equals(UserWaiting))
        		{
        			System.out.printf("%s, %s\n", UserWaiting, request.getParameter("username"));
        			responseMessage = "Still Match Making";
        			break;
        		}
        		
        		JSONObject JSONresponse = new JSONObject();
        		System.out.println("UpdateGame");
        		try {
					JSONresponse = updateGame(request, Database);
				} catch (Exception e) {
						// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		
        		responseMessage = JSONresponse.toString();
        		break;
        	default:
        		out.println("Invalid request");
        		break;
        }   
        
        Database.CutConnection();
        
        out.println(responseMessage);
        
        return;
	}
 
	public JSONObject updateGame(HttpServletRequest request, WSDatabase Database) throws Exception
	{
		
		//String jsonString = request.getParameter("json");
		String gameID = request.getParameter("gameID");
		String username = request.getParameter("username");
		System.out.println(username);
		System.out.println(gameID);
		JSONObject json = null;
		JSONObject jsonResponse = null;
		
		int UserNumber = 0;
		
		gameID = gameID.replace("\n", "");
		
		String User1 = Database.GetUser1(gameID);
		String User2 = Database.GetUser2(gameID);
		System.out.println(User1);
		System.out.println(User2);
		
		if(username.equals(User1)){
			UserNumber = 1;
		}
		else if(username.equals(User2)){
			UserNumber = 2;
		}
		
		System.out.println(Integer.valueOf(UserNumber));
		
		try {
			//json = (JSONObject)new JSONParser().parse(jsonString);
			jsonResponse = getData(gameID, UserNumber, Database);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jsonResponse;//.toString();
	}
	
	public JSONObject getData(String gameID, int UserNumber, WSDatabase Database) throws Exception
	{
		
		JSONObject jsonResponse = new JSONObject();
		
		try {
			jsonResponse.put("gameID", gameID);
			
			int i = 0;
			if(UserNumber == 2){
				while(i <= 8)
				{
					System.out.println("get hex data");
					jsonResponse.put(String.valueOf(i) + "hex" + "U1", String.valueOf(Database.GetHexData(gameID, i, "U1")));
					jsonResponse.put(String.valueOf(i) + "hex" + "U2", String.valueOf(Database.GetHexData(gameID, i, "U2")));
					jsonResponse.put(String.valueOf(i) + "hex" + "U3", String.valueOf(Database.GetHexData(gameID, i, "U3")));
					jsonResponse.put(String.valueOf(i) + "hex" + "Terrain", String.valueOf(Database.GetHexData(gameID, i, "Terrain")));
					jsonResponse.put(String.valueOf(i) + "hex" + "Structure", String.valueOf(Database.GetHexData(gameID, i, "Structure")));
					jsonResponse.put(String.valueOf(i) + "hex"+"Owner", String.valueOf(Database.GetHexData(gameID, i, "Owner")));
					jsonResponse.put(String.valueOf(i) + "hex"+"Resource", String.valueOf(Database.GetHexData(gameID, i, "Resource")));
					i++;
				}
			}
			else if(UserNumber == 1){
				while(i <= 8)
				{
					System.out.println("get hex data 2");
					jsonResponse.put(String.valueOf(lastHexIndex-i) + "hex" + "U1", String.valueOf(Database.GetHexData(gameID, i, "U1")));
					jsonResponse.put(String.valueOf(lastHexIndex-i) + "hex" + "U2", String.valueOf(Database.GetHexData(gameID, i, "U2")));
					jsonResponse.put(String.valueOf(lastHexIndex-i) + "hex" + "U3", String.valueOf(Database.GetHexData(gameID, i, "U3")));
					jsonResponse.put(String.valueOf(lastHexIndex-i) + "hex" + "Terrain", String.valueOf(Database.GetHexData(gameID, i, "Terrain")));
					jsonResponse.put(String.valueOf(lastHexIndex-i) + "hex" + "Structure", String.valueOf(Database.GetHexData(gameID, i, "Structure")));
					jsonResponse.put(String.valueOf(lastHexIndex-i) + "hex"+ "Owner", String.valueOf(Database.GetHexData(gameID, i, "Owner")));
					jsonResponse.put(String.valueOf(lastHexIndex-i) + "hex"+ "Resource", String.valueOf(Database.GetHexData(gameID, i, "Resource")));
					i++;
				}
			}
			
			String User1 = Database.GetUser1(gameID);
			String User2 = Database.GetUser2(gameID);
			
			jsonResponse.put(User1, "1");
			jsonResponse.put(User2, "2");
			
			i = 1;
			while(i<=2)
			{
				System.out.println("get user data");
				jsonResponse.put(String.valueOf(i) + "player"+"Income", String.valueOf(Database.GetPlayerData(gameID, i, "Income")));
				jsonResponse.put(String.valueOf(i) + "player"+"TCash", String.valueOf(Database.GetPlayerData(gameID, i, "TCash")));
				jsonResponse.put(String.valueOf(i) + "player"+"UnitTotal", String.valueOf(Database.GetPlayerData(gameID, i, "UnitTotal")));
				jsonResponse.put(String.valueOf(i) + "player"+"ResourceA", String.valueOf(Database.GetPlayerData(gameID, i, "ResourceA")));
				i++;
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("return");
		
		return jsonResponse;
	}
	public String moveUnits(HttpServletRequest request, WSDatabase Database)
	{
		
		String gameID = request.getParameter("gameID");
		gameID = gameID.replace("\n", "");
		String username = request.getParameter("username");
		String jsonString = request.getParameter("data");
		String fromHexNumber = null;
		String toHexNumber = null;
		String fromU1 = null;
		String fromU2 = null;
		String fromU3 = null;
		String toU1 = null;
		String toU2 = null;
		String toU3 = null;
		
		System.out.printf("%s, %s, %s\n",gameID, username, jsonString);
		
		try {
			JSONObject json = new JSONObject(jsonString);
			fromHexNumber = json.get("fromHexNumber").toString();
			toHexNumber = json.get("toHexNumber").toString();
			fromU1 = json.get(fromHexNumber + "hexU1").toString();
			fromU2 = json.get(fromHexNumber + "hexU2").toString();
			fromU3 = json.get(fromHexNumber + "hexU3").toString();
			toU1 = json.get(toHexNumber + "hexU1").toString();
			toU2 = json.get(toHexNumber + "hexU2").toString();
			toU3 = json.get(toHexNumber + "hexU3").toString();
			
			System.out.printf("%s, %s, %s\n", username, gameID, json.toString());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//should not have to set the owner.. but maybe later
		try {
			
			int UserNumber = 0;
			int opponentNumber = 0;
			
			String Users[] = Database.GetUserID(gameID);
			if(username.equals(Users[0])){
				UserNumber = 1;
				opponentNumber = 2;
			}
			else if(username.equals(username)){
				UserNumber = 2;
				opponentNumber = 1;
			}
			
			
			System.out.printf("%d, %s, %s\n", UserNumber, toHexNumber, fromHexNumber);
			
			//TODO add subtract froms and tos
			
			if(Database.GetHexData(gameID, Integer.valueOf(toHexNumber), "Owner") != opponentNumber)
			{
				
				if(UserNumber == 2){
					Database.SetHexData(gameID, Integer.valueOf(toHexNumber),"Owner", UserNumber);
					Database.SetHexData(gameID, Integer.valueOf(toHexNumber), "U1", Integer.valueOf(fromU1));
					Database.SetHexData(gameID, Integer.valueOf(toHexNumber), "U2", Integer.valueOf(fromU2));
					Database.SetHexData(gameID, Integer.valueOf(toHexNumber), "U3", Integer.valueOf(fromU3));
					Database.SetHexData(gameID, Integer.valueOf(fromHexNumber), "U1", 0);
					Database.SetHexData(gameID, Integer.valueOf(fromHexNumber), "U2", 0);
					Database.SetHexData(gameID, Integer.valueOf(fromHexNumber), "U3", 0);
				}
				else if(UserNumber == 1){
					Database.SetHexData(gameID, lastHexIndex - Integer.valueOf(toHexNumber),"Owner", UserNumber);
					Database.SetHexData(gameID, lastHexIndex - Integer.valueOf(toHexNumber), "U1", Integer.valueOf(fromU1));
					Database.SetHexData(gameID, lastHexIndex - Integer.valueOf(toHexNumber), "U2", Integer.valueOf(fromU2));
					Database.SetHexData(gameID, lastHexIndex - Integer.valueOf(toHexNumber), "U3", Integer.valueOf(fromU3));
					Database.SetHexData(gameID, lastHexIndex - Integer.valueOf(fromHexNumber), "U1", 0);
					Database.SetHexData(gameID, lastHexIndex - Integer.valueOf(fromHexNumber), "U2", 0);
					Database.SetHexData(gameID, lastHexIndex - Integer.valueOf(fromHexNumber), "U3", 0);
				}
			}
			else
			{
				return "opponent owns hex";
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "Units Moved";
		
	}
	public String buildUnits(HttpServletRequest request, WSDatabase Database)
	{
		
		String gameID = request.getParameter("gameID");
		gameID = gameID.replace("\n", "");
		String username = request.getParameter("username");
		String jsonString = request.getParameter("data");
		String hexNumber = null;
		String U1 = null;
		String U2 = null;
		String U3 = null;
		 
		
		System.out.printf("%s, %s, %s\n",gameID, username, jsonString);
		
		try {
			JSONObject json = new JSONObject(jsonString);
			hexNumber = json.get("hexNumber").toString();
			U1 = json.get(hexNumber + "hexU1").toString();
			U2 = json.get(hexNumber + "hexU2").toString();
			U3 = json.get(hexNumber + "hexU3").toString();
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//should not have to set the owner.. but maybe later
		try {
			
			int UserNumber = 0;
			
			String Users[] = Database.GetUserID(gameID);
			if(username.equals(Users[0])){
				UserNumber = 1;
			}
			else if(username.equals(username)){
				UserNumber = 2;
			}
			
			if(UserNumber == 2){
				Database.SetHexData(gameID, Integer.valueOf(hexNumber), "U1", Integer.valueOf(U1));
				Database.SetHexData(gameID, Integer.valueOf(hexNumber), "U2", Integer.valueOf(U2));
				Database.SetHexData(gameID, Integer.valueOf(hexNumber), "U3", Integer.valueOf(U3));
			}
			else if(UserNumber == 1){
				Database.SetHexData(gameID, lastHexIndex - Integer.valueOf(hexNumber), "U1", Integer.valueOf(U1));
				Database.SetHexData(gameID, lastHexIndex - Integer.valueOf(hexNumber), "U2", Integer.valueOf(U2));
				Database.SetHexData(gameID, lastHexIndex - Integer.valueOf(hexNumber), "U3", Integer.valueOf(U3));
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "Units Built";
		
	}
	
	public String leaveGame(HttpServletRequest request, WSDatabase Database){
		
		String username = request.getParameter("username");
		String gameID = null;
		
		try {
			gameID = Database.GetGameID(username);
			if(gameID != null){
				Database.DeleteGame(gameID);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "GameID still active";
		}
		
		return "Main Menu";
	}
	
	public String login(HttpServletRequest request, WSDatabase Database){
		//TODO insert user
        String username = request.getParameter("username");
        String password = request.getParameter("password");

		try {
			if(Database.ConfirmUserID(username) == null){
				return "Invalid Username";
			}
			//compare password to database password;
			else if(!Database.ConfirmUserID(username).equals(password)){
				return "Invalid Password";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Check UserName Failed";
		}
		
		String gameID = "0";
		//is user already in a game?
		try {
			gameID = Database.GetGameID(username);
			if(gameID == null){
				return "Game Options " + "0" ;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "Home " + gameID;
	}

	public String register(HttpServletRequest request, WSDatabase Database){
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if(password.length() < 8){
        	return "Password too short";
        }
        if(password.contains(" ")){
        	return "Invalid characters present in password";
        }
        if(username.contains(" ")){
        	return "No spaces in username";
        }
        
        //String password = request.getParameter("password");
        try {
        	if(Database.ConfirmUserID(username) != null){
        		return "Username already taken";
        	}
			Database.InsertUser(username, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error: could not insert user->" + username;
		}
		
		return "Home";
	}

	public String newGame(HttpServletRequest request, WSDatabase Database){
		
		String username = request.getParameter("username");
		String user1 = null;
		String user2 = null;
		
//		try {
//			user1 = Database.GetUserID(match)[0];
//		} catch (Exception e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//
//		//username is first in set gameID to match
//		if(user1 == null){
//			try {
//				Database.SetGameID(username, match);
//				return("Match Making");
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		
		int gameID = 1;
		String newID = null;
		
		try {
			while (Database.ConfirmGameID(Integer.toString(gameID))){
				gameID ++;
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return "failed to find gameID";
		}
		
		if(UserWaiting == null){
			UserWaiting = username;
			newID = "Match Making " + Integer.toString(gameID);
			return newID;
		}
		
		user1 = UserWaiting;
		UserWaiting = null;
		user2 = username;
		
		try {
			Database.InsertNewGame(Integer.toString(gameID), user1, user2);
			Database.SetHexData(Integer.toString(gameID), 1, "Owner", 1); //hex1
			Database.SetHexData(Integer.toString(gameID), 7, "Owner", 2); //hex7
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "failed to insert Game";
		}

		newID = "Game Created " + Integer.toString(gameID);

		return newID;
	}
	
	public String addMessage(HttpServletRequest request, WSDatabase Database){
		return "Home";
	}
	
	public String GetChatUpdate(HttpServletRequest request, WSDatabase Database){
		
		String username = request.getParameter("username");
		String gameID;
		Integer logNumber = 3;
		
		try {
			gameID = Database.GetGameID(request.getParameter("username"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return "no gameId";
		}
		
		//get the most recent chat log
		try {
			
			while(Database.GetChatlog(gameID, logNumber) == null && logNumber != 0){
				logNumber --;
			}
			if(logNumber == 0){
				return "no chat logs";
			}
			
			String chatMessage = Database.GetChatlog(gameID, logNumber);
			if(chatMessage.startsWith(username) == false){
				Database.SetChatLog(gameID, logNumber, null);
			}
			else{
				return "no chat logs";
			}
			return chatMessage;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "error";
	}
	
	public String ChatPost(HttpServletRequest request, WSDatabase Database){
		
		String username = request.getParameter("username");
		String gameID;
		Integer logNumber = 1;
		
		try {
			gameID = Database.GetGameID(request.getParameter("username"));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return "no gameId";
		}
		
		//get the most recent chat log
		try {
			
			while(Database.GetChatlog(gameID, logNumber) != null && logNumber != 4){
				logNumber ++;
			}
			if(logNumber == 4){
				return "Chat Log Full";
			}
			Database.SetChatLog(gameID, logNumber, username + ": " + request.getParameter("chatData"));
			return "Chat Sent";
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "error";	
	}
}
