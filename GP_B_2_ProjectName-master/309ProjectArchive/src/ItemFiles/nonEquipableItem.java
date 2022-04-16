package ItemFiles;

public class nonEquipableItem implements item
{
	private int levelRequirement;
	private double healValue;
	private String healType;
	private int quantity;
	private double weightPerItem;
	private String itemName;
	
	public nonEquipableItem(int levelReq, String healType, double healValue, double weight, int quantity, String itemN)
	{
		for (int i = 0; i < 3; i++)
		{
			if (healType.equals(acceptableHealTypes[i]))
			{
				isLegal = true;
			}
		}
		levelRequirement = levelReq;
		this.healValue = healValue;
		this.healType = healType;
		weightPerItem = weight;
		itemName = itemN;
	}
	public int getLevelRequirement() 
	{
		return levelRequirement;
	}

	public boolean getIsEquipable() 
	{
		return false;
	}
	
	public double getWeightTotal()
	{
		return quantity * weightPerItem;
	}
	
	public double getWeight()
	{
		return weightPerItem;
	}
	public String getHealType()
	{
		return healType;
	}
	
	public double getHealValue()
	{
		return healValue;
	}
	
	public void increaseQuantity(int increaseBy)
	{
		quantity += increaseBy;
	}
	
	public String getItemName()
	{
		return itemName;
	}
	
	public String toString()
	{
		return  levelRequirement + ":" +
				healType + ":" +
				healValue + ":" +
				weightPerItem + ":" +
				quantity + ":" +
				itemName;
	}
	/*
        *	@author Anthony Bertucci
        *
        * 	This method creates a nonEquipableItem from a String, allowing for de-serialization from database strings
         */
	public nonEquipableItem fromString(String input)
	{
		String[] arr = input.String.split(":");
		int levelReq = Integer.parseInt(arr[0]);
		String healT = arr[1];
		double healV = Double.parseDouble(arr[2]);
		double w = Double.parseDouble(arr[3]);
		int q = Integer.parseInt(arr[4]);
		String n = arr[5];
		return nonEquipableItem(levelReq, healT, healV, w, q, n);
	}
}
