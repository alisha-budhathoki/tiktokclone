package com.halfwaiter.lol.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.halfwaiter.lol.R;
import com.halfwaiter.lol.adapter.MostRecentAdapter;
import com.halfwaiter.lol.adapter.ProfileAdapter;
import com.halfwaiter.lol.model.MostRecentModel;
import com.halfwaiter.lol.model.ProfileModel;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {
    private int[] profileDisplayPic = new int[]{R.drawable.index, R.drawable.bkgrnd2, R.drawable.bkgrnd2, R.drawable.index, R.drawable.index, R.drawable.bkgrnd2};
    private String[] profilePicLikes = new String[]{"5k", "2k", "10k", "2k", "9k", "4k"};

    ProfileAdapter profileAdapter;
    RecyclerView recylerViewProfile;
    ArrayList<ProfileModel> mListProfile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        recylerViewProfile = view.findViewById(R.id.recycler_profile);

        mListProfile = seeProfileData();
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
        recylerViewProfile.setHasFixedSize(true);
        recylerViewProfile.setLayoutManager(layoutManager);
        profileAdapter = new ProfileAdapter(getContext(), mListProfile);
        recylerViewProfile.setAdapter(profileAdapter);

        return view;
    }

    private ArrayList<ProfileModel> seeProfileData() {
        ArrayList<ProfileModel> listProfile = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            ProfileModel recentModel = new ProfileModel();
            recentModel.setProfilePicDsiplay(profileDisplayPic[i]);
            recentModel.setNoLikesProfile(profilePicLikes[i]);
            listProfile.add(recentModel);
        }
        return listProfile;
    }
}