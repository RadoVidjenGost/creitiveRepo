package com.vaivaidev.creitiveblog.creitiveblog.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vaivaidev.creitiveblog.R;
import com.vaivaidev.creitiveblog.creitiveblog.model.UserCredentials;
import com.vaivaidev.creitiveblog.creitiveblog.presenter.LoginPresenter;
import com.vaivaidev.creitiveblog.creitiveblog.view.LoginView;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private EditText userEmail;
    private EditText userPassword;
    private Button loginButton;
    private LoginPresenter loginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupUi();
        loginPresenter = new LoginPresenter(this);
    }

    private void setupUi() {
        userEmail = findViewById(R.id.editTextEmail);
        userPassword = findViewById(R.id.editTextPassword);
        loginButton = findViewById(R.id.buttonLogin);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = userEmail.getText().toString();
                String password = userPassword.getText().toString();

                loginPresenter.login(new UserCredentials("candidate@creitive.com", "1234567"));

            }
        });

    }


    @Override
    public void onSuccessLogin() {
        Toast.makeText(this, "Successful login!", Toast.LENGTH_SHORT).show();
        //startBlogActivity();
    }

    @Override
    public void onFailedLogin() {
        Toast.makeText(this, "Failed login!", Toast.LENGTH_SHORT).show();
    }
}