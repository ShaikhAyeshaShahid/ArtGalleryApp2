package com.example.artgalleryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

//import com.example.artgalleryapp.HomeAdapter.FeaturedAdapter;
//import com.example.artgalleryapp.HomeAdapter.FeaturedHelperClass;
//import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {

    RecyclerView featuredRecycler;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);
    //Hooks
        featuredRecycler = findViewById(R.id.featured_recycler);
        featuredRecycler();

    }

    private void featuredRecycler()
    {
        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

       /* ArrayList<FeaturedHelperClass> featureArts = new ArrayList<FeaturedHelperClass>();
        featureArts.add(new FeaturedHelperClass(R.drawable.abstract1, "Texture and Paints 1" , "Abstract art texture paint design with the combination of grey"));
        featureArts.add(new FeaturedHelperClass(R.drawable.abstract2, "Texture and Paints 2" , "Abstract art texture paint design with the combination of grey"));
        featureArts.add(new FeaturedHelperClass(R.drawable.abstract3, "Texture and Paints 3" , "Abstract art texture paint design with the combination of grey"));
        featureArts.add(new FeaturedHelperClass(R.drawable.abstract4, "Texture and Paints 4" , "Abstract art texture paint design with the combination of grey"));
        featureArts.add(new FeaturedHelperClass(R.drawable.abstract3, "Texture and Paints 5" , "Abstract art texture paint design with the combination of grey"));

        adapter = new FeaturedAdapter(featureArts);
        featuredRecycler.setAdapter(adapter);

        GradientDrawable gradient1 = new GradientDrawable(GradientDrawable.Orientation.LEFT_RIGHT, new int[]{0xffeff400, 0xffaff600}); */

    }
}