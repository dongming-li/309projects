package mainmenu;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mlscholl.wickedchambers.R;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadLocalRandom;

import characters.CharCreate;
import game.ImmersiveModeFragment;
import game.MainActivity;
import game.VolleyCallback;
import rooms.RoomGui;
import server.ServerService;

/**
 * Created by bertucci on 11/6/2017.
 */

public class StartMenuActivity extends AppCompatActivity implements CharacterValueFragment.OnFragmentInteractionListener
{
    public static final String FRAGTAG = "ImmersiveModeFragment";
    private String TAG = MainMenuActivity.class.getSimpleName();

    private String mClass;
    private int mHealth;
    private int mStrength;
    private int mIntellect;
    private int mAgility;
    private int mCharLevel;
    private int mCharXp;
    private Fragment charValFrag;

    private TextView game1Text;
    /*
    private TextView game2Text;
    private TextView game3Text;
    private TextView game4Text;
    private TextView game5Text;
    private TextView game6Text;
    */
    private TextView invisData;

    private Button game1Button, createRoomsButton;
    /*
    private Button game2Button;
    private Button game3Button;
    private Button game4Button;
    private Button game5Button;
    private Button game6Button;
    */
    private Button startButton;


    public String[] attributes;
    private int[] numPlayers;
    private int startTime = 1;
    private int currentLobby = 6;
    private int playerNum = 0;
    public static String mUsername;
    public static String otherUsers;
    public static String roomData;
    private int tableCheck=0;

    public static String[] usernameList;

    public static String[] attributeP1;
    public static String[] attributeP2;
    public static String[] attributeP3;
    public static String[] attributeP4;

    private BroadcastReceiver statusReceiver;
    private IntentFilter mIntent;
    private int count = 0;

    private Handler handy = new Handler();
    private Runnable runny = new Runnable() {
      public void run()
      {
          gameoneUpdate();
          update(game1Text);
          if(numPlayers[0] == 4) {
              findotherUsers();
          }
          handy.postDelayed(this,2500); // set time here to refresh textView
      }
    };


    /**
     * Handles all of the window creation based on the previous saved instance state
     * @param savedInstanceState
     */
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.main_fragment_layout);
        //Get values from database for correct character information here, then input into private variables

        if (getSupportFragmentManager().findFragmentByTag(FRAGTAG) == null) {
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            ImmersiveModeFragment fragment = new ImmersiveModeFragment();
            transaction.add(fragment, FRAGTAG);
            transaction.commit();
        }

        game1Button = (Button)findViewById(R.id.game1Button);
        createRoomsButton = (Button)findViewById(R.id.createRoomsButton);
        createRoomsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(StartMenuActivity.this, RoomGui.class);
                startActivity(i);
            }
        });
        /*
        game2Button = (Button)findViewById(R.id.game2Button);
        game3Button = (Button)findViewById(R.id.game3Button);
        game4Button = (Button)findViewById(R.id.game4Button);
        game5Button = (Button)findViewById(R.id.game5Button);
        game6Button = (Button)findViewById(R.id.game6Button);
        */
        startButton = (Button)findViewById(R.id.startButton);
        startButton.setVisibility(View.VISIBLE);

        game1Text = (TextView)findViewById(R.id.game1Details);
        /*
        game2Text = (TextView)findViewById(R.id.game2Details);
        game3Text = (TextView)findViewById(R.id.game3Details);
        game4Text = (TextView)findViewById(R.id.game4Details);
        game5Text = (TextView)findViewById(R.id.game5Details);
        game6Text = (TextView)findViewById(R.id.game6Details);
        */
        invisData = (TextView)findViewById(R.id.invisData);
        numPlayers = new int[6];

        for (int i = 0; i < 6; i++)
        {
            numPlayers[i] = 0;
        }
        //update(game1Text, game2Text, game3Text, game4Text, game5Text, game6Text);

        Intent i = getIntent();
        final String username = i.getStringExtra("USERNAME");
        mUsername = username;
        //Toast.makeText(getApplicationContext(), username, Toast.LENGTH_SHORT).show();
        String[] data = makeDashboard(username);
