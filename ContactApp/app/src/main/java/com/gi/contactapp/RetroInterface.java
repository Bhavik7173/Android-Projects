package com.gi.contactapp;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface RetroInterface {

    @GET("/contact_insert")
    Call<String> createPost(@Query("eid") String eid, @Query("ename") String ename, @Query("econtact") String econtact, @Query("eemail") String eemail);

    @GET("/contact_display")
    Call<ArrayList<Contact>> disp_contact();

    @GET("/contact_delete")
    Call<String> deleteContact(@Query("contact") String econtact);

    @GET("/contact_update")
    Call<String> updateContact(@Query("eid") String eid,@Query("ename") String ename,@Query("econtact") String econtact,@Query("email") String eemail);

    @GET("/contact_search")
    Call<List<Contact>> searchContact(@Query("contact") String econtact);

    @Multipart
    @GET("/upload_image")
    Call<ImageFile> upload(@Part MultipartBody.Part file);
}
