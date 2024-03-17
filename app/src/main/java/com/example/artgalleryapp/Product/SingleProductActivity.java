package com.example.artgalleryapp.Product;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.artgalleryapp.Cart.Prevalent;
import com.example.artgalleryapp.ExploreFragment;
import com.example.artgalleryapp.R;
import com.example.artgalleryapp.storingdata;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class SingleProductActivity extends AppCompatActivity
{
    TextView SingleHeadLine, SinglePrice, SingleBrand, SingleProductType, SingleAboutProduct, SingleOrigin;
    ImageView SingleImage;

    private TextView numberText;
    private Button increaseButton;
    private Button decreaseButton;

    private Button addToCartButton;
    private int number = 1;


    private String productID = "", state = "Normal";

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_product);

        addToCartButton = (Button) findViewById(R.id.pd_add_to_cart_button);

        numberText = findViewById(R.id.numberText);
        increaseButton = findViewById(R.id.increaseButton);
        decreaseButton = findViewById(R.id.decreaseButton);

        increaseButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                if (number < 3) {
                    number++;
                    numberText.setText(String.valueOf(number));
                }
            }
        });

        decreaseButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                if (number >= 1) {
                    number--;
                    numberText.setText(String.valueOf(number));
                }
            }
        });


        SingleHeadLine = findViewById(R.id.SingleHeadLine);
        SinglePrice = findViewById(R.id.SinglePrice);
        SingleBrand = findViewById(R.id.SingleBrand);
        SingleProductType = findViewById(R.id.SingleProductType);
        SingleAboutProduct = findViewById(R.id.SingleAboutProduct);
        SingleOrigin = findViewById(R.id.SingleOrigin);

        SingleImage = findViewById(R.id.SingleImage);

        Picasso.get().load(getIntent().getStringExtra("SingleImage"))
                .placeholder(R.drawable.calligraphy2)
                .into(SingleImage);
        SingleHeadLine.setText(getIntent().getStringExtra("SingleHeadLine"));
        SinglePrice.setText(getIntent().getStringExtra("SinglePrice"));
        SingleBrand.setText(getIntent().getStringExtra("SingleBrand"));
        SingleProductType.setText(getIntent().getStringExtra("SingleProductType"));
        SingleAboutProduct.setText(getIntent().getStringExtra("SingleAboutProduct"));
        SingleOrigin.setText(getIntent().getStringExtra("SingleOrigin"));


        productID = getIntent().getStringExtra("pid");
        //getProductDetails(productID);

        addToCartButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view)
            {
               /* if (state.equals("Order Placed") || state.equals("Order Shipped")) {
                    Toast.makeText(SingleProductActivity.this, "You can add Purchase more product, once your order is shipped or confirmed", Toast.LENGTH_LONG).show();
                } else {
                    addingToCartList();

                }
*/
                addingToCartList();
            }

        });
    }


    @Override
    protected void onStart ( )
    {
        super.onStart();
        //CheckOrderState();
    }

    private void addingToCartList ( )
    {
        String saveCurrentTime, saveCurrentDate;
        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd. yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());
        final DatabaseReference cartListRef = FirebaseDatabase.getInstance().getReference().child("Cart List");
        final HashMap<String, Object> cartMap = new HashMap<>();
        cartMap.put("pid", productID);
        cartMap.put("pname", SingleHeadLine.getText().toString());
        cartMap.put("price", SinglePrice.getText().toString());
        cartMap.put("date", saveCurrentDate);
        cartMap.put("time", saveCurrentTime);
        numberText.setText(String.valueOf(numberText));
        // cartMap.put("discount", "");

        cartListRef.child("User view").child(Prevalent.currentOnlineUser.getUsername()).child("Products").child(productID).updateChildren(cartMap).addOnCompleteListener(new OnCompleteListener<Void>()
        {
            @Override
            public void onComplete (@NonNull Task<Void> task)
            {
                if (task.isSuccessful()) {
                    cartListRef.child("Admin view").child(Prevalent.currentOnlineUser.getPhone())
                            .child("Products").child(productID)
                            .updateChildren(cartMap)
                            .addOnCompleteListener(new OnCompleteListener<Void>()
                            {
                                @Override
                                public void onComplete (@NonNull Task<Void> task)
                                {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(SingleProductActivity.this, "Added to cart List", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(SingleProductActivity.this, ExploreFragment.class);
                                        startActivity(intent);
                                    }
                                }
                            });
                }
            }
        });

    }

    /*private void getProductDetails (String productID)
    {
        DatabaseReference productsRef = FirebaseDatabase.getInstance().getReference().child("Products");
        productsRef.child(productID).addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange (DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.exists()) {
                    ProductModule products = dataSnapshot.getValue(ProductModule.class);
                    SingleHeadLine.setText(products.getHeadline());
                    SinglePrice.setText(products.getPrice());
                    SingleAboutProduct.setText(products.getAboutProduct());
                    Picasso.get().load(products.getProductImage()).into(SingleImage);

                }
            }

            @Override
            public void onCancelled (DatabaseError databaseError)
            {

            }
        });
    }

    private void CheckOrderState ( )
    {
        DatabaseReference ordersRef;
        ordersRef = FirebaseDatabase.getInstance().getReference().child("Orders").child(Prevalent.currentOnlineUser.getUsername());
        ordersRef.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange (DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.exists()) {
                    String shippingState = dataSnapshot.child("state").getValue().toString();
                    if (shippingState.equals("Shipped")) {
                        state = "Order Shipped";
                    } else if (shippingState.equals("Not Shipped")) {
                        state = "Order Placed";
                    }
                }
            }

            @Override
            public void onCancelled (DatabaseError databaseError)
            {

            }
        });
    }*/


}