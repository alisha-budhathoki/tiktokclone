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
import com.halfwaiter.lol.model.MostRecentModel;

import java.io.IOException;
import java.util.List;

public class MostRecentAdapter extends RecyclerView.Adapter<MostRecentAdapter.MyViewHolder> {
    List<MostRecentModel> mList;
    Context context;

    public MostRecentAdapter(Context context, List<MostRecentModel> mList){
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MostRecentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_most_recent, parent, false);
        MostRecentAdapter.MyViewHolder vh = new MostRecentAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final MostRecentAdapter.MyViewHolder holder, final int position) {
        final MostRecentModel mostRecentModel =mList.get(position);

        holder.recentVideo.setImageResource(mostRecentModel.getRecentVideo());
        System.out.println("dmjbass"+mostRecentModel.getRecentVideo());
        holder.noRecentLikes.setText(mostRecentModel.getNoLikes());
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;
        ImageView recentVideo;
        ConstraintLayout constraintLayout;
        //        VideoPlayerView videoPlayerView;
        TextView noRecentLikes, captions, soundName, noLike, noComment;
        //        CardView cardViewSucess;
        public MyViewHolder(View itemView) {
            super(itemView);
            recentVideo = itemView.findViewById(R.id.recent_video_view);
            noRecentLikes = itemView.findViewById(R.id.txt_loveRect);
        }


    }

}

