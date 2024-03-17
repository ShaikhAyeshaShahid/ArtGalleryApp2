package com.example.artgalleryapp.Product;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.artgalleryapp.R;
import com.example.artgalleryapp.UploadProduct.AbstractProduct;
import com.example.artgalleryapp.UploadProduct.CalligraphyProduct;
import com.example.artgalleryapp.UploadProduct.CeramicProduct;
import com.example.artgalleryapp.UploadProduct.CharcoalProduct;
import com.example.artgalleryapp.UploadProduct.DarweishProduct;
import com.example.artgalleryapp.UploadProduct.DigitalPotraitProduct;
import com.example.artgalleryapp.UploadProduct.DigitalProduct;
import com.example.artgalleryapp.UploadProduct.EducationProduct;
import com.example.artgalleryapp.UploadProduct.HotelProduct;
import com.example.artgalleryapp.UploadProduct.IslamicProduct;
import com.example.artgalleryapp.UploadProduct.ResturantProduct;
import com.example.artgalleryapp.UploadProduct.ShopProduct;

public class SelectCategoryActivity extends AppCompatActivity
{
    private ImageView abstract_art, islamic_art, ceramic_art, charcoal_art;
    private ImageView digitalpotrait_art, digital_art, calligarphy_art, darweish_art;
    private ImageView shop, hotel, resturant, education;
    private Button LogoutBtn, CheckOrdersBtn;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_category);

        abstract_art = (ImageView) findViewById(R.id.abstract_art);
        islamic_art = (ImageView) findViewById(R.id.islamic);
        ceramic_art = (ImageView) findViewById(R.id.ceramic);
        charcoal_art = (ImageView) findViewById(R.id.pencilcharcoal);

        digitalpotrait_art = (ImageView) findViewById(R.id.digitalpotrait);
        digital_art = (ImageView) findViewById(R.id.digitalart);
        calligarphy_art = (ImageView) findViewById(R.id.calligraphy);
        darweish_art = (ImageView) findViewById(R.id.darweish);

        hotel = (ImageView) findViewById(R.id.hotel);
        shop = (ImageView) findViewById(R.id.shop);
        education = (ImageView) findViewById(R.id.education);
        resturant = (ImageView) findViewById(R.id.resturant);

        abstract_art.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view)
            {
                Intent intent = new Intent(SelectCategoryActivity.this, AbstractProduct.class);
                //intent.putExtra("category", "tShirts");
                startActivity(intent);
            }
        });
        calligarphy_art.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view)
            {
                Intent intent = new Intent(SelectCategoryActivity.this, CalligraphyProduct.class);
                //intent.putExtra("category", "Sports tShirts");
                startActivity(intent);
            }
        });


        ceramic_art.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view)
            {
                Intent intent = new Intent(SelectCategoryActivity.this, CeramicProduct.class);
                //intent.putExtra("category", "Female Dresses");
                startActivity(intent);
            }
        });


        charcoal_art.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view)
            {
                Intent intent = new Intent(SelectCategoryActivity.this, CharcoalProduct.class);
                // intent.putExtra("category", "Sweathers");
                startActivity(intent);
            }
        });


        darweish_art.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view)
            {
                Intent intent = new Intent(SelectCategoryActivity.this, DarweishProduct.class);
                //intent.putExtra("category", "Glasses");
                startActivity(intent);
            }
        });


        digital_art.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view)
            {
                Intent intent = new Intent(SelectCategoryActivity.this, DigitalProduct.class);
                //intent.putExtra("category", "Hats Caps");
                startActivity(intent);
            }
        });


        digitalpotrait_art.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view)
            {
                Intent intent = new Intent(SelectCategoryActivity.this, DigitalPotraitProduct.class);
                //intent.putExtra("category", "Wallets Bags Purses");
                startActivity(intent);
            }
        });


        education.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view)
            {
                Intent intent = new Intent(SelectCategoryActivity.this, EducationProduct.class);
                //intent.putExtra("category", "Shoes");
                startActivity(intent);
            }
        });


        hotel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view)
            {
                Intent intent = new Intent(SelectCategoryActivity.this, HotelProduct.class);
                //intent.putExtra("category", "HeadPhones HandFree");
                startActivity(intent);
            }
        });


        islamic_art.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view)
            {
                Intent intent = new Intent(SelectCategoryActivity.this, IslamicProduct.class);
                //intent.putExtra("category", "Laptops");
                startActivity(intent);
            }
        });


        resturant.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view)
            {
                Intent intent = new Intent(SelectCategoryActivity.this, ResturantProduct.class);
                //intent.putExtra("category", "Watches");
                startActivity(intent);
            }
        });


        shop.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view)
            {
                Intent intent = new Intent(SelectCategoryActivity.this, ShopProduct.class);
                //intent.putExtra("category", "Mobile Phones");
                startActivity(intent);
            }
        });

    }
}