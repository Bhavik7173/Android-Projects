package com.gi.projectgroup;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface RetroInterface {

    @GET("/signup")
    Call<String> signup(@Query("name") String name,@Query("dob") String dob,@Query("mob") String mob,@Query("email") String email,@Query("pass") String pass);

    @GET("/login")
    Call<List<User>> login(@Query("email") String email);
}
