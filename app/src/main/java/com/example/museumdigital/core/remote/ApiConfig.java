package com.example.museumdigital.core.remote;

import android.content.Context;
import android.content.SharedPreferences;

import com.chuckerteam.chucker.api.ChuckerCollector;
import com.chuckerteam.chucker.api.ChuckerInterceptor;
import com.example.museumdigital.core.remote.apiservice.ApiServiceAddBudaya;
import com.example.museumdigital.core.remote.apiservice.ApiServiceAddMakanan;
import com.example.museumdigital.core.remote.apiservice.ApiServiceBudaya;
import com.example.museumdigital.core.remote.apiservice.ApiServiceDetailBudaya;
import com.example.museumdigital.core.remote.apiservice.ApiServiceDetailMakanan;
import com.example.museumdigital.core.remote.apiservice.ApiServiceMakanan;
import com.example.museumdigital.core.remote.apiservice.AuthApi;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.Collections;

public class ApiConfig {

//    deploy
//    private static final String API_BASE_URL = "http://34.128.85.8/api/v1/";

    private static final String API_BASE_URL = "http://192.168.46.93:3000/api/v1/";


    private ApiConfig() {}

    public static <S> S getApiService(Context context, Class<S> serviceClass) {
        ChuckerInterceptor chuckerInterceptor = new ChuckerInterceptor.Builder(context)
                .collector(new ChuckerCollector(context))
                .maxContentLength(250000L)
                .redactHeaders(Collections.emptySet())
                .alwaysReadResponseBody(false)
                .build();

        SharedPreferences sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.addInterceptor(chuckerInterceptor);

        if (token != null) {
            clientBuilder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", "Bearer " + token);
                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
        }

        OkHttpClient client = clientBuilder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(serviceClass);
    }

    public static AuthApi getAuthApi(Context context) {
        return getApiService(context, AuthApi.class);
    }

    public static ApiServiceBudaya getApiServiceBudaya(Context context) {
        return getApiService(context, ApiServiceBudaya.class);
    }

    public static ApiServiceAddBudaya getApiServiceAddBudaya(Context context) {
        return getApiService(context, ApiServiceAddBudaya.class);
    }

    public static ApiServiceMakanan getApiServiceMakanan(Context context) {
        return getApiService(context, ApiServiceMakanan.class);
    }

    public static ApiServiceAddMakanan getApiServiceAddMakanan(Context context) {
        return getApiService(context, ApiServiceAddMakanan.class);
    }

    public static ApiServiceDetailMakanan getApiServiceDetailMakanan(Context context) {
        return getApiService(context, ApiServiceDetailMakanan.class);
    }

    public static ApiServiceDetailBudaya getApiServiceDetailBudaya(Context context) {
        return getApiService(context, ApiServiceDetailBudaya.class);
    }
}
