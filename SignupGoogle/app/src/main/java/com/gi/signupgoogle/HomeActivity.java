package com.gi.signupgoogle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    private TextView geeksforgeeks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        geeksforgeeks = findViewById(R.id.gfg);
        geeksforgeeks.setText(
                "GeeksForGeeks(Firebase Authentication)");
    }
}