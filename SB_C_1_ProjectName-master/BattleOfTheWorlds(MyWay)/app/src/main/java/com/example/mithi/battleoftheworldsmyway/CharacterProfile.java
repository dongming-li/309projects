package com.example.mithi.battleoftheworldsmyway;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import java.util.UUID;


/**
 * Created by Mithil Shah and Aaron Rees on 12/4/2017.
 */

/**
 * Class for the Character profile of the
 * selected character from the character select screen
 */
public class CharacterProfile extends AppCompatActivity
{
    protected static String characterClass;
    private int level = 1;
    private int killCount = 0;
    private int deathCount = 0;
    protected static String username;

    /**
     * main method for the character profile Activity and for entering the game.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_character);
        getSupportActionBar().setTitle(username);

        TextView charClass = findViewById(R.id.characterClass);
        charClass.setText(this.characterClass.toUpperCase());

        TextView level = findViewById(R.id.level);
        level.append(Integer.toString(this.level));

        TextView killCount = findViewById(R.id.killCount);
        killCount.append(Integer.toString(this.killCount));

        TextView deathCount = findViewById(R.id.deathCount);
        deathCount.append(Integer.toString(this.deathCount));

        TextView uuidView = findViewById(R.id.uuid);
        UUID uuid = UUID.randomUUID();
        uuidView.append(uuid.toString());

        TextView clientID = findViewById(R.id.clientID);
        clientID.setPaintFlags(clientID.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        final ImageView galacticRanger = findViewById(R.id.galacticRangerProfilePic);
        final ImageView kiMaster =  findViewById(R.id.kiMasterProfilePic);
        final ImageView berserker = findViewById(R.id.berserkerProfilePic);

        if(characterClass.equals("Ki-Master"))
        {
            kiMaster.setVisibility(View.VISIBLE);
        }
        else if(characterClass.equals("Berserker"))
        {
            berserker.setVisibility(View.VISIBLE);
            charClass.setTextColor(Color.parseColor("#2ba500"));
        }
        else
        {
            galacticRanger.setVisibility(View.VISIBLE);
            charClass.setTextColor(Color.parseColor("#7b2db5"));
        }

        Button enterGame = findViewById(R.id.enterGameButton);
        enterGame.setOnClickListener(new Button.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent gameStart = new Intent(CharacterProfile.this, GameMain.class);
                startActivity(gameStart);
            }
        });
    }
}
