package com.example.museumdigital.admin.resep.presenter;

import android.content.Context;

import com.example.museumdigital.admin.resep.model.Makanan;
import com.example.museumdigital.admin.resep.model.Resep;
import com.example.museumdigital.admin.resep.model.TambahMakananResponse;
import com.example.museumdigital.admin.resep.view.AddMakananView;
import com.example.museumdigital.core.remote.ApiConfig;
import com.example.museumdigital.core.remote.apiservice.ApiServiceAddMakanan;
import com.example.museumdigital.core.utils.UserDataStoreImpl;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMakananPresenter {

    private Context context;
    private UserDataStoreImpl userDataStoreImpl;
    private AddMakananView addMakananView;

    public AddMakananPresenter(Context context, UserDataStoreImpl userDataStoreImpl, AddMakananView addMakananView) {
        this.context = context;
        this.userDataStoreImpl = userDataStoreImpl;
        this.addMakananView = addMakananView;
    }

    public void addMakanan(Makanan makanan, Resep resep, File imageFile) {
        String token = userDataStoreImpl.getToken();
        if (token != null && !token.isEmpty()) {
            ApiServiceAddMakanan apiService = ApiConfig.getApiServiceAddMakanan(context);

            // Create request parts
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);
            MultipartBody.Part body = MultipartBody.Part.createFormData("media", imageFile.getName(), requestFile);

            RequestBody namaMakanan = RequestBody.create(MediaType.parse("multipart/form-data"), makanan.getNamaMakanan());
            RequestBody deskripsi = RequestBody.create(MediaType.parse("multipart/form-data"), makanan.getDeskripsi());
            RequestBody bahan = RequestBody.create(MediaType.parse("multipart/form-data"), resep.getBahan());
            RequestBody caraMemasak = RequestBody.create(MediaType.parse("multipart/form-data"), resep.getCaraMemasak());
            RequestBody penyajian = RequestBody.create(MediaType.parse("multipart/form-data"), resep.getPenyajian());
            RequestBody keunikan = RequestBody.create(MediaType.parse("multipart/form-data"), resep.getKeunikan());
            RequestBody lokasi = RequestBody.create(MediaType.parse("multipart/form-data"), makanan.getLokasi());

            // Call API to add Makanan
            Call<TambahMakananResponse> call = apiService.addMakanan("Bearer " + token, body, namaMakanan, deskripsi, bahan, caraMemasak, penyajian, keunikan, lokasi);
            call.enqueue(new Callback<TambahMakananResponse>() {
                @Override
                public void onResponse(Call<TambahMakananResponse> call, Response<TambahMakananResponse> response) {
                    if (response.isSuccessful()) {
                        TambahMakananResponse tambahMakananResponse = response.body();
                        Makanan makananResponse = tambahMakananResponse.getMakanan();
                        Resep resepResponse = tambahMakananResponse.getResep();

                        // Update UI on successful addition
                        addMakananView.showAddMakananSuccessMessage(response.body().getMessage(), makananResponse);
                    } else {
                        // Handle unsuccessful response
                        addMakananView.showAddMakananErrorMessage(response.message());
                    }
                }

                @Override
                public void onFailure(Call<TambahMakananResponse> call, Throwable t) {
                    // Handle failure
                    addMakananView.showAddMakananErrorMessage(t.getMessage());
                }
            });
        } else {
            // Handle case where user is not authenticated
            addMakananView.showAddMakananErrorMessage("User is not authenticated");
        }
    }
}
