package com.halfwaiter.lol.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.halfwaiter.lol.adapter.TimeLengthAdapter;
import com.halfwaiter.lol.model.TimeSecond;

import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class VideoActivity extends Activity {
    private VideoCapture videoCapture;
    private Button stop;
    private View mToggleButton;
    TimerThread mTimer;
    int mCount;

    TextView message;

    private String[] myTimeTitle = new String[]{"15s","60s"};
    TimeLengthAdapter timeLengthAdapter;
    RecyclerView recyclerViewTime;
    ArrayList<TimeSecond> mListTimeLength;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
//        Log.i(null , "Video starting");
        mCount=15;
        recyclerViewTime = findViewById(R.id.timeSecondsRecycler);
        mListTimeLength = seeTimeLength();
        timeLengthAdapter = new TimeLengthAdapter(VideoActivity.this, mListTimeLength);
        recyclerViewTime.setAdapter(timeLengthAdapter);
        recyclerViewTime.setLayoutManager(new LinearLayoutManager(VideoActivity.this, LinearLayoutManager.HORIZONTAL, false));

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


        recyclerViewTime.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE){
                    int position = getCurrentItem();
                    System.out.println("dbjsbdjdb"+position);
                    if (position ==0){
                        mCount = 15;
                    }
                    else {
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

                System.out.println("dsnbdisd"+dx+"shbds"+dy);
            }

            });


    }

    private int getCurrentItem(){
        return ((LinearLayoutManager)recyclerViewTime.getLayoutManager())
                .findFirstVisibleItemPosition();
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
        System.out.println("sbdsb");
        mTimer.start();
    }

    OnAlarmListener mSTimer_OnAlarm= new OnAlarmListener() {
        @Override
        public void OnAlarm(TimerThread source) {
            mCount--;
            message.setVisibility(View.VISIBLE);
            message.setText(""+mCount);
            if( mCount==0) source.stop();
        }
    };

}