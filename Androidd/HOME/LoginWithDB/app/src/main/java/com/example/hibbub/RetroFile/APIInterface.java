package com.example.hibbub.RetroFile;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {
    @GET("per_prof2.php")
    Call<String> submit();

    /*
    @GET("two.php")
    Call<String> second(@Query("t1")String x,@Query("t2") String y);

    @GET("third.php")
    Call<User> third();

    @GET("fourth.php")
    Call<List<User>> fourth();

    @POST("five.php")
    Call<String> fifth(@Body User user);
    */
}