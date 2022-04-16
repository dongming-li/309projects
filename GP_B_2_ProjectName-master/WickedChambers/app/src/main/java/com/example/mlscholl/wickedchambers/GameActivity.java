package com.example.mlscholl.wickedchambers;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ThreadLocalRandom;

import game.ImmersiveModeFragment;

import static mainmenu.MainMenuActivity.FRAGTAG;

/**
 * Created by mlscholl on 10/4/17.
 */

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private ProgressBar p1_health;
    private ProgressBar p2_health;
    private ProgressBar p3_health;
    private ProgressBar p4_health;
    private ProgressBar monster_health;

    private Button attack_btn;
    private Button magic_btn;
    private Button items_btn;

    private int monster_hp = 100;

    private String data;

    private TextView player1_data, player2_data, player3_data, player4_data;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_game);

        if (getSupportFragmentManager().findFragmentByTag(FRAGTAG) == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            ImmersiveModeFragment fragment = new ImmersiveModeFragment();
            transaction.add(fragment, FRAGTAG);
            transaction.commit();
        }

        attack_btn = (Button) findViewById(R.id.attack_button);
        magic_btn = (Button) findViewById(R.id.skill_button);
        items_btn = (Button) findViewById(R.id.item_button);


        data = getIntent().getStringExtra("DATA");
        player1_data.setText(data);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.attack_button: {
                int damage = ThreadLocalRandom.current().nextInt(2, 5 + 1);
                monster_hp -= damage;
                if (monster_hp < 0) monster_hp = 0;
                monster_health.setProgress(monster_hp);
                break;
            }

            case R.id.skill_button: {

                break;
            }

            case R.id.item_button: {
                Toast.makeText(this, "Grenade out!", Toast.LENGTH_SHORT).show();
                int damage = ThreadLocalRandom.current().nextInt(20, 30 + 1);
                monster_hp -= damage;
                if (monster_hp < 0) monster_hp = 0;
                monster_health.setProgress(monster_hp);
                break;
            }
        }
    }
}