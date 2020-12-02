package com.halfwaiter.lol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.halfwaiter.lol.activities.VideoActivity;
import com.halfwaiter.lol.fragments.HomeFragment;
import com.halfwaiter.lol.fragments.NotificationFragment;
import com.halfwaiter.lol.fragments.ProfileFragment;
import com.halfwaiter.lol.fragments.SearchFragment;

import java.io.File;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    int versionCode = BuildConfig.VERSION_CODE;
    public final static int PICK_PHOTO_CODE = 1046;
    String versionName = BuildConfig.VERSION_NAME;
    FloatingActionButton fabBtn;
    private final int VIDEO_REQUEST_CODE = 101;
    Dialog dialog;
    TextView txtCamera, txtGallery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        fabBtn = findViewById(R.id.fab);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        bottomNavigation.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new HomeFragment())
                .commit();


        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showFileAndCameraDialog(v);
//                captureVideo(v);
                Intent videoIntent = new Intent(getApplicationContext(), VideoActivity.class);
                startActivity(videoIntent);
            }
        });
    }

    private void showFileAndCameraDialog(View v) {
        dialog = new Dialog(HomeActivity.this, R.style.Dialog);
        dialog.setContentView(R.layout.payment);
        dialog.setTitle("Choose Method");

//        Objects.requireNonNull(dialog.getWindow()).getAttributes().windowAnimations = R.style.DialogAnimation;

        dialog.setCanceledOnTouchOutside(true);

        txtCamera = dialog.findViewById(R.id.btnCamer);
        txtGallery = dialog.findViewById(R.id.btnGallery);

        txtGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
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

        txtCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                System.out.println("nsdsj");
                Intent camera_intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                camera_intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 5);
//        For opening camera
//Passing file path for saving file
                File video_file = getFielPath();
//        Now converting this file to uri
                Uri video_uri = Uri.fromFile(video_file);
//        Now adding this file path into the intent object
                camera_intent.putExtra(MediaStore.ACTION_VIDEO_CAPTURE, video_uri);
//        specifying quality for video
                camera_intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1); //1 specifies the highest quality
                startActivityForResult(camera_intent, VIDEO_REQUEST_CODE);


            }
        });

        dialog.show();
    }


    private void captureVideo(View v) {
        System.out.println("nsdsj");
        Intent camera_intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        camera_intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, 5);
//        For opening camera
//Passing file path for saving file
        File video_file = getFielPath();
//        Now converting this file to uri
        Uri video_uri = Uri.fromFile(video_file);
//        Now adding this file path into the intent object
        camera_intent.putExtra(MediaStore.ACTION_VIDEO_CAPTURE, video_uri);
//        specifying quality for video
        camera_intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1); //1 specifies the highest quality
        // startActivityForResult(camera_intent, VIDEO_REQUEST_CODE);4
        // Create intent for picking a photo from the gallery
    }

    public File getFielPath() {
        System.out.println("skjsddf");
        File folder = new File(getApplicationContext().getExternalFilesDir(null), "jasa");
//        File folder = new File(getApplicationContext().getExternalFilesDir(null),"myfile.mp4");        System.out.println("njsjjds"+ this.getFilesDir().getPath());
//        for saving app file
        if (folder.exists()) {
            folder.mkdir();
        }
//        For saving video file
        File video_file = new File("sample_video.mp4");
        return video_file;
    }

    public void openFragment(Fragment fragment) {
        FragmentManager transaction = getSupportFragmentManager();
        transaction.beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
//        transaction.addToBackStack(null);
//        transaction.commit();
    }

    BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                    FragmentTransaction transaction = getSupportFragmentManager();

                    switch (item.getItemId()) {
                        case R.id.navigation_home:
//                            transaction.replace(R.id.container,new HomeFragment()).addToBackStack(null).commit();
                            openFragment(new HomeFragment());
                            return true;
                        case R.id.nabigation_search:
                            openFragment(new SearchFragment());
//                            openFragment(SearchFragment.newInstance("", ""));
                            return true;
                        case R.id.navigation_video:
//                            openFragment(new VideoFragment());
//                            return true;

                        case R.id.navigation_notifications:
                            openFragment(new NotificationFragment());
                            return true;

                        case R.id.navigation_profile:
                            openFragment(new ProfileFragment());
                            return true;
                    }

                    return false;
                }
            };

}
