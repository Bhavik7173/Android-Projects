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
import com.gi.ginquiry.admin.settings.AddSkill;

import java.util.ArrayList;
import java.util.List;

public class Recycler_View_Skills extends AppCompatActivity {

    Context context;
    List<String> Skill;
    List<String> Time;
    //List<AllSkillData> ar;
    RecyclerView rc;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler__view__skills);
//        Intent intent=getIntent();
//        data = intent.getStringExtra("data");
//        Log.d("Table",data);
//        context = getBaseContext();
        rc = findViewById(R.id.rvs);

//        ar = new ArrayList<>();
        Skill = new ArrayList<>();
        Time = new ArrayList<>();
        rc.setLayoutManager(new LinearLayoutManager(this));

    }

    public void add(View view) {
        Log.d("WhyredOkay", "Hello");

        Intent intent = new Intent(this, AddSkill.class);
        SharedPreferences sharedPreferences = getSharedPreferences("EMAIL", MODE_PRIVATE);
        intent.putExtra("email", sharedPreferences.getString("email", null));
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }
}
