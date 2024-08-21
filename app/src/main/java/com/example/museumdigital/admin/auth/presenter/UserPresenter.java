package com.example.museumdigital.admin.auth.presenter;

import android.content.Context;
import android.util.Log;

import com.example.museumdigital.admin.auth.view.UserView;
import com.example.museumdigital.core.model.Login.LoginResponse;
import com.example.museumdigital.core.remote.ApiConfig;
import com.example.museumdigital.core.remote.apiservice.AuthApi;
import com.example.museumdigital.core.utils.UserDataStoreImpl;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserPresenter {
    private Context context;
    private UserDataStoreImpl userDataStoreImpl;
    private UserView userView;

    public UserPresenter(Context context, UserDataStoreImpl userDataStoreImpl, UserView userView) {
        this.context = context;
        this.userDataStoreImpl = userDataStoreImpl;
        this.userView = userView;
    }

    public void login(String username, String password) {
        AuthApi authApi = ApiConfig.getAuthApi(context);
        Call<LoginResponse> call = authApi.login(username, password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse loginResponse = response.body();
                    LoginResponse.User user = loginResponse.getUser();
                    if (user != null) {
                        String token = user.getToken();
                        String refreshToken = user.getRefreshToken();
                        if (token != null && refreshToken != null && !token.isEmpty() && !refreshToken.isEmpty()) {
                            userDataStoreImpl.saveToken(token, refreshToken);
                            Log.d("UserPresenter", "Token saved: " + token);
                            userView.showLoginSuccessMessage();
                        } else {
                            Log.e("UserPresenter", "Token or refreshToken is null or empty");
                            userView.showLoginErrorMessage("Token or refreshToken is null or empty");
                        }
                    } else {
                        Log.e("UserPresenter", "User object in response is null");
                        userView.showLoginErrorMessage("User object in response is null");
                    }
                } else {
                    Log.e("UserPresenter", "Login failed with message: " + response.message());
                    userView.showLoginErrorMessage("Login failed with message: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("UserPresenter", "Failed to execute login request: " + t.getMessage(), t);
                userView.showLoginErrorMessage("Failed to execute login request: " + t.getMessage());
            }
        });
    }
    public void signOut() {
        String token = userDataStoreImpl.getToken();

        if (token != null) {
            AuthApi authApi = ApiConfig.getAuthApi(context);
            Call<Void> call = authApi.signOut("Bearer " + token);

            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        userDataStoreImpl.clearToken();
                        userView.showLoginSuccessMessage();
                    } else {
                        String errorMessage = "Sign out failed";
                        if (response.errorBody() != null) {
                            try {
                                errorMessage = response.errorBody().string();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        Log.e("UserPresenter", "Sign out failed with message: " + errorMessage);
                        userView.showLoginErrorMessage(errorMessage);
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.e("UserPresenter", "Failed to execute sign out request: " + t.getMessage(), t);
                    userView.showLoginErrorMessage("Failed to execute sign out request: " + t.getMessage());
                }
            });
        } else {
            userView.showLoginErrorMessage("No token found for sign out");
        }
    }
}
