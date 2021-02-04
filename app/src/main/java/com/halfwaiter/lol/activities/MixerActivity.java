package com.halfwaiter.lol.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetFileDescriptor;
import android.media.MediaCodec;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaMuxer;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.downloader.Error;
import com.downloader.OnDownloadListener;
import com.downloader.PRDownloader;
import com.halfwaiter.lol.R;

import org.mp4parser.Container;
import org.mp4parser.muxer.Movie;
import org.mp4parser.muxer.Track;
import org.mp4parser.muxer.builder.DefaultMp4Builder;
import org.mp4parser.muxer.container.mp4.MovieCreator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.HashMap;

public class MixerActivity extends AppCompatActivity {
    String root = Environment.getExternalStorageDirectory().toString();
    String audio = "/storage/emulated/0/Pictures/LOL/audiosample.aac";
    String video = "/storage/emulated/0/Pictures/LOL/WIN_20210108_07_48_37_Pro.mp4";
    String output = root + "/ouputVideo5.mp4";
    MediaPlayer audio1 = new MediaPlayer();
    VideoView videoView;
    public String parentPath = "";
    TextView clickAction;


//    mux(video, audio, output);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mixer);
        clickAction = findViewById(R.id.textClick);
        System.out.println("File: " + "audio:" + audio + "video: " + video + "out: " + output);
        videoView = findViewById(R.id.play_video);
        Uri uri = Uri.parse(video);
        videoView.setVideoURI(uri);
        videoView.start();
        PRDownloader.initialize(getApplicationContext());
        downLoadMusic("https://dl.espressif.com/dl/audio/gs-16b-2c-44100hz.aac");

        clickAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (audio1!= null){
                    Toast.makeText(MixerActivity.this, "Audio click started", Toast.LENGTH_SHORT).show();
                    audio1.start();
                    Toast.makeText(MixerActivity.this, "Music will start now", Toast.LENGTH_SHORT).show();
                    }
            }
        });
