package com.halfwaiter.lol.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
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
import java.util.ArrayList;

public class VideoActivity extends Activity implements SurfaceHolder.Callback {
//    private VideoCapture videoCapture;

    Camera camera;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;

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
    TextView message;
    private MediaRecorder recorder;
    private SurfaceHolder holder;

    public Context context;


    public static boolean isFront = false;
    Boolean isStarted = false;

    public static String videoPath = Environment.getExternalStorageDirectory()
            .getAbsolutePath() + "/YOUR_VIDEO.mp4";

    private String[] myTimeTitle = new String[]{"15s", "60s"};
    TimeLengthAdapter timeLengthAdapter;
    RecyclerView recyclerViewTime;
    ArrayList<TimeSecond> mListTimeLength;
    TextView soundTxt;
    ImageView swapImg, gallery, cross;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        soundTxt = findViewById(R.id.txt_sound);
        cross = findViewById(R.id.ic_cross);
        gallery = findViewById(R.id.galleryTxt);
        swapImg = findViewById(R.id.cameraSwap);

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

//                mCount = 15;
//                System.out.println("sdsdsujbjkdsjs");
//                myTimeTitle[0]
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                System.out.println("dsnbdisd" + dx + "shbds" + dy);
            }

        });


//        videoCapture.startCapturingVideo();
//        counterIncrease();


        surfaceView = findViewById(R.id.videoView);
        surfaceHolder = surfaceView.getHolder();

        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        surfaceHolder.addCallback(this);

        // deprecated setting, but required on Android versions prior to 3.0
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

//        jpegCallback = new Camera.PictureCallback() {
//            public void onPictureTaken(byte[] data, Camera camera) {
//                FileOutputStream outStream = null;
//                try {
//                    outStream = new FileOutputStream(String.format("/sdcard/%d.jpg", System.currentTimeMillis()));
//                    outStream.write(data);
//                    outStream.close();
//                    Log.d("Log", "onPictureTaken - wrote bytes: " + data.length);
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } finally {
//                }
//                Toast.makeText(getApplicationContext(), "Picture Saved",Toast.LENGTH_SHORT).show();
//                refreshCamera();
//            }
//        };
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
                recorder.stop();
                System.out.println("See here");
                camera.lock();
                System.out.println("recording stopped");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else
            System.out.println("knsjsbdjd");
    }

    public void startCapturingVideo() {
        try {
            camera.unlock();
            recorder = new MediaRecorder();
            recorder.setOnErrorListener(new MediaRecorder.OnErrorListener() {

                @Override
                public void onError(MediaRecorder mr, int what, int extra) {
                    System.out.println("error here");
                }
            });

            recorder.setCamera(camera);
            recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
            System.out.println("js bsdb");
            recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
            recorder.setVideoEncoder(MediaRecorder.VideoEncoder.H263);
            System.out.println("nsjbsd");
            recorder.setMaxDuration(200000); // set to 20000

            String uniqueOutFile = videoPath + System.currentTimeMillis() + ".3gp";
            File outFile = new File(uniqueOutFile);
            if (outFile.exists()) {
                outFile.delete();
            }
            recorder.setOutputFile(uniqueOutFile);
            recorder.setVideoFrameRate(20); // set to 20
            recorder.setVideoSize(surfaceView.getWidth(), surfaceView.getHeight());
            System.out.println("sndbbj");
//            recorder.setPreviewDisplay(holder.getSurface());
//            recorder.setMaxFileSize(50000); // set to 50000
            recorder.prepare();

            recorder.start();

        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
            camera.lock();
        }
    }

    //
