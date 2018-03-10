package com.vaivaidev.creitiveblog.creitiveblog.presenter;



import com.vaivaidev.creitiveblog.creitiveblog.model.ResponseToken;
import com.vaivaidev.creitiveblog.creitiveblog.model.UserCredentials;
import com.vaivaidev.creitiveblog.creitiveblog.retrofit.GetTokenFromLoginService;
import com.vaivaidev.creitiveblog.creitiveblog.retrofit.RetrofitClient;
import com.vaivaidev.creitiveblog.creitiveblog.utils.ErrorUtils;
import com.vaivaidev.creitiveblog.creitiveblog.view.LoginView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public void login(String userEmail, String userPassword) {

        UserCredentials userCredentials = new UserCredentials(userEmail, userPassword);

        Call<ResponseToken> call =
                RetrofitClient.createService(GetTokenFromLoginService.class).loginWithCredentials(userCredentials);

        call.enqueue(new Callback<ResponseToken>() {
            @Override
            public void onResponse(Call<ResponseToken> call, Response<ResponseToken> response) {
                if(response.isSuccessful()) {
                    responseToken = response.body();
                    loginView.saveToken(responseToken.getToken());
                    loginView.onSuccessLogin();
                } else {
                    loginView.onServerError(ErrorUtils.serverErrorResponse(response.code()));
                }

            }

            @Override
            public void onFailure(Call<ResponseToken> call, Throwable t) {
                t.printStackTrace();
                loginView.onFailedLogin();
            }
        });

    }

    /**
     * Simple email validation method.
     *
     * */
    public boolean isEmailValid(String email) {
        boolean isValid = false;

        String expression = "^[\\w.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);

        if (matcher.matches()) {
            isValid = true;
        }
        return isValid;
    }


}
