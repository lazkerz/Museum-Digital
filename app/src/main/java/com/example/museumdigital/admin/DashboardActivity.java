package com.example.museumdigital.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.museumdigital.MainActivity;
import com.example.museumdigital.R;
import com.example.museumdigital.admin.auth.presenter.UserPresenter;
import com.example.museumdigital.admin.auth.view.UserView;
import com.example.museumdigital.admin.budaya.view.TambahBudayaActivity;
import com.example.museumdigital.admin.resep.TambahResepActivity;
import com.example.museumdigital.core.utils.UserDataStoreImpl;

public class DashboardActivity extends AppCompatActivity implements UserView {

    private UserPresenter userPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        userPresenter = new UserPresenter(this, new UserDataStoreImpl(this), this);

        ImageView logoutButton = findViewById(R.id.logo);
        logoutButton.setOnClickListener(v -> logout());

        Button budaya = findViewById(R.id.btn_budaya);
        budaya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, TambahBudayaActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button masakan = findViewById(R.id.btn_masakan); // Ganti dengan ID yang sesuai
        masakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, TambahResepActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void logout() {
        userPresenter.signOut();
        Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showUserCreatedMessage() {

    }

    @Override
    public void showUserCreationErrorMessage(String message) {

    }

    @Override
    public void showLoginSuccessMessage() {
        // Handle login success UI if needed
    }

    @Override
    public void showLoginErrorMessage(String message) {
        // Handle login error UI if needed
    }

    @Override
    public void showLoading(boolean isLoading) {

    }
}
