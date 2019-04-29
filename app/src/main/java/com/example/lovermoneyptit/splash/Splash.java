package com.example.lovermoneyptit.splash;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lovermoneyptit.HomeFragment;
import com.example.lovermoneyptit.LoginActivity;
import com.example.lovermoneyptit.MainActivity;

public class Splash extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 5000;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        preferences = getSharedPreferences("user", MODE_PRIVATE);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String savedUsername = preferences.getString("username", "");
                String savedPassword = preferences.getString("password", "");
                if (savedUsername != "" && savedPassword != "") {
                    Intent intent = new Intent(Splash.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(Splash.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, SPLASH_TIME_OUT);
    }
}
