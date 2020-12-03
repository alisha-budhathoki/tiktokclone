package com.halfwaiter.lol.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.halfwaiter.lol.R;
import com.halfwaiter.lol.adapter.TabAdapter;
import com.halfwaiter.lol.fragments.BookmarkedFragment;
import com.halfwaiter.lol.fragments.ExploreFragment;
import com.halfwaiter.lol.fragments.FavouriteFragment;
import com.halfwaiter.lol.fragments.MostRecentFragment;
import com.halfwaiter.lol.fragments.TrendingFragment;

public class SoundActivity extends AppCompatActivity {
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewPagerSound;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);
        TextView toolbar_title = findViewById(R.id.toolbar_title);
        toolbar_title.setText("Sounds");

//        setting the size of drawable dynamically
//        Drawable dr = ContextCompat.getDrawable(SoundActivity.this, R.drawable.ic_music);
////        dr.setBounds(0,0,120,120);
//        toolbar_title.setCompoundDrawables( dr,null, null, null); //setting drawable on left programeticall
        toolbar_title.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_music, 0, 0, 0); //setting drawable on left programetically

        viewPagerSound = (ViewPager) findViewById(R.id.viewPagerSound);
        tabLayout = findViewById(R.id.tab_sound);
        adapter = new TabAdapter(getSupportFragmentManager());
        adapter.addFragment(new ExploreFragment(), "Explore");
        adapter.addFragment(new BookmarkedFragment(), "Bookmarked");


        viewPagerSound.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPagerSound);
        viewPagerSound.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                System.out.println("dklnsjkd");
            }

            @Override
            public void onPageSelected(int i) {
                System.out.println("dnsbshjsd");
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                System.out.println("nsvhv");
            }
        });

    }
}