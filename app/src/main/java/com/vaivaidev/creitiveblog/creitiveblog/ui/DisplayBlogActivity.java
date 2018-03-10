package com.vaivaidev.creitiveblog.creitiveblog.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.Html;
import android.widget.TextView;

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

    private TextView content;
    private DisplayBlogPresenter displayBlogPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_blog);

        Bundle bundle = getIntent().getExtras();
        int contentId = -1;
        if(bundle != null) {
            contentId = bundle.getInt(BlogItemsAdapter.BLOG_ID);
        }
        setupUi();
        displayBlogPresenter = new DisplayBlogPresenter(this);
        displayBlogPresenter.
                showBlogWithId(SharedPreferencesManager.getInstance(this).getUserToken(), contentId);


    }

    private void setupUi(){
        content = findViewById(R.id.blog_content);

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
            this.content.setText(Html.fromHtml(content));
        }
    }
}
