package com.example.museumdigital.budaya.presenter;

import com.example.museumdigital.core.MainView;
import com.example.museumdigital.core.model.Budaya.BudayaResponse;
import com.example.museumdigital.core.remote.ApiClient.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BudayaPresenter {

    private MainView view;
    private ApiClient apiClient;

    public BudayaPresenter(MainView view, ApiClient apiClient) {
        this.view = view;
        this.apiClient = apiClient;
    }

    public void fetchBudayaData(String kategori) {
        apiClient.fetchBudayaData(kategori, new Callback<BudayaResponse>() {
            @Override
            public void onResponse(Call<BudayaResponse> call, Response<BudayaResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        view.showBudayaData(response.body().getData());
                    } else {
                        view.showError("Empty response body");
                    }
                } else {
                    view.showError("Failed to fetch budaya data");
                }
            }

            @Override
            public void onFailure(Call<BudayaResponse> call, Throwable t) {
                view.showError(t.getMessage());
            }
        });
    }
}
