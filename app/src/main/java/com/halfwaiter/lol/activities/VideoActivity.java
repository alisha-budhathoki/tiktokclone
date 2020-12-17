package com.halfwaiter.lol.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.media.AudioManager;
import android.media.CamcorderProfile;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.halfwaiter.lol.OnAlarmListener;
import com.halfwaiter.lol.R;
import com.halfwaiter.lol.TimerThread;
import com.halfwaiter.lol.VideoCapture;
import com.halfwaiter.lol.adapter.TimeLengthAdapter;
import com.halfwaiter.lol.model.TimeSecond;

import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class VideoActivity extends Activity implements SurfaceHolder.Callback {
//    private VideoCapture videoCapture;

    Camera camera;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    private static final int CAMERA_CAPTURE_VIDEO_REQUEST_CODE = 200;

    Camera.PictureCallback rawCallback;
    Camera.ShutterCallback shutterCallback;
    Camera.PictureCallback jpegCallback;

    //
    private Button stop;
    private View mToggleButton;
    TimerThread mTimer;
    int mCount;
    BottomSheetBehavior sheetBehavior;
    public final static int PICK_PHOTO_CODE = 1046;
    int camId = 0;
    int camDegree = 90;
    TextView message;
    private MediaRecorder recorder;
    private SurfaceHolder holder;

    public Context context;


    public static boolean isFront = false;
    Boolean isStarted = false;

    public static String videoPath;


    private String[] myTimeTitle = new String[]{"15s", "60s"};
    TimeLengthAdapter timeLengthAdapter;
    RecyclerView recyclerViewTime;
    ArrayList<TimeSecond> mListTimeLength;
    TextView soundTxt;
    ImageView swapImg, gallery, cross;
    Uri videoUri;
    Bundle bundle = new Bundle();
    String musicName, musicUrl;
    public static MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);


//        Bundle bundleget = getIntent().getExtras();
//        if (bundleget.getBoolean("isSound")) {
//            String abc = bundleget.getString("audiourl");
//            System.out.println("nsbsdbs" + abc);
//        }
//        else {
//            System.out.println("snhbds");
//        }


        soundTxt = findViewById(R.id.txt_sound);
        cross = findViewById(R.id.ic_cross);
        gallery = findViewById(R.id.galleryTxt);
        swapImg = findViewById(R.id.cameraSwap);

        try {
            if (getIntent().getExtras().getString("isSound").equals("1")) {
                musicUrl = getIntent().getExtras().getString("audiourl");
                musicName = getIntent().getExtras().getString("audioName");
                System.out.println("SoundUrl : " + musicUrl + musicName);
//                soundTxt.setText(musicName);
                soundTxt.setEllipsize(TextUtils.TruncateAt.MARQUEE);
                soundTxt.setText(musicName);
                soundTxt.setSelected(true);
                soundTxt.setSingleLine(true);
                soundTxt.setSelected(true);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }


//        surfaceView = findViewById(R.id.videoView);

        mCount = 15;
        recyclerViewTime = findViewById(R.id.timeSecondsRecycler);
        mListTimeLength = seeTimeLength();
        timeLengthAdapter = new TimeLengthAdapter(VideoActivity.this, mListTimeLength);
        recyclerViewTime.setAdapter(timeLengthAdapter);
        recyclerViewTime.setLayoutManager(new LinearLayoutManager(VideoActivity.this, LinearLayoutManager.HORIZONTAL, false));
//        videoCapture = (VideoCapture) findViewById(R.id.videoView);

//
        mToggleButton = (Button) findViewById(R.id.stop);
        message = findViewById(R.id.msg);
////        videoCapture = new VideoCapture(VideoActivity.this);
        mTimer = new TimerThread();
        mTimer.setOnAlarmListener(mSTimer_OnAlarm);
        mTimer.setPeriod(1000);
//        videoCapture.init(0);
//        videoCapture.startCapturingVideo();
        soundTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gotoSound = new Intent(getApplicationContext(), SoundActivity.class);
                startActivity(gotoSound);
                System.out.println("sankjkbdsa");

            }
        });
        gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                // /If you call startActivityForResult() using an intent that no app can handle, your app will crash.
                // So as long as the result is not null, it's safe to use the intent.
                if (intent.resolveActivity(getPackageManager()) != null) {
                    // Bring up gallery to select a photo
                    startActivityForResult(intent, PICK_PHOTO_CODE);
                }

            }
        });

