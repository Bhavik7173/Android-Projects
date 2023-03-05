package com.example.wallet;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class Splash extends AppCompatActivity implements Runnable {

    LinearLayout linearLayout;
    Handler handler;
    Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        handler=new Handler();
        animation= AnimationUtils.loadAnimation(this, R.anim.fade_in);
        animation.setDuration(Long.parseLong(getString(R.string.waiting_time)));
        linearLayout=findViewById(R.id.layout);
       /* AnimationDrawable animationDrawable = (AnimationDrawable) linearLayout.getBackground();
        animationDrawable.setEnterFadeDuration(500);
        animationDrawable.setExitFadeDuration(700);
        animationDrawable.start();*/
        linearLayout.setAnimation(animation);
        handler.postDelayed(this, Long.parseLong("5000"));
    }

    @Override
    public void run() {

        SharedPreferences spf = getSharedPreferences("MobileNo", Context.MODE_PRIVATE);
        String a = spf.getString("mobile","no data");
        if(a.equals("6353539097")) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            Intent i =new Intent(this,MainActivity.class);
            startActivity(i);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}
