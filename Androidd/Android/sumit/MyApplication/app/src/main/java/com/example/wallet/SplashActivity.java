package com.example.wallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class SplashActivity extends AppCompatActivity implements Runnable {

    LinearLayout linearLayout;
    Handler handler;
    Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler=new Handler();
        animation= AnimationUtils.loadAnimation(this, R.anim.custom_fade_in);
        animation.setDuration(3000);
        linearLayout=findViewById(R.id.layout);

        linearLayout.setAnimation(animation);
        handler.postDelayed(this, Long.parseLong("5000"));
    }

    @Override
    public void run() {
        Intent intent=new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}