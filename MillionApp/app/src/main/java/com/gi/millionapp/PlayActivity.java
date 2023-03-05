package com.gi.millionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class PlayActivity extends AppCompatActivity {

    Animation rotation_clock_wise,rotation_anti_clock_wise;
    ImageView imageView_outerwheel,imageView_inner_wheel;
    Button btPlayQuiz,btSettings;
    private static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        btPlayQuiz = findViewById(R.id.btPlayQuiz);
        btSettings = findViewById(R.id.btsettings);
        imageView_inner_wheel = findViewById(R.id.imgInnerView);
        imageView_outerwheel = findViewById(R.id.imgOuterView);
        rotation_anti_clock_wise = AnimationUtils.loadAnimation(this,R.anim.rotation_anti_clock_wise);
        rotation_clock_wise = AnimationUtils.loadAnimation(this,R.anim.rotation_clock_wise);

        imageView_inner_wheel.startAnimation(rotation_clock_wise);
        imageView_outerwheel.startAnimation(rotation_anti_clock_wise);

        context = getApplicationContext();

        btSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent settingIntent = new Intent(PlayActivity.this,Settings.class);
                startActivity(settingIntent);


            }
        });
        btPlayQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent playquizIntent = new Intent(PlayActivity.this,QuizActivity.class);
                startActivity(playquizIntent);

            }
        });
    }
}