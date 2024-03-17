package com.example.artgalleryapp;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.artgalleryapp.Category.AbstractActivity;
import com.example.artgalleryapp.Category.CalligraphyActivity;
import com.example.artgalleryapp.Category.CeramicArtActivity;
import com.example.artgalleryapp.Category.DigitalArtActivity;
import com.example.artgalleryapp.Category.DigitalPotraitActivity;
import com.example.artgalleryapp.Category.EducationActivity;
import com.example.artgalleryapp.Category.HotelActivity;
import com.example.artgalleryapp.Category.IslamicArtActivity;
import com.example.artgalleryapp.Category.PencilCharcoalActivity;
import com.example.artgalleryapp.Category.ResturantActivity;
import com.example.artgalleryapp.Category.ShopActivity;
import com.example.artgalleryapp.Category.SufismActivity;
import com.example.artgalleryapp.Product.ProductAdapter;
import com.example.artgalleryapp.Product.ProductModule;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ExploreFragment extends Fragment
{
    SearchView mySearchView;
    ListView myList;

    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    List<Categories> allFilterCategories;

    TextView tvFilter;

    RecyclerView recyclerView;

    ArrayList<ProductModule> recycleList;
    FirebaseDatabase firebaseDatabase;


    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_explore, container, false);

        mySearchView = view.findViewById(R.id.SearchView);
        recyclerView = view.findViewById(R.id.newrecyclerview);


        recycleList = new ArrayList<>();

        firebaseDatabase = FirebaseDatabase.getInstance();

        ProductAdapter recyclerAdapter = new ProductAdapter(recycleList, this.getContext());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(recyclerAdapter);

        firebaseDatabase.getReference().child("Product").addListenerForSingleValueEvent(new ValueEventListener()
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


        mySearchView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {

            }
        });

        myList = view.findViewById(R.id.myList);
        list = new ArrayList<String>();

        list.add("Abstract Art");
        list.add("Ceramic Art");
        list.add("Calligraphy");
        list.add("Darweish and Sufism");
        list.add("Charcoal and Pencil");
        list.add("Islamic Art");
        list.add("Digital Art");
        list.add("Digital potrait");
        list.add("Resturants");
        list.add("Shop");
        list.add("Hotels");
        list.add("Educational");

        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);
        myList.setAdapter(adapter);

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick (AdapterView<?> parent, View view, int position, long id)
            {

                String item = (String) parent.getItemAtPosition(position);
                switch (item) {
                    case "Abstract Art":
                        startActivity(new Intent(getActivity(), AbstractActivity.class));
                        break;
                    case "Ceramic Art":
                        startActivity(new Intent(getActivity(), CeramicArtActivity.class));
                        break;
                    case "Charcoal and Pencil":
                        startActivity(new Intent(getActivity(), PencilCharcoalActivity.class));
                        break;
                    case "Darweish and Sufism":
                        startActivity(new Intent(getActivity(), SufismActivity.class));
                        break;
                    case "Calligraphy":
                        startActivity(new Intent(getActivity(), CalligraphyActivity.class));
                        break;
                    case "Islamic Art":
                        startActivity(new Intent(getActivity(), IslamicArtActivity.class));
                        break;
                    case "Digital Art":
                        startActivity(new Intent(getActivity(), DigitalArtActivity.class));
                        break;
                    case "Digital potrait":
                        startActivity(new Intent(getActivity(), DigitalPotraitActivity.class));
                        break;
                    case "Resturants":
                        startActivity(new Intent(getActivity(), ResturantActivity.class));
                        break;
                    case "Shop":
                        startActivity(new Intent(getActivity(), ShopActivity.class));
                        break;
                    case "Hotels":
                        startActivity(new Intent(getActivity(), HotelActivity.class));
                        break;
                    case "Educational":
                        startActivity(new Intent(getActivity(), EducationActivity.class));
                        break;
                    // Add more cases for each type of art
                    default:
                        break;
                }
            }
        });

        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit (String query)
            {
                return false;
            }

            @Override
            public boolean onQueryTextChange (String newText)
            {
                adapter.getFilter().filter(newText);
                return false;
            }
        });

        ImageButton filterbtn = (ImageButton) view.findViewById(R.id.filterbtn);
        filterbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                Intent intent = new Intent(getActivity(), FilterActivity.class);
                startActivity(intent);
                //startActivityForResult(intent, 101);
            }
        });

        Button resturant_btn = (Button) view.findViewById(R.id.resturant);
        Button hotel_btn = (Button) view.findViewById(R.id.hotel);
        Button education_btn = (Button) view.findViewById(R.id.education);
        Button shop_btn = (Button) view.findViewById(R.id.shop);

        resturant_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                Intent intent = new Intent(getActivity(), ResturantActivity.class);
                startActivity(intent);

            }
        });

        hotel_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                Intent intent = new Intent(getActivity(), HotelActivity.class);
                startActivity(intent);

            }
        });

        education_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                Intent intent = new Intent(getActivity(), EducationActivity.class);
                startActivity(intent);

            }
        });

        shop_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                Intent intent = new Intent(getActivity(), ShopActivity.class);
                startActivity(intent);

            }
        });
        return view;
    }
}