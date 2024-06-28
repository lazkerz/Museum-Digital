package com.example.museumdigital.core.remote.apiservice;

import com.example.museumdigital.budaya.model.DetailBudayaResponse;
import com.example.museumdigital.resep.model.DetailMakananResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiServiceDetailBudaya {
    @GET("detail-budaya/{id}")
    Call<DetailBudayaResponse> getDetailBudaya(@Path("id") int id);
}
