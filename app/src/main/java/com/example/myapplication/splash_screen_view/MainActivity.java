package com.example.myapplication.splash_screen_view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;


import com.example.MealHub.R;
import com.example.myapplication.home_screen.home_view.home_screen;


public class MainActivity extends AppCompatActivity {
    private static final int SPLASH_TIMEOUT = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the Main Activity
                Intent toHome = new Intent(MainActivity.this, home_screen.class);
                startActivity(toHome);
                // Finish the splash activity so it can't be returned to
                finish();
            }
        }, SPLASH_TIMEOUT);

    }

}