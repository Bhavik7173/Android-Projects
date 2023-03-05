package com.gi.programing_quiz.Network;

import android.content.Context;
import android.util.JsonReader;

import com.gi.programing_quiz.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.StringReader;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retro {
    public static Retrofit r = null;

    public static Retrofit getRetrofit(Context context) {
        if (r == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(30, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .build();

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
//            JsonReader reader = new JsonReader(new StringReader(result1));
//            reader.setLenient(true);
//            Userinfo userinfo1 = gson.fromJson(reader, Userinfo.class);

            Retrofit.Builder builder = new Retrofit.Builder();
            builder.baseUrl(context.getString(R.string.IP));
            builder.addConverterFactory(GsonConverterFactory.create(gson));
            builder.client(okHttpClient);
            r = builder.build();
        }
        return r;
    }
}
