package com.example.wallet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class PasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
    }

    public void submit(View view) {
        Intent i = new Intent(PasswordActivity.this,HomeActivity.class);
        startActivity(i);
        finish();
    }
}