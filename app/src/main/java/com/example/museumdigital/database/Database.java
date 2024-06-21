package com.example.museumdigital.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private Connection connection;
    private final String host = "34.34.223.128";
    private final String database = "museum";
    private final int port = 5432;
    private final String user = "postgres";
    private final String pass = "";
    private String url = "jdbc:postgresql://%s:%d/%s";

    public Database() {
        this.url = String.format(this.url, this.host, this.port, this.database);
        connect();
    }

    private void connect() {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Connecting to database: " + url);
            connection = DriverManager.getConnection(url, user, pass);
            System.out.println("Connected successfully!");
        } catch (Exception e) {
            System.out.println("Connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
