package com.example.museumdigital.core.remote;

import android.content.Context;

import com.chuckerteam.chucker.api.ChuckerCollector;
import com.chuckerteam.chucker.api.ChuckerInterceptor;
import com.example.museumdigital.core.model.Token.Token;
import com.example.museumdigital.core.model.Token.TokenResponse;
import com.example.museumdigital.core.remote.apiservice.ApiServiceBudaya;
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

import io.realm.Realm;

public class ApiConfig {

    private static final String API_BASE_URL = "http://34.128.85.8/api/v1/";
//    private static String accessToken = "";
//    private static String refreshToken = "";

    // Private constructor to prevent instantiation
    private ApiConfig() {}

    public static <S> S getApiService(Context context, Class<S> serviceClass) {
        // Inisialisasi Realm (tidak perlu karena sudah diinisialisasi di MyApplication)
        // Realm.init(context);

        // Retrieve token from Realm
//        Realm realm = Realm.getDefaultInstance();
//        Token token = realm.where(Token.class).findFirst();
//        if (token != null) {
//            accessToken = token.getAccessToken();
//            refreshToken = token.getRefreshToken();
//        }

        // Create a ChuckerInterceptor for debugging network requests
        ChuckerInterceptor chuckerInterceptor = new ChuckerInterceptor.Builder(context)
                .collector(new ChuckerCollector(context))
                .maxContentLength(250000L)
                .redactHeaders(Collections.emptySet())
                .alwaysReadResponseBody(false)
                .build();

        // Define an Interceptor to add the Authorization header to each request
//        Interceptor authInterceptor = new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request request = chain.request();
//                Request requestWithHeaders = request.newBuilder()
//                        .addHeader("Authorization", "Bearer " + accessToken)
//                        .build();
//                return chain.proceed(requestWithHeaders);
//            }
//        };

        // Define TokenRefreshInterceptor to handle token refresh
//        Interceptor refreshTokenInterceptor = new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Response response = chain.proceed(chain.request());
//                if (response.code() == 401) {
//                    // Token expired, refresh token
//                    synchronized (ApiConfig.class) {
//                        // Make synchronous request to refresh token
//                        Retrofit retrofit = new Retrofit.Builder()
//                                .baseUrl(API_BASE_URL)
//                                .addConverterFactory(GsonConverterFactory.create())
//                                .build();
//
//                        AuthApi authApi = retrofit.create(AuthApi.class);
//                        retrofit2.Response<TokenResponse> tokenResponse = authApi.refreshToken(refreshToken).execute();
//
//                        if (tokenResponse.isSuccessful() && tokenResponse.body() != null) {
//                            accessToken = tokenResponse.body().getAccessToken();
//                            refreshToken = tokenResponse.body().getRefreshToken();
//
//                            // Save the new tokens to Realm
//                            realm.executeTransaction(realmInstance -> {
//                                token.setAccessToken(accessToken);
//                                token.setRefreshToken(refreshToken);
//                            });
//
//                            // Retry the original request with the new token
//                            Request newRequest = chain.request().newBuilder()
//                                    .header("Authorization", "Bearer " + accessToken)
//                                    .build();
//                            response = chain.proceed(newRequest);
//                        }
//                    }
//                }
//                return response;
//            }
//        };

        // Build the OkHttpClient with the interceptors
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(chuckerInterceptor)
                .build();

        // Build the Retrofit instance with the OkHttpClient
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Return the requested API service
        return retrofit.create(serviceClass);
    }

    public static ApiServiceBudaya getApiServiceBudaya(Context context) {
        return getApiService(context, ApiServiceBudaya.class);
    }

    public static ApiServiceMakanan getApiServiceMakanan(Context context) {
        return getApiService(context, ApiServiceMakanan.class);
    }
}
