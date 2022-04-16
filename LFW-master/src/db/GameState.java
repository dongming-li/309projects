package db;

import java.util.Date;

import javax.persistence.*;
/**
 * mapped to GameStete table in the database
 * @author Huiye Lin
 */



@Entity  
@Table(name = "GameState")  

@Inheritance(strategy = InheritanceType.JOINED) 


public class GameState {
	@Id    
	
	//Need to change column name according to your SQL table
	@Column(name = "GameID")  
	private int GameID;
	@Column(name = "Night")  
	private int Night;
	@Column(name = "Turn")
	private String Turn;
	@Column(name = "CarriageActivated")
	private boolean isCarriageActivated;
	@Column(name = "StreetLampActivated")
	private boolean isStreetLampActivated;
	@Column(name = "Done")
	private boolean isDone;
	
	public GameState(){
		
	}
	
	public GameState(int gameID, int night, String turn, boolean isCarriageActivated, boolean isStreetLampActivated, boolean isDone){
		this.GameID = gameID;
		this.Night = night;
		this.Turn = turn;
		this.isCarriageActivated = isCarriageActivated;
		this.isStreetLampActivated = isStreetLampActivated;
		this.isDone = isDone;
	}
	
	/**
	 * get GameID from the GameState object
	 * @return GameID
	 */
	public int getGameID(){
		return this.GameID;
	}
	/**
	 * get night from the GameState object
	 * @return Night
	 */
	public int getNight(){
		return this.Night;
	}
	/**
	 * get turn from the GameState object
	 * @return turn of user to make a move
	 */
	public String getTurn(){
		return this.getTurn();
	}
	/**
	 * set GameID from the GameState object
	 * @param gameID new GameID
	 */
	public void setGameID(int gameID){
		this.GameID = gameID;
	}
	/**
	 * set night from the GameState object
	 * @param night new Night
	 */
	public void setNight(int night){
		this.Night = night;
	}
	/**
	 * set turn from the GameState object
	 * @param turn new turn
	 */
	public void setTurn(String turn){
		this.Turn = turn;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
