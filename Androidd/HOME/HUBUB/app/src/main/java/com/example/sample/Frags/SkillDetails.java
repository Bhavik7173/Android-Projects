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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sample.Api;
import com.example.sample.HubsHome;
import com.example.sample.MyRetrofit;
import com.example.sample.R;
import com.example.sample.SkillResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SkillDetails extends AppCompatActivity {

    EditText Skill,Time;
    Api api;
    String email,func;
    View view;
    Context context;
    Button update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skill_details);
        view = getWindow().getDecorView().getRootView();
        context = this;
        Intent intent = getIntent();
        Log.d("Whyred1","skilldetails");
        Skill = findViewById(R.id.Skill);
        Time = findViewById(R.id.Time);
        update = findViewById(R.id.update);

        Skill.setText(intent.getStringExtra("Skill"));
        Time.setText(intent.getStringExtra("Time"));
        func = intent.getStringExtra("func");
        SharedPreferences sharedPreferences = getSharedPreferences("EMAIL",MODE_PRIVATE);
        email = sharedPreferences.getString("email",null);
        if(func.equals("Delete"))
        {
            update.setVisibility(View.GONE);

            final AlertDialog.Builder builder = new AlertDialog.Builder(context);
            ViewGroup viewGroup = (ViewGroup)view.findViewById(android.R.id.content);
            View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.confirmdialog, viewGroup, false);
            final Button[] ok = new Button[2];
            ok[0] = (Button)dialogView.findViewById(R.id.Yes);
            ok[1] = (Button)dialogView.findViewById(R.id.No);
            builder.setView(dialogView);
            builder.setCancelable(false);
            final AlertDialog alertDialog = builder.create();
            TextView msg = dialogView.findViewById(R.id.msg);
            msg.setText("Are you sure you want to \ndelete your skill?");
            alertDialog.show();

            ok[0].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.cancel();
                    deleteSkill();


                }
            });
            ok[1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.cancel();
                    Intent intent = new Intent(context, HubsHome.class);
                    startActivity(intent);
                }
            });


        }


    }

    public void updateSkill(View view) {
        int flag = 0;
        if(Skill.getText().toString().isEmpty())
        {
            flag++;
            Skill.setError("Please fill the empty field!");
        }
        if(Time.getText().toString().isEmpty())
        {
            flag++;
            Time.setError("Please fill the empty field!");
        }

        if(flag==0)
        {

            Log.d("skillu",Skill.getText()+" "+Time.getText()+"");
            Retrofit instance= MyRetrofit.getRetrofit(getString(R.string.IP1));

            Call<SkillResponse> call;
            api=instance.create(Api.class);
            call=api.skill_ud(Skill.getText()+"",Time.getText()+"",email,"Update");

            call.enqueue(new Callback<SkillResponse>() {
                @Override
                public void onResponse(Call<SkillResponse> call, Response<SkillResponse> response) {
                    final SkillResponse skillResponse=response.body();
                    Log.d("responseP",skillResponse.getStatus());

                    finish(skillResponse.getStatus(),"Your skill has been \nsuccessfully updated!");
                }

                @Override
                public void onFailure(Call<SkillResponse> call, Throwable t) {
                    Log.d("responseP","error : "+t.toString());
                }
            });

        }
    }
    public void deleteSkill() {


            Log.d("skill",Skill.getText()+""+Time.getText()+"");
            Retrofit instance= MyRetrofit.getRetrofit(getString(R.string.IP1));

            Call<SkillResponse> call;
            api=instance.create(Api.class);
            call=api.skill_ud(Skill.getText()+"",Time.getText()+"",email,"Delete");

            call.enqueue(new Callback<SkillResponse>() {
                @Override
                public void onResponse(Call<SkillResponse> call, Response<SkillResponse> response) {
                    final SkillResponse skillResponse=response.body();
                    Log.d("responseP",skillResponse.getStatus());

                    finish(skillResponse.getStatus(),"Your skill has been \nsuccessfully deleted!");
                }

                @Override
                public void onFailure(Call<SkillResponse> call, Throwable t) {
                    Log.d("responseP","error : "+t.toString());
                }
            });

        }

    public void finish(String check, String msg1)
    {
        if(check.equals("Success")) {

            final AlertDialog.Builder builder = new AlertDialog.Builder(context);
            ViewGroup viewGroup = (ViewGroup)view.findViewById(android.R.id.content);
            View dialogView = LayoutInflater.from(view.getContext()).inflate(R.layout.successdialog, viewGroup, false);
            final Button[] ok = new Button[1];
            ok[0] = (Button)dialogView.findViewById(R.id.ok);
            builder.setView(dialogView);
            builder.setCancelable(false);
            final AlertDialog alertDialog = builder.create();
            TextView msg = dialogView.findViewById(R.id.msg);
            msg.setText(msg1);
            alertDialog.show();

            ok[0].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.cancel();
                    Intent intent = new Intent(context, HubsHome.class);
                    startActivity(intent);


                }
            });
        }
        else if(check.equals("Failure"))
        {
            Toast.makeText(this,"Problem with Server", Toast.LENGTH_LONG).show();
        }
    }
}
