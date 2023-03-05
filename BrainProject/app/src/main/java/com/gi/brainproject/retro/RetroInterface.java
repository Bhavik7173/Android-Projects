package com.gi.brainproject.retro;

import com.gi.brainproject.model.User;
import com.gi.brainproject.model.FileResponse;
import com.gi.brainproject.model.PatientData;
import com.gi.brainproject.model.PatientResponse;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface RetroInterface {

    @GET("/signup")
    Call<String> signup(@Query("name") String name,@Query("dob") String dob,@Query("mob") String mob,@Query("email") String email,@Query("pass") String pass);

    @GET("/login")
    Call<List<User>> login(@Query("email") String email, @Query("pass") String pass);

    @Multipart
    @POST("upload")
    Call<FileResponse> upload(@Part("userdata") PatientData patientdetail, @Part MultipartBody.Part image);

    @GET("/checkemail")
    Call<FileResponse> checkEmail(@Query("uemail") String uemail);

    @GET("/forgotpassword")
    Call<String> forgotpassword(@Query("email") String str,@Query("newpass") String newpass,@Query("newcpass") String newcpass);

    @GET("/personal_detail")
    Call<List<User>> getPersonalDetail(@Query("email") String email);

    @GET("/update_detail")
    Call<FileResponse> updateDetail(@Query("name") String name,@Query("dob") String dob,@Query("mob") String mob,@Query("email") String email,@Query("pass") String pass);

    @GET("/feedback")
    Call<String> feedback(@Query("email") String email,@Query("subject") String sub,@Query("yf") String your_fb);

//    @Multipart
    @FormUrlEncoded
    @POST("addpatient")
    Call<FileResponse> addPatient(@Field("patient") PatientData patientdetail);

    @GET("getpatient")
    Call<ArrayList<PatientResponse>> getPatientDetail(@Query("id")String id);

    @GET("contactus/")
    Call<String> contactus(@Query("uid")String uid,@Query("uname") String uname,@Query("umob") String umob,@Query("utime") String utime,@Query("upro") String upro);
}
