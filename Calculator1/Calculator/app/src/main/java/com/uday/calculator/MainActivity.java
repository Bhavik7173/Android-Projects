package com.uday.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button b1, b2, b3, b4, b5;
    EditText e1, e2;
    TextView t1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = findViewById(R.id.plus);
        b2 = findViewById(R.id.subtraction);
        b3 = findViewById(R.id.multiplication);
        b4 = findViewById(R.id.division);
        e1 = findViewById(R.id.number1);
        e2 = findViewById(R.id.number2);
        t1 = findViewById(R.id.answer);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number1 = e1.getText().toString();
                String number2 = e2.getText().toString();
                String answer = t1.getText().toString();
                Intent intent = new Intent(getApplicationContext(), Plus_Page.class);
                intent.putExtra("NUMBER 1", number1);
                intent.putExtra("NUMBER 2", number2);
                startActivity(intent);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number1 = e1.getText().toString();
                String number2 = e2.getText().toString();
                Intent intent = new Intent(getApplicationContext(), Subtraction_Page.class);
                intent.putExtra("NUMBER 1", number1);
                intent.putExtra("NUMBER 2", number2);
                startActivity(intent);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number1 = e1.getText().toString();
                String number2 = e2.getText().toString();
                Intent intent = new Intent(getApplicationContext(), Multiplication_Page.class);
                intent.putExtra("NUMBER 1", number1);
                intent.putExtra("NUMBER 2", number2);
                startActivity(intent);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number1 = e1.getText().toString();
                String number2 = e2.getText().toString();
                Intent intent = new Intent(getApplicationContext(), Division_Page.class);
                intent.putExtra("NUMBER 1", number1);
                intent.putExtra("NUMBER 2", number2);
                startActivity(intent);
            }
        });
    }

    public void Plus(View view) {
        Intent intent = new Intent(this, Plus_Page.class);
        startActivity(intent);
    }
}