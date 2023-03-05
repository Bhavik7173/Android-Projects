package com.example.wallet;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class MyAdapter extends BaseAdapter {

    String path = Environment.getExternalStorageDirectory().toString() + "/temp";
    File f = new File(path);
    String data[] = f.list();
    int n;
    MainActivity m;

    public MyAdapter(MainActivity m) {
        this.m = m;
        Toast.makeText(m,path, Toast.LENGTH_LONG).show();
    }

    @Override
    public int getCount() {
        return data.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        LayoutInflater in;
        in = LayoutInflater.from(m);
        v = in.inflate(R.layout.pattern, null);

        EditText ed1 = (EditText) v.findViewById(R.id.EN);
        ed1.setText(data[position]);

        EditText ed2 = (EditText) v.findViewById(R.id.EA);
        ed2.setText(data[position]);

        ImageView iv;
        iv = (ImageView) v.findViewById(R.id.iv);
        Bitmap bmp;
        bmp = BitmapFactory.decodeFile(path + "/" + data[position]);
        iv.setImageBitmap(bmp);

        Button b;
        b = (Button) v.findViewById(R.id.send);
        n = position;

        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                File temp = new File(path + "/" + data[n]);
                temp.delete();
                m.refresh();
            }
        });

        return v;
    }
}
