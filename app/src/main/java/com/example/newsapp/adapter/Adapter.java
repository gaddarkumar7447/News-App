package com.example.newsapp.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsCallback;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newsapp.R;
import com.example.newsapp.WebView;
import com.example.newsapp.model.Model;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    Context context;
    ArrayList<Model> modelArrayList;

    public Adapter(Context context, ArrayList<Model> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item, parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(context, WebView.class);
                intent.putExtra("url", modelArrayList.get(position).getUrl());
                context.startActivity(intent);*/

                String url = modelArrayList.get(position).getUrl();
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                builder.setShowTitle(true);
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(context, Uri.parse(url));
            }
        });

        holder.mTime.setText("Published At: "+modelArrayList.get(position).getPublishedAt());
        holder.mAuthor.setText("Author: "+modelArrayList.get(position).getAuthor());
        holder.mMainHeading.setText(modelArrayList.get(position).getTitle());
        holder.mContenent.setText(modelArrayList.get(position).getDescription());
        Glide.with(context).load(modelArrayList.get(position).getUrlToImage()).into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mMainHeading, mContenent, mAuthor, mTime;
        ImageView mImageView;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mMainHeading = itemView.findViewById(R.id.mainHeading);
            mContenent = itemView.findViewById(R.id.contenent);
            mAuthor = itemView.findViewById(R.id.author);
            mTime = itemView.findViewById(R.id.time);
            mImageView = itemView.findViewById(R.id.imageView);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
