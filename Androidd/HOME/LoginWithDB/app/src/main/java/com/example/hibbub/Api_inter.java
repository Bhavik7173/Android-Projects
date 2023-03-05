package com.example.hibbub;

import com.example.hibbub.Home.HomeResponse;
import com.example.hibbub.SignUp.SignUpActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface Api_inter {
    @POST("signup1.php")
    Call<SignUpActivity> signup(@Field("name") String name, @Field("DOB") String DOB, @Field("Img") String Img,@Field("Gen") String gender,@Field("CN1") String cn1,@Field("CN2") String cn2,@Field("Status") String status,@Field("Address") String add,@Field("Hobby") String hob,@Field("Interest") String inte, @Field("email") String Email,@Field("password") String pass, @Field("image") String image);


    @POST("getHomeData.php")
    Call<List<HomeResponse>> getHomeData();

}
