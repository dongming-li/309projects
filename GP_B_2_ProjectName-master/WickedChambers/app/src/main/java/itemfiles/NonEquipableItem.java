package itemfiles;

/**
*	A NonEquipableItem is an item that the player can use but not equip. These include potions and key items to be used in battle.
 */
public class NonEquipableItem implements Item
{
	private int levelRequirement;
	private double healValue;
	private String healType;
	private int quantity;
	private double weightPerItem;
	private String itemName;
	private int itemId;
	private int itemD;
	/**
	*	Constructor for a NonEquipableItem object. The details of this item are required as parameters
	*
	* 	@param levelReq level required to equip this item
	* 	@param healType stat to be increased/healed by this item
	*	@param healValue amount for the given stat to be boosted/healed
	*	@param weight weight of the given object (to be used for determining overencumbrence)
	*	@param quantity number of this particular object the player is holding
	*	@param itemN name of the given item
	*	@param itemId deterministic integer id of the given object
	 *  @param itemD drawable resource to be used to display this item
	 */
	public NonEquipableItem(int levelReq, String healType, double healValue, double weight, int quantity, String itemN, int itemId, int itemD)
	{
		this.itemId = itemId;
		levelRequirement = levelReq;
		this.healValue = healValue;
		this.healType = healType;
		weightPerItem = weight;
		this.quantity = quantity;
		itemName = itemN;
		this.itemD = itemD;
	}
	/**
    *	Default constructor for a NonEquipableItem object to ensure non null data for basic objects (useful for comparisons to empty objects)
     */
	public NonEquipableItem()
	{
		levelRequirement = 0;
		healValue = 0;
		healType = null;
		quantity = 0;
		weightPerItem = 0;
		itemD = 0;
		itemName = null;
	}

	/**
	 * @return level requirement to use this item
	 */
	public int getLevelRequirement() 
	{
		return levelRequirement;
	}

	/**
	 * @return whether or not the given item is equippable (always false)
	 */
	public boolean getIsEquipable() 
	{
		return false;
	}

	/**
	 * @return total weight of all of the items of this type in the given slot.
	 */
	public double getWeightTotal()
	{
		return quantity * weightPerItem;
	}

	/**
	 * @return weight of only one of this item type
	 */
	public double getWeight()
	{
		return weightPerItem;
	}

	/**
	 * @return The stat influenced/healed by this item
	 */
	public String getHealType()
	{
		return healType;
	}

	/**
	 * @return The amount healed by this item
	 */
	public double getHealValue()
	{
		return healValue;
	}

	/**
	 * @param increaseBy quantity to increase the given item by
	 */
	public void increaseQuantity(int increaseBy)
	{
		quantity += increaseBy;
	}

	/**
	 * @return the unique identifying number for the given item
	 */
	public int getItemId() { return itemId; }

	/**
	 * @return the name of the given item
	 */
	public String getItemName()
	{
		return itemName;
	}

	/**
	 * @return the quantity of the given items within the slot
	 */
	public int getQuantity() { return quantity; }

	/**
	 * @return a constant -1 so that this item can't be illegally equipped
	 */
	public int getSlot() { return -1; }

	/**
	 * @return the drawable resource used to display this item
	 */
	public int getItemD() { return itemD; }
}
