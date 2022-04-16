package ItemFiles;

import java.util.ArrayList;

public class Inventory 
{
	public ArrayList<item> equippedItems;
	public ArrayList<item> heldItems;
	public int maxInventorySize;
	
	public Inventory()
	{
		maxInventorySize = 20;
		equippedItems = new ArrayList<> ();
		heldItems = new ArrayList<> ();
	}
	
	public static ArrayList<item?> generateMasterItemList()
	{
		ArrayList<item> returner = new ArrayList<>();
		returner.add(new nonEquipableItem(1, "health", 10, .1, 1, "Weak Health Potion"));
		returner.add(new nonEquipableItem(1, "strength", 10, .1, 1, "Weak Strength Potion"));
		returner.add(new nonEquipableItem(1, "intelligence", 10, .1, 1, "Weak Intelligence Potion"));
		returner.add(new nonEquipableItem(1, "agility", 10, .1, 1, "Weak Agility Potion"));
		returner.add(new equipableItem(1, "held", 0, 10, "melee", 2, "Rusty Sword"));
		returner.add(new equipableItem(1, "offhand", 10, 0, "none", 5, "Rusty Shield"));
		returner.add(new equipableItem(1, "head", 15, 0, "none", 1, "Initiate Helmet"));
		returner.add(new equipableItem(1, "chest", 25, 0, "none", 15, "Initiate Chestplate"));
		returner.add(new equipableItem(1, "arms", 10, 0, "none", 3, "Initiate Gauntlets"));
		returner.add(new equipableItem(1, "legs", 20, 0, "none", 10, "Initiate Greaves"));
		returner.add(new equipableItem(1, "held", 0, 10, "magic", .5, "Beginner Wand"));
		returner.add(new equipableItem(1, "offhand", 0, 10, "magic", .5, "Beginner Spellbook"));
		returner.add(new equipableItem(1, "head", 0, 5, "magic", 1, "Beginner Hat"));
		returner.add(new equipableItem(1, "chest", 5, 5, "magic", 15, "Beginner Robes"));
		returner.add(new equipableItem(1, "arms", 0, 5, "magic", 3, "Beginner Gloves"));
		returner.add(new equipableItem(1, "legs", 0, 5, "magic", 10, "Beginner Boots"));
		return returner;
	}
	
	public ArrayList<item> getEquippedItems()
	{
		return equippedItems;
	}
	
	public ArrayList<item> getHeldItems()
	{
		return heldItems;
	}
	
	public int getMaxInventorySize()
	{
		return maxInventorySize;
	}
}
