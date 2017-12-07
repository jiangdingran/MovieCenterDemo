package com.jx.test.sift;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jx.test.R;
import com.jx.test.sift.adapter.HomeAdapter;
import com.jx.test.sift.bean.MyHome;
import com.jx.test.sift.presenter.Mpresenter;
import com.jx.test.sift.view.Iview;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 蒋丁然 on 2017/12/4.
 */

public class Fragment_sift extends Fragment implements Iview,SwipeRefreshLayout.OnRefreshListener{

    View view;
    @BindView(R.id.line)
    TextView line;
    @BindView(R.id.lv)
    ScrollViewWithListView lv;
    @BindView(R.id.scrollview)
    ObservableScrollView scrollview;
    Unbinder unbinder;
    @BindView(R.id.id_swipe)
    SwipeRefreshLayout idSwipe;
    private HomeAdapter adapter;
    private int imageHeight = 100; //设置渐变高度，一般为导航图片高度，自己控制
    private Mpresenter mpresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sift, null);
        unbinder = ButterKnife.bind(this, view);
        //搜索框在布局最上面
        line.bringToFront();
        //滑动监听
        scrollview.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                if (y <= 0) {
                    line.setText("精选");
                    line.setBackgroundColor(Color.argb((int) 0, 227, 29, 26));//AGB由相关工具获得，或者美工提供
                    line.setTextColor(Color.argb(0, 255, 255, 255));
                } else if (y > 0 && y <= imageHeight) {
                    float scale = (float) y / imageHeight;
                    float alpha = (255 * scale);
                    // 只是layout背景透明
                    line.setText("精选");
                    line.setBackgroundColor(Color.argb((int) alpha, 227, 29, 26));
                    line.setTextColor(Color.argb((int) alpha, 255, 255, 255));
                } else {
                    line.setText("精选");
                    line.setBackgroundColor(Color.argb((int) 255, 227, 29, 26));
                    line.setTextColor(Color.argb((int) 225, 255, 255, 255));
                }
            }
        });
        mpresenter = new Mpresenter(this);
        mpresenter.getHomedaTa();
        /*停止刷新图案*/
        idSwipe.setOnRefreshListener(this);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void getHome(List<MyHome.RetBean.ListBean> list) {
        lv.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new HomeAdapter(list, getActivity());
        lv.setAdapter(adapter);
    }

   @Override
    public void onRefresh() {
        idSwipe.setRefreshing(false);
    }
}
