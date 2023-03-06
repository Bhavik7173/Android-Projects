package com.gi.application2.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.gi.application2.R;


public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}
