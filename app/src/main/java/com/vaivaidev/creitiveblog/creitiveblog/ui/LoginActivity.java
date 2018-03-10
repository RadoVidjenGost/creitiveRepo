package com.vaivaidev.creitiveblog.creitiveblog.ui;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vaivaidev.creitiveblog.R;
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
        if(!connected) {
            Snackbar snackbar =
                    Snackbar.make(findViewById(R.id.activity_login_id),
                            getResources().getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG);
            snackbar.getView().setBackgroundColor(getResources().getColor(R.color.red));
            snackbar.show();
        }
    }

    private void setupUi() {
        setTitle(getResources().getString(R.string.login_activity_title));
        userEmail = findViewById(R.id.edit_text_email);
        userPassword = findViewById(R.id.edit_text_password);
        loginButton = findViewById(R.id.button_login);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = userEmail.getText().toString();
                String password = userPassword.getText().toString();

                if(!loginPresenter.isEmailValid(email)) {
                    userEmail.setError(getString(R.string.email_invalid_warning));
                    return;
                }

                if(TextUtils.isEmpty(password)) {
                    userPassword.setError(getString(R.string.password_empty_warning));
                    return;
                }

                loginPresenter.login(email, password);

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

    @Override
    public void onServerError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    private void isUserAlreadyLoggedIn() {
        if(SharedPreferencesManager.getInstance(this).getUserToken() != null) {
            startBlogActivity();
        }

    }

    private void startBlogActivity() {
        Intent intent = new Intent(this, BlogListActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
    }


}
