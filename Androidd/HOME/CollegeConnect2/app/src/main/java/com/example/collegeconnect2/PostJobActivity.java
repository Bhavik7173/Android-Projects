package com.example.collegeconnect2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PostJobActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_job);
    }

    public void next(View view) {
        Intent i =new Intent(this,PostEventActivity.class);
        startActivity(i);
    }

    public void file(View view) {
    }
}