package characters;

import android.graphics.Canvas;

import itemfiles.*;
/*
* This interface details the basic information necessary to create a character interface. This class will be useful for creating future classes that require the functionality that the
* Character interface details.
*
*
 */
public interface Character {

	void draw(Canvas canvas);

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
        boolean equipItem(Item item, int slot, int gridSlot);
        
        boolean unEquipItem(int slot);
}
