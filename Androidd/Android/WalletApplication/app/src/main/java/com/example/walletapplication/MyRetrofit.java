package com.example.walletapplication;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class MyRetrofit
{
    public static Retrofit retrofit=null;


    public static Retrofit getRetrofit(String baseURL)
    {
        if(retrofit==null)
        {
                Retrofit.Builder builder =new Retrofit.Builder();
                builder.baseUrl(baseURL);
                builder.addConverterFactory(GsonConverterFactory.create());
                retrofit=builder.build();
        }
        return retrofit;
    }
}
