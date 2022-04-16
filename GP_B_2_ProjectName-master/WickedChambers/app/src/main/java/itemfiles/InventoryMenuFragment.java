package itemfiles;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mlscholl.wickedchambers.R;

import characters.CharCreate;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link InventoryMenuFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link InventoryMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InventoryMenuFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_INPUT = "SERIALIZED_CHARACTER";

    private Button exitButton;

    private Button b00;
    private Button b01;
    private Button b02;
    private Button b03;
    private Button b04;
    private Button b10;
    private Button b11;
    private Button b12;
    private Button b13;
    private Button b14;
    private Button b20;
    private Button b21;
    private Button b22;
    private Button b23;
    private Button b24;
    private Button b30;
    private Button b31;
    private Button b32;
    private Button b33;
    private Button b34;

    private Button hand;
    private Button offhand;
    private Button head;
    private Button chest;
    private Button arms;
    private Button legs;

    // TODO: Rename and change types of parameters
    private CharCreate characterInput;
    private String statOffset;
    private int healthOffset;
    private int strengthOffset;
    private int intellectOffset;
    private int agilityOffset;
    private String mParam1;
    private String mCLS;
    private String mHP;
    private String mSTR;
    private String mINT;
    private String mAGI;
    private String mLVL;
    private String mEXP;
    private TextView mCLSTV;
    private TextView mHPTV;
    private TextView mSTRTV;
    private TextView mINTTV;
    private TextView mAGITV;
    private TextView mLVLTV;
    private TextView mEXPTV;


    private OnFragmentInteractionListener mListener;

    public InventoryMenuFragment() {
        // Required empty public constructor
    }

    /**
     * Used to manage the parameters needed to create a new Inventory Fragment
     *
     * @param input the character object that the inventory is going to be based on
     * @return A new instance of fragment InventoryMenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InventoryMenuFragment newInstance(CharCreate input) {
        InventoryMenuFragment fragment = new InventoryMenuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_INPUT, input.serialize());
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * This creates the required variables for an Inventory Menu Fragment and saves them so that the rest of the fragment's methods can access them.
     * @param savedInstanceState the current state saved by the application
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_INPUT);
        }
        statOffset = "0:0:0:0"; //As a test, add 5 to every skill every time the inventory is opened
        characterInput = CharCreate.deserialize(mParam1);
        statOffset += ":" + characterInput.inventory.serialize();
        //Toast.makeText(getContext(), statOffset, Toast.LENGTH_SHORT).show();
        mCLS = characterInput.getType();
        mHP = characterInput.getHealth() + "";
        mSTR = characterInput.getStrength() + "";
        mINT = characterInput.getIntellect() + "";
        mAGI = characterInput.getAgility() + "";
        mLVL = characterInput.getCharLevel() + "";
        mEXP = characterInput.getCharXP() + "";

    }

    /**
     * Creates all of the different objects that exist within an Inventory Menu Fragment, as well as sets the functionality of those that are able to
     * be interacted with by the user (buttons). This fragment utilizes the logic implemented in Inventory.class to manage the contents of an inventory,
     * as well as display the items contained within correctly and in a way that is pleasing to use for the user.
     * @param inflater layout to design the fragment
     * @param container container to hold the fragment within another layout
     * @param savedInstanceState current saved values of the application
     * @return view to be displayed to the user
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_inventory_menu, container, false);
        exitButton = (Button)v.findViewById(R.id.exitButton);
        mCLSTV = (TextView)v.findViewById(R.id.characterCLS);
        mHPTV = (TextView)v.findViewById(R.id.characterHP);
        mSTRTV = (TextView)v.findViewById(R.id.characterSTR);
        mINTTV = (TextView)v.findViewById(R.id.characterINT);
        mAGITV = (TextView)v.findViewById(R.id.characterAGI);
        mLVLTV = (TextView)v.findViewById(R.id.characterLEVEL);
        mEXPTV = (TextView)v.findViewById(R.id.characterEXP);
        mCLSTV.setText("Class: " + mCLS);
        mHPTV.setText("HP: " + mHP);
        mSTRTV.setText("STR: " + mSTR);
        mINTTV.setText("INT: " + mINT);
        mAGITV.setText("AGI: " + mAGI);
        mLVLTV.setText("LVL: " + mLVL);
        mEXPTV.setText("EXP: " + mEXP);

        b00 = (Button)v.findViewById(R.id.b00);
        b01 = (Button)v.findViewById(R.id.b01);
        b02 = (Button)v.findViewById(R.id.b02);
        b03 = (Button)v.findViewById(R.id.b03);
        b04 = (Button)v.findViewById(R.id.b04);
        b10 = (Button)v.findViewById(R.id.b10);
        b11 = (Button)v.findViewById(R.id.b11);
        b12 = (Button)v.findViewById(R.id.b12);
        b13 = (Button)v.findViewById(R.id.b13);
        b14 = (Button)v.findViewById(R.id.b14);
        b20 = (Button)v.findViewById(R.id.b20);
        b21 = (Button)v.findViewById(R.id.b21);
        b22 = (Button)v.findViewById(R.id.b22);
        b23 = (Button)v.findViewById(R.id.b23);
        b24 = (Button)v.findViewById(R.id.b24);
        b30 = (Button)v.findViewById(R.id.b30);
        b31 = (Button)v.findViewById(R.id.b31);
        b32 = (Button)v.findViewById(R.id.b32);
        b33 = (Button)v.findViewById(R.id.b33);
        b34 = (Button)v.findViewById(R.id.b34);

        hand = (Button)v.findViewById(R.id.hand);
        offhand = (Button)v.findViewById(R.id.offhand);
        head = (Button)v.findViewById(R.id.head);
        chest = (Button)v.findViewById(R.id.chest);
        arms = (Button)v.findViewById(R.id.arms);
        legs = (Button)v.findViewById(R.id.legs);

        b00.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if (!characterInput.inventory.heldItems[0].getItemName().equals("BLANKITEM")) {
                    if (characterInput.inventory.heldItems[0].getIsEquipable())
                    {
                        //equip the item
                        itemEquipped((EquipableItem) characterInput.inventory.heldItems[0], 0);
                    }
                    else
                    {
                        int slot = 0;
                        if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("health"))
                        {
                            characterInput.addHealth((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("strength"))
                        {
                            characterInput.addStrength((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("intellect"))
                        {
                            characterInput.addIntellect((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("agility"))
                        {
                            characterInput.addAgility((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        Toast.makeText(getContext(), ((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType() + " increased by " + ((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue(), Toast.LENGTH_SHORT).show();
                        characterInput.inventory.heldItems[slot] = new NonEquipableItem(1, "BLANKITEM", 1, 1, 1, "BLANKITEM", 0, R.drawable.blank_item);
                        generateInventoryImages();
                    }
                }
            }
        });
        b01.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if (!characterInput.inventory.heldItems[1].getItemName().equals("BLANKITEM")) {
                    if (characterInput.inventory.heldItems[1].getIsEquipable())
                    {
                        //equip the item
                        itemEquipped((EquipableItem) characterInput.inventory.heldItems[1], 1);
                    }
                    else
                    {
                        int slot = 1;
                        if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("health"))
                        {
                            characterInput.addHealth((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("strength"))
                        {
                            characterInput.addStrength((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("intellect"))
                        {
                            characterInput.addIntellect((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("agility"))
                        {
                            characterInput.addAgility((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        Toast.makeText(getContext(), ((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType() + " increased by " + ((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue(), Toast.LENGTH_SHORT).show();
                        characterInput.inventory.heldItems[slot] = new NonEquipableItem(1, "BLANKITEM", 1, 1, 1, "BLANKITEM", 0, R.drawable.blank_item);
                        generateInventoryImages();
                    }
                }
            }
        });
        b02.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if (!characterInput.inventory.heldItems[2].getItemName().equals("BLANKITEM")) {
                    if (characterInput.inventory.heldItems[2].getIsEquipable())
                    {
                        //equip the item
                        itemEquipped((EquipableItem) characterInput.inventory.heldItems[2], 2);
                    }
                    else
                    {
                        int slot = 2;
                        if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("health"))
                        {
                            characterInput.addHealth((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("strength"))
                        {
                            characterInput.addStrength((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("intellect"))
                        {
                            characterInput.addIntellect((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("agility"))
                        {
                            characterInput.addAgility((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        Toast.makeText(getContext(), ((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType() + " increased by " + ((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue(), Toast.LENGTH_SHORT).show();
                        characterInput.inventory.heldItems[slot] = new NonEquipableItem(1, "BLANKITEM", 1, 1, 1, "BLANKITEM", 0, R.drawable.blank_item);
                        generateInventoryImages();
                    }
                }
            }
        });
        b03.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if (!characterInput.inventory.heldItems[3].getItemName().equals("BLANKITEM")) {
                    if (characterInput.inventory.heldItems[3].getIsEquipable())
                    {
                        //equip the item
                        itemEquipped((EquipableItem) characterInput.inventory.heldItems[3], 3);
                    }
                    else
                    {
                        int slot = 3;
                        if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("health"))
                        {
                            characterInput.addHealth((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("strength"))
                        {
                            characterInput.addStrength((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("intellect"))
                        {
                            characterInput.addIntellect((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("agility"))
                        {
                            characterInput.addAgility((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        Toast.makeText(getContext(), ((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType() + " increased by " + ((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue(), Toast.LENGTH_SHORT).show();
                        characterInput.inventory.heldItems[slot] = new NonEquipableItem(1, "BLANKITEM", 1, 1, 1, "BLANKITEM", 0, R.drawable.blank_item);
                        generateInventoryImages();
                    }
                }
            }
        });
        b04.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if (!characterInput.inventory.heldItems[4].getItemName().equals("BLANKITEM")) {
                    if (characterInput.inventory.heldItems[4].getIsEquipable())
                    {
                        //equip the item
                        itemEquipped((EquipableItem) characterInput.inventory.heldItems[4], 4);
                    }
                    else
                    {
                        int slot = 4;
                        if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("health"))
                        {
                            characterInput.addHealth((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("strength"))
                        {
                            characterInput.addStrength((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("intellect"))
                        {
                            characterInput.addIntellect((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("agility"))
                        {
                            characterInput.addAgility((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        Toast.makeText(getContext(), ((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType() + " increased by " + ((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue(), Toast.LENGTH_SHORT).show();
                        characterInput.inventory.heldItems[slot] = new NonEquipableItem(1, "BLANKITEM", 1, 1, 1, "BLANKITEM", 0, R.drawable.blank_item);
                        generateInventoryImages();
                    }
                }
            }
        });
        b10.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if (!characterInput.inventory.heldItems[5].getItemName().equals("BLANKITEM")) {
                    if (characterInput.inventory.heldItems[5].getIsEquipable())
                    {
                        //equip the item
                        itemEquipped((EquipableItem) characterInput.inventory.heldItems[5], 5);
                    }
                    else
                    {
                        int slot = 5;
                        if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("health"))
                        {
                            characterInput.addHealth((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("strength"))
                        {
                            characterInput.addStrength((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("intellect"))
                        {
                            characterInput.addIntellect((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("agility"))
                        {
                            characterInput.addAgility((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        Toast.makeText(getContext(), ((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType() + " increased by " + ((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue(), Toast.LENGTH_SHORT).show();
                        characterInput.inventory.heldItems[slot] = new NonEquipableItem(1, "BLANKITEM", 1, 1, 1, "BLANKITEM", 0, R.drawable.blank_item);
                        generateInventoryImages();
                    }
                }
            }
        });
        b11.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if (!characterInput.inventory.heldItems[6].getItemName().equals("BLANKITEM")) {
                    if (characterInput.inventory.heldItems[6].getIsEquipable())
                    {
                        //equip the item
                        itemEquipped((EquipableItem) characterInput.inventory.heldItems[6], 6);
                    }
                    else
                    {
                        int slot = 6;
                        if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("health"))
                        {
                            characterInput.addHealth((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("strength"))
                        {
                            characterInput.addStrength((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("intellect"))
                        {
                            characterInput.addIntellect((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("agility"))
                        {
                            characterInput.addAgility((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        Toast.makeText(getContext(), ((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType() + " increased by " + ((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue(), Toast.LENGTH_SHORT).show();
                        characterInput.inventory.heldItems[slot] = new NonEquipableItem(1, "BLANKITEM", 1, 1, 1, "BLANKITEM", 0, R.drawable.blank_item);
                        generateInventoryImages();
                    }
                }
            }
        });
        b12.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if (!characterInput.inventory.heldItems[7].getItemName().equals("BLANKITEM")) {
                    if (characterInput.inventory.heldItems[7].getIsEquipable())
                    {
                        //equip the item
                        itemEquipped((EquipableItem) characterInput.inventory.heldItems[7], 7);
                    }
                    else
                    {
                        int slot = 7;
                        if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("health"))
                        {
                            characterInput.addHealth((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("strength"))
                        {
                            characterInput.addStrength((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("intellect"))
                        {
                            characterInput.addIntellect((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("agility"))
                        {
                            characterInput.addAgility((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        Toast.makeText(getContext(), ((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType() + " increased by " + ((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue(), Toast.LENGTH_SHORT).show();
                        characterInput.inventory.heldItems[slot] = new NonEquipableItem(1, "BLANKITEM", 1, 1, 1, "BLANKITEM", 0, R.drawable.blank_item);
                        generateInventoryImages();
                    }
                }
            }
        });
        b13.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if (!characterInput.inventory.heldItems[8].getItemName().equals("BLANKITEM")) {
                    if (characterInput.inventory.heldItems[8].getIsEquipable())
                    {
                        //equip the item
                        itemEquipped((EquipableItem) characterInput.inventory.heldItems[8], 8);
                    }
                    else
                    {
                        int slot = 8;
                        if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("health"))
                        {
                            characterInput.addHealth((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("strength"))
                        {
                            characterInput.addStrength((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("intellect"))
                        {
                            characterInput.addIntellect((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("agility"))
                        {
                            characterInput.addAgility((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        Toast.makeText(getContext(), ((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType() + " increased by " + ((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue(), Toast.LENGTH_SHORT).show();
                        characterInput.inventory.heldItems[slot] = new NonEquipableItem(1, "BLANKITEM", 1, 1, 1, "BLANKITEM", 0, R.drawable.blank_item);
                        generateInventoryImages();
                    }
                }
            }
        });
        b14.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if (!characterInput.inventory.heldItems[9].getItemName().equals("BLANKITEM")) {
                    if (characterInput.inventory.heldItems[9].getIsEquipable())
                    {
                        //equip the item
                        itemEquipped((EquipableItem) characterInput.inventory.heldItems[9], 9);
                    }
                    else
                    {
                        int slot = 9;
                        if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("health"))
                        {
                            characterInput.addHealth((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("strength"))
                        {
                            characterInput.addStrength((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("intellect"))
                        {
                            characterInput.addIntellect((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("agility"))
                        {
                            characterInput.addAgility((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        Toast.makeText(getContext(), ((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType() + " increased by " + ((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue(), Toast.LENGTH_SHORT).show();
                        characterInput.inventory.heldItems[slot] = new NonEquipableItem(1, "BLANKITEM", 1, 1, 1, "BLANKITEM", 0, R.drawable.blank_item);
                        generateInventoryImages();
                    }
                }
            }
        });
        b20.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if (!characterInput.inventory.heldItems[10].getItemName().equals("BLANKITEM")) {
                    if (characterInput.inventory.heldItems[10].getIsEquipable())
                    {
                        //equip the item
                        itemEquipped((EquipableItem) characterInput.inventory.heldItems[10], 10);
                    }
                    else
                    {
                        int slot = 10;
                        if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("health"))
                        {
                            characterInput.addHealth((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("strength"))
                        {
                            characterInput.addStrength((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("intellect"))
                        {
                            characterInput.addIntellect((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("agility"))
                        {
                            characterInput.addAgility((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        Toast.makeText(getContext(), ((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType() + " increased by " + ((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue(), Toast.LENGTH_SHORT).show();
                        characterInput.inventory.heldItems[slot] = new NonEquipableItem(1, "BLANKITEM", 1, 1, 1, "BLANKITEM", 0, R.drawable.blank_item);
                        generateInventoryImages();
                    }
                }
            }
        });
        b21.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if (!characterInput.inventory.heldItems[11].getItemName().equals("BLANKITEM")) {
                    if (characterInput.inventory.heldItems[11].getIsEquipable())
                    {
                        //equip the item
                        itemEquipped((EquipableItem) characterInput.inventory.heldItems[11], 11);
                    }
                    else
                    {
                        int slot = 11;
                        if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("health"))
                        {
                            characterInput.addHealth((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("strength"))
                        {
                            characterInput.addStrength((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("intellect"))
                        {
                            characterInput.addIntellect((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("agility"))
                        {
                            characterInput.addAgility((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        Toast.makeText(getContext(), ((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType() + " increased by " + ((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue(), Toast.LENGTH_SHORT).show();
                        characterInput.inventory.heldItems[slot] = new NonEquipableItem(1, "BLANKITEM", 1, 1, 1, "BLANKITEM", 0, R.drawable.blank_item);
                        generateInventoryImages();
                    }
                }
            }
        });
        b22.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if (!characterInput.inventory.heldItems[12].getItemName().equals("BLANKITEM")) {
                    if (characterInput.inventory.heldItems[12].getIsEquipable())
                    {
                        //equip the item
                        itemEquipped((EquipableItem) characterInput.inventory.heldItems[12], 12);
                    }
                    else
                    {
                        int slot = 12;
                        if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("health"))
                        {
                            characterInput.addHealth((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("strength"))
                        {
                            characterInput.addStrength((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("intellect"))
                        {
                            characterInput.addIntellect((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("agility"))
                        {
                            characterInput.addAgility((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        Toast.makeText(getContext(), ((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType() + " increased by " + ((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue(), Toast.LENGTH_SHORT).show();
                        characterInput.inventory.heldItems[slot] = new NonEquipableItem(1, "BLANKITEM", 1, 1, 1, "BLANKITEM", 0, R.drawable.blank_item);
                        generateInventoryImages();
                    }
                }
            }
        });
        b23.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if (!characterInput.inventory.heldItems[13].getItemName().equals("BLANKITEM")) {
                    if (characterInput.inventory.heldItems[13].getIsEquipable())
                    {
                        //equip the item
                        itemEquipped((EquipableItem) characterInput.inventory.heldItems[13], 13);
                    }
                    else
                    {
                        int slot = 13;
                        if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("health"))
                        {
                            characterInput.addHealth((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("strength"))
                        {
                            characterInput.addStrength((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("intellect"))
                        {
                            characterInput.addIntellect((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("agility"))
                        {
                            characterInput.addAgility((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        Toast.makeText(getContext(), ((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType() + " increased by " + ((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue(), Toast.LENGTH_SHORT).show();
                        characterInput.inventory.heldItems[slot] = new NonEquipableItem(1, "BLANKITEM", 1, 1, 1, "BLANKITEM", 0, R.drawable.blank_item);
                        generateInventoryImages();
                    }
                }
            }
        });
        b24.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if (!characterInput.inventory.heldItems[14].getItemName().equals("BLANKITEM")) {
                    if (characterInput.inventory.heldItems[14].getIsEquipable())
                    {
                        //equip the item
                        itemEquipped((EquipableItem) characterInput.inventory.heldItems[14], 14);
                    }
                    else
                    {
                        int slot = 14;
                        if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("health"))
                        {
                            characterInput.addHealth((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("strength"))
                        {
                            characterInput.addStrength((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("intellect"))
                        {
                            characterInput.addIntellect((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("agility"))
                        {
                            characterInput.addAgility((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        Toast.makeText(getContext(), ((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType() + " increased by " + ((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue(), Toast.LENGTH_SHORT).show();
                        characterInput.inventory.heldItems[slot] = new NonEquipableItem(1, "BLANKITEM", 1, 1, 1, "BLANKITEM", 0, R.drawable.blank_item);
                        generateInventoryImages();
                    }
                }
            }
        });
        b30.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if (!characterInput.inventory.heldItems[15].getItemName().equals("BLANKITEM")) {
                    if (characterInput.inventory.heldItems[15].getIsEquipable())
                    {
                        //equip the item
                        itemEquipped((EquipableItem) characterInput.inventory.heldItems[15], 15);
                    }
                    else
                    {
                        int slot = 15;
                        if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("health"))
                        {
                            characterInput.addHealth((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("strength"))
                        {
                            characterInput.addStrength((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("intellect"))
                        {
                            characterInput.addIntellect((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("agility"))
                        {
                            characterInput.addAgility((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        Toast.makeText(getContext(), ((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType() + " increased by " + ((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue(), Toast.LENGTH_SHORT).show();
                        characterInput.inventory.heldItems[slot] = new NonEquipableItem(1, "BLANKITEM", 1, 1, 1, "BLANKITEM", 0, R.drawable.blank_item);
                        generateInventoryImages();
                    }
                }
            }
        });
        b31.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if (!characterInput.inventory.heldItems[16].getItemName().equals("BLANKITEM")) {
                    if (characterInput.inventory.heldItems[16].getIsEquipable())
                    {
                        //equip the item
                        itemEquipped((EquipableItem) characterInput.inventory.heldItems[16], 16);
                    }
                    else
                    {
                        int slot = 16;
                        if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("health"))
                        {
                            characterInput.addHealth((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("strength"))
                        {
                            characterInput.addStrength((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("intellect"))
                        {
                            characterInput.addIntellect((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("agility"))
                        {
                            characterInput.addAgility((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        Toast.makeText(getContext(), ((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType() + " increased by " + ((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue(), Toast.LENGTH_SHORT).show();
                        characterInput.inventory.heldItems[slot] = new NonEquipableItem(1, "BLANKITEM", 1, 1, 1, "BLANKITEM", 0, R.drawable.blank_item);
                        generateInventoryImages();
                    }
                }
            }
        });
        b32.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if (!characterInput.inventory.heldItems[17].getItemName().equals("BLANKITEM")) {
                    if (characterInput.inventory.heldItems[17].getIsEquipable())
                    {
                        //equip the item
                        itemEquipped((EquipableItem) characterInput.inventory.heldItems[17], 17);
                    }
                    else
                    {
                        int slot = 17;
                        if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("health"))
                        {
                            characterInput.addHealth((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("strength"))
                        {
                            characterInput.addStrength((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("intellect"))
                        {
                            characterInput.addIntellect((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("agility"))
                        {
                            characterInput.addAgility((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        Toast.makeText(getContext(), ((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType() + " increased by " + ((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue(), Toast.LENGTH_SHORT).show();
                        characterInput.inventory.heldItems[slot] = new NonEquipableItem(1, "BLANKITEM", 1, 1, 1, "BLANKITEM", 0, R.drawable.blank_item);
                        generateInventoryImages();
                    }
                }
            }
        });
        b33.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if (!characterInput.inventory.heldItems[18].getItemName().equals("BLANKITEM")) {
                    if (characterInput.inventory.heldItems[18].getIsEquipable())
                    {
                        //equip the item
                        itemEquipped((EquipableItem) characterInput.inventory.heldItems[18], 18);
                    }
                    else
                    {
                        int slot = 18;
                        if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("health"))
                        {
                            characterInput.addHealth((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("strength"))
                        {
                            characterInput.addStrength((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("intellect"))
                        {
                            characterInput.addIntellect((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("agility"))
                        {
                            characterInput.addAgility((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        Toast.makeText(getContext(), ((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType() + " increased by " + ((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue(), Toast.LENGTH_SHORT).show();
                        characterInput.inventory.heldItems[slot] = new NonEquipableItem(1, "BLANKITEM", 1, 1, 1, "BLANKITEM", 0, R.drawable.blank_item);
                        generateInventoryImages();
                    }
                }
            }
        });
        b34.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if (!characterInput.inventory.heldItems[19].getItemName().equals("BLANKITEM")) {
                    if (characterInput.inventory.heldItems[19].getIsEquipable())
                    {
                        //equip the item
                        //characterInput.equipItem(characterInput.inventory.heldItems[19], characterInput.inventory.heldItems[19].getSlot(), 19);
                        itemEquipped((EquipableItem) characterInput.inventory.heldItems[19], 19);

                    }
                    else
                    {
                        int slot = 19;
                        if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("health"))
                        {
                            characterInput.addHealth((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("strength"))
                        {
                            characterInput.addStrength((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("intellect"))
                        {
                            characterInput.addIntellect((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        else if (((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType().equals("agility"))
                        {
                            characterInput.addAgility((int)((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue());
                        }
                        Toast.makeText(getContext(), ((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealType() + " increased by " + ((NonEquipableItem)characterInput.inventory.heldItems[slot]).getHealValue(), Toast.LENGTH_SHORT).show();
                        characterInput.inventory.heldItems[slot] = new NonEquipableItem(1, "BLANKITEM", 1, 1, 1, "BLANKITEM", 0, R.drawable.blank_item);
                        generateInventoryImages();
                    }
                }
            }
        });
        hand.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View view)
           {
               if (!characterInput.inventory.equippedItems[0].getItemName().equals("BLANKITEM"))
               {
                   itemUnEquipped((EquipableItem) characterInput.inventory.equippedItems[0]);
               }
           }
        });
        offhand.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if (!characterInput.inventory.equippedItems[1].getItemName().equals("BLANKITEM"))
                {
                    itemUnEquipped((EquipableItem) characterInput.inventory.equippedItems[1]);
                }
            }
        });
        head.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if (!characterInput.inventory.equippedItems[2].getItemName().equals("BLANKITEM"))
                {
                    itemUnEquipped((EquipableItem) characterInput.inventory.equippedItems[2]);
                }
            }
        });
        chest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if (!characterInput.inventory.equippedItems[3].getItemName().equals("BLANKITEM"))
                {
                    itemUnEquipped((EquipableItem) characterInput.inventory.equippedItems[3]);
                }
            }
        });
        arms.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if (!characterInput.inventory.equippedItems[4].getItemName().equals("BLANKITEM"))
                {
                    itemUnEquipped((EquipableItem) characterInput.inventory.equippedItems[4]);
                }
            }
        });
        legs.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if (!characterInput.inventory.equippedItems[5].getItemName().equals("BLANKITEM"))
                {
                    itemUnEquipped((EquipableItem) characterInput.inventory.equippedItems[5]);
                }
            }
        });
        exitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                onDetach();
            }
        });
        generateInventoryImages();
        //Toast.makeText(getContext(), "" + characterInput.inventory.equippedItems[0].getItemName(), Toast.LENGTH_SHORT).show();
        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {

        mListener.onFragmentInteraction(statOffset);
        super.onDetach();
        mListener = null;
    }

    /**
     * Method used by an Inventory Menu to change the inventory of its modeled character object in response to equipping an item
     * @param item item to be equipped
     * @param gridSlot slot in which to equip the item
     */
    public void itemEquipped(EquipableItem item, int gridSlot)
    {
        boolean couldEquip = false;
        if (item.getLevelRequirement() > characterInput.charLevel)
        {
            Toast.makeText(getContext(), "Insufficient Level to Equip!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (item.getTypeSlot().equals("hand"))
        {
            couldEquip = characterInput.equipItem(item, 0, gridSlot);
        }
        else if (item.getTypeSlot().equals("offhand"))
        {
            couldEquip = characterInput.equipItem(item, 1, gridSlot);
        }
        else if (item.getTypeSlot().equals("head"))
        {
            couldEquip = characterInput.equipItem(item, 2, gridSlot);
        }
        else if (item.getTypeSlot().equals("chest"))
        {
            couldEquip = characterInput.equipItem(item, 3, gridSlot);
        }
        else if (item.getTypeSlot().equals("arms"))
        {
            couldEquip = characterInput.equipItem(item, 4, gridSlot);
        }
        else if (item.getTypeSlot().equals("legs"))
        {
            couldEquip = characterInput.equipItem(item, 5, gridSlot);
        }
        if (couldEquip)
        {
            int healthChange = 0, strengthChange = 0, intellectChange = 0, agilityChange = 0;
            double armorVal, weaponVal;
            armorVal = item.returnArmorValue();
            weaponVal = item.returnWeaponValue();
            if (item.returnDamageType().equals("melee"))
            {
                healthChange = (int)(armorVal);
                strengthChange = (int)(weaponVal);
            }
            else if (item.returnDamageType().equals("magic"))
            {
                healthChange = (int)(armorVal);
                intellectChange = (int)(weaponVal);
            }
            else if (item.returnDamageType().equals("stealth"))
            {
                healthChange = (int)(armorVal);
                agilityChange = (int)(weaponVal);
            }
            healthOffset += healthChange;
            strengthOffset += strengthChange;
            intellectOffset += intellectChange;
            agilityOffset += agilityChange;
            int health = characterInput.getHealth() + healthOffset;
            int strength = characterInput.getStrength() + strengthOffset;
            int intellect = characterInput.getIntellect() + intellectOffset;
            int agility = characterInput.getAgility() + agilityOffset;
            mHPTV.setText("HP: " + health);
            mSTRTV.setText("STR: " + strength);
            mINTTV.setText("INT: " + intellect);
            mAGITV.setText("AGI: " + agility);
            statOffset = healthOffset + ":" + strengthOffset + ":" + intellectOffset + ":" + agilityOffset + ":" + characterInput.inventory.serialize();
            generateInventoryImages();
        }
        else
        {
            Toast.makeText(getContext(), "Slot Already Equipped!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Method used by an Inventory Menu to change the inventory of its modeled character object in response to unequipping an item
     * @param item item to be unequipped
     */
    public void itemUnEquipped(EquipableItem item)
    {
        boolean couldRemove = false;
        if (item.getTypeSlot().equals("hand"))
        {
            couldRemove = characterInput.unEquipItem(0);
        }
        else if (item.getTypeSlot().equals("offhand"))
        {
            couldRemove = characterInput.unEquipItem(1);
        }
        else if (item.getTypeSlot().equals("head"))
        {
            couldRemove = characterInput.unEquipItem(2);
        }
        else if (item.getTypeSlot().equals("chest"))
        {
            couldRemove = characterInput.unEquipItem(3);
        }
        else if (item.getTypeSlot().equals("arms"))
        {
            couldRemove = characterInput.unEquipItem(4);
        }
        else if (item.getTypeSlot().equals("legs"))
        {
            couldRemove = characterInput.unEquipItem(5);
        }
        if (couldRemove)
        {
            int healthChange = 0, strengthChange = 0, intellectChange = 0, agilityChange = 0;
            double armorVal, weaponVal;
            armorVal = item.returnArmorValue();
            weaponVal = item.returnWeaponValue();
            if (item.returnDamageType().equals("melee"))
            {
                healthChange = (int)(armorVal);
                strengthChange = (int)(weaponVal);
            }
            else if (item.returnDamageType().equals("magic"))
            {
                healthChange = (int)(armorVal);
                intellectChange = (int)(weaponVal);
            }
            else if (item.returnDamageType().equals("stealth"))
            {
                healthChange = (int)(armorVal);
                agilityChange = (int)(weaponVal);
            }
            healthOffset -= healthChange;
            strengthOffset -= strengthChange;
            intellectOffset -= intellectChange;
            agilityOffset -= agilityChange;
            int health = characterInput.getHealth() + healthOffset;
            int strength = characterInput.getStrength() + strengthOffset;
            int intellect = characterInput.getIntellect() + intellectOffset;
            int agility = characterInput.getAgility() + agilityOffset;
            mHPTV.setText("HP: " + health);
            mSTRTV.setText("STR: " + strength);
            mINTTV.setText("INT: " + intellect);
            mAGITV.setText("AGI: " + agility);
            statOffset = healthOffset + ":" + strengthOffset + ":" + intellectOffset + ":" + agilityOffset + ":" + characterInput.inventory.serialize();
            generateInventoryImages();
        }
        else
        {
            Toast.makeText(getContext(), "Inventory is Full!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Updates all of the images displayed on an Inventory Menu to the current contents of the Inventory object. Used whenever the contents of the inventory are modified.
     */
    public void generateInventoryImages()
    {
        hand.setBackgroundResource(characterInput.inventory.equippedItems[0].getItemD());
        offhand.setBackgroundResource(characterInput.inventory.equippedItems[1].getItemD());
        head.setBackgroundResource(characterInput.inventory.equippedItems[2].getItemD());
        chest.setBackgroundResource(characterInput.inventory.equippedItems[3].getItemD());
        arms.setBackgroundResource(characterInput.inventory.equippedItems[4].getItemD());
        legs.setBackgroundResource(characterInput.inventory.equippedItems[5].getItemD());

        b00.setBackgroundResource(characterInput.inventory.heldItems[0].getItemD());
        b01.setBackgroundResource(characterInput.inventory.heldItems[1].getItemD());
        b02.setBackgroundResource(characterInput.inventory.heldItems[2].getItemD());
        b03.setBackgroundResource(characterInput.inventory.heldItems[3].getItemD());
        b04.setBackgroundResource(characterInput.inventory.heldItems[4].getItemD());
        b10.setBackgroundResource(characterInput.inventory.heldItems[5].getItemD());
        b11.setBackgroundResource(characterInput.inventory.heldItems[6].getItemD());
        b12.setBackgroundResource(characterInput.inventory.heldItems[7].getItemD());
        b13.setBackgroundResource(characterInput.inventory.heldItems[8].getItemD());
        b14.setBackgroundResource(characterInput.inventory.heldItems[9].getItemD());
        b20.setBackgroundResource(characterInput.inventory.heldItems[10].getItemD());
        b21.setBackgroundResource(characterInput.inventory.heldItems[11].getItemD());
        b22.setBackgroundResource(characterInput.inventory.heldItems[12].getItemD());
        b23.setBackgroundResource(characterInput.inventory.heldItems[13].getItemD());
        b24.setBackgroundResource(characterInput.inventory.heldItems[14].getItemD());
        b30.setBackgroundResource(characterInput.inventory.heldItems[15].getItemD());
        b31.setBackgroundResource(characterInput.inventory.heldItems[16].getItemD());
        b32.setBackgroundResource(characterInput.inventory.heldItems[17].getItemD());
        b33.setBackgroundResource(characterInput.inventory.heldItems[18].getItemD());
        b34.setBackgroundResource(characterInput.inventory.heldItems[19].getItemD());

        if (characterInput.inventory.heldItems[0].getItemName().equals("BLANKITEM"))
        {
            b00.setText("");
        }
        else
        {
            b00.setText(characterInput.inventory.heldItems[0].getItemName() + "\nLevel: " + ((EquipableItem)characterInput.inventory.heldItems[0]).getLevelRequirement());
        }
        if (characterInput.inventory.heldItems[1].getItemName().equals("BLANKITEM"))
        {
            b01.setText("");
        }
        else
        {
            b01.setText(characterInput.inventory.heldItems[1].getItemName() + "\nLevel: " + ((EquipableItem)characterInput.inventory.heldItems[1]).getLevelRequirement());
        }
        if (characterInput.inventory.heldItems[2].getItemName().equals("BLANKITEM"))
        {
            b02.setText("");
        }
        else
        {
            b02.setText(characterInput.inventory.heldItems[2].getItemName() + "\nLevel: " + ((EquipableItem)characterInput.inventory.heldItems[2]).getLevelRequirement());
        }
        if (characterInput.inventory.heldItems[3].getItemName().equals("BLANKITEM"))
        {
            b03.setText("");
        }
        else
        {
            b03.setText(characterInput.inventory.heldItems[3].getItemName() + "\nLevel: " + ((EquipableItem)characterInput.inventory.heldItems[3]).getLevelRequirement());
        }
        if (characterInput.inventory.heldItems[4].getItemName().equals("BLANKITEM"))
        {
            b04.setText("");
        }
        else
        {
            b04.setText(characterInput.inventory.heldItems[4].getItemName() + "\nLevel: " + ((EquipableItem)characterInput.inventory.heldItems[4]).getLevelRequirement());
        }
        if (characterInput.inventory.heldItems[5].getItemName().equals("BLANKITEM"))
        {
            b10.setText("");
        }
        else
        {
            b10.setText(characterInput.inventory.heldItems[5].getItemName() + "\nLevel: " + ((EquipableItem)characterInput.inventory.heldItems[5]).getLevelRequirement());
        }
        if (characterInput.inventory.heldItems[6].getItemName().equals("BLANKITEM"))
        {
            b11.setText("");
        }
        else
        {
            b11.setText(characterInput.inventory.heldItems[6].getItemName() + "\nLevel: " + ((EquipableItem)characterInput.inventory.heldItems[6]).getLevelRequirement());
        }
        if (characterInput.inventory.heldItems[7].getItemName().equals("BLANKITEM"))
        {
            b12.setText("");
        }
        else
        {
            b12.setText(characterInput.inventory.heldItems[7].getItemName() + "\nLevel: " + ((EquipableItem)characterInput.inventory.heldItems[7]).getLevelRequirement());
        }
        if (characterInput.inventory.heldItems[8].getItemName().equals("BLANKITEM"))
        {
            b13.setText("");
        }
        else
        {
            b13.setText(characterInput.inventory.heldItems[8].getItemName() + "\nLevel: " + ((EquipableItem)characterInput.inventory.heldItems[8]).getLevelRequirement());
        }
        if (characterInput.inventory.heldItems[9].getItemName().equals("BLANKITEM"))
        {
            b14.setText("");
        }
        else
        {
            b14.setText(characterInput.inventory.heldItems[9].getItemName() + "\nLevel: " + ((EquipableItem)characterInput.inventory.heldItems[9]).getLevelRequirement());
        }
        if (characterInput.inventory.heldItems[10].getItemName().equals("BLANKITEM"))
        {
            b20.setText("");
        }
        else
        {
            b20.setText(characterInput.inventory.heldItems[10].getItemName() + "\nLevel: " + ((EquipableItem)characterInput.inventory.heldItems[10]).getLevelRequirement());
        }
        if (characterInput.inventory.heldItems[11].getItemName().equals("BLANKITEM"))
        {
            b21.setText("");
        }
        else
        {
            b21.setText(characterInput.inventory.heldItems[11].getItemName() + "\nLevel: " + ((EquipableItem)characterInput.inventory.heldItems[11]).getLevelRequirement());
        }
        if (characterInput.inventory.heldItems[12].getItemName().equals("BLANKITEM"))
        {
            b22.setText("");
        }
        else
        {
            b22.setText(characterInput.inventory.heldItems[12].getItemName() + "\nLevel: " + ((EquipableItem)characterInput.inventory.heldItems[12]).getLevelRequirement());
        }
        if (characterInput.inventory.heldItems[13].getItemName().equals("BLANKITEM"))
        {
            b23.setText("");
        }
        else
        {
            b23.setText(characterInput.inventory.heldItems[13].getItemName() + "\nLevel: " + ((EquipableItem)characterInput.inventory.heldItems[13]).getLevelRequirement());
        }
        if (characterInput.inventory.heldItems[14].getItemName().equals("BLANKITEM"))
        {
            b24.setText("");
        }
        else
        {
            b24.setText(characterInput.inventory.heldItems[14].getItemName() + "\nLevel: " + ((EquipableItem)characterInput.inventory.heldItems[14]).getLevelRequirement());
        }
        if (characterInput.inventory.heldItems[15].getItemName().equals("BLANKITEM"))
        {
            b30.setText("");
        }
        else
        {
            b30.setText(characterInput.inventory.heldItems[15].getItemName() + "\nLevel: " + ((EquipableItem)characterInput.inventory.heldItems[15]).getLevelRequirement());
        }
        if (characterInput.inventory.heldItems[16].getItemName().equals("BLANKITEM"))
        {
            b31.setText("");
        }
        else
        {
            b31.setText(characterInput.inventory.heldItems[16].getItemName() + "\nLevel: " + ((EquipableItem)characterInput.inventory.heldItems[16]).getLevelRequirement());
        }
        if (characterInput.inventory.heldItems[17].getItemName().equals("BLANKITEM"))
        {
            b32.setText("");
        }
        else
        {
            b32.setText(characterInput.inventory.heldItems[17].getItemName() + "\nLevel: " + ((EquipableItem)characterInput.inventory.heldItems[17]).getLevelRequirement());
        }
        if (characterInput.inventory.heldItems[18].getItemName().equals("BLANKITEM"))
        {
            b33.setText("");
        }
        else
        {
            b33.setText(characterInput.inventory.heldItems[18].getItemName() + "\nLevel: " + ((EquipableItem)characterInput.inventory.heldItems[18]).getLevelRequirement());
        }
        if (characterInput.inventory.heldItems[19].getItemName().equals("BLANKITEM"))
        {
            b34.setText("");
        }
        else
        {
            b34.setText(characterInput.inventory.heldItems[19].getItemName() + "\nLevel: " + ((EquipableItem)characterInput.inventory.heldItems[19]).getLevelRequirement());
        }
    }



    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(String statOffset);
    }
}
