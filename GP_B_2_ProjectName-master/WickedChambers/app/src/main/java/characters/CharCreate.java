package characters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.annotation.StringRes;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mlscholl.wickedchambers.R;

import game.VolleyCallback;
import itemfiles.Inventory;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import itemfiles.*;

import skillfiles.*;

import server.ServerUpdates;

import static game.MainActivity.player1;
import static game.MainThread.canvas;
import static java.security.AccessController.getContext;


/*
* 	A CharCreate Object is the main way the player will be able to see the effects of their interactions on the game. It contains their character's stats, health, level, xp, inventory
* 	and unlocked skills. As the player progresses through the game, they will be able to customize their CharCreate object using items and xp they obtain, as well as what they choose to
* 	equip and the skills they wish to unlock
*
 */
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
	public SkillTree skillTree;
	public Inventory inventory;
	public String[] attributes;

	private static final int NUMBER_OF_VERTICAL_SQUARES = 4;
	private static final int NUMBER_OF_HORIZONTAL_SQUARES = 7;

	private Rect p1Hp, p2Hp, p3Hp, p4Hp;
	private int healthh = 100, mana = 100;
	private Bitmap character, death1, death2, death3, blood;
	private int player = 0;
	private String username;
	private int canvasHeight, canvasWidth;
	private int healthScale = 0;
	private int test1 = 1, test2 = 1, test3 = 1, test4 = 1;
	private int firstRun = 0;
	private Context context;
	private int deathTime = 0;
	private int count = 0;

	/**
	*	Constructor
	*	@param cl (required) class of the character. Can be w, m, or r (Warrior, Mage, or Rogue)
	*
	 */
	public CharCreate(char cl) {
		if (cl == 'w') {
			isWarr = true;
			health = 100;
			strength = 5;
			intellect = 0;
			agility = 0;
			charLevel = 1;
			charXP = 0;
			isAlive = true;
			skillTree = SkillTree.generateWarriorSkillTree(); //Generate a default skill tree of warrior skills
			inventory = new Inventory();
		} else if (cl == 'm') {
			isMage = true;
			health = 100;
			strength = 0;
			intellect = 5;
			agility = 0;
			charLevel = 1;
			charXP = 0;
			isAlive = true;
			skillTree = SkillTree.generateMageSkillTree(); //Generate a default skill tree of Mage skills
			inventory = new Inventory();
		} else {
			isRogue = true;
			health = 100;
			strength = 0;
			intellect = 0;
			agility = 5;
			charLevel = 1;
			charXP = 0;
			isAlive = true;
			skillTree = SkillTree.generateRogueSkillTree(); //Generate a default skill tree of Rogue skills
			inventory = new Inventory();
		}
	}

	/**
	 * Updates the character objects Bitmap information
	 * @param image (required) bitmap image to be used
	 * @param height (required) height (in pixels) of image
	 */
	public void updateImage(Bitmap image, int height) {
		character = Bitmap.createScaledBitmap(image, (int)(height*.25), (int)(height*.25), true);
	}

	/**
	 * Constructor
	 * @param attributes (required) String array of attributes to be read in from the database and used to create a CharCreate Object
	 * @param player (optional) player number used to reflect which of the four players the CharCreate object will represent
	 */
	public CharCreate(String[] attributes, int player){

		this.player = player;

		if (attributes[0].equals("w")){
			isWarr = true;
			//Temporary AF
			//this.skillTree = SkillTree.generateWarriorSkillTree();
		}
		else if (attributes[0].equals("r")){
			isRogue = true;
			//Temporary AF
			//this.skillTree = SkillTree.generateRogueSkillTree();
		}
		else
		{
			isMage = true;
			//Temporary AF
			//this.skillTree = SkillTree.generateMageSkillTree();
		}

		this.health = Integer.parseInt(attributes[1]);
		this.strength = Integer.parseInt(attributes[2]);
		this.intellect = Integer.parseInt(attributes[3]);
		this.agility = Integer.parseInt(attributes[4]);
		this.charLevel = Integer.parseInt(attributes[5]);
		this.charXP = Integer.parseInt(attributes[6]);
		this.inventory = new Inventory();
		this.inventory = Inventory.deserialize(attributes[7]);
		this.skillTree = SkillTree.deserialize(attributes[8]);
		p1Hp = new Rect();
		p2Hp = new Rect();
		p3Hp = new Rect();
		p4Hp = new Rect();
	}

	/**
	 * Returns username of current character
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Updates username information to the passed string
	 * @param username new username information
	 */
	public void updateUser(String username) {
		this.username = username;
	}

	/**
	 * Loads Resources to be used for death and blood animations
	 * @param context context in which this animation is to be used.
	 */
	public void loadResources(Context context) {
		this.context = context;

		death1 = BitmapFactory.decodeResource(context.getResources(), R.drawable.cloud_fire0);
		death2 = BitmapFactory.decodeResource(context.getResources(), R.drawable.cloud_fire1);
		death3 = BitmapFactory.decodeResource(context.getResources(), R.drawable.cloud_fire2);
		blood = BitmapFactory.decodeResource(context.getResources(), R.drawable.blood_red1);
	}

	/**
	* 	Returns the class of the given character in the form of a string
	*
	* 	@return String the class of the given CharCreate Object, which is either a Warrior, Mage, or Rogue.
	 */
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

	/**
	 * Draws all four of the players on the current GameWindow to reflect their health and alive status.
	 * @param canvas canvas object on which the characters are to be drawn
	 */
	@Override
	public void draw(Canvas canvas) {

		Paint redPaint = new Paint();
		redPaint.setStrokeWidth(0);
		redPaint.setColor(Color.RED);
		redPaint.setMaskFilter(new BlurMaskFilter(5, BlurMaskFilter.Blur.INNER));

		canvasWidth = canvas.getWidth();
		canvasHeight = canvas.getHeight();

		int squareWidth = canvasWidth / NUMBER_OF_HORIZONTAL_SQUARES;
		int squareHeight = canvasHeight / NUMBER_OF_VERTICAL_SQUARES;

		healthScale = squareWidth;

		Rect p1Rect = new Rect();
		Rect p2Rect = new Rect();
		Rect p3Rect = new Rect();
		Rect p4Rect = new Rect();

		if (firstRun == 0) {
			if (player == 1) p1Hp.set((int)(canvasWidth*.7), (int)(canvasHeight*.2), (int)(canvasWidth*.7 + squareWidth), (int)(canvasHeight*.25));
			if (player == 2) p2Hp.set((int)(canvasWidth*.85), (int)(canvasHeight*.3), (int)(canvasWidth*.85 + squareWidth), (int)(canvasHeight*.35));
			if (player == 3) p3Hp.set((int)(canvasWidth*.7), (int)(canvasHeight*.6), (int)(canvasWidth*.7 + squareWidth), (int)(canvasHeight*.65));
			if (player == 4) p4Hp.set((int)(canvasWidth*.85), (int)(canvasHeight*.7), (int)(canvasWidth*.85 + squareWidth), (int)(canvasHeight*.75));
			firstRun = 1;
		}
		if (player == 1) {
			if (!isAlive) {character = blood;}
			p1Rect.set(0, 0, squareWidth, squareHeight);
			p1Rect.offsetTo((int)(canvasWidth*.7), (int)(canvasHeight*.25));
			canvas.drawBitmap(character, null, p1Rect, null);
			canvas.drawRect(p1Hp, redPaint);
		}
		else if (player == 2) {
			if (!isAlive) {character = blood;}
			p2Rect.set(0, 0, squareWidth, squareHeight);
			p2Rect.offsetTo((int)(canvasWidth*.85), (int)(canvasHeight*.35));
			canvas.drawBitmap(character, null, p2Rect, null);
			canvas.drawRect(p2Hp, redPaint);
		}
		else if (player == 3) {
			if (!isAlive) {character = blood;}
			p3Rect.set(0, 0, squareWidth, squareHeight);
			p3Rect.offsetTo((int)(canvasWidth*.7), (int)(canvasHeight*.65));
			canvas.drawBitmap(character, null, p3Rect, null);
			canvas.drawRect(p3Hp, redPaint);
		}
		else if (player == 4) {
			if (!isAlive) {character = blood;}
			p4Rect.set(0, 0, squareWidth, squareHeight);
			p4Rect.offsetTo((int)(canvasWidth*.85), (int)(canvasHeight*.75));
			canvas.drawBitmap(character, null, p4Rect, null);
			canvas.drawRect(p4Hp, redPaint);
		}
		else {
		}
	}

	/**
	 * Updates the current players visible stats on the game window based on what their current stat values are, namely to reflect health loss.
	 */
	public void updateStats() {
		double hpPixels = ((double)this.health / 100) * healthScale;

		if (player == 1) {
			p1Hp.set((int)(canvasWidth*.7), (int)(canvasHeight*.2), (int)(canvasWidth*.7 + hpPixels), (int)(canvasHeight*.25));
		}
		else if (player == 2) {
			p2Hp.set((int)(canvasWidth*.85), (int)(canvasHeight*.3), (int)(canvasWidth*.85 + hpPixels), (int)(canvasHeight*.35));
		}
		else if (player == 3) {
			p3Hp.set((int)(canvasWidth*.7), (int)(canvasHeight*.6), (int)(canvasWidth*.7 + hpPixels), (int)(canvasHeight*.65));
		}
		else if (player == 4) {
			p4Hp.set((int)(canvasWidth*.85), (int)(canvasHeight*.7), (int)(canvasWidth*.85 + hpPixels), (int)(canvasHeight*.75));
		}
		else {

		}

	}

	/**
	* 	Returns the health of the given CharCreate
	* 	@return health stat
	 */
	@Override
	public int getHealth() {
		return health;
	}
	/**
	* 	Returns the intellect of the given CharCreate
	*
	* 	@return intellect stat
	 */
	@Override
	public int getIntellect() {
		return intellect;
	}
	/**
	* 	Returns the strength of the given CharCreate
	*
	* 	@return strength stat
	 */
	@Override
	public int getStrength() {
		return strength;
	}
	/**
	* 	Returns the agility of the given CharCreate
	*
	* 	@return agility stat
	 */
	@Override
	public int getAgility() {
		return agility;
	}
	/**
	* 	Increases or decreases the health of the given CharCreate object depending on input. Useful for updating health following the CharCreate object being attacked or healing. This
	* 	method will also determine if the CharCreate has died due to the damage received, which is reflected in the updating of the isAlive boolean variable.
	*
	* 	@param amount number to update health by (can be positive or negative depending on taking damage or healing)
	 */
	@Override
	public void addHealth(int amount) {
		health = health + amount;
		if(health <= 0){
			health = 0;
			isAlive = false;
		}
	}
	/**
	* 	Increases intellect stat of the given CharCreate object by the given amount. Useful for leveling up or consuming an intellect potion.
	*
	* 	@param amount number to update intellect by
	 */
	@Override
	public void addIntellect(int amount) {
		intellect = intellect + amount;
		if(intellect <= 0){
			intellect = 0;
		}
	}
	/**
	* 	Increases strength stat of the given CharCreate object by the given amount. Useful for leveling up or consuming a strength potion.
	*
	* 	@param amount number to update strength by
	 */
	@Override
	public void addStrength(int amount) {
		strength = strength + amount;
		if(strength <= 0){
			strength = 0;
		}
	}
	/**
    * 	Increases agility stat of the given CharCreate object by the given amount. Useful for leveling up or consuming a strength potion.
    * 	@param amount number to update agility by
     */
	@Override
	public void addAgility(int amount) {
		agility = agility + amount;
		if(agility <= 0){
			agility = 0;
		}
	}
	/**
	* 	Returns isAlive variable which determines whether or not the given CharCreate has died.
	* 	@return boolean isAlive whether or not the CharCreate is alive
	 */
	@Override
	public boolean isAlive() {
		return isAlive;
	}
	/**
	* 	Returns the level of the given CharCreate object.
	* 	@return int level
	 */
	@Override
	public int getCharLevel() {
		return charLevel;
	}
	/**
	* 	Returns the xp of the given CharCreate object.
	* 	@return int xp
	 */
	@Override
	public int getCharXP() {
		return charXP;
	}
	/**
	* 	Increments the given CharCreate objects level. Used for levelling up.
	* 	@param amount level(s) to increment by.
	 */
	@Override
	public void addCharLevel(int amount) {
		charLevel = charLevel + amount;
	}
	/**
	* 	Increases the given CharCreates xp by the given amount.
	* 	@param amount xp to be given to the CharCreate object.
	 */
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
	/**
	* 	Calculates the damage produced by an attack from the given CharCreate object. Damage for warriors is determined by strength stat, intellect stat for mages, and agility stat for
	* 	rogues. The mainStatScale variable is determined by character level and the quality of gear they have equipped.
	*
	* 	@return int damage to be done by an attack.
	 */
	public int attack(){
            String charClass = getType();
            Random r = new Random();
            int baseDmg = r.nextInt(3) + 1;
            switch(charClass) {
                case "Warrior":
                    return baseDmg * strength;
                case "Mage":
                    return baseDmg * intellect;
                case "Rogue":
                    return baseDmg * agility;
                default:
                    return 0;
            }
	}

	/**
	*	This method equips a chosen item from the heldItems arraylist in the corresponding slot. If the item desired
	*	is not contained within the held items, this method returns false and does nothing. Otherwise, the item is 
	*	removed from the held items arraylist and added to the corresponding slot within the equipped items array.
	*
	*	@param item Item to be added to equipped items
	*	@param slot corresponding slot within equipped items to add the item
	*
	*	@return boolean whether or not the desired item was equipped to equipped items
	*/
   
         @Override
        public boolean equipItem(Item item, int slot, int gridSlot) {
			 boolean couldEquip = false;
			 if (((EquipableItem)item).getLevelRequirement() > this.charLevel)
			 	return false;
			 if (slot == 0 && inventory.equippedItems[0].getItemName().equals("BLANKITEM"))
			 {
				 inventory.equippedItems[0] = item;
				 couldEquip = true;
			 }
			 else if (slot == 1 && inventory.equippedItems[1].getItemName().equals("BLANKITEM"))
			 {
				 inventory.equippedItems[1] = item;
				 couldEquip = true;
			 }
			 else if (slot == 2 && inventory.equippedItems[2].getItemName().equals("BLANKITEM"))
			 {
				 inventory.equippedItems[2] = item;
				 couldEquip = true;
			 }
			 else if (slot == 3 && inventory.equippedItems[3].getItemName().equals("BLANKITEM"))
			 {
				 inventory.equippedItems[3] = item;
				 couldEquip = true;
			 }
			 else if (slot == 4 && inventory.equippedItems[4].getItemName().equals("BLANKITEM"))
			 {
				 inventory.equippedItems[4] = item;
				 couldEquip = true;
			 }
			 else if (slot == 5 && inventory.equippedItems[5].getItemName().equals("BLANKITEM"))
			 {
				 inventory.equippedItems[5] = item;
				 couldEquip = true;
			 }
			 if (couldEquip)
			 {
				 inventory.heldItems[gridSlot] = new NonEquipableItem(1, "BLANKITEM", 1, 1, 1, "BLANKITEM", 0, R.drawable.blank_item);
			 }
			 return couldEquip;
        }
	/**
	*	This method removes an equipped from the desired slot and places said item back into the held items portion of the
	*	inventory. This method checks whether or not the desired slot to unequip from contains an item, as well as if there
	*	is sufficient space within the held items portion of the inventory to hold the equipped item. If these conditions are
	*	met, the item will be returned to heldItems and the corresponding slot in equippedItems will be set to null
	*
	*	@param slot slot for an item to be removed from (0 through 5, in the order of hand, offhand, head, chest, arms and legs)
	*
	*/
        @Override
        public boolean unEquipItem(int slot)
		{
			boolean couldUnequip = false;
			if (slot == 0)
			{
				couldUnequip = true;
				inventory.heldItems[19] = inventory.equippedItems[slot];
				inventory.equippedItems[slot] = new NonEquipableItem(1, "BLANKITEM", 1, 1, 1, "BLANKITEM", 0, R.drawable.blank_item);
				updateHeldItems();
			}
			else if (slot == 1)
			{
				couldUnequip = true;
				inventory.heldItems[19] = inventory.equippedItems[slot];
				inventory.equippedItems[slot] = new NonEquipableItem(1, "BLANKITEM", 1, 1, 1, "BLANKITEM", 0, R.drawable.blank_item);
				updateHeldItems();
			}
			else if (slot == 2)
			{
				couldUnequip = true;
				inventory.heldItems[19] = inventory.equippedItems[slot];
				inventory.equippedItems[slot] = new NonEquipableItem(1, "BLANKITEM", 1, 1, 1, "BLANKITEM", 0, R.drawable.blank_item);
				updateHeldItems();
			}
			else if (slot == 3)
			{
				couldUnequip = true;
				inventory.heldItems[19] = inventory.equippedItems[slot];
				inventory.equippedItems[slot] = new NonEquipableItem(1, "BLANKITEM", 1, 1, 1, "BLANKITEM", 0, R.drawable.blank_item);
				updateHeldItems();
			}
			else if (slot == 4)
			{
				couldUnequip = true;
				inventory.heldItems[19] = inventory.equippedItems[slot];
				inventory.equippedItems[slot] = new NonEquipableItem(1, "BLANKITEM", 1, 1, 1, "BLANKITEM", 0, R.drawable.blank_item);
				updateHeldItems();
			}
			else if (slot == 5)
			{
				couldUnequip = true;
				inventory.heldItems[19] = inventory.equippedItems[slot];
				inventory.equippedItems[slot] = new NonEquipableItem(1, "BLANKITEM", 1, 1, 1, "BLANKITEM", 0, R.drawable.blank_item);
				updateHeldItems();
			}
			return couldUnequip;
        }

	public boolean giveItem(Item i)
	{
		for (int j = 0; j < inventory.heldItems.length; j++)
		{
			if (inventory.heldItems[j].getItemName().equals("BLANKITEM"))
			{
				inventory.heldItems[j] = i;
				return true;
			}
		}
		return false;
	}

	/**
	 * Updates the current state of heldItems when an item is unequipped to ensure the inventory stays somewhat organized
	 */
	public void updateHeldItems()
	{
		//If an item exists at space 19 and there are other spaces available earlier, will move item into earliest available space
		for (int i = 0; i < inventory.heldItems.length - 1; i++)
		{
			if (inventory.heldItems[i].getItemName().equals("BLANKITEM"))
			{
				inventory.heldItems[i] = inventory.heldItems[19];
				inventory.heldItems[19] = new NonEquipableItem(1, "BLANKITEM", 1, 1, 1, "BLANKITEM", 0, R.drawable.blank_item);
			}
		}

	}


        /**
        * 	This method converts a CharCreate object (and all relevant data) into strings for the purpose of pushing to the database.
        * 	Data is converted in the following orientation:
        *
        * 	isAWarrior:isARogue:isAMage:healthStat:strengthStat:intellectStat:agilityStat:characterLevel:characterXP:characterIsAlive:skillTreeString:inventoryString
        *
        * 	In addition, toString() methods for skillTree and Inventory were made such that no linespaces existed, and individual fromString methods parsed using '|' rather
        * 	than ':' to allow this method to function.
         */
        public String serialize()
		{
			String isW = "F";
			String isM = "F";
			String isR = "F";
			String isA = "F";
			if (isWarr)
			{
				isW = "T";
				isM = "F";
				isR = "F";
			}
			else if (isRogue)
			{
				isW = "F";
				isM = "F";
				isR = "T";
			}
			else if (isMage)
			{
				isW = "F";
				isM = "T";
				isR = "F";
			}
			if (isAlive)
			{
				isA = "T";
			}
			String toString = isW + ":" + isR + ":" + isM + ":" + Integer.toString(health) + ":" +
					Integer.toString(strength) + ":" + Integer.toString(intellect) + ":" +
					Integer.toString(agility) + ":" + Integer.toString(charLevel) + ":" +
					Integer.toString(charXP) + ":" + isA + ":" + inventory.serialize() + ":" +
					skillTree.serialize();
			return toString;
		}


		/**
		*
		* 	This method converts the output from the toString() method and converts it into a functional CharCreate object with all the relevant data fields.
		* 	This is for the purpose of pulling data from the database to create a CharCreate object from stored data.
		* 	Data from toString is stored in the following pattern:
		*
		*	isAWarrior:isARogue:isAMage:healthStat:strengthStat:intellectStat:agilityStat:characterLevel:characterXP:characterIsAlive:skillTreeString:inventoryString
		*
		 */
		public static CharCreate deserialize(String input)
		{
			//Initalization to be changed later
			CharCreate fromString = new CharCreate('d');
			//Organize pulled data
			String[] data = input.split(":");
			//Is a Warrior
			if (data[0].equals("T"))
			{
				fromString = new CharCreate('w');
			}
			//Is a Rogue
			else if (data[1].equals("T"))
			{
				fromString = new CharCreate('r');
			}
			//Is a Mage
			else if (data[2].equals("T"))
			{
				fromString = new CharCreate('m');
			}
			//Modify health stat
			fromString.health = Integer.parseInt(data[3]);
			//Modify strength stat
			fromString.strength = Integer.parseInt(data[4]);
			//Modify intellect stat
			fromString.intellect = Integer.parseInt(data[5]);
			//Modify agility stat
			fromString.agility = Integer.parseInt(data[6]);
			//Modify character level
			fromString.charLevel = Integer.parseInt(data[7]);
			//Modify character experience
			fromString.charXP = Integer.parseInt(data[8]);
			//Modify character isAlive
			if (data[9].equals("T"))
				fromString.isAlive = true;
			else
				fromString.isAlive = false;
			fromString.inventory = Inventory.deserialize(data[10]);
			fromString.skillTree = SkillTree.deserialize(data[11]);
			return fromString;
		}
}
