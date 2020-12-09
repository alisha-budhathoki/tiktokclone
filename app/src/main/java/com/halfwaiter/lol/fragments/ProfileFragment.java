package com.halfwaiter.lol.fragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

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
    ImageView icThreedots;

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

        icThreedots = view.findViewById(R.id.iv_three_dots);
        icThreedots.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                getActivity().openOptionsMenu();
                System.out.println("sjbd");
                showPopup(v);
            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.example_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.one:
                Toast.makeText(getContext(), "Item 1 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.two:
                Toast.makeText(getContext(), "Item 2 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.three:
                Toast.makeText(getContext(), "Item 3 selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getActivity().getMenuInflater();
//        inflater.inflate(R.menu.example_menu, menu);
//        return true;
//    }

    private void showPopup(final View anchor) {
        PopupMenu popupMenu = new PopupMenu(new ContextThemeWrapper(anchor.getContext(), R.style.CustomPopupTheme), anchor);
        popupMenu.inflate(R.menu.example_menu);
//        PopupMenu popup = new PopupMenu(new ContextThemeWrapper(getActivity(), R.style.CustomPopupTheme), anchor);
//        popupMenu.getMenu().add(R.id.menuGroup, R.id.menu1, Menu.NONE, "slot1");
//        popupMenu.getMenu().add(R.id.menuGroup, R.id.menu2, Menu.NONE,"slot2");
//        popupMenu.getMenu().add(R.id.menuGroup, R.id.menu3, Menu.NONE,"slot3");
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(anchor.getContext(), item.getTitle() + "clicked", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        popupMenu.show();
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