package com.example.museumdigital.core.model;

import java.util.List;

import com.example.museumdigital.core.model.Makanan.DataItem;
import com.google.gson.annotations.SerializedName;

public class BudayaResponse{

	@SerializedName("data")
	private List<DataItem> data;

	@SerializedName("message")
	private String message;

	public List<DataItem> getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}
}