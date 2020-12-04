package com.halfwaiter.lol.adapter;

import android.content.Context;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.halfwaiter.lol.R;
import com.halfwaiter.lol.model.HomeModel;
import com.volokh.danylo.video_player_manager.manager.PlayerItemChangeListener;
import com.volokh.danylo.video_player_manager.manager.SingleVideoPlayerManager;
import com.volokh.danylo.video_player_manager.manager.VideoPlayerManager;
import com.volokh.danylo.video_player_manager.meta.MetaData;
import com.volokh.danylo.video_player_manager.ui.SimpleMainThreadMediaPlayerListener;
import com.volokh.danylo.video_player_manager.ui.VideoPlayerView;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
    List<HomeModel> mList;
    Context context;
    Boolean isLiked = false;
//    VideoPlayerManager<MetaData> mVideoPlayerManager = new SingleVideoPlayerManager(new PlayerItemChangeListener() {
//        @Override
//        public void onPlayerItemChanged(MetaData metaData) {
//
//        }
//    });

    public HomeAdapter(Context context, List<HomeModel> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final HomeModel homeModel = mList.get(position);

//        holder.lolVideo.setBackgroundColor(Color.WHITE);
        holder.lolVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            @Override
            public void onPrepared(MediaPlayer mp) {
                System.out.println("shbjhs");
                holder.lolVideo.start();
//                holder.lolVideo.setBackgroundColor(Color.TRANSPARENT);
            }
        });
        String videoPath = "android.resource://" + context.getPackageName() + "/" + homeModel.getLolVideo();
        Uri uri = Uri.parse(videoPath);
        holder.lolVideo.setVideoURI(uri);
        holder.lolVideo.start();
        holder.userImage.setImageResource(homeModel.getUserImage());
        holder.username.setText(homeModel.getUsername());
        holder.soundName.setText(homeModel.getSoundName());
        holder.captions.setText(homeModel.getCaption());
        holder.noLike.setText(homeModel.getNoLikes());
        holder.noComment.setText(homeModel.getNoComments());
        holder.icLove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLiked == false) {
                    isLiked =true;
                    holder.icLove.setColorFilter(Color.rgb(255, 0, 0));
                }
                else {
                    isLiked = false;
                    holder.icLove.setColorFilter(Color.rgb(255, 255, 255));
                }

            }
        });
        holder.relPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.lolVideo.isPlaying()) {
                    System.out.println("sdhj");
                    holder.lolVideo.pause();
                } else {
                    System.out.println("sjbhsd");
                    holder.lolVideo.start();
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
        ImageView userImage, icLove;
        VideoView lolVideo;
        ConstraintLayout constraintLayout;
        RelativeLayout relPlayPause;
        //        VideoPlayerView videoPlayerView;
        TextView username, captions, soundName, noLike, noComment;

        //        CardView cardViewSucess;
        public MyViewHolder(View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.constraintMain);
            icLove = itemView.findViewById(R.id.iv_love);
            lolVideo = itemView.findViewById(R.id.myvideoview);
            userImage = (ImageView) itemView.findViewById(R.id.ivRoundProfile);
            username = itemView.findViewById(R.id.txtUsrname);
            captions = itemView.findViewById(R.id.txtCaption);
            soundName = itemView.findViewById(R.id.txtSiundName);
            noLike = itemView.findViewById(R.id.txtLove);
            noComment = itemView.findViewById(R.id.txtComment);
            relPlayPause = itemView.findViewById(R.id.video_play_pause);

        }


    }

}
