package com.halfwaiter.lol.fragments;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.halfwaiter.lol.R;
import com.halfwaiter.lol.adapter.CommentAdapter;
import com.halfwaiter.lol.adapter.HomeAdapter;
import com.halfwaiter.lol.model.CommentModel;
import com.halfwaiter.lol.model.HomeModel;

import java.util.ArrayList;

public class ExampleBottomSheetDialog extends BottomSheetDialogFragment {
    private int[] usePic = new int[]{R.drawable.bkgrnd, R.drawable.bkgrnd, R.drawable.bkgrnd, R.drawable.bkgrnd, R.drawable.bkgrnd};
    private String[] userName = new String[]{"rimi", "sdb", "heuh", "sdh", "hbsh"};
    private String[] commentContent = new String[]{"nsdbvgdgdsgsvdsh", "jbshsjvsahvsvhdvh", "hbsuhsvsagasghh", "dsvgdgsd", "jnahusdav"};
    private String[] timeLength = new String[]{"10:34", "2:89", "4:89", "1:23", "4:34"};

    TextView toolbarTitle, commentNo;
    CommentAdapter commentAdapter;
    RecyclerView recyclerViewComment;
    ArrayList<CommentModel> mListComments;
    ImageView ivCancel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_comment, container, false);
        toolbarTitle = v.findViewById(R.id.toolbar_title);
        ivCancel = v.findViewById(R.id.imgCross);
        commentNo = v.findViewById(R.id.comment_no);
        ivCancel.setVisibility(View.VISIBLE);
        toolbarTitle.setText("comments");
        toolbarTitle.setTypeface(toolbarTitle.getTypeface(), Typeface.NORMAL);
        commentNo.setTextSize(15);
        commentNo.setVisibility(View.VISIBLE);
        commentNo.setText("12");
        toolbarTitle.setTextSize(15);
        recyclerViewComment = v.findViewById(R.id.recycler_comments);
        mListComments = seeComments();
        commentAdapter = new CommentAdapter(getContext(), mListComments);
        recyclerViewComment.setAdapter(commentAdapter);
        recyclerViewComment.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return v;
    }

    private ArrayList<CommentModel> seeComments() {
        ArrayList<CommentModel> listComments = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            CommentModel commentModel = new CommentModel();
            commentModel.setUserComment(commentContent[i]);
            commentModel.setUserPhoto(usePic[i]);
            commentModel.setUserName(userName[i]);
            commentModel.setComntTime(timeLength[i]);

            listComments.add(commentModel);
        }
        return listComments;

    }

}