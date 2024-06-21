package com.example.museumdigital.admin.auth.presenter;
import com.example.museumdigital.admin.auth.data.User;
import com.example.museumdigital.admin.auth.data.UserRepository;
import com.example.museumdigital.admin.auth.view.UserView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

public class UserPresenter {
    private UserView view;
    private UserRepository userRepository;

    public UserPresenter(UserView view, UserRepository userRepository) {
        this.view = view;
        this.userRepository = userRepository;
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public void createUser(String username, String password) throws SQLException {
        String hashedPassword = hashPassword(password);
        User user = new User(username, hashedPassword);
        boolean success = userRepository.createUser(user);
        if (success) {
            view.showUserCreatedMessage();
        } else {
            view.showUserCreationErrorMessage("Failed to create user.");
        }
    }

//    public void loginUser(String username, String password) {
//        User user = userRepository.getUserByUsername(username);
//        if (user != null && hashPassword(password).equals(user.getPassword())) {
//            view.showLoginSuccessMessage();
//        } else {
//            view.showLoginErrorMessage("Invalid username or password.");
//        }
//    }
}