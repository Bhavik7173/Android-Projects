package com.gi.restapi2;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitApi {

    @GET("user")
    Call<String> createPost(@Query("name") String name, @Query("job") String job);

    @GET("user1")
    Call<DataModel> createPost(@Body DataModel modal);
}
