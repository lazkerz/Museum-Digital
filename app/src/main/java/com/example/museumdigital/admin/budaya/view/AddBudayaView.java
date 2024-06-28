package com.example.museumdigital.admin.budaya.view;

import com.example.museumdigital.admin.budaya.model.Budaya;

public interface AddBudayaView {
    void showAddBudayaSuccessMessage(String message, Budaya data);
    void showAddBudayaErrorMessage(String message);

    void showLoginSuccessMessage();

    void showLoginErrorMessage(String message);

    void showUserCreatedMessage();
}
