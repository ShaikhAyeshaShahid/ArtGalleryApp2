package com.example.artgalleryapp;

import static com.example.artgalleryapp.R.id.fullname_field;
import static com.example.artgalleryapp.R.id.username_field;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.artgalleryapp.OTP.entermobileno;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserProfile extends AppCompatActivity {
    Button user_update;
    TextInputLayout fullName, email, phoneNo, password,userName;
    TextView fullNameLabel, usernameLabel;

    String user_username, user_email, user_fullname, user_phoneno, user_password;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("Registration");

        fullName = findViewById(R.id.full_name_profile);
        userName = findViewById(R.id.username_profile);
        email = findViewById(R.id.email_profile);
        phoneNo = findViewById(R.id.phone_no_profile);
        password = findViewById(R.id.password_profile);

        fullNameLabel = findViewById(fullname_field);
        usernameLabel = findViewById(username_field);
        
        gettingData();


    }

    private void gettingData ( )
    {
        Intent intent = getIntent();
        user_username = intent.getStringExtra("username");
        user_email = intent.getStringExtra("email");
        user_fullname = intent.getStringExtra("name");
        user_phoneno = intent.getStringExtra("phone");
        user_password = intent.getStringExtra("password");

        fullNameLabel.setText(user_fullname);
        usernameLabel.setText(user_username);

        fullName.getEditText().setText(user_fullname);
        userName.getEditText().setText(user_username);
        email.getEditText().setText(user_email);
        phoneNo.getEditText().setText(user_phoneno);
        password.getEditText().setText(user_password);
    }

    public void update_button_click (View view)
    {
        if(namechange() || phonenochange() || passwordchange() || emailchange())
        {
            Toast.makeText(this, "Data has been updated sucessfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(UserProfile.this, entermobileno.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "You did not made any changes", Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(UserProfile.this,entermobileno.class);
        startActivity(intent);
    }

    private boolean namechange ( )
    {
        if (!user_fullname.equals(fullName.getEditText().getText().toString()))
        {
            reference.child(user_username).child("name").setValue(fullName.getEditText().getText().toString());
            user_fullname = fullName.getEditText().getText().toString();
            fullNameLabel.setText(fullName.getEditText().getText().toString());
            return true;
        }
        else {
            return false;
        }
    }
    private boolean phonenochange ( )
    {
        if (!user_phoneno.equals(phoneNo.getEditText().getText().toString()))
        {
            reference.child(user_username).child("phone").setValue(phoneNo.getEditText().getText().toString());
            user_phoneno = phoneNo.getEditText().getText().toString();
            return true;
        }
        else {
            return false;
        }
    }
    private boolean passwordchange ( )
    {
        if (!user_password.equals(password.getEditText().getText().toString()))
        {
            reference.child(user_username).child("password").setValue(password.getEditText().getText().toString());
            user_password = password.getEditText().getText().toString();
            return true;
        }
        else {
            return false;
        }
    }
    private boolean emailchange ( )
    {
        if (!user_email.equals(email.getEditText().getText().toString()))
        {
            reference.child(user_username).child("email").setValue(email.getEditText().getText().toString());
            user_email = email.getEditText().getText().toString();
            return true;
        }
        else {
            return false;
        }
    }
}