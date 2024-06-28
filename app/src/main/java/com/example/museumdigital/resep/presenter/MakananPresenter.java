package com.example.museumdigital.resep.presenter;

import com.example.museumdigital.core.MainView;
import com.example.museumdigital.core.model.Makanan.MakananResponse;
import com.example.museumdigital.core.remote.ApiClient.ApiClient;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MakananPresenter {

    private MainView view;
    private ApiClient apiClient;

    public MakananPresenter(MainView view, ApiClient apiClient) {
        this.view = view;
        this.apiClient = apiClient;
    }

    public void fetchMakananData() {
        apiClient.fetchMakananData(new Callback<MakananResponse>() {
            @Override
            public void onResponse(Call<MakananResponse> call, Response<MakananResponse> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        view.showMakananData(response.body().getData());
                    } else {
                        view.showError("Empty response body");
                    }
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
