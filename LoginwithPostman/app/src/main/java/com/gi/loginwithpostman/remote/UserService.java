package com.gi.loginwithpostman.remote;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserService {

    @GET("login/{username}/{password}")
    Call login(@Path("username") String username, @Path("password") String password);
}