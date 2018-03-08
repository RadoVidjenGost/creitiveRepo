package com.vaivaidev.creitiveblog.creitiveblog.retrofit;

import com.vaivaidev.creitiveblog.creitiveblog.model.ResponseToken;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Iva on 3/8/2018.
 */

public interface GetTokenFromLogin {

    @POST("login")
    Call<ResponseToken> loginWithCredentials(@Body String userEmail,
                                             @Body String userPassword);
}
