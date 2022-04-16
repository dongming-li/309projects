package itemfiles;


import java.util.ArrayList;
import characters.CharCreate;

/**
 * Created by bertucci on 10/5/2017.
 */

public class ItemTests
{
    public static void main(String args[])
    {
        Inventory testInv = new Inventory();
        ArrayList<Item> mList = Inventory.generateMasterItemList();
        testInv.give(mList.get(1));
        testInv.give(mList.get(6));
        testInv.equippedItems[1] = mList.get(6);
        testInv.heldItems[19] = mList.get(5);
        CharCreate buddy = new CharCreate('w');
        buddy.inventory = testInv;
        System.out.println(buddy.inventory.serialize());
        buddy.equipItem(buddy.inventory.heldItems[19], 0, 19);
        System.out.println(buddy.inventory.serialize());
        buddy.unEquipItem(0);
        System.out.println(buddy.inventory.serialize());
    }

}
