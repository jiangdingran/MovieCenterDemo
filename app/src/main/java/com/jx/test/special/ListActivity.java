package com.jx.test.special;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.jx.test.R;
import com.jx.test.special.adapter.GridAdapter;
import com.jx.test.special.bean.PostionBean;
import com.jx.test.special.bean.SpecialBean;
import com.jx.test.special.presenter.Mypresent;
import com.jx.test.special.view.Sview;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListActivity extends AppCompatActivity implements Sview {

    @BindView(R.id.gv)
    GridView gv;
    @BindView(R.id.swp)
    SwipeRefreshLayout swp;
    @BindView(R.id.title_bar_name)
    TextView titleBarName;
    @BindView(R.id.settv)
    TextView settv;
    @BindView(R.id.goback)
    ImageView goback;
    private Mypresent mypresent;
    private GridAdapter adapter;
    private int pos;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        mypresent = new Mypresent(this);
        mypresent.setdata("homePage.do");
        titleBarName.setText(name);
        settv.setVisibility(View.INVISIBLE);
    }

    @Subscribe(sticky = true)
    public void onMoonEvent(PostionBean message) {
        pos = message.getPos();
        name = message.getName();
    }

    @Override
    public void getsview(List<SpecialBean.RetBean.ListBean> list) {
        List<SpecialBean.RetBean.ListBean.ChildListBean> childList = list.get(pos).getChildList();
        adapter = new GridAdapter(childList, ListActivity.this);
        gv.setAdapter(adapter);
    }

    @OnClick(R.id.goback)
    public void onViewClicked() {
        ListActivity.this.finish();
    }
}
