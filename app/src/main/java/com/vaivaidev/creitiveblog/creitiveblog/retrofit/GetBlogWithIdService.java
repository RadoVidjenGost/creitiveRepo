package com.vaivaidev.creitiveblog.creitiveblog.retrofit;

import com.vaivaidev.creitiveblog.creitiveblog.model.BlogItemContent;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;

/**
 * Created by Iva on 3/9/2018.
 */

public interface GetBlogWithIdService {


    @Headers({
            "Accept: application/json"
    })
    @GET("/blogs/{id}")
    Call<BlogItemContent> getBlogItemWithId(@Header("X-Authorize") String authToken,
                                            @Path("id") int contentId);
}
