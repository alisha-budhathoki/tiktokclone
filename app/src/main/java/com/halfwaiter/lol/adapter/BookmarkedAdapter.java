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
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.halfwaiter.lol.R;
import com.halfwaiter.lol.model.BookmarkModel;
import com.halfwaiter.lol.model.TrendingModel;

import java.util.List;

public class BookmarkedAdapter extends Adapter<BookmarkedAdapter.MyViewHolder> {
    List<BookmarkModel> mList;
    Context context;
    Boolean isLiked = false;


    public BookmarkedAdapter(Context context, List<BookmarkModel> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public BookmarkedAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bookmark, parent, false);
        BookmarkedAdapter.MyViewHolder vh = new BookmarkedAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final BookmarkedAdapter.MyViewHolder holder, final int position) {
        final BookmarkModel bookmarkModel = mList.get(position);
        holder.soundname.setText(bookmarkModel.getSoundNameBookmark());
        holder.soundlength.setText(bookmarkModel.getSoundLengthBookmark());
            holder.reactBookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isLiked == false) {
                        isLiked =true;
                        holder.reactBookmark.setColorFilter(Color.rgb(255, 0, 0));
                    }
                    else {
                        isLiked = false;
                        holder.reactBookmark.setColorFilter(Color.rgb(0, 0, 0));
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
        TextView soundname, soundlength;
        Boolean isReactedBookmark;
        ImageView reactBookmark;

        public MyViewHolder(View itemView) {
            super(itemView);
            soundname = itemView.findViewById(R.id.txt_sound_nameb);
            soundlength = itemView.findViewById(R.id.txt_sound_lengthb);
            reactBookmark = itemView.findViewById(R.id.txt_like_b);
        }


    }


}
