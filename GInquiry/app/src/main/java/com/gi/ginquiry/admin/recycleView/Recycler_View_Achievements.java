package com.gi.ginquiry.admin.recycleView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gi.ginquiry.R;
import com.gi.ginquiry.admin.settings.AddAchievement;

import java.util.ArrayList;
import java.util.List;

public class Recycler_View_Achievements extends AppCompatActivity {

    Context context;
    List<String> Ach;
    List<String> Year;
    List<String> Rank;
    List<String> Image;
    //    List<AllAchievementData> ar;
    RecyclerView rc;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler__view__achievements);
//        Intent intent=getIntent();
//        data = intent.getStringExtra("data");
//        Log.d("Table",data);
//        context = getBaseContext();
        rc = findViewById(R.id.rva);

//        ar = new ArrayList<>();
        Ach = new ArrayList<>();
        Year = new ArrayList<>();
        Rank = new ArrayList<>();
        Image = new ArrayList<>();
        rc.setLayoutManager(new LinearLayoutManager(context));

    }

    public void add(View view) {
        Log.d("WhyredOkay", "Hello");
        Intent intent = new Intent(this, AddAchievement.class);
        SharedPreferences sharedPreferences = getSharedPreferences("EMAIL", MODE_PRIVATE);
        intent.putExtra("email", sharedPreferences.getString("email", null));
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }
}
