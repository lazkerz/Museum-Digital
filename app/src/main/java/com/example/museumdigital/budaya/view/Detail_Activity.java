package com.example.museumdigital.budaya.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.museumdigital.R;
import com.example.museumdigital.budaya.model.DetailBudayaResponse;
import com.example.museumdigital.core.remote.ApiClient.ApiClient;
import com.example.museumdigital.budaya.model.Data;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detail_Activity extends AppCompatActivity {

    public ImageView imageBudaya, back;

    public TextView judul, kategori, deskripsi, link;

    public ApiClient apiClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        back = findViewById(R.id.ic_back);
        judul = findViewById(R.id.tv_judul);
        imageBudaya = findViewById(R.id.image_budaya);
        kategori = findViewById(R.id.tv_category);
        deskripsi = findViewById(R.id.tv_desc);
        link = findViewById(R.id.tv_link_maps);

        int budayaId = getIntent().getIntExtra("BUDAYA_ID", 1);

        // Initialize ApiClient
        apiClient = new ApiClient(this);

        back.setOnClickListener(v -> onBackPressed());

        // Fetch the detail data
        fetchDetailBudaya(budayaId);
    }

    private void fetchDetailBudaya(int budayaId) {
        apiClient.fetchBudayaDetailData(budayaId, new Callback<DetailBudayaResponse>() {
            @Override
            public void onResponse(Call<DetailBudayaResponse> call, Response<DetailBudayaResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    DetailBudayaResponse detailBudayaResponse = response.body();

                    updateUI(detailBudayaResponse.getData());
                } else {
                    Toast.makeText(Detail_Activity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DetailBudayaResponse> call, Throwable t) {
                Toast.makeText(Detail_Activity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void updateUI(Data data) {
        if (data.getMedia() != null && !data.getMedia().isEmpty()) {
            Glide.with(this)
                    .load(data.getMedia().get(0))  // Load the first image URL
                    .into(imageBudaya);
        }
        judul.setText(data.getNamaBudaya());
        deskripsi.setText(formatDescription(data.getDeskripsi()));
        kategori.setText(data.getKategoris().getKategori());
        link.setText(data.getLokasi());

        String lokasi = data.getLokasi();
        link.setText(lokasi);
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLink(lokasi);
            }
        });
    }

    private void openLink(String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }

    private String formatDescription(String description) {
        // Formatting the description into paragraphs
        String[] paragraphs = description.split("\\. ");
        StringBuilder formattedDescription = new StringBuilder();
        for (String paragraph : paragraphs) {
            formattedDescription.append(paragraph.trim()).append(".\n\n");
        }
        return formattedDescription.toString().trim();
    }
}