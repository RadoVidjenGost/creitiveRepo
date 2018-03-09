package com.vaivaidev.creitiveblog.creitiveblog.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vaivaidev.creitiveblog.R;
import com.vaivaidev.creitiveblog.creitiveblog.model.UserCredentials;
import com.vaivaidev.creitiveblog.creitiveblog.presenter.LoginPresenter;
import com.vaivaidev.creitiveblog.creitiveblog.utils.SharedPreferencesManager;
import com.vaivaidev.creitiveblog.creitiveblog.view.LoginView;

public class LoginActivity extends BaseActivity implements LoginView {

    private EditText userEmail;
    private EditText userPassword;
    private Button loginButton;
    private LoginPresenter loginPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        isUserAlreadyLoggedIn();
        setupUi();

        loginPresenter = new LoginPresenter(this);
    }

    @Override
    protected void onNetworkChange(boolean connected) {
        if(connected) {
            Snackbar snackbar =
                    Snackbar.make(findViewById(R.id.activity_login_id),
                            getResources().getString(R.string.internet_connection), Snackbar.LENGTH_LONG);
            snackbar.show();
        } else {
            Snackbar snackbar =
                    Snackbar.make(findViewById(R.id.activity_login_id),
                            getResources().getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG);
            snackbar.getView().setBackgroundColor(getResources().getColor(R.color.red));
            snackbar.show();
        }
    }

    private void setupUi() {
        setTitle("Login");
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
    public void saveToken(String token) {
        if(token != null)
            SharedPreferencesManager.getInstance(this).saveUserToken(token);
    }

    @Override
    public void onSuccessLogin() {
        Toast.makeText(this, "Successful login!", Toast.LENGTH_SHORT).show();
        startBlogActivity();
    }

    @Override
    public void onFailedLogin() {
        Toast.makeText(this, "Failed login!", Toast.LENGTH_SHORT).show();
    }

    private void isUserAlreadyLoggedIn() {
        if(SharedPreferencesManager.getInstance(this).getUserToken() != null) {
            startBlogActivity();
        }

    }

    private void startBlogActivity() {
        Toast.makeText(this, "BLOG ACTIVITY STARTED", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, BlogListActivity.class);
        startActivity(intent);
        finish();

    }
}
