package com.example.museumdigital.resep.view;

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
import com.example.museumdigital.core.remote.ApiClient.ApiClient;
import com.example.museumdigital.resep.model.Data;
import com.example.museumdigital.resep.model.DetailMakananResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detail_Resep_Activity extends AppCompatActivity {

    private ImageView imageMakanan , back;
    private TextView tvNamaMakanan, tvDeskripsi, tvBahan, tvCaraMemasak, tvPenyajian, tvKeunikan , link;
    private ApiClient apiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_resep);

        // Initialize views
        back = findViewById(R.id.ic_back);
        link = findViewById(R.id.lokasi);
        imageMakanan = findViewById(R.id.img_resep);
        tvNamaMakanan = findViewById(R.id.tv_nama_makanan);
        tvDeskripsi = findViewById(R.id.tv_deskripsi_makanan);
        tvBahan = findViewById(R.id.tv_deskrpsi_bahan);
        tvCaraMemasak = findViewById(R.id.tv_deskripsi_cara_memasak);
        tvPenyajian = findViewById(R.id.tv_deskripsi_penyajian);
        tvKeunikan = findViewById(R.id.tv_deskripsi_keunikan);

        // Get the passed ID
        int makananId = getIntent().getIntExtra("MAKANAN_ID", -1);

        // Initialize ApiClient
        apiClient = new ApiClient(this);

        back.setOnClickListener(v -> onBackPressed());

        // Fetch the detail data
        fetchDetailMakanan(makananId);
    }


    private void fetchDetailMakanan(int makananId) {
        apiClient.fetchMakananDetailData(makananId, new Callback<DetailMakananResponse>() {
            @Override
            public void onResponse(Call<DetailMakananResponse> call, Response<DetailMakananResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    DetailMakananResponse detailMakananResponse = response.body();

                    updateUI(detailMakananResponse.getData());
                } else {
                    Toast.makeText(Detail_Resep_Activity.this, "Failed to fetch data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DetailMakananResponse> call, Throwable t) {
                Toast.makeText(Detail_Resep_Activity.this, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void updateUI(Data data) {
        if (data.getMedia() != null && !data.getMedia().isEmpty()) {
            Glide.with(this)
                    .load(data.getMedia().get(0))  // Load the first image URL
                    .into(imageMakanan);
        }
        tvNamaMakanan.setText(data.getNamaMakanan());
        tvDeskripsi.setText(data.getDeskripsi());
        tvBahan.setText(data.getResep().getBahan());
        tvCaraMemasak.setText(data.getResep().getCaraMemasak());
        tvPenyajian.setText(data.getResep().getPenyajian());
        tvKeunikan.setText(data.getResep().getKeunikan());
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
}
