package com.example.museumdigital.admin.resep.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Makanan{

	@SerializedName("createdAt")
	private String createdAt;

	@SerializedName("kategori_id")
	private int kategoriId;

	@SerializedName("resep_id")
	private int resepId;

	@SerializedName("lokasi")
	private String lokasi;

	@SerializedName("nama_makanan")
	private String namaMakanan;

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

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public int getKategoriId(){
		return kategoriId;
	}

	public void setKategoriId(int kategoriId) {
		this.kategoriId = kategoriId;
	}

	public int getResepId(){
		return resepId;
	}

	public void setResepId(int resepId) {
		this.resepId = resepId;
	}

	public String getLokasi(){
		return lokasi;
	}

	public void setLokasi(String lokasi) {
		this.lokasi = lokasi;
	}

	public String getNamaMakanan(){
		return namaMakanan;
	}

	public void setNamaMakanan(String namaMakanan) {
		this.namaMakanan = namaMakanan;
	}

	public List<String> getMedia(){
		return media;
	}

	public void setMedia(List<String> media) {
		this.media = media;
	}

	public int getId(){
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDeskripsi(){
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
}
