package com.example.artgalleryapp.WelcomeScreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.artgalleryapp.Admin.AdminActivity;
import com.example.artgalleryapp.Cart.Prevalent;
import com.example.artgalleryapp.DrawerActivity;
import com.example.artgalleryapp.ForgetPassword;
import com.example.artgalleryapp.R;
import com.example.artgalleryapp.R.id;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import io.paperdb.Paper;

public class LoginActivity extends AppCompatActivity
{
    //ActivityLoginBinding binding;
    private Button callSignUp;
    private Button login_go;
    private Button forgetpass;
    TextView logotext, slogantext;
    DatabaseReference reference;
    private ProgressDialog loadingBar;

    private String parentDbName = "Registration";
    private CheckBox chkBoxRememberMe;

    TextInputLayout username, password;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        Button admin_login;

        admin_login = findViewById(id.btnAdmin);

        loadingBar = new ProgressDialog(this);
        chkBoxRememberMe = (CheckBox) findViewById(id.RememberMe);
        Paper.init(this);

        callSignUp = (Button) findViewById(R.id.signup_screen);
        login_go = (Button) findViewById(R.id.Login_go);
        forgetpass = (Button) findViewById(id.btn_forgetpass);

        username = findViewById(R.id.username_text_field_design);
        password = findViewById(id.password_input_field);

        login_go.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {

                if (chkBoxRememberMe.isChecked()) {
                    Paper.book().write(Prevalent.UserUsernamekey, username);
                    Paper.book().write(Prevalent.UserPasswordKey, password);
                }
                String username_ = username.getEditText().getText().toString();
                String password_ = password.getEditText().getText().toString();


                if (!username_.isEmpty()) {

                    username.setError(null);
                    username.setEnabled(false);

                    if (!password_.isEmpty()) {

                        password.setError(null);
                        password.setEnabled(false);

                        final String username_data = username.getEditText().getText().toString();
                        final String password_data = password.getEditText().getText().toString();


                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference databaseReference = firebaseDatabase.getReference("Registration");


                        Query check_username = databaseReference.orderByChild("username").equalTo(username_data);
                        check_username.addListenerForSingleValueEvent(new ValueEventListener()
                        {
                            @Override
                            public void onDataChange (@NonNull DataSnapshot snapshot)
                            {

                                if (snapshot.exists()) {
                                    username.setError(null);
                                    username.setErrorEnabled(false);
                                    String passwordcheck = snapshot.child(username_data).child("password").getValue(String.class);

                                    if (passwordcheck.equals(password_data)) {
                                        password.setError(null);
                                        password.setErrorEnabled(false);

                                        reference = firebaseDatabase.getInstance().getReference("Login");

                                        String name = snapshot.child(username_data).child("name").getValue(String.class);
                                        String email = snapshot.child(username_data).child("email").getValue(String.class);
                                        String phone = snapshot.child(username_data).child("phone").getValue(String.class);
                                        String password = snapshot.child(username_data).child("password").getValue(String.class);
                                        String username = snapshot.child(username_data).child("username").getValue(String.class);

                                        Intent intent = new Intent(LoginActivity.this, DrawerActivity.class);
                                        intent.putExtra("name", name);
                                        intent.putExtra("email", email);
                                        intent.putExtra("phone", phone);
                                        intent.putExtra("username", username);
                                        intent.putExtra("password", password);


                                        Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_SHORT).show();
                                        startActivity(intent);
                                        finish();

                                    } else {
                                        password.setError("Wrong Password");
                                    }

                                } else {
                                    username.setError("User doesn't exists");
                                }

                            }

                            @Override
                            public void onCancelled (@NonNull DatabaseError error)
                            {

                            }
                        });


                    } else {
                        password.setError("Please enter the password");
                    }
                } else {
                    username.setError("Please enter the username");
                }
                // AllowAccessToAccount(username_, password_);
            }
        });


        admin_login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                String username_ = username.getEditText().getText().toString();
                String password_ = password.getEditText().getText().toString();

                if (!username_.isEmpty()) {
                    username.setError(null);
                    username.setEnabled(false);

                    if (!password_.isEmpty()) {
                        password.setError(null);
                        password.setEnabled(false);

                        final String username_data = username.getEditText().getText().toString();
                        final String password_data = password.getEditText().getText().toString();

                        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                        DatabaseReference databaseReference = firebaseDatabase.getReference("Admin");

                        Query check_username = databaseReference.orderByChild("username").equalTo(username_data);
                        check_username.addListenerForSingleValueEvent(new ValueEventListener()
                        {
                            @Override
                            public void onDataChange (@NonNull DataSnapshot snapshot)
                            {
                                if (snapshot.exists()) {
                                    username.setError(null);
                                    username.setErrorEnabled(false);
                                    String passwordcheck = snapshot.child(username_data).child("password").getValue(String.class);

                                    if (passwordcheck.equals(password_data)) {
                                        password.setError(null);
                                        password.setErrorEnabled(false);

                                        Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        password.setError("Wrong Password");
                                    }
                                } else {
                                    username.setError("User doesn't exist");
                                }
                            }

                            @Override
                            public void onCancelled (@NonNull DatabaseError error)
                            {

                            }
                        });
                    } else {
                        password.setError("Please enter the password");
                    }
                } else {
                    username.setError("Please enter the username");
                }
            }
        });


        callSignUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        });

        forgetpass.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                Intent intent = new Intent(LoginActivity.this, ForgetPassword.class);
                startActivity(intent);
            }
        });

    }

   /* private void AllowAccessToAccount (final String username, final String password)
    {

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("Registration");

        if (chkBoxRememberMe.isChecked()) {
            Paper.book().write(Prevalent.Userphone, username);
            Paper.book().write(Prevalent.UserPasswordKey, password);
        }

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener()
        {
            @Override
            public void onDataChange (@NonNull DataSnapshot dataSnapshot)
            {
                if (dataSnapshot.child(parentDbName).child(username).exists()) {

                    storingdata usersData = dataSnapshot.child(username).child("username").getValue(storingdata.class);
                    if (usersData.getUsername().equals(username)) {
                        if (usersData.getPassword().equals(password)) {
                            if (dataSnapshot.child("Registration").equals("password")) {
                                Toast.makeText(LoginActivity.this, "Welcome Admin, you are logged in Successfully...", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                                startActivity(intent);
                            } else if (dataSnapshot.child("Registration").equals("username")) {
                                Toast.makeText(LoginActivity.this, "logged in Successfully...", Toast.LENGTH_SHORT).show();
                                loadingBar.dismiss();

                                Intent intent = new Intent(LoginActivity.this, DrawerActivity.class);
                                Prevalent.currentOnlineUser = usersData;
                                startActivity(intent);
                            }

                        } else {
                            loadingBar.dismiss();
                            Toast.makeText(LoginActivity.this, "Password is incorrect", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Account with this " + username + " number do not exists.", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }

            @Override
            public void onCancelled (DatabaseError databaseError)
            {

            }
        });
    }*/


}

