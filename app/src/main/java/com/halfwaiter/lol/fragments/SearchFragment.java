package com.halfwaiter.lol.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.android.material.tabs.TabLayout;
import com.halfwaiter.lol.HomeActivity;
import com.halfwaiter.lol.R;
import com.halfwaiter.lol.adapter.TabAdapter;

import org.w3c.dom.Text;

public class SearchFragment extends Fragment {
    private TabAdapter adapter;
    private TabLayout tabLayout;
    private ViewPager viewpagerSearch;

    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
//        Toolbar toolbar = ((HomeActivity)getActivity()).findViewById(R.id.toolbar);
        TextView toolbar_title = view.findViewById(R.id.toolbar_title);
        toolbar_title.setText("Explore");
        viewpagerSearch = (ViewPager) view.findViewById(R.id.viewPagerSearch);
        tabLayout = view.findViewById(R.id.tabLayout);
        adapter = new TabAdapter(getFragmentManager());
        adapter.addFragment(new MostRecentFragment(), "Most Recent");
        adapter.addFragment(new TrendingFragment(), "Trending");
        adapter.addFragment(new FavouriteFragment(), "Favourite");

        viewpagerSearch.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpagerSearch);
        viewpagerSearch.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
              }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        return view;
    }
}