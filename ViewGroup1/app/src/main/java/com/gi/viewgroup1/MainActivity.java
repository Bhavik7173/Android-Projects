package com.gi.viewgroup1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button b1, b2, b3, b4, b5, b6, b7, b8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = findViewById(R.id.textview);
        b2 = findViewById(R.id.edittext);
        b3 = findViewById(R.id.buttonview);
        b4 = findViewById(R.id.radiobutton);
        b5 = findViewById(R.id.checkbox1);
        b6 = findViewById(R.id.imagebutton);
        b7 = findViewById(R.id.togglebutton);
        b8 = findViewById(R.id.ratingbar);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        b8.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == findViewById(R.id.textview)) {
            Intent i = new Intent(this, TextView1.class);
            startActivity(i);
        } else if (view == findViewById(R.id.edittext)) {
            Intent i = new Intent(this, EditText1.class);
            startActivity(i);
        } else if (view == findViewById(R.id.buttonview)) {
            Intent i = new Intent(this, ButtonView1.class);
            startActivity(i);
        } else if (view == findViewById(R.id.radiobutton)) {
            Intent i = new Intent(this, RadioButton1.class);
            startActivity(i);
        } else if (view == findViewById(R.id.checkbox1)) {
            Intent i = new Intent(this, CheckBox1.class);
            startActivity(i);
        } else if (view == findViewById(R.id.imagebutton)) {
            Intent i = new Intent(this, ImageButton1.class);
            startActivity(i);
        } else if (view == findViewById(R.id.togglebutton)) {
            Intent i = new Intent(this, ToggleButton1.class);
            startActivity(i);
        } else if (view == findViewById(R.id.ratingbar)) {
            Intent i = new Intent(this, RatingBar1.class);
            startActivity(i);
        }
    }
}