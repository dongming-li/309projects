package com.example.mithi.battleoftheworldsmyway;

        import android.app.Activity;
        import android.content.Intent;
        import android.content.pm.ActivityInfo;
        import android.graphics.Bitmap;
        import android.media.Image;
        import android.os.Bundle;
        import android.view.View;
        import android.view.WindowManager;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.Toast;

        import java.util.ArrayList;

/**
 * Created by Mithil Shah on 12/4/2017.
 */

/**
 * Class for instantiating the Marketplace in the game
 */
public class Marketplace extends MainActivity
{
    /**
     * main method for the Marketplace Activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.layout_marketplace);

        final Button sword1 = findViewById(R.id.sword_button1);
        Button sword2 = findViewById(R.id.sword_button2);
        Button sword3 = findViewById(R.id.sword_button3);

        sword1.setOnClickListener(new Button.OnClickListener()
        {
            public void onClick(View v)
            {
                if(Player.coins < 500)
                {
                    Toast.makeText(Marketplace.this, "Not enough coins!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    ArrayList<Bitmap> getter = Weapon.returnSwords(getApplicationContext());
                    Weapon.resetBitMap(getter.get(0));
                }
            }
        });
        sword2.setOnClickListener(new Button.OnClickListener()
        {
            public void onClick(View v)
            {
//                if(Player.coins < 5000)
//                {
//                    Toast.makeText(Marketplace.this, "Not enough coins!", Toast.LENGTH_SHORT).show();
//                }
//                else
//                {
                ArrayList<Bitmap> getter = Weapon.returnSwords(getApplicationContext());
                Weapon.resetBitMap(getter.get(1));
                GameView.setMapField("frontyard");
                killActivity();
//                    Intent back = new Intent(Marketplace.this, GameActivity.class);
//                    startActivity(back);
                // }
            }
        });
        sword3.setOnClickListener(new Button.OnClickListener()
        {
            public void onClick(View v)
            {
                if(Player.coins < 50000)
                {
                    Toast.makeText(Marketplace.this, "Not enough coins!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    ArrayList<Bitmap> getter = Weapon.returnSwords(getApplicationContext());
                    Weapon.resetBitMap(getter.get(2));
                }
            }
        });
    }

    /**
     * method for stopping the activity
     */
    private void killActivity()
    {
        Marketplace.this.finish();
    }
}