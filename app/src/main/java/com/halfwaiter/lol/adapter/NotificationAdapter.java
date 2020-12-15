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
import com.halfwaiter.lol.model.BookmarkModel;
import com.halfwaiter.lol.model.NotificationModel;

import java.util.List;


public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.MyViewHolder> {
    List<NotificationModel> mList;
    Context context;
    Boolean isLiked = false;


    public NotificationAdapter(Context context, List<NotificationModel> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public NotificationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);
        NotificationAdapter.MyViewHolder vh = new NotificationAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final NotificationAdapter.MyViewHolder holder, final int position) {
        final NotificationModel notificationModel = mList.get(position);
        holder.notifcationImg.setImageResource(notificationModel.getNotificationPic());
        holder.notificationName.setText(notificationModel.getNotificationTitle());
        holder.notificationContent.setText(notificationModel.getNotificationContent());
//        holder.soundname.setText(notificationModel.getSoundNameBookmark());

    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView notificationName, notificationContent;
        ImageView notifcationImg;


        public MyViewHolder(View itemView) {
            super(itemView);
            notificationName = itemView.findViewById(R.id.txt_notifctn_name);
            notificationContent = itemView.findViewById(R.id.txt_notification_cntnt);
            notifcationImg = itemView.findViewById(R.id.img_notifctn);
        }


    }

}
