package com.example.artgalleryapp.Category;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.example.artgalleryapp.CustomizationActivity;
import com.example.artgalleryapp.Product.ProductAdapter;
import com.example.artgalleryapp.Product.ProductModule;
import com.example.artgalleryapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class IslamicArtActivity extends AppCompatActivity
{
    private Button cart;
    private Button custom;

    RecyclerView recyclerView;

    ArrayList<ProductModule> recycleList;

    FirebaseDatabase firebaseDatabase;


    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_islamic_art);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        recyclerView = findViewById(R.id.recyclerview);
        recycleList = new ArrayList<>();

        firebaseDatabase = FirebaseDatabase.getInstance();

        ProductAdapter recyclerAdapter = new ProductAdapter(recycleList, getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(recyclerAdapter);

        firebaseDatabase.getReference().child("CeramicArt").addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange (@NonNull DataSnapshot snapshot)
            {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    ProductModule projectModule = dataSnapshot.getValue(ProductModule.class);
                    recycleList.add(projectModule);
                }
                recyclerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled (@NonNull DatabaseError error)
            {

            }
        });

       /* custom = (Button) findViewById(R.id.btn_islcustom1);
        custom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                custom();
            }
        });*/

    }

   /* private void custom() {
        Intent intent = new Intent(IslamicArtActivity.this, CustomizationActivity.class);
        startActivity(intent);

    }*/
}