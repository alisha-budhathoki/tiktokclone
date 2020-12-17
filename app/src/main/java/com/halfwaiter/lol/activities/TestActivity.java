package com.halfwaiter.lol.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;

import com.github.hiteshsondhi88.libffmpeg.ExecuteBinaryResponseHandler;
import com.github.hiteshsondhi88.libffmpeg.FFmpeg;
import com.github.hiteshsondhi88.libffmpeg.exceptions.FFmpegCommandAlreadyRunningException;
import com.halfwaiter.lol.R;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class TestActivity extends AppCompatActivity {
    String outputFilePath = "/storage/emulated/0/Pictures/LOL/VID_20201216_140806.mp4";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        String videpath = "/storage/emulated/0/Pictures/LOL/VID_20201216_140806.mp4";
        String audioPath = "/storage/emulated/0/Download/I Wanna Grow Old With You - Westlife.mp3";
        System.out.println("snjsjdb");
        try {
            FFmpeg ffmpeg = FFmpeg.getInstance(this);
            String cmd = "-i " + videpath + " -i " + audioPath + " -shortest -threads 0 -preset ultrafast -strict -2 " + outputFilePath;
            ffmpeg.execute(new String[]{cmd}, mergeListener);
            System.out.println("nbss");
        } catch (FFmpegCommandAlreadyRunningException e) {
            e.printStackTrace();
            System.out.println("sdjbsd");
        }


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