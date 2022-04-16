package com.example.matthewburke.partyat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;


/** This class is the main screen for the user. Lets the user check in on the main screen.
 * Allows user to swipe left to interact with friends, swipe right to see all promotions, and up to edit their profile. **/
public class MenuScreen extends AppCompatActivity {
    RelativeLayout listView;
    SharedPreferences USERTYPE;
    String userType;


    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);
        listView = (RelativeLayout) findViewById(R.id.menullout);
        USERTYPE = getApplicationContext().getSharedPreferences(getString(R.string.PREFERENCES), Context.MODE_PRIVATE);
        userType = USERTYPE.getString(getString(R.string.USERTYPE),"null");
        //checkIn = (Button) findViewById(R.id.btnCheckIn);
        Log.d("usertype", userType);


        listView.setOnTouchListener(new OnSwipeTouchListener(MenuScreen.this) {

                public void onSwipeTop(){
                    //Toast.makeText(MenuScreen.this, "top", Toast.LENGTH_SHORT).show();
                    Intent myIntent = new Intent(MenuScreen.this,
                            NearbyEstablishments.class);
                    startActivity(myIntent);
                }
                public void onSwipeRight() {
                    if(userType.equals("b")) {
                        Intent myIntent = new Intent(MenuScreen.this,
                                MakePromotion.class);
                        startActivity(myIntent);
                    }
                    else{
                        Intent myIntent = new Intent(MenuScreen.this,
                                FriendsList.class);
                        startActivity(myIntent);
                    }
                }
                public void onSwipeLeft() {
                    Intent myIntent = new Intent(MenuScreen.this,
                            PromotionsList.class);
                    startActivity(myIntent);
                }
                public void onSwipeBottom() {
                    Intent myIntent = new Intent(MenuScreen.this,
                            UserProfile.class);
                    startActivity(myIntent);
                }
            });
    }

}
