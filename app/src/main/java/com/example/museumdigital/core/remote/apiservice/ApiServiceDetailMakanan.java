package com.example.museumdigital.core.remote.apiservice;

import com.example.museumdigital.core.model.Makanan.MakananResponse;
import com.example.museumdigital.resep.model.DetailMakananResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiServiceDetailMakanan {
    @GET("detail-makanan/{id}")
    Call<DetailMakananResponse> getDetailMakanan(@Path("id") int id);
}
