package com.example.sample.Frags;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.sample.Api;
import com.example.sample.JobApplicationResponse;
import com.example.sample.JobsActivity;
import com.example.sample.MyRetrofit;
import com.example.sample.R;
import com.example.sample.account.myActivities.viewPDF;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class JobDetails extends AppCompatActivity {

    String JF,id;
    TextView JT,JD,JS,JRE,JL,JSD,JED;
    Api api;
    ImageView Img;
    String JID;
    Button JA;
    Context context;
    View view;
    String job_application,email,path,apply,data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_details);
        view = getWindow().getDecorView().getRootView();
        context = this;
        Intent intent=getIntent();
        JF=intent.getStringExtra("JF");
        JT=findViewById(R.id.JT);
        JA=findViewById(R.id.JA);
        JD=findViewById(R.id.JD);
        JS=findViewById(R.id.JS);
        JRE=findViewById(R.id.JRE);
        JL=findViewById(R.id.location);
        JSD=findViewById(R.id.JSD);
        JED=findViewById(R.id.JED);
        Img=findViewById(R.id.Img);


        JID = intent.getStringExtra("JID");
        JT.setText(intent.getStringExtra("JT"));
        JL.setText(intent.getStringExtra("JL"));
        JSD.setText(intent.getStringExtra("JSD"));
        JED.setText(intent.getStringExtra("JED"));

        JD.setText(intent.getStringExtra("JD"));
        JS.setText(intent.getStringExtra("JS"));
        JRE.setText(intent.getStringExtra("JRE"));
        path=intent.getStringExtra("JI");
        apply=intent.getStringExtra("apply");
        data=intent.getStringExtra("data");
        JA.setText(apply);
        Glide.with(Img.getContext()).load(getString(R.string.IP1)+path).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(Img);
    }

    public void apply(View view) {

        SharedPreferences sharedPreferences = getSharedPreferences("EMAIL", MODE_PRIVATE);
        email = sharedPreferences.getString("email",null);
        id = sharedPreferences.getString("id",null);
        Log.d("Whyred",email);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat curFormater = new SimpleDateFormat("yyyy-MM-dd");
        String date = curFormater.format(calendar.getTime());

        DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
        String time = dateFormat.format(new Date()).toString();

        job_application = "'"+JID+"','"+date+"','"+time+"'";


        Retrofit instance= MyRetrofit.getRetrofit(getString(R.string.IP1));
        Call<JobApplicationResponse> call;
        api=instance.create(Api.class);
        call=api.jobapplication_insert(JID,date,time,id);
        Log.d("Whyred",job_application+":"+id+":insert");

        call.enqueue(new Callback<JobApplicationResponse>() {
            @Override
            public void onResponse(Call<JobApplicationResponse> call, Response<JobApplicationResponse> response) {
                JobApplicationResponse jobApplicationResponse=response.body();

                String status=jobApplicationResponse.getStatus();
                Log.d("responseP",status);
                finish(status);
            }

            @Override
            public void onFailure(Call<JobApplicationResponse> call, Throwable t) {

                Log.d("responseP","error : "+t.toString());

            }
        });

    }
    void finish(String status)
    {
        if(status.equals("Success"))
        {
            final AlertDialog.Builder builder = new AlertDialog.Builder(context);
            ViewGroup viewGroup = (ViewGroup)view.findViewById(android.R.id.content);
            View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.successdialog, viewGroup, false);
            final Button[] ok = new Button[1];
            ok[0] = (Button)dialogView.findViewById(R.id.ok);
            builder.setView(dialogView);
            builder.setCancelable(false);
            final AlertDialog alertDialog = builder.create();
            TextView msg = dialogView.findViewById(R.id.msg);
            msg.setText("You have successfully applied\nfor the job!");
            alertDialog.show();

            ok[0].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    JA.setText("Cancel Application");
                    alertDialog.cancel();


                }
            });
        }
        else if(status.equals("Deleted"))
        {
            final AlertDialog.Builder builder = new AlertDialog.Builder(context);
            ViewGroup viewGroup = (ViewGroup)view.findViewById(android.R.id.content);
            View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.successdialog, viewGroup, false);
            final Button[] ok = new Button[1];
            ok[0] = (Button)dialogView.findViewById(R.id.ok);
            builder.setView(dialogView);
            builder.setCancelable(false);
            final AlertDialog alertDialog = builder.create();
            TextView msg = dialogView.findViewById(R.id.msg);
            msg.setText("You have successfully took back\nyour application for the job!");
            alertDialog.show();

            ok[0].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.cancel();


                }
            });
            JA.setText("Apply");
        }
        else if(status.equals("Applied"))
        {
            final AlertDialog.Builder builder = new AlertDialog.Builder(context);
            ViewGroup viewGroup = (ViewGroup) view.findViewById(android.R.id.content);
            View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.confirmdialog, viewGroup, false);
            final Button[] ok = new Button[2];
            ok[0] = (Button) dialogView.findViewById(R.id.Yes);
            ok[1] = (Button) dialogView.findViewById(R.id.No);
            builder.setView(dialogView);
            builder.setCancelable(false);
            final AlertDialog alertDialog = builder.create();
            TextView msg = dialogView.findViewById(R.id.msg);
            TextView title = dialogView.findViewById(R.id.title);
            msg.setText("It looks like you've already\napplied for this job earlier.\nWould you like to take back\nyour application back?");
            title.setText("Cancel Application");
            alertDialog.show();

            ok[1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.cancel();
                    JA.setText("Applied");
                }
            });
            ok[0].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.cancel();
                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    ViewGroup viewGroup = (ViewGroup) view.findViewById(android.R.id.content);
                    View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.confirmdialog, viewGroup, false);
                    final Button[] ok = new Button[2];
                    ok[0] = (Button) dialogView.findViewById(R.id.Yes);
                    ok[1] = (Button) dialogView.findViewById(R.id.No);
                    builder.setView(dialogView);
                    builder.setCancelable(false);
                    final AlertDialog alertDialog1 = builder.create();
                    TextView msg = dialogView.findViewById(R.id.msg);
                    msg.setText("Are you sure you want to take\nback your application for this job?");
                    alertDialog1.show();

                    ok[0].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog1.cancel();
                            deleteJobDetails();


                        }
                    });
                    ok[1].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alertDialog1.cancel();
                        }
                    });
                }
            });
        }

    }
    public void deleteJobDetails () {

        job_application="'"+JID+"','"+id+"'";
        Retrofit instance= MyRetrofit.getRetrofit(getString(R.string.IP1));
        Call<JobApplicationResponse> call;
        api=instance.create(Api.class);
        call=api.jobapplication_de(JID,id);
        Log.d("Whyred",job_application+":"+"delete");

        call.enqueue(new Callback<JobApplicationResponse>() {
            @Override
            public void onResponse(Call<JobApplicationResponse> call, Response<JobApplicationResponse> response) {
                JobApplicationResponse jobApplicationResponse=response.body();
                String status=jobApplicationResponse.getStatus();
                Log.d("responseP",status);
                finish(status);
            }

            @Override
            public void onFailure(Call<JobApplicationResponse> call, Throwable t) {

                Log.d("responseP","error : "+t.toString());

            }
        });


    }

    @Override
    public void onBackPressed() {
        Log.d("responseP",data);
        Intent intent=new Intent(context, JobsActivity.class);
        intent.putExtra("data",data);
        startActivity(intent);
        super.onBackPressed();
    }

    public void pdfview(View view) {
        Intent intent=new Intent(this, viewPDF.class);
        intent.putExtra("PDF",JF);
        intent.putExtra("Type","Job");

        startActivity(intent);
    }
}
