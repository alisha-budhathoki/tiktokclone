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
import com.halfwaiter.lol.model.CommentModel;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder>{
    List<CommentModel> mList;
    Context context;
    Boolean isLiked = false;

    public CommentAdapter(Context context, List<CommentModel> mList){
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public CommentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment, parent, false);
        CommentAdapter.MyViewHolder vh = new CommentAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final CommentAdapter.MyViewHolder holder, final int position) {
        final CommentModel commentModel =mList.get(position);
        holder.txtReplies.setText(commentModel.getUserReplies());
        holder.txtTimeCmnt.setText(commentModel.getComntTime());
        holder.txtContentCmnt.setText(commentModel.getUserComment());
        holder.txtNameCommenter.setText(commentModel.getUserName());
        holder.pic_commenter.setImageResource(commentModel.getUserPhoto());

        holder.rectComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLiked == false) {
                    isLiked = true;
                    holder.rectComment.setColorFilter(Color.rgb(255, 0, 0));
                } else {
                    isLiked = false;
                    holder.rectComment.setColorFilter(Color.rgb(0, 0, 0));
                }

            }
        });
//        holder.soundExploreLength.setText(exploreModel.getSoundLength());
//        holder.soundExploreName.setText(exploreModel.getSoundName());
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;
        ImageView rectComment, pic_commenter;
        TextView txtNameCommenter, txtContentCmnt, txtTimeCmnt, txtReplies;
        public MyViewHolder(View itemView) {
            super(itemView);
            rectComment = itemView.findViewById(R.id.image_love_cmnt);
            pic_commenter = itemView.findViewById(R.id.img_pic_cmntr);
            txtNameCommenter = itemView.findViewById(R.id.txt_cmntr_name);
            txtContentCmnt = itemView.findViewById(R.id.txt_comment_content);
            txtTimeCmnt = itemView.findViewById(R.id.txt_comment_time);
            txtReplies = itemView.findViewById(R.id.txt_replies);

//            soundExploreLength = itemView.findViewById(R.id.txt_sndlngth_explr);
//            soundExploreName = itemView.findViewById(R.id.txt_soundnm_explr);
//            reactExplore = itemView.findViewById(R.id.txt_like_explr);
        }
    }
}
