package com.gi.androidproject1.color;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.gi.androidproject1.R;

public class MainActivity extends AppCompatActivity {
    LinearLayout l;
    Button b1,b2,b3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        l = findViewById(R.id.layout);
        b1 = findViewById(R.id.redBtn);
        b2 = findViewById(R.id.greenBtn);
        b3 = findViewById(R.id.blueBtn);
    }

    public void setColor(View v)
    {
        l.setBackgroundColor(Color.GREEN);
    }
    public void setColor1(View v)
    {
        l.setBackgroundColor(Color.RED);
    }
    public void setColor2(View v)
    {
        l.setBackgroundColor(Color.BLUE);
    }
}