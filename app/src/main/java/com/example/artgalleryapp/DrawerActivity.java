package com.example.artgalleryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.artgalleryapp.Cart.CartActivity;
import com.example.artgalleryapp.Category.CategoryFragment;
import com.example.artgalleryapp.WelcomeScreen.LoginActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DrawerActivity extends AppCompatActivity
{

    DrawerLayout drawerLayout;
    NavigationView dashboardView;
    Toolbar toolbar;
    TextView userNameTextView, userEmail;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;


    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer);

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("Registration");

        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        dashboardView = findViewById(R.id.dashboardView);
        userNameTextView = findViewById(R.id.user_profile_name);
        userEmail = findViewById(R.id.user_email);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DrawerActivity.this,CartActivity.class);
                startActivity(intent);
            }
        });

        //View headerView = dashboardView.getHeaderView(0);
        //userNameTextView.setText(Prevalent.currentOnlineUser.getName());


        //step 1
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.OpenDrawer, R.string.CloseDrawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        loadFragment(new ExploreFragment());


        dashboardView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected (@NonNull MenuItem item)
            {

                int id = item.getItemId();

                if (id == R.id.opt_explore) {
                    loadFragment(new ExploreFragment());
                } else if (id == R.id.account) {
                    Intent intent = new Intent(DrawerActivity.this, UserProfile.class);
                    startActivity(intent);
                    // loadFragment(new AccountFragment());
                } else if (id == R.id.cart) {
                    Intent intent = new Intent(DrawerActivity.this, CartActivity.class);
                    startActivity(intent);
                    //loadFragment(new CartFragment());
                } else if (id == R.id.category) {
                    loadFragment(new CategoryFragment());
                    //Toast.makeText(Dashboard.this, "Home",Toast.LENGTH_SHORT).show();
                } else if (id == R.id.message) {
                    Intent intent = new Intent(DrawerActivity.this, MessageChat.class);
                    startActivity(intent);
                    //loadFragment(new MessageFragment());
                } else if (id == R.id.opt_setting) {
                    Intent intent = new Intent(DrawerActivity.this, SettingActivity.class);
                    startActivity(intent);
                    //loadFragment(new SettingFragment());
                } else if (id == R.id.opt_location) {
                    loadFragment(new LocationFragment());
                } else if (id == R.id.share) {
                    loadFragment(new ShareFragment());
                } else if (id == R.id.rate) {
                    Intent intent = new Intent(DrawerActivity.this, RateActivity.class);
                    startActivity(intent);
                    //loadFragment(new RateFragment());
                }
               /* else if (id == R.id.Trackorder) {
                    loadFragment(new LogoutFragment());
                } */
                else {
                    Toast.makeText(DrawerActivity.this, "setting", Toast.LENGTH_SHORT).show();
                }

                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    private void loadFragment (LocationFragment locationFragment)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.container, locationFragment);
        ft.commit();

    }

    @Override
    public void onBackPressed ( )
    {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
            Intent intent = new Intent(DrawerActivity.this, LoginActivity.class );
            startActivity(intent);
        } else {
            super.onBackPressed();
        }
    }


    private void loadFragment (ExploreFragment EFragment)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.container, EFragment);
        ft.commit();
    }

    private void loadFragment (CategoryFragment CFragment)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.container, CFragment);
        ft.commit();
    }

   /* private void loadFragment(CartFragment CaFragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.container, CaFragment);
        ft.commit();

    }*/

    private void loadFragment (ShareFragment SFragment)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.container, SFragment);
        ft.commit();
    }

    /* private void loadFragment(RateFragment RFragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.container, RFragment);
        ft.commit();
    } */

   /* private void loadFragment(LogoutFragment LFragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.container, LFragment);
        ft.commit();

    } */
}