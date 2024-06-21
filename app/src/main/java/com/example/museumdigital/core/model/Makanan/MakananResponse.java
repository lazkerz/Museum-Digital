package com.example.museumdigital.core.model.Makanan;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class MakananResponse{

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