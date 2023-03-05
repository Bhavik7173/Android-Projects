package com.example.sample;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sample.Frags.AllEventData;
import com.example.sample.Frags.RCAdapter_E;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EventsActivity extends AppCompatActivity {

    String data;
    Context context;
    Context context1;
    ArrayList<String> EID;
    ArrayList<String> EN;
    ArrayList<String> ED;
    ArrayList<String> EF;
    ArrayList<String> ESD;
    ArrayList<String> EED;
    ArrayList<String> EET;
    ArrayList<String> EST;
    ArrayList<String> EL;
    ArrayList<String> EI;
    ArrayList<String> like;
    ArrayList<String> dislike;
    ArrayList<String> interest;
    ArrayList<String> ldl;
    ArrayList<String> INI;
    ArrayList<AllEventData> ar;
    String email,id;
    RecyclerView rc;
    TextView Msg;
    int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler__view__event);
        Intent intent=getIntent();
        data = intent.getStringExtra("data");
        context = getBaseContext();
        rc = findViewById(R.id.rve);
        Msg = findViewById(R.id.Msg);

        SharedPreferences sharedPreferences = getSharedPreferences("EMAIL", MODE_PRIVATE);
        email = sharedPreferences.getString("email", null);
        id = sharedPreferences.getString("id",null);

        Log.d("Table",data+" "+email);
        ar = new ArrayList<>();
        EID = new ArrayList<>();
        EN = new ArrayList<>();
        ED = new ArrayList<>();
        EF = new ArrayList<>();
        ESD = new ArrayList<>();
        EED = new ArrayList<>();
        EI = new ArrayList<>();
        EL = new ArrayList<>();
        EST = new ArrayList<>();
        EET = new ArrayList<>();
        like = new ArrayList<>();
        dislike = new ArrayList<>();
        interest = new ArrayList<>();
        ldl = new ArrayList<>();
        INI = new ArrayList<>();

        rc.setLayoutManager(new LinearLayoutManager(this));
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String time = dateFormat.format(new Date()).toString();

        Retrofit r = MyRetrofit.getRetrofit(getString(R.string.IP1));
        Call<ArrayList<AllEventData>> call;
        Api api = r.create(Api.class);
        call = api.getDataEvent(data+","+time,id);
        Log.d("Whyred123",data+","+time+","+email);
        call.enqueue(new Callback<ArrayList<AllEventData>>() {
            @Override
            public void onResponse(Call<ArrayList<AllEventData>> call, Response<ArrayList<AllEventData>> response) {

                if (response.body().isEmpty()){
                        flag++;
                    String msg="";
                    if(data.equals("EVENTS"))
                    {
                        msg = "No Events";
                    }
                    else if(data.equals("POPULAR EVENTS"))
                    {
                        msg = "No Popular Events";
                    }
                    else if(data.equals("RECENT EVENTS"))
                    {
                        msg = "No Recent Events";
                    }
                    else if(data.equals("TEVENTS"))
                    {
                        msg = "No Event Today";
                    }
                    else if(data.equals("LIKED"))
                    {
                        msg = "You didn't like any event!";
                    }
                    else if(data.equals("DISLIKED"))
                    {
                        msg = "You didn't dislike any event!";
                    }
                    else if(data.equals("INTERESTED"))
                    {
                        msg = "You are not interested any event!";
                    }
                    else
                    {
                        msg = "You Have Not Posted Any Event";
                    }

                    change(flag,msg);
                    Log.d("Whyred", flag+"");
                }
                else {
                    ar = response.body();
                    for (AllEventData d : ar) {

                        Log.d("DataE", d.toString());
                        EID.add(d.getEID());
                        EN.add(d.getEN());
                        ED.add(d.getED());
                        ESD.add(d.getESD());
                        EED.add(d.getEED());
                        EI.add(d.getEI());
                        EL.add(d.getEL());
                        EF.add(d.getEF());
                        EST.add(d.getEST());
                        EET.add(d.getEET());
                        like.add(d.getLike());
                        dislike.add(d.getDislike());
                        interest.add(d.getInterest());
                        ldl.add(d.getLdl());
                        INI.add(d.getINI());
                    }
                    int s = EN.size();
                    Log.d("WhyredE", s + "");
                    RCAdapter_E rcAdapter_e = new RCAdapter_E(EID, EN, ED, EF, EI, ESD, EED, EST, EET, EL, like, dislike, ldl, INI, interest, context);
                    rc.setAdapter(rcAdapter_e);
                }


            }

            @Override
            public void onFailure(Call<ArrayList<AllEventData>> call, Throwable t) {
                Log.d("WhyredOkay",t.toString());
            }
        });



    }
    public void change(int flag, String msg)
    {
        if(flag>=1)
        {
            Msg.setText(msg);
            Msg.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
