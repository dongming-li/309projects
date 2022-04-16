package db;

import java.util.Date;

import javax.persistence.*;
/**
 * mapped to Game table in the database
 * @author Huiye Lin
 */



@Entity  
@Table(name = "Game")  

@Inheritance(strategy = InheritanceType.JOINED) 


public class Game {
	@Id    
	
	//Need to change column name according to your SQL table
	@Column(name = "Username")  
	private String Username;
	@Column(name = "Role")  
	private String Role;
	@Column(name = "Move")
	private int Move;
	@Column(name = "Position")  
	private String Position;
	
	@Column(name = "GameID")
	private int GameID;
	@Column(name = "Color")
	private String Color;
	
	public Game(){
		
	}
	
	public Game(String Username, String Role, String Position, int Move, int GameID){
		this.Username = Username;
		this.Role = Role;
		this.Position = Position;
		this.Move =Move;
		this.GameID = GameID;
	}
	/**
	 * get username from the Game object
	 * @return username
	 */
	public String getUsername(){
		return this.Username;
	}
	/**
	 * get role from the Game object
	 * @return role
	 */
	public String getRole(){
		return this.Role;
	}
	/**
	 * get position from the Game object
	 * @return position
	 */
	public String getPosition(){
		return this.Position;
	}
	/**
	 * get move from the Game object
	 * @return move
	 */
	public int getMove(){
		return this.Move;
	}
	/**
	 * get gameid from the Game object
	 * @return gameid
	 */
	public int getGameID(){
		return this.GameID;
	}
	/**
	 * set role from the Game object
	 * @param Role new role
	 */
	public void setRole(String Role){
		this.Role = Role;
	}
	/**
	 * set position from the Game object
	 * @param position new position
	 */
	public void setPosition(String position){
		this.Position = position;
	}
	/**
	 * set move from the Game object
	 * @param Move new move
	 */
	public void setMove(int Move){
		this.Move = Move;
	}
	/**
	 * set gameid from the Game object
	 * @param gameID new gameid
	 */
	public void setGameID(int gameID){
		this.GameID = gameID;
	}

	public String getColor() {
		return Color;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
