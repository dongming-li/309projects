package db;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;


@Entity  
@Table(name = "Game")  

@Inheritance(strategy = InheritanceType.JOINED) 


public class Lobby {
@Id

		//Need to change column name according to your SQL table
		@Column(name = "LobbyName")  
		private String LobbyName;
		@Column(name = "GameID")  
		private int GameID;
		@Column(name = "Private")  
		private boolean isPrivate;
		@Column(name = "Timer")
		private int Timer;
		@Column(name = "ToolTip")
		private boolean isToolTip;

		
		public Lobby(){
			
		}

		public Lobby(String LobbyName, int GameID, boolean isPrivate, int Timer, boolean isToolTip){
			this.LobbyName = LobbyName;
			this.GameID = GameID;
			this.isPrivate = isPrivate;
			this.Timer = Timer;
			this.isToolTip = isToolTip;
		}
		
		public int getGameID()
		{
			return GameID;
		}
		
		public String getLobbyName()
		{
			return LobbyName;
		}
		
}
