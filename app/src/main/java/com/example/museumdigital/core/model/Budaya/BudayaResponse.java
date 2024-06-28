package com.example.museumdigital.core.model.Budaya;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class BudayaResponse{

	@SerializedName("data")
	private List<DataBudaya> data;

	@SerializedName("message")
	private String message;

	public List<DataBudaya> getData(){
		return data;
	}

	public String getMessage(){
		return message;
	}
}