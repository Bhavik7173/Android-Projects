package com.gi.application1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements Runnable{

    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler();
        handler.postDelayed(this, 3000);
    }

    @Override
    public void run() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
        Log.d("mylog","MainActivity");
    }
}