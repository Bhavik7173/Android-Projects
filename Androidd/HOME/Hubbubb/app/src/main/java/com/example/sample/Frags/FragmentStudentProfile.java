package com.example.sample.Frags;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sample.Api;
import com.example.sample.MyRetrofit;
import com.example.sample.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.Context.MODE_PRIVATE;

public class FragmentStudentProfile extends Fragment {

    String email;
    Api api;
    ImageView Image;
    TextView Class,Div,Mentor;
    ProgressBar progressBar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.studentprofile,null);
        Class=view.findViewById(R.id.Class);
        Div=view.findViewById(R.id.Div);
        Mentor =view.findViewById(R.id.Mentor);
        progressBar=view.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);
        Image=view.findViewById(R.id.Image);
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("EMAIL", MODE_PRIVATE);
        email = sharedPreferences.getString("email",null);
        Retrofit instance= MyRetrofit.getRetrofit(getString(R.string.IP));
        Call<StudentInfo> call;
        api=instance.create(Api.class);
        call=api.studentinfo(email);

        call.enqueue(new Callback<StudentInfo>() {
            @Override
            public void onResponse(Call<StudentInfo> call, Response<StudentInfo> response) {
                StudentInfo studentInfo=response.body();
                set(studentInfo.getS_Class(),studentInfo.getDiv(),studentInfo.getMentor());
            }

            @Override
            public void onFailure(Call<StudentInfo> call, Throwable t) {
                Log.d("Whyred",t.toString());
            }
        });

        return view;
    }
    void set(String Class, String Div, String Mentor)
    {
        this.Class.setText(Class);
        this.Div.setText(Div);
        this.Mentor.setText(Mentor);
    }
}
