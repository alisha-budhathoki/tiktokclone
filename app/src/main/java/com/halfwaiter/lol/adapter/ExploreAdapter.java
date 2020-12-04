package com.halfwaiter.lol.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.halfwaiter.lol.R;
import com.halfwaiter.lol.model.ExploreModel;
import com.halfwaiter.lol.model.TrendingModel;

import java.util.List;

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.MyViewHolder>{
    List<ExploreModel> mList;
    Context context;
    Boolean isLiked = false;

    public ExploreAdapter(Context context, List<ExploreModel> mList){
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public ExploreAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_explore, parent, false);
        ExploreAdapter.MyViewHolder vh = new ExploreAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ExploreAdapter.MyViewHolder holder, final int position) {
        final ExploreModel exploreModel =mList.get(position);
        holder.soundExploreLength.setText(exploreModel.getSoundLength());
        holder.soundExploreName.setText(exploreModel.getSoundName());
        holder.reactExplore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLiked == false) {
                    isLiked =true;
                    holder.reactExplore.setColorFilter(Color.rgb(255, 0, 0));
                }
                else {
                    isLiked = false;
                    holder.reactExplore.setColorFilter(Color.rgb(0, 0, 0));
                }

            }
        });
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;
        ImageView reactExplore;
        TextView soundExploreLength, soundExploreName;
        public MyViewHolder(View itemView) {
            super(itemView);
            soundExploreLength = itemView.findViewById(R.id.txt_sndlngth_explr);
            soundExploreName = itemView.findViewById(R.id.txt_soundnm_explr);
            reactExplore = itemView.findViewById(R.id.txt_like_explr);
        }
    }
}

