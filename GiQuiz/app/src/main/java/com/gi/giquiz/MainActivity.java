package com.gi.giquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.gi.giquiz.Registration.Login;

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
        if (sharedPreferences.contains("status1")) {
            Intent intent = new Intent(MainActivity.this, Home.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
        }
    }
}