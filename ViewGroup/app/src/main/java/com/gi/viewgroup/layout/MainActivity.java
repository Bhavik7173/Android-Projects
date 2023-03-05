package com.gi.viewgroup.layout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.gi.viewgroup.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn_linear, btn_relative, btn_scroll, btn_table, btn_frame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_frame = findViewById(R.id.framelayout);
        btn_linear = findViewById(R.id.linearlayout);
        btn_relative = findViewById(R.id.relativelayout);
        btn_scroll = findViewById(R.id.scrollingview);
        btn_table = findViewById(R.id.tablelayout);

        btn_table.setOnClickListener(this);
        btn_frame.setOnClickListener(this);
        btn_scroll.setOnClickListener(this);
        btn_relative.setOnClickListener(this);
        btn_linear.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view == findViewById(R.id.linearlayout)) {
            Intent i = new Intent(this, SampleLinearLayout.class);
            startActivity(i);
        } else if (view == findViewById(R.id.relativelayout)) {
            Intent i = new Intent(this, SampleRelativeLayout.class);
            startActivity(i);
        } else if (view == findViewById(R.id.scrollingview)) {
            Intent i = new Intent(this, SampleScrollLayout.class);
            startActivity(i);
        } else if (view == findViewById(R.id.tablelayout)) {
            Intent i = new Intent(this, SampleTableLayout.class);
            startActivity(i);
        } else if (view == findViewById(R.id.framelayout)) {
            Intent i = new Intent(this, SampleFrameLayout.class);
            startActivity(i);
        }
    }
}