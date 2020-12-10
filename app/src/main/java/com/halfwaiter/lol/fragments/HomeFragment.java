package com.halfwaiter.lol.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import com.halfwaiter.lol.HomeActivity;
import com.halfwaiter.lol.R;
import com.halfwaiter.lol.adapter.HomeAdapter;
import com.halfwaiter.lol.model.HomeModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    Context context;
    private int[] usePic = new int[]{R.drawable.ic_profile_n, R.drawable.ic_profile_n, R.drawable.ic_profile_n, R.drawable.ic_profile_n, R.drawable.ic_profile_n};
    private int[] lolVideos = new int[]{R.raw.video5, R.raw.video5, R.raw.video5, R.raw.video5, R.raw.video5};
    private String[] usernames = new String[]{"John", "Johnny", "Johnny", "Johnny", "Johnny"};
    private String[] captions = new String[]{"John", "Johnny", "Johnny", "Johnny", "Johnny"};
    private String[] soundNames = new String[]{"Rock", "Sounds", "Music", "Pop", "Remix"};
    private int[] noLikes = new int[]{23, 25, 250, 245, 1};
    private String[] noComments = new String[]{"34", "45", "45k", "56.4k", "12.3k"};

    HomeAdapter homeAdapter;
    RecyclerView recyclerViewHome;
    ArrayList<HomeModel> mListHome;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerViewHome = view.findViewById(R.id.recyclerHome);

//        For only one item at a time in recycler view
        SnapHelper mSnapHelper = new PagerSnapHelper();
        mSnapHelper.attachToRecyclerView(recyclerViewHome);


        mListHome = seeNewSucessStories();
        homeAdapter = new HomeAdapter(getContext(), mListHome);
        recyclerViewHome.setAdapter(homeAdapter);
        recyclerViewHome.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));


//        final VideoView videoView = view.findViewById(R.id.myvideoview);


//        String videoPath = "android.resource://" + getContext().getPackageName() + "/" + R.raw.nextt_video;
//        Uri uri = Uri.parse(videoPath);
//        videoView.setVideoURI(uri);
//        videoView.start();
//        videoView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (videoView.isPlaying())
//                {
//                    videoView.pause();
//                }
//                else {
//                    videoView.start();
//                }
//            }
//        });

//        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//            @Override
//            public void onPrepared(MediaPlayer mp) {
//                mp.setOnSeekCompleteListener(new MediaPlayer.OnSeekCompleteListener() {
//                    @Override
//                    public void onSeekComplete(MediaPlayer mp) {
//                        if (videoView.isPlaying()){
//                            System.out.println("nksnj");
//                            mp.pause();
//                            videoView.pause();
//
//                        }
//                        else{
//                            mp.start();
//                            System.out.println("kasdbjs");
//                            videoView.start();
//                        }
//                    }
//                });
//
//                // so it fits on the screen
//                int videoWidth = mp.getVideoWidth();
//                int videoHeight = mp.getVideoHeight();
//                float videoProportion = (float) videoWidth / (float) videoHeight;
//
//
//                DisplayMetrics mDisplayMetrics = new DisplayMetrics();
//                getActivity().getWindowManager().getDefaultDisplay().getMetrics(mDisplayMetrics);
//
//                float screenWidth = mDisplayMetrics.widthPixels;
//                float screenHeight = mDisplayMetrics.heightPixels;
//
//                float screenProportion = (float) screenWidth / (float) screenHeight;
//                android.view.ViewGroup.LayoutParams lp = videoView.getLayoutParams();
//
//                if (videoProportion > screenProportion) {
//                    lp.width = (int) screenWidth;
//                    lp.height = (int) ((float) screenWidth / videoProportion);
//                } else {
//                    lp.width = (int) (videoProportion * (float) screenHeight);
//                    lp.height = (int) screenHeight;
//                }
//
//                videoView.setLayoutParams(lp);
//            }
//        });

//        videoView.setMediaController(null);
        return view;
    }

    private ArrayList<HomeModel> seeNewSucessStories() {
        ArrayList<HomeModel> listSucess = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            HomeModel homeModel = new HomeModel();
            homeModel.setLolVideo(lolVideos[i]);
            homeModel.setUserImage(usePic[i]);
            homeModel.setUsername(usernames[i]);
            homeModel.setCaption(captions[i]);
            homeModel.setNoComments(noComments[i]);
            homeModel.setNoLikes(noLikes[i]);
            homeModel.setSoundName(soundNames[i]);

            listSucess.add(homeModel);
        }
        return listSucess;

    }
}