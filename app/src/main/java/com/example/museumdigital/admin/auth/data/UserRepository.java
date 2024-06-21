package com.example.museumdigital.admin.auth.data;

import com.example.museumdigital.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRepository {
    private Database database;
    public UserRepository() {
        this.database = new Database(); // Inisialisasi objek Database
    }

    public boolean createUser(User user) throws SQLException {
        String query = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (Connection connection = database.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            // Lebih baik melempar kembali eksepsi atau memberikan pesan yang bermakna
            throw new SQLException("Failed to create user: " + e.getMessage());
        }
    }

}
