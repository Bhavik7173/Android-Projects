package com.example.hibbub.sendnotificationPack;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface APIService {

    //@Headers("Content-Type: application/json")
    @POST("notification/insert_key.php")
    Call<String>insertKey(@Query("key") String key);

    @Headers("Content-Type: application/json")
    @GET("notification/events.php")
    Call<String> insert_event(@Query("t1") String t1, @Query("t2") String t2 , @Query("t3") String t3 , @Query("t4") String t4);
}