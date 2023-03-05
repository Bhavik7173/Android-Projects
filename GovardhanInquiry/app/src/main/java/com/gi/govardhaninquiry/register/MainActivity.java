package com.gi.govardhaninquiry.register;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.gi.govardhaninquiry.R;
import com.gi.govardhaninquiry.admin.AdminHome;

public class MainActivity extends AppCompatActivity implements Runnable {

    Handler handler;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler();
        handler.postDelayed(this, 3000);
        sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
    }

    @Override
    public void run() {
//        if (sharedPreferences.contains("status1")) {
//            if (sharedPreferences.getString("status", null).equals("Admin")) {
//                Intent intent = new Intent(MainActivity.this, AdminHome.class);
//                startActivity(intent);
//            }
//        } else{
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
//        }

    }
}