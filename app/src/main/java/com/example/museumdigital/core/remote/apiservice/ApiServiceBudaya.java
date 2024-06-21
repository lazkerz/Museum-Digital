package com.example.museumdigital.core.remote.apiservice;

import com.example.museumdigital.core.model.BudayaResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServiceBudaya {
    @GET("api/v1/")
    Call<List<BudayaResponse>> getBudaya
            (@Query("budaya") String budaya);
}
