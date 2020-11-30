package com.halfwaiter.lol.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.hardware.Camera;
import android.media.CamcorderProfile;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.halfwaiter.lol.OnAlarmListener;
import com.halfwaiter.lol.R;
import com.halfwaiter.lol.TimerThread;
import com.halfwaiter.lol.VideoCapture;

import java.io.File;
import java.io.IOException;

public class VideoActivity extends Activity {
    private VideoCapture videoCapture;
    private Button stop;
    private View mToggleButton;
    TimerThread mTimer;
    int mCount;
    TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
//        Log.i(null , "Video starting");

        videoCapture = (VideoCapture) findViewById(R.id.videoView);
        mToggleButton= (Button) findViewById(R.id.stop);
        message = findViewById(R.id.msg);
        mTimer= new TimerThread();
        mTimer.setOnAlarmListener(mSTimer_OnAlarm);
        mTimer.setPeriod(1000);

        mToggleButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("ksdnjnjd");
                if (((ToggleButton) v).isChecked()) {
                    System.out.println("jnsab");
                    videoCapture.startCapturingVideo();
                    counterIncrease();
                }
                else {
                    System.out.println("nksaksa");
                    videoCapture.stopCapturingVideo();
                    setResult(Activity.RESULT_OK);
                    finish();
                }
//
            }
        });


    }

    private void counterIncrease() {
        System.out.println("sbdsb");
        mCount = 30;
        mTimer.start();

    }

    OnAlarmListener mSTimer_OnAlarm= new OnAlarmListener() {
        @Override
        public void OnAlarm(TimerThread source) {
            mCount--;
            message.setVisibility(View.VISIBLE);
            message.setText("Count="+mCount);
            if( mCount==0) source.stop();
        }
    };

}