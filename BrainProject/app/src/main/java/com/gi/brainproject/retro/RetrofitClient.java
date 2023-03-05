package com.gi.brainproject.retro;

import android.content.Context;

import com.gi.brainproject.activity.ValidIPAddress;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(Context context) {


        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://192.168.238.222:9000")
//                .baseUrl("http://192.168.29.85:9000")
                .baseUrl(ValidIPAddress.ipaddress)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
//        RetrofitClient retrofitAPI = (RetrofitClient) retrofit.create(RetroInterface.class);

        return retrofit;
    }
}
