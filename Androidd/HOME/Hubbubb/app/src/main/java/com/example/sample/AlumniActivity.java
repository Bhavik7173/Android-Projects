package com.example.sample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sample.Frags.AllAlumniData;
import com.example.sample.Frags.RCAdapter_A;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AlumniActivity extends AppCompatActivity {

    String data;
    Context context;
    List<String> Name;
    List<String> Age;
    List<String> Gender;
    List<String> Contact;
    List<String> Image;
    List<String> Status;
    List<String> Email;
    List<String> Qualification;
    List<String> YOP;
    List<String> Experience;
    List<String> Link;
    List<AllAlumniData> ar;
    RecyclerView rc;
    int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler__view__alumni);
        Intent intent=getIntent();
        data = intent.getStringExtra("data");
        Log.d("Table",data);
        context = getBaseContext();
        rc = findViewById(R.id.rva);


        ar = new ArrayList<>();
        Name = new ArrayList<>();
        Age = new ArrayList<>();
        Gender = new ArrayList<>();
        Contact = new ArrayList<>();
        Image = new ArrayList<>();
        Status = new ArrayList<>();
        Email = new ArrayList<>();
        Qualification = new ArrayList<>();
        YOP = new ArrayList<>();
        Experience = new ArrayList<>();
        Link = new ArrayList<>();

        rc.setLayoutManager(new LinearLayoutManager(this));
        Retrofit r = MyRetrofit.getRetrofit(getString(R.string.IP1));
        Call<List<AllAlumniData>> call;
        Api api = r.create(Api.class);
        call = api.getDataAlumni(data);

        call.enqueue(new Callback<List<AllAlumniData>>() {
            @Override
            public void onResponse(Call<List<AllAlumniData>> call, Response<List<AllAlumniData>> response) {
                ar = response.body();
                for (AllAlumniData d : ar) {

                    Log.d("DataE", d.toString());
                    Name.add(d.getName());
                    Age.add(d.getAge());
                    Gender.add(d.getGender());
                    Contact.add(d.getContact());
                    Image.add(d.getImage());
                    Status.add(d.getStatus());
                    Email.add(d.getEmail());
                    Qualification.add(d.getQualification());
                    YOP.add(d.getYOP());
                    Experience.add(d.getExperience());
                    Link.add(d.getLink());
                }
                int s = Name.size();
                Log.d("WhyredE", s + "");
                RCAdapter_A rcAdapter_a = new RCAdapter_A(Name, Age, Gender, Contact, Image, Status, Email, Qualification, YOP, Experience, Link, context);
                rc.setAdapter(rcAdapter_a);

            }

            @Override
            public void onFailure(Call<List<AllAlumniData>> call, Throwable t) {
                Log.d("WhyredOkay",t.toString());
            }
        });




    }

}