//        muxing(output, audio, video);

    }

    private void muxing( String outputFile,  String audioFile, String videoFile) {

//        String outputFile = "";

        try {

//            File file = new File(Environment.getExternalStorageDirectory()File.separator"final2.mp4");
//            file.createNewFile();
//            outputFile = file.getAbsolutePath();

            MediaExtractor videoExtractor = new MediaExtractor();
            videoExtractor.setDataSource(videoFile);
//            AssetFileDescriptor afdd = getAssets().openFd("Produce.MP4");
//            videoExtractor.setDataSource(afdd.getFileDescriptor(), afdd.getStartOffset(), afdd.getLength());

            MediaExtractor audioExtractor = new MediaExtractor();
            audioExtractor.setDataSource(audioFile);

            Log.d("apple", "Video Extractor Track Count " + videoExtractor.getTrackCount());
            Log.d("apple", "Audio Extractor Track Count " + audioExtractor.getTrackCount());

            MediaMuxer muxer = new MediaMuxer(outputFile, MediaMuxer.OutputFormat.MUXER_OUTPUT_MPEG_4);

            videoExtractor.selectTrack(0);
            MediaFormat videoFormat = videoExtractor.getTrackFormat(0);
            int videoTrack = muxer.addTrack(videoFormat);

            audioExtractor.selectTrack(0);
            MediaFormat audioFormat = audioExtractor.getTrackFormat(0);
            int audioTrack = muxer.addTrack(audioFormat);

            Log.d("apple", "Video Format " + videoFormat.toString());
            Log.d("apple", "Audio Format " + audioFormat.toString());

            boolean sawEOS = false;
            int frameCount = 0;
            int offset = 100;
            int sampleSize = 256 * 1024;
            ByteBuffer videoBuf = ByteBuffer.allocate(sampleSize);
            ByteBuffer audioBuf = ByteBuffer.allocate(sampleSize);
            MediaCodec.BufferInfo videoBufferInfo = new MediaCodec.BufferInfo();
            MediaCodec.BufferInfo audioBufferInfo = new MediaCodec.BufferInfo();


            videoExtractor.seekTo(0, MediaExtractor.SEEK_TO_CLOSEST_SYNC);
            audioExtractor.seekTo(0, MediaExtractor.SEEK_TO_CLOSEST_SYNC);

            muxer.start();

            while (!sawEOS) {
                videoBufferInfo.offset = offset;
                videoBufferInfo.size = videoExtractor.readSampleData(videoBuf, offset);


                if (videoBufferInfo.size < 0 || audioBufferInfo.size < 0) {
                    Log.d("apple", "saw input EOS.");
                    sawEOS = true;
                    videoBufferInfo.size = 0;

                } else {
                    videoBufferInfo.presentationTimeUs = videoExtractor.getSampleTime();
                    videoBufferInfo.flags = videoExtractor.getSampleFlags();
                    muxer.writeSampleData(videoTrack, videoBuf, videoBufferInfo);
                    videoExtractor.advance();


//                    frameCount;
                    Log.d("apple", "Frame (" + frameCount + ") Video PresentationTimeUs:" + videoBufferInfo.presentationTimeUs+" Flags:"+videoBufferInfo.flags+" Size(KB) "+videoBufferInfo.size / 1024);
                    Log.d("apple", "Frame (" + frameCount + ") Audio PresentationTimeUs:" + audioBufferInfo.presentationTimeUs+" Flags:"+audioBufferInfo.flags+" Size(KB) "+audioBufferInfo.size / 1024);

                }
            }

            Toast.makeText(getApplicationContext(), "frame:" + frameCount, Toast.LENGTH_SHORT).show();


            boolean sawEOS2 = false;
            int frameCount2 = 0;
            while (!sawEOS2) {
//                frameCount2  ;

                audioBufferInfo.offset = offset;
                audioBufferInfo.size = audioExtractor.readSampleData(audioBuf, offset);

                if (videoBufferInfo.size < 0 || audioBufferInfo.size < 0) {
                    Log.d("apple", "saw input EOS.");
                    sawEOS2 = true;
                    audioBufferInfo.size = 0;
                } else {
                    audioBufferInfo.presentationTimeUs = audioExtractor.getSampleTime();
                    audioBufferInfo.flags = audioExtractor.getSampleFlags();
                    muxer.writeSampleData(audioTrack, audioBuf, audioBufferInfo);
                    audioExtractor.advance();


                    Log.d("apple", "Frame (" + frameCount + ") Video PresentationTimeUs:"+videoBufferInfo.presentationTimeUs+" Flags:"+videoBufferInfo.flags+" Size(KB) "+videoBufferInfo.size / 1024);
                    Log.d("apple", "Frame (" + frameCount + ") Audio PresentationTimeUs:"+audioBufferInfo.presentationTimeUs+" Flags:"+audioBufferInfo.flags+" Size(KB) "+audioBufferInfo.size / 1024);

                }
            }

            Toast.makeText(getApplicationContext(), "frame:"+frameCount2, Toast.LENGTH_SHORT).show();

            muxer.stop();
            muxer.release();


        } catch (IOException e) {
            Log.d("apple", "Mixer Error 1 " + e.getMessage());
        } catch (Exception e) {
            Log.d("apple", "Mixer Error 2 " + e.getMessage());
        }


    }
    private void downLoadMusic(String endPoint) {

        PRDownloader.download(endPoint, getPath().getPath(), "recordSound.aac")
                .build()
//                .setOnStartOrResumeListener()
                .start(new OnDownloadListener() {
                    @Override
                    public void onDownloadComplete() {
                        System.out.println("See here");
                        createAudioForCamera();

//                        customDialogBuilder.hideLoadingDialog();
//                        viewModel.isStartRecording.set(true);
//                        viewModel.createAudioForCamera();
                        System.out.println("Loading successfull");
                    }

                    @Override
                    public void onError(Error error) {
                        System.out.println("error encountered");

                    }

//                    @Override
//                    public void onError(Error error) {
//                        customDialogBuilder.hideLoadingDialog();
//                    }
                });
    }

    public void createAudioForCamera() {
        File file = new File(parentPath.concat("/recordSound.aac"));
        if (file.exists()) {
            try {
                audio1.setDataSource(parentPath.concat("/recordSound.aac"));
                audio1.prepare();
//                soundTextVisibility.set(View.VISIBLE);
//                onDurationUpdate.setValue((long) audio.getDuration());
                System.out.println("audio recording started");
                Log.i("TAG", "createAudioForCamera: " + "0.00");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            System.out.println("File mo exists");
        }
    }

    public File getPath() {
        System.out.println("nasbsbsa1234");
        String state = Environment.getExternalStorageState();
        File filesDir;
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            // We can read and write the media
            filesDir = getExternalFilesDir(null);
        } else {
            // Load another directory, probably local memory
            filesDir = getFilesDir();
        }
        if (filesDir != null) {
            parentPath = filesDir.getPath();
        }
        System.out.println("jahsbhashs"+ filesDir);
        return filesDir;
    }
}