package com.example.matthewburke.partyat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/** This class prompts the user to pick which kind of user they are going to sign in as. **/
public class UserType extends AppCompatActivity {

    Button user;
    Button business;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_type);

        user = (Button)findViewById(R.id.userLogIn);
        business = (Button)findViewById(R.id.businessLogIn);



        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(UserType.this,
                        CheckUserName.class);
                startActivity(myIntent);
            }
        });

        business.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                Intent myIntent = new Intent(UserType.this,
                        CheckBusinessName.class);
                startActivity(myIntent);
            }
        });



    }
}
