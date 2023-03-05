package com.gi.ginquiry.Retro;

import com.gi.ginquiry.FileResponse;
import com.gi.ginquiry.register.PersonalProfile;
import com.gi.ginquiry.register.User;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetroInterface {

    @GET("/signup")
    Call<String> signup(@Query("name") String name, @Query("status")  String status, @Query("email")  String email,@Query("password")  String password);

    @FormUrlEncoded
    @POST("/personal_profile1")
    Call<String> personal_profile1(@Field("personal_profile") PersonalProfile personalProfile);

    @GET("/forgot_password")
    Call<String> forgotpassword(@Query("email") String uemail,@Query("password") String upass);

    @GET("/login")
    Call<List<User>> login(@Query("email") String uemail, @Query("password") String upass, @Query("mode") String umode);

    @FormUrlEncoded
    @POST("/personal_profile")
    Call<String> personal_profile(@Field("uid") String uid,@Field("dob") String dob, @Field("image") String image,@Field("gen") String gen,@Field("cn1") String cn1,@Field("cn2") String cn2,@Field("address") String address, @Field("hobby") String hobby);

    Call<FileResponse> upload(MultipartBody.Part partImage);


//    @GET("/signup")
//    Call<String> signup(@Query("name") String name,@Query("dob")  String dob,@Query("image")  String image,@Query("gen")  String gen,@Query("cn1")  String cn1,@Query("cn2")  String cn2,@Query("status")  String status,@Query("address") String address,@Query("hobby")  String hobby,@Query("email")  String email,@Query("password")  String password);
}
