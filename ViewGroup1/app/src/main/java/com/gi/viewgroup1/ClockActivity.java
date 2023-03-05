package com.gi.viewgroup1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AnalogClock;
import android.widget.Button;
import android.widget.RatingBar;

import java.sql.Time;
import java.util.Date;

public class ClockActivity extends AppCompatActivity {

    RatingBar ratingBar;
    AnalogClock analogClock;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);
        ratingBar =findViewById(R.id.ratingbar);
        analogClock =findViewById(R.id.analog);
        b = findViewById(R.id.btn1);
    }
    public void click(View v)
    {
        Log.d("Gilog",ratingBar.getRating()+"");
        Time t = new Time(analogClock.getDrawingTime());
        Log.d("Gilog",t+"");
    }
}