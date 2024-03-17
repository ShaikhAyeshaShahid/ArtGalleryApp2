package com.example.artgalleryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class RecyclerCatActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList <Categories> categoriesArrayList;
    CatAdapter catAdapter;
    String[] categoryHeading;
    int[] imageResourceId;


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_cat);
        recyclerView= findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        categoriesArrayList = new ArrayList<Categories>();

        catAdapter = new CatAdapter(this,categoriesArrayList);
        recyclerView.setAdapter(catAdapter);

        categoryHeading = new String[]{
                "Abstract Art", "Digital Art", "Digital Portait", "Calligraphy", "Islamic Art", "Sufism", "Pencil Charcoal", "Ceramic Art"

        };
        imageResourceId = new int[]
                {
                        R.drawable.icons_abstract,
                        R.drawable.icons_digitalart,
                        R.drawable.icons_digitalportrait,
                        R.drawable.icons_calligraphy,
                        R.drawable.icons_islamic,
                        R.drawable.icon_darwaish,
                        R.drawable.icons8_pencilcharcoal,
                        R.drawable.icons8_ceramic,

                };
        getData();

    }

    private void getData ( ) {
        for (int i=0; i<categoryHeading.length; i++)
        {
            Categories categories = new Categories(categoryHeading[i], imageResourceId[i]);
            categoriesArrayList.add(categories);
        }

        catAdapter = new CatAdapter(this,categoriesArrayList);
        recyclerView.setAdapter(catAdapter);
        catAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {

        getMenuInflater().inflate(R.menu.menu_item,menu);
        MenuItem menuItem = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Search");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit (String query) {



                return false;
            }

            @Override
            public boolean onQueryTextChange (String newText) {

                catAdapter.getFilter().filter(newText);

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}