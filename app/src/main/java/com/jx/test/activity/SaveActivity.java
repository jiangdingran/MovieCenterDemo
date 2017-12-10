package com.jx.test.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jx.test.R;
import com.jx.test.detail.ShiPActivity;
import com.jx.test.find.adapter.CollectGridAdapter;
import com.jx.test.find.greendao.CollectBean;
import com.jx.test.find.greendao.gen.CollectBeanDao;
import com.jx.test.find.greendao.gen.DaoMaster;
import com.jx.test.find.greendao.gen.DaoSession;
import com.jx.test.sift.bean.MyDataId;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SaveActivity extends BaseActivity {

    @BindView(R.id.goback)
    ImageView goback;
    @BindView(R.id.title_bar_name)
    TextView titleBarName;
    @BindView(R.id.settv)
    TextView settv;
    @BindView(R.id.title_bar_layout)
    LinearLayout titleBarLayout;
    @BindView(R.id.collect_gridview)
    GridView gridView;

    CollectBeanDao userDao;

    CollectGridAdapter adapter;

    List<CollectBean> list;

    @Override
    protected int getRootView() {
        return R.layout.activity_save;
    }

    @Override
    protected void init() {

        titleBarName.setText("收藏");
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);

        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(getApplicationContext(), "lenve.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        //UserDao接受
        userDao = daoSession.getCollectBeanDao();

        initdata();

    }



    public void initdata() {
        list = userDao.queryBuilder()
                .build().list();
        //之后就是遍历查询出来的集合
        for (int i = 0; i < list.size(); i++) {
            Log.d("google_lenve", "id=========" + list.get(i).getId() + "name===== " + list.get(i).getNickname() + "age====" + list.get(i).getNickhead());

        }

        adapter = new CollectGridAdapter(list, SaveActivity.this);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                EventBus.getDefault().postSticky(new MyDataId(list.get(i).getUserid()));

                Intent intent = new Intent(SaveActivity.this, ShiPActivity.class);
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

                for(int i = 0 ; i < list.size();i++){
                    userDao.delete(list.get(i));
                }

                list.clear();
                adapter.notifyDataSetChanged();
                break;
        }
    }
}
