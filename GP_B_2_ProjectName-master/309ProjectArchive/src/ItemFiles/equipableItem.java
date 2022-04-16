package ItemFiles;

public class equipableItem implements item
{
	private String itemName;
	private boolean isEquipped;
	private int levelRequirement;
	private String typeSlot;
	private double armorValue;
	private double weaponValue;
	private String damageType;
	private double weight;
	private String[] acceptableItemList = {"head", "chest", "legs", "arms", "held", "offhand"};
	
	public equipableItem(int levelReq, String type, double armorValue, double weaponValue, String damageType, double weight, String itemName) throws IllegalArgumentException
	{
		boolean isLegal = false;
		for (int i = 0; i < 6; i++)
		{
			if (type.equals(acceptableItemList[i]))
			{
				isLegal = true;
			}
		}
		if (!isLegal)
			throw new IllegalArgumentException("Type Not valid");
		levelRequirement = levelReq;
		typeSlot = type;
		this.armorValue = armorValue;
		this.weaponValue = weaponValue;
		this.damageType = damageType;
		this.weight = weight;
		this.itemName = itemName;
		isEquipped = false;
	}
	
	public int getLevelRequirement() 
	{
		return levelRequirement;
	}

	public boolean getIsEquipable() 
	{
		return true;
	}
	
	public String returnItemType()
	{
		return typeSlot;
	}
	public String returnDamageType()
	{
		return damageType;
	}
	public double returnArmorValue()
	{
		return armorValue;
	}
	public double returnWeaponValue()
	{
		return weaponValue;
	}
	public double getWeight()
	{
		return weight;
	}
	
	public String getItemName()
	{
		return itemName;
	}
	
	public boolean returnIsEquipped()
	{
		return isEquipped;
	}
	
	public void equip()
	{
		isEquipped = true;
	}
	
	public String toString()
	{
		return  levelRequirement + ":" +
				typeSlot + ":" +
				armorValue + ":" +
				weaponValue + ":" +
				damageType + ":" +
				weight + ":" +
				itemName;
	}
	/*
	*	@author Anthony Bertucci
	*
	* 	This method creates an equipableItem from a String, allowing for de-serialization from database strings
	 */
	public equipableItem fromString(String input)
	{
		String[] arr = input.String.split(":");
		int levelReq = Integer.parseInt(arr[0]);
		String typeSlot = arr[1];
		double armorValue = Double.parseDouble(arr[2]);
		double weaponValue = Double.parseDouble(arr[3]);
		String damageType = arr[4];
		double weight = Double.parseDouble(arr[5]);
		String itemName = arr[6];
		return new equipableItem(levelReq, typeSlot, armorValue, weaponValue, damageType, weight, itemName);
	}

}
