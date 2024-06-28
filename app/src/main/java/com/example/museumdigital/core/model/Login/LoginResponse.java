package com.example.museumdigital.core.model.Login;

import com.google.gson.annotations.SerializedName;

public class LoginResponse{

	@SerializedName("message")
	private String message;

	@SerializedName("user")
	private User user;

	public String getMessage(){
		return message;
	}

	public User getUser(){
		return user;
	}

	public class User {
		@SerializedName("id")
		private int id;

		@SerializedName("username")
		private String username;

		@SerializedName("token")
		private String token;

		@SerializedName("refreshToken")
		private String refreshToken;

		public int getId() {
			return id;
		}

		public String getUsername() {
			return username;
		}

		public String getToken() {
			return token;
		}

		public String getRefreshToken() {
			return refreshToken;
		}
	}
}
