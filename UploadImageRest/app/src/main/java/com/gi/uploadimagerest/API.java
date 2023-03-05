package com.gi.uploadimagerest;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Streaming;

public interface API {
    @GET("/getAllImage")
    Call<List<Image>> getImages();                                                                      // GET request to get all images

    @GET("/fileUpload")                                                                 // GET request to get an image by its name
    @Streaming
    Call<ResponseBody> getImageByName(@Path("filename") String name);

    @Multipart
    // POST request to upload an image from storage
    @GET("/fileUpload1")
    Call<ResponseBody> uploadImage(@Part MultipartBody.Part image);
}
