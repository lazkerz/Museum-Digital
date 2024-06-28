package com.example.museumdigital.resep.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.museumdigital.R;
import com.example.museumdigital.core.MainView;
import com.example.museumdigital.core.model.Budaya.DataBudaya;
import com.example.museumdigital.core.model.Makanan.DataItem;
import com.example.museumdigital.core.remote.ApiClient.ApiClient;
import com.example.museumdigital.resep.adapter.MakananAdapter;
import com.example.museumdigital.resep.presenter.MakananPresenter;

import java.util.List;

public class MakananFragment extends Fragment implements MainView {

    private RecyclerView rvMakanan;
    private MakananAdapter makananAdapter;
    private MakananPresenter presenter;

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_makanan, container, false);

        swipeRefreshLayout = rootView.findViewById(R.id.swiperefresh);
        if (swipeRefreshLayout == null) {
            throw new NullPointerException("SwipeRefreshLayout is null. Check the layout file.");
        }

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Refresh Makanan Data
                presenter.fetchMakananData();
            }
        });

        rvMakanan = rootView.findViewById(R.id.rv_makanan);
        rvMakanan.setLayoutManager(new LinearLayoutManager(getContext()));
        // Initialize Adapter
        makananAdapter = new MakananAdapter(getContext(), new MakananAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int makananId) {
                // Handle item click here

                // Contoh navigasi ke activity detail
                Intent intent = new Intent(getContext(), Detail_Resep_Activity.class);
                intent.putExtra("MAKANAN_ID", makananId);
                startActivity(intent);
            }
        });

        rvMakanan.setAdapter(makananAdapter);

        // Initialize Presenter
        presenter = new MakananPresenter(this, new ApiClient(getContext()));

        // Fetch Makanan Data
        presenter.fetchMakananData();

        return rootView;
    }

    @Override
    public void showMakananData(List<DataItem> makananList) {
        // Show Makanan Data in RecyclerView through Adapter
        makananAdapter.setData(makananList);
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showBudayaData(List<DataBudaya> budayaList) {
        // Not used in MakananFragment
    }

    @Override
    public void showError(String message) {
        swipeRefreshLayout.setRefreshing(false);
    }
}
