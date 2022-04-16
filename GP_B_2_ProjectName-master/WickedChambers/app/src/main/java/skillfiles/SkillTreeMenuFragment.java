package skillfiles;

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
 * {@link SkillTreeMenuFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SkillTreeMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SkillTreeMenuFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private String statOffset, introID;
    private String mCharacterInput;
    private CharCreate characterInput;
    private OnFragmentInteractionListener mListener;
    private Skill selectedSkill;
    private TextView skillDetails, levelDetails;
    private SkillTree localTree;

    private int mHP, mSTR, mINT, mAGI, mLVL, mEXP;
    private int mHPO, mSTRO, mINTO, mAGIO, mLVLO, mEXPO;

    private Button s0, s1, s2, s3, s4, s5, s6, s7, s8, unlockSkill, exitButton;



    public SkillTreeMenuFragment() {
        // Required empty public constructor
    }

    /**
     * Creates a new instance of a SkillTree Menu using character input
     *
     * @param input Character on which to base the displayed SkillTree
     * @return A new instance of fragment SkillTreeMenuFragment.
     */
    public static SkillTreeMenuFragment newInstance(CharCreate input) {
        SkillTreeMenuFragment fragment = new SkillTreeMenuFragment();
        Bundle args = new Bundle();
        String characterInput = input.serialize();
        args.putString(ARG_PARAM1, characterInput);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Retrieves all of the values necessary to create a SkillTree menu
     * @param savedInstanceState saved data for the application
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCharacterInput = getArguments().getString(ARG_PARAM1);
        }
        characterInput = CharCreate.deserialize(mCharacterInput);
        mHP = characterInput.getHealth();
        mSTR = characterInput.getStrength();
        mINT = characterInput.getIntellect();
        mAGI = characterInput.getAgility();
        mLVL = characterInput.getCharLevel();
        mEXP = characterInput.getCharXP();
        mHPO = 0;
        mSTRO = 0;
        mINTO = 0;
        mAGIO = 0;
        mLVLO = 0;
        mEXPO = 0;

        statOffset = "0:0:0:0:0:0:";
        statOffset += characterInput.skillTree.serialize();
    }

    /**
     * Creates the display for a SkillTree menu. This creates all of the buttons seen on the screen as well as gives each button functionality for selecting and
     * unlocking skills, as well as reading in stat information (levels and experience points)
     * @param inflater layout displayed in the fragment
     * @param container container to hold the fragment
     * @param savedInstanceState saved application data
     * @return view for this menu
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_skill_tree_menu, container, false);
        exitButton = (Button)v.findViewById(R.id.exitMenuButton);
        s0 = (Button)v.findViewById(R.id.s0);
        s1 = (Button)v.findViewById(R.id.s1);
        s2 = (Button)v.findViewById(R.id.s2);
        s3 = (Button)v.findViewById(R.id.s3);
        s4 = (Button)v.findViewById(R.id.s4);
        s5 = (Button)v.findViewById(R.id.s5);
        s6 = (Button)v.findViewById(R.id.s6);
        s7 = (Button)v.findViewById(R.id.s7);
        s8 = (Button)v.findViewById(R.id.s8);
        skillDetails = (TextView)v.findViewById(R.id.skillDetails);
        levelDetails = (TextView)v.findViewById(R.id.levelData);
        unlockSkill = (Button)v.findViewById(R.id.unlockSkill);
        localTree = characterInput.skillTree;
        introID = "";
        if (characterInput.getType().equals("Warrior"))
        {
            introID = "WR_";
        }
        else if (characterInput.getType().equals("Mage"))
        {
            introID = "MG_";
        }
        else if (characterInput.getType().equals("Rogue"))
        {
            introID = "RG_";
        }
        String data0[] = localTree.findSkill(introID + "00").getData().getDescription().split(":");
        String zeroT = data0[0];
        String data1[] = localTree.findSkill(introID + "01").getData().getDescription().split(":");
        String oneT = data1[0];
        String data2[] = localTree.findSkill(introID + "02").getData().getDescription().split(":");
        String twoT = data2[0];
        String data3[] = localTree.findSkill(introID + "03").getData().getDescription().split(":");
        String threeT = data3[0];
        String data4[] = localTree.findSkill(introID + "04").getData().getDescription().split(":");
        String fourT = data4[0];
        String data5[] = localTree.findSkill(introID + "05").getData().getDescription().split(":");
        String fiveT = data5[0];
        String data6[] = localTree.findSkill(introID + "06").getData().getDescription().split(":");
        String sixT = data6[0];
        String data7[] = localTree.findSkill(introID + "07").getData().getDescription().split(":");
        String sevenT = data7[0];
        String data8[] = localTree.findSkill(introID + "08").getData().getDescription().split(":");
        String eightT = data8[0];
        s0.setText(zeroT);
        s1.setText(oneT);
        s2.setText(twoT);
        s3.setText(threeT);
        s4.setText(fourT);
        s5.setText(fiveT);
        s6.setText(sixT);
        s7.setText(sevenT);
        s8.setText(eightT);
        generateSkillImages();
        selectedSkill = characterInput.skillTree.findSkill(introID + "00").getData();
        s0.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                selectedSkill = localTree.findSkill(introID + "00").getData();
                skillDetails.setText(selectedSkill.getDescription());
            }
        });
        s1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                selectedSkill = localTree.findSkill(introID + "01").getData();
                skillDetails.setText(selectedSkill.getDescription());
            }
        });
        s2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                selectedSkill = localTree.findSkill(introID + "02").getData();
                skillDetails.setText(selectedSkill.getDescription());
            }
        });
        s3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                selectedSkill = localTree.findSkill(introID + "03").getData();
                skillDetails.setText(selectedSkill.getDescription());
            }
        });
        s4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                selectedSkill = localTree.findSkill(introID + "04").getData();
                skillDetails.setText(selectedSkill.getDescription());
            }
        });
        s5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                selectedSkill = localTree.findSkill(introID + "05").getData();
                skillDetails.setText(selectedSkill.getDescription());
            }
        });
        s6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                selectedSkill = localTree.findSkill(introID + "06").getData();
                skillDetails.setText(selectedSkill.getDescription());
            }
        });
        s7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                selectedSkill = localTree.findSkill(introID + "07").getData();
                skillDetails.setText(selectedSkill.getDescription());
            }
        });
        s8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                selectedSkill = localTree.findSkill(introID + "08").getData();
                skillDetails.setText(selectedSkill.getDescription());
            }
        });
        unlockSkill.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if (characterInput.getCharXP() > 0)
                {
                    boolean unlocked = characterInput.skillTree.unlockNode(selectedSkill.getID());
                    if (unlocked)
                    {
                        Toast.makeText(getContext(), "Leveled Up!", Toast.LENGTH_SHORT).show();
                        characterInput.addHealth(selectedSkill.getHP());
                        characterInput.addStrength(selectedSkill.getSTR());
                        characterInput.addIntellect(selectedSkill.getINT());
                        characterInput.addAgility(selectedSkill.getAGI());
                        characterInput.addCharLevel(1);
                        characterInput.addCharXP(-1);
                        mHP = characterInput.getHealth();
                        mSTR = characterInput.getStrength();
                        mINT = characterInput.getIntellect();
                        mAGI = characterInput.getAgility();
                        mLVL = characterInput.getCharLevel();
                        mEXP = characterInput.getCharXP();
                        levelDetails.setText("Level: " + mLVL + "\nXP Points to Spend: " + mEXP);
                        generateSkillImages();
                    }
                    else if (!characterInput.skillTree.findSkill(selectedSkill.getID()).getParentSkillTree().getData().getUnlockedValue())
                    {
                        Toast.makeText(getContext(), "Skill not yet Available!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Toast.makeText(getContext(), "Skill Already Unlocked!", Toast.LENGTH_SHORT).show();
                    }
                }
                else if (characterInput.skillTree.findSkill(selectedSkill.getID()).getData().skillIsUnlocked)
                {
                    Toast.makeText(getContext(), "Skill Already Unlocked!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getContext(), "Insufficient EXP to level up!", Toast.LENGTH_SHORT).show();
                }
                generateSkillImages();
            }
        });
        exitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                onDetach();
            }
        });

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

    /**
     * Generates and updates the images for all of the skills in the given SkillTree. Unlocked skills are denoted as a different
     * color than locked skills. The color for unlocked skills depends on the class of the given character (Red for Warrior, Aqua for
     * Mage and Green for Rogue). This method is to be used to update images whenever the contents of the given SkillTree object are
     * modified.
     */
    public void generateSkillImages()
    {
        boolean b0, b1, b2, b3, b4, b5, b6, b7, b8;
        String data[] = characterInput.skillTree.serialize().split("z");
        levelDetails.setText("Level: " + mLVL + "\nXP Points to Spend: " + mEXP);
        if (Integer.parseInt(data[1]) == 1)
        {
            b0 = true;
            b1 = true;
            b2 = false;
            b3 = false;
            b4 = false;
            b5 = false;
            b6 = false;
            b7 = false;
            b8 = false;
        }
        else if (Integer.parseInt(data[1]) == 2)
        {
            b0 = true;
            b1 = true;
            b2 = true;
            b3 = false;
            b4 = false;
            b5 = false;
            b6 = false;
            b7 = false;
            b8 = false;
        }
        else if (Integer.parseInt(data[1]) == 3)
        {
            b0 = true;
            b1 = true;
            b2 = true;
            b3 = true;
            b4 = false;
            b5 = false;
            b6 = false;
            b7 = false;
            b8 = false;
        }
        else if (Integer.parseInt(data[1]) == 4)
        {
            b0 = true;
            b1 = true;
            b2 = true;
            b3 = true;
            b4 = true;
            b5 = false;
            b6 = false;
            b7 = false;
            b8 = false;
        }
        else if (Integer.parseInt(data[1]) == 5)
        {
            b0 = true;
            b1 = true;
            b2 = true;
            b3 = true;
            b4 = true;
            b5 = true;
            b6 = false;
            b7 = false;
            b8 = false;
        }
        else if (Integer.parseInt(data[1]) == 6)
        {
            b0 = true;
            b1 = true;
            b2 = true;
            b3 = false;
            b4 = false;
            b5 = false;
            b6 = true;
            b7 = false;
            b8 = false;
        }
        else if (Integer.parseInt(data[1]) == 7)
        {
            b0 = true;
            b1 = true;
            b2 = true;
            b3 = false;
            b4 = false;
            b5 = false;
            b6 = true;
            b7 = true;
            b8 = false;
        }
        else if (Integer.parseInt(data[1]) == 8)
        {
            b0 = true;
            b1 = true;
            b2 = true;
            b3 = false;
            b4 = false;
            b5 = false;
            b6 = true;
            b7 = true;
            b8 = true;
        }
        else
        {
            b0 = true;
            b1 = false;
            b2 = false;
            b3 = false;
            b4 = false;
            b5 = false;
            b6 = false;
            b7 = false;
            b8 = false;
        }
        if (b0)
        {
            if (characterInput.getType().equals("Warrior"))
            {
                s0.setBackgroundColor(getResources().getColor(R.color.red));
                s0.setTextColor(getResources().getColor(R.color.black));
            }
            else if (characterInput.getType().equals("Mage"))
            {
                s0.setBackgroundColor(getResources().getColor(R.color.aqua));
            }
            else if (characterInput.getType().equals("Rogue"))
            {
                s0.setBackgroundColor(getResources().getColor(R.color.green));
            }
        }
        if (b1)
        {
            if (characterInput.getType().equals("Warrior"))
            {
                s1.setBackgroundColor(getResources().getColor(R.color.red));
                s1.setTextColor(getResources().getColor(R.color.black));
            }
            else if (characterInput.getType().equals("Mage"))
            {
                s1.setBackgroundColor(getResources().getColor(R.color.aqua));
            }
            else if (characterInput.getType().equals("Rogue"))
            {
                s1.setBackgroundColor(getResources().getColor(R.color.green));
            }
        }
        if (b2)
        {
            if (characterInput.getType().equals("Warrior"))
            {
                s2.setBackgroundColor(getResources().getColor(R.color.red));
                s2.setTextColor(getResources().getColor(R.color.black));
            }
            else if (characterInput.getType().equals("Mage"))
            {
                s2.setBackgroundColor(getResources().getColor(R.color.aqua));
            }
            else if (characterInput.getType().equals("Rogue"))
            {
                s2.setBackgroundColor(getResources().getColor(R.color.green));
            }
        }
        if (b3)
        {
            if (characterInput.getType().equals("Warrior"))
            {
                s3.setBackgroundColor(getResources().getColor(R.color.red));
                s3.setTextColor(getResources().getColor(R.color.black));
            }
            else if (characterInput.getType().equals("Mage"))
            {
                s3.setBackgroundColor(getResources().getColor(R.color.aqua));
            }
            else if (characterInput.getType().equals("Rogue"))
            {
                s3.setBackgroundColor(getResources().getColor(R.color.green));
            }
        }
        if (b4)
        {
            if (characterInput.getType().equals("Warrior"))
            {
                s4.setBackgroundColor(getResources().getColor(R.color.red));
                s4.setTextColor(getResources().getColor(R.color.black));
            }
            else if (characterInput.getType().equals("Mage"))
            {
                s4.setBackgroundColor(getResources().getColor(R.color.aqua));
            }
            else if (characterInput.getType().equals("Rogue"))
            {
                s4.setBackgroundColor(getResources().getColor(R.color.green));
            }
        }
        if (b5)
        {
            if (characterInput.getType().equals("Warrior"))
            {
                s5.setBackgroundColor(getResources().getColor(R.color.red));
                s5.setTextColor(getResources().getColor(R.color.black));
            }
            else if (characterInput.getType().equals("Mage"))
            {
                s5.setBackgroundColor(getResources().getColor(R.color.aqua));
            }
            else if (characterInput.getType().equals("Rogue"))
            {
                s5.setBackgroundColor(getResources().getColor(R.color.green));
            }
        }
        if (b6)
        {
            if (characterInput.getType().equals("Warrior"))
            {
                s6.setBackgroundColor(getResources().getColor(R.color.red));
                s6.setTextColor(getResources().getColor(R.color.black));
            }
            else if (characterInput.getType().equals("Mage"))
            {
                s6.setBackgroundColor(getResources().getColor(R.color.aqua));
            }
            else if (characterInput.getType().equals("Rogue"))
            {
                s6.setBackgroundColor(getResources().getColor(R.color.green));
            }
        }
        if (b7)
        {
            if (characterInput.getType().equals("Warrior"))
            {
                s7.setBackgroundColor(getResources().getColor(R.color.red));
                s7.setTextColor(getResources().getColor(R.color.black));
            }
            else if (characterInput.getType().equals("Mage"))
            {
                s7.setBackgroundColor(getResources().getColor(R.color.aqua));
            }
            else if (characterInput.getType().equals("Rogue"))
            {
                s7.setBackgroundColor(getResources().getColor(R.color.green));
            }
        }
        if (b8)
        {
            if (characterInput.getType().equals("Warrior"))
            {
                s8.setBackgroundColor(getResources().getColor(R.color.red));
                s8.setTextColor(getResources().getColor(R.color.black));
            }
            else if (characterInput.getType().equals("Mage"))
            {
                s8.setBackgroundColor(getResources().getColor(R.color.aqua));
            }
            else if (characterInput.getType().equals("Rogue"))
            {
                s8.setBackgroundColor(getResources().getColor(R.color.green));
            }
        }

    }

    @Override
    public void onDetach() {
        mListener.onFragmentInteraction(characterInput);
        super.onDetach();
        mListener = null;
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
        void onFragmentInteraction(CharCreate character);
    }
}
