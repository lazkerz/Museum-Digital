package com.example.museumdigital.admin.resep.model;

import com.google.gson.annotations.SerializedName;

public class TambahMakananResponse{

	@SerializedName("message")
	private String message;

	@SerializedName("resep")
	private Resep resep;

	@SerializedName("makanan")
	private Makanan makanan;

	public String getMessage(){
		return message;
	}

	public Resep getResep(){
		return resep;
	}

	public Makanan getMakanan(){
		return makanan;
	}
}