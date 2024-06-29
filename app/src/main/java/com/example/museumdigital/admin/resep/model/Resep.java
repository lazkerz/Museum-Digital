package com.example.museumdigital.admin.resep.model;

import com.google.gson.annotations.SerializedName;

public class Resep {

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

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getBahan() {
		return bahan;
	}

	public void setBahan(String bahan) {
		this.bahan = bahan;
	}

	public String getCaraMemasak() {
		return caraMemasak;
	}

	public void setCaraMemasak(String caraMemasak) {
		this.caraMemasak = caraMemasak;
	}

	public String getPenyajian() {
		return penyajian;
	}

	public void setPenyajian(String penyajian) {
		this.penyajian = penyajian;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKeunikan() {
		return keunikan;
	}

	public void setKeunikan(String keunikan) {
		this.keunikan = keunikan;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
}
