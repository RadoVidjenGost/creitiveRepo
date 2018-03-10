package com.vaivaidev.creitiveblog.creitiveblog.presenter;

import com.vaivaidev.creitiveblog.creitiveblog.model.BlogItemContent;
import com.vaivaidev.creitiveblog.creitiveblog.model.ResponseToken;
import com.vaivaidev.creitiveblog.creitiveblog.retrofit.GetBlogWithIdService;
import com.vaivaidev.creitiveblog.creitiveblog.retrofit.RetrofitClient;
import com.vaivaidev.creitiveblog.creitiveblog.view.DisplayBlogView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Iva on 3/9/2018.
 */

public class DisplayBlogPresenter {

    private DisplayBlogView displayBlogView;
    private BlogItemContent blogItemContent;

    public DisplayBlogPresenter(DisplayBlogView displayBlogView) {
        this.displayBlogView = displayBlogView;
        this.blogItemContent = new BlogItemContent(null);
    }

    public void showBlogWithId(String authToken, int id) {

        Call<BlogItemContent> call =
                RetrofitClient.createService(GetBlogWithIdService.class).getBlogItemWithId(authToken, id);

        call.enqueue(new Callback<BlogItemContent>() {
            @Override
            public void onResponse(Call<BlogItemContent> call, Response<BlogItemContent> response) {
                blogItemContent = response.body();
                displayBlogView.displayBlogItem(blogItemContent.getContent());

            }

            @Override
            public void onFailure(Call<BlogItemContent> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

}
