package com.example.museumdigital.admin.auth.data;

import com.example.museumdigital.remote.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {
    private Database database;

    public UserRepository(Database database) {
        this.database = database;
    }

    public boolean createUser(User user) {
        String query = "INSERT INTO User (username, password) VALUES (?, ?)";
        try (Connection connection = database.getExtraConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public User getUserByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ?";
        try (Connection connection = database.getExtraConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String password = resultSet.getString("password");
                return new User(username, password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

