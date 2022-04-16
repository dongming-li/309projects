package main;

/**
 * @author b3altena
 *
 */

public class Start {

	/**
	 * @param args
	 */
	static StartMenu menu;
	
	public Start(){
		menu = new StartMenu();
		int done = 0;
		while(done == 0) { done = menu.getCheck(); }
	}
	
	public static void main(String[] args) {
		menu = new StartMenu();
		int done = 0;
		while(done == 0) { done = menu.getCheck(); }
		

		System.out.println("Username: " + menu.getUsername());
		System.out.println("Password: " + menu.getPassword());
		System.out.println("Name: " + menu.getName());
		System.out.println("GenderID: " + menu.getGenderID());
		System.out.println("Major Choice: " + menu.getMajorChoice());
		System.out.println("Club Choice: " + menu.getClubChoice());
		System.out.println("Clothing Choice: " + menu.getClothingChoice());
		System.out.println("Last X: " + menu.getLastX());
		System.out.println("Last Y: " + menu.getLastY());
		System.out.println("isActv: " + menu.getActv());
		
		if(menu.getSpecChoice() != -1)
			System.out.println("Spectator Choice: " + menu.getSpecChoice());
		
	}
	public String getUsername(){
		return menu.getUsername();
	}
	public String getName(){
		return menu.getName();
	}
	public int getGender(){
		return menu.getGenderID();
	}
	public int getMajor(){
		return menu.getMajorChoice();
	}
	public int getClub(){
		return menu.getClubChoice();
	}
	public int getClothes(){
		return menu.getClothingChoice();
	}
	public int getLaxtX(){
		return menu.getLastX();
	}
	public int getLastY(){
		return menu.getLastY();
	}
	public int getActv(){
		return menu.getActv();
	}

}
