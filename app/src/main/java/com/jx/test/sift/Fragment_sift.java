package com.jx.test.sift;


import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jx.test.R;
import com.jx.test.detail.ShiPActivity;
import com.jx.test.search.SearchActivity;
import com.jx.test.sift.adapter.HomeAdapter;
import com.jx.test.sift.bean.MyDataId;
import com.jx.test.sift.bean.MyHome;
import com.jx.test.sift.presenter.Mpresenter;
import com.jx.test.sift.view.Iview;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.transformers.Transformer;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 蒋丁然 on 2017/12/4.
 */

public class Fragment_sift extends Fragment implements Iview, SwipeRefreshLayout.OnRefreshListener {
    Unbinder unbinder;

    @BindView(R.id.id_swipe)
    SwipeRefreshLayout idSwipe;
    @BindView(R.id.sift_xr)
    XRecyclerView siftXr;
    @BindView(R.id.title_bar_name)
    TextView titleBarName;
    @BindView(R.id.settv)
    TextView settv;
    @BindView(R.id.goback)
    ImageView goback;
    @BindView(R.id.title_bar_layout)
    LinearLayout titleBarLayout;

    private TextView line;
    private HomeAdapter adapter;
    private int imageHeight = 100; //设置渐变高度，一般为导航图片高度，自己控制
    private Mpresenter mpresenter;
    private XBanner mybanner;
    private TextView edit_sou;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sift, null);
        unbinder = ButterKnife.bind(this, view);
        View abnv = View.inflate(getActivity(), R.layout.layout_bann, null);
//        line = (TextView) view.findViewById(R.id.line);
        settv.setText("");
        goback.setVisibility(View.INVISIBLE);
        mybanner = (XBanner) abnv.findViewById(R.id.mybanner);
        edit_sou = (TextView) abnv.findViewById(R.id.edit_sou);
        edit_sou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), SearchActivity.class));
            }
        });
        //搜索框在布局最上面
        titleBarLayout.bringToFront();
        //滑动监听
        siftXr.addHeaderView(abnv);
        siftXr.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int y = 0;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                y += dy;
                if (y <= 0) {
                    titleBarName.setText("精选");
                    titleBarLayout.getBackground().setAlpha(0);//AGB由相关工具获得，或者美工提供
                    titleBarName.setTextColor(Color.argb(0, 255, 255, 255));
                } else if (y > 0 && y <= imageHeight) {
                    float scale = (float) y / imageHeight;
                    float alpha = (255 * scale);
                    // 只是layout背景透明
                    titleBarName.setText("精选");
                    titleBarLayout.setBackgroundColor(Color.argb((int) alpha, 227, 29, 26));
                    titleBarName.setTextColor(Color.argb((int) alpha, 255, 255, 255));
                } else {
                    titleBarName.setText("精选");
                    titleBarLayout.setBackgroundColor(Color.argb((int) 255, 227, 29, 26));
                    titleBarName.setTextColor(Color.argb((int) 225, 255, 255, 255));
                }

            }
        });
        init();
        mpresenter = new Mpresenter(this);
        mpresenter.getHomedaTa();
        /*停止刷新图案*/
        idSwipe.setOnRefreshListener(this);
        return view;
    }

    private void init() {
        siftXr.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                mpresenter.getHomedaTa();
                adapter.notifyDataSetChanged();
                siftXr.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                siftXr.loadMoreComplete();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void getHome(final List<MyHome.RetBean.ListBean> list) {
        final List<MyHome.RetBean.ListBean.ChildListBean> childList = list.get(0).getChildList();
        siftXr.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new HomeAdapter(list, getActivity());
        siftXr.setAdapter(adapter);
        final List<String> xbanimg = new ArrayList<>();
        for (int i = 0; i < childList.size(); i++) {
            if (i!=2){
                xbanimg.add(childList.get(i).getPic());
            }
        }
        mybanner.setData(xbanimg, null);
//        mybanner.setmAutoPalyTime(2500);
        // 设置XBanner的页面切换特效
        mybanner.setPageTransformer(Transformer.Depth);
        // 设置XBanner页面切换的时间，即动画时长
        mybanner.setPageChangeDuration(1000);
        mybanner.setmAdapter(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, View view, int position) {
                Glide.with(getActivity()).load(xbanimg.get(position)).into((ImageView) view);
            }
        });
        mybanner.setOnItemClickListener(new XBanner.OnItemClickListener() {


            private String dataId;

            @Override
            public void onItemClick(XBanner banner, int position) {
                if (position>=2){
                    dataId = list.get(0).getChildList().get(position+1).getDataId();
                }else {
                    dataId = list.get(0).getChildList().get(position).getDataId();
                }
                EventBus.getDefault().postSticky(new MyDataId(dataId));
                startActivity(new Intent(getActivity(), ShiPActivity.class));


            }
        });

    }

    @Override
    public void onRefresh() {
        idSwipe.setRefreshing(false);
    }
}
