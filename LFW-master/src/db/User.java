package db;

import java.util.Date;

import javax.persistence.*;
/**
 * mapped to User table in the database
 * @author Huiye Lin
 */



@Entity  
@Table(name = "User")  

@Inheritance(strategy = InheritanceType.JOINED) 


public class User {
	@Id    
	
	//Need to change column name according to your SQL table
	@Column(name = "Username")  
	private String Username;
	@Column(name = "Password")  
	private String Password;
	@Column(name = "Role")  
	private String Role;
	@Column(name = "Win")
	private int Win;
	@Column(name = "Lose")
	private int Lose;
	
	public User(){
		
	}
	
	public User(String Username, String Password, String Role, int win, int lose){
		this.Username = Username;
		this.Password = Password;
		this.Role = Role;
		this.Win = win;
		this.Lose = lose;
	}
	/**
	 * get username from the user object
	 * @return username
	 */
	public String getUsername(){
		return this.Username;
	}
	/**
	 * get password from the user object
	 * @return password
	 */
	public String getPassword(){
		return this.Password;
	}
	/**
	 * get number of wins from the user object
	 * @return win
	 */
	public int getWin(){
		return this.Win;
	}
	/**
	 * get number of loses from the user object
	 * @return lose
	 */
	public int getLost(){
		return this.Lose;
	}
	/**
	 * get role from the user object
	 * @return Role
	 */
	public String getRole(){
		return this.Role;
	}
	/**
	 * set password of the user object
	 * @param pass new password to set
	 */
	public void setPassword(String pass){
		this.Password = pass;
	}
	/**
	 * set number of win of the user object
	 * @param win new number of wins
	 */
	public void setWin(int win){
		this.Win = win;
	}
	/**
	 * set number of loses of the user object
	 * @param lose new number of loses
	 */
	public void setLose(int lose){
		this.Win = lose;
	}
	/**
	 * set role of the user object
	 * @param role new role to set
	 */
	public void setRole(String role){
		this.Role = role;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
