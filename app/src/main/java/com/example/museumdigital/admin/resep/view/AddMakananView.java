package com.example.museumdigital.admin.resep.view;

import com.example.museumdigital.admin.budaya.model.Budaya;
import com.example.museumdigital.admin.resep.model.Makanan;

public interface AddMakananView {
    void showAddMakananSuccessMessage(String message, Makanan data);
    void showAddMakananErrorMessage(String message);
}
