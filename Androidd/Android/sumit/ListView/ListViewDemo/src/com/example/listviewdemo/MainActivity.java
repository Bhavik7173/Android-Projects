package com.example.listviewdemo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class MainActivity extends Activity {

	ListView ls;
	Myadapter ad;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);		
		ls = (ListView)findViewById(R.id.ls);
		
		ad = new Myadapter(this);
		
		ls.setAdapter(ad);
	}
	public void refresh()
	{
		ad = new Myadapter(this);		
		ls.setAdapter(ad);
	}	
}
