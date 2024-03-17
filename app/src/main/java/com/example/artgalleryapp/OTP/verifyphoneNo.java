package com.example.artgalleryapp.OTP;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.artgalleryapp.DrawerActivity;
import com.example.artgalleryapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class verifyphoneNo extends AppCompatActivity
{

    String verficationCodeBySystem;
    EditText phoneNumberEnteredByTheUser;
    FirebaseAuth mAuth;
    EditText inputNumber1, inputNumber2, inputNumber3, inputNumber4, inputNumber5, inputNumber6;
    String getOTPbackend;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verifyphone_no);

        final Button verify_btn = findViewById(R.id.verify_btn);
        final ProgressBar progressBar = findViewById(R.id.progress_bar);

        inputNumber1 = findViewById(R.id.inputotp1);
        inputNumber2 = findViewById(R.id.inputotp2);
        inputNumber3 = findViewById(R.id.inputotp3);
        inputNumber4 = findViewById(R.id.inputotp4);
        inputNumber5 = findViewById(R.id.inputotp5);
        inputNumber6 = findViewById(R.id.inputotp6);

        TextView textMobile = findViewById(R.id.textmobile);
        textMobile.setText(String.format("+92-%s", getIntent().getStringExtra("mobile")));

        getOTPbackend = getIntent().getStringExtra("backendOTP");

        verify_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                if (!inputNumber1.getText().toString().trim().isEmpty() && !inputNumber2.getText().toString().trim().isEmpty() && !inputNumber3.getText().toString().trim().isEmpty() && !inputNumber4.getText().toString().trim().isEmpty() && !inputNumber5.getText().toString().trim().isEmpty() && !inputNumber6.getText().toString().trim().isEmpty()) {
                    String entercodeOTP = inputNumber1.getText().toString() +
                            inputNumber2.getText().toString() +
                            inputNumber3.getText().toString() +
                            inputNumber4.getText().toString() +
                            inputNumber5.getText().toString() +
                            inputNumber6.getText().toString();

                    if (getOTPbackend != null) {
                        progressBar.setVisibility(View.VISIBLE);
                        verify_btn.setVisibility(View.INVISIBLE);

                        PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(
                                getOTPbackend, entercodeOTP
                        );
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>()
                                {
                                    @Override
                                    public void onComplete (@NonNull Task<AuthResult> task)
                                    {
                                        progressBar.setVisibility(View.GONE);
                                        verify_btn.setVisibility(View.VISIBLE);

                                        if (task.isSuccessful()) {
                                            Intent intent = new Intent(getApplicationContext(), DrawerActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(verifyphoneNo.this, "Enter the correct OTP", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                    // Toast.makeText(verifyphoneNo.this, "OTP Verify",Toast.LENGTH_SHORT).show();
                    else {
                        Toast.makeText(verifyphoneNo.this, "Please check your internet connection", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(verifyphoneNo.this, "Please enter all number", Toast.LENGTH_SHORT).show();
                }
            }
        });
        numberOTPmove();

        TextView resendlabel1 = findViewById(R.id.textresendotp);
        resendlabel1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+92" + getIntent().getStringExtra("mobile"),
                        60,
                        TimeUnit.SECONDS,
                        verifyphoneNo.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks()
                        {
                            @Override
                            public void onVerificationCompleted (@NonNull PhoneAuthCredential phoneAuthCredential)
                            {
                            }

                            @Override
                            public void onVerificationFailed (@NonNull FirebaseException e)
                            {

                                Toast.makeText(verifyphoneNo.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent (@NonNull String newOTPbackend, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken)
                            {
                                getOTPbackend = newOTPbackend;
                                Toast.makeText(verifyphoneNo.this, "OTP sended successfully", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
            }
        });

    }

    private void numberOTPmove ( )
    {
        inputNumber1.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged (CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged (CharSequence s, int start, int before, int count)
            {
                if (!s.toString().trim().isEmpty()) {
                    inputNumber2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged (Editable s)
            {

            }
        });
        inputNumber2.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged (CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged (CharSequence s, int start, int before, int count)
            {
                if (!s.toString().trim().isEmpty()) {
                    inputNumber3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged (Editable s)
            {

            }
        });
        inputNumber3.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged (CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged (CharSequence s, int start, int before, int count)
            {
                if (!s.toString().trim().isEmpty()) {
                    inputNumber4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged (Editable s)
            {

            }
        });
        inputNumber4.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged (CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged (CharSequence s, int start, int before, int count)
            {
                if (!s.toString().trim().isEmpty()) {
                    inputNumber5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged (Editable s)
            {

            }
        });
        inputNumber5.addTextChangedListener(new TextWatcher()
        {
            @Override
            public void beforeTextChanged (CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged (CharSequence s, int start, int before, int count)
            {
                if (!s.toString().trim().isEmpty()) {
                    inputNumber6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged (Editable s)
            {

            }
        });
    }
}
















 /*  verify_btn = findViewById(R.id.verify_btn);
        phoneNumberEnteredByTheUser = findViewById(R.id.verification_code_entered_by_user);
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);

        String phoneNo = getIntent().getStringExtra("phoneNumber");
        SendVerificationCodeToUser(phoneNo);

        verify_btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                String code = phoneNumberEnteredByTheUser.getText().toString();
                if (code.isEmpty() || code.length() < 6) {
                    phoneNumberEnteredByTheUser.setError("Wrong OTP...");
                    phoneNumberEnteredByTheUser.requestFocus();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                verifyCode(code);
            }
        });

    }
    private void SendVerificationCodeToUser (String phoneNo)
    {

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+92" + phoneNo)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)   // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks()
    {
        @Override
        public void onCodeSent (@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken)
        {
            super.onCodeSent(s, forceResendingToken);
            verficationCodeBySystem = s;
        }

        @Override
        public void onVerificationCompleted (@NonNull PhoneAuthCredential phoneAuthCredential)
        {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                progressBar.setVisibility(View.VISIBLE);
                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed (@NonNull FirebaseException e)
        {
            Toast.makeText(verifyphoneNo.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };

    private void verifyCode (String CodeByUser)
    {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verficationCodeBySystem, CodeByUser);
        SignInTheUserByCredentials(credential);
    }

    private void SignInTheUserByCredentials (PhoneAuthCredential credential)
    {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(verifyphoneNo.this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete (@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful()) {

                            Toast.makeText(verifyphoneNo.this, "Your Account has been created successfully!", Toast.LENGTH_SHORT).show();

                            //Perform Your required action here to either let the user sign In or do something required
                            Intent intent = new Intent(getApplicationContext(), UserProfile.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        } else {
                            Toast.makeText(verifyphoneNo.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }); */