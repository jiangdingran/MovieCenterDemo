package com.jx.test.activity;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.ImageView;

import com.jx.test.R;
import com.jx.test.welfare.API.Api;
import com.jx.test.welfare.API.SpacesItemDecoration;
import com.jx.test.welfare.adapter.WelAdapter;
import com.jx.test.welfare.bean.WelfareBean;
import com.jx.test.welfare.presenter.WelfarePresenter;
import com.jx.test.welfare.view.IView;

import java.util.ArrayList;

import butterknife.BindView;

//http://api.tianapi.com/meinv/?key=10858b1c16838d0b07ec806a3698523c&num=10
public class WelfareActivity extends BaseActivity implements IView {

    WelfarePresenter welfarePresenter;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;

    @Override
    protected int getRootView() {
        return R.layout.activity_welfare;
    }

    @Override
    protected void init() {
        welfarePresenter = new WelfarePresenter(this);
        welfarePresenter.getUrl(Api.WEL_PATH);//P层中网络请求的方法
    }

    @Override
    public void getUser(ArrayList<WelfareBean.NewslistBean> list) {
        //关联适配器
        final WelAdapter adapter = new WelAdapter(WelfareActivity.this, list);
        //布局管理器
        recyclerview.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerview.setAdapter(adapter);
        //RecycleView 增加边距
        int spacingInPixels = 8;
        recyclerview.addItemDecoration(new SpacesItemDecoration(spacingInPixels));
    }
}
