package com.jx.test.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jx.test.R;
import com.jx.test.detail.ShiPActivity;
import com.jx.test.find.adapter.HistoryGridAdapter;
import com.jx.test.find.greendao.HistroyBean;
import com.jx.test.find.greendao.gen.DaoMaster;
import com.jx.test.find.greendao.gen.DaoSession;
import com.jx.test.find.greendao.gen.HistroyBeanDao;
import com.jx.test.sift.bean.MyDataId;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HistroyActivity extends BaseActivity {


    HistroyBeanDao userDao;
    List<HistroyBean> list;
    @BindView(R.id.historygridview)
    GridView gridView;

    HistoryGridAdapter adapter;
    @BindView(R.id.goback)
    ImageView goback;
    @BindView(R.id.gobackLayout)
    LinearLayout gobackLayout;
    @BindView(R.id.title_bar_name)
    TextView titleBarName;
    @BindView(R.id.settv)
    TextView settv;
    @BindView(R.id.title_bar_layout)
    LinearLayout titleBarLayout;


    @Override
    protected int getRootView() {
        return R.layout.activity_histroy;
    }

    @Override
    protected void init() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "lenve.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        //UserDao接受
        userDao = daoSession.getHistroyBeanDao();

        initData();
    }

    public void initData() {
        list = userDao.queryBuilder()
                .build().list();
        for (int i = 0; i < list.size(); i++) {
            Log.d("google_lenve", "id=========" + list.get(i).getMovieid() + "name===== " + list.get(i).getMoviename() + "age====" + list.get(i).getMoviehead());
        }
        Log.d("myMain", list.toString() + "    " + list.size());

        adapter = new HistoryGridAdapter(list, HistroyActivity.this);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                EventBus.getDefault().postSticky(new MyDataId(list.get(i).getMovieid()));

                Intent intent = new Intent(HistroyActivity.this, ShiPActivity.class);
                startActivity(intent);
            }
        });

    }


    @OnClick({R.id.goback, R.id.settv})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.goback:
                finish();
                break;
            case R.id.settv:
                for (int i = 0; i < list.size(); i++) {
                    userDao.delete(list.get(i));
                }

                list.clear();
                adapter.notifyDataSetChanged();
                break;
        }
    }
}
