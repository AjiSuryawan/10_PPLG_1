package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        editor = pref.edit();

        username = pref.getString("username", null);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //cek sharedpref
                if (username ==null || username == ""){
                    Intent home=new Intent(SplashScreen.this, LoginView.class);
                    startActivity(home);
                    finish();
                }else{
                    Intent home=new Intent(SplashScreen.this, MainMenu.class);
                    startActivity(home);
                    finish();
                }


            }
        },2000);
    }
}