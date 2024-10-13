package com.example.myapplication.splash_screen_view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;


import com.airbnb.lottie.LottieAnimationView;
import com.example.MealHub.R;
import com.example.myapplication.home_screen.home_view.HomeScreen;


public class MainActivity extends AppCompatActivity {
    private static final int SPLASH_TIMEOUT = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        LottieAnimationView animationView = findViewById(R.id.lottieAnimationView);
        animationView.setSpeed(1.2f);
        animationView.playAnimation();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the Main Activity
                Intent toHome = new Intent(MainActivity.this, HomeScreen.class);
                startActivity(toHome);
                // Finish the splash activity so it can't be returned to
                finish();
            }
        }, SPLASH_TIMEOUT);

    }

}