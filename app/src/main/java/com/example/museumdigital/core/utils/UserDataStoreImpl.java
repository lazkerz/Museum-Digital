package com.example.museumdigital.core.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class UserDataStoreImpl {

    private static final String PREFS_NAME = "user_prefs";
    private SharedPreferences sharedPreferences;

    public UserDataStoreImpl(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public void saveToken(String token, String refreshToken) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", token);
        editor.putString("refreshToken", refreshToken);
        editor.apply();
        Log.d("UserDataStoreImpl", "Token saved: " + token);
    }

    public String getToken() {
        return sharedPreferences.getString("token", null);
    }

    public String getRefreshToken() {
        return sharedPreferences.getString("refreshToken", null);
    }

    public void clearToken() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("token");
        editor.remove("refreshToken");
        editor.apply();
        Log.d("UserDataStoreImpl", "Token cleared");
    }
}
