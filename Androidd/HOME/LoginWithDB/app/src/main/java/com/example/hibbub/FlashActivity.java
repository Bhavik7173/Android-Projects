package com.example.hibbub;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.hibbub.Login.LoginActivity;

public class FlashActivity extends AppCompatActivity implements Runnable {
    LinearLayout linearLayout;
    Handler handler;
    Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);
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
        Intent intent=new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}