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
import androidx.recyclerview.widget.RecyclerView;

import com.halfwaiter.lol.R;
import com.halfwaiter.lol.model.FavouriteModel;

import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.MyViewHolder>{
    List<FavouriteModel> mList;
    Context context;

    public FavouriteAdapter(Context context, List<FavouriteModel> mList){
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public FavouriteAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favourite, parent, false);
        FavouriteAdapter.MyViewHolder vh = new FavouriteAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final FavouriteAdapter.MyViewHolder holder, final int position) {
        final FavouriteModel favouriteModel =mList.get(position);

        holder.favVideoImage.setImageResource(favouriteModel.getFavouriteImg());
        holder.noFavouriteLikes.setText(favouriteModel.getFavLikes());
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;
//        VideoView favouriteVideo;
        TextView noFavouriteLikes;
        ImageView favVideoImage;

        public MyViewHolder(View itemView) {
            super(itemView);
            favVideoImage = itemView.findViewById(R.id.iv_fvrt);
            noFavouriteLikes = itemView.findViewById(R.id.txt_likes_fvrt);
        }


    }

}
