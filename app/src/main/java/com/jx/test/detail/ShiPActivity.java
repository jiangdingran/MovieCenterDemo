package com.jx.test.detail;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.jx.test.R;
import com.jx.test.activity.BaseActivity;
import com.jx.test.activity.MainActivity;
import com.jx.test.detail.adapter.FragmentAdapter;
import com.jx.test.detail.presenter.Spresenter;
import com.jx.test.detail.view.ContentFragment;
import com.jx.test.detail.view.ContentFragments;
import com.jx.test.detail.view.Sview;
import com.jx.test.find.greendao.CollectBean;
import com.jx.test.find.greendao.gen.CollectBeanDao;
import com.jx.test.find.greendao.gen.DaoMaster;
import com.jx.test.find.greendao.gen.DaoSession;
import com.jx.test.sift.bean.MyDataId;
import com.jx.test.sift.bean.MyShiPinBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ShiPActivity extends BaseActivity implements Sview {
    @BindView(R.id.goback)
    ImageView goback;
    @BindView(R.id.title_bar_name)
    TextView titleBarName;
    @BindView(R.id.settv)
    TextView settv;

    private List<Fragment> fragments;
    private FragmentAdapter adapter;
    private ViewPager viewpager;
    private TabLayout tablayout;
    private Spresenter spresenter;
    private String dataids;
    private PlayerView playerView;

    String movieid;
    String moviename;
    String moviepic;

    CollectBeanDao userDao;

    @Override
    protected int getRootView() {
        return R.layout.activity_shi_p;
    }

    @Override
    protected void init() {
        //初始化管理的包（后面new的OpenHelper包）
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "lenve.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        //UserDao接受
        userDao = daoSession.getCollectBeanDao();

        spresenter = new Spresenter(this);
        EventBus.getDefault().register(ShiPActivity.this);
        settv.setBackgroundResource(R.mipmap.collection);
        settv.setText("");
        fragments = new ArrayList<Fragment>();
        viewpager = (ViewPager) findViewById(R.id.viewpage);
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        ContentFragment fragment1 = new ContentFragment();
        ContentFragments fragment2 = new ContentFragments();
        fragments.add(fragment1);
        fragments.add(fragment2);

        Bundle bundle = new Bundle();
        bundle.putString("id", dataids);
        fragment1.setArguments(bundle);
        fragment2.setArguments(bundle);
        adapter = new FragmentAdapter(getSupportFragmentManager(), fragments);

        viewpager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewpager);
        settv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settv.setBackgroundResource(R.mipmap.collection_select);
                AnimationSet animationSet = new AnimationSet(true);

                ScaleAnimation scaleAnimation = new ScaleAnimation(1,0.5f,1,0.5f,
                        Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
                //3秒完成动画
                scaleAnimation.setDuration(2000);
                //将AlphaAnimation这个已经设置好的动画添加到 AnimationSet中
                animationSet.addAnimation(scaleAnimation);
                //启动动画
                ShiPActivity.this.settv.startAnimation(animationSet);
                //收藏
                CollectBean collectBean = new CollectBean(null,movieid,moviepic,moviename);
                userDao.insert(collectBean);
                Toast.makeText(ShiPActivity.this, "已收藏", Toast.LENGTH_SHORT).show();
            }
        });
        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void getShiPin(MyShiPinBean.RetBean ret) {
        playerView = new PlayerView(this)
                .setTitle(ret.getTitle())
                .setScaleType(PlayStateParams.fitparent)
                .hideMenu(true)
                .forbidTouch(false)
                .setPlaySource(ret.getHDURL());
        playerView.startPlay();
        titleBarName.setText(ret.getTitle());

        moviepic = ret.getPic();
        moviename = ret.getTitle();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(ShiPActivity.this);
        playerView.stopPlay();
    }

    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void onMoonEvent(MyDataId event) {
        dataids = event.getDataids();
        event.setDataids(dataids);

        movieid = dataids;

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("mediaId", dataids);

        spresenter.getShiPindaTa("videoDetailApi/videoDetail.do?", map);


    }

}
