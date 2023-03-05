package com.gi.androidproject1.Copy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.gi.androidproject1.R;

public class CopyActivity extends AppCompatActivity {

    LinearLayout l;
    EditText name,copyname;
    Button reset,copy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_copy);

        l = findViewById(R.id.layout);
        name = findViewById(R.id.name);
        copyname = findViewById(R.id.copy);
        reset = findViewById(R.id.resetBtn);
        copy = findViewById(R.id.copyBtn);
    }

    public void copyBtn(View v)
    {
        String a = name.getText().toString();
        copyname.setText(a);
    }
    public void resetBtn(View v)
    {
        name.setText("");
        copyname.setText("");
    }
}