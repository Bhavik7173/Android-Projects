package com.gi.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.gi.listview.adapter.CustomAdapter;

public class MusicListActivity extends AppCompatActivity {

    ListView lst;

    String song_name[] = {"Song1","Song2","Song3","Song4","Song5","Song6","Song7","Song8","Song9","Song10","Song11"};
    String player_name[] = {"Player1","Player2","Player3","Player4","Player5","Player6","Player7","Player8","Player9","Player10","Player11"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_list);
        lst = findViewById(R.id.lst);
        CustomAdapter adapter;
        adapter = new CustomAdapter(song_name,player_name);
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