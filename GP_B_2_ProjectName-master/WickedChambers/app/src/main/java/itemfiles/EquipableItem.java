package itemfiles;
/**
*	An equipable item is an item that can be equipped by the player. They will improve the stats of this player by amounts dependent on the items themselves.
*
 */
public class EquipableItem implements Item
{
	private String itemName;
	private boolean isEquipped;
	private int levelRequirement;
	private String typeSlot;
	private double armorValue;
	private double weaponValue;
	private String damageType;
	private double weight;
	private int itemId;
	private int itemD;

	//equipped Items is in the following format: 0: weapon, 1: offhand, 2: head, 3: chest, 4: hands, 5: feet


	/**
	*	Creates an EquipableItem object. The details of this item are required as parameters.
	*
	* 	@param levelReq level requirement to equip this item
	* 	@param type slot this item is equipped in
	* 	@param armorValue armor provided by this item
	* 	@param weaponValue damage provided by this item
	* 	@param damageType type of damage (physical or magic)
	*	@param weight weight of the given object (to be used for determining overencumbrence)
	*	@param itemName name of the given item
	*	@param itemId deterministic integer id of the given item
	 *  @param itemD determines the drawable to be used to display this object
	 */
	public EquipableItem(int levelReq, String type, double armorValue, double weaponValue, String damageType, double weight, String itemName, int itemId, int itemD)
	{
		this.itemId = itemId;
		levelRequirement = levelReq;
		typeSlot = type;
		this.armorValue = armorValue;
		this.weaponValue = weaponValue;
		this.damageType = damageType;
		this.weight = weight;
		this.itemName = itemName;
		isEquipped = false;
		this.itemD = itemD;
	}
	/**
	*	Default constructor for an EquipableItem object to ensure non null data for basic objects (useful for comparisons to empty objects)
	 */
	public EquipableItem()
	{
		levelRequirement = 0;
		this.itemD = 0;
		typeSlot = null;
		armorValue = 0;
		weaponValue = 0;
		damageType = null;
		weight = 0;
		isEquipped = false;
		itemName = "BLANKITEM";
	}

	/**
	 * @return level requirement of item
	 */
	public int getLevelRequirement() 
	{
		return levelRequirement;
	}

	/**
	 * @return equipable status of item
	 */
	public boolean getIsEquipable() 
	{
		return true;
	}

	/**
	 * @return type of item
	 */
	public String returnItemType()
	{
		return typeSlot;
	}

	/**
	 * @return the type of damage done by this item, if any
	 */
	public String returnDamageType()
	{
		return damageType;
	}

	/**
	 * @return the value of armor provided by this item, if any
	 */
	public double returnArmorValue()
	{
		return armorValue;
	}

	/**
	 * @return the value of damage provided by this item, if any
	 */
	public double returnWeaponValue()
	{
		return weaponValue;
	}

	/**
	 * @return the weight of this item
	 */
	public double getWeight()
	{
		return weight;
	}

	/**
	 * @return the identification number of this item
	 */
	public int getItemId() { return itemId; }

	/**
	 * @return the name of this item
	 */
	public String getItemName()
	{
		return itemName;
	}

	/**
	 * @return the slot in which this item is to be equipped
	 */
	public String getTypeSlot() { return typeSlot; }

	/**
	 * @return whether or not this item is currently equipped
	 */
	public boolean returnIsEquipped()
	{
		return isEquipped;
	}

	/**
	 * Equips the current item
	 */
	public void equip()
	{
		isEquipped = true;
	}

	/**
	 * @return the slot in which this item is to be equipped (in string form)
	 */
	public int getSlot()
	{
		if (typeSlot.equals("hand"))
			return 0;
		else if (typeSlot.equals("offhand"))
			return 1;
		else if (typeSlot.equals("head"))
			return 2;
		else if (typeSlot.equals("chest"))
			return 3;
		else if (typeSlot.equals("arms"))
			return 4;
		else if (typeSlot.equals("legs"))
			return 5;
		else
			return -1;
	}

	/**
	 * @return the drawable to be used to display this item
	 */
	public int getItemD()
	{
		return itemD;
	}
}
