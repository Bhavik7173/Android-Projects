package com.example.sample.account.accountSecurity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.sample.Api;
import com.example.sample.Frags.StudentInfo;
import com.example.sample.MyRetrofit;
import com.example.sample.ProProfileResponse;
import com.example.sample.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class StudentProfileActivity extends AppCompatActivity {

    String email,id;
    Api api;
    ImageView Image;
    TextView Class,Div,Mentor;
    Context context;
    View view;
    Button edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        context = this;
        view = getWindow().getDecorView().findViewById(android.R.id.content);

        Class=view.findViewById(R.id.Class);
        Div=view.findViewById(R.id.Div);
        Mentor = findViewById(R.id.Mentor);
        edit= findViewById(R.id.edit);
        SharedPreferences sharedPreferences = getSharedPreferences("EMAIL", MODE_PRIVATE);
        email = sharedPreferences.getString("email",null);
        id = sharedPreferences.getString("id",null);


        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edit.getText().toString().equalsIgnoreCase("edit"))
                {
                    edit.setText("save");
                    Class.setEnabled(true);
                    Div.setClickable(true);
                    Mentor.setEnabled(true);
                }
                else if(edit.getText().toString().equalsIgnoreCase("save"))
                {
                    int flag=0;
                    if(Class.getText().toString().trim().isEmpty())
                    {
                        flag++;
                        Class.setError("Please fill the empty field!");
                    }
                    if(Div.getText().toString().trim().isEmpty())
                    {
                        flag++;
                        Div.setError("Please fill the empty field!");
                    }
                    if(Mentor.getText().toString().trim().isEmpty())
                    {
                        flag++;
                        Mentor.setError("Please fill the empty field!");
                    }
                    if(flag==0)
                    {
                        send();
                    }
                }
            }
        });
        Retrofit instance= MyRetrofit.getRetrofit(getString(R.string.IP1));
        Call<StudentInfo> call;
        api=instance.create(Api.class);
        call=api.studentinfo(id);

        call.enqueue(new Callback<StudentInfo>() {
            @Override
            public void onResponse(Call<StudentInfo> call, Response<StudentInfo> response) {
                StudentInfo studentInfo=response.body();
                Log.d("Whyred",studentInfo.getS_Class());

                set(studentInfo.getS_Class(),studentInfo.getDiv(),studentInfo.getMentor());
            }

            @Override
            public void onFailure(Call<StudentInfo> call, Throwable t) {
                Log.d("Whyred",t.toString());
            }
        });

    }
    void set(String Class, String Div, String Mentor)
    {
        Log.d("Whyred",Class+" "+Div+" "+Mentor);

        this.Class.setText(Class);
        this.Div.setText(Div);
        this.Mentor.setText(Mentor);
    }
    void send()
    {
        Log.d("studentu",Class.getText()+" "+Div.getText()+" "+Mentor.getText()+" "+email);
        Retrofit instance= MyRetrofit.getRetrofit(getString(R.string.IP1));

        Call<ProProfileResponse> call;
        api=instance.create(Api.class);
        call=api.studentprofile_u(Class.getText().toString(), Div.getText().toString(), Mentor.getText().toString(), id);

        call.enqueue(new Callback<ProProfileResponse>() {
            @Override
            public void onResponse(Call<ProProfileResponse> call, Response<ProProfileResponse> response) {

                Log.d("responseP",response.toString());
                Log.d("responseP",response.body().getStatus());

                finish(response.body().getStatus(),"Your student profile details has been \nsuccessfully updated!");
            }

            @Override
            public void onFailure(Call<ProProfileResponse> call, Throwable t) {
                Log.d("responseP","error : "+t.toString());
            }
        });

    }
    public void finish(String check, String msg1)
    {
        if(check.equals("Success")) {
            edit.setText("edit");
            Class.setEnabled(false);
            Div.setClickable(false);
            Mentor.setEnabled(false);

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
                    onBackPressed();
                }
            });
        }
        else if(check.equals("Failure"))
        {
            Toast.makeText(context,"Problem with Server", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
