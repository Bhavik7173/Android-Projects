package com.gi.waitlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements  Runnable{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Handler handler = new Handler();
        handler.postDelayed(MainActivity.this, 3000);
    }

    @Override
    public void run() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        Log.d("mylog","MainActivity");
    }
}