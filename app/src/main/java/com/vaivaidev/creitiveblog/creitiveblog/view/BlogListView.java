package com.vaivaidev.creitiveblog.creitiveblog.view;

import com.vaivaidev.creitiveblog.creitiveblog.model.BlogItem;

import java.util.List;

/**
 * Created by Iva on 3/9/2018.
 */

public interface BlogListView {

    void preparedListForAdapter(List<BlogItem> blogItemList);
}
