package com.example.samples.Frags;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.samples.Api;
import com.example.samples.CompanyResponse;
import com.example.samples.HubsHome;
import com.example.samples.MainActivity;
import com.example.samples.MyRetrofit;
import com.example.samples.ProProfileResponse;
import com.example.samples.R;

import java.lang.reflect.Field;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ProProfileDetails extends AppCompatActivity {

    EditText Qualification,YOP,Experience,Link;
    Api api;
    Context context;
    View view;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pro_profile_details);
        view = getWindow().getDecorView().getRootView();
        context = this;

        Qualification = findViewById(R.id.Qua);
        YOP = findViewById(R.id.YOP);
        Experience = findViewById(R.id.Exp);
        Link = findViewById(R.id.Link);
        Intent intent = getIntent();
        Qualification.setText(intent.getStringExtra("Qualification"));
        YOP.setText(intent.getStringExtra("YOP"));
        Experience.setText(intent.getStringExtra("Experience"));
        Link.setText(intent.getStringExtra("Link"));
        SharedPreferences sharedPreferences = getSharedPreferences("EMAIL",MODE_PRIVATE);
        email = sharedPreferences.getString("email",null);
        YOP.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View view) {
                Log.d("WhyredPto","hello");
                show();
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void show()
    {

        final Dialog d = new Dialog(this);
        d.setTitle("Year Of Passing");
        d.setContentView(R.layout.pickerdialog);
        Button b1 = (Button) d.findViewById(R.id.button1);
        Button b2 = (Button) d.findViewById(R.id.button2);
        Date date = new Date();

        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
        np.setMaxValue(date.getYear()+1900); // max value 100
        np.setMinValue(1999);   // min value 0
        np.setWrapSelectorWheel(false);
        np.setTextColor(getResources().getColor(R.color.colorAccent));
        np.setTextColor(getResources().getColor(R.color.colorAccent));

        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker numberPicker, int i, int i1) {

            }
        });
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                YOP.setText(String.valueOf(np.getValue())); //set the value to textview
                d.dismiss();
            }
        });
        b2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                d.dismiss(); // dismiss the dialog
            }
        });
        d.show();


    }

    public void updateProProfile(View view) {
        int flag = 0;
        if(Qualification.getText().toString().isEmpty())
        {
            flag++;
            Qualification.setError("Please fill the empty field!");
        }
        if(YOP.getText().toString().isEmpty())
        {
            flag++;
            YOP.setError("Please fill the empty field!");
        }
        if(Experience.getText().toString().isEmpty())
        {
            flag++;
            Experience.setError("Please fill the empty field!");
        }
        if(Link.getText().toString().isEmpty())
        {
            flag++;
            Link.setError("Please fill the empty field!");
        }

        if(flag==0)
        {

            Log.d("companyu",Qualification.getText()+" "+YOP.getText()+" "+Experience.getText()+" "+Link.getText()+"");
            Retrofit instance= MyRetrofit.getRetrofit(getString(R.string.IP));

            Call<ProProfileResponse> call;
            api=instance.create(Api.class);
            call=api.proprofile_u(Qualification.getText()+"", YOP.getText()+"", Experience.getText()+"", Link.getText()+"", email);

            call.enqueue(new Callback<ProProfileResponse>() {
                @Override
                public void onResponse(Call<ProProfileResponse> call, Response<ProProfileResponse> response) {
                    final ProProfileResponse proProfileResponse=response.body();
                    Log.d("responseP",proProfileResponse.getStatus());

                    finish(proProfileResponse.getStatus(),"Your professional profile details has been \nsuccessfully updated!");
                }

                @Override
                public void onFailure(Call<ProProfileResponse> call, Throwable t) {
                    Log.d("responseP","error : "+t.toString());
                }
            });

        }
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
