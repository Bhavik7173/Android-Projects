package com.gi.govardhaninquiry.retro;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit = null;
    public  static String IpAddress = "http://192.168.26.222:9000";
    public static Retrofit getClient(Context context) {


        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://192.168.29.85:9000")
                .baseUrl(IpAddress)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }
}
