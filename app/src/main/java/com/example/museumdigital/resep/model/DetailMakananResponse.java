package com.example.museumdigital.resep.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailMakananResponse{

	@SerializedName("data")
	private Data data;

	@SerializedName("message")
	private String message;

	public Data getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}
}
