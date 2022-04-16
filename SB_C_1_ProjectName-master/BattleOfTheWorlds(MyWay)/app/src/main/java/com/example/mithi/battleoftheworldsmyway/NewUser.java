package com.example.mithi.battleoftheworldsmyway;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Mithil Shah and Aaron Rees on 12/4/2017.
 */

/**
 * Class for creating a new user in the game
 */
public class NewUser extends MainActivity
{
    /**
     * main method for the new user activity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_new);
        getSupportActionBar().setTitle("Create Account");

        final Button create = findViewById(R.id.signUpButton);
        final EditText username = findViewById(R.id.createUsername);
        final EditText password = findViewById(R.id.createPassword);
        final EditText email = findViewById(R.id.emailAddress);

        create.setOnClickListener(new Button.OnClickListener()
        {
            public void onClick(View v)
            {
                if(username.getText().toString().equals("") && password.getText().toString().equals("") && email.getText().toString().equals(""))
                {
                    Toast.makeText(NewUser.this, "Please enter your credentials", Toast.LENGTH_SHORT).show();
                }
                else if(username.getText().toString().equals("") && password.getText().toString().equals(""))
                {
                    Toast.makeText(NewUser.this, "Please enter your username and password", Toast.LENGTH_SHORT).show();
                }
                else if(email.getText().toString().equals("") && password.getText().toString().equals(""))
                {
                    Toast.makeText(NewUser.this, "Please enter your email address and password", Toast.LENGTH_SHORT).show();
                }
                else if(username.getText().toString().equals("") && email.getText().toString().equals(""))
                {
                    Toast.makeText(NewUser.this, "Please enter your email address and username", Toast.LENGTH_SHORT).show();
                }
                else if(username.getText().toString().equals(""))
                {
                    Toast.makeText(NewUser.this, "Please enter your username", Toast.LENGTH_SHORT).show();
                }
                else if(password.getText().toString().equals(""))
                {
                    Toast.makeText(NewUser.this, "Please enter your password", Toast.LENGTH_SHORT).show();
                }
                else if(email.getText().toString().equals(""))
                {
                    Toast.makeText(NewUser.this, "Please enter your email address", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(android.util.Patterns.EMAIL_ADDRESS.matcher(email.getText()).matches())
                    {
                        //Socket stuff and database stuff
                        Intent newUser = new Intent(NewUser.this, MainActivity.class);
                        startActivity(newUser);
                    }
                    else
                    {
                        Toast.makeText(NewUser.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
