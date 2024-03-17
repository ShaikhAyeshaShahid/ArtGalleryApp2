package com.example.artgalleryapp.WelcomeScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.artgalleryapp.R;
import com.example.artgalleryapp.UserProfile;
import com.example.artgalleryapp.storingdata;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity
{
    TextInputLayout regfullname, regusername, regemail, regphoneno, regpassword;
    Button regToLoginBtn, regBtn;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    FirebaseAuth auth;


    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        regfullname = findViewById(R.id.fullname_field);
        regusername = findViewById(R.id.username);
        regemail = findViewById(R.id.email);
        regphoneno = findViewById(R.id.phoneNo);
        regpassword = findViewById(R.id.password);

        regBtn = (Button) findViewById(R.id.reg_btn);
        regToLoginBtn = (Button) findViewById(R.id.login_reg);



        regBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                if (!validateName() | !validatePassword() | !validatePhoneNo() | !validateEmail() | !validateUsername()) {


                    return;
                }

                firebaseDatabase = FirebaseDatabase.getInstance();
                reference = firebaseDatabase.getReference("Registration");

                String fullname = regfullname.getEditText().getText().toString();
                String uname = regusername.getEditText().getText().toString();
                String remail = regemail.getEditText().getText().toString();
                String rphone = regphoneno.getEditText().getText().toString();
                String rpass = regpassword.getEditText().getText().toString();

              /*  storingdata storingdata = new storingdata(fullname, uname, remail, rphone, rpass);
                reference.child(uname).setValue(storingdata);
                Intent intent = new Intent(getApplicationContext(), verifyphoneNo.class);
                intent.putExtra("phoneNumber", rphone);
                startActivity(intent); */

                storingdata storingdata = new storingdata(fullname, uname, remail, rphone, rpass);
                reference.child(uname).setValue(storingdata);
                Toast.makeText(getApplicationContext(), "Register sucessfully", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegisterActivity.this, UserProfile.class);
                intent.putExtra("name", fullname);
                intent.putExtra("email", remail);
                intent.putExtra("username", uname);
                intent.putExtra("phone", rphone);
                intent.putExtra("password", rpass);
                startActivity(intent);
                finish();

            }
        });

        regToLoginBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });
    }

    private Boolean validateName ( )
    {
        String val = regfullname.getEditText().getText().toString();
        if (val.isEmpty()) {
            regfullname.setError("Field cannot be empty");
            return false;
        } else {
            regfullname.setError(null);
            regfullname.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateUsername ( )
    {
        String val = regusername.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";
        if (val.isEmpty()) {
            regusername.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 15) {
            regusername.setError("Username too long");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            regusername.setError("White Spaces are not allowed");
            return false;
        } else {
            regusername.setError(null);
            regusername.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateEmail ( )
    {
        String val = regemail.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if (val.isEmpty()) {
            regemail.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            regemail.setError("Invalid email address");
            return false;
        } else {
            regemail.setError(null);
            regemail.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePhoneNo ( )
    {
        String val = regphoneno.getEditText().getText().toString();
        if (val.isEmpty()) {
            regphoneno.setError("Field cannot be empty");
            return false;
        } else {
            regphoneno.setError(null);
            regphoneno.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword ( )
    {
        String val = regpassword.getEditText().getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";
        if (val.isEmpty()) {
            regpassword.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            regpassword.setError("Password is too weak");
            return false;
        } else {
            regpassword.setError(null);
            regpassword.setErrorEnabled(false);
            return true;
        }
    }

}

