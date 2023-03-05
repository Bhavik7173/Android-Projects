package com.gi.uday;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ResourceBundle;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner sfont, scolor, sstyle;
    String[] ar1;
    String[] ar2;
    String[] ar3;
    Button apply;
    TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        scolor = findViewById(R.id.colorArray);
        sfont = findViewById(R.id.sizeArray);
        sstyle = findViewById(R.id.styleArray);

        scolor.setOnItemSelectedListener(this);
        sfont.setOnItemSelectedListener(this);
        sstyle.setOnItemSelectedListener(this);

//        ar1 = getResources().getStringArray(R.array.colorArray);
//        ar2 = getResources().getStringArray(R.array.colorArray);
//        ar3 = getResources().getStringArray(R.array.colorArray);
        Resources res= getResources();
        ar1 = res.getStringArray(R.array.colorArray);
        ar2 = res.getStringArray(R.array.fontSizeArray);
        ar3 = res.getStringArray(R.array.fontStyleArray);
    }



    public void applyBtn(View v)
    {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        int id = adapterView.getId();
        if(id == R.id.colorArray)
        {
            int size = Color.parseColor(ar1[i]) ;
            name.setTextColor(size);
        }
        else if(id == R.id.styleArray)
        {
            switch(i)
            {
                case 0 :
                    name.setTypeface(null, Typeface.BOLD);
                    break;
                case 1 :
                    name.setTypeface(null, Typeface.ITALIC);
                    break;
                case 2 :
                    name.setTypeface(null, Typeface.BOLD_ITALIC);
                    break;
                case 3 :
                    name.setTypeface(null, Typeface.NORMAL);
                    break;
            }
        }
        else if(id == R.id.sizeArray)
        {
            float size = Float.parseFloat(ar2[i]);
            name.setTextSize(size);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}