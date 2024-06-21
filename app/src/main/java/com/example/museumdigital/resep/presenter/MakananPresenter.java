package com.example.museumdigital.resep.presenter;

import com.example.museumdigital.core.model.Makanan.MakananResponse;
import com.example.museumdigital.core.remote.ApiClient.ApiClient;
import com.example.museumdigital.core.contract.MainContract;
import com.example.museumdigital.core.model.BudayaResponse;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MakananPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private ApiClient apiClient;

    public MakananPresenter(MainContract.View view, ApiClient apiClient) {
        this.view = view;
        this.apiClient = apiClient;
    }

    @Override
    public void fetchMakananData() {
        apiClient.fetchMakananData(new Callback<MakananResponse>() {
            @Override
            public void onResponse(Call<MakananResponse> call, Response<MakananResponse> response) {
                if (response.isSuccessful()) {
                    view.showMakananData(response.body().getData());
                } else {
                    view.showError("Failed to fetch makanan data");
                }
            }

            @Override
            public void onFailure(Call<MakananResponse> call, Throwable t) {
                view.showError(t.getMessage());
            }
        });
    }
}

