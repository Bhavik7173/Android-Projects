package com.example.sample;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface PDFInterface {

    String IMAGEURL = "http://192.168.43.171/PDF/";
    @Multipart
    @POST("uploadfile.php")
    Call<String> uploadImage(
            @Part MultipartBody.Part file, @Part("filename") RequestBody name
    );

}
