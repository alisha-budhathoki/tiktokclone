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

import xyz.hanks.library.bang.SmallBangView;

public class ExploreAdapter extends RecyclerView.Adapter<ExploreAdapter.MyViewHolder> {
    List<ExploreModel> mList;
    Context context;
    Boolean isLiked;
    Boolean isPlayed = false;
    private int currentItem = -1;
    SmallBangView smallBangView;

    public ExploreAdapter(Context context, List<ExploreModel> mList) {
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

        final int[] mLastSelected = {-1};
//        smallBangView.addOnAttachStateChangeListener(this);
        holder.btnStop.setEnabled(mLastSelected[0] == -1 || mLastSelected[0] == position);
        final ExploreModel exploreModel = mList.get(position);
        holder.soundExploreLength.setText(exploreModel.getSoundLength());
        holder.soundExploreName.setText(exploreModel.getSoundName());

        holder.smallBangView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.smallBangView.isSelected()){
                    isLiked = false;
                    holder.smallBangView.likeAnimation();
                    holder.smallBangView.setSelected(false);
                }else{
                    isLiked = true;
                    holder.smallBangView.likeAnimation();
                    holder.smallBangView.setSelected(true);
                }
            }
        });
//        holder.reactExplore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isLiked == false) {
//                    isLiked = true;
//                    holder.reactExplore.setColorFilter(Color.rgb(255, 0, 0));
//                } else {
//                    isLiked = false;
//                    holder.reactExplore.setColorFilter(Color.rgb(0, 0, 0));
//                }
//
//            }
//        });

        if (currentItem != -1) {
            if (currentItem == position) {
                holder.btnStop.setVisibility(View.VISIBLE);
                holder.btnPlay.setVisibility(View.GONE);
            } else {
                holder.btnStop.setVisibility(View.GONE);
                holder.btnPlay.setVisibility(View.VISIBLE);
            }
        }

        holder.btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                currentItem = holder.getAdapterPosition();
                notifyItemChanged(currentItem);
                notifyDataSetChanged();

            }
        });

        holder.btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                holder.btnStop.setVisibility(View.GONE);
                holder.btnPlay.setVisibility(View.VISIBLE);

            }
        });

    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;
        ImageView  btnPlay, btnStop, like;
        TextView soundExploreLength, soundExploreName;
        SmallBangView smallBangView;

        public MyViewHolder(View itemView) {
            super(itemView);
            smallBangView = itemView.findViewById(R.id.smallbang);
            soundExploreLength = itemView.findViewById(R.id.txt_sndlngth_explr);
            soundExploreName = itemView.findViewById(R.id.txt_soundnm_explr);
//            reactExplore = itemView.findViewById(R.id.txt_like_explr);
            btnPlay = itemView.findViewById(R.id.fabMusicPlay);
            btnStop = itemView.findViewById(R.id.fabMusicStop);
        }
    }
}

