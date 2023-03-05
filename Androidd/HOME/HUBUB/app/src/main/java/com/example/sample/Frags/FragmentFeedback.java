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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.sample.Api;
import com.example.sample.FeedbackResponse;
import com.example.sample.MyRetrofit;
import com.example.sample.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static android.content.Context.MODE_PRIVATE;

public class FragmentFeedback extends Fragment {

    String email;
    Api api;
    Context context;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view= inflater.inflate(R.layout.layout_feedback,null);

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("EMAIL", MODE_PRIVATE);
        email = sharedPreferences.getString("email",null);
        Log.d("email1",email+"Rate Us");
        final EditText YF = (EditText)view.findViewById(R.id.YF);
        final EditText sub = (EditText)view.findViewById(R.id.sub);
        Button SF = (Button) view.findViewById(R.id.SF);
        final Button[] ok = new Button[1];

        SF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Log.d("Rating","BUTTON PRESSED");
                final View v = view;
                final String feedback ="'"+email.toString()+"','"+sub.getText().toString()+"','"+YF.getText().toString()+"'";

                Retrofit instance= MyRetrofit.getRetrofit(getString(R.string.IP1));
                Call<FeedbackResponse> call;
                api=instance.create(Api.class);
                call = api.feedback(feedback);
                Log.d("Feedback",feedback);

                call.enqueue(new Callback<FeedbackResponse>() {
                    @Override
                    public void onResponse(Call<FeedbackResponse> call, Response<FeedbackResponse> response) {
                        FeedbackResponse feedbackResponse = response.body();
                        String status=feedbackResponse.getStatus();
                        Log.d("responseP",status);


                        if(status.equals("Success")) {
                            final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            ViewGroup viewGroup = (ViewGroup) view.findViewById(android.R.id.content);
                            View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.successdialog, viewGroup, false);
                            ok[0] = (Button) dialogView.findViewById(R.id.ok);
                            TextView msg = (TextView) dialogView.findViewById(R.id.msg);
                            msg.setText("Your feedback has been \nsuccessfully submitted!");
                            builder.setView(dialogView);
                            builder.setCancelable(false);
                            final AlertDialog alertDialog = builder.create();
                            alertDialog.show();

                            ok[0].setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    sub.setText("");
                                    YF.setText("");
                                    alertDialog.cancel();
                                }
                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<FeedbackResponse> call, Throwable t) {
                        Log.d("responseP","error : "+t.toString());
                    }
                });
            }
        });

        return view;
    }
    public FragmentFeedback(Context context)
    {
        this.context=context;
    }
}
