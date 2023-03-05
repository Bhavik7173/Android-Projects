package com.example.samples;

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

import com.example.samples.Api;
import com.example.samples.Frags.AllEventData;
import com.example.samples.Frags.RCAdapter_E;
import com.example.samples.HubsHome;
import com.example.samples.MyRetrofit;
import com.example.samples.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EventsActivity extends AppCompatActivity {

    String data;
    Context context;
    Context context1;
    List<String> EID;
    List<String> EN;
    List<String> ED;
    List<String> EF;
    List<String> ESD;
    List<String> EED;
    List<String> EET;
    List<String> EST;
    List<String> EL;
    List<String> EI;
    List<String> like;
    List<String> dislike;
    List<String> interest;
    List<String> ldl;
    List<String> INI;
    List<AllEventData> ar;
    String email="";
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

        Retrofit r = MyRetrofit.getRetrofit(getString(R.string.IP));
        Call<List<AllEventData>> call;
        Api api = r.create(Api.class);
        call = api.getDataEvent(data+","+time,email);
        Log.d("Whyred123",data+","+time+","+email);
        call.enqueue(new Callback<List<AllEventData>>() {
            @Override
            public void onResponse(Call<List<AllEventData>> call, Response<List<AllEventData>> response) {

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
            public void onFailure(Call<List<AllEventData>> call, Throwable t) {
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
