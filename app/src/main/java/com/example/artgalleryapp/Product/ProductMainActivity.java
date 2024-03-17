package com.example.artgalleryapp.Product;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.artgalleryapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProductMainActivity extends AppCompatActivity
{
    Button upload;
    RecyclerView recyclerView;

    ArrayList<ProductModule> recycleList;

    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_main);

        upload = findViewById(R.id.upload);
        recyclerView = findViewById(R.id.recyclerview);
        recycleList = new ArrayList<>();

        firebaseDatabase = FirebaseDatabase.getInstance();

        ProductAdapter recyclerAdapter = new ProductAdapter(recycleList, getApplicationContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(recyclerAdapter);

        firebaseDatabase.getReference().child("Product").addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange (@NonNull DataSnapshot snapshot)
            {
                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
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



        upload.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                Intent intent = new Intent(ProductMainActivity.this, UploadProductActivity.class);
                startActivity(intent);
            }
        });

    }
}