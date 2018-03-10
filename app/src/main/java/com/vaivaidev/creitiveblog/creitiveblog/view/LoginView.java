package com.vaivaidev.creitiveblog.creitiveblog.view;

/**
 * Created by Iva on 3/8/2018.
 */

public interface LoginView {

    void saveToken(String token);
    void onSuccessLogin();
    void onFailedLogin();
    void onServerError(String errorMessage);
}
