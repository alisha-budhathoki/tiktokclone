package com.halfwaiter.lol.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.halfwaiter.lol.R;
import com.halfwaiter.lol.model.TimeSecond;

import java.util.List;

public class TimeLengthAdapter extends RecyclerView.Adapter<TimeLengthAdapter.MyViewHolder>{
    List<TimeSecond> mList;
    Context context;

    public TimeLengthAdapter(Context context, List<TimeSecond> mList){
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_time_length, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final TimeSecond sucessStories =mList.get(position);
        holder.timeLength.setText(sucessStories.gettTimeTxt());
        holder.timeLength.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("dbhdh");
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerViewTime;
        TextView timeLength;
        public MyViewHolder(View itemView) {
            super(itemView);

            timeLength = itemView.findViewById(R.id.txtTimeLength);
        }


    }
}
