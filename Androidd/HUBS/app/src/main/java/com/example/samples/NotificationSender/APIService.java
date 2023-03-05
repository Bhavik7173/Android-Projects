package com.example.samples.NotificationSender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    //to send notification
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAAzeIYEPY:APA91bELAlbOk2pI8lsaBtRzaBjBJ3ByJej-V7b0aRn6Ff5NrK5fjXWq0bsdflCFmv7TApAs2rRHkv-YHVVTKoKA4R10uYP1ilz00QR-0wYDdGbxzPKIzof0EykwxsRIuHn0pBqoOI-g"// Your server key refer to video for finding your server key
                //    "Authorization:key=AAAAzeIYEPY:APA91bELAlbOk2pI8lsaBtRzaBjBJ3ByJej-V7b0aRn6Ff5NrK5fjXWq0bsdflCFmv7TApAs2rRHkv-YHVVTKoKA4R10uYP1ilz00QR-0wYDdGbxzPKIzof0EykwxsRIuHn0pBqoOI-g"
            }
    )
    @POST("fcm/send")
    Call<MyResponse> sendNotifcation(@Body NotificationSender body);

    //to read token from database
    @Headers("Content-Type: application/json")
    @GET("get_key.php")
    Call<String> getKey();
}

