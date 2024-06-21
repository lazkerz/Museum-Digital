package com.example.museumdigital.core.contract;

import com.example.museumdigital.core.model.BudayaResponse;
import com.example.museumdigital.core.model.Makanan.DataItem;
import com.example.museumdigital.core.model.Makanan.MakananResponse;

import java.util.List;

public interface MainContract {

    interface View {
        void showMakananData(List<DataItem> makananList);
//        void showBudayaData(List<BudayaResponse.DataItem> budayaList);
        void showError(String message);
    }

    interface Presenter {
        void fetchMakananData();
//        void fetchBudayaData();
    }
}

