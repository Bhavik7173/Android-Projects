package com.gi.imageuploading.model;


public class ApiUtils {
    public ApiUtils()
    {}
    public static final String API_URl = "http://192.168.29.86:9000";

    public static FileService getFileService()
    {
        return RetrofitClient.getRetrofit(API_URl).create(FileService.class);
    }
}
