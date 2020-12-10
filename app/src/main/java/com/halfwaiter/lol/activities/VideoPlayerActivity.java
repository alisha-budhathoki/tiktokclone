package com.halfwaiter.lol.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.VideoView;

import com.halfwaiter.lol.R;

public class VideoPlayerActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    Uri file;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        videoView = findViewById(R.id.preview_video);
        Uri videoUri = getIntent().getData();
//        txtVideoName.setText((CharSequence) videoUri);
        Bundle bundle = getIntent().getExtras();
        String vidPath = bundle.getString("videoUri");
        Uri uri = Uri.parse(vidPath);
        videoView.setVideoURI(uri);
        videoView.start();


    }
}