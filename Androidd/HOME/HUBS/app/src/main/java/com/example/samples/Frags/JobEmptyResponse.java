package com.example.samples.Frags;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.samples.HubsHome;
import com.example.samples.R;

public class JobEmptyResponse extends AppCompatActivity {

    TextView Msg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_empty_response);
        Msg = findViewById(R.id.Msg);
        Intent intent = getIntent();
        Msg.setText(intent.getStringExtra("Msg"));
    }
    @Override
    public void onBackPressed() {
        SharedPreferences sharedPreferences = getSharedPreferences("EMAIL", Context.MODE_PRIVATE);
        Intent intent = new Intent(this, HubsHome.class);
        intent.putExtra("email",sharedPreferences.getString("email",null));
        startActivity(intent);
        super.onBackPressed();
    }
}
