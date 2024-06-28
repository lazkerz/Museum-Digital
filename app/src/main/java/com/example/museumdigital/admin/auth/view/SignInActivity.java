package com.example.museumdigital.admin.auth.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.museumdigital.R;
import com.example.museumdigital.admin.DashboardActivity;
import com.example.museumdigital.admin.auth.presenter.UserPresenter;
import com.example.museumdigital.core.utils.UserDataStoreImpl;

public class SignInActivity extends AppCompatActivity implements UserView {

    private EditText etUsername, etPassword;
    private FrameLayout btnLogin;
    private TextView tvLogin;
    private ProgressBar progressLogin;
    private UserPresenter userPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        etUsername = findViewById(R.id.authUserNameEditText); // Adjust if the ID is different in auth_field_component.xml
        etPassword = findViewById(R.id.authPasswordEditText); // Adjust if the ID is different in auth_field_component.xml
        btnLogin = findViewById(R.id.btn_login);
        tvLogin = findViewById(R.id.tv_login);
        progressLogin = findViewById(R.id.progress_login);

        userPresenter = new UserPresenter(this, new UserDataStoreImpl(this), this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in both fields", Toast.LENGTH_SHORT).show();
            return;
        }

        showLoading(true);

        userPresenter.login(username, password);
    }

    @Override
    public void showUserCreatedMessage() {
        // Not used in SignInActivity
    }

    @Override
    public void showUserCreationErrorMessage(String message) {
        // Not used in SignInActivity
    }

    @Override
    public void showLoginSuccessMessage() {
        showLoading(false);
        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
        // Redirect to another activity or perform another action
        Intent intent = new Intent(SignInActivity.this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void showLoginErrorMessage(String message) {
        showLoading(false);
        Toast.makeText(this, "Login failed: " + message, Toast.LENGTH_SHORT).show();
    }

    public void showLoading(boolean isLoading) {
        if (isLoading) {
            tvLogin.setVisibility(View.GONE);
            progressLogin.setVisibility(View.VISIBLE);
        } else {
            tvLogin.setVisibility(View.VISIBLE);
            progressLogin.setVisibility(View.GONE);
        }
    }
}
