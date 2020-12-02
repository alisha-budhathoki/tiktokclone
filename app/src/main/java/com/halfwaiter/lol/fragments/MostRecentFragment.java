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
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.halfwaiter.lol.R;
import com.halfwaiter.lol.adapter.FavouriteAdapter;
import com.halfwaiter.lol.adapter.HomeAdapter;
import com.halfwaiter.lol.adapter.MostRecentAdapter;
import com.halfwaiter.lol.model.HomeModel;
import com.halfwaiter.lol.model.MostRecentModel;

import java.util.ArrayList;

public class MostRecentFragment extends Fragment {
    private int[] recentVideos = new int[]{R.drawable.index, R.drawable.bkgrnd2
            , R.drawable.bkgrnd2, R.drawable.index, R.drawable.index, R.drawable.bkgrnd2};
    private String[] recentLikes = new String[]{"5k", "2k", "10k", "2k", "9k", "4k"};

    MostRecentAdapter mostRecentAdapter;
    RecyclerView recyclerViewRecent;
    ArrayList<MostRecentModel> mListRecent;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_most_recent, container, false);
        recyclerViewRecent = view.findViewById(R.id.recycler_recent);

        mListRecent = seeNewRecentVideos();
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        recyclerViewRecent.setHasFixedSize(true);
        recyclerViewRecent.setLayoutManager(layoutManager);
        mostRecentAdapter = new MostRecentAdapter(getContext(), mListRecent);
        recyclerViewRecent.setAdapter(mostRecentAdapter);

        return view;
    }

    private ArrayList<MostRecentModel> seeNewRecentVideos() {
        ArrayList<MostRecentModel> listMostRecent = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            MostRecentModel recentModel = new MostRecentModel();
            recentModel.setRecentVideo(recentVideos[i]);
            recentModel.setNoLikes(recentLikes[i]);
            listMostRecent.add(recentModel);
        }
        return listMostRecent;

    }
}
