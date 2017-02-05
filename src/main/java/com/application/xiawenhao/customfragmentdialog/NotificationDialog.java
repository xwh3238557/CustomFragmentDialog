package com.application.xiawenhao.customfragmentdialog;

import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by xiawenhao on 2016/10/18.
 */

public class NotificationDialog extends BaseFragmentDialog {
    private TextView mMessageView;

    private String mMessage;

    public String getMessage(){
        return mMessageView != null? mMessageView.getText().toString(): mMessage;
    }

    public void setMessage(String message){
        if(mMessageView != null) {
            mMessageView.setText(message);
        }
        mMessage = message;
    }

    @Override
    public void onResume() {
        super.onResume();
        setMessage(mMessage);
    }

    @NonNull
    @Override
    protected View onCreateContentView(LayoutInflater inflater, FrameLayout containerView, ViewGroup rootView) {
        View contentView = inflater.inflate(R.layout.fragment_notification_dialog, containerView ,false);
        mMessageView = (TextView) contentView.findViewById(R.id.message_view);
        containerView.addView(contentView);

        int rootViewWidth = rootView.getWidth();
        int rootViewHeight = rootView.getHeight();
        containerView.measure(View.MeasureSpec.makeMeasureSpec(rootViewWidth, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(rootViewHeight, View.MeasureSpec.EXACTLY));

        FrameLayout.LayoutParams contentViewLayoutParams =
                (FrameLayout.LayoutParams) contentView.getLayoutParams();
        contentViewLayoutParams.gravity = Gravity.CENTER;

        contentViewLayoutParams.width = containerView.getMeasuredWidth() /3 *2;
        contentViewLayoutParams.height = contentViewLayoutParams.width /5 *3;
        return contentView;
    }
}
