package com.gi.listview1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.gi.listview1.adapter.CustomAdapter;

public class MusicListActivity extends AppCompatActivity {

    ListView lst;

    String song_name[] = {"Song 1","Song 2","Song 3","Song 4","Song 5","Song 6","Song 7","Song 8","Song 9","Song 10","Song 11","Song 12","Song 13","Song 14","Song 15"};
    String player_name[] = {"Player 1","Player 2","Player 3","Player 4","Player 5","Player 6","Player 7","Player 8","Player 9","Player 10","Player 11","Player 12","Player 13","Player 14","Player 15"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);
        lst = findViewById(R.id.lst);
        CustomAdapter adapter;
        adapter = new CustomAdapter(song_name,player_name,this);
        lst.setAdapter(adapter);
        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MusicListActivity.this,song_name[i] + " Clicked",Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(MusicListActivity.this,DetailActivity.class);
                intent.putExtra("Key",song_name[i]);
                intent.putExtra("Key1",player_name [i]);
                startActivity(intent);
            }
        });
    }

}