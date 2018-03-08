package com.vaivaidev.creitiveblog.creitiveblog.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.vaivaidev.creitiveblog.R;
import com.vaivaidev.creitiveblog.creitiveblog.view.LoginView;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private EditText userEmail;
    private EditText userPassword;
    private Button loginButton;
    //private Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userEmail = findViewById(R.id.)
        setupUi();
    }

    private void setupUi() {
        userEmail = findViewById(R.id.editTextEmail);
        userPassword = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.buttonLogin);


    }

    @Override
    public void login(String userEmail, String userPassword) {

    }

    @Override
    public void onSuccessLogin() {

    }
}
