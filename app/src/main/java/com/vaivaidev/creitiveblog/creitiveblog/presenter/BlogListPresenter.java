package com.vaivaidev.creitiveblog.creitiveblog.presenter;

import android.util.Log;

import com.vaivaidev.creitiveblog.creitiveblog.model.BlogItem;
import com.vaivaidev.creitiveblog.creitiveblog.retrofit.GetBlogItemService;
import com.vaivaidev.creitiveblog.creitiveblog.retrofit.RetrofitClient;
import com.vaivaidev.creitiveblog.creitiveblog.view.BlogListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Iva on 3/9/2018.
 */

public class BlogListPresenter {

    private BlogListView blogListView;
    private List<BlogItem> blogItemList = new ArrayList<>();


    public BlogListPresenter(BlogListView blogListView) {
        this.blogListView = blogListView;
    }



    public void fetchDataFromServer(String authToken){


        Call<List<BlogItem>> call =
                RetrofitClient.createService(GetBlogItemService.class).getBlogItemsFromServer(authToken);

        call.enqueue(new Callback<List<BlogItem>>() {
            @Override
            public void onResponse(Call<List<BlogItem>> call, Response<List<BlogItem>> response) {
                blogItemList.addAll(response.body());
                if(blogListView != null)
                    blogListView.preparedListForAdapter(blogItemList);
            }

            @Override
            public void onFailure(Call<List<BlogItem>> call, Throwable t) {
                t.printStackTrace();

            }
        });


    }
}
