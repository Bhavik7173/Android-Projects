package com.gi.inquiry.Network;


import com.gi.inquiry.pojo.UserPojo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetroInterface {


//    @FormUrlEncoded
//    @POST("login.php")
//    Call<UserPojo> login1(@Query("user_name") String user_name,@Query("user_password") String user_password);

//    @FormUrlEncoded
//    @POST("inquiry/login.php")
//    Call<String> login(@Query("user_name") String user_name,@Query("user_password") String user_password);


    @Headers("Content-Type: application/json")
    @POST("insertKey.php")
    Call<String> insertKey(@Query("user_fcm") String user_fcm);


    @GET("login.php")
    Call<UserPojo> login(@Query("uname") String user_name, @Query("upass") String user_password, @Query("ustatus") String user_status);

    @GET("forgotpassword.php")
    Call<String> forgotPassword(@Query("uname") String user_name, @Query("unpass") String user_new_pass);
}
