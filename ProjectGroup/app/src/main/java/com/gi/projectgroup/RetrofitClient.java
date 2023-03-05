package com.gi.projectgroup;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(Context context) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.4.222:9000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
//        RetrofitClient retrofitAPI = (RetrofitClient) retrofit.create(RetroInterface.class);

        return retrofit;
    }
}
