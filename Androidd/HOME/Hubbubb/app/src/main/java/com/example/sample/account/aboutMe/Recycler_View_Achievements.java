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
import com.example.sample.Frags.RCAdapter_Ach;
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
    List<String> Ach;
    List<String> Year;
    List<String> Rank;
    List<String> Image;
    List<AllAchievementData> ar;
    RecyclerView rc;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler__view__achievements);

        Intent intent=getIntent();
        data = intent.getStringExtra("data");
        Log.d("Table",data);
        context = getBaseContext();
        rc = findViewById(R.id.rva);

        ar = new ArrayList<>();
        Ach = new ArrayList<>();
        Year = new ArrayList<>();
        Rank = new ArrayList<>();
        Image = new ArrayList<>();
        rc.setLayoutManager(new LinearLayoutManager(context));
        Retrofit r = MyRetrofit.getRetrofit(getString(R.string.IP1));
        Call<List<AllAchievementData>> call;
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
        });
    }
    public void add(View view) {
        Log.d("WhyredOkay","Hello");
        Intent intent = new Intent(this, AddAchievement.class);
        SharedPreferences sharedPreferences = getSharedPreferences("EMAIL",MODE_PRIVATE);
        intent.putExtra("email",sharedPreferences.getString("email",null));
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }
}
