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
import com.halfwaiter.lol.adapter.BookmarkedAdapter;
import com.halfwaiter.lol.adapter.ExploreAdapter;
import com.halfwaiter.lol.model.BookmarkModel;
import com.halfwaiter.lol.model.ExploreModel;

import java.util.ArrayList;

public class ExploreFragment extends Fragment {
    private String[] soundLength = new String[]{"00:15", "01:16"};
    private String[] soundName = new String[]{"Sound name", "sound name"};
    private String[] catName = new String[]{"Romantic", "Pop"};

    ExploreAdapter exploreAdapter;
    RecyclerView recyclerviewExplore;
    ArrayList<ExploreModel> mListExplore;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore, container, false);

        recyclerviewExplore = view.findViewById(R.id.recycler_explore);
        mListExplore = seeBookmarkData();
        exploreAdapter = new ExploreAdapter(getContext(), mListExplore);
        recyclerviewExplore.setAdapter(exploreAdapter);
        recyclerviewExplore.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        return view;
    }

    private ArrayList<ExploreModel> seeBookmarkData() {
        ArrayList<ExploreModel> listExplore = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            ExploreModel exploreModel = new ExploreModel();
            System.out.println("sjhdhjbd"+soundName[0]+soundName[1]);
            exploreModel.setSoundLength(soundLength[i]);
            exploreModel.setSoundName(soundName[i]);

            listExplore.add(exploreModel);
        }
        return listExplore;

    }

}