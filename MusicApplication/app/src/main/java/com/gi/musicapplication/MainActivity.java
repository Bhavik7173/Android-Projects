package com.gi.musicapplication;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, AdapterView.OnItemSelectedListener, Runnable {

    Spinner spinner;
    TextView st, et;
    LottieAnimationView anim;
    MediaPlayer mediaPlayer;
    Handler handler;
    ImageView rewind, pause, play, forward;
    String[] ways = {"In app", "Phone Storage", "Live Streaming"};
    SeekBar seekBar;
    int i;
    Boolean flag = false,pro_flag=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        set();
    }

    public void set() {
        spinner = findViewById(R.id.music_way);
        st = findViewById(R.id.st);
        et = findViewById(R.id.et);
        play = findViewById(R.id.playBtn);
        rewind = findViewById(R.id.rewindBtn);
        forward = findViewById(R.id.forwardBtn);
        pause = findViewById(R.id.pauseBtn);
        anim = findViewById(R.id.anim);
        seekBar = findViewById(R.id.seekbar);

        handler = new Handler();
        ArrayAdapter adpater = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, ways);
        adpater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adpater);
        spinner.setOnItemSelectedListener(this);

        seekBar.setOnSeekBarChangeListener(this);
        enable(false);
    }

    public void pause(View view) {
        mediaPlayer.pause();
        anim.pauseAnimation();
        flag = false;
    }

    public void forward(View view) {
        anim.reverseAnimationSpeed();
        int cur = mediaPlayer.getCurrentPosition();
        cur = cur + 10 * 1000;
        if (cur <= i) {
            st.setText(time_convert(cur));
            mediaPlayer.seekTo(cur);
            seekBar.setProgress(cur);
        } else {
            seekBar.setProgress(i);
            mediaPlayer.seekTo(i);
            st.setText(time_convert(i));
        }
        Log.d("golig", cur + "");
    }

    public void rewind(View view) {

        anim.reverseAnimationSpeed();
        int cur = mediaPlayer.getCurrentPosition();
        cur = cur - 10 * 1000;
        if (cur >= 0) {
            mediaPlayer.seekTo(cur);
            st.setText(time_convert(cur));
            seekBar.setProgress(cur);
        } else {
            mediaPlayer.seekTo(0);
            st.setText(time_convert(0));
            seekBar.setProgress(0);
        }
        Log.d("golig", cur + "");
    }

    public void play(View view) {
        mediaPlayer.start();
        anim.playAnimation();
        pro_flag=true;
        handler.postDelayed(this, 1000);

        enable(true);
        play.setEnabled(false);
        flag = true;
        i = mediaPlayer.getDuration();
        seekBar.setMax(i);
        et.setText(time_convert(i));
    }

    public String time_convert(int i) {
        i = i / 1000;
        int m = i / 60;
        i = i % 60;

        String time = m + " : " + i;
        return time;
    }

    @Override
    public void run() {

        st.setText(time_convert(mediaPlayer.getCurrentPosition()));
        seekBar.setProgress(mediaPlayer.getCurrentPosition());
        if (flag==true) {
            handler.postDelayed(this, 1000);
        }
    }


    void enable(boolean s) {
        pause.setEnabled(s);
        rewind.setEnabled(s);
        forward.setEnabled(s);
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (i == 0) {
            Toast.makeText(this, "In app", Toast.LENGTH_SHORT).show();
            inapp();
        } else if (i == 1) {
            Toast.makeText(this, "Phone Storage", Toast.LENGTH_SHORT).show();
//            phoneStorage();
        } else if (i == 2) {
            Toast.makeText(this, "Live Streaming", Toast.LENGTH_SHORT).show();
//            livestreaming();
        }
    }

    private void inapp() {

        mediaPlayer = MediaPlayer.create(this, R.raw.gucci);
        play(play);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }



    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        mediaPlayer.seekTo(i);
        st.setText(time_convert(i));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

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