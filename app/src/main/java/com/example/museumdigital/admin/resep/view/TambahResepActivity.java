package com.example.museumdigital.admin.resep.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.museumdigital.R;
import com.example.museumdigital.admin.resep.model.Makanan;
import com.example.museumdigital.admin.resep.model.Resep;
import com.example.museumdigital.admin.resep.presenter.AddMakananPresenter;
import com.example.museumdigital.core.utils.UserDataStoreImpl;

import java.io.File;

public class TambahResepActivity extends AppCompatActivity implements AddMakananView {

    private static final int REQUEST_CODE_GALLERY = 1;
    private static final int REQUEST_PERMISSION = 100;

    private EditText etName, etDescription, etKeunikan, etBahan, etLangkah, etPenyajian, etMaps;
    private ImageView ivProfilePicture, icback;
    private TextView btnUpload, btnTambah;

    private Uri selectedImageUri;
    private AddMakananPresenter addMakananPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_resep);

        etName = findViewById(R.id.etNama);
        etDescription = findViewById(R.id.etDeskripsi);
        etKeunikan = findViewById(R.id.etKeunikan);
        etBahan = findViewById(R.id.etBahan);
        etLangkah = findViewById(R.id.etLangkah);
        etPenyajian = findViewById(R.id.etPenyajian);
        etMaps = findViewById(R.id.etMaps);
        ivProfilePicture = findViewById(R.id.ivProfilePicture);
        icback = findViewById(R.id.ic_back);

        btnUpload = findViewById(R.id.btn_upload_gambar);
        btnTambah = findViewById(R.id.btn_upload_resep);

        addMakananPresenter = new AddMakananPresenter(this, new UserDataStoreImpl(this), this);

        icback.setOnClickListener(v -> onBackPressed());
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUploadGambarClick(v);
            }
        });

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tambahResep(v);
            }
        });
    }

    public void onUploadGambarClick(View view) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION);
        } else {
            openGallery();
        }
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_CODE_GALLERY);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            ivProfilePicture.setImageURI(selectedImageUri);
        }
    }

    public void tambahResep(View view) {
        String name = etName.getText().toString().trim();
        String description = etDescription.getText().toString().trim();
        String keunikan = etKeunikan.getText().toString().trim();
        String bahan = etBahan.getText().toString().trim();
        String langkah = etLangkah.getText().toString().trim();
        String penyajian = etPenyajian.getText().toString().trim();
        String maps = etMaps.getText().toString().trim();

        if (name.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        File imageFile = null;
        if (selectedImageUri != null) {
            String imagePath = getRealPathFromURI(selectedImageUri);
            if (imagePath != null) {
                imageFile = new File(imagePath);
            }
        }

        Makanan makanan = new Makanan();
        Resep resep = new Resep();
        makanan.setNamaMakanan(name);
        makanan.setDeskripsi(description);
        makanan.setLokasi(maps);
        resep.setBahan(bahan);
        resep.setCaraMemasak(langkah);
        resep.setPenyajian(penyajian);
        resep.setKeunikan(keunikan);

        addMakananPresenter.addMakanan(makanan, resep, imageFile);
    }

    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor == null) return null;
        cursor.moveToFirst();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        String path = cursor.getString(column_index);
        cursor.close();
        return path;
    }

    @Override
    public void showAddMakananSuccessMessage(String message, Makanan data) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        // Optionally display the returned data or navigate to another activity
        finish();
    }

    @Override
    public void showAddMakananErrorMessage(String message) {
        Toast.makeText(this, "Failed to add makanan: " + message, Toast.LENGTH_SHORT).show();
    }
}
