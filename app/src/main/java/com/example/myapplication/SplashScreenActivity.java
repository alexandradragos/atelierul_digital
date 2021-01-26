package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This is the launcher activity
 */
public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //to hide the ActionBar(which contains the name of the project) so the SplashScreen will be fullscreen
        getSupportActionBar().hide();

        //Launch the MainActivity after displaying SplassScreen for a few seconds
        Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);

        //Launch MainActivity after 1000 ms
        //We used Handler instead of Thread, beacause it has the option to start the thread after a delay
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}