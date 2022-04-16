package game;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mlscholl.wickedchambers.R;
import java.util.HashMap;
import java.util.Map;
import characters.CharCreate;
import enemy.Boss;
import enemy.Enemy;
import itemfiles.Inventory;
import itemfiles.InventoryMenuFragment;
import mainmenu.StartMenuActivity;
import server.ServerUpdates;
import skillfiles.SkillTreeMenuFragment;

import static android.support.v7.widget.AppCompatDrawableManager.get;


/**
 * Created by mlscholl on 10/16/17.
 */

/**
 * Main activity is the main function that starts the gameWindow which in turn starts the mainthread
 * The Main activity also starts the xml which contains the surfaceview
 * The two fragments the are implemented in the main activity are for displaying the inventory and skilltree fragments over the surfaceview
 */
public class MainActivity extends AppCompatActivity implements InventoryMenuFragment.OnFragmentInteractionListener, SkillTreeMenuFragment.OnFragmentInteractionListener {

    public static final String FRAGTAG = "ImmersiveModeFragment";

    public static volatile int whoseTurn = 0;
    public static int player;
    public static String[] attributeP1;
    public static String[] attributeP2;
    public static String[] attributeP3;
    public static String[] attributeP4;
    public static String[] roomDataDelim;
    public static int playertoKill = 1;

    public static CharCreate player1, player2, player3, player4, currentPlayer;
    public FrameLayout menuFrame;
    public FragmentManager fM;
    public FragmentTransaction fT;
    public SkillTreeMenuFragment skillFragment;
    public InventoryMenuFragment inventoryFragment;

    public static int damage;
    public static int target = 1;

    public static Enemy enemy1, enemy2, enemy3, enemy4;
    public static Boss boss1;
    private Button attack_btn;
    private Button skill_btn;
    private Button items_btn;
    private String usernameFromIntent;
    private String test;

    /**
     * The main on create method for our app
     * Initially all the characters are created with the attributes taken from the server
     * The turn and player numbers are set and then the gameWindow / surfaceview is created
     * Lastly all the buttons that are outside of the surfaceView are given their onclicklisteners
     * @param savedInstanceState
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        usernameFromIntent = getIntent().getStringExtra("USERNAME");
        //lobbyNumFromIntent = getIntent().getStringExtra("LOBBYNUM");
        //lobbyNum = lobbyNumFromIntent;

        attributeP1 = getIntent().getStringArrayExtra("attribute1");
        attributeP2 = getIntent().getStringArrayExtra("attribute2");
        attributeP3 = getIntent().getStringArrayExtra("attribute3");
        attributeP4 = getIntent().getStringArrayExtra("attribute4");

        player1 = new CharCreate(attributeP1, 1);
        player2 = new CharCreate(attributeP2, 2);
        player3 = new CharCreate(attributeP3, 3);
        player4 = new CharCreate(attributeP4, 4);

        player1.updateUser(StartMenuActivity.mUsername);
        player2.updateUser(StartMenuActivity.usernameList[0]);
        player3.updateUser(StartMenuActivity.usernameList[1]);
        player4.updateUser(StartMenuActivity.usernameList[2]);

        roomDataDelim = StartMenuActivity.roomData.split(":");

        //if (roomDataDelim[1].equals("Enemy")) {
            //enemy1 = new Enemy("Skeleton", 1);
            enemy1 = new Enemy(roomDataDelim[2], 1);
            enemy2 = new Enemy(roomDataDelim[3], 2);
            enemy3 = new Enemy(roomDataDelim[4], 3);
            enemy4 = new Enemy(roomDataDelim[5], 4);
       // }
        //else{
            //boss1 = new Boss();
        //}
        //usernameFromIntent = "meat:beef:milk:feet";
        //usernameList = usernameFromIntent.split(":");

        /* WE NEED TO SET WHO EVERYONE IS RIGHT HERE
        * This includes enemies and the room
        * From this point we can then update the global vars
        * That will then be taken over at the game window to draw the creation of the room */


        //turnplayerset();

        getTurn();
        getPlayer();


        /* This starts GAMEWINDOW do everything to start the game before this */
        setContentView(R.layout.activity_game);


        if (getSupportFragmentManager().findFragmentByTag(FRAGTAG) == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            ImmersiveModeFragment fragment = new ImmersiveModeFragment();
            transaction.add(fragment, FRAGTAG);
            transaction.commit();
        }

        Intent service = new Intent(getApplicationContext(), ServerUpdates.class);
        startService(service);

        //ListView list = (ListView) findViewById(R.id.)

        attack_btn = (Button) findViewById(R.id.attack_button);
        skill_btn = (Button) findViewById(R.id.skill_button);
        items_btn = (Button) findViewById(R.id.item_button);
        //setting player number and whose turn it is.
        //turnplayerset();

        //Toast.makeText(getApplicationContext(), "" + MainActivity.whoseTurn, Toast.LENGTH_SHORT).show();


        Log.d("MainActivity", "In Main Activity");
        //Log.d("gunnar tempplayer", Integer.toString(player));
        //Log.d("gunnar tempwhoseTurn", Integer.toString(whoseTurn));

