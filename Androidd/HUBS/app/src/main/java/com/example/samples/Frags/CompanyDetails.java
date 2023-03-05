package com.example.samples.Frags;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.samples.Api;
import com.example.samples.CompanyResponse;
import com.example.samples.HubsHome;
import com.example.samples.MyRetrofit;
import com.example.samples.R;
import com.example.samples.SkillResponse;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CompanyDetails extends AppCompatActivity {

    EditText CN,CDOJ,CDOL,CP,CS;
    int y,m,da;
    Api api;
    String email,func;
    View view;
    Context context;
    Button update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_details);

        y=m=da=0;
        view = getWindow().getDecorView().getRootView();
        context = this;
        Intent intent = getIntent();
        Log.d("Whyred1","skilldetails");
        CN = findViewById(R.id.CN);
        CDOJ = findViewById(R.id.CDOJ);
        CDOL = findViewById(R.id.CDOL);
        CP = findViewById(R.id.CP);
        CS = findViewById(R.id.CS);
        context = this;
        update = findViewById(R.id.update);

        CN.setText(intent.getStringExtra("CN"));
        CDOJ.setText(intent.getStringExtra("CDOJ"));
        CDOL.setText(intent.getStringExtra("CDOL"));
        CP.setText(intent.getStringExtra("CP"));
        CS.setText(intent.getStringExtra("CS"));
        func = intent.getStringExtra("func");
        SharedPreferences sharedPreferences = getSharedPreferences("EMAIL",MODE_PRIVATE);
        email = sharedPreferences.getString("email",null);


        Log.d("Whyred",y+":"+m+":"+da+"");
        CDOJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener listener;
                listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

                        CDOJ.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        CDOJ.setError(null);
                    }
                };
                Date d = new Date();
                Calendar c1 = Calendar.getInstance();
                Calendar c2 = Calendar.getInstance();
                Date d1 = new Date(d.getYear()-120,d.getMonth(),d.getDay());
                Date d2 = new Date(d.getYear(),d.getMonth(),d.getDay()+15);
                c1.setTime(d1);
                c2.setTime(d2);
                DatePickerDialog dp;
                dp = new DatePickerDialog(context, R.style.DialogTheme, listener, d.getYear() + 1900, d.getMonth(), d.getDay() + 15);
                dp.getDatePicker().setMinDate(c1.getTime().getTime());
                dp.getDatePicker().setMaxDate(c2.getTime().getTime());
                dp.show();
            }
        });

        CDOL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener listener;
                listener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {

                        CDOL.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        CDOL.setError(null);
                    }
                };
                Date d = new Date();

                int i1 =CDOJ.getText().toString().trim().indexOf("-");
                int i2 =CDOJ.getText().toString().trim().lastIndexOf("-");
                y = Integer.parseInt(CDOJ.getText().toString().trim().substring(0,i1));
                m = Integer.parseInt(CDOJ.getText().toString().trim().substring(i1+1,i2));
                da = Integer.parseInt(CDOJ.getText().toString().trim().substring(i2+1));

                Log.d("Whyred",y+"");
                y = d.getYear()+1900-y;
                Log.d("Whyred",y+"");
                Log.d("Whyred",m+"");
                m = d.getMonth()+1-m;
                Log.d("Whyred",m+"");
                Log.d("Whyred",da+"");
                da = d.getDay()+15-da;
                Log.d("Whyred",da+"");
                Calendar c1 = Calendar.getInstance();

                Date d1 = new Date(d.getYear()-y,d.getMonth()-m,d.getDay()+15-da);
                c1.setTime(d1);
                DatePickerDialog dp;
                dp = new DatePickerDialog(context, R.style.DialogTheme, listener, d.getYear() + 1900, d.getMonth(), d.getDay() + 15);
                dp.getDatePicker().setMinDate(c1.getTime().getTime());
                dp.show();
            }
        });

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
            msg.setText("Are you sure you want to \ndelete your company details?");
            alertDialog.show();

            ok[0].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.cancel();
                    deleteComapnyDetails();


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

    public void updateCompanyDetails(View view) {

        DateFormat dateFormat = new SimpleDateFormat("y-M-d");
        Date d = null;
        Date dsd=null,ded=null;
        int flag = 0;
        if(CN.getText().toString().isEmpty())
        {
            flag++;
            CN.setError("Please fill the empty field!");
        }
        if(CDOJ.getText().toString().isEmpty())
        {
            flag++;
            CDOJ.setError("Please fill the empty field!");
        }
        else
        {
            CDOJ.setError(null);
            try {
                dsd = d = dateFormat.parse(CDOJ.getText().toString().trim());
                Log.d("Whyred",dsd.getYear()+"");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(!CDOL.getText().toString().isEmpty())
        {
            CDOL.setError(null);
            try {
                ded = d = dateFormat.parse(CDOL.getText().toString().trim());
                Log.d("Whyred",ded.getYear()+"");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(CP.getText().toString().isEmpty())
        {
            flag++;
            CP.setError("Please fill the empty field!");
        }
        if(CS.getText().toString().isEmpty())
        {
            flag++;
            CS.setError("Please fill the empty field!");
        }
        if(!CDOJ.getText().toString().trim().isEmpty() && !CDOL.getText().toString().trim().isEmpty())
        {
            if(ded.before(dsd))
            {
                flag++;
                CDOJ.setError("Fill valid date of joining!");
                CDOL.setError("Fill valid date of leaving!");
            }
        }

        if(flag==0)
        {

            Log.d("companyu",CN.getText()+" "+CDOJ.getText()+" "+CDOL.getText()+" "+CP.getText()+" "+CS.getText()+"");
            Retrofit instance= MyRetrofit.getRetrofit(getString(R.string.IP));

            Call<CompanyResponse> call;
            api=instance.create(Api.class);
            call=api.company_ud(CN.getText()+"", CDOJ.getText()+"", CDOL.getText()+"", CP.getText()+"", CS.getText()+"",email,"Update");

            call.enqueue(new Callback<CompanyResponse>() {
                @Override
                public void onResponse(Call<CompanyResponse> call, Response<CompanyResponse> response) {
                    final CompanyResponse companyResponse=response.body();
                    Log.d("responseP",companyResponse.getStatus());

                    finish(companyResponse.getStatus(),"Your company details has been \nsuccessfully updated!");
                }

                @Override
                public void onFailure(Call<CompanyResponse> call, Throwable t) {
                    Log.d("responseP","error : "+t.toString());
                }
            });

        }
    }
    public void deleteComapnyDetails() {


        Log.d("companyu",CN.getText()+" "+CDOJ.getText()+" "+CDOL.getText()+" "+CP.getText()+" "+CS.getText()+"");
        Retrofit instance= MyRetrofit.getRetrofit(getString(R.string.IP));

        Call<CompanyResponse> call;
        api=instance.create(Api.class);
        call=api.company_ud(CN.getText()+"", CDOJ.getText()+"", CDOL.getText()+"", CP.getText()+"", CS.getText()+"",email,"Delete");

        call.enqueue(new Callback<CompanyResponse>() {
            @Override
            public void onResponse(Call<CompanyResponse> call, Response<CompanyResponse> response) {
                final CompanyResponse companyResponse=response.body();
                Log.d("responseP",companyResponse.getStatus());

                finish(companyResponse.getStatus(),"Your company details has been \nsuccessfully deleted!");
            }

            @Override
            public void onFailure(Call<CompanyResponse> call, Throwable t) {
                Log.d("responseP","error : "+t.toString());
            }
        });

    }
    public void finish(String check,String msg1)
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
            Toast.makeText(this,"Problem with Server",Toast.LENGTH_LONG).show();
        }
    }
}
