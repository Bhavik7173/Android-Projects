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
import com.example.sample.Frags.AddCompanyProfile;
import com.example.sample.Frags.AllCompanyData;
import com.example.sample.Frags.RCAdapter_C;
import com.example.sample.MyRetrofit;
import com.example.sample.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Recycler_View_Company_Profile extends AppCompatActivity {

    Context context;
    List<String> C_Name;
    List<String> C_DOJ;
    List<String> C_DOL;
    List<String> C_Post;
    List<String> C_Salary;
    List<AllCompanyData> ar;
    RecyclerView rc;
    String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler__view__company__profile);

        Intent intent=getIntent();
        data = intent.getStringExtra("data");
        Log.d("Table",data);
        context = getBaseContext();
        rc = findViewById(R.id.rvcp);

        ar = new ArrayList<>();
        C_Name = new ArrayList<>();
        C_DOJ = new ArrayList<>();
        C_DOL = new ArrayList<>();
        C_Post = new ArrayList<>();
        C_Salary = new ArrayList<>();

        rc.setLayoutManager(new LinearLayoutManager(this));
        Retrofit r = MyRetrofit.getRetrofit(getString(R.string.IP1));
        Call<List<AllCompanyData>> call;
        Api api = r.create(Api.class);
        call = api.getDataCompany(data);

        call.enqueue(new Callback<List<AllCompanyData>>() {
            @Override
            public void onResponse(Call<List<AllCompanyData>> call, Response<List<AllCompanyData>> response) {
                ar = response.body();
                for (AllCompanyData d : ar) {

                    Log.d("DataE", d.toString());
                    C_Name.add(d.getC_Name());
                    C_DOJ.add(d.getC_DOJ());
                    C_DOL.add(d.getC_DOL());
                    C_Post.add(d.getC_Post());
                    C_Salary.add(d.getC_Salary());
                }
                int s = C_Name.size();
                Log.d("WhyredE", s + "");
                RCAdapter_C rcAdapter_c = new RCAdapter_C(C_Name, C_DOJ, C_DOL, C_Post, C_Salary, context);
                rc.setAdapter(rcAdapter_c);
            }

            @Override
            public void onFailure(Call<List<AllCompanyData>> call, Throwable t) {
                Log.d("WhyredOkay",t.toString());
            }
        });

    }

    public void add(View view) {
        Log.d("WhyredOkay","Hello");

       Intent intent = new Intent(this, AddCompanyProfile.class);
        SharedPreferences sharedPreferences = getSharedPreferences("EMAIL",MODE_PRIVATE);
        intent.putExtra("email",sharedPreferences.getString("email",null));
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }
}
