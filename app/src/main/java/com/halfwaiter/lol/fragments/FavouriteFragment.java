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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.halfwaiter.lol.R;
import com.halfwaiter.lol.adapter.FavouriteAdapter;
import com.halfwaiter.lol.adapter.HomeAdapter;
import com.halfwaiter.lol.model.FavouriteModel;
import com.halfwaiter.lol.model.HomeModel;

import java.util.ArrayList;

public class FavouriteFragment extends Fragment {
    private int[] favrtVideoPic =new int[]{R.drawable.index, R.drawable.bkgrnd2,  R.drawable.index, R.drawable.index, R.drawable.bkgrnd2};
    private String[] favNoLikes = new String[]{"2k", "1k", "6k", "2k", "1k"};

    FavouriteAdapter favouriteAdapter;
    RecyclerView recyclerViewFvrt;
    ArrayList<FavouriteModel> mListFvrtModl;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite, container, false);
        recyclerViewFvrt = view.findViewById(R.id.recycler_fvrt);

        mListFvrtModl = seeFavourites();
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
                recyclerViewFvrt.setHasFixedSize(true);
        recyclerViewFvrt.setLayoutManager(layoutManager);
        favouriteAdapter = new FavouriteAdapter(getContext(), mListFvrtModl);
        recyclerViewFvrt.setAdapter(favouriteAdapter);

        return view;
    }

    private ArrayList<FavouriteModel> seeFavourites() {
        ArrayList<FavouriteModel> listFavourites = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            FavouriteModel favouriteModel = new FavouriteModel();
            favouriteModel.setFavLikes(favNoLikes[i]);
            favouriteModel.setFavouriteImg(favrtVideoPic[i]);

            listFavourites.add(favouriteModel);
        }
        return listFavourites;

    }
}