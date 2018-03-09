package com.vaivaidev.creitiveblog.creitiveblog.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.vaivaidev.creitiveblog.R;
import com.vaivaidev.creitiveblog.creitiveblog.model.BlogItem;

import java.util.List;

/**
 * Created by Iva on 3/9/2018.
 */

public class BlogItemsAdapter extends
        RecyclerView.Adapter<BlogItemsAdapter.BlogItemsViewHolder> {

    private List<BlogItem> blogItemList;
    private Context context;

    public BlogItemsAdapter(List<BlogItem> blogItemList, Context context) {
        this.blogItemList = blogItemList;
        this.context = context;
    }

    @Override
    public BlogItemsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_list_item, parent, false);
        return new BlogItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BlogItemsViewHolder holder, int position) {

        BlogItem blogItem = blogItemList.get(position);

        holder.title.setText(blogItem.getTitle());

        Picasso.with(context)
                .load(blogItem.getImageUrl())
                .into(holder.image);

        holder.description.setText(blogItem.getDescription());
    }

    @Override
    public int getItemCount() {
        return blogItemList.size();
    }

    static class BlogItemsViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView image;
        TextView description;

        public BlogItemsViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.blog_item_title);
            image = itemView.findViewById(R.id.blog_item_imageview);
            description = itemView.findViewById(R.id.blog_item_description);


        }
    }
}