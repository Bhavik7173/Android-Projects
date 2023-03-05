package com.gi.activitylifecycle2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        t1 = findViewById(R.id.text1);
        Intent intent = getIntent();
        String a = intent.getStringExtra("Key2");
//        t1.setText(a);
        Toast.makeText(this, "Open the Second Activity", Toast.LENGTH_SHORT).show();

    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LifeCycle","onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LifeCycle","onDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("LifeCycle","onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("LifeCycle","onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LifeCycle","onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("LifeCycle","onPause");
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this, "Press The Back Button", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("message", "Answer from First Activity");
        //startActivity(i);     <----   will start new Activity
        setResult(1, i);
    }
}