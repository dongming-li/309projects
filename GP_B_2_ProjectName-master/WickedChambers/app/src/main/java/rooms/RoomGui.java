package rooms;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mlscholl.wickedchambers.R;

import java.util.ArrayList;

import enemy.Boss;
import enemy.Enemy;
import enemy.GenericEnemy;

/**
 * Created by mlscholl on 12/2/2017.
 */

public class RoomGui extends AppCompatActivity {

    private Button goblinButton, banditButton, skeletonButton, slimeButton, zombieButton, boss1Button, boss2Button, boss3Button, createRoom, enemyType, bossType, removeButton;
    private boolean isBossType;
    private TextView enemy1, enemy2, enemy3, enemy4, boss;
    private ArrayList<GenericEnemy> enemyList, bossList;
    private EditText roomName;

    public static int num = 0;

    /**
     * Does the onCreate stuff.
     * @param savedInstanceState
     */
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.room_creation_layout);

        enemyList = new ArrayList<>();
        bossList = new ArrayList<>();
        isBossType = false;
        removeButton = (Button)findViewById(R.id.removeButton);
        removeButton.setVisibility(View.GONE);
        goblinButton = (Button)findViewById(R.id.goblinButton);
        banditButton = (Button)findViewById(R.id.banditButton);
        skeletonButton = (Button)findViewById(R.id.skeletonButton);
        slimeButton = (Button)findViewById(R.id.slimeButton);
        zombieButton = (Button)findViewById(R.id.zombieButton);
        boss1Button = (Button)findViewById(R.id.boss1Button);
        boss2Button = (Button)findViewById(R.id.boss2Button);
        boss3Button = (Button)findViewById(R.id.boss3Button);
        createRoom = (Button)findViewById(R.id.createRoom);
        enemyType = (Button)findViewById(R.id.enemyTypeButton);
        bossType = (Button)findViewById(R.id.bossTypeButton);
        enemy1 = (TextView)findViewById(R.id.enemy1Image);
        enemy2 = (TextView)findViewById(R.id.enemy2Image);
        enemy3 = (TextView)findViewById(R.id.enemy3Image);
        enemy4 = (TextView)findViewById(R.id.enemy4Image);
        boss = (TextView)findViewById(R.id.bossImage);
        roomName = (EditText)findViewById(R.id.nameInputText);
        generateImages();
        enemyType.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                boss1Button.setVisibility(View.GONE);
                boss2Button.setVisibility(View.GONE);
                boss3Button.setVisibility(View.GONE);
                boss.setVisibility(View.GONE);
                goblinButton.setVisibility(View.VISIBLE);
                banditButton.setVisibility(View.VISIBLE);
                skeletonButton.setVisibility(View.VISIBLE);
                slimeButton.setVisibility(View.VISIBLE);
                zombieButton.setVisibility(View.VISIBLE);
                enemy1.setVisibility(View.VISIBLE);
                enemy2.setVisibility(View.VISIBLE);
                enemy3.setVisibility(View.VISIBLE);
                enemy4.setVisibility(View.VISIBLE);
                isBossType = false;
            }
        });
        bossType.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                boss1Button.setVisibility(View.VISIBLE);
                boss2Button.setVisibility(View.VISIBLE);
                boss3Button.setVisibility(View.VISIBLE);
                boss.setVisibility(View.VISIBLE);
                goblinButton.setVisibility(View.GONE);
                banditButton.setVisibility(View.GONE);
                skeletonButton.setVisibility(View.GONE);
                slimeButton.setVisibility(View.GONE);
                zombieButton.setVisibility(View.GONE);
                enemy1.setVisibility(View.GONE);
                enemy2.setVisibility(View.GONE);
                enemy3.setVisibility(View.GONE);
                enemy4.setVisibility(View.GONE);
                isBossType = true;
            }
        });
        goblinButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if (enemyList.size() < 4)
                {
                    enemyList.add(new Enemy("Goblin"));
                }
                generateImages();
            }
        });
        slimeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if (enemyList.size() < 4)
                {
                    enemyList.add(new Enemy("Slime"));
                }
                generateImages();
            }
        });
        zombieButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if (enemyList.size() < 4)
                {
                    enemyList.add(new Enemy("Zombie"));
                }
                generateImages();
            }
        });
        skeletonButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if (enemyList.size() < 4)
                {
                    enemyList.add(new Enemy("Skeleton"));
                }
                generateImages();
            }
        });
        banditButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if (enemyList.size() < 4)
                {
                    enemyList.add(new Enemy("Bandit"));
                }
                generateImages();
            }
        });
        boss1Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if (bossList.size() == 0)
                {
                    bossList.add(new Boss());
                    generateImages();
                }
            }
        });
        boss2Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                //DO NOTHING, NO BOSS 2 EXISTS YET
            }
        });
        boss3Button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                //DO NOTHING, NO BOSS 2 EXISTS YET
            }
        });
        removeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                if (isBossType)
                {
                    bossList.remove(0);
                }
                else
                {
                    enemyList.remove(enemyList.size() - 1);
                }
                generateImages();
            }
        });
        createRoom.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {
                RoomCreate room = null;
                boolean completed = false;

                if (isBossType && bossList.size() > 0)
                {
                    room = new RoomCreate(roomName.getText().toString(), "Boss", bossList);
                    completed = true;
                }
                else if (!isBossType && enemyList.size() > 0)
                {
                    room = new RoomCreate(roomName.getText().toString(), "Enemy", enemyList);
                    completed = true;
                }
                if (completed)
                {
                    String roomSerialized = room.serializeRoom();
                    Log.d("room: ", roomSerialized);
                    pushRoomToDatabase(roomSerialized);
                    Toast.makeText(getApplicationContext(), roomSerialized, Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    /**
     * Gets the image based on the enemy given
     * @param enemy enemy image to get
     * @return enemy image drawable
     */
    public int getImage(GenericEnemy enemy)
    {
        int returner = 0;
        if (enemy.getClass().equals(Boss.class))
        {
            Boss b = (Boss)enemy;
            //TODO Add different image types for different types of bosses here, if desired
            return R.drawable.skeleton_humanoid_large;
        }
        else if (enemy.getClass().equals(Enemy.class))
        {
            Enemy e = (Enemy)enemy;
            if (e.type().equals("Bandit"))
            {
                return R.drawable.bandit;
            }
            else if (e.type().equals("Slime"))
            {
                return R.drawable.slime;
            }
            else if (e.type().equals("Zombie"))
            {
                return R.drawable.zombie;
            }
            else if (e.type().equals("Skeleton"))
            {
                return R.drawable.skeleton_humanoid_small;
            }
            else if (e.type().equals("Goblin"))
            {
                return R.drawable.goblin;
            }

        }
        return returner;
    }

    /**
     * Generates the images
     */
    public void generateImages()
    {
        for (int i = 0; i < enemyList.size(); i++)
        {
            if (i == 0)
            {
                if (enemyList.get(i) == null)
                {
                    enemy1.setBackground(getResources().getDrawable(R.drawable.button));
                }
                else
                {
                    enemy1.setBackground(getResources().getDrawable(getImage(enemyList.get(i))));
                }

            }
            else if (i == 1)
            {
                if (enemyList.get(i) == null)
                {
                    enemy2.setBackground(getResources().getDrawable(R.drawable.button));
                }
                else
                {
                    enemy2.setBackground(getResources().getDrawable(getImage(enemyList.get(i))));
                }
            }
            else if (i == 2)
            {
                if (enemyList.get(i) == null)
                {
                    enemy3.setBackground(getResources().getDrawable(R.drawable.button));
                }
                else
                {
                    enemy3.setBackground(getResources().getDrawable(getImage(enemyList.get(i))));
                }
            }
            else if (i == 3)
            {
                if (enemyList.get(i) == null)
                {
                    enemy4.setBackground(getResources().getDrawable(R.drawable.button));
                }
                else
                {
                    enemy4.setBackground(getResources().getDrawable(getImage(enemyList.get(i))));
                }
            }
        }
        if (enemyList.size() == 1)
        {
            enemy2.setBackground(getResources().getDrawable(R.drawable.button));
            enemy3.setBackground(getResources().getDrawable(R.drawable.button));
            enemy4.setBackground(getResources().getDrawable(R.drawable.button));
        }
        else if (enemyList.size() == 2)
        {
            enemy3.setBackground(getResources().getDrawable(R.drawable.button));
            enemy4.setBackground(getResources().getDrawable(R.drawable.button));
        }
        else if (enemyList.size() == 3)
        {
            enemy4.setBackground(getResources().getDrawable(R.drawable.button));
        }
        else if (enemyList.size() == 0)
        {
            enemy1.setBackground(getResources().getDrawable(R.drawable.button));
            enemy2.setBackground(getResources().getDrawable(R.drawable.button));
            enemy3.setBackground(getResources().getDrawable(R.drawable.button));
            enemy4.setBackground(getResources().getDrawable(R.drawable.button));
        }
        if (bossList.size() == 1)
        {
            boss.setBackground(getResources().getDrawable(getImage(bossList.get(0))));
        }
        else
        {
            boss.setBackground(getResources().getDrawable(R.drawable.button));
        }
        if ((isBossType && bossList.size() != 0))
        {
            removeButton.setVisibility(View.VISIBLE);
        }
        else if (!isBossType && enemyList.size() > 0)
        {
            removeButton.setVisibility(View.VISIBLE);
        }
        else
        {
            removeButton.setVisibility(View.GONE);
        }
    }

    /**
     * Pushes the room data to the database
     * @param data data to be pushed
     */
    public void pushRoomToDatabase(String data){

        num++;

        if (num == 11) {
            num = 1;
        }

        String url = "http://proj-309-gp-b-2.cs.iastate.edu/gm_map_add.php?map_data="+data+"&map_num="+(num+"");
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error){
                    }
                }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
