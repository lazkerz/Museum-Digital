package com.example.museumdigital.budaya.model;

import com.google.gson.annotations.SerializedName;

public class Kategoris{

	@SerializedName("kategori")
	private String kategori;

	public String getKategori(){
		return kategori;
	}
}