package com.halfwaiter.lol.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import com.halfwaiter.lol.R;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        final VideoView videoView = view.findViewById(R.id.myvideoview);

        String videoPath = "android.resource://" + getContext().getPackageName() + "/" + R.raw.next_video;
        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);
        videoView.start();
        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoView.isPlaying())
                {
                    System.out.println("asdbjh");
                    videoView.pause();
                }
                else {
                    System.out.println("skjadbhjsdh");
                    videoView.start();
                }
            }
        });
        videoView.setMediaController(null);
        return view;
    }
}