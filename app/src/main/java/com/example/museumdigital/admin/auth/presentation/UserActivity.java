package com.example.museumdigital.admin.auth.presentation;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.museumdigital.R;
import com.example.museumdigital.admin.auth.data.UserRepository;
import com.example.museumdigital.admin.auth.presenter.UserPresenter;
import com.example.museumdigital.admin.auth.view.UserView;
import com.example.museumdigital.remote.Database;

import java.util.List;

public class UserActivity extends AppCompatActivity implements UserView {
    private UserPresenter presenter;
    private EditText usernameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Database database = new Database();
        UserRepository userRepository = new UserRepository(database);
        presenter = new UserPresenter(this, userRepository);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        Button createUserButton = findViewById(R.id.createUserButton);
//        Button loginUserButton = findViewById(R.id.loginUserButton);
//        Button loadUsersButton = findViewById(R.id.loadUsersButton);

        createUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                presenter.createUser(username, password);
            }
        });

//        loginUserButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String username = usernameEditText.getText().toString();
//                String password = passwordEditText.getText().toString();
//                presenter.loginUser(username, password);
//            }
//        });

//        loadUsersButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                presenter.loadUsers();
//            }
//        });
    }

    @Override
    public void showUserCreatedMessage() {
        Toast.makeText(this, "User created successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUserCreationErrorMessage(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoginSuccessMessage() {
        Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoginErrorMessage(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

//    @Override
//    public void showUserList(List<User> users) {
//        // Contoh sederhana: tampilkan nama pengguna sebagai Toast
//        StringBuilder userList = new StringBuilder("User list:\n");
//        for (User user : users) {
//            userList.append(user.getUsername()).append("\n");
//        }
//        Toast.makeText(this, userList.toString(), Toast.LENGTH_LONG).show();
//    }
//
//    @Override
//    public void updateUIAfterLogin() {
//        // Contoh sederhana: kosongkan input setelah login
//        usernameEditText.setText("");
//        passwordEditText.setText("");
//        Toast.makeText(this, "UI updated after login", Toast.LENGTH_SHORT).show();
//    }
}

