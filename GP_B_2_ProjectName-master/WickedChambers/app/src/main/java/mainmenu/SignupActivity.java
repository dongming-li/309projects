package mainmenu;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
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

import characters.CharCreate;
import rooms.RoomGui;
import skillfiles.SkillTree;

/**
*   This class details the implementation of the SignupActivity class. This is the class that users will interact with if they wish to create a new username and password (associated with
*   a new CharCreate object which will also be created and stored in the database along with their unique username and password).
 */
public class SignupActivity extends AppCompatActivity {
    private String TAG = SignupActivity.class.getSimpleName();
    private String tag_string_req = "string_req";
    private boolean isGameMaster;
    private String t = "";
    EditText Username, Password;
    Spinner Class;
    int class_id;
    Button registerButton;
    CharCreate createdCharacter;
    String skill;
    String inv = "0i0i0i0i0i0i0i0i0i0i0i0i0i0i0i0i0i0i0i0i0i0i0i0i0i0";
    /**
    *   Creates and links the SignupActivity class to the relevant activity_register.xml file and sets up listeners for the entailed widgets.
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        class_id = 3;
        Class = (Spinner) findViewById(R.id.classS);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.class_choices, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Class.setAdapter(adapter);
        Class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Object sel = adapterView.getItemAtPosition(i);
                if (sel.toString().equals("Warrior")) {
                    t = "w";
                    class_id = 0;

                } else if (sel.toString().equals("Mage")) {
                    t = "m";
                    class_id = 1;


                } else if (sel.toString().equals("Rogue")) {
                    t = "r";
                    class_id = 2;


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //DO NOTHING :D
            }
        });
        Username = (EditText) findViewById(R.id.userN);
        Password = (EditText) findViewById(R.id.passW);

        registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });
    }
    /**
    *   Creates a new entry in the database for the new information (simultaneously checking to see if said information already exists, in which case the entry is rejected) containing
    *   the given username, password, class, and default stat values associated with that class.
     */
    public void signUp() {
            if (class_id != 3) {
                String username = Username.getText().toString();
                String password = Password.getText().toString();
                if (class_id == 0) {
                    createdCharacter = new CharCreate('w');
                    skill = SkillTree.generateWarriorSkillTree().serialize();
                }
                else if (class_id == 1) {
                    createdCharacter = new CharCreate('m');
                    skill = SkillTree.generateMageSkillTree().serialize();
                }
                else if (class_id == 2) {
                    createdCharacter = new CharCreate('r');
                    skill = SkillTree.generateRogueSkillTree().serialize();
                }
                //To add to database: type, health, strength, intellect, agility, level, xp, damage, statScale
                final String type, health, strength, intellect, agility, level, xp, damage, statScale;
                type = createdCharacter.getType();
                health = "" + createdCharacter.getHealth();
                strength = "" + createdCharacter.getStrength();
                intellect = "" + createdCharacter.getIntellect();
                agility = "" + createdCharacter.getAgility();
                level = "" + createdCharacter.getCharLevel();
                xp = "" + createdCharacter.getCharXP();
                damage = "" + createdCharacter.damage;
                statScale = "" + createdCharacter.mainStatScale;
                Toast.makeText(this, "New " + createdCharacter.getType() + " character created!", Toast.LENGTH_SHORT).show();
                //Following line(s) is/are subject to change based on database organization

                String url = "http://proj-309-gp-b-2.cs.iastate.edu/push.php";
                Map<String, String> params = new HashMap<String, String>();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                //response.toString();
                                TextView t = (TextView)findViewById(R.id.textView2);
                                t.setText(response.toString());
                                t.setVisibility(View.VISIBLE);
                                Log.d("confirm", response);
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }
                ){
                    @Override
                    protected Map<String,String> getParams(){
                        Map<String,String> params = new HashMap<String, String>();

                        params.put("username", Username.getText().toString().trim());
                        params.put("password", Password.getText().toString().trim());
                        params.put("type", t);
                        params.put("charactername", "billy");
                        params.put("health", health);
                        params.put("intellect", intellect);
                        params.put("strength", strength);
                        params.put("agility", agility);
                        params.put("level", level);
                        params.put("exp", xp);
                        params.put("inv", inv);
                        params.put("skill", skill);
                        return params;
                    }

                };

                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);
            }
    }
}
