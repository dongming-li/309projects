package com.example.mithi.battleoftheworldsmyway;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//import com.github.nkzawa.socketio.client.IO;
//import com.github.nkzawa.socketio.client.Socket;
//import com.github.nkzawa.emitter.Emitter;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.URISyntaxException;

/**
 * Created by Mithil Shah and Aaron Rees on 12/4/2017.
 */

/**
 * Class for executing the login activity
 */
public class MainActivity extends AppCompatActivity {

    /**
     * main method for executing the login activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Login");

        Typeface exocet = ResourcesCompat.getFont(getApplicationContext(), R.font.exocet);

        final EditText usernameField = findViewById(R.id.username_input);
        usernameField.setTypeface(exocet);
        final Button submit = findViewById(R.id.submit);
        final EditText passwordField = findViewById(R.id.password_input);
        passwordField.setTypeface(exocet);

        final TextView newUser = findViewById(R.id.newUser);
        newUser.setOnClickListener(new TextView.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent newUser = new Intent(MainActivity.this, NewUser.class);
                startActivity(newUser);
            }
        });

        submit.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                if(passwordField.getText().toString().equals("") && usernameField.getText().toString().equals(""))
                {
                    Toast.makeText(MainActivity.this, "Please enter your username and password", Toast.LENGTH_SHORT).show();
                }
                else if(usernameField.getText().toString().equals(""))
                {
                    Toast.makeText(MainActivity.this, "Please enter your username", Toast.LENGTH_SHORT).show();
                }
                else if(passwordField.getText().toString().equals(""))
                {
                    Toast.makeText(MainActivity.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    //Add socket stuff here and send login information to server
                    CharacterProfile.username = usernameField.getText().toString();
                    Intent characterSelection = new Intent(MainActivity.this, CharacterClassSelection.class);
                    startActivity(characterSelection);
                }
            }
        });

//        //establishes server connection
//        private static Socket mSocket;
//        {
//            try {
//                mSocket = IO.socket("http://10.25.70.50:8080");
//            }
//            catch (URISyntaxException e)
//            {
//                e.printStackTrace();
//            }
//        }
//
//        /**
//         * method for sending account information to the server to access account data for login.
//         */
//        private void attemptSend(){
//            try{
//                String user = usernameMainInput.getText().toString().trim();
//                String pswd = passwordMainInput.getText().toString().trim();
//
//                JSONObject json = new JSONObject();
//                json.putOpt("username",user);
//                json.putOpt("password",pswd);
//
//                mSocket.emit("login",json);
//
//                mSocket.on("success",  new Emitter.Listener() {
//                    @Override
//                    public void call(final Object... args) {
//                        //starting game activity
//                        startActivity(new Intent(MainActivity.this,AccountHome.class));
//                    }
//                });
//                mSocket.on("failure", new Emitter.Listener() {
//
//                            @Override
//                            public void call(Object... args) {
//
//                                Toast.makeText(MainActivity.this, "Username or Password feild is incorrect",
//                                        Toast.LENGTH_LONG).show();
//
//                            }
//                        }
//                );
//            }catch (JSONException e){
//                Toast.makeText(MainActivity.this, "There is no Internet Connection", Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        /**
//         * method for returning the socket for use on other activities or classes
//         * @return
//         * socket for server communication
//         */
//        public static Socket returnSocket(){
//            return mSocket;
//        }
//
    }
}
