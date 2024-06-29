package com.example.museumdigital.core.remote.apiservice;

import com.example.museumdigital.admin.budaya.model.AddBudayaResponse;
import com.example.museumdigital.admin.resep.model.TambahMakananResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiServiceAddMakanan {

    @Multipart
    @POST("admin/add-makanan")
    Call<TambahMakananResponse> addMakanan(
            @Header("Authorization") String token,
            @Part MultipartBody.Part image,
            @Part("nama_makanan") RequestBody namaMakanan,
            @Part("deskripsi") RequestBody deskripsi,
            @Part("bahan") RequestBody bahan,
            @Part("cara_memasak") RequestBody caraMemasak,
            @Part("penyajian") RequestBody penyajian,
            @Part("keunikan") RequestBody keunikan,
            @Part("lokasi") RequestBody lokasi
    );
}
