package com.gi.mediaplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, Runnable {

    Spinner spinner;
    ImageView play,rewind,forward;
    TextView st,et;
    LottieAnimationView anim;
    MediaPlayer mediaPlayer;
    Boolean flag=false, pro_flag=false;
    Handler handler;
    String[] ways = {"In app","Phone Storage","Live Streaming"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.musicway);
        play = findViewById(R.id.playBtn);
        rewind = findViewById(R.id.rewindBtn);
        forward = findViewById(R.id.forwardBtn);
        st = findViewById(R.id.st);
        et = findViewById(R.id.et);
        anim = findViewById(R.id.anim);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,ways);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        play.setOnClickListener(this);
        rewind.setOnClickListener(this);
        forward.setOnClickListener(this);

        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View view) {
        Log.d("gilog","Click on");
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Log.d("gilog",i + "Click on");
        if(i==0)
        {
            inApp();
        }
        else if(i==1)
        {
            phoneStorage();
        }
        else if(i==2)
        {
            livestreaming();
        }
    }

    private void phoneStorage() {
    }

    private void livestreaming() {
    }

    private void inApp() {
        mediaPlayer = MediaPlayer.create(this,R.raw.dj);
        play(play);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void rewind(View view) {
        Log.d("gilog","Click on Rewind Button");
    }

    public void play(View view) {
        Log.d("gilog","Click on Play Button");
        handler = new Handler();
        pro_flag = true;
        handler.postDelayed(this,1000);
        ImageView imageView=(ImageView) view;
        if(flag==false)
        {
            mediaPlayer.start();
            anim.playAnimation();
            int i = mediaPlayer.getDuration() ;
            et.setText(time_convert(i));
            imageView.setBackgroundResource(R.drawable.pause);
            flag=true;
        }
        else
        {
            mediaPlayer.pause();
            anim.pauseAnimation();
            imageView.setBackgroundResource(R.drawable.play);
            flag=false;
        }
    }

    public void forward(View view) {
        Log.d("gilog","Click on Forward Button");
    }

    public String time_convert(int i)
    {
        i = i/1000;
        int m = i/60;
        i = i % 60;

        String time = m + " : "+i;
        return time;
    }
    @Override
    public void run() {
        st.setText(time_convert(mediaPlayer.getCurrentPosition()));
        if(pro_flag)
        {
            handler.postDelayed(this,1000);
        }
    }
    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.pause();
        Log.d("LifeCycle","onPause");
        flag=false;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mediaPlayer.start();
        Log.d("LifeCycle","onRestart");
        flag=true;
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("LifeCycle","onResume");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("LifeCycle","onDestroy");
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d("LifeCycle","onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("LifeCycle","onStop");
    }
}