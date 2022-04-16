package mainmenu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mlscholl.wickedchambers.GameActivity;
import com.example.mlscholl.wickedchambers.R;

import java.util.HashMap;
import java.util.Map;

import game.ImmersiveModeFragment;

/**
 * Created by mlscholl on 10/1/2017.
 */

public class MainMenuActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String TAG = "MainActivity";

    public static final String FRAGTAG = "ImmersiveModeFragment";

    private Button play_button;
    private Button settings_button;
    private Button quit_button;

    private String data;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_mainmenu);

        if (getSupportFragmentManager().findFragmentByTag(FRAGTAG) == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            ImmersiveModeFragment fragment = new ImmersiveModeFragment();
            transaction.add(fragment, FRAGTAG);
            transaction.commit();
        }

        settings_button = (Button) findViewById(R.id.settings_button);
        settings_button.setOnClickListener(this);

        play_button = (Button) findViewById(R.id.play_button);
        play_button.setOnClickListener(this);

        quit_button = (Button) findViewById(R.id.quit_button);
        quit_button.setOnClickListener(this);

        data = getIntent().getStringExtra("RESPONSE");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.settings_button: {
                break;
            }

            case R.id.play_button: {
                Intent i = new Intent(MainMenuActivity.this, GameActivity.class);
                i.putExtra("DATA", data);
                //game_connect();
                startActivity(i);
                break;
            }
            case R.id.quit_button: {
                finish();
                break;
            }
        }
    }

    /**
     * This is never used.
     */
    private void game_connect() {
        String delims = "[ ]:-";
        final String[] tokens = data.split(delims);

        String url = "http://proj-309-gp-b-2.cs.iastate.edu/game_connection.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //response.toString();
                        TextView t = (TextView) findViewById(R.id.textView2);
                        t.setText(response.toString());
                        t.setVisibility(View.VISIBLE);
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
                params.put("username", tokens[1]);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
