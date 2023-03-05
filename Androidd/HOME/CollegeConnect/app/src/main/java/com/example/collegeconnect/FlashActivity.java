package com.example.collegeconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;


public class FlashActivity extends AppCompatActivity implements Runnable{
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash);
        handler = new Handler();
        handler.postDelayed(this,2000);
    }
    @Override
    public void run() {
        SharedPreferences spf;
        spf = getSharedPreferences("login_file",MODE_PRIVATE);

        String a = spf.getString("status", "no data");
        if (a.equals("login")) {
            Intent i = new Intent(this, HomeActivity.class);
            startActivity(i);
        } else {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        }
        finish();
    }
}