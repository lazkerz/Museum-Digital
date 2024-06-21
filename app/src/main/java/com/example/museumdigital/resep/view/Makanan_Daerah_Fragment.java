package com.example.museumdigital.resep.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.museumdigital.R;
import com.example.museumdigital.core.contract.MainContract;
import com.example.museumdigital.core.model.Makanan.DataItem;
import com.example.museumdigital.core.remote.ApiClient.ApiClient;
import com.example.museumdigital.resep.adapter.MakananAdapter;
import com.example.museumdigital.resep.presenter.MakananPresenter;

import java.util.List;

public class Makanan_Daerah_Fragment extends Fragment implements MainContract.View {

    private RecyclerView rvMakanan;
    private MakananAdapter makananAdapter;
    private MakananPresenter presenter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_makanan__daerah_, container, false);

        rvMakanan = rootView.findViewById(R.id.rv_makanan);
        rvMakanan.setLayoutManager(new LinearLayoutManager(getContext()));

        // Inisialisasi Adapter
        makananAdapter = new MakananAdapter(getContext());
        rvMakanan.setAdapter(makananAdapter);

        // Inisialisasi Presenter
        presenter = new MakananPresenter(this, new ApiClient(getContext()));

        // Fetch data makanan
        presenter.fetchMakananData();

        return rootView;
    }

    @Override
    public void showMakananData(List<DataItem> makananList) {
        // Menampilkan data makanan ke RecyclerView melalui adapter
        makananAdapter.setData(makananList);
    }

    @Override
    public void showError(String message) {
        // Tampilkan pesan kesalahan jika ada
    }
}