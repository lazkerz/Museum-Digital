package com.example.museumdigital.database;

import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Objects;

public class DatabaseHelper {
    protected static String database = "dbmuseum";
    protected static String ip = "127.0.0.1";
    protected static int port = 3306;
    protected static String user = "root";
    protected static String pass = "";

    public Connection CONN() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String connectionString = "jdbc:mysql://" + ip + ":" + port + "/" + database;
            conn = DriverManager.getConnection(connectionString, user, pass);
        } catch (Exception e) {
            Log.e("ERROR", Objects.requireNonNull(e.getMessage()));
        }
        return conn;
    }
}
