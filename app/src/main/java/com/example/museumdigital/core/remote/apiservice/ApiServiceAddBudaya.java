package com.example.museumdigital.core.remote.apiservice;

import com.example.museumdigital.admin.budaya.model.AddBudayaResponse;
import com.example.museumdigital.admin.budaya.model.Budaya;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiServiceAddBudaya {

    @Multipart
    @POST("admin/add-budaya")
    Call<AddBudayaResponse> addBudaya(
            @Header("Authorization") String token,
            @Part MultipartBody.Part image,
            @Part("nama_budaya") RequestBody namaBudaya,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("kategori_id") RequestBody kategoriId,
            @Part("lokasi") RequestBody lokasi
    );
}
