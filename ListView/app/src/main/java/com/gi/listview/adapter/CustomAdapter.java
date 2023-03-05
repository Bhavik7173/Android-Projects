package com.gi.listview.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gi.listview.R;

public class CustomAdapter extends BaseAdapter {
    String song[], player[];

    public CustomAdapter(String[] song, String[] player) {
        this.song = song;
        this.player = player;
    }

    public CustomAdapter() {
    }

    @Override
    public int getCount() {
        return song.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        View v = inflater.inflate(R.layout.activity_music_design, null);

        TextView song_tx, player_tx;
        song_tx = v.findViewById(R.id.song_name);
        player_tx = v.findViewById(R.id.player_name);

        song_tx.setText(song[i]);
        player_tx.setText(player[i]);

        return v;
    }
}
