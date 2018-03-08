package com.vaivaidev.creitiveblog.creitiveblog.presenter;

import com.vaivaidev.creitiveblog.creitiveblog.model.ResponseToken;
import com.vaivaidev.creitiveblog.creitiveblog.model.UserCredentials;
import com.vaivaidev.creitiveblog.creitiveblog.retrofit.GetTokenFromLogin;
import com.vaivaidev.creitiveblog.creitiveblog.retrofit.RetrofitClient;
import com.vaivaidev.creitiveblog.creitiveblog.view.LoginView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Iva on 3/8/2018.
 */

public class LoginPresenter {


    private LoginView loginView;
    private ResponseToken responseToken;

    public LoginPresenter(LoginView loginView) {
        this.loginView = loginView;
    }

    public void login(UserCredentials userCredentials) {

        Call<ResponseToken> call =
                RetrofitClient.createService(GetTokenFromLogin.class).loginWithCredentials(userCredentials);

        call.enqueue(new Callback<ResponseToken>() {
            @Override
            public void onResponse(Call<ResponseToken> call, Response<ResponseToken> response) {
                responseToken = response.body();
                loginView.onSuccessLogin();

            }

            @Override
            public void onFailure(Call<ResponseToken> call, Throwable t) {
                t.printStackTrace();
                loginView.onFailedLogin();
            }
        });



    }

}
