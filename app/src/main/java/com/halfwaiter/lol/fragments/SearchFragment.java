package com.halfwaiter.lol.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import com.halfwaiter.lol.R;

public class SearchFragment extends Fragment {
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
//        VideoView videoView = view.findViewById(R.id.myvideoview);
//        String videoPath = "android.resource://" + getContext().getPackageName() + "/" + R.raw.next_video;
//        Uri uri = Uri.parse(videoPath);
//        videoView.setVideoURI(uri);
//        videoView.start();
//
//        videoView.setMediaController(null);
//        MediaController mediaController = new MediaController(getContext());
//        videoView.setMediaController(mediaController);
//        mediaController.setAnchorView(videoView);

//        android.widget.LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) videoView.getLayoutParams();
//        int width = getResources().getConfiguration().screenWidthDp;
//        int height = getResources().getConfiguration().screenHeightDp;
//        System.out.println("jbsajhasdh"+ width+ height);
//        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){}
//        params.leftMargin = 0;
//        videoView.setLayoutParams(params);
        return view;
    }
}