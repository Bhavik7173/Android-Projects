package com.gi.androidproject1.Copy;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.gi.androidproject1.R;

public class CopyActivity1 extends AppCompatActivity {

    LinearLayout l;
    EditText name, copyname;
    Button b1,b2,b3;
    Button reset, copy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copy1);
        l = findViewById(R.id.layout);
        name = findViewById(R.id.name);
        copyname = findViewById(R.id.copy);
        reset = findViewById(R.id.resetBtn);
        copy = findViewById(R.id.copyBtn);

        b1 = findViewById(R.id.redBtn);
        b2 = findViewById(R.id.greenBtn);
        b3 = findViewById(R.id.blueBtn);
    }

    public void copyBtn(View v) {
        String a = name.getText().toString();
        copyname.setText(a);
    }

    public void resetBtn(View v) {
        name.setText("");
        copyname.setText("");
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