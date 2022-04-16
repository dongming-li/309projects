package itemfiles;

import com.example.mlscholl.wickedchambers.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * The Inventory is a data structure contained within every CharCreate object to detail the items that the character currently holds and has equipped. An inventory is made up of
 * arrays of Item objects, which can be either of type EquippableItem or NonEquippableItem. The equippedItems portion of the inventory contains any EquippableItems that the user not
 * only has in their inventory, but has decided their character is wearing (using the InventoryMenuFragment UI). A character can only have one item in each equippedItems slot. The
 * Inventory also consists of an array of heldItems, that represent the items (Equippable or NonEquippable) that the user is currently holding.
 */
public class Inventory 
{
	public Item[] equippedItems;
	public Item[] heldItems;
	public int maxInventorySize;
	private int iterator = 0;

	/**
	 * Constructor for an Inventory object. Creates a blank inventory that contains no items, held, equipped or otherwise.
	 */
	public Inventory()
	{
		ArrayList<Item> mList = generateMasterItemList();
		maxInventorySize = 20;
		equippedItems = new Item[6];
		for (int i = 0; i < equippedItems.length; i++)
		{
			equippedItems[i] = mList.get(0);
		}
		heldItems = new Item[20];
		for (int i = 0; i < heldItems.length; i++)
		{
			heldItems[i] = mList.get(0);
		}
		iterator = 0;
	}

