package com.jx.test.activity;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.jx.test.utils.LogUtils;

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
        Fresco.initialize(this);
        setContentView(getRootView());

        LogUtils.Builder lBuilder = new LogUtils.Builder();
        lBuilder = new LogUtils.Builder()
                .setLogSwitch(false)// 设置log总开关，默认开
                .setGlobalTag("CMJ")// 设置log全局标签，默认为空
                // 当全局标签不为空时，我们输出的log全部为该tag，
                // 为空时，如果传入的tag为空那就显示类名，否则显示tag
                .setLog2FileSwitch(false)// 打印log时是否存到文件的开关，默认关
                .setBorderSwitch(true)// 输出日志是否带边框开关，默认开
                .setLogFilter(LogUtils.V);// log过滤器，和logcat过滤器同理，默认Verbose

        mContext = this;
        //绑定ButterKnife
        mUnBinder = ButterKnife.bind(this);
        LogUtils.e("BaseActivity", this.getClass().getSimpleName());
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
