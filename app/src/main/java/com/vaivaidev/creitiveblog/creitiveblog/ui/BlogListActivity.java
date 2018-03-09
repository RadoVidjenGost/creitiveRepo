package com.vaivaidev.creitiveblog.creitiveblog.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.vaivaidev.creitiveblog.R;
import com.vaivaidev.creitiveblog.creitiveblog.view.BlogListView;

/**
 * Created by Iva on 3/9/2018.
 */

public class BlogListActivity extends AppCompatActivity implements BlogListView {

    private RecyclerView recyclerView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blog_list);

        setupUi();


    }

    private void setupUi() {





    }
}
