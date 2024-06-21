package com.example.museumdigital.core.model.Token;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Token extends RealmObject {
    @PrimaryKey
    private String id = "token"; // Single token instance
    private String accessToken;
    private String refreshToken;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
