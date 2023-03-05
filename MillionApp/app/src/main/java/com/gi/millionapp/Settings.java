package com.gi.millionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Settings extends AppCompatActivity {
    private Button ok_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ok_btn = findViewById(R.id.bt_ok);

        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToPlayScreen();
            }
        });
    }
    private void goToPlayScreen() {

        Intent intentSetting = new Intent(Settings.this,PlayActivity.class);
        startActivity(intentSetting);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intentSetting = new Intent(Settings.this,PlayActivity.class);
        startActivity(intentSetting);
    }
}