package com.example.museumdigital.core.remote.ApiClient;


import android.content.Context;

import com.example.museumdigital.core.model.Makanan.MakananResponse;
import com.example.museumdigital.core.remote.ApiConfig;
import com.example.museumdigital.core.remote.apiservice.ApiServiceBudaya;
import com.example.museumdigital.core.remote.apiservice.ApiServiceMakanan;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiClient {
    private ApiServiceMakanan apiServiceMakanan;
    private ApiServiceBudaya apiServiceBudaya;

    public ApiClient(Context context) {
        apiServiceMakanan = ApiConfig.getApiServiceMakanan(context);
        apiServiceBudaya = ApiConfig.getApiServiceBudaya(context);
    }

    public void fetchMakananData(Callback<MakananResponse> callback) {
        Call<MakananResponse> call = apiServiceMakanan.getMakanan();
        call.enqueue(new Callback<MakananResponse>() {
            @Override
            public void onResponse(Call<MakananResponse> call, Response<MakananResponse> response) {
                if (response.isSuccessful()) {
                    callback.onResponse(call, response);
                } else {
                    callback.onFailure(call, new Throwable("Failed to fetch makanan data"));
                }
            }

            @Override
            public void onFailure(Call<MakananResponse> call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }
}

