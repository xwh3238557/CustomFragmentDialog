package com.application.xiawenhao.customfragmentdialog;

import android.app.FragmentManager;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by xiawenhao on 2016/10/17.
 */
public class AlertDialog extends BaseFragmentDialog{

    private TextView mMessageView;

    private Button mLeftButton;
    private Button mRightButton;

    private String mLeftButtonName;
    private String mRightButtonName;
    private String mMessage;

    private Button.OnClickListener mLeftButtonOnClickListener, mRightButtonOnClickListener;

    /**
     * 设置左边按钮名
     * @param name 按钮名
     */
    public void setLeftButtonName(String name) {
        if(mLeftButton != null){
            mLeftButton.setText(name);
        }else{
            mLeftButtonName = name;
        }
    }

    /**
     * 设置左边按钮的点击回调
     * @param onClickListener 回调对象
     */
    public void setLeftButtonOnClickListener(final Button.OnClickListener onClickListener){
        if(mLeftButton != null){
            mLeftButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onClick(v);
                    dismiss();
                }
            });
        }else{
            mLeftButtonOnClickListener = onClickListener;
        }
    }

    /**
     * 设置左边按钮的属性
     * @param name 按钮名称
     * @param onClickListener 按钮的监听
     */
    public void setLeftButton(String name, Button.OnClickListener onClickListener){
        setLeftButtonName(name);
        setLeftButtonOnClickListener(onClickListener);
    }

    /**
     * 设置右边按钮名
     * @param name 按钮名称
     */
    public void setRightButtonName(String name){
        if(mRightButton != null){
            mRightButton.setText(name);
        }else{
            mRightButtonName = name;
        }
    }

    /**
     * 设置右边按钮的回调
     * @param onClickListener 按钮回调
     */
    public void setRightButtonOnClickListener(final Button.OnClickListener onClickListener){
        if(mRightButton != null){
            mRightButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onClick(v);
                    dismiss();
                }
            });
        }else{
            mRightButtonOnClickListener = onClickListener;
        }
    }

    /**
     * 设置右边按钮的属性
     * @param name 设置按钮名称
     * @param onClickListener 按钮的监听
     */
    public void setRightButton(String name, Button.OnClickListener onClickListener){
        setRightButtonName(name);
        setRightButtonOnClickListener(onClickListener);
    }


    public String getMessage(){
        return mMessageView != null? mMessageView.getText().toString(): mMessage;
    }

    public void setMessage(String message){
        if(mMessageView != null){
            mMessageView.setText(message);
        }else{
            mMessage = message;
        }
    }

    @NonNull
    @Override
    protected View onCreateContentView(LayoutInflater inflater, final FrameLayout containerView, ViewGroup rootView) {
        View contentView = inflater.inflate(R.layout.fragment_alert_dialog, containerView, false);
        mMessageView = (TextView) contentView.findViewById(R.id.message_view);
        mLeftButton = (Button) contentView.findViewById(R.id.left_button);
        mRightButton = (Button) contentView.findViewById(R.id.right_button);

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
    

    @Override
    public void onResume() {
        super.onResume();
        setMessage(mMessage);
        setLeftButton(mLeftButtonName, mLeftButtonOnClickListener);
        setRightButton(mRightButtonName, mRightButtonOnClickListener);
    }

}
