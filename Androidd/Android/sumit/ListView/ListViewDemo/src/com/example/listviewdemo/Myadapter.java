package com.example.listviewdemo;

import java.io.File;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;


public class Myadapter  extends BaseAdapter{

	String path = Environment.getExternalStorageDirectory().toString()+"/temp";
	File f = new File(path);
	String data[] = f.list();
	int n;
	
	MainActivity m;
	public Myadapter(MainActivity m)
	{
		this.m = m;
		Toast.makeText(m, path, Toast.LENGTH_LONG).show();
	}
	
	@Override
	public int getCount() {
		return data.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int index, View arg1, ViewGroup arg2) {
		View v;
		
		LayoutInflater in;
		in = LayoutInflater.from(m);
		
		v = in.inflate(R.layout.pattern,null);
		
		TextView tx1 = (TextView)v.findViewById(R.id.tx1);
		tx1.setText(data[index]);
		
		TextView tx2 = (TextView)v.findViewById(R.id.tx2);
		File file = new File(path+"/"+data[index]);		
		tx2.setText(""+file.length());
		
		ImageView iv;
		iv = (ImageView)v.findViewById(R.id.iv);
		Bitmap bmp;
		bmp = BitmapFactory.decodeFile(path+"/"+data[index]);
		iv.setImageBitmap(bmp);
		
		Button b;
		b = (Button)v.findViewById(R.id.btn);
		n = index;
		
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				File temp = new File(path+"/"+data[n]);
				temp.delete();
				m.refresh();
			}
		});
		
		return v;
	}

}
