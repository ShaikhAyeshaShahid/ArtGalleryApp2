package com.example.artgalleryapp.OTP;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.artgalleryapp.R;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class entermobileno extends AppCompatActivity
{
    EditText inputmobile;
    Button getotp;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entermobileno);

        inputmobile = findViewById(R.id.inputmobilenumber);
        getotp = findViewById(R.id.getOTP);

        ProgressBar progressBar = findViewById(R.id.progress_bar);

        getotp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                if (!inputmobile.getText().toString().trim().isEmpty()) {
                    if ((inputmobile.getText().toString().trim()).length() == 10) {
                        progressBar.setVisibility(View.VISIBLE);
                        getotp.setVisibility(View.INVISIBLE);

                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+92" + inputmobile.getText().toString(),
                                60,
                                TimeUnit.SECONDS,
                                entermobileno.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks()
                                {
                                    @Override
                                    public void onVerificationCompleted (@NonNull PhoneAuthCredential phoneAuthCredential)
                                    {
                                        progressBar.setVisibility(View.GONE);
                                        getotp.setVisibility(View.VISIBLE);
                                    }

                                    @Override
                                    public void onVerificationFailed (@NonNull FirebaseException e)
                                    {
                                        progressBar.setVisibility(View.GONE);
                                        getotp.setVisibility(View.VISIBLE);

                                        Toast.makeText(entermobileno.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onCodeSent (@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken)
                                    {
                                        progressBar.setVisibility(View.GONE);
                                        getotp.setVisibility(View.VISIBLE);
                                        Intent intent = new Intent(getApplicationContext(), verifyphoneNo.class);
                                        intent.putExtra("mobile", inputmobile.getText().toString());
                                        intent.putExtra("backendOTP", s);
                                        startActivity(intent);
                                    }
                                }
                        );

                        //Intent intent = new Intent(getApplicationContext(),verifyphoneNo.class);
                        //intent.putExtra("mobile", inputmobile.getText().toString());
                        //startActivity(intent);
                    } else {
                        Toast.makeText(entermobileno.this, "Please enter correct phone number", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(entermobileno.this, "Enter phone number", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}