	/**
	 * Creates and returns a master list of all items currently in the game. All items to be added to the game are to be added here, so that a finite number of items
	 * exists within the game, making many of the serialization and deserialization necessary for database management possible.
	 * @return Arraylist containing all of the currently implemented items within the game
	 */
	public static ArrayList<Item> generateMasterItemList()
	{
		ArrayList<Item> returner = new ArrayList<>();
		//TIER 1 ITEMS
		returner.add(new NonEquipableItem(1, "BLANKITEM", 1, 1, 1, "BLANKITEM", 0, R.drawable.blank_item));
		returner.add(new NonEquipableItem(1, "health", 10, .1, 1, "Weak Health Potion", 1, R.drawable.ruby));
		returner.add(new NonEquipableItem(1, "strength", 10, .1, 1, "Weak Strength Potion", 2, R.drawable.orange));
		returner.add(new NonEquipableItem(1, "intellect", 10, .1, 1, "Weak Intelligence Potion", 3, R.drawable.silver));
		returner.add(new NonEquipableItem(1, "agility", 10, .1, 1, "Weak Agility Potion", 4, R.drawable.yellow));
		returner.add(new EquipableItem(1, "hand", 0, 10, "melee", 2, "Rusty Sword", 5, R.drawable.rusty_sword));
		returner.add(new EquipableItem(1, "offhand", 10, 0, "melee", 5, "Rusty Shield", 6, R.drawable.rusty_shield));
		returner.add(new EquipableItem(1, "head", 15, 0, "melee", 1, "Initiate Helmet", 7, R.drawable.initiate_helmet));
		returner.add(new EquipableItem(1, "chest", 25, 0, "melee", 15, "Initiate Chestplate", 8, R.drawable.initiate_chestplate));
		returner.add(new EquipableItem(1, "arms", 10, 0, "melee", 3, "Initiate Gauntlets", 9, R.drawable.initiate_gauntlets));
		returner.add(new EquipableItem(1, "legs", 20, 0, "melee", 10, "Initiate Boots", 10, R.drawable.initiate_boots));
		returner.add(new EquipableItem(1, "hand", 0, 10, "magic", .5, "Beginner Wand", 11, R.drawable.beginner_wand));
		returner.add(new EquipableItem(1, "offhand", 0, 10, "magic", .5, "Beginner Spellbook", 12, R.drawable.beginner_spellbook));
		returner.add(new EquipableItem(1, "head", 0, 5, "magic", 1, "Beginner Hat", 13, R.drawable.beginner_hat));
		returner.add(new EquipableItem(1, "chest", 5, 5, "magic", 15, "Beginner Robes", 14, R.drawable.beginner_robe));
		returner.add(new EquipableItem(1, "arms", 0, 5, "magic", 3, "Beginner Gloves", 15, R.drawable.beginner_gloves));
		returner.add(new EquipableItem(1, "legs", 0, 5, "magic", 10, "Beginner Boots", 16, R.drawable.beginner_boots));
		returner.add(new EquipableItem(1, "hand", 0, 8, "stealth", .5, "Dagger", 17, R.drawable.dagger));
		returner.add(new EquipableItem(1, "offhand", 0, 8, "stealth", .5, "Offhand Dagger", 18, R.drawable.offhand_dagger));
		returner.add(new EquipableItem(1, "head", 0, 5, "stealth", 1, "Leather Cloak", 19, R.drawable.leather_cloak));
		returner.add(new EquipableItem(1, "chest", 5, 5, "stealth", 15, "Leather Armor", 20, R.drawable.leather_armor));
		returner.add(new EquipableItem(1, "arms", 0, 5, "stealth", 3, "Leather Gloves", 21, R.drawable.leather_gloves));
		returner.add(new EquipableItem(1, "legs", 0, 5, "stealth", 10, "Leather Boots", 22, R.drawable.leather_boots));
		//TIER 2 ITEMS
		returner.add(new EquipableItem(3, "hand", 0, 15, "melee", 2, "Steel Sword", 23, R.drawable.steel_sword));
		returner.add(new EquipableItem(3, "offhand", 20, 0, "melee", 5, "Hardy Shield", 24, R.drawable.hardy_shield));
		returner.add(new EquipableItem(3, "head", 20, 0, "melee", 1, "Steel Helmet", 25, R.drawable.steel_helmet));
		returner.add(new EquipableItem(3, "chest", 35, 0, "melee", 15, "Steel Chestplate", 26, R.drawable.steel_chestplate));
		returner.add(new EquipableItem(3, "arms", 25, 0, "melee", 3, "Steel Gauntlets", 27, R.drawable.steel_gauntlets));
		returner.add(new EquipableItem(3, "legs", 25, 0, "melee", 10, "Hardened Greaves", 28, R.drawable.hardened_greaves));
		returner.add(new EquipableItem(3, "hand", 0, 20, "magic", .5, "Apprentice Wand", 29, R.drawable.apprentice_staff));
		returner.add(new EquipableItem(3, "offhand", 0, 20, "magic", .5, "Apprentice Spellbook", 30, R.drawable.apprentice_spellbook));
		returner.add(new EquipableItem(3, "head", 5, 10, "magic", 1, "Apprentice Hat", 31, R.drawable.apprentice_hat));
		returner.add(new EquipableItem(3, "chest", 10, 10, "magic", 15, "Apprentice Robes", 32, R.drawable.apprentice_robes));
		returner.add(new EquipableItem(3, "arms", 5, 10, "magic", 3, "Apprentice Gloves", 33, R.drawable.apprentice_gloves));
		returner.add(new EquipableItem(3, "legs", 5, 10, "magic", 10, "Apprentice Boots", 34, R.drawable.apprentice_boots));
		returner.add(new EquipableItem(5, "hand", 0, 12, "stealth", .5, "Sharpened Dagger", 35, R.drawable.sharpened_dagger));
		returner.add(new EquipableItem(5, "offhand", 0, 10, "stealth", .5, "Sharpened Offhand Dagger", 36, R.drawable.sharpened_dagger));
		returner.add(new EquipableItem(5, "head", 0, 10, "stealth", 1, "Hardened Leather Cloak", 37, R.drawable.scaled_cloak));
		returner.add(new EquipableItem(5, "chest", 5, 10, "stealth", 15, "Hardened Leather Armor", 38, R.drawable.hardened_leather_armor));
		returner.add(new EquipableItem(5, "arms", 5, 10, "stealth", 3, "Hardened Leather Gloves", 39, R.drawable.hardened_leather_gloves));
		returner.add(new EquipableItem(5, "legs", 5, 10, "stealth", 10, "Hardened Leather Boots", 40, R.drawable.hardened_leather_boots));
		//TIER 3 ITEMS
		returner.add(new EquipableItem(5, "hand", 0, 30, "melee", 2, "Steel Sword", 41, R.drawable.powerful_greatsword));
		returner.add(new EquipableItem(5, "offhand", 40, 0, "melee", 5, "Hardy Shield", 42, R.drawable.soldiers_shield));
		returner.add(new EquipableItem(5, "head", 35, 0, "melee", 1, "Steel Helmet", 43, R.drawable.soldiers_helmet));
		returner.add(new EquipableItem(5, "chest", 50, 0, "melee", 15, "Steel Chestplate", 44, R.drawable.soldiers_chestplate));
		returner.add(new EquipableItem(5, "arms", 35, 0, "melee", 3, "Steel Gauntlets", 45, R.drawable.soldiers_gauntlets));
		returner.add(new EquipableItem(5, "legs", 35, 0, "melee", 10, "Hardened Greaves", 46, R.drawable.soldiers_greaves));
		returner.add(new EquipableItem(5, "hand", 0, 30, "magic", .5, "Apprentice Wand", 47, R.drawable.experts_staff));
		returner.add(new EquipableItem(5, "offhand", 0, 30, "magic", .5, "Apprentice Spellbook", 48, R.drawable.experts_spellbook));
		returner.add(new EquipableItem(5, "head", 10, 20, "magic", 1, "Apprentice Hat", 49, R.drawable.experts_hat));
		returner.add(new EquipableItem(5, "chest", 10, 20, "magic", 15, "Apprentice Robes", 50, R.drawable.experts_robes));
		returner.add(new EquipableItem(5, "arms", 10, 20, "magic", 3, "Apprentice Gloves", 51, R.drawable.experts_gloves));
		returner.add(new EquipableItem(5, "legs", 10, 20, "magic", 10, "Apprentice Boots", 52, R.drawable.experts_boots));
		returner.add(new EquipableItem(5, "hand", 0, 18, "stealth", .5, "Sharpened Dagger", 53, R.drawable.falchion));
		returner.add(new EquipableItem(5, "offhand", 0, 15, "stealth", .5, "Sharpened Offhand Dagger", 54, R.drawable.offhand_falchion));
		returner.add(new EquipableItem(5, "head", 5, 15, "stealth", 1, "Hardened Leather Cloak", 55, R.drawable.dragonleather_cloak));
		returner.add(new EquipableItem(5, "chest", 10, 15, "stealth", 15, "Hardened Leather Armor", 56, R.drawable.dragonleather_armor));
		returner.add(new EquipableItem(5, "arms", 10, 15, "stealth", 3, "Hardened Leather Gloves", 57, R.drawable.dragonleather_gloves));
		returner.add(new EquipableItem(5, "legs", 10, 15, "stealth", 10, "Hardened Leather Boots", 58, R.drawable.dragonleather_boots));
		return returner;
	}

