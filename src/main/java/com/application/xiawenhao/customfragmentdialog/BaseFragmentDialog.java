package com.application.xiawenhao.customfragmentdialog;


import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

/**
 * Created by xiawenhao on 2016/10/17.
 */

public abstract class BaseFragmentDialog extends Fragment {
    //遮蔽层
    private FrameLayout mShiledingView;
    //dialog层
    private View mContentView;

    //是否正在显示
    private boolean mIsShowing = false;

    //是否点击外层view隐藏dialog
    private boolean mDismissByTouchOutside = true;


    private FragmentManager mFragmentManager;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mShiledingView = new FrameLayout(getActivity().getBaseContext());
        mShiledingView.setBackgroundResource(R.drawable.background_g);

        onCreateContentView(inflater, mShiledingView, container);

        return mShiledingView;
    }

    public boolean isShowing() {
        return mIsShowing;
    }


    public boolean isDismissByTouchOutside() {
        return mDismissByTouchOutside;
    }

    @Override
    public void onResume() {
        super.onResume();
        doDismissByTouchOutside(mDismissByTouchOutside);
    }

    private void doDismissByTouchOutside(boolean canDismissByTouchOutside){
        if(mShiledingView == null){
            return;
        }

        View.OnClickListener onShiedlingViewClickListener;
        if(mDismissByTouchOutside){
            onShiedlingViewClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            };
        }else{
            onShiedlingViewClickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {}
            };
        }
        mShiledingView.setOnClickListener(onShiedlingViewClickListener);
    }

    public void setDismissByTouchOutside(boolean dismissByTouchOutside) {
        mDismissByTouchOutside = dismissByTouchOutside;
        doDismissByTouchOutside(mDismissByTouchOutside);
    }

    @Override
    public void onPause() {
        super.onPause();


    }


    /**
     * 隐藏dialog
     */
    public void dismiss(){
        if(mFragmentManager!=null){
            mFragmentManager.beginTransaction().remove(BaseFragmentDialog.this).commitAllowingStateLoss();
            mIsShowing = false;
        }
    }

    /**
     * 在某个区域 显示这个dialog
     * @param id 用来显示这个dialog的位置
     */
    public void show(final int id, FragmentManager fragmentManager){
        mFragmentManager = fragmentManager;
        if(mFragmentManager!=null){
            mFragmentManager.beginTransaction().add(id, BaseFragmentDialog.this).commitAllowingStateLoss();
            mIsShowing = true;
        }
    }



    /**
     * 直接显示在activity的contentview上
     */
    public void show(FragmentManager fragmentManager){
        show(android.R.id.content, fragmentManager);
    }

    /**
     * 用于获得dialog的主view 实现不用将view添加在viewgroup上
     * @return
     */
    @NonNull
    protected abstract View onCreateContentView(LayoutInflater inflater, FrameLayout containerView, ViewGroup rootView);
}
