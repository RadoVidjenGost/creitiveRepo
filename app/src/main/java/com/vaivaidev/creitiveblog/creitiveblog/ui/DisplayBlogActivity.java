package com.vaivaidev.creitiveblog.creitiveblog.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;


import com.vaivaidev.creitiveblog.R;
import com.vaivaidev.creitiveblog.creitiveblog.adapter.BlogItemsAdapter;
import com.vaivaidev.creitiveblog.creitiveblog.presenter.DisplayBlogPresenter;
import com.vaivaidev.creitiveblog.creitiveblog.utils.SharedPreferencesManager;
import com.vaivaidev.creitiveblog.creitiveblog.view.DisplayBlogView;

/**
 * Created by Iva on 3/9/2018.
 */

public class DisplayBlogActivity extends BaseActivity
        implements DisplayBlogView {

    private WebView webView;
    private ProgressBar progressBar;
    private DisplayBlogPresenter displayBlogPresenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_blog);

        setupUi();

        Bundle bundle = getIntent().getExtras();
        int contentId = -1;
        if(bundle != null) {
            contentId = bundle.getInt(BlogItemsAdapter.BLOG_ID);
        }

        displayBlogPresenter = new DisplayBlogPresenter(this);
        displayBlogPresenter.
                showBlogWithId(SharedPreferencesManager.getInstance(this).getUserToken(), contentId);


    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setupUi(){

        webView = findViewById(R.id.blog_content);
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
            }
        });
        webView.getSettings().setJavaScriptEnabled(true);
    }

    @Override
    protected void onNetworkChange(boolean connected) {
        if(!connected) {
            Snackbar snackbar =
                    Snackbar.make(findViewById(R.id.display_activity_id),
                            getResources().getString(R.string.no_internet_connection), Snackbar.LENGTH_LONG);
            snackbar.getView().setBackgroundColor(getResources().getColor(R.color.red));
            snackbar.show();
        }

    }

    @Override
    public void displayBlogItem(String content) {
        if(content != null) {
            webView.setVisibility(View.VISIBLE);
            webView.loadData(content, "text/html", "UTF-8");
        }
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        }
    }
}
