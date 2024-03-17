package com.example.artgalleryapp.UploadProduct;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.artgalleryapp.Product.ProductModule;
import com.example.artgalleryapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class DigitalPotraitProduct extends AppCompatActivity
{
    TextView headline, discription, price, brand, productType, aboutProduct, origin;
    ImageView uploadbtn, productImage;
    Button submit;
    Uri ImageUri;

    RelativeLayout relativeLayout;

    private FirebaseDatabase database;
    private FirebaseStorage firebaseStorage;

    ProgressDialog dialog;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digital_potrait_product);

        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference categoryRef = databaseRef.child("categories");

        database = FirebaseDatabase.getInstance();
        firebaseStorage = FirebaseStorage.getInstance();

        dialog = new ProgressDialog(this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Please Wait...");
        dialog.setCancelable(false);
        dialog.setTitle("Product Uploading");
        dialog.setCanceledOnTouchOutside(false);

        headline = findViewById(R.id.headline);
        discription = findViewById(R.id.discription);
        price = findViewById(R.id.price);
        brand = findViewById(R.id.brand);
        productType = findViewById(R.id.productType);
        aboutProduct = findViewById(R.id.aboutProduct);
        origin = findViewById(R.id.origin);

        uploadbtn = findViewById(R.id.upload_btn);
        productImage = findViewById(R.id.Product_Image);

        submit = findViewById(R.id.submit);

        relativeLayout = findViewById(R.id.relative);

        uploadbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                UploadImage();
                relativeLayout.setVisibility(View.VISIBLE);
                uploadbtn.setVisibility(View.GONE);
            }
        });

        submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                dialog.show();

                final StorageReference reference = firebaseStorage.getReference().child("DigitalPotrait")
                        .child(System.currentTimeMillis() + " ");
                reference.putFile(ImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
                {
                    @Override
                    public void onSuccess (UploadTask.TaskSnapshot taskSnapshot)
                    {
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>()
                        {
                            @Override
                            public void onSuccess (Uri uri)
                            {
                                ProductModule model = new ProductModule();
                                model.setProductImage(uri.toString());

                                model.setHeadline(headline.getText().toString());
                                model.setDiscription(discription.getText().toString());
                                model.setPrice(price.getText().toString());
                                model.setBrand(brand.getText().toString());
                                model.setProductType(productType.getText().toString());
                                model.setAboutProduct(aboutProduct.getText().toString());
                                model.setOrigin(origin.getText().toString());

                                database.getReference().child("DigitalPotrait").push().setValue(model)
                                        .addOnSuccessListener(new OnSuccessListener<Void>()
                                        {
                                            @Override
                                            public void onSuccess (Void unused)
                                            {
                                                Toast.makeText(DigitalPotraitProduct.this, "Product Upload Successfully", Toast.LENGTH_SHORT).show();
                                                dialog.dismiss();
                                            }
                                        }).addOnFailureListener(new OnFailureListener()
                                        {
                                            @Override
                                            public void onFailure (@NonNull Exception e)
                                            {
                                                dialog.dismiss();
                                                Toast.makeText(DigitalPotraitProduct.this, "Error", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                            }
                        });

                    }
                });

            }
        });

    }

    private void UploadImage ( )
    {
        Dexter.withContext(this)
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener()
                {
                    @Override
                    public void onPermissionGranted (PermissionGrantedResponse permissionGrantedResponse)
                    {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(intent, 101);

                    }

                    @Override
                    public void onPermissionDenied (PermissionDeniedResponse permissionDeniedResponse)
                    {
                        Toast.makeText(DigitalPotraitProduct.this, "Permission Denied", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown (PermissionRequest permissionRequest, PermissionToken permissionToken)
                    {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101 && resultCode == RESULT_OK) {
            ImageUri = data.getData();
            productImage.setImageURI(ImageUri);
        }
    }


    public void onItemSelected (AdapterView<?> parent, View view, int position, long id)
    {
        String text = parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    public void onNothingSelected (AdapterView<?> parent)
    {

    }
}