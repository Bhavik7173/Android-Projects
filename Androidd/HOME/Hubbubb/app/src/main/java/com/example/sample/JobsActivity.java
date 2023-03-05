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

import com.example.sample.Frags.AllJobData;
import com.example.sample.Frags.RCAdapter_J;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class JobsActivity extends AppCompatActivity {


    Context context1;
    String data;
    List<String> JID;
    List<String> JT;
    List<String> JRE;
    List<String> JF;
    List<String> JD;
    List<String> JS;
    List<String> JSD;
    List<String> JED;
    List<String> JI;
    List<String> JL;
    List<String> JTA;
    List<String> apply;
    String email="";
    List<AllJobData> ar;
    RecyclerView rc;
    TextView Msg;
    int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler__view__job);
        Intent intent=getIntent();
        data = intent.getStringExtra("data");
        rc = findViewById(R.id.rvj);
        Msg = findViewById(R.id.Msg);

        SharedPreferences sharedPreferences = getSharedPreferences("EMAIL", MODE_PRIVATE);
        email = sharedPreferences.getString("email", null);

        ar = new ArrayList<>();
        JID = new ArrayList<>();
        JT = new ArrayList<>();
        JL = new ArrayList<>();
        JRE = new ArrayList<>();
        JS = new ArrayList<>();
        JSD = new ArrayList<>();
        JED = new ArrayList<>();
        JI = new ArrayList<>();
        JD = new ArrayList<>();
        JTA = new ArrayList<>();
        JF=new ArrayList<>();
        apply = new ArrayList<>();

        rc.setLayoutManager(new LinearLayoutManager(this));
        Retrofit r = MyRetrofit.getRetrofit(getString(R.string.IP1));
        Call<List<AllJobData>> call;
        Api api = r.create(Api.class);
        Log.d("Whyred",data+" "+email);
        call = api.getDataJob(data,email);

        call.enqueue(new Callback<List<AllJobData>>() {
            @Override
            public void onResponse(Call<List<AllJobData>> call, Response<List<AllJobData>> response) {
                if (response.body().isEmpty()){
                    flag++;
                    String msg="";
                    if(data.equals("JOBS"))
                    {
                        msg = "No Jobs Posted";
                    }
                    else if(data.equals("RECENT JOBS"))
                    {
                        msg = "No Recent Jobs Posted";
                    }
                    else if(data.equals("POPULAR JOBS"))
                    {
                        msg = "No Popular Jobs Posted";
                    }
                    else if(data.equals("APPLIED"))
                    {
                        msg = "You have not applied for any job!";
                    }
                    else
                    {
                        msg = "You Have Not Posted Any Job";
                    }

                    change(flag,msg);
                    Log.d("Whyred", flag+"");
                }
                else {
                    ar = response.body();

                    for (AllJobData d : ar) {

                        Log.d("Data", d.toString());
                        JID.add(d.getJID());
                        JT.add(d.getJT());
                        JD.add(d.getJD());
                        JRE.add(d.getJRE());
                        JF.add(d.getJF());
                        JS.add(d.getJS());
                        JSD.add(d.getJSD());
                        JED.add(d.getJED());
                        JL.add(d.getJL());
                        JI.add(d.getJI());
                        JTA.add(d.getJTA());
                        apply.add(d.getApply());
                    }
                    int s = JRE.size();
                    Log.d("Whyred", s + "");
                    RCAdapter_J rcAdapter_j = new RCAdapter_J(JID, JT, JRE, JF, JS, JSD, JED, JI, JD, JL, JTA, apply, data, context1);
                    rc.setAdapter(rcAdapter_j);
                }
            }

            @Override
            public void onFailure(Call<List<AllJobData>> call, Throwable t) {

                Log.d("Whyred","Fail");
            }
        });


    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    public void change(int flag, String msg)
    {
        if(flag>=1)
        {
            Msg.setText(msg);
            Msg.setVisibility(View.VISIBLE);
        }
    }


}
