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
import com.example.sample.Frags.AddAchievement;
import com.example.sample.Frags.AllAchievementData;
import com.example.sample.Frags.AllSkillData;
import com.example.sample.Frags.RCAdapter_Ach;
import com.example.sample.Frags.RCAdapter_S;
import com.example.sample.MyRetrofit;
import com.example.sample.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Recycler_View_Achievements extends AppCompatActivity {

    Context context;
    ArrayList<String> Ach;
    ArrayList<String> Year;
    ArrayList<String> Rank;
    ArrayList<String> Image;
    ArrayList<AllAchievementData> ar;
    RecyclerView rc;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler__view__achievements);

        SharedPreferences sharedPreferences = getSharedPreferences("EMAIL", MODE_PRIVATE);
        String id = sharedPreferences.getString("id", null);

        Intent intent = getIntent();
        data = intent.getStringExtra("data");
        Log.d("Table", data);
        context = getBaseContext();
        rc = findViewById(R.id.rva);

        ar = new ArrayList<>();
        Ach = new ArrayList<>();
        Year = new ArrayList<>();
        Rank = new ArrayList<>();
        Image = new ArrayList<>();
        rc.setLayoutManager(new LinearLayoutManager(context));
        Retrofit r = MyRetrofit.getRetrofit(getString(R.string.IP1));
        Api api = r.create(Api.class);
        Call<ArrayList<AllAchievementData>> call = api.getDataAchievement(id);

        call.enqueue(new Callback<ArrayList<AllAchievementData>>() {
            @Override
            public void onResponse(Call<ArrayList<AllAchievementData>> call, Response<ArrayList<AllAchievementData>> response) {
                ar = response.body();
                RCAdapter_Ach ar1 = new RCAdapter_Ach(ar);
                rc.setAdapter(ar1);
                Log.d("Skill Data:", response.body().toString());
            }

            @Override
            public void onFailure(Call<ArrayList<AllAchievementData>> call, Throwable t) {
                Log.d("Skill Data", t.toString());
            }
        });

        /*Call<List<AllAchievementData>> call;
        Api api = r.create(Api.class);
        call = api.getDataAchievement(data);

        call.enqueue(new Callback<List<AllAchievementData>>() {
            @Override
            public void onResponse(Call<List<AllAchievementData>> call, Response<List<AllAchievementData>> response) {
                ar = response.body();
                for (AllAchievementData d : ar) {

                    Log.d("DataE", d.toString());
                    Ach.add(d.getAch());
                    Year.add(d.getYear());
                    Rank.add(d.getRank());
                    Image.add(d.getImage());
                }
                int s = Ach.size();
                Log.d("WhyredE", s + "");
                RCAdapter_Ach rcAdapter_ach = new RCAdapter_Ach(Ach, Year, Rank, Image, context);
                rc.setAdapter(rcAdapter_ach);
            }

            @Override
            public void onFailure(Call<List<AllAchievementData>> call, Throwable t) {
                Log.d("WhyredOkay",t.toString());
            }
        });*/
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
