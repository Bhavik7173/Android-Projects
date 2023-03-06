package com.gi.millioners.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import com.gi.millioners.R;


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
