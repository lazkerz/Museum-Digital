package com.example.museumdigital.core.remote.apiservice;

import com.example.museumdigital.core.model.Makanan.MakananResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface ApiServiceMakanan {

    @GET("makanan")
    Call<MakananResponse> getMakanan();
}
