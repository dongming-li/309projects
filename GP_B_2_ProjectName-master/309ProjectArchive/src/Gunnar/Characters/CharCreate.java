package proj;

import java.util.concurrent.ThreadLocalRandom;
import ItemFiles.Inventory;
import ItemFiles.item;
import ItemFiles.equipableItem;
import ItemFiles.nonEquipableItem;

import SkillFiles.BinarySkillTree;
import SkillFiles.Skill;

public class CharCreate implements Character{

	public boolean isWarr = false;
	public boolean isMage = false;
	public boolean isRogue = false;
	public int health = 0;
	public int intellect = 0;
	public int strength = 0;
	public int agility = 0;
	public int charLevel = 0;
	public int charXP = 0;
	public boolean isAlive = true;
	public int damage = 0;
	public int mainStatScale = 5; //determines the scaling of str/agi/int
	public BinarySkillTree skillTree;
	public Inventory inventory;
	
	public CharCreate(char cl){
		if(cl == 'w'){
			isWarr = true;
			health = 5;
			strength = 5;
			intellect = 0;
			agility = 0;
			charLevel = 1;
			charXP = 0;
			isAlive = true;
			skillTree = Skill.generateWarriorSkillTree(); //Generate a default skill tree of warrior skills
			inventory = new Inventory();
		}
		else if(cl == 'm'){
			isMage = true;
			health = 5;
			strength = 0;
			intellect = 5;
			agility = 0;
			charLevel = 1;
			charXP = 0;
			isAlive = true;
			skillTree = Skill.generateMageSkillTree(); //Generate a default skill tree of Mage skills
			inventory = new Inventory();
		}
		else{
			isRogue = true;
			health = 5;
			strength = 0;
			intellect = 0;
			agility = 5;
			charLevel = 1;
			charXP = 0;
			isAlive = true;
			skillTree = Skill.generateRogueSkillTree(); //Generate a default skill tree of Rogue skills
			inventory = new Inventory();
		}
	}
	
	@Override
	public String getType() {
		if(isWarr == true){
			return "Warrior";
		}
		else if(isMage == true){
			return "Mage";
		}
		else{
			return "Rogue";
		}
	}
	
	@Override
	public int getHealth() {
		return health;
	}

	@Override
	public int getIntellect() {
		return intellect;
	}

	@Override
	public int getStrength() {
		return strength;
	}

	@Override
	public int getAgility() {
		return agility;
	}

	@Override
	public void addHealth(int amount) {
		health = health + amount;
		if(health <= 0){
			health = 0;
			isAlive = false;
		}
	}

	@Override
	public void addIntellect(int amount) {
		intellect = intellect + amount;
		if(intellect <= 0){
			intellect = 0;
		}
	}

	@Override
	public void addStrength(int amount) {
		strength = strength + amount;
		if(strength <= 0){
			strength = 0;
		}
	}

	@Override
	public void addAgility(int amount) {
		agility = agility + amount;
		if(agility <= 0){
			agility = 0;
		}
	}

	@Override
	public boolean isAlive() {
		return isAlive;
	}

	@Override
	public int getCharLevel() {
		return charLevel;
	}

	@Override
	public int getCharXP() {
		return charXP;
	}

	@Override
	public void addCharLevel(int amount) {
		charLevel = charLevel + amount;
	}

	@Override
	public void addCharXP(int amount) {
		int temp = charLevel*100;
		charXP = charXP + amount;
		while(charXP >=temp){
			charLevel++;
			charXP = charXP - temp;
			temp = charLevel*100;
		}
		
	}
	
	public int attack(){
            String charClass = getType();
            int baseDmg = ThreadLocalRandom.current().nextInt(8, 12);
            switch(charClass) {
                case "Warrior":
                    return baseDmg + (strength/mainStatScale);
                case "Mage":
                    return baseDmg + (intellect/mainStatScale);
                case "Rogue":
                    return baseDmg + (agility/mainStatScale);
                default:
                    return 0;
            }
	}
	
	public void useAbility(){
		if(isWarr == true){
			health = health + strength;
		}
		else if(isMage == true){
			damage = damage + intellect;
		}
		else{
			agility = agility*2;
		}
	}
	/*
	*	@author Anthony Bertucci
	*	This method equips a chosen item from the heldItems arraylist in the corresponding slot. If the item desired
	*	is not contained within the held items, this method returns false and does nothing. Otherwise, the item is 
	*	removed from the held items arraylist and added to the corresponding slot within the equipped items arraylist.
	*
	*	@param item Item to be added to equipped items
	*	@param slot corresponding slot within equipped items to add the item
	*
	*	@return boolean whether or not the desired item was equipped to equipped items
	*/
   
         @Override
        public boolean equipItem(item item, int slot) {
            if (inventory.heldItems.contains(item)) {
		unEquipItem(slot);
		item toAdd = inventory.heldItems.get(inventory.heldItems.indexOf(item));
		inventory.heldItems.set(inventory.heldItems.indexOf(item), null);
		inventory.equippedItems.set(slot, toAdd);
		return true;
            }
            else {
                return false;
            }
        }
	/*
	*	@author Anthony Bertucci
	*	This method removes an equipped from the desired slot and places said item back into the held items portion of the
	*	inventory. This method checks whether or not the desired slot to unequip from contains an item, as well as if there
	*	is sufficient space within the held items portion of the inventory to hold the equipped item. If these conditions are
	*	met, the item will be returned to heldItems and the corresponding slot in equippedItems will be set to null
	*
	*	@param slot slot for an item to be removed from (0 through 5, in the order of head, chest, legs, arms, held, and offhand)
	*
	*/
        @Override
        public boolean unEquipItem(int slot){
            if (inventory.equippedItems.get(slot) != null && inventory.heldItems.size() < inventory.getMaxInventorySize()) {
            //an item is currently equipped in this slot and space exists in the inventory to hold the item
                item toRemove = inventory.equippedItems.get(slot); 	//Retreive item held in slot
                inventory.heldItems.add(toRemove);				//Add retreived item to held items
                inventory.equippedItems.set(slot, null);			//Set slot item to null, indicating item is no longer equipped
                return true; //Item was succesfully unequipped and placed within inventory
            }
            else {
                return false; //Item was not unequipped due to not existing or insufficient inventory space.
            }
        }

        @Override
        public boolean returnItem(int slot) {
            return true;
        }
}
