package com.steven.example.shimmerloadexample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.steven.example.shimmerloadexample.R;
import com.steven.example.shimmerloadexample.model.Movie;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    public Context mContext;
    public List<Movie> mMovieList = new ArrayList<>();

    public RecyclerViewAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(mContext).load(mMovieList.get(position).Poster).into(holder.mCover);
        holder.mTitle.setText(mMovieList.get(position).Title);
        holder.mYear.setText(mMovieList.get(position).Year);
        holder.mType.setText(mMovieList.get(position).Type);
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

    public void addItems(List<Movie> movies) {
        this.mMovieList = movies;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mCover;
        private TextView mTitle, mYear, mType;

        public ViewHolder(View itemView) {
            super(itemView);
            mCover = itemView.findViewById(R.id.cover_image);
            mTitle = itemView.findViewById(R.id.title);
            mYear = itemView.findViewById(R.id.year);
            mType = itemView.findViewById(R.id.type);
        }
    }
}
