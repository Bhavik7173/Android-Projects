package com.gi.demoapp1;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {


    @POST("users")
    Call<DataModel> createPost(@Body DataModel dataModal);
    @FormUrlEncoded
    @POST("users1")
    Call<String> createPost1(@Field("name") String name,@Field("job") String job);

    @GET("users1")
    Call<String> createPost2(@Query("name") String name, @Query("job") String job);

}
