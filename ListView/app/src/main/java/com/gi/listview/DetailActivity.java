package com.gi.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    TextView song_name,player_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        song_name = findViewById(R.id.song_name);
        player_name = findViewById(R.id.singer_name);

        Intent i = getIntent();
        String song_name1 = i.getStringExtra("Key");
        String player_name1 = i.getStringExtra("Key1");

        song_name.setText(song_name1);
        player_name.setText(player_name1);
        Log.d("gilog",song_name1 +" : "+player_name1);
    }
}