package com.example.museumdigital.core.remote.apiservice;

import com.example.museumdigital.core.model.Budaya.BudayaResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServiceBudaya {
    @GET("budaya-category")
    Call<BudayaResponse> getBudaya(@Query("kategori") String kategori);
}