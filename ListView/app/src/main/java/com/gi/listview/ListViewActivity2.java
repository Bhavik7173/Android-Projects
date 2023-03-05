package com.gi.listview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ListViewActivity2 extends AppCompatActivity {

    ListView lst;
    String ar[]={"Hibernate","MVC","Ethihcal Hacking"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view2);
        lst = findViewById(R.id.lst);
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.activity_list_design,ar);
        lst.setAdapter(adapter);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(ListViewActivity2.this,ar[i]+"Clicked",Toast.LENGTH_SHORT).show();
            }
        });
    }
}