package com.example.wallet.Service;

import android.graphics.Bitmap;

import com.example.wallet.PaymentResponse;
import com.example.wallet.Response.LoginResponse;
import com.example.wallet.Response.SignupResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface Api {
    @FormUrlEncoded
    @POST("fcm_key.php")
    Call<String> insertKey(@Field("token") String token);

    @FormUrlEncoded
    @POST("paymentsend.php")
    Call<PaymentResponse> paymentsend(@Field("cmob") String cmobileno,@Field("mobile") String mobile, @Field("amount") String amount);

    @FormUrlEncoded
    @POST("userlogin.php")
    Call<LoginResponse> userLogin(@Field("mobile") String mobile,@Field("password") String pass);

    @FormUrlEncoded
    @POST("signinfo.php")
    //Call<SignupResponse> userinfo(@Field("name") String name, @Field("mobile") String cmobileno, @Field("password") String password, @Field("adharcard") String adharCard, @Field("pancard") String pancard);
    Call<SignupResponse> userinfo(@Field("name") String name, @Field("mobile") String cmobileno, @Field("password") String password, @Field("adharcard") String adharCard, @Field("pancard") String pancard,@Field("qrcode") Bitmap bitmap);
}
