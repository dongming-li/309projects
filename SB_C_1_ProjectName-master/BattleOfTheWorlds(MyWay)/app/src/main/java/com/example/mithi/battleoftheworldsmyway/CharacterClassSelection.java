package com.example.mithi.battleoftheworldsmyway;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Mithil Shah and Aaron Rees on 12/4/2017.
 */

/**
 * Class for selecting character to play in game
 */
public class CharacterClassSelection extends MainActivity
{
    /**
     * main method for executing the Character selection Activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selection_class_character);
        getSupportActionBar().setTitle("Character Class Selection");

        final ImageView berserker = findViewById(R.id.berserker);
        final ImageView galacticRanger = findViewById(R.id.galacticRanger);
        final ImageView kiMaster = findViewById(R.id.kiMaster);

        final Intent characterProfile = new Intent(CharacterClassSelection.this, CharacterProfile.class);

        berserker.setOnClickListener(new TextView.OnClickListener()
        {
            public void onClick(View v)
            {
                //Tell server the user has chosen the Berserker class
                CharacterProfile.characterClass = "Berserker";
                startActivity(characterProfile);
            }
        });

        galacticRanger.setOnClickListener(new TextView.OnClickListener()
        {
            public void onClick(View v)
            {
                //Tell server the user has chosen the Galactic Ranger class
                CharacterProfile.characterClass = "Galactic Ranger";
                startActivity(characterProfile);
            }
        });

        kiMaster.setOnClickListener(new TextView.OnClickListener()
        {
            public void onClick(View v)
            {
                //Tell server the user has chosen the Ki-Master class
                CharacterProfile.characterClass = "Ki-Master";
                startActivity(characterProfile);
            }
        });
    }
}
