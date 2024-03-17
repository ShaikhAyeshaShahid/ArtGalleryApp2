package com.example.artgalleryapp.Category;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.artgalleryapp.R;

public class CategoryFragment extends Fragment {


    public CategoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_category, container, false);

        Button abs_btn = (Button) view.findViewById(R.id.abs_btn);
        Button digiart_btn = (Button) view.findViewById(R.id.digiart_btn);
        Button digipotr_btn = (Button) view.findViewById(R.id.digipotr_btn);
        Button Cal_btn = (Button) view.findViewById(R.id.Cal_btn);
        Button isl_btn = (Button) view.findViewById(R.id.isl_btn);
        Button sufi_btn = (Button) view.findViewById(R.id.sufi_btn);
        Button cera_btn = (Button) view.findViewById(R.id.cera_btn);
        Button pen_btn = (Button) view.findViewById(R.id.pen_btn);



        abs_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AbstractActivity.class);
                startActivity(intent);
            }
        });

        digiart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DigitalArtActivity.class);
                startActivity(intent);
            }
        });

        digipotr_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DigitalPotraitActivity.class);
                startActivity(intent);
            }
        });

        Cal_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CalligraphyActivity.class);
                startActivity(intent);
            }
        });

        isl_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), IslamicArtActivity.class);
                startActivity(intent);
            }
        });

        sufi_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SufismActivity.class);
                startActivity(intent);
            }
        });

        cera_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getActivity(), CeramicArtActivity.class);
                startActivity(intent);
            }
        });

        pen_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PencilCharcoalActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
