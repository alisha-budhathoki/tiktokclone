package com.halfwaiter.lol;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class VideoCapture extends SurfaceView implements SurfaceHolder.Callback {
    private MediaRecorder recorder;
    private SurfaceHolder holder;
    public Context context;
    private Camera camera;
    Boolean isStarted;

    public static String videoPath = Environment.getExternalStorageDirectory()
            .getPath() + "/YOUR_VIDEO.mp4";

    public VideoCapture(Context context) {
        super(context);
        this.context = context;
        init();
        recorder.setPreviewDisplay(holder.getSurface());

    }

    public VideoCapture(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        recorder.setPreviewDisplay(holder.getSurface());
    }

    public VideoCapture(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
        recorder.setPreviewDisplay(holder.getSurface());
    }

    @SuppressLint("NewApi")
    public void init() {
        try {
            System.out.println("dsjd" + videoPath);
            recorder = new MediaRecorder();
            holder = getHolder();
            holder.addCallback(this);
            holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
            camera = Camera.open();
            if (android.os.Build.VERSION.SDK_INT > 7)
                camera.setDisplayOrientation(90);
            System.out.println("sdnjsd");
            camera.unlock();
            System.out.println("camera unlocked");
            recorder.setCamera(camera);
            recorder.setVideoSource(MediaRecorder.VideoSource.DEFAULT);
            recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            recorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);
            recorder.setOutputFile(videoPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
    }

    public void surfaceCreated(SurfaceHolder mHolder) {

        try {
            System.out.println("dsnjbd");
//            recorder.setProfile(CamcorderProfile.get(-1,CamcorderProfile.QUALITY_HIGH));
//            recorder.setPreviewDisplay(mHolder.getSurface());
//            recorder.start();
            System.out.println("xnsbh");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
            recorder.prepare();
            recorder.start();
            isStarted = true;
            System.out.println("recording started");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @TargetApi(5)
    public void surfaceDestroyed(SurfaceHolder arg0) {
        if (recorder != null) {
            stopCapturingVideo();
            recorder.release();
            camera.lock();
            camera.release();
            recorder = null;
        }
    }

    private Camera getCameraInstance() {
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
        } catch (Exception e) {
            // Camera is not available (in use or does not exist)
        }
        return c;
    }
}
