package com.gi.giquiz.StaticFunction;

import android.content.Context;
import android.util.Log;

import com.gi.giquiz.Network.Retro;
import com.gi.giquiz.Network.RetroInterface;
import com.gi.giquiz.Pojo.LinkPojo;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ErrorLogs {
    public static void insertLogs(Context context, String userId, String error) {
        Retro.getRetrofit(context).create(RetroInterface.class).insert_logs(userId, error).enqueue(new Callback<LinkPojo>() {
            @Override
            public void onResponse(Call<LinkPojo> call, Response<LinkPojo> response) {
                Log.d("error_log", response.toString());
            }

            @Override
            public void onFailure(Call<LinkPojo> call, Throwable t) {
                Log.e("error_log", t.toString());
            }
        });
    }
}
