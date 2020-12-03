package com.halfwaiter.lol.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.halfwaiter.lol.R;
import com.halfwaiter.lol.model.MostRecentModel;
import com.halfwaiter.lol.model.ProfileModel;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.MyViewHolder> {
    List<ProfileModel> mList;
    Context context;

    public ProfileAdapter(Context context, List<ProfileModel> mList){
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public ProfileAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_profile_videopic, parent, false);
        ProfileAdapter.MyViewHolder vh = new ProfileAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ProfileAdapter.MyViewHolder holder, final int position) {
        final ProfileModel profileModel =mList.get(position);

        holder.profileDisplayPic.setImageResource(profileModel.getProfilePicDsiplay());
        holder.noProfileLikes.setText(profileModel.getNoLikesProfile());
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView profileDisplayPic;
        TextView noProfileLikes;
        public MyViewHolder(View itemView) {
            super(itemView);
            profileDisplayPic = itemView.findViewById(R.id.iv_profile_pic);
            noProfileLikes = itemView.findViewById(R.id.txt_profile_rect);
        }


    }

}

