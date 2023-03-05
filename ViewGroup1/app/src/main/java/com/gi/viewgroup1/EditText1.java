package com.gi.viewgroup1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class EditText1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text1);
        EditText ed1 = findViewById(R.id.sedittext);
        EditText ed2 = findViewById(R.id.sedittext2);
    }
}