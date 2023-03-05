package com.gi.restapi2;

import android.content.Context;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(Context context) {


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.29.85:9000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIClient retrofitAPI = (APIClient) retrofit.create(RetrofitApi.class);



        return retrofit;
    }
}
