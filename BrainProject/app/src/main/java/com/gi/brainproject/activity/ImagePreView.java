package com.gi.brainproject.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.gi.brainproject.R;

public class ImagePreView extends AppCompatActivity {

    Intent intent;
    String name, mobile, image, age, gender, image_path, status;
    ImageView iv;
    TextView pname, pcontact, pimage, page, pgen, pstatus;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        iv = findViewById(R.id.nImg1);
        pname = findViewById(R.id.nEdtt);
        pcontact = findViewById(R.id.mEdtt);
        pimage = findViewById(R.id.iEdtt);
        page = findViewById(R.id.aEdtt);
        pgen = findViewById(R.id.gEdtt);
        pstatus = findViewById(R.id.sEdtt);

        intent = getIntent();
        name = intent.getStringExtra("patient_name");
        mobile = intent.getStringExtra("patient_mobile");
        image = intent.getStringExtra("patient_image");
        age = intent.getStringExtra("patient_age");
        status = intent.getStringExtra("patient_status");
        gender = intent.getStringExtra("patient_gen");
        image_path = intent.getStringExtra("patient_image_path");
        Log.d("gilog_image", name + ":" + mobile + ":" + image);
        Glide.with(this)
                .load(image)
//                .override(300, 200)
                .into(iv);
        pname.setText("Name: " + name);
        pcontact.setText("Mobile: " + mobile);
        page.setText("Age: " + age);
        pgen.setText("Gender: " + gender);
        pstatus.setText("Status: " + status);
//        pstatus.setTextSize(20);
        pstatus.setTextColor(Color.RED);
        pimage.setText("Image: " + image_path);
    }
}