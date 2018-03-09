package com.vaivaidev.creitiveblog.creitiveblog.retrofit;

import com.vaivaidev.creitiveblog.creitiveblog.model.BlogItem;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;

/**
 * Created by Iva on 3/9/2018.
 */

public interface GetBlogItemService {

    @Headers({
            "Accept: application/json",
            "Content-Type: application/json"

    })
    @GET("/blogs")
    Call<BlogItem> getBlogItemFromServer(@Header("X-Authorize") String authToken);

}
