//package com.halfwaiter.lol.utils;
//
//import android.app.Dialog;
//import android.content.Context;
//import android.graphics.Color;
//import android.graphics.drawable.ColorDrawable;
//import android.view.LayoutInflater;
//import android.view.Window;
//import android.view.animation.Animation;
//import android.view.animation.AnimationUtils;
//
//public class CustomDialogBuilder {
//    private Context mContext;
//    private Dialog mBuilder = null;
//
//    public CustomDialogBuilder(Context context) {
//        this.mContext = context;
//        if (mContext != null) {
//            mBuilder = new Dialog(mContext);
//            mBuilder.requestWindowFeature(Window.FEATURE_NO_TITLE);
//            mBuilder.setCancelable(false);
//            mBuilder.setCanceledOnTouchOutside(false);
//            if (mBuilder.getWindow() != null) {
//                mBuilder.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//            }
//        }
//    }
//
//    public void showSimpleDialog(String title, String message, String negativeText, String positiveText, OnDismissListener onDismissListener) {
//
//        if (mContext == null)
//            return;
//
//        mBuilder.setCancelable(true);
//        mBuilder.setCanceledOnTouchOutside(true);
//        LoutPopupBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.lout_popup, null, false);
//        mBuilder.setContentView(binding.getRoot());
//        Dialog1 dialog1 = new Dialog1();
//        dialog1.setTitle(title);
//        dialog1.setMessage(message);
//        dialog1.setPositiveText(positiveText);
//        dialog1.setNegativeText(negativeText);
//        binding.setModel(dialog1);
//        binding.tvPositive.setOnClickListener(v -> {
//            mBuilder.dismiss();
//            onDismissListener.onPositiveDismiss();
//        });
//        binding.tvCancel.setOnClickListener(v -> {
//            mBuilder.dismiss();
//            onDismissListener.onNegativeDismiss();
//        });
//        mBuilder.show();
//
//    }
//
//    public void showSendCoinDialogue(OnCoinDismissListener onCoinDismissListener) {
//        if (mContext == null)
//            return;
//
//        mBuilder.setCancelable(true);
//        mBuilder.setCanceledOnTouchOutside(true);
//
//        LoutSendBubbleBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.lout_send_bubble, null, false);
//        mBuilder.setContentView(binding.getRoot());
//        binding.tvCancel.setOnClickListener(view -> {
//            mBuilder.dismiss();
//            onCoinDismissListener.onCancelDismiss();
//        });
//        binding.lout5.setOnClickListener(view -> {
//            mBuilder.dismiss();
//            onCoinDismissListener.on5Dismiss();
//        });
//        binding.lout10.setOnClickListener(view -> {
//            mBuilder.dismiss();
//            onCoinDismissListener.on10Dismiss();
//        });
//        binding.lout20.setOnClickListener(view -> {
//            mBuilder.dismiss();
//            onCoinDismissListener.on20Dismiss();
//        });
//        mBuilder.show();
//
//    }
//
//    public void showSendCoinResultDialogue(boolean success, OnResultButtonClick onResultButtonClick) {
//        if (mContext == null)
//            return;
//
//        mBuilder.setCancelable(true);
//        mBuilder.setCanceledOnTouchOutside(true);
//        LoutSendResultPopupBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.lout_send_result_popup, null, false);
//        mBuilder.setContentView(binding.getRoot());
//        binding.setSuccess(success);
//        binding.loutButton.setOnClickListener(view -> {
//            mBuilder.dismiss();
//            onResultButtonClick.onButtonClick(success);
//        });
//
//        mBuilder.show();
//    }
//
//    public void showLoadingDialog() {
//        if (mContext == null)
//            return;
//
//        mBuilder.setCancelable(false);
//        mBuilder.setCanceledOnTouchOutside(false);
//        DailogLoaderBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.dailog_loader, null, false);
//        Animation rotateAnimation = AnimationUtils.loadAnimation(mContext, R.anim.rotate);
//        Animation reverseAnimation = AnimationUtils.loadAnimation(mContext, R.anim.rotate_reverse);
//        binding.ivParent.startAnimation(rotateAnimation);
//        binding.ivChild.startAnimation(reverseAnimation);
//        mBuilder.setContentView(binding.getRoot());
//        mBuilder.show();
//    }
//
//    public void hideLoadingDialog() {
//        try {
//            mBuilder.dismiss();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public interface OnResultButtonClick {
//        void onButtonClick(boolean success);
//    }
//
//    public interface OnDismissListener {
//        void onPositiveDismiss();
//
//        void onNegativeDismiss();
//    }
//
//    public interface OnCoinDismissListener {
//        void onCancelDismiss();
//
//        void on5Dismiss();
//
//        void on10Dismiss();
//
//        void on20Dismiss();
//
//    }
//
//}