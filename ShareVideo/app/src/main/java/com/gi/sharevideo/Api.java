package com.gi.sharevideo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {
    @POST("email_send.php")
    Call<OTP> matchOTP(@Query("email") String Email);

    @POST("forgotPassword.php")
    Call<ForgotPassword> forgotPassword(@Query("email") String Email, @Query("password") String Password);

    @FormUrlEncoded
    @POST("signupResponse.php")
    Call<SignupResponse> check(@Field("email") String Email);

    @FormUrlEncoded
    @POST("signupverify.php")
    Call<OTP> signupverify(@Field("email") String email);

    @POST("login.php")
    Call <LoginResponse> userLogin(@Field("email") String Email, @Field("password") String password);

}
