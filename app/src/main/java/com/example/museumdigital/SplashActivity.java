package com.example.museumdigital;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.museumdigital.admin.DashboardActivity;
import com.example.museumdigital.admin.auth.view.SignInActivity;

public class SplashActivity extends AppCompatActivity {

    private static final int SPLASH_DISPLAY_LENGTH = 3000; // durasi splash screen dalam milidetik

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Menampilkan splash screen dengan durasi tertentu
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isUserLoggedIn()) {
                    // Jika user sudah login, langsung ke MainActivity
                    Intent mainIntent = new Intent(SplashActivity.this, DashboardActivity.class);
                    startActivity(mainIntent);
                } else {
                    // Jika user belum login, pergi ke LoginActivity
                    Intent loginIntent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(loginIntent);
                }
                finish(); // Mengakhiri SplashActivity agar tidak bisa kembali ke splash screen
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    // Method untuk memeriksa apakah token sudah disimpan (user sudah login)
    private boolean isUserLoggedIn() {
        SharedPreferences sharedPref = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        String token = sharedPref.getString("token", null);
        return token != null;
    }
}