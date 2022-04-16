package itemfiles;

import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.mlscholl.wickedchambers.R;

import java.util.ArrayList;

import characters.CharCreate;

import static android.view.View.GONE;

/**
 * Created by Antonio on 12/1/2017.
 */

public class InventoryTest extends AppCompatActivity implements InventoryMenuFragment.OnFragmentInteractionListener
{

    private ArrayList<Item> mList;

    private CharCreate buddy;
    private CharCreate buddy2;
    private CharCreate selectedCharacter;
    private Button createInventory;
    private Button displayStats;
    private FrameLayout fL;
    //THIS IS IMPORTANT AND WILL NEED TO BE TRANSPLANTED WHEREVER THIS MENU GOES
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory_fragment_test_layout);
        buddy = new CharCreate('m');
        buddy.addHealth(100);
        buddy.addIntellect(500);
        mList = Inventory.generateMasterItemList();
        buddy.giveItem(mList.get(47));
        buddy.giveItem(mList.get(48));
        buddy.giveItem(mList.get(49));
        buddy.giveItem(mList.get(50));
        buddy.giveItem(mList.get(51));
        buddy.giveItem(mList.get(52));



        createInventory = (Button)findViewById(R.id.createFragment);
        displayStats = (Button)findViewById(R.id.displayStats);
        fL = (FrameLayout)findViewById(R.id.fragmentlayout);
        createInventory.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                InventoryMenuFragment fraggy = InventoryMenuFragment.newInstance(buddy);
                FragmentManager fM = getSupportFragmentManager();
                FragmentTransaction fT = fM.beginTransaction();
                fT.add(R.id.fragmentlayout, fraggy, "TAG");
                FrameLayout frame = (FrameLayout)findViewById(R.id.fragmentlayout);
                frame.setVisibility(View.VISIBLE);
                createInventory.setVisibility(View.GONE);
                displayStats.setVisibility(View.GONE);
                fT.commit();
            }
        });
        displayStats.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Toast.makeText(getApplicationContext(), "HP: " + buddy.getHealth() + "\nSTR: " + buddy.getStrength() + "\nINT: " + buddy.getIntellect() + "\nAGI: " + buddy.getAgility(), Toast.LENGTH_LONG).show();
            }
        });
    }
    //THIS IS IMPORTANT AND WILL NEED TO BE TRANSPLANTED INTO WHEREVER THIS MENU GOES
    @Override
    public void onFragmentInteraction(String statOffset) {
        String[] data = statOffset.split(":");
        int healthBonus = Integer.parseInt(data[0]);
        int strengthBonus = Integer.parseInt(data[1]);
        int intellectBonus = Integer.parseInt(data[2]);
        int agilityBonus = Integer.parseInt(data[3]);
        Inventory newInv = Inventory.deserialize(data[4]);
        //Toast.makeText(getApplicationContext(), data[4], Toast.LENGTH_SHORT).show();
        buddy.addHealth(healthBonus);
        buddy.addStrength(strengthBonus);
        buddy.addIntellect(intellectBonus);
        buddy.addAgility(agilityBonus);
        buddy.inventory = newInv;
        FrameLayout frame = (FrameLayout)findViewById(R.id.fragmentlayout);
        frame.setVisibility(View.GONE);
        createInventory.setVisibility(View.VISIBLE);
        displayStats.setVisibility(View.VISIBLE);
    }
}
