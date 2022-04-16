package db;

import java.awt.Dimension;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * hibernate application for the database use
 * @author Huiye Lin
 */
public class DB {
	
	private static Session session;
	
	public static void main(String[] args){
		DB test = new DB();
		System.out.println(test.getGame("alec").getPosition());
		test.deleteDB();
	}
	
	public DB(){
		SessionFactory sessionFactory = null;
		
		//Configure setting
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		try
		{
			Configuration configuration = new Configuration();
			sessionFactory = configuration.configure().buildSessionFactory(registry);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			StandardServiceRegistryBuilder.destroy(registry);
		}
		 session = sessionFactory.openSession();
		 session.getTransaction();
	}
	
	public void deleteDB(){
		session.close();
	}
	
	
	//the User table
	/**
	 * get an User object so that you can use functions in User object
	 * @param Username username we want to search
	 * @return corresponding User object 
	 */
	@SuppressWarnings({ "unchecked" })
	public User getUser(String Username){
		 
		 StringBuilder sb = new StringBuilder();
		 sb.append("select u from User u where Username ='" + Username + "'");
		 String item = sb.toString();
		 Query query = session.createQuery(item);
		 List<User> user = query.list();
		 return user.get(0);
		
	}

	/**
	 * update the user record in the User table
	 * @param Username username we want to update
	 * @param attri attribute name we want to update
	 * @param attrinew new attribute content we want to update
	 */
	@SuppressWarnings({ "unchecked" })
	public void updateUser(String Username,String attri,String attrinew){
		 
		
		 StringBuilder sb = new StringBuilder();
		 sb.append("UPDATE User set " + attri + " = '" + attrinew + 
				 "' where Username = '" + Username + "'");
		 String item = sb.toString();
		 Query query = session.createQuery(item);
		 query.executeUpdate();
		 
		
	}
	/**
	 * update the number of wins for the user record in the User table
	 * @param Username username we want to update
	 */
	@SuppressWarnings({ "unchecked" })
	public void updateWin(String Username){
		 
		 StringBuilder sb = new StringBuilder();
		 sb.append("UPDATE User set Win = Win + 1" + 
				 " where Username = '" + Username + "'");
		 String item = sb.toString();
		 Query query = session.createQuery(item);
		 query.executeUpdate();
		
		
	}
	/**
	 * update the number of loses for the user record in the User table
	 * @param Username username we want to update
	 */
	@SuppressWarnings({ "unchecked" })
	public void updateLose(String Username){
		
		
		 StringBuilder sb = new StringBuilder();
		 sb.append("UPDATE User set Lose = Lose + 1" + 
				 " where Username = '" + Username + "'");
		 String item = sb.toString();
		 Query query = session.createQuery(item);
		 query.executeUpdate();
		
	}
	/**
	 * delete the user record in the User table
	 * @param Username username we want to delete
	 */
	public void deleteUser(String Username){
		
		 StringBuilder sb = new StringBuilder();
		 sb.append("DELETE from User where Username = '" + Username + "'");
		 String item = sb.toString();
		 Query query = session.createQuery(item);
		 query.executeUpdate();
		 
	}
	/**
	 * add the user record in the User table
	 * @param toAdd user object to add
	 */
	public void addUser(User toAdd){
		
		session.save(toAdd);
		session.beginTransaction().commit();
		
		
	}
	
	
	
	
	
	//the GameState table
	/**
	 * get a GameState object so that you can use functions in GameState object
	 * @param GameID gameid of the GameState we want to search
	 * @return corresponding GameState object 
	 */
	@SuppressWarnings({ "unchecked" })
	public Game getGameState(int GameID){
		
		 StringBuilder sb = new StringBuilder();
		 sb.append("select g from GameState g where GameID =" + GameID);
		 String item = sb.toString();
		 Query query = session.createQuery(item);
		 List<Game> gamestate = query.list();
		
		 return gamestate.get(0);
		
	}
	/**
	 * update the night for the GameState record in the GameState table
	 * @param GameID gameid of the GameState we want to update
	 * @param Night new night to update
	 */
	@SuppressWarnings({ "unchecked" })
	public void updateNight(int GameID, int Night){
		
		 
		 StringBuilder sb = new StringBuilder();
		 sb.append("UPDATE GameState set Night =" + Night +  
				 " where GameID = "+GameID);
		 String item = sb.toString();
		 Query query = session.createQuery(item);
		 query.executeUpdate();
	
	}
	/**
	 * update the turn for the GameState record in the GameState table
	 * @param GameID gameid of the GameState we want to update
	 * @param turn new turn to update
	 */
	@SuppressWarnings({ "unchecked" })
	public void updateTurn(int GameID, String turn){
		 
		 StringBuilder sb = new StringBuilder();
		 sb.append("UPDATE GameState set Turn ='" +turn+ "' where GameID = " + GameID);
		 String item = sb.toString();
		 Query query = session.createQuery(item);
		 query.executeUpdate();
		 
	}
	/**
	 * delete the GameState record in the GameState table
	 * @param GameID gameid of the GameState we want to delete
	 */
	@SuppressWarnings({ "unchecked" })
	public void deleteGameState(int GameID){
		
		 StringBuilder sb = new StringBuilder();
		 sb.append("DELETE from GameState where GameID = " + GameID);
		 String item = sb.toString();
		 Query query = session.createQuery(item);
		 query.executeUpdate();
		
	}
	/**
	 * add the GameState record in the GameState table
	 * @param toAdd GameState we want to add
	 */
	@SuppressWarnings({ "unchecked" })
	public static void addGameState(GameState toAdd){
		session.save(toAdd);
		session.beginTransaction().commit();

}
	
