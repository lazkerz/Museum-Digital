package com.example.museumdigital.admin.budaya.view;

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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.museumdigital.R;
import com.example.museumdigital.admin.budaya.model.Budaya;
import com.example.museumdigital.admin.budaya.presenter.AddBudayaPresenter;
import com.example.museumdigital.core.utils.UserDataStoreImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TambahBudayaActivity extends AppCompatActivity implements AddBudayaView {

    private static final int REQUEST_CODE_GALLERY = 1;
    private static final int REQUEST_PERMISSION = 100;

    private EditText etName, etDescription, etMaps;
    private Spinner spKategori;

    private HashMap<String, Integer> kategoriMap;

    private TextView btnUpload;
    private ImageView ivProfilePicture;
    private Uri selectedImageUri;
    private AddBudayaPresenter addBudayaPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_budaya);

        etName = findViewById(R.id.etNama);
        etDescription = findViewById(R.id.etDeskripsi);
        etMaps = findViewById(R.id.etMaps);
        spKategori = findViewById(R.id.spKategori);
        ivProfilePicture = findViewById(R.id.ivProfilePicture);
        btnUpload = findViewById(R.id.btn_upload_budaya);

        setupSpinner();

        addBudayaPresenter = new AddBudayaPresenter(this, new UserDataStoreImpl(this), this);

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUploadGambarClick(v);
            }
        });

        TextView btnAddBudaya = findViewById(R.id.btn_upload_budaya);
        btnAddBudaya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                String description = etDescription.getText().toString().trim();
                String maps = etMaps.getText().toString().trim();
                String selectedKategori = spKategori.getSelectedItem().toString();
                int kategoriId = kategoriMap.get(selectedKategori);// Change as needed

                if (name.isEmpty() || description.isEmpty()) {
                    Toast.makeText(TambahBudayaActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                File imageFile = null;
                if (selectedImageUri != null) {
                    String imagePath = getRealPathFromURI(selectedImageUri);
                    if (imagePath != null) {
                        imageFile = new File(imagePath);
                    }
                }

                Budaya budaya = new Budaya();
                budaya.setNamaBudaya(name);
                budaya.setDeskripsi(description);
                budaya.setLokasi(maps);
                budaya.setKategoriId(kategoriId);

                addBudayaPresenter.addBudaya(budaya, imageFile);
            }
        });
    }

    private void setupSpinner() {
        // Menyiapkan data untuk spinner dan nilai yang terkait
        kategoriMap = new HashMap<>();
        kategoriMap.put("Baju Adat", 1);
        kategoriMap.put("Alat Musik", 2);
        kategoriMap.put("Tarian", 3);
        kategoriMap.put("Tradisi", 4);

        // Membuat daftar untuk menampilkan teks dalam spinner
        List<String> kategoriList = new ArrayList<>(kategoriMap.keySet());

        // Membuat ArrayAdapter menggunakan daftar string
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, kategoriList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spKategori.setAdapter(adapter);

        // Mengatur listener untuk mendapatkan nilai yang dipilih
        spKategori.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedKategori = parent.getItemAtPosition(position).toString();
                int selectedValue = kategoriMap.get(selectedKategori);
                Toast.makeText(TambahBudayaActivity.this, "Selected: " + selectedKategori + " with value: " + selectedValue, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
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
            String imagePath = getRealPathFromURI(selectedImageUri);
            if (imagePath != null) {
                ivProfilePicture.setImageURI(selectedImageUri);
            } else {
                Toast.makeText(this, "Failed to get image path", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor == null) return null;
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String path = cursor.getString(column_index);
        cursor.close();
        return path;
    }



    @Override
    public void showAddBudayaSuccessMessage(String message, Budaya data) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        // Optionally display the returned data or navigate to another activity
        finish();
    }

    @Override
    public void showAddBudayaErrorMessage(String message) {
        Toast.makeText(this, "Failed to add budaya: " + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoginSuccessMessage() {
        // Implementation not shown for brevity
    }

    @Override
    public void showLoginErrorMessage(String message) {
        // Implementation not shown for brevity
    }

    @Override
    public void showUserCreatedMessage() {
        // Implementation not shown for brevity
    }
}
