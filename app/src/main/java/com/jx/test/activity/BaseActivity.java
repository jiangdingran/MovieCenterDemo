package com.jx.test.activity;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 蒋丁然 on 2017/12/5.
 */

public abstract class BaseActivity extends me.yokeyword.fragmentation.SupportActivity {

    protected Activity mContext;
    private Unbinder mUnBinder;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getRootView());

        mContext = this;
        //绑定ButterKnife
        mUnBinder = ButterKnife.bind(this);
        Log.e("BaseActivity", this.getClass().getSimpleName());
        setTranslucentStatus(true);
        init();

    }

    //加载布局
    protected abstract int getRootView();
    //初始化控件
    protected abstract void init();


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("onDestroy",  this.getClass().getName() + "------>onDestroy");
        //解除绑定
        if (mUnBinder != null)
            mUnBinder.unbind();
    }

    /**
     * 设置沉浸式
     *
     * @param on
     */
    protected void setTranslucentStatus(boolean on) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (on) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
            }
            win.setAttributes(winParams);
        }
    }





}
