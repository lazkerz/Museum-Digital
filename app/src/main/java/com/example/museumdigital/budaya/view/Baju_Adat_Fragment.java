package com.example.museumdigital.budaya.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.museumdigital.R;
import com.example.museumdigital.budaya.adapter.BudayaAdapter;
import com.example.museumdigital.budaya.presenter.BudayaPresenter;
import com.example.museumdigital.core.MainView;
import com.example.museumdigital.core.model.Budaya.DataBudaya;
import com.example.museumdigital.core.model.Makanan.DataItem;
import com.example.museumdigital.core.remote.ApiClient.ApiClient;

import java.util.List;

public class Baju_Adat_Fragment extends Fragment implements MainView {

    private RecyclerView rvBudaya;
    private BudayaAdapter budayaAdapter;
    private BudayaPresenter presenter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_baju__adat_, container, false);

        rvBudaya = rootView.findViewById(R.id.rv_baju);
        rvBudaya.setLayoutManager(new LinearLayoutManager(getContext()));

        swipeRefreshLayout = rootView.findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setOnRefreshListener(() -> presenter.fetchBudayaData("1"));

        // Initialize Adapter
        budayaAdapter = new BudayaAdapter(getContext(), budayaId -> {
            Intent intent = new Intent(getContext(), Detail_Activity.class);
            intent.putExtra("BUDAYA_ID", budayaId);
            startActivity(intent);
        });
        rvBudaya.setAdapter(budayaAdapter);

        // Initialize Presenter
        presenter = new BudayaPresenter(this, new ApiClient(getContext()));

        // Fetch Budaya Data
        presenter.fetchBudayaData("1");

        return rootView;
    }

    @Override
    public void showMakananData(List<DataItem> makananList) {
        // Show Makanan Data in RecyclerView through Adapter
    }

    @Override
    public void showBudayaData(List<DataBudaya> budayaList) {
        budayaAdapter.setData(budayaList);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showError(String message) {
        swipeRefreshLayout.setRefreshing(false);
    }
}
