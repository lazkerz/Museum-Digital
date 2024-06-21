package com.example.museumdigital.core.model.Makanan;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DataItem{

	@SerializedName("createdAt")
	private String createdAt;

	@SerializedName("kategori_id")
	private int kategoriId;

	@SerializedName("resep_id")
	private int resepId;

	@SerializedName("lokasi")
	private Object lokasi;

	@SerializedName("nama_makanan")
	private String namaMakanan;

	@SerializedName("media")
	private List<String> media;

	@SerializedName("id")
	private int id;

	@SerializedName("deskripsi")
	private String deskripsi;

	@SerializedName("resep")
	private Resep resep;

	@SerializedName("updatedAt")
	private String updatedAt;

	public String getCreatedAt(){
		return createdAt;
	}

	public int getKategoriId(){
		return kategoriId;
	}

	public int getResepId(){
		return resepId;
	}

	public Object getLokasi(){
		return lokasi;
	}

	public String getNamaMakanan(){
		return namaMakanan;
	}

	public List<String> getMedia(){
		return media;
	}

	public int getId(){
		return id;
	}

	public String getDeskripsi(){
		return deskripsi;
	}

	public Resep getResep(){
		return resep;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}
}