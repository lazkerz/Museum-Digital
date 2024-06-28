package com.example.museumdigital.core.remote.ApiClient;

import android.content.Context;

import com.example.museumdigital.budaya.model.DetailBudayaResponse;
import com.example.museumdigital.core.model.Budaya.BudayaResponse;
import com.example.museumdigital.core.model.Makanan.MakananResponse;
import com.example.museumdigital.core.remote.ApiConfig;
import com.example.museumdigital.core.remote.apiservice.ApiServiceBudaya;
import com.example.museumdigital.core.remote.apiservice.ApiServiceDetailBudaya;
import com.example.museumdigital.core.remote.apiservice.ApiServiceDetailMakanan;
import com.example.museumdigital.core.remote.apiservice.ApiServiceMakanan;
import com.example.museumdigital.resep.model.DetailMakananResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiClient {
    private ApiServiceMakanan apiServiceMakanan;
    private ApiServiceBudaya apiServiceBudaya;

    private ApiServiceDetailMakanan apiServiceDetailMakanan;

    private ApiServiceDetailBudaya apiServiceDetailBudaya;

    public ApiClient(Context context) {
        apiServiceMakanan = ApiConfig.getApiServiceMakanan(context);
        apiServiceDetailMakanan = ApiConfig.getApiServiceDetailMakanan(context);
        apiServiceBudaya = ApiConfig.getApiServiceBudaya(context);
        apiServiceDetailBudaya = ApiConfig.getApiServiceDetailBudaya(context);
    }

    public void fetchMakananData(Callback<MakananResponse> callback) {
        Call<MakananResponse> call = apiServiceMakanan.getMakanan();
        call.enqueue(callback);
    }

    public void fetchMakananDetailData(int makananId, Callback<DetailMakananResponse> callback) {
        Call<DetailMakananResponse> call = apiServiceDetailMakanan.getDetailMakanan(makananId);
        call.enqueue(callback);
    }

    public void fetchBudayaData(String kategori, Callback<BudayaResponse> callback) {
        Call<BudayaResponse> call = apiServiceBudaya.getBudaya(kategori);
        call.enqueue(callback);
    }

    public void fetchBudayaDetailData(int budayaId, Callback<DetailBudayaResponse> callback) {
        Call<DetailBudayaResponse> call = apiServiceDetailBudaya.getDetailBudaya(budayaId);
        call.enqueue(callback);
    }
}
