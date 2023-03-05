package com.gi.loginwithpostman.remote;

public class ApiUtils {

    public static final String BASE_URL = "http://192.168.181.195/demo/";

    public static UserService getUserService(){
        return RetrofitClient.getClient(BASE_URL).create(UserService.class);
    }
}
