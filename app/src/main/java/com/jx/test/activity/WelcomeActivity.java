package com.jx.test.activity;

import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.jx.test.R;

import butterknife.BindView;

public class WelcomeActivity extends BaseActivity {

    @BindView(R.id.wcom)
    ImageView wcom;

    @Override
    protected int getRootView() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void init() {
        //初始化ScaleAnimation
        ScaleAnimation animation = new ScaleAnimation(1f, 1.2f, 1f, 1.2f, Animation.RELATIVE_TO_SELF, 0f, Animation.RELATIVE_TO_SELF, 0.5f);
        //动画执行时间
        animation.setDuration(3000);
        //动画重复次数-1表示不停重复
        //animation.setRepeatCount(-1);
        //给控件设置动画
        animation.setFillAfter(true);//播放完成之后保持状态
        wcom.startAnimation(animation);

        //动画监听
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            //动画结束跳转
            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
