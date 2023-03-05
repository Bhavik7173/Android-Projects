package com.gi.govardhaninquiry.retro;


import com.gi.govardhaninquiry.admin.model.BatchInfo;
import com.gi.govardhaninquiry.admin.model.CourseModel;
import com.gi.govardhaninquiry.admin.model.FileResponse;
import com.gi.govardhaninquiry.admin.model.InquiryData;
import com.gi.govardhaninquiry.admin.model.MyFacultyData;
import com.gi.govardhaninquiry.register.User;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface RetroInterface {

    @GET("/signup")
    Call<String> signup(@Query("name")String name,@Query("dob")  String dob, @Query("status")  String status,@Query("gen")  String gen,@Query("cn1")  String contact1,@Query("email")  String email,@Query("pass")  String pass);

    @GET("/login")
    Call<List<User>> login(@Query("email")String uemail, @Query("upass")String upass,@Query("umode") String umode);

    @GET("/addinquiry")
    Call<String> addInquiry(@Query("inquirydata") InquiryData inquiryData);

    @GET("/addinquiry")
    Call<String> addInquiry1(@Query("uid") String uid,@Query("name") String name, @Query("mobile") String mobile, @Query("email") String email, @Query("course") String course, @Query("college") String college, @Query("prefer") String preffered, @Query("note") String note, @Query("date") String date);

    @GET("/getinquiry")
    Call<ArrayList<InquiryData>> inquiryDetail();

    @GET("/getcourse")
    Call<ArrayList<CourseModel>> courseDetail();

    @GET("/delete_inquiry")
    Call<ArrayList<InquiryData>> deleteInquiry(@Query("name") String name,@Query("mobile") String mobile);


    @Multipart
    @POST("addcourse")
    Call<FileResponse> addcourse(@Part("course") CourseModel courseModel, @Part MultipartBody.Part spdfupload, @Part("spdffile_request") RequestBody spdfile_request, @Part MultipartBody.Part ppdfupload, @Part("ppdffile_request") RequestBody ppdfile_request,@Part MultipartBody.Part lpdfupload);

    @GET("/delete_course")
    Call<ArrayList<CourseModel>> deleteCourse(@Query("name")String cname);

    @GET("/getfaculty")
    Call<ArrayList<MyFacultyData>> facultyDetail();

    @Multipart
    @POST("addfaculty")
    Call<FileResponse> addFaculty(@Part("faculty")MyFacultyData faculty,@Part MultipartBody.Part qpdfupload,@Part MultipartBody.Part rpdfupload, @Part MultipartBody.Part apdfupload,@Part MultipartBody.Part ppdfupload);

    @Multipart
    @POST("uploadresume")
    Call<FileResponse> uploadResume(@Part MultipartBody.Part rpdfupload);

    @GET("/getallcourse")
    Call<ArrayList<CourseModel>> getAllCoursePhoto();

    @GET("/applybatch")
    Call<String> applybatch(@Query("uid") String userID, @Query("fid") String fname, @Query("cid") String cname, @Query("hour") String hour, @Query("time") String time, @Query("date") String admission_date, @Query("status") String status);

    @GET("/batchinfo")
    Call<ArrayList<BatchInfo>> batchdetail();
}