//        mClass = data[0];
//        mHealth = Integer.parseInt(data[1]);
//        mStrength = Integer.parseInt(data[2]);
//        mIntellect = Integer.parseInt(data[3]);
//        mAgility = Integer.parseInt(data[4]);
//        mCharLevel = Integer.parseInt(data[5]);
//        mCharXp = Integer.parseInt(data[6]);

        //mClass = "Warrior";
        //mHealth = 100;
        //mStrength = 100;
        //mIntellect = 100;
        //mAgility = 100;
        //mCharLevel = 1;
        //mCharXp = 0;
        findotherUsers();
        gameoneUpdate();
        pullRoomData();

        update(game1Text);
        handy.postDelayed(runny, 2500);
        /*final Handler handler=new Handler();
        handler.post(new Runnable(){
            @Override
            public void run() {
                gameoneUpdate();
                update(game1Text);
                if(numPlayers[0] == 4) {
                    findotherUsers();
                }
                handler.postDelayed(this,2500); // set time here to refresh textView
            }
        });*/




        game1Button.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  //TODO Join game lobby 1
                  //update(game1Text, game2Text, game3Text, game4Text, game5Text, game6Text);

                  if (numPlayers[0] == 4)
                  {
                      Toast.makeText(getApplicationContext(), "Game is Full!", Toast.LENGTH_SHORT).show();
                  }
                  else
                  {
                      if (currentLobby != 6)
                          //numPlayers[currentLobby]--;
                      currentLobby = 0;
                      if (numPlayers[currentLobby] == 0) {
                          numPlayers[currentLobby] = 1;
                          playerNum = 1;
                          startTime = 1;
                          Toast.makeText(getApplicationContext(), "Joined Game 1 Lobby!", Toast.LENGTH_SHORT).show();
                          update(game1Text);
                      }
                      else if (numPlayers[currentLobby] == 1) {
                          numPlayers[currentLobby] = 2;
                          playerNum = 2;
                          startTime = 1;
                          Toast.makeText(getApplicationContext(), "Joined Game 1 Lobby!", Toast.LENGTH_SHORT).show();

                          update(game1Text);
                      }
                      else if (numPlayers[currentLobby] == 2) {
                          numPlayers[currentLobby] = 3;
                          playerNum = 3;
                          startTime = 1;
                          Toast.makeText(getApplicationContext(), "Joined Game 1 Lobby!", Toast.LENGTH_SHORT).show();

                          update(game1Text);
                      }
                      else if (numPlayers[currentLobby] == 3) {
                          numPlayers[currentLobby] = 4;
                          playerNum = 4;
                          startTime = 1;
                          Toast.makeText(getApplicationContext(), "Joined Game 1 Lobby!", Toast.LENGTH_SHORT).show();

                          update(game1Text);
                      }

                      connectToGame(username, currentLobby);
                      setTurn(playerNum, currentLobby);
                  }

              }
        });
        /*
        game2Button.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  //TODO Join game lobby 2
                  if (numPlayers[1] == 4)
                  {
                      Toast.makeText(getApplicationContext(), "Game is Full!", Toast.LENGTH_SHORT).show();
                  }
                  else
                  {
                      if (currentLobby != 6)
                          numPlayers[currentLobby]--;
                      currentLobby = 1;
                      if (numPlayers[currentLobby] == 0) {
                          playerNum = 1;
                          startTime = 1;

                      }
                      else if (numPlayers[currentLobby] == 1) {
                          playerNum = 2;
                          startTime = 1;
                      }
                      else if (numPlayers[currentLobby] == 2) {
                          playerNum = 3;
                          startTime = 1;
                      }
                      else if (numPlayers[currentLobby] == 3) {
                          playerNum = 4;
                          startTime = 1;
                      }
                      Toast.makeText(getApplicationContext(), "Joined Game 1 Lobby!", Toast.LENGTH_SHORT).show();
                      numPlayers[currentLobby]++;
                      update(game1Text, game2Text, game3Text, game4Text, game5Text, game6Text);
                      connectToGame(username, currentLobby);
                      setTurn(playerNum, currentLobby+1);
                  }
              }
        });
        game3Button.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  //TODO Join game lobby 3
                  if (numPlayers[2] == 4)
                  {
                      Toast.makeText(getApplicationContext(), "Game is Full!", Toast.LENGTH_SHORT).show();
                  }
                  else
                  {
                      if (currentLobby != 6)
                          numPlayers[currentLobby]--;
                      currentLobby = 2;
                      if (numPlayers[currentLobby] == 0) {
                          playerNum = 1;
                          startTime = 1;

                      }
                      else if (numPlayers[currentLobby] == 1) {
                          playerNum = 2;
                          startTime = 1;
                      }
                      else if (numPlayers[currentLobby] == 2) {
                          playerNum = 3;
                          startTime = 1;
                      }
                      else if (numPlayers[currentLobby] == 3) {
                          playerNum = 4;
                          startTime = 1;
                      }
                      Toast.makeText(getApplicationContext(), "Joined Game 1 Lobby!", Toast.LENGTH_SHORT).show();
                      numPlayers[currentLobby]++;
                      update(game1Text, game2Text, game3Text, game4Text, game5Text, game6Text);
                      connectToGame(username, currentLobby);
                      setTurn(playerNum, currentLobby+1);
                  }
              }
        });
        game4Button.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  //TODO Join game lobby 4
                  if (numPlayers[3] == 4)
                  {
                      Toast.makeText(getApplicationContext(), "Game is Full!", Toast.LENGTH_SHORT).show();
                  }
                  else
                  {
                      if (currentLobby != 6)
                          numPlayers[currentLobby]--;
                      currentLobby = 3;
                      if (numPlayers[currentLobby] == 0) {
                          playerNum = 1;
                          startTime = 1;

                      }
                      else if (numPlayers[currentLobby] == 1) {
                          playerNum = 2;
                          startTime = 1;
                      }
                      else if (numPlayers[currentLobby] == 2) {
                          playerNum = 3;
                          startTime = 1;
                      }
                      else if (numPlayers[currentLobby] == 3) {
                          playerNum = 4;
                          startTime = 1;
                      }
                      Toast.makeText(getApplicationContext(), "Joined Game 1 Lobby!", Toast.LENGTH_SHORT).show();
                      numPlayers[currentLobby]++;
                      update(game1Text, game2Text, game3Text, game4Text, game5Text, game6Text);
                      connectToGame(username, currentLobby);
                      setTurn(playerNum, currentLobby+1);
                  }
              }
        });
        game5Button.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  //TODO Join game lobby 5
                  if (numPlayers[4] == 4)
                  {
                      Toast.makeText(getApplicationContext(), "Game is Full!", Toast.LENGTH_SHORT).show();
                  }
                  else
                  {
                      if (currentLobby != 6)
                          numPlayers[currentLobby]--;
                      currentLobby = 4;
                      if (numPlayers[currentLobby] == 0) {
                          playerNum = 1;
                          startTime = 1;

                      }
                      else if (numPlayers[currentLobby] == 1) {
                          playerNum = 2;
                          startTime = 1;
                      }
                      else if (numPlayers[currentLobby] == 2) {
                          playerNum = 3;
                          startTime = 1;
                      }
                      else if (numPlayers[currentLobby] == 3) {
                          playerNum = 4;
                          startTime = 1;
                      }
                      Toast.makeText(getApplicationContext(), "Joined Game 1 Lobby!", Toast.LENGTH_SHORT).show();
                      numPlayers[currentLobby]++;
                      update(game1Text, game2Text, game3Text, game4Text, game5Text, game6Text);
                      MainActivity.player = playerNum;
                      connectToGame(username, currentLobby);
                      setTurn(playerNum, currentLobby+1);
                  }
              }
        });
        game6Button.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  //TODO Join game lobby 6
                  if (numPlayers[5] == 4)
                  {
                      Toast.makeText(getApplicationContext(), "Game is Full!", Toast.LENGTH_SHORT).show();
                  }
                  else
                  {
                      if (currentLobby != 6)
                          numPlayers[currentLobby]--;
                      currentLobby = 5;
                      if (numPlayers[currentLobby] == 0) {
                          playerNum = 1;
                          startTime = 1;

                      }
                      else if (numPlayers[currentLobby] == 1) {
                          playerNum = 2;
                          startTime = 1;
                      }
                      else if (numPlayers[currentLobby] == 2) {
                          playerNum = 3;
                          startTime = 1;
                      }
                      else if (numPlayers[currentLobby] == 3) {
                          playerNum = 4;
                          startTime = 1;
                      }
                      Toast.makeText(getApplicationContext(), "Joined Game 1 Lobby!", Toast.LENGTH_SHORT).show();
                      numPlayers[currentLobby]++;
                      update(game1Text, game2Text, game3Text, game4Text, game5Text, game6Text);
                      connectToGame(username, currentLobby);
                      setTurn(playerNum, currentLobby+1);
                  }
              }
        });
        */
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(numPlayers[currentLobby] < 4){
                    Toast.makeText(getApplicationContext(), "Waiting for four players", Toast.LENGTH_SHORT).show();
                }
               else {
                    //connectToGame(username, currentLobby);
                    //setTurn(playerNum, currentLobby+1);
                    if (count == 0) {
                        Intent service = new Intent(getApplicationContext(), ServerService.class);
                        //service.putExtra("MYUSERNAME",mUsername);
                        //service.putExtra("OTHERUSERNAMES", "meat:stupid:milk");
                        String temp = otherUsers.trim().replaceAll("\\s","");
                        otherUsers = temp;
                        Log.d("myuser", mUsername);
                        Log.d("otherusers", roomData);
                        Log.d("roomdata", roomData);

                        usernameList = otherUsers.split(":");

                        startService(service);

                        Log.v("Service", "Service is starting");
                    }

                    if (count == 1) {
                        Intent i = new Intent(getBaseContext(), MainActivity.class);
                        i.putExtra("USERNAME", username);

                        i.putExtra("attribute1", attributeP1);
                        i.putExtra("attribute2", attributeP2);
                        i.putExtra("attribute3", attributeP3);
                        i.putExtra("attribute4", attributeP4);
                        startActivity(i);

                    }
                    count++;

                }
            }
        });
        update(game1Text);

    }


    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle b = intent.getExtras();

            if (b != null) {
                attributeP1 = intent.getStringArrayExtra("attribute1");
                attributeP2 = intent.getStringArrayExtra("attribute2");
                attributeP3 = intent.getStringArrayExtra("attribute3");
                attributeP4 = intent.getStringArrayExtra("attribute4");
            }
        }
    };

    @Override
    public void onResume(){
        super.onResume();
        //registerReceiver(statusReceiver, mIntent);
        handy.postDelayed(runny, 2500);
        LocalBroadcastManager.getInstance(StartMenuActivity.this).registerReceiver(broadcastReceiver, new IntentFilter("NOW"));
    }

    @Override
    public void onPause(){
        if(mIntent != null) {
            unregisterReceiver(statusReceiver);
            mIntent = null;
        }
        handy.removeCallbacks(runny);
        super.onPause();
    }

    @Override
    public void onFragmentInteraction(Uri uri)
    {

    }

    /**
     * creates the dashboard based on the username's character information from the user table.
     * @param username
     * @return
     */
    public String[] makeDashboard(String username)
    {
        mUsername = username;
        String url = "http://proj-309-gp-b-2.cs.iastate.edu/dash.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {
                        String format = response.toString().substring(1).trim();
                        String data[] = format.split(":");
                        mClass = data[0];
                        mHealth = Integer.parseInt(data[1]);
                        mStrength = Integer.parseInt(data[2]);
                        mIntellect = Integer.parseInt(data[3]);
                        mAgility = Integer.parseInt(data[4]);
                        mCharLevel = Integer.parseInt(data[5]);
                        mCharXp = Integer.parseInt(data[6]);
                        FragmentTransaction fT = getFragmentManager().beginTransaction();
                        charValFrag = CharacterValueFragment.newInstance(mClass, mHealth, mStrength, mIntellect, mAgility, mCharLevel, mCharXp);
                        fT.add(R.id.characterInfoContainer, charValFrag);
                        fT.commit();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error){

                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", mUsername);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        return null;
    }

    public void update(TextView text1)
    {
        tableCheck();

        text1.setText("Game Lobby 1\nNumber of Players: " + numPlayers[0]);
    }

    /**
     * Updates the game based on changes.
     * @param callback volley callback to store data
     * @param whichGame game to update.
     */
    public void gameUpdater(final VolleyCallback callback, final String whichGame)
    {
        String url = "http://proj-309-gp-b-2.cs.iastate.edu/update_game_one.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response){
                        callback.onSuccess(response);

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
                params.put("game", whichGame);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


        //TODO Insert method here to retrieve number of players in each game lobby and populate numPlayers array with these values
    }

    public void gameoneUpdate(){
        gameUpdater(new VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                final String temp = result.trim();
                numPlayers[0] = Integer.parseInt(temp);
                update(game1Text);
            }
        }, "game_one");

    }
