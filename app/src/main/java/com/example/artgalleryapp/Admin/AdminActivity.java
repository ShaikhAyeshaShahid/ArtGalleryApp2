package com.example.artgalleryapp.Admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.artgalleryapp.WelcomeScreen.LoginActivity;
import com.example.artgalleryapp.Product.SelectCategoryActivity;
import com.example.artgalleryapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AdminActivity extends AppCompatActivity
{
    Button UplaodProduct;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference reference;
    FirebaseDatabase database;
    TextView Tvemail, Tvuid, tvCountUser, tvCountAdmin;
    LinearLayout logout;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);


        UplaodProduct = findViewById(R.id.btn_UploadProduct);

        UplaodProduct.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                Intent intent = new Intent(AdminActivity.this, SelectCategoryActivity.class);
                startActivity(intent);
                finish();
            }
        });

       // init_screen();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();

        Tvemail = findViewById(R.id.email);
        Tvuid = findViewById(R.id.uid);
        tvCountUser = findViewById(R.id.count_user);
        tvCountAdmin = findViewById(R.id.count_admin);
       // CountAdmin();
        //CountUser();

        logout = findViewById(R.id.BtnLogOut);
        logout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                firebaseAuth.signOut();
                startActivity(new Intent(AdminActivity.this, LoginActivity.class));
                finish();
            }
        });

//        showAdminProfile();
//
//        LinearLayout manager_user = (LinearLayout) findViewById(R.id.manager_user);
//        manager_user.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick (View v)
//            {
//                startActivity(new Intent(AdminActivity.this, ManagerUserActivity.class));
//                finish();
//            }
//        }); */
//
//
//        LinearLayout manager_admin = (LinearLayout) findViewById(R.id.manager_admin);
//        manager_admin.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick (View v)
//            {
//                startActivity(new Intent(AdminActivity.this, ManagerAdminActivity.class));
//                finish();
//
//            }
//        });
//    }
//
//    private void CountAdmin ( )
//    {
//        reference = database.getReference("Admin");
//        reference.addListenerForSingleValueEvent(new ValueEventListener()
//        {
//            @Override
//            public void onDataChange (@NonNull DataSnapshot snapshot)
//            {
//                int counter = (int) snapshot.getChildrenCount();
//                String adminCounter = String.valueOf(counter);
//                tvCountAdmin.setText(adminCounter);
//            }
//
//            @Override
//            public void onCancelled (@NonNull DatabaseError error)
//            {
//
//            }
//        });
//    }
//
//    private void CountUser ( )
//    {
//        reference = database.getReference("User");
//        reference.addListenerForSingleValueEvent(new ValueEventListener()
//        {
//            @Override
//            public void onDataChange (@NonNull DataSnapshot snapshot)
//            {
//                int counter = (int) snapshot.getChildrenCount();
//                String userCounter = String.valueOf(counter);
//                tvCountUser.setText(userCounter);
//            }
//
//            @Override
//            public void onCancelled (@NonNull DatabaseError error)
//            {
//
//            }
//        });
//    }
//
//    private void showAdminProfile ( )
//    {
//        String userID = firebaseUser.getUid();
//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Admin");
//        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener()
//        {
//            @Override
//            public void onDataChange (@NonNull DataSnapshot snapshot)
//            {
//                ModelAdmin modelAdmin = snapshot.getValue(ModelAdmin.class);
//                if (modelAdmin != null) {
//                    String email = " " + snapshot.child("Email").getValue();
//                    String uid = " " + snapshot.child("Uid").getValue();
//
//                    Tvemail.setText(email);
//                    Tvuid.setText("Uid: " + uid);
//                }
//            }
//
//            @Override
//            public void onCancelled (@NonNull DatabaseError error)
//            {
//                Toast.makeText(AdminActivity.this, "Somthing is wrong", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private void init_screen ( )
//    {
//        final int flags = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
//        getWindow().getDecorView().setSystemUiVisibility(flags);
//
//        final View view = getWindow().getDecorView();
//        view.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener()
//        {
//            @Override
//            public void onSystemUiVisibilityChange (int visibility)
//            {
//                if ((visibility & View.SYSTEM_UI_FLAG_FULLSCREEN) == 0) {
//                    view.setSystemUiVisibility(flags);
//                }
//            }
//        });
//
//    }

//    int backPressed = 0;
//
//    @Override
//    public void onBackPressed ( )
//    {
//        backPressed++;
//        if (backPressed == 1) {
//            startActivity(new Intent(AdminActivity.this, LoginActivity.class));
//            finish();
//        }
//        super.onBackPressed();
   }
}