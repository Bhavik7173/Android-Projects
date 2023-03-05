package com.gi.restapiapp;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitApi {
//    @FormUrlEncoded
//    @POST("/user/")
//    Call<DataModel> createPost(@Body DataModel dataModal);

    @GET("/user")
    Call<String> createPost(@Query("name") String name, @Query("job") String job);

}
