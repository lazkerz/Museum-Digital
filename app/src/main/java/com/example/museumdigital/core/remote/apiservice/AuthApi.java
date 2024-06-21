package com.example.museumdigital.core.remote.apiservice;

import com.example.museumdigital.core.model.Token.TokenResponse;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;

public interface AuthApi {
    @FormUrlEncoded
    @POST("auth/refresh")
    Call<TokenResponse> refreshToken(@Field("refresh_token") String refreshToken);
}
