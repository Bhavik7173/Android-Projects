package com.gi.retrofituploads1;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

public interface ApiConfig {
    static String BASE_URL="http://192.168.29.85:9000";
//    @Multipart
//    @GET("/images")
//    Call<ServerResponse> uploadImage(String s, @Part MultipartBody.Part image);

    @Multipart
    @GET("/images")
    Call<Object> uploadImage(@Url String url, @Part MultipartBody.Part image);
}