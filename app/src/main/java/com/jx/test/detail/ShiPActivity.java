package com.jx.test.detail;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.dou361.ijkplayer.widget.PlayStateParams;
import com.dou361.ijkplayer.widget.PlayerView;
import com.jx.test.R;
import com.jx.test.detail.adapter.FragmentAdapter;
import com.jx.test.detail.presenter.Spresenter;
import com.jx.test.detail.view.ContentFragment;
import com.jx.test.detail.view.ContentFragments;
import com.jx.test.detail.view.Sview;
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

public class ShiPActivity extends AppCompatActivity implements Sview {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shi_p);
        ButterKnife.bind(this);
        spresenter = new Spresenter(this);
        EventBus.getDefault().register(ShiPActivity.this);
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

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("mediaId", dataids);

        spresenter.getShiPindaTa("videoDetailApi/videoDetail.do?", map);


    }

}
