package com.example.museumdigital.remote;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private Connection connection;

    // For Local PostgreSQL on physical device
    private final String host = "192.168.0.137";  // Ganti dengan IP address lokal komputer host Anda
    private final String database = "dbMuseum";
    private final int port = 5433;
    private final String user = "postgres";
    private final String pass = "Lazira4567";
    private String url = "jdbc:postgresql://%s:%d/%s";
    private boolean status;

    public Database()
    {
        this.url = String.format(this.url, this.host, this.port, this.database);
        connect();
        //this.disconnect();
        System.out.println("Connection status: " + status);
    }

    private void connect()
    {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run()
            {
                try
                {
                    Class.forName("org.postgresql.Driver");
                    System.out.println("Connecting to database: " + url);
                    connection = DriverManager.getConnection(url, user, pass);
                    status = true;
                    System.out.println("Connected: " + status);
                }
                catch (Exception e)
                {
                    status = false;
                    System.out.print("Connection failed: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        try
        {
            thread.join();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            this.status = false;
        }
    }

    public Connection getExtraConnection()
    {
        Connection c = null;
        try
        {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(url, user, pass);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return c;
    }
}
