package com.vaivaidev.creitiveblog.creitiveblog.retrofit;

import com.vaivaidev.creitiveblog.creitiveblog.model.ResponseToken;
import com.vaivaidev.creitiveblog.creitiveblog.model.UserCredentials;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Iva on 3/8/2018.
 */

public interface GetTokenFromLoginService {


    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"
    })
    @POST("/login")
    Call<ResponseToken> loginWithCredentials(@Body UserCredentials userCredentials);
}
