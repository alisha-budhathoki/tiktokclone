package com.halfwaiter.lol.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.halfwaiter.lol.R;
import com.halfwaiter.lol.adapter.HomeAdapter;
import com.halfwaiter.lol.adapter.TrendingAdapter;
import com.halfwaiter.lol.model.HomeModel;
import com.halfwaiter.lol.model.TrendingModel;

import java.util.ArrayList;

public class TrendingFragment extends Fragment {
    private int[] trendingVideos = new int[]{R.drawable.bkgrnd2};
    private String[] trendingLiskes = new String[]{"John"};

    TrendingAdapter trendingAdapter;
    RecyclerView recyclerViewTrending;
    ArrayList<TrendingModel> mListTrending;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trending, container, false);

        mListTrending = seeNewTrending();

        recyclerViewTrending = view.findViewById(R.id.recycler_trending);
        trendingAdapter = new TrendingAdapter(getContext(), mListTrending);
        recyclerViewTrending.setAdapter(trendingAdapter);
        recyclerViewTrending.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));


        return view;
    }

    private ArrayList<TrendingModel> seeNewTrending() {
        ArrayList<TrendingModel> listTrending = new ArrayList<>();
        for (int i = 0; i < 1; i++) {
            TrendingModel trendingModel = new TrendingModel();
            trendingModel.setTrendingVideo(trendingVideos[i]);
            trendingModel.setNoTrendingLikes(trendingLiskes[i]);

            listTrending.add(trendingModel);
        }
        return listTrending;

    }
}