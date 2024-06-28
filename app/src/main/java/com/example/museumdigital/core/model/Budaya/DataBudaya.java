package com.example.museumdigital.core.model.Budaya;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class DataBudaya {

	@SerializedName("createdAt")
	private String createdAt;

	@SerializedName("kategori_id")
	private int kategoriId;

	@SerializedName("lokasi")
	private String lokasi;

	@SerializedName("nama_budaya")
	private String namaBudaya;

	@SerializedName("media")
	private List<String> media;

	@SerializedName("id")
	private int id;

	@SerializedName("deskripsi")
	private String deskripsi;

	@SerializedName("updatedAt")
	private String updatedAt;

	public String getCreatedAt(){
		return createdAt;
	}

	public int getKategoriId(){
		return kategoriId;
	}

	public String getLokasi(){
		return lokasi;
	}

	public String getNamaBudaya(){
		return namaBudaya;
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

	public String getUpdatedAt(){
		return updatedAt;
	}
}