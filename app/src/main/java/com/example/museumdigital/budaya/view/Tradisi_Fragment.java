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
import com.example.museumdigital.resep.view.Detail_Resep_Activity;

import java.util.List;


public class Tradisi_Fragment extends Fragment implements MainView {

    private RecyclerView rvBudaya;
    private BudayaAdapter budayaAdapter;
    private BudayaPresenter presenter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tradisi_, container, false);

        rvBudaya = rootView.findViewById(R.id.rv_tradisi);
        rvBudaya.setLayoutManager(new LinearLayoutManager(getContext()));

        swipeRefreshLayout = rootView.findViewById(R.id.swiperefresh);
        if (swipeRefreshLayout == null) {
            throw new NullPointerException("SwipeRefreshLayout is null. Check the layout file.");
        }

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh Makanan Data
                presenter.fetchBudayaData("4");
            }
        });
        // Initialize Adapter
        budayaAdapter = new BudayaAdapter(getContext(), budayaId -> {
            Intent intent = new Intent(getContext(), Detail_Activity.class);
            intent.putExtra("BUDAYA_ID", budayaId);
            startActivity(intent);
        });
        rvBudaya.setAdapter(budayaAdapter);

        // Initialize Presenter
        presenter = new BudayaPresenter(this, new ApiClient(getContext()));

        // Fetch Makanan Data
        presenter.fetchBudayaData("4");

        return rootView;
    }


    @Override
    public void showMakananData(List<DataItem> makananList) {
        // Show Makanan Data in RecyclerView through Adapter
    }

    @Override
    public void showBudayaData(List<DataBudaya> budayaList) {
        // Not used in MakananFragment
        budayaAdapter.setData(budayaList);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showError(String message) {
        swipeRefreshLayout.setRefreshing(false);
    }
}