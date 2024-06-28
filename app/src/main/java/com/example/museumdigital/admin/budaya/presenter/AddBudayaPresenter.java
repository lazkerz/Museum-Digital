package com.example.museumdigital.admin.budaya.presenter;

import android.content.Context;

import com.example.museumdigital.admin.budaya.model.AddBudayaResponse;
import com.example.museumdigital.admin.budaya.model.Budaya;
import com.example.museumdigital.admin.budaya.model.Data;
import com.example.museumdigital.admin.budaya.view.AddBudayaView;
import com.example.museumdigital.core.remote.ApiConfig;
import com.example.museumdigital.core.remote.apiservice.ApiServiceAddBudaya;
import com.example.museumdigital.core.utils.UserDataStoreImpl;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.io.File;

public class AddBudayaPresenter {

    private Context context;
    private UserDataStoreImpl userDataStoreImpl;
    private AddBudayaView addBudayaView;

    public AddBudayaPresenter(Context context, UserDataStoreImpl userDataStoreImpl, AddBudayaView addBudayaView) {
        this.context = context;
        this.userDataStoreImpl = userDataStoreImpl;
        this.addBudayaView = addBudayaView;
    }

    public void addBudaya(Budaya budaya, File imageFile) {
        String token = userDataStoreImpl.getToken();
        if (token != null && !token.isEmpty()) {
            ApiServiceAddBudaya authApi = ApiConfig.getApiServiceAddBudaya(context);

            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);
            MultipartBody.Part body = MultipartBody.Part.createFormData("media", imageFile.getName(), requestFile);

            RequestBody namaBudaya = RequestBody.create(MediaType.parse("multipart/form-data"), budaya.getNamaBudaya());
            RequestBody deskripsi = RequestBody.create(MediaType.parse("multipart/form-data"), budaya.getDeskripsi());
            RequestBody kategoriId = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(budaya.getKategoriId()));
            RequestBody lokasi = RequestBody.create(MediaType.parse("multipart/form-data"), budaya.getLokasi());

            Call<AddBudayaResponse> call = authApi.addBudaya("Bearer " + token, body, namaBudaya, deskripsi, kategoriId, lokasi);

            call.enqueue(new Callback<AddBudayaResponse>() {
                @Override
                public void onResponse(Call<AddBudayaResponse> call, Response<AddBudayaResponse> response) {
                    if (response.isSuccessful()) {
                        Data data = response.body().getData();
                        Budaya budayaData = new Budaya();
                        budayaData.setId(data.getId());
                        budayaData.setNamaBudaya(data.getNamaBudaya());
                        budayaData.setDeskripsi(data.getDeskripsi());
                        budayaData.setKategoriId(data.getKategoriId());
                        budayaData.setLokasi(data.getLokasi());
                        budayaData.setMedia(data.getMedia());
                        budayaData.setUpdatedAt(data.getUpdatedAt());
                        budayaData.setCreatedAt(data.getCreatedAt());
                        addBudayaView.showAddBudayaSuccessMessage(response.body().getMessage(), budayaData);
                    } else {
                        addBudayaView.showAddBudayaErrorMessage(response.message());
                    }
                }

                @Override
                public void onFailure(Call<AddBudayaResponse> call, Throwable t) {
                    addBudayaView.showAddBudayaErrorMessage(t.getMessage());
                }
            });
        } else {
            addBudayaView.showAddBudayaErrorMessage("User is not authenticated");
        }
    }
}
