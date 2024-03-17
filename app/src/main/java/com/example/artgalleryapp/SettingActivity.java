package com.example.artgalleryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.artgalleryapp.WelcomeScreen.LoginActivity;

import io.paperdb.Paper;

public class SettingActivity extends AppCompatActivity
{
    ImageButton btn_notification, btn_contact, btn_TC, btn_PP, btn_SO;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        btn_SO = findViewById(R.id.iv_sign_out);
        btn_notification = findViewById(R.id.iv_notification);
        btn_contact = findViewById(R.id.iv_contact_us);
        btn_TC = findViewById(R.id.iv_terms_and_conditions);
        btn_PP = findViewById(R.id.iv_privacy_policy);


        builder = new AlertDialog.Builder(this);

        btn_SO.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                Paper.book().destroy();
                builder.setTitle("Insight Artistry")
                        .setMessage("Do you want to logout")
                        .setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick (DialogInterface dialog, int which)
                            {
                                Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick (DialogInterface dialog, int which)
                            {
                                dialog.cancel();
                            }
                        })
                        .show();
            }
        });

        btn_notification.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                Dialog dialog = new Dialog(SettingActivity.this);
                dialog.setContentView(R.layout.notification_dialogue);
                dialog.show();
            }
        });

        btn_contact.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                Dialog dialog = new Dialog(SettingActivity.this);
                dialog.setContentView(R.layout.contact_dialogue);
                dialog.show();
            }
        });

        btn_TC.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                Dialog dialog = new Dialog(SettingActivity.this);
                dialog.setContentView(R.layout.termsandcondition_dialogue);
                dialog.show();
            }
        });

        btn_PP.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                Dialog dialog = new Dialog(SettingActivity.this);
                dialog.setContentView(R.layout.privacypolicy_dialogue);
                dialog.show();
            }
        });

    }

}