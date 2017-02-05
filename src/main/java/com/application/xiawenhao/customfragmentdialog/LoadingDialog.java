package com.application.xiawenhao.customfragmentdialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by xiawenhao on 2016/10/18.
 */

public class LoadingDialog extends BaseFragmentDialog {

    ImageView mLoadingCircle;

    private String mMessage = "正在加载...";

    private TextView mMessageView;

    public void setMessage(String message){
        if(mMessageView != null) {
            mMessageView.setText(message);
        }else{
            mMessage = message;
        }
    }

    public String getMessage(){
        return mMessageView != null? mMessageView.getText().toString(): mMessage;
    }

    @NonNull
    @Override
    protected View onCreateContentView(LayoutInflater inflater, FrameLayout containerView, ViewGroup rootView) {
        View contentView = inflater.inflate(R.layout.fragment_loading_dialog, containerView, false);
        mLoadingCircle = (ImageView) contentView.findViewById(R.id.loading_circle_view);
        mMessageView = (TextView) contentView.findViewById(R.id.message_view);
        containerView.addView(contentView);

        int rootViewWidth = rootView.getWidth();
        int rootViewHeight = rootView.getHeight();
        containerView.measure(View.MeasureSpec.makeMeasureSpec(rootViewWidth, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(rootViewHeight, View.MeasureSpec.EXACTLY));

        FrameLayout.LayoutParams contentViewLayoutParams =
                (FrameLayout.LayoutParams) contentView.getLayoutParams();
        contentViewLayoutParams.gravity = Gravity.CENTER;
        contentViewLayoutParams.width = containerView.getMeasuredWidth()/7*2;
        contentViewLayoutParams.height = containerView.getMeasuredWidth()/7*3;


        return contentView;
    }

    @Override
    public void onResume() {
        super.onResume();
        setMessage(mMessage);
        startLoadingAnimation();
    }

    @Override
    public void onPause() {
        super.onPause();
        stopLoadingAnimation();
    }

    private void startLoadingAnimation(){
        Animation loadingAnimation =
                new RotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        loadingAnimation.setRepeatCount(Animation.INFINITE);
        loadingAnimation.setDuration(1000);
        mLoadingCircle.startAnimation(loadingAnimation);
    }

    private void stopLoadingAnimation(){
        mLoadingCircle.clearAnimation();
    }


}
