package com.example.sample.Frags;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.sample.Api;
import com.example.sample.MyRetrofit;
import com.example.sample.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.Context.MODE_PRIVATE;

public class FragmentRateUs extends Fragment {

    String email;
    Api api;
    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.layout_rateus,null);

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("EMAIL", MODE_PRIVATE);
        email = sharedPreferences.getString("email",null);
        Log.d("email1",email+"Rate Us");
        final EditText RD = (EditText)view.findViewById(R.id.RD);
        Button RU = (Button) view.findViewById(R.id.RU);
        final RatingBar RR = (RatingBar) view.findViewById(R.id.RR);
        final float[] n = {0};
        final Button[] ok = new Button[1];

        RR.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean b) {
                n[0] = rating;
                Log.d("rate_us", n[0] +"");
                final String rate_entry ="'"+email.toString()+"','"+n[0]+"','"+RD.getText().toString()+"'";
                Log.d("job",rate_entry);

            }
        });

        RU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Log.d("Rating","BUTTON PRESSED");
final View v = view;
                Log.d("Rating",n[0]+"");
                final String rate_entry ="'"+email.toString()+"','"+n[0]+"','"+RD.getText().toString()+"'";

                Retrofit instance= MyRetrofit.getRetrofit(getString(R.string.IP1));
                Call<String> call;
                api=instance.create(Api.class);
                call = api.rateus(rate_entry);
                Log.d("Rating",rate_entry);

                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String rateUsResponse = response.body();
                        String status=rateUsResponse.toString();
                        Log.d("responseP",status);

                        if(status.equals("Success")) {
                            final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            ViewGroup viewGroup = (ViewGroup) view.findViewById(android.R.id.content);
                            View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.successdialog, viewGroup, false);
                            ok[0] = (Button) dialogView.findViewById(R.id.ok);
                            builder.setView(dialogView);
                            builder.setCancelable(false);
                            final AlertDialog alertDialog = builder.create();
                            alertDialog.show();

                            ok[0].setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    RR.setRating(0);
                                    RD.setText("");
                                    alertDialog.cancel();
                                }
                            });
                        }

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.d("responseP","error : "+t.toString());
                    }
                });

            }
        });

        return view;
    }
    public FragmentRateUs(Context context)
    {
        this.context=context;
    }
}
