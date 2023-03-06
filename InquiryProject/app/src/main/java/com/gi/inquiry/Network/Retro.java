package com.gi.inquiry.Network;

import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retro {

    public static Retrofit retrofit = null;

    public static final String BASE_URL = "http://192.168.99.195/Inquiry_app/";

    public static Retrofit getRetrofit() {
        if (retrofit == null) {

            //Defining the Retrofit using Builder
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)   //This is the only mandatory call on Builder object.
                    .addConverterFactory(GsonConverterFactory.create()) // Convertor library used to convert response into POJO
                    .build();
        }
        System.out.println(retrofit);
        Log.d("mylog", "retrofit error");
        return retrofit;
    }
}