	//the Game table
	/**
	 * get a Game object so that you can use functions in Game object
	 * @param Username username of the Game we want to search
	 * @return corresponding Game object 
	 */
	@SuppressWarnings({ "unchecked" })
	public Game getGame(String Username){
		
		 StringBuilder sb = new StringBuilder();
		 sb.append("select g from Game g where Username ='" + Username + "'");
		 String item = sb.toString();
		 Query query = session.createQuery(item);
		 List<Game> game = query.list();
		
		 return game.get(0);
		
	}
	
	public String getUserFromGameIDAndColor(int GameID, String color)
	{
		 StringBuilder sb = new StringBuilder();
		 sb.append("select g from Game g where GameID ='" + GameID + "' and Color='" + color + "'");
		 String item = sb.toString();
		 Query query = session.createQuery(item);
		 List<Game> game = query.list();
		
		 return game.get(0).getUsername();
	}
	
	public String getJackFromGameID(int GameID)
	{
		StringBuilder sb = new StringBuilder();
		 sb.append("select g from Game g where GameID ='" + GameID + "' and Role='jack'");
		 String item = sb.toString();
		 Query query = session.createQuery(item);
		 List<Game> game = query.list();
		
		 return game.get(0).getUsername();
	}
	
	/**
	 * Gets a list of Games using the GameID
	 * @param GameID
	 * @return list of user games with matching IDs
	 */
	@SuppressWarnings({ "unchecked" })
	public List<Game> getGameFromID(int GameID){
		
		 StringBuilder sb = new StringBuilder();
		 sb.append("select * from Game where GameID =" + GameID);
		 String item = sb.toString();
		 Query query = session.createQuery(item);
		 return query.list();
	}
	
	/**
	 * update the position for the Game record in the Game table
	 * @param Username username of the Game we want to update
	 * @param position new position to update
	 */
	@SuppressWarnings({ "unchecked" })
	public void updatePosition(String Username,String position){
		
		 
		 StringBuilder sb = new StringBuilder();
		 sb.append("UPDATE Game set Position ='" + position +  
				 "' where Username = '" + Username + "'");
		 String item = sb.toString();
		 Query query = session.createQuery(item);
		 query.executeUpdate();
	
	}
	/**
	 * update the move for the Game record in the Game table
	 * @param Username username of the Game we want to update
	 */
	@SuppressWarnings({ "unchecked" })
	public void updateMove(String Username){
		 
		 StringBuilder sb = new StringBuilder();
		 sb.append("UPDATE Game set Move = Move + 1 where Username = '" + Username + "'");
		 String item = sb.toString();
		 Query query = session.createQuery(item);
		 query.executeUpdate();
		 
	}
	/**
	 * delete the move for the Game record in the Game table
	 * @param GameID of the Game we want to delete
	 */
	@SuppressWarnings({ "unchecked" })
	public void deleteGame(int GameID){
		
		 StringBuilder sb = new StringBuilder();
		 sb.append("DELETE from Game where GameID = " + GameID);
		 String item = sb.toString();
		 Query query = session.createQuery(item);
		 query.executeUpdate();
		
	}
	/**
	 * add the Game record in the Game table
	 * @param toAdd Game object we want to add
	 */
	@SuppressWarnings({ "unchecked" })
	public static void addGame(Game toAdd){
		session.save(toAdd);
		session.beginTransaction().commit();
		}
	
	
	/**
	 * Gets a lobby based on the name of it
	 * @param LobbyName name of lobby wanted to be found
	 * @return a lobby object
	 */
	@SuppressWarnings({ "unchecked" })
	public Lobby getLobby(String LobbyName){
		 StringBuilder sb = new StringBuilder();
		 sb.append("select l from Lobby l where LobbyName ='" + LobbyName + "'");
		 String item = sb.toString();
		 Query query = session.createQuery(item);
		 List<Lobby> lobby = query.list();
		 return lobby.get(0);
	}
	
	/**
	 * Searches for lobbies meeting the criteria given
	 * @param Timer timer of how long player has to move
	 * @param isPrivate whether the lobby is public or private
	 * @return a list of lobbies meeting the criteria
	 */
	@SuppressWarnings({"unchecked"})
	public List<Lobby> searchLobby(int Timer, boolean isPrivate)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("select * from Lobby where Timer=" + Timer + " and Private=" + true);
		String item = sb.toString();
		Query query = session.createQuery(item);
		return query.list();
	}

}
