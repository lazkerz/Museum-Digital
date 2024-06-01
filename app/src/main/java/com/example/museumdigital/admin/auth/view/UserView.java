package com.example.museumdigital.admin.auth.view;

public interface UserView {
    void showUserCreatedMessage();
    void showUserCreationErrorMessage(String error);
    void showLoginSuccessMessage();
    void showLoginErrorMessage(String error);
}

