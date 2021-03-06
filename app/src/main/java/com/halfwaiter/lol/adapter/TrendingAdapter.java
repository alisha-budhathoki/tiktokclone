package com.halfwaiter.lol.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.halfwaiter.lol.R;
import com.halfwaiter.lol.model.HomeModel;
import com.halfwaiter.lol.model.TrendingModel;

import java.util.List;

public class TrendingAdapter extends RecyclerView.Adapter<TrendingAdapter.MyViewHolder>{
    List<TrendingModel> mList;
    Context context;

    public TrendingAdapter(Context context, List<TrendingModel> mList){
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public TrendingAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_trending, parent, false);
        TrendingAdapter.MyViewHolder vh = new TrendingAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final TrendingAdapter.MyViewHolder holder, final int position) {
        final TrendingModel trendingModel =mList.get(position);
        holder.trendingVideoImage.setImageResource(trendingModel.getTrendingVideo());
        holder.noTrendingLikes.setText(trendingModel.getNoTrendingLikes());
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;
        ImageView trendingVideoImage;
        TextView noTrendingLikes;
        public MyViewHolder(View itemView) {
            super(itemView);
            trendingVideoImage = itemView.findViewById(R.id.video_trending);
            noTrendingLikes = itemView.findViewById(R.id.txt_likes_trending);
        }


    }


}

