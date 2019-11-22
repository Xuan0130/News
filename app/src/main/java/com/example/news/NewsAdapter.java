package com.example.news;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private Context context;
    private List<News> newsList = new ArrayList<>();

    public NewsAdapter(Context context) {
        this.context = context;

    }

    public void setList(List<News> newsList){
        this.newsList.clear();
        this.newsList.addAll(newsList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final News news = newsList.get(position);
        holder.newsTitle.setText(news.getTitle());
        holder.newsText.setText(news.getText());
        holder.newsDate.setText(news.getDate());
        Glide.with(context).load(news.getImage_url()).into(holder.newsImage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(news.getNews_url());
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(intent);
            }
        });

    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView newsTitle;
        ImageView newsImage;
        TextView newsDate;
        TextView newsText;

        public ViewHolder(View itemView) {
            super(itemView);
            newsImage = itemView.findViewById(R.id.news_img);
            newsTitle = itemView.findViewById(R.id.news_title);
            newsDate = itemView.findViewById(R.id.news_date);
            newsText = itemView.findViewById(R.id.news_text);
        }
    }
}