/*
    public void gametwoUpdate(){
        gameUpdater(new VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                final String temp = result.trim();
                numPlayers[1] = Integer.parseInt(temp);
                update(game1Text, game2Text, game3Text, game4Text, game5Text, game6Text);
            }
        }, "game_two");

    }
    public void gamethreeUpdate(){
        gameUpdater(new VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                final String temp = result.trim();
                numPlayers[2] = Integer.parseInt(temp);
                update(game1Text, game2Text, game3Text, game4Text, game5Text, game6Text);
            }
        }, "game_three");

    }

    public void gamefourUpdate(){
        gameUpdater(new VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                final String temp = result.trim();
                numPlayers[3] = Integer.parseInt(temp);
                update(game1Text, game2Text, game3Text, game4Text, game5Text, game6Text);
            }
        }, "game_four");

    }
    public void gamefiveUpdate(){
        gameUpdater(new VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                final String temp = result.trim();
                numPlayers[4] = Integer.parseInt(temp);
                update(game1Text, game2Text, game3Text, game4Text, game5Text, game6Text);
            }
        }, "game_five");

    }

    public void gamesixUpdate(){
        gameUpdater(new VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                final String temp = result.trim();
                numPlayers[5] = Integer.parseInt(temp);
                update(game1Text, game2Text, game3Text, game4Text, game5Text, game6Text);
            }
        }, "game_six");

    }
    */

    /**
     * connects the user to the game table
     * @param username username to connect with
     * @param game_num game lobby to put the user in
     */
    public void connectToGame(String username, int game_num){
        Log.v(TAG, username);
        String game = "";
        if(game_num == 0) {
            game = "game_one";
        }
        else if(game_num == 1){
            game = "game_two";
        }
        else if(game_num == 2){
            game = "game_three";
        }
        else if(game_num == 3){
            game = "game_four";
        }
        else if(game_num == 4){
            game = "game_five";
        }
        else if(game_num == 5){
            game = "game_six";
        }
        String url = "http://proj-309-gp-b-2.cs.iastate.edu/game_connection.php?username="+username+"&game_num="+game;
        Log.v(TAG, url);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                      /*  //response.toString();
                        TextView t = (TextView) findViewById(R.id.textView2);
                        t.setText(response.toString());
                        t.setVisibility(View.VISIBLE);*/
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    /**
     * unused
     */
    public void attrCallback() {
        getAttributes(new VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                final String temp = result.trim();
                attributes = temp.split(":");
            }
        }, "test", "test2");
    }

    /**
     * gets the attributes of the current user's character
     * @param callback volley callback to store data
     * @param username username to retrieve from
     * @param game_table game lobby to check
     */
    public void getAttributes(final VolleyCallback callback, String username, String game_table){

        String url = "http://proj-309-gp-b-2.cs.iastate.edu/PLACEHOLDER.php?username="+username+"&game_table="+game_table;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        callback.onSuccess(response);
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
     * Sets the turn of the players joining the game.
     * @param turnNumber turn number to set
     * @param game_num game lobby the player is entering.
     */
    public void setTurn(final int turnNumber, int game_num){

        String game = "";
        if(game_num == 0) {
            game = "game_one";
        }
        else if(game_num == 1){
            game = "game_two";
        }
        else if(game_num == 2){
            game = "game_three";
        }
        else if(game_num == 3){
            game = "game_four";
        }
        else if(game_num == 4){
            game = "game_five";
        }
        else if(game_num == 5){
            game = "game_six";
        }
        final String temp = game;
        String url = "http://proj-309-gp-b-2.cs.iastate.edu/setturn.php";
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
                params.put("myturn", Integer.toString(turnNumber));
                params.put("game", temp);
                params.put("username", mUsername);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    /**
     * Searches for the username.
     * @param callback
     */
    public void usernameSearch(final VolleyCallback callback){
        String url = "http://proj-309-gp-b-2.cs.iastate.edu/usernameSearch.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response){
                    callback.onSuccess(response);

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
                params.put("username", mUsername);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    /**
     * Checks the current table.
     */
    public void tableCheck(){
        usernameSearch(new VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                final String temp = result.trim();

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        tableCheck = Integer.parseInt(temp);
                        currentLobby = 0;
                        if (startTime == 1 || tableCheck > 0)
                        {
                            startButton.setVisibility(View.VISIBLE);
                        }
                        else
                        {
                            startButton.setVisibility(View.INVISIBLE);
                        }
                    }
                });



            }
        });
    }

    /**
     * Finds other users in the database.
     */
    public void findotherUsers(){
        String url = "http://proj-309-gp-b-2.cs.iastate.edu/findotherUsers.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response){
                        StartMenuActivity.otherUsers = response.trim();


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
                params.put("username", mUsername);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    /**
     * Pulls the room data from a random room.
     */
    public void pullRoomData(){

        Random rand = new Random();
        int room_num = rand.nextInt(10) + 1;
        String url = "http://proj-309-gp-b-2.cs.iastate.edu/map_pull.php?map_num="+(room_num+"");
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response){
                        StartMenuActivity.roomData = response.trim();
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
