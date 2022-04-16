package mainmenu;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mlscholl.wickedchambers.R;
/**
*   Implements the CharacterValueFragment, which displays the stats and class of the player's character to them on the screen.
*
*
 */
public class CharacterValueFragment extends Fragment
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";
    private static final String ARG_PARAM4 = "param4";
    private static final String ARG_PARAM5 = "param5";
    private static final String ARG_PARAM6 = "param6";
    private static final String ARG_PARAM7 = "param7";

    private TextView myClassText;
    private TextView myHealthText;
    private TextView myStrengthText;
    private TextView myIntellectText;
    private TextView myAgilityText;
    private TextView myCharacterLevelText;
    private TextView myCharacterXPText;

    private ImageView charImage;

    // TODO: Rename and change types of parameters
    private String mClass;
    private int mHealth;
    private int mIntellect;
    private int mStrength;
    private int mAgility;
    private int mCharLevel;
    private int mCharXp;


    private OnFragmentInteractionListener mListener;

    public CharacterValueFragment() {
        // Required empty public constructor
    }

    /**
    *   Initializes a new instance of a CharacterValueFragment. Requires stats of the character pulled from the database to be generated.
    *
    *   @param mClass class of the given character
    *   @param health health stat of the given character
    *   @param strength strength stat of the given character
    *   @param intellect intellect stat of the given character
    *   @param agility agility stat of the given character
    *   @param charLevel level of the given character
    *   @param charXp experience of the given character
     */
    public static CharacterValueFragment newInstance(String mClass, int health, int strength, int intellect, int agility, int charLevel, int charXp) {
        CharacterValueFragment fragment = new CharacterValueFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, mClass);
        args.putInt(ARG_PARAM2, health);
        args.putInt(ARG_PARAM3, strength);
        args.putInt(ARG_PARAM4, intellect);
        args.putInt(ARG_PARAM5, agility);
        args.putInt(ARG_PARAM6, charLevel);
        args.putInt(ARG_PARAM7, charXp);

        fragment.setArguments(args);
        return fragment;
    }
    /**
    *   Required method of the Fragment class that details the initialization of the parameters required to create a CharacterValueFragment fragment.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mClass = getArguments().getString(ARG_PARAM1);
            mHealth = getArguments().getInt(ARG_PARAM2);
            mStrength = getArguments().getInt(ARG_PARAM3);
            mIntellect = getArguments().getInt(ARG_PARAM4);
            mAgility = getArguments().getInt(ARG_PARAM5);
            mCharLevel = getArguments().getInt(ARG_PARAM6);
            mCharXp = getArguments().getInt(ARG_PARAM7);
        }
    }
    /*
    *   Creates the fragment and connects to the relevant layout objects in the fragment_character_value.xml file=
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_character_value, container, false);
        myClassText = (TextView)v.findViewById(R.id.classText);
        myHealthText = (TextView)v.findViewById(R.id.healthText);
        myStrengthText = (TextView)v.findViewById(R.id.strengthText);
        myIntellectText = (TextView)v.findViewById(R.id.intellectText);
        myAgilityText = (TextView)v.findViewById(R.id.agilityText);
        myCharacterLevelText = (TextView)v.findViewById(R.id.charLevelText);
        myCharacterXPText = (TextView)v.findViewById(R.id.charXPText);
        charImage = (ImageView)v.findViewById(R.id.charImage);
        if (mClass.equals("m"))
        {
            mClass = "Mage";
            charImage.setImageDrawable(getResources().getDrawable(R.drawable.mage));
        }
        else if (mClass.equals("w"))
        {
            mClass = "Warrior";
            charImage.setImageDrawable(getResources().getDrawable(R.drawable.warrior));
        }
        else if (mClass.equals("r"))
        {
            mClass = "Rogue";
            charImage.setImageDrawable(getResources().getDrawable(R.drawable.rogue));
        }
        myClassText.setText("Class: " + mClass);
        myHealthText.setText("HP: " + mHealth);
        myStrengthText.setText("Strength: " + mStrength);
        myIntellectText.setText("Intellect: " + mIntellect);
        myAgilityText.setText("Agility: " + mAgility);
        myCharacterLevelText.setText("Current Level: " + mCharLevel);
        myCharacterXPText.setText("Current XP: " + mCharXp);

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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
    public interface OnFragmentInteractionListener
    {
        void onFragmentInteraction(Uri uri);
    }
}
