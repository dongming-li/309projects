package proj;

import ItemFiles.*;

public interface Character {

	String getType();
	
	int getHealth();
	
	int getIntellect();
	
	int getStrength();
	
	int getAgility();
	
	int getCharLevel();
	
	int getCharXP();
	
	void addHealth(int amount);
	
	void addIntellect(int amount);
	
	void addStrength(int amount);
	
	void addAgility(int amount);
	
	void addCharLevel(int amount);
	
	void addCharXP(int amount);
	
	boolean isAlive();
        
        //will need to take an item object instead of name string
        boolean equipItem(item item, int slot);
        
        //will need to return an item object
        boolean returnItem(int slot);
        
        boolean unEquipItem(int slot);
}
