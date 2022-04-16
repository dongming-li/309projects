package itemfiles;


/*
*	Interface for required item object functionality. Used within EquipableItem and NonEquipableItem classses
*
*
 */
public interface Item
{
	public boolean getIsEquipable();
	public double getWeight();
	public String getItemName();
	public int getItemId();
	public int getSlot();
	public int getItemD();
}
