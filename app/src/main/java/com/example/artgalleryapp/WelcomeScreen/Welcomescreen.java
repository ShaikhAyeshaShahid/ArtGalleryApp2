package com.example.artgalleryapp.WelcomeScreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.artgalleryapp.Cart.Prevalent;
import com.example.artgalleryapp.DrawerActivity;
import com.example.artgalleryapp.R;
import com.example.artgalleryapp.storingdata;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class Welcomescreen extends AppCompatActivity
{
    private Button button;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomescreen);

        button = (Button) findViewById(R.id.get_btn);
        Paper.init(this);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                // Intent intent = new Intent(Welcomescreen.this, ProductMainActivity.class);
                // startActivity(intent);

                loginscreen();
            }
        });

        String UserUsernamekey = Paper.book().read(Prevalent.UserUsernamekey);
        String UserPasswordKey = Paper.book().read(Prevalent.UserPasswordKey);
        if (UserUsernamekey != "" && UserPasswordKey != "") {
            if (!TextUtils.isEmpty(UserUsernamekey) && !TextUtils.isEmpty(UserPasswordKey)) {
                AllowAccess(UserUsernamekey, UserPasswordKey);

                loadingBar.setTitle("Already Logged in");
                loadingBar.setMessage("Please wait.....");
                loadingBar.setCanceledOnTouchOutside(false);
                loadingBar.show();
            }
        }

    }

    public void loginscreen ( )
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    private void AllowAccess (final String username, final String password)
    {
        final DatabaseReference RootRef;
        RootRef = FirebaseDatabase.getInstance().getReference();
        RootRef.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange (@NonNull DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.child("Registration").child(username).exists()) {

                    storingdata usersData = dataSnapshot.child("Users").child(username).getValue(storingdata.class);
                    if (usersData.getUsername().equals(username)) {
                        if (usersData.getPassword().equals(password)) {
                            Toast.makeText(Welcomescreen.this, "Please wait, you are already logged in...", Toast.LENGTH_SHORT).show();
                            loadingBar.dismiss();

                            Intent intent = new Intent(Welcomescreen.this, DrawerActivity.class);
                            Prevalent.currentOnlineUser = usersData;
                            startActivity(intent);

                        } else {
                            loadingBar.dismiss();
                            Toast.makeText(Welcomescreen.this, "Password is incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(Welcomescreen.this, "Account with this " + username + " number do not exists.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled (DatabaseError databaseError)
            {

            }
        });

    }

}