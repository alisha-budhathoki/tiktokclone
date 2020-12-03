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
import com.halfwaiter.lol.adapter.TrendingAdapter;
import com.halfwaiter.lol.model.BookmarkModel;
import com.halfwaiter.lol.model.TrendingModel;

import java.util.ArrayList;

public class BookmarkedFragment extends Fragment {
    private String[] soundLength = new String[]{"00:15", "01:16"};
    private String[] soundName = new String[]{"Sound name", "sound name"};
    private String[] catName = new String[]{"Romantic", "Pop"};

    BookmarkedAdapter bookmarkedAdapter;
    RecyclerView recyclerViewBookmark;
    ArrayList<BookmarkModel> mListBookmark;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bookmark, container, false);


        recyclerViewBookmark = view.findViewById(R.id.recycler_bookmark);
        mListBookmark = seeBookmarkData();
        bookmarkedAdapter = new BookmarkedAdapter(getContext(), mListBookmark);
        recyclerViewBookmark.setAdapter(bookmarkedAdapter);
        recyclerViewBookmark.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));


        return view;
    }
    private ArrayList<BookmarkModel> seeBookmarkData() {
        ArrayList<BookmarkModel> listBookmark = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            BookmarkModel bookmarkModel = new BookmarkModel();
            System.out.println("sjhdhjbd"+soundName[0]+soundName[1]);
            bookmarkModel.setSoundLengthBookmark(soundLength[i]);
            bookmarkModel.setSoundNameBookmark(soundName[i]);

            listBookmark.add(bookmarkModel);
        }
        return listBookmark;

    }
}
