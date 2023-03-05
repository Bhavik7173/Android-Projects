package com.example.sample.Frags;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.example.sample.Api;
import com.example.sample.HubsHome;
import com.example.sample.MyRetrofit;
import com.example.sample.ProInfo;
import com.example.sample.ProProfileResponse;
import com.example.sample.R;

import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.Context.MODE_PRIVATE;

public class FragmentProProfile extends Fragment {

    String email;
    Api api;
    ImageView Image;
    View view;
    Context context;
    TextView Qualification,YOP,Experience,Link;
    Button edit;
    ProgressBar progressBar;

    public FragmentProProfile(Context context)
    {
        this.context=context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.proprofile,null);
        Qualification=view.findViewById(R.id.Qualification);
        YOP=view.findViewById(R.id.YOP);
        Experience=view.findViewById(R.id.Experience);
        Link =view.findViewById(R.id.Link);
        progressBar=view.findViewById(R.id.imageloading);
        progressBar.setVisibility(View.VISIBLE);
        Image=view.findViewById(R.id.Image);
        edit=view.findViewById(R.id.edit);
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("EMAIL", MODE_PRIVATE);
        email = sharedPreferences.getString("email",null);

        YOP.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.Q)
            @Override
            public void onClick(View view) {
                Log.d("WhyredPto","hello");
                show();
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(edit.getText().toString().equalsIgnoreCase("edit"))
                {
                    edit.setText("save");
                    Qualification.setEnabled(true);
                    YOP.setClickable(true);
                    YOP.setEnabled(true);
                    Experience.setEnabled(true);
                    Link.setEnabled(true);
                }
                else if(edit.getText().toString().equalsIgnoreCase("save"))
                {
                    int flag=0;
                    if(Qualification.getText().toString().trim().isEmpty())
                    {
                        flag++;
                        Qualification.setError("Please fill the empty field!");
                    }
                    if(YOP.getText().toString().trim().isEmpty())
                    {
                        flag++;
                        Experience.setError("Please fill the empty field!");
                    }
                    if(Experience.getText().toString().trim().isEmpty())
                    {
                        flag++;
                        Experience.setError("Please fill the empty field!");
                    }
                    if(Link.getText().toString().trim().isEmpty())
                    {
                        flag++;
                        Link.setError("Please fill the empty field!");
                    }
                    if(flag==0)
                    {
                        send();
                    }
                }
            }
        });
        Retrofit instance= MyRetrofit.getRetrofit(getString(R.string.IP1));
        Call<ProInfo> call;
        api=instance.create(Api.class);
        call=api.proinfo(email);

        call.enqueue(new Callback<ProInfo>() {
            @Override
            public void onResponse(Call<ProInfo> call, Response<ProInfo> response) {
               ProInfo proInfo=response.body();
                set(proInfo.getQualification(),proInfo.getYOP(),proInfo.getExperience(),proInfo.getLink(),proInfo.getImage());
            }

            @Override
            public void onFailure(Call<ProInfo> call, Throwable t) {
                Log.d("Whyred",t.toString());
            }
        });
        return view;
    }
    void set(String Qualification, String YOP, String Experience, String Link, String Image)
    {
        this.Qualification.setText(Qualification);
        this.YOP.setText(YOP);
        this.Experience.setText(Experience);
        this.Link.setText(Link);
        Log.d("imagepathfunction",Image);
        Glide.with(this.Image.getContext()).load(getString(R.string.IP1)+Image).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).apply(RequestOptions.circleCropTransform()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                return false;
            }
        }).into(this.Image);
    }
    void send()
    {
        Log.d("companyu",Qualification.getText()+" "+YOP.getText()+" "+Experience.getText()+" "+Link.getText()+"");
        Retrofit instance= MyRetrofit.getRetrofit(getString(R.string.IP1));

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
    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void show()
    {

        final Dialog d = new Dialog(context);
        d.setTitle("Year Of Passing");
        d.setContentView(R.layout.pickerdialog);
        Button b1 = (Button) d.findViewById(R.id.button1);
        Button b2 = (Button) d.findViewById(R.id.button2);
        Date date = new Date();

        final NumberPicker np = (NumberPicker) d.findViewById(R.id.numberPicker1);
        np.setMaxValue(date.getYear()+1900); // max value 100
        np.setMinValue(1900);   // min value 0
        if(YOP.getText().toString().trim().isEmpty())
            np.setValue(date.getYear()+1900);
        else
            np.setValue(Integer.parseInt(YOP.getText().toString().trim()));
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
    public void finish(String check, String msg1)
    {
        if(check.equals("Success")) {
            edit.setText("edit");
            Qualification.setEnabled(false);
            YOP.setClickable(false);
            YOP.setEnabled(false);
            Experience.setEnabled(false);
            Link.setEnabled(false);

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
            Toast.makeText(context,"Problem with Server", Toast.LENGTH_LONG).show();
        }
    }
}
