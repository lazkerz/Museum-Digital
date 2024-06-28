package com.example.museumdigital.admin.budaya.model;

import com.google.gson.annotations.SerializedName;

public class AddBudayaResponse{

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