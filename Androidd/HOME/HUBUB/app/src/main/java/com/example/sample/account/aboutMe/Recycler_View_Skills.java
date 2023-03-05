package com.example.sample.account.aboutMe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sample.Api;
import com.example.sample.Frags.AddSkill;
import com.example.sample.Frags.AllSkillData;
import com.example.sample.Frags.RCAdapter_S;
import com.example.sample.MyRetrofit;
import com.example.sample.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Recycler_View_Skills extends AppCompatActivity {

    Context context;
    List<String> Skill;
    List<String> Time;
    List<AllSkillData> ar;
    RecyclerView rc;
    String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler__view__skills);

        Intent intent=getIntent();
        data = intent.getStringExtra("data");
        Log.d("Table",data);
        context = getBaseContext();
        rc = findViewById(R.id.rvs);

        ar = new ArrayList<>();
        Skill = new ArrayList<>();
        Time = new ArrayList<>();
        rc.setLayoutManager(new LinearLayoutManager(this));
        Retrofit r = MyRetrofit.getRetrofit(getString(R.string.IP1));
        Call<List<AllSkillData>> call;
        Api api = r.create(Api.class);
        call = api.getDataSkill(data);

        call.enqueue(new Callback<List<AllSkillData>>() {
            @Override
            public void onResponse(Call<List<AllSkillData>> call, Response<List<AllSkillData>> response) {
                ar = response.body();
                for (AllSkillData d : ar) {

                    Log.d("DataE", d.toString());
                    Skill.add(d.getSkill());
                    Time.add(d.getTime());
                }
                int s = Skill.size();
                Log.d("WhyredE", s + "");
                RCAdapter_S rcAdapter_s = new RCAdapter_S(Skill, Time, context);
                rc.setAdapter(rcAdapter_s);
            }

            @Override
            public void onFailure(Call<List<AllSkillData>> call, Throwable t) {
                Log.d("WhyredOkay",t.toString());
            }
        });
    }
    public void add(View view) {
        Log.d("WhyredOkay","Hello");

        Intent intent = new Intent(this, AddSkill.class);
        SharedPreferences sharedPreferences = getSharedPreferences("EMAIL",MODE_PRIVATE);
        intent.putExtra("email",sharedPreferences.getString("email",null));
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }
}