	/**
	 * @return an Array representing the items currently equipped by a character
	 */
	public Item[] getEquippedItems()
	{
		return equippedItems;
	}

	/**
	 * Generates and gives a random item to an inventory. This is to be used when a game has ended to distribute rewards to the players.
	 */
	public void receiveRandomItem()
	{
		Random randy = new Random();
		int toGet = randy.nextInt(58) + 1;
		Item toGive = generateMasterItemList().get(toGet);
		give(toGive);
	}

	/**
	 * @return an Array representing the items currently held but not equipped by the player
	 */
	public Item[] getHeldItems()
	{
		return heldItems;
	}

	/**
	 * @return returns the maximum size of the current heldItems portion of the inventory.
	 */
	public int getMaxInventorySize()
	{
		return maxInventorySize;
	}

	/**
	 * Places an item in the helditems portion of the inventory, if the character has space to hold it.
	 * @param item item to be given
	 * @return whether or not the item was succesfully placed in the inventory
	 */
	public boolean give(Item item)
	{
		//space exists in inventory
		if (iterator <= maxInventorySize)
		{
			heldItems[iterator] = item;
			iterator++;
			return true;
		}
		return false;
	}

	/**
	 * Returns an item from the master list depending on whether or not that item exists within it. Useful for testing to see if an item has been succesfully created from the
	 * master list or illegally created elsewhere in code.
	 * @param id Identification number with which to find the item
	 * @return Item that was found (null if not found)
	 */
	public static Item retrieveFromId(int id)
	{
		ArrayList<Item> mList = generateMasterItemList();
		for (int i = 0; i < mList.size(); i++)
		{
			if (mList.get(i).getItemId() == id)
			{
				return mList.get(i);
			}
		}
		return null;
	}

	/**
	 * Removes an item from the given position in the inventory
	 * @param position position of item to be removed
	 * @return whether or not the item was removed
	 */
	public boolean remove(int position)
	{
		//position desired is illogical
		if (position >= iterator)
			return false;
		for (int i = position + 1; i < maxInventorySize; i++)
		{
			//swap with next space, regardless of null
			heldItems[position - 1] = heldItems[position];
		}
		return true;
	}

	/**
	 * Converts the given inventory into a String object for the purposes of database management. Due to the fact that only items generated by generateMasterItemList() above
	 * exist within the game, this method is able to reliable convert items into their corresponding itemId. Items are then separated by an 'i', so that they can later be
	 * deserialized. The format of the string created is as follows:
	 *
	 * sixhelditems i twenty held items i
	 * @return
	 */
	public String serialize()
	{
		String output = "";
		for (int i = 0; i < equippedItems.length; i++)
		{
			if (equippedItems[i].getItemId() == 0)
			{
				output += "0";
			}
			else
			{
				output += equippedItems[i].getItemId();
			}
			output += "i";
		}
		for (int i = 0; i < heldItems.length; i++)
		{
			if (heldItems[i].getItemId() == 0)
			{
				output += "0";
			}
			else
			{
				output += heldItems[i].getItemId();
			}
			output += "i";
		}
		return output.substring(0, output.length() - 1);
	}

	/**
	 * Creates an inventory object based on string input. Is used in tandem with the serialize() method to effectively manage database values and convert these into complex
	 * datatypes upon being puled from the database.
	 * @param input String representing the inventory to be deserialized
	 * @return Inventory created from String input
	 */
	public static Inventory deserialize(String input)
	{
		Inventory toReturn = new Inventory();
		String[] data = input.split("i");
		for (int i = 0; i < toReturn.equippedItems.length; i++)
		{
			if (!data[i].equals("0"))
			{
				toReturn.equippedItems[i] = retrieveFromId(Integer.parseInt(data[i]));
			}
		}
		for (int i = 0; i < toReturn.heldItems.length; i++)
		{
			if (!data[i + 6].equals("0"))
			{
				toReturn.heldItems[i] = retrieveFromId(Integer.parseInt(data[i + 6]));
			}
		}
		return toReturn;
	}

}
