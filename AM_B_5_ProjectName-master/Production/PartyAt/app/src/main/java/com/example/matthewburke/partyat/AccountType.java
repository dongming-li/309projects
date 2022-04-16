package com.example.matthewburke.partyat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/** This class checks what type of user is to be made, as a user and business are not insterted
 * into the same table.**/
public class AccountType extends AppCompatActivity {

    Button user;
    Button business;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_type);

        user = (Button)findViewById(R.id.insertNewUser);
        business = (Button)findViewById(R.id.insertNewBusiness);

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(AccountType.this,
                        InsertUser.class);
                startActivity(myIntent);
            }
        });

        business.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                Intent myIntent = new Intent(AccountType.this,
                        InsertBusiness.class);
                startActivity(myIntent);
            }
        });



    }

}