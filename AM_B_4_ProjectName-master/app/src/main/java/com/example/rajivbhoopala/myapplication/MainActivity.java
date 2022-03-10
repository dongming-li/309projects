package com.example.rajivbhoopala.myapplication;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/**
 * Created by rajivbhoopala on  10/4/17.
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Get login status code
        Bundle bundle = getIntent().getExtras();
        int login_status = bundle.getInt("login_status");

        switch(login_status) {

            case 1: // Status 1: Fitness trainer
                navigationView.getMenu().findItem(R.id.nav_account).setVisible(true);
                navigationView.getMenu().findItem(R.id.nav_exercise_catalog).setVisible(true);
                navigationView.getMenu().findItem(R.id.nav_chat_with_user).setVisible(true);
                navigationView.getMenu().findItem(R.id.nav_logout).setVisible(true);
                break;

            case 2: // Status 2: Admin
                navigationView.getMenu().findItem(R.id.nav_account).setVisible(true);
                navigationView.getMenu().findItem(R.id.nav_member_management).setVisible(true);
                navigationView.getMenu().findItem(R.id.nav_logout).setVisible(true);
                break;

            case 3: // Status 3: User
                navigationView.getMenu().findItem(R.id.nav_account).setVisible(true);
                navigationView.getMenu().findItem(R.id.nav_weight).setVisible(true);
                navigationView.getMenu().findItem(R.id.nav_calendar).setVisible(true);
                navigationView.getMenu().findItem(R.id.nav_track).setVisible(true);
                navigationView.getMenu().findItem(R.id.nav_fitness).setVisible(true);
                navigationView.getMenu().findItem(R.id.nav_logout).setVisible(true);
                break;
        }

        // Launch my account my default
        fragment = new MyAccount();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_frame, fragment);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
       }else{
            if(fragment instanceof MyAccount ){
                super.onBackPressed();
            }
        }

    }

    /**
     * Shows My Account fragment when back button is pressed.
     */
    private void showMyAccount(){
        fragment = new MyAccount();
        if(fragment != null){
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().replace(R.id.activity_main, fragment, fragment.getTag()).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Will display the fragment based on navigation drawer click
     * @param id Whichever fragment is related to ID
     */
    private void displaySelectedScreen(int id){
        Fragment fragment = null;

        switch(id){

            // User IDs
            case R.id.nav_account:
                fragment = new MyAccount();
                break;

            case R.id.nav_calendar:
                fragment = new Calendar();
                break;

            case R.id.nav_fitness:
                fragment = new FitnessHelp();
                break;

            case R.id.nav_weight:
                fragment = new WeightTracker();
                break;

            case R.id.nav_track:
                fragment = new Track();
                break;

            // Admin ID
            case R.id.nav_member_management:
                fragment = new MemberManagement();
                break;

            case R.id.nav_chat_with_user:
                fragment = new ChatWithUser();
                break;

            case R.id.nav_exercise_catalog:
                fragment = new ExerciseCatalog();
                break;

            case R.id.nav_logout:
                new Utility(MainActivity.this).launchActivity(MainActivity.this, LoginActivity.class);
                break;

            default:
                fragment = new MyAccount();
                break;
        }

        if(fragment!= null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        displaySelectedScreen(id);
        return true;

    }


}