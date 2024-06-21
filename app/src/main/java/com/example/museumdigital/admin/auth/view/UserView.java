package com.example.museumdigital.admin.auth.view;

public interface UserView {
    void showUserCreatedMessage();
    void showUserCreationErrorMessage(String message);
    void showLoginSuccessMessage();
    void showLoginErrorMessage(String message);
}

