package com.halfwaiter.lol;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.halfwaiter.lol.activities.VideoActivity;
import com.halfwaiter.lol.fragments.HomeFragment;
import com.halfwaiter.lol.fragments.NotificationFragment;
import com.halfwaiter.lol.fragments.ProfileFragment;
import com.halfwaiter.lol.fragments.SearchFragment;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigation;
    int versionCode = BuildConfig.VERSION_CODE;
    String versionName = BuildConfig.VERSION_NAME;
    FloatingActionButton fabBtn;

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
                Intent videoIntent = new Intent(getApplicationContext(), VideoActivity.class);
                startActivity(videoIntent);
            }
        });
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
                            openFragment(new NotificationFragment());                            return true;

                        case R.id.navigation_profile:
                            openFragment(new ProfileFragment());                            return true;
                    }

                    return false;
                }
            };

}
