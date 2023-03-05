package com.gi.calculatordemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Plus_Page extends AppCompatActivity {

    TextView t1, t2, t3;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plus_page);
        t1 = findViewById(R.id.n1);
        t2 = findViewById(R.id.n2);
        t3 = findViewById(R.id.n3);
        b1 = findViewById(R.id.submit);
        Intent intent = getIntent();
        String number1 = intent.getStringExtra("NUMBER 1");
        String number2 = intent.getStringExtra("NUMBER 2");
        t1.setText(number1);
        t2.setText(number2);
    }

    public void Submit(View view) {
        String num1 = t1.getText().toString();
        int n1 = Integer.parseInt(num1);
        String num2 = t2.getText().toString();
        int n2 = Integer.parseInt(num2);
        int r1 = n1 + n2;
        t3.setText("" + r1);
    }
}