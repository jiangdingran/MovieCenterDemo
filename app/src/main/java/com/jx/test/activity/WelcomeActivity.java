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
        AnimationSet animationSet = new AnimationSet(true);
        ScaleAnimation scaleAnimation = new ScaleAnimation(1,1f,1.3f,1.1f,
                Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        //3秒完成动画
        scaleAnimation.setDuration(3000);
        //将AlphaAnimation这个已经设置好的动画添加到 AnimationSet中
        animationSet.addAnimation(scaleAnimation);
        //启动动画
        WelcomeActivity.this.wcom.startAnimation(animationSet);
        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