        attack_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "" + MainActivity.whoseTurn, Toast.LENGTH_SHORT).show();
                Log.d("player", Integer.toString(MainActivity.player));
                Log.d("whoseTurn", Integer.toString(MainActivity.whoseTurn));
                if (player == whoseTurn) {
                    //Toast.makeText(getApplicationContext(), "Attack Button!", Toast.LENGTH_SHORT).show();
                    //endturn_btn.setVisibility(View.VISIBLE);
                    //Toast.makeText(getApplicationContext(), "You have changed your action to attack", Toast.LENGTH_SHORT).show();

                    // Log.v(TAG, "In Attack Button");
                    createAndShowAlertDialog();
                }
            }
        });
        skill_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // (player == whoseTurn) {
                    //Toast.makeText(getApplicationContext(), "Magic Button! (Not yet implemented)", Toast.LENGTH_SHORT).show();
                    getString(new VolleyCallback() {
                        @Override
                        public void onSuccess(String result) {
                            test = result;
                        }
                    });

                    //Log.v(TAG, "In Magic Button");
                    menuFrame = (FrameLayout)findViewById(R.id.skillAndInventoryMenuSpace);
                    fM = getSupportFragmentManager();
                    fT = fM.beginTransaction();
                    skillFragment = SkillTreeMenuFragment.newInstance(player1);
                    fT.add(R.id.skillAndInventoryMenuSpace, skillFragment, "TAG").commit();
                    menuFrame.bringToFront();
                    menuFrame.setVisibility(View.VISIBLE);
                //}
            }
        });
        items_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if (player == whoseTurn) {
                    menuFrame = (FrameLayout)findViewById(R.id.skillAndInventoryMenuSpace);
                    fM = getSupportFragmentManager();
                    fT = fM.beginTransaction();
                    inventoryFragment = InventoryMenuFragment.newInstance(player1);
                    fT.add(R.id.skillAndInventoryMenuSpace, inventoryFragment, "TAG").commit();
                    menuFrame.bringToFront();
                    menuFrame.setVisibility(View.VISIBLE);
                //}
            }
        });
    }

    /**
     * Destroys the activity
     */
    public void onDestroy(){
        super.onDestroy();
        Log.d("destroy", "get rekt nerd");
    }

    /**
     * Displays the alertdialog for ending your turn
     * Yes ends your turn and updates the turn value and no just brings you back the screen
     */
    private void createAndShowAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Would you like to end your turn?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                if(player == 1){
                    damage = player1.attack();

                }
                else if (player == 2){
                    damage = player2.attack();
                }
                else if (player == 3){
                    damage = player3.attack();
                }
                else {
                    damage = player4.attack();
                }



                if(target == 1){
                    enemy1.takeDamage(damage);
                    Log.d("player damage:", Integer.toString(damage));
                    Log.d("enemy health:", Integer.toString(enemy1.health));
                }
                else if (target == 2){
                    enemy2.takeDamage(damage);
                }
                else if (target == 3){
                    enemy3.takeDamage(damage);
                }
                else {
                    enemy4.takeDamage(damage);
                }

                playertoKill = ((int)System.nanoTime() % 3)+2;
                if(playertoKill == 1){
                    player1.addHealth(-enemy1.attack());
                }
                else if(playertoKill == 2){
                    player2.addHealth(-enemy2.attack());
                }
                else if(playertoKill == 3){
                    player3.addHealth(-enemy3.attack());
                }
                else if(playertoKill == 4){
                    player4.addHealth(-enemy4.attack());
                }
                damageCalc();
                nextTurn();
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                /* If no do nothing */
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
        dialog.setTitle("End turn");
        dialog.show();
        dialog.getWindow().getDecorView().setSystemUiVisibility(this.getWindow().getDecorView().getSystemUiVisibility());
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);
    }

    /**
     * Calls the map_pull php script from the server
     * @param callback
     */
    public void getString(final VolleyCallback callback) {
        String url = "http://proj-309-gp-b-2.cs.iastate.edu/map_pull.php";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callback.onSuccess(response);
                Log.d("Response", response);
            }
        },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){}
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    /**
     * Calls the nextturn php script from the server
     */
    public void nextTurn(){
        whoseTurn = whoseTurn + 1;
        if(whoseTurn == 5){
            whoseTurn = 1;
        }


        String url = "http://proj-309-gp-b-2.cs.iastate.edu/nextturn.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response){


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("turn", Integer.toString(whoseTurn));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }

