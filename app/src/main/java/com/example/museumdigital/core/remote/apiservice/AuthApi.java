package com.example.museumdigital.core.remote.apiservice;

import com.example.museumdigital.core.model.Login.LoginResponse;
import com.example.museumdigital.core.model.Login.RequestLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.FormUrlEncoded;

public interface AuthApi {
    @FormUrlEncoded
    @POST("auth/login-admin")
    Call<LoginResponse> login(@Field("username") String username, @Field("password") String password);

    @POST("auth/logout")
    Call<Void> signOut(@Header("Authorization") String token);
}