//        init(1);

        cross.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mToggleButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("ksdnjnjd");
                if (((ToggleButton) v).isChecked()) {
                    System.out.println("jnsab");
                    startCapturingVideo();
                    counterIncrease();
                    exitSound();
                } else {
                    System.out.println("nksaksa");
                    stopCapturingVideo();
                    setResult(Activity.RESULT_OK);
                    counterStop();
                    finish();
                }
//
            }
        });

        recyclerViewTime.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    int position = getCurrentItem();
                    System.out.println("dbjsbdjdb" + position);
                    if (position == 0) {
                        System.out.println("asjbs" + position);
                        mCount = 15;
                    } else {
                        System.out.println("nsbsb" + position);
                        mCount = 60;
                    }
//                    onPageChanged(position);
                }

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                System.out.println("dsnbdisd" + dx + "shbds" + dy);
            }

        });


        surfaceView = findViewById(R.id.videoView);
        surfaceHolder = surfaceView.getHolder();

        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        surfaceHolder.addCallback(this);

        // deprecated setting, but required on Android versions prior to 3.0
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

    }

    private void exitSound() {
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(musicUrl);
            System.out.println("ksdadsajnsd");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.out.println("sdnjadddb");
            mediaPlayer.prepare(); // might take long! (for buffering, etc)
        } catch (Exception e) {
            e.printStackTrace();
        }
        mediaPlayer.start();

    }


    private void launchUploadActivity1(boolean isPlaying, Uri uri) {

        Intent i = new Intent(VideoActivity.this, VideoPlayerActivity.class);
//      i.setData(uri);  // set the uri to intent
        i.setData(videoUri);
        startActivity(i);
    }

    private int getCurrentItem() {
        return ((LinearLayoutManager) recyclerViewTime.getLayoutManager())
                .findFirstVisibleItemPosition();
    }

    //
    public void stopCapturingVideo() {
        if (isStarted == true) {
            System.out.println("snidsid");
            try {
                bundle.putString("videoUri", videoPath);
                Intent intent = new Intent(this, VideoPlayerActivity.class);

                intent.putExtras(bundle);
                startActivityForResult(intent, 1);
                recorder.stop();
                System.out.println("See here");
                camera.lock();
                System.out.println("recording stopped");
                stopSound();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else
            System.out.println("knsjsbdjd");
    }

    private void stopSound() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.reset();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        VideoActivity.this.finish();
        stopSound();
        stopCapturingVideo();
    }

    public void startCapturingVideo() {
        try {
            swapImg.setVisibility(View.GONE);
            camera.unlock();
            recorder = new MediaRecorder();
            File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES), "LOL");

            if (!mediaStorageDir.exists()) {
                mediaStorageDir.mkdir();
                if (!mediaStorageDir.mkdirs()) {
                    Log.d("MyCameraApp", "failed to create directory");
                    return;
                }
            }

            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String i = sdf.format(new Date());

            File mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "VID_" + i + ".mp4");

            videoPath = mediaFile.getAbsolutePath();

//            file.getParentFile().mkdirs();

            recorder.setOnErrorListener(new MediaRecorder.OnErrorListener() {

                @Override
                public void onError(MediaRecorder mr, int what, int extra) {
                    System.out.println("error here");
                }
            });

            recorder.setCamera(camera);
            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
            recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            System.out.println("nsjbsd");
            recorder.setVideoSize(640, 480);
            recorder.setVideoFrameRate(16); //might be auto-determined due to lighting
            recorder.setVideoEncodingBitRate(3000000);
            recorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);// MPEG_4_SP
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
//            setMicMuted(true);
            recorder.setOutputFile(videoPath);
            recorder.setOrientationHint(camDegree);
            recorder.prepare();
//            recorder.setPreviewDisplay(surfaceView.getHolder().getSurface());
//            recorder.setPreviewDisplay(holder.getSurface());
//            recorder.setMaxFileSize(50000); // set to 50000

            recorder.start();
            isStarted = true;

        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
            camera.lock();
        }

