package com.example.museumdigital.core;

import com.example.museumdigital.core.model.Budaya.DataBudaya;
import com.example.museumdigital.core.model.Makanan.DataItem;


import java.util.List;

public interface MainView {
    void showMakananData(List<DataItem> makananList);
    void showBudayaData(List<DataBudaya> budayaList);
    void showError(String message);
}
