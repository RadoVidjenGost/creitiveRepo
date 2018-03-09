package com.vaivaidev.creitiveblog.creitiveblog.retrofit;

import com.vaivaidev.creitiveblog.creitiveblog.model.BlogItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * Created by Iva on 3/9/2018.
 */

public interface GetBlogWithIdService {


    @Headers({
            "Accept: application/json",
    })
    @GET("/:id")
    Call<BlogItem> getBlogItemWithId(@Header("X-Authorize") String authToken);
}
