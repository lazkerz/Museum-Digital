package com.example.museumdigital.admin.auth.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.museumdigital.R;
//import com.example.museumdigital.admin.auth.data.UserRepository;
import com.example.museumdigital.admin.auth.data.UserRepository;
import com.example.museumdigital.admin.auth.presenter.UserPresenter;
import com.example.museumdigital.database.Database;

public class UserActivity extends AppCompatActivity {
    private UserPresenter presenter;
    private UserRepository repository;

    private Database database;
    private UserView view;
    private EditText usernameEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        Button createUserButton = findViewById(R.id.createUserButton);

        repository = new UserRepository();
        presenter = new UserPresenter((UserView) view, repository);

        createUserButton.setOnClickListener(view -> {
            String username = usernameEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();

            if (!username.isEmpty() && !password.isEmpty()) {
                new Thread(() -> {
                    try {
                        presenter.createUser(username, password);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    runOnUiThread(() -> Toast.makeText(UserActivity.this, "User created", Toast.LENGTH_SHORT).show());
                }).start();
            } else {
                Toast.makeText(UserActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
