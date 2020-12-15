package com.halfwaiter.lol.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.halfwaiter.lol.R;
import com.halfwaiter.lol.adapter.ExploreAdapter;
import com.halfwaiter.lol.adapter.NotificationAdapter;
import com.halfwaiter.lol.model.ExploreModel;
import com.halfwaiter.lol.model.NotificationModel;

import java.util.ArrayList;

public class NotificationFragment extends Fragment {

    private String[] notificationTiltle = new String[]{"New user","Extra Offer" ,"Happy Sunday"};
    private String[] notificationContent = new String[]{"abc", "Pop", "remix"};
    private int[] notificationPic = new int[]{R.drawable.index, R.drawable.bkgrnd2, R.drawable.bkgrnd2};


    NotificationAdapter notificationAdapter;
    RecyclerView recyclerNotification;
    ArrayList<NotificationModel> mListNotification;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        recyclerNotification = view.findViewById(R.id.recycler_notification);
        mListNotification = seeNotificationData();
        notificationAdapter = new NotificationAdapter(getContext(), mListNotification);
        recyclerNotification.setAdapter(notificationAdapter);
        recyclerNotification.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        return view;
    }

    private ArrayList<NotificationModel> seeNotificationData() {
        ArrayList<NotificationModel> listNotification = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            NotificationModel notificationModel = new NotificationModel();
//            System.out.println("sjhdhjbd"+soundName[0]+soundName[1]);
            notificationModel.setNotificationTitle(notificationTiltle[i]);
            notificationModel.setNotificationContent(notificationContent[i]);
            notificationModel.setNotificationPic(notificationPic[i]);

            listNotification.add(notificationModel);
        }
        return listNotification;

    }
}