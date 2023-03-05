package com.gi.listview1.adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gi.listview1.R;

public class CustomAdapter extends BaseAdapter {
    Context context;
    String song[], player[], play[];

    public CustomAdapter(String[] song, String[] player, Context context) {
        this.song = song;
        this.player = player;
        this.context = context;
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        View v = inflater.inflate(R.layout.activity_music_design, null);

        TextView song_tx, player_tx;
        ImageView play,delete;
        song_tx = v.findViewById(R.id.song_name);
        player_tx = v.findViewById(R.id.player_name);
        play = v.findViewById(R.id.play);
        delete = v.findViewById(R.id.del);

        song_tx.setText(song[i]);
        player_tx.setText(player[i]);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, song[i] + " is clicked.", Toast.LENGTH_SHORT).show();
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, song[i] + " is deleted.", Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }
}