//    public void init(int camId) {
//        try {
////            camera.stopPreview();
////            camera.release();
////            camera.release();
//            System.out.println("dsjd" + videoPath);
//            Toast.makeText(this, "i am being called!!", Toast.LENGTH_SHORT).show();
//            recorder = new MediaRecorder();
//
//            holder = surfaceView.getHolder();
//            holder.addCallback(this);
//            holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
//            recorder.setPreviewDisplay(holder.getSurface());
//            camera = Camera.open(camId);
//            if (android.os.Build.VERSION.SDK_INT > 7)
//                camera.setDisplayOrientation(90);
//            System.out.println("sdnjsd");
//            camera.unlock();
//            System.out.println("camera unlocked");
//            recorder.setCamera(camera);
//            recorder.setVideoSource(MediaRecorder.VideoSource.DEFAULT);
//            recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
//            recorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);
//            recorder.setOutputFile(videoPath);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
////    private void releaseCameraAndPreview() {
//////        myCameraPreview.setCamera(null);
////        if (camera != null) {
////            camera.startPreview();
////            camera.release();
////            camera = null;
////        }
////    }
//
//    private int getCurrentItem() {
//        return ((LinearLayoutManager) recyclerViewTime.getLayoutManager())
//                .findFirstVisibleItemPosition();
//    }
//
//
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
        Log.i("nsasabsa", "Switching Camera");
        if (camera != null) {
            camera.stopPreview();
            camera.release();
            //mCamera = null;
        }

        //swap the id of the camera to be used
        if (camId == Camera.CameraInfo.CAMERA_FACING_BACK) {
            camId = Camera.CameraInfo.CAMERA_FACING_FRONT;
        }else {
            camId = Camera.CameraInfo.CAMERA_FACING_BACK;
        }
        try {
            camera = Camera.open(camId);
            //mCamera.setDisplayOrientation(90);
            //You must get the holder of SurfaceView!!!
            setCameraDisplayOrientation();

            camera.setPreviewDisplay(surfaceView.getHolder());
            //Then resume preview...
            camera.startPreview();
        }
        catch (Exception e) { e.printStackTrace(); }}
//        Previ mPreview = new CameraPreview(this, mCamera);
//        preview = (FrameLayout) this.findViewById(R.id.camera_previeww);
//        preview.removeAllViews();
//        preview.addView(mPreview);

//        if (VideoCapture.isFront == false)
//        VideoCapture.isFront = true;
//        else
//            VideoCapture.isFront = false;
//        Toast.makeText(this, "swapped clicked", Toast.LENGTH_SHORT).show();
//    }



//
//    @Override
//    public void surfaceCreated(SurfaceHolder holder) {
//        try {
//
//            Toast.makeText(this, "i am calling", Toast.LENGTH_SHORT).show();
//            // open the camera
//            camera = Camera.open();
//        } catch (RuntimeException e) {
//            // check for exceptions
//            System.err.println(e);
//            return;
//        }
//        Camera.Parameters param;
//        param = camera.getParameters();
//
//        // modify parameter
//        param.setPreviewSize(352, 288);
//        camera.setParameters(param);
//        try {
//            // The Surface has been created, now tell the camera where to draw
//            // the preview.
//            camera.setPreviewDisplay(holder);
//            camera.startPreview();
//        } catch (Exception e) {
//            // check for exceptions
//            System.err.println(e);
//            return;
//        }
//    }
//
//    @Override
//    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
//
//    }
//
//    @Override
//    public void surfaceDestroyed(SurfaceHolder holder) {
//        if (recorder != null) {
//            stopCapturingVideo();
//            recorder.release();
//            camera.lock();
//            camera.release();
//            recorder = null;
//        }
//    }
//
//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//
//    }

//    public void captureImage(View v) throws IOException {
//        //take the picture
//        camera.takePicture(null, null, jpegCallback);
//    }

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

        // set preview size and make any resize, rotate or
        // reformatting changes here
        // start preview with new settings
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
        if (info.facing == Camera.CameraInfo.CAMERA_FACING_FRONT) {
            result = (info.orientation + degrees) % 360;
            result = (360 - result) % 360;  // compensate the mirror
        } else {  // back-facing
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
}