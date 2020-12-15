package com.halfwaiter.lol.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.halfwaiter.lol.R;

public class VideoPlayerActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;
    Uri file;
    VideoView videoView;
    ImageView imgOk, imgCancel;

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

        imgOk = findViewById(R.id.ic_ok);
        imgCancel = findViewById(R.id.ic_cancel);

        imgCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VideoPlayerActivity.this);
                builder.setMessage("Discard the last clip?")
                        .setCancelable(false)
                        .setPositiveButton("Discard", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                VideoPlayerActivity.this.finish();
                            }
                        })
                        .setNegativeButton("Keep", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });

    }
}