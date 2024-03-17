package com.example.artgalleryapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.android.material.chip.Chip;

import java.util.ArrayList;

public class FilterActivity extends AppCompatActivity
{
    private Chip chipTrending, chipNewArrival, chipBestSelling;
    private Chip chipLtoH, chipHtoL;
    TextView tvFilter;

    private Button btnApply;
    private ArrayList<String> selectedChipData;


    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        chipTrending = findViewById(R.id.chipTranding);
        chipNewArrival = findViewById(R.id.chipNewArrival);
        chipBestSelling = findViewById(R.id.chipBestSelling);
        chipLtoH = findViewById(R.id.chipLtoH);
        chipHtoL = findViewById(R.id.chipHtoL);

        selectedChipData = new ArrayList<>();
        CompoundButton.OnCheckedChangeListener checkedChangeListener = new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged (CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked) {
                    selectedChipData.add(buttonView.getText().toString());

                } else {
                    selectedChipData.remove(buttonView.getText().toString());
                }
            }
        };

        chipTrending.setOnCheckedChangeListener(checkedChangeListener);
        chipNewArrival.setOnCheckedChangeListener(checkedChangeListener);
        chipBestSelling.setOnCheckedChangeListener(checkedChangeListener);
        chipLtoH.setOnCheckedChangeListener(checkedChangeListener);
        chipHtoL.setOnCheckedChangeListener(checkedChangeListener);

        btnApply = findViewById(R.id.btnApply);
        btnApply.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                Intent intent = new Intent(FilterActivity.this, FilterActivity.class);
                startActivityForResult(intent, 101);
                Intent resultIntent = new Intent();
                resultIntent.putExtra("data", selectedChipData.toString());
                setResult(101, resultIntent);
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            tvFilter.findViewById(R.id.tvFilter);
            tvFilter.setText(data.getStringExtra("data"));
        }
    }
}