package com.example.museumdigital.admin.auth.presenter;

import com.example.museumdigital.admin.auth.data.User;
import com.example.museumdigital.admin.auth.data.UserRepository;
import com.example.museumdigital.admin.auth.view.UserView;

import org.springframework.security.crypto.bcrypt.BCrypt;


public class UserPresenter {
    private UserView view;
    private UserRepository userRepository;

    public UserPresenter(UserView view, UserRepository userRepository) {
        this.view = view;
        this.userRepository = userRepository;
    }

    public void createUser(String username, String password) {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        User user = new User(username, hashedPassword);
        boolean success = userRepository.createUser(user);
        if (success) {
            view.showUserCreatedMessage();
        } else {
            view.showUserCreationErrorMessage("Failed to create user.");
        }
    }

    public void loginUser(String username, String password) {
        User user = userRepository.getUserByUsername(username);
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            view.showLoginSuccessMessage();
        } else {
            view.showLoginErrorMessage("Invalid username or password.");
        }
    }
}
