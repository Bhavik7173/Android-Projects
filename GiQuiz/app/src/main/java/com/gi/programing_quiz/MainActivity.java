package com.gi.programing_quiz;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.gi.programing_quiz.Registration.Login;
import com.gi.programing_quiz.SharedPrefrence.SharedPre;

public class MainActivity extends AppCompatActivity implements Runnable {
    Handler handler;
//    SharedPreferences sharedPreferences;
SharedPre sharedPre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPre = new SharedPre(this);
        handler = new Handler();
        handler.postDelayed(this, 3000);
//        sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
    }

    @Override
    public void run() {
        if (sharedPre.readData("status","").equals("LoggedIn")) {
            Intent intent = new Intent(MainActivity.this, Home.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(MainActivity.this, Login.class);
            startActivity(intent);
        }
    }
}