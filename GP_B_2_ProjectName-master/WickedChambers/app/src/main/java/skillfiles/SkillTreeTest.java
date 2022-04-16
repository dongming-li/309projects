package skillfiles;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.mlscholl.wickedchambers.R;

import characters.CharCreate;
import itemfiles.InventoryMenuFragment;

/**
 * Created by bertucci on 12/3/2017.
 */

public class SkillTreeTest extends AppCompatActivity implements SkillTreeMenuFragment.OnFragmentInteractionListener
{
    private Button createMenu, showStats;
    private CharCreate buddy;
    private FrameLayout frame;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.skill_tree_test);
        buddy = new CharCreate('w');
        buddy.addCharXP(30);
        createMenu = (Button)findViewById(R.id.createMenu);
        showStats = (Button)findViewById(R.id.showStats);
        createMenu.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                SkillTreeMenuFragment fraggy = SkillTreeMenuFragment.newInstance(buddy);
                FragmentManager fM = getSupportFragmentManager();
                FragmentTransaction fT = fM.beginTransaction();
                fT.add(R.id.skillTreeFrame, fraggy, "TAG");
                frame = (FrameLayout)findViewById(R.id.skillTreeFrame);
                frame.setVisibility(View.VISIBLE);
                createMenu.setVisibility(View.GONE);
                showStats.setVisibility(View.GONE);
                fT.commit();
            }
        });
        showStats.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                Toast.makeText(getApplicationContext(), buddy.serialize(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onFragmentInteraction(CharCreate character) {
        buddy = character;
        frame.setVisibility(View.GONE);
        createMenu.setVisibility(View.VISIBLE);
        showStats.setVisibility(View.VISIBLE);
    }
}
