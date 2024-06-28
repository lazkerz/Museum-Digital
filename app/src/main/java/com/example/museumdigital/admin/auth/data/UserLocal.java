package com.example.museumdigital.admin.auth.data;

public class UserLocal {

    private Integer id;
    private String name;
    private String username;
    private String password;
    private String token;
    private String refreshToken;

    public UserLocal(Integer id, String name, String username, String password,String token, String refreshToken) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.token = token;
        this.refreshToken = refreshToken;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
