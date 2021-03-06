package com.vaivaidev.creitiveblog.creitiveblog.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vaivaidev.creitiveblog.R;
import com.vaivaidev.creitiveblog.creitiveblog.adapter.BlogItemsAdapter;
import com.vaivaidev.creitiveblog.creitiveblog.model.BlogItem;
import com.vaivaidev.creitiveblog.creitiveblog.presenter.BlogListPresenter;
import com.vaivaidev.creitiveblog.creitiveblog.utils.SharedPreferencesManager;
import com.vaivaidev.creitiveblog.creitiveblog.view.BlogListView;

import java.util.List;

/**
 * Created by Iva on 3/9/2018.
 */

public class BlogListActivity extends BaseActivity implements BlogListView {

    private RecyclerView recyclerView;
    private BlogItemsAdapter blogItemsAdapter;
    private BlogListPresenter blogListPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_list);

        setupUi();

        blogListPresenter = new BlogListPresenter(this);
        blogListPresenter.fetchDataFromServer(SharedPreferencesManager.getInstance(this).getUserToken());

    }

    @Override
    protected void onNetworkChange(boolean connected) {
        if(!connected) {
            Snackbar snackbar =
                    Snackbar.make(findViewById(R.id.blog_activity_id),
                            getResources().getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG);
            snackbar.getView().setBackgroundColor(getResources().getColor(R.color.red));
            snackbar.show();
        }

    }

    private void setupUi() {
        setTitle(getResources().getString(R.string.blog_list_activity_title));
        recyclerView = findViewById(R.id.blog_items_recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void preparedListForAdapter(List<BlogItem> blogItemList) {
        blogItemsAdapter = new BlogItemsAdapter(blogItemList, this);
        recyclerView.setAdapter(blogItemsAdapter);
    }
}
