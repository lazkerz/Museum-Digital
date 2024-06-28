package com.example.museumdigital.resep.model;

import com.google.gson.annotations.SerializedName;

public class Resep{

	@SerializedName("createdAt")
	private String createdAt;

	@SerializedName("bahan")
	private String bahan;

	@SerializedName("cara_memasak")
	private String caraMemasak;

	@SerializedName("penyajian")
	private String penyajian;

	@SerializedName("id")
	private int id;

	@SerializedName("keunikan")
	private String keunikan;

	@SerializedName("updatedAt")
	private String updatedAt;

	public String getCreatedAt(){
		return createdAt;
	}

	public String getBahan(){
		return bahan;
	}

	public String getCaraMemasak(){
		return caraMemasak;
	}

	public String getPenyajian(){
		return penyajian;
	}

	public int getId(){
		return id;
	}

	public String getKeunikan(){
		return keunikan;
	}

	public String getUpdatedAt(){
		return updatedAt;
	}
}