//        Intent mediaIntent = new Intent(Intent.ACTION_VIEW);
//        startActivityForResult(mediaIntent, CAMERA_CAPTURE_VIDEO_REQUEST_CODE);
    }
    private void setMicMuted(boolean state){
        AudioManager myAudioManager = (AudioManager)getApplicationContext().getSystemService(Context.AUDIO_SERVICE);

        // get the working mode and keep it
        int workingAudioMode = myAudioManager.getMode();

        myAudioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);

        // change mic state only if needed
        if (myAudioManager.isMicrophoneMute() != state) {
            myAudioManager.setMicrophoneMute(state);
        }

        // set back the original working mode
        myAudioManager.setMode(workingAudioMode);
    }

    private ArrayList<TimeSecond> seeTimeLength() {
        ArrayList<TimeSecond> listTimes = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            TimeSecond timeSecond = new TimeSecond();
            timeSecond.settTimeTxt(myTimeTitle[i]);
            listTimes.add(timeSecond);
        }
        return listTimes;
    }

    private void counterIncrease() {
        mTimer.start();
    }

    private void counterStop() {
        mTimer.stop();
    }

    OnAlarmListener mSTimer_OnAlarm = new OnAlarmListener() {
        @Override
        public void OnAlarm(TimerThread source) {
            mCount--;
            message.setVisibility(View.VISIBLE);
            message.setText("" + mCount);
            if (mCount == 0) {
                Toast.makeText(VideoActivity.this, "Maximum recording limit exceeded", Toast.LENGTH_SHORT).show();
                source.stop();
                stopCapturingVideo();
                setResult(Activity.RESULT_OK);
                finish();
            }
        }
    };

    public void swapCamera(View view) {
        System.out.println("switching camera");
        Log.i("nsasabsa", "Switching Camera");
        if (camera != null) {
            camera.stopPreview();
            camera.release();
            //mCamera = null;
        }

        //swap the id of the camera to be used
        if (camId == Camera.CameraInfo.CAMERA_FACING_BACK) {
            camId = Camera.CameraInfo.CAMERA_FACING_FRONT;
            camDegree = 270;
//            System.out.println("asbshd"+camDegree);
        } else {
            camId = Camera.CameraInfo.CAMERA_FACING_BACK;
            camDegree = 90;

        }
        try {
            camera = Camera.open(camId);
            setCameraDisplayOrientation();

            camera.setPreviewDisplay(surfaceView.getHolder());
            //Then resume preview...
            camera.startPreview();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void refreshCamera() {
        if (surfaceHolder.getSurface() == null) {
            // preview surface does not exist
            return;
        }

        // stop preview before making changes
        try {
            camera.stopPreview();
        } catch (Exception e) {
            // ignore: tried to stop a non-existent preview
        }

        try {
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
        } catch (Exception e) {

        }
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        // Now that the size is known, set up the camera parameters and begin
        // the preview.
        refreshCamera();
    }

    public void setCameraDisplayOrientation() {
        if (camera == null) {
            System.out.println("set camer display orientation -- camera null");
            return;
        }
        System.out.println("nbsds");
        Camera.CameraInfo info = new Camera.CameraInfo();
        Camera.getCameraInfo(0, info);

        WindowManager winManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        int rotation = winManager.getDefaultDisplay().getRotation();

        int degrees = 0;

        switch (rotation) {
            case Surface.ROTATION_0:
                degrees = 0;
                break;
            case Surface.ROTATION_90:
                degrees = 90;
                break;
            case Surface.ROTATION_180:
                degrees = 180;
                break;
            case Surface.ROTATION_270:
                degrees = 270;
                break;
        }

        int result;
        System.out.println("sbsdhjash" + info.facing);
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            System.out.println("jnbsdds");
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;  // compensate the mirror
        } else {  // back-facing
            System.out.println("sadns");
            result = (info.orientation - degrees + 360) % 360;
        }
        camera.setDisplayOrientation(result);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        try {
            // open the camera
            camera = Camera.open(camId);
        } catch (RuntimeException e) {
            // check for exceptions
            System.err.println(e);
            return;
        }
        Camera.Parameters param;
        param = camera.getParameters();

        // modify parameter
        param.setPreviewSize(352, 288);
        camera.setParameters(param);
        try {
            // The Surface has been created, now tell the camera where to draw
            // the preview.
            setCameraDisplayOrientation();
            camera.setPreviewDisplay(surfaceHolder);
            camera.startPreview();
        } catch (Exception e) {
            // check for exceptions
            System.err.println(e);
            return;
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        // stop preview and release camera
        camera.stopPreview();
        camera.release();
        camera = null;
    }

//    @Override
//    public void onBackPressed() {
//        System.out.println("snjbshdvh");
//        // your code.
//        mediaPlayer.stop();
//        VideoActivity.this.finish();
//    }


}