/*
    public void turnplayerset(){

        getPlayer(new VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                int checker = 0;
                while(checker != 1) {
                    checker = setPl(Integer.parseInt(result.trim()));
                }

                //final String temp = result.trim();

                //player = Integer.parseInt(temp);
                //setPl(player);

                //aaron aaplayer = 4;
            }
        });
       /* getTurn(new VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                int checker = 0;
                while(checker != 1) {
                    checker = setwhoseT(Integer.parseInt(result.trim()));
                }
                //final String temp = result.trim();
                //whoseTurn = Integer.parseInt(temp);
                //setwhoseT(whoseTurn);



            }
        });

    }
    */

    /**
     * Calls the whoturn php script from the server
     */
    public void getTurn() {

        RequestFuture<String> future = RequestFuture.newFuture();
        String url = "http://proj-309-gp-b-2.cs.iastate.edu/whoturn.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response){
                        //setwhoseT(Integer.parseInt(response.trim()));
                        Log.d("volley response:", response);

                       // callback.onSuccess(response);
                        MainActivity.whoseTurn = Integer.parseInt(response.trim());

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        /*String response = "";
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        };
        StringRequest request = new StringRequest(Request.Method.POST, url, future,errorListener);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
        try {
            response = future.get(3, TimeUnit.SECONDS);
            Log.d("returned in try", response);
        } catch (InterruptedException e) {
            Log.d("caught an error", response);

            errorListener.onErrorResponse(new VolleyError(e));
        } catch (ExecutionException e) {
            Log.d("caught an error", response);
            errorListener.onErrorResponse(new VolleyError(e));
        } catch (TimeoutException e) {
            Log.d("timeout", response);
            e.printStackTrace();
        }
        */
    }

    /**
     * Calls the whatplayer php script from the server
     */
    public void getPlayer() {


        String url = "http://proj-309-gp-b-2.cs.iastate.edu/whatplayer.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response){
                        //setPl(Integer.parseInt(response.trim()));
                        MainActivity.player = Integer.parseInt(response.trim());

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", usernameFromIntent);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    /**
     *
     * @param playernum
     * @return
     */
    public int setPl(int playernum){
        this.player = playernum;
        return 1;
    }

    /**
     *
     * @param turn
     * @return
     */
    public int setwhoseT(int turn){
        this.whoseTurn = turn;
        return 1;
    }

    /**
     *
     * @param statOffset
     */
    @Override
    public void onFragmentInteraction(String statOffset) {
        String[] data = statOffset.split(":");
        int healthBonus = Integer.parseInt(data[0]);
        int strengthBonus = Integer.parseInt(data[1]);
        int intellectBonus = Integer.parseInt(data[2]);
        int agilityBonus = Integer.parseInt(data[3]);
        Inventory newInv = Inventory.deserialize(data[4]);
        player1.addHealth(healthBonus);
        player1.addStrength(strengthBonus);
        player1.addIntellect(intellectBonus);
        player1.addAgility(agilityBonus);
        player1.inventory = newInv;
        menuFrame = (FrameLayout)findViewById(R.id.skillAndInventoryMenuSpace);
        menuFrame.setVisibility(View.GONE);
        GameWindow gW = (GameWindow)findViewById(R.id.surfaceView);
        gW.setVisibility(View.VISIBLE);

        updateGameTable(healthBonus,strengthBonus,intellectBonus,agilityBonus,0,0);
        fT.remove(inventoryFragment);
    }

    /**
     *
     * @param character
     */
    @Override
    public void onFragmentInteraction(CharCreate character) {
        //player1 = character;
        int mHPI = character.getHealth() - player1.getHealth();
        int mSTRI = character.getStrength() - player1.getStrength();
        int mINTI = character.getIntellect() - player1.getIntellect();
        int mAGII = character.getAgility() - player1.getAgility();
        int mLVLI = character.getCharLevel() - player1.getCharLevel();
        int mEXPI = character.getCharXP() - player1.getCharXP();
        player1 = character;
        menuFrame = (FrameLayout)findViewById(R.id.skillAndInventoryMenuSpace);
        menuFrame.setVisibility(View.GONE);
        GameWindow gW = (GameWindow)findViewById(R.id.surfaceView);
        gW.setVisibility(View.VISIBLE);

        updateGameTable(mHPI,mSTRI,mINTI,mAGII,mLVLI,mEXPI);
        fT.remove(skillFragment);
    }

    /**
     * Updates the game table based on the stat changes via equipping items/skills. Parameters are all increases to that stat.
     * @param health
     * @param strength
     * @param intellect
     * @param agility
     * @param charlvl
     * @param charxp
     */
    public void updateGameTable(int health, int strength, int intellect, int agility, int charlvl, int charxp){
        String url = "http://proj-309-gp-b-2.cs.iastate.edu/inv_skill_push.php?inv="+player1.inventory.serialize()+"&skill="+player1.skillTree.serialize()+"&username="+usernameFromIntent+
                "&hp_inc="+(health+"")+"&int_inc="+(intellect+"")+"&str_inc="+(strength+"")+"&agi_inc="+(agility+"")+"&level_inc="+(charlvl+"")+"&exp_inc="+(charxp+"");

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

    /**
     *  Updates the health on the server of all enemies and players.
     */
    public void damageCalc(){
        String url = "http://proj-309-gp-b-2.cs.iastate.edu/damage_calc.php?p1="+player1.health+"&p2="+player2.health+"&p3="+player3.health+"&p4="+player4.health+
                "&e1="+enemy1.health+"&e2="+enemy2.health+"&e3="+enemy3.health+"&e4="+enemy4.health;

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

