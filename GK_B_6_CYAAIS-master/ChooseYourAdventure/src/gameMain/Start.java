package gameMain;

/**
 * The Class Start.
 *
 * @author b3altena
 */

public class Start {

	/**
	 * The menu.
	 *
	 */
	static StartMenu menu;
	
	/**
	 * Instantiates a new start.
	 */
	public Start(){
		menu = new StartMenu();
		int done = 0;
		while(done == 0) { done = menu.getCheck(); }
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
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
	
	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername(){
		return menu.getUsername();
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName(){
		return menu.getName();
	}
	
	/**
	 * Gets the gender.
	 *
	 * @return the gender
	 */
	public int getGender(){
		return menu.getGenderID();
	}
	
	/**
	 * Gets the major.
	 *
	 * @return the major
	 */
	public int getMajor(){
		return menu.getMajorChoice();
	}
	
	/**
	 * Gets the club.
	 *
	 * @return the club
	 */
	public int getClub(){
		return menu.getClubChoice();
	}
	
	/**
	 * Gets the clothes.
	 *
	 * @return the clothes
	 */
	public int getClothes(){
		return menu.getClothingChoice();
	}
	
	/**
	 * Gets the laxt X.
	 *
	 * @return the laxt X
	 */
	public int getLaxtX(){
		return menu.getLastX();
	}
	
	/**
	 * Gets the last Y.
	 *
	 * @return the last Y
	 */
	public int getLastY(){
		return menu.getLastY();
	}
	
	/**
	 * Gets the spec.
	 *
	 * @return the spec
	 */
	public int getSpec() {
		return menu.getSpecChoice();
	}
	
	/**
	 * Gets the actv.
	 *
	 * @return the actv
	 */
	public int getActv(){
		return menu.getActv();
	}
	
	/**
	 * Checks if is admin.
	 *
	 * @return the int
	 */
	public int isAdmin(){
		return menu.getIsAdmin();
	}

}
