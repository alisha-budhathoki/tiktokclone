package com.halfwaiter.lol.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import com.github.hiteshsondhi88.libffmpeg.ExecuteBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.github.hiteshsondhi88.libffmpeg.LoadBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegNotSupportedException;
import com.halfwaiter.lol.R;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestActivity extends AppCompatActivity {
    String outputFilePath = "/storage/emulated/0/Pictures/LOL/VID_20201216_140806.mp4";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        File file = new File("resources/video5.mp4");
        String absolutePath = file.getAbsolutePath();

        System.out.println("amsjdhbjs" + getInternalDirectoryPath());
        String Video = getInternalDirectoryPath() + "/video5.mp4";
        String Audio = getInternalDirectoryPath() + "/my_mp32.mp3";
        String output = getInternalDirectoryPath() + "/video5.mp4";

        String videpath = "/storage/emulated/0/Pictures/LOL/VID_20201216_140806.mp4";
        String audioPath = "/storage/emulated/0/Download/I Wanna Grow Old With You - Westlife.mp3";
        System.out.println("snjsjdb");
        try {
            Uri pathUri;
            File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES), "LOLin");

            if (!mediaStorageDir.exists()) {
                System.out.println("msndsn d");
                mediaStorageDir.mkdir();
                if (!mediaStorageDir.mkdirs()) {
                    Log.d("MyCameraApp", "failed to create directory");
                    return;
                }
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String i = sdf.format(new Date());

            File mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_12" + i + ".mp4");

            FFmpeg ffmpeg = FFmpeg.getInstance(this);
            String cmd = "-i " + Video + " -i " + Audio + " -shortest -threads 0 -preset ultrafast -strict -2 " + mediaFile;
            ffmpeg.loadBinary(new LoadBinaryResponseHandler() {
                @Override
                public void onStart() {
                    System.out.println("sdaknjs");
                }

                @Override
                public void onFailure() {
                    System.out.println("nbshsb");
                }

                @Override
                public void onSuccess() {
                    System.out.println("ashgsh");
                }

                @Override
                public void onFinish() {
                    System.out.println("s12jdbd");
                }
            });
            ffmpeg.execute(new String[]{String.valueOf(cmd)}, mergeListener);
            System.out.println("nbss");
        } catch (FFmpegCommandAlreadyRunningException | FFmpegNotSupportedException e) {
            e.printStackTrace();
            System.out.println("sdjbsd");
        }


    }

    private String getInternalDirectoryPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    ExecuteBinaryResponseHandler mergeListener = new ExecuteBinaryResponseHandler() {
        @Override
        public void onStart() {
            System.out.println("jnssjdb");
            //started
        }

        @Override
        public void onFailure(String message) {
            System.out.println("asbhsd");
            //failed
        }

        @Override
        public void onFinish() {
            File output = new File(outputFilePath);
            //Do whatever with your muxed file
            System.out.println("sadjhshbjs");
        }
    };
}