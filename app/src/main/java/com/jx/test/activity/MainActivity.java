package com.jx.test.activity;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;

import com.jx.test.R;
import com.jx.test.find.Fragment_find;
import com.jx.test.mine.Fragment_mine;
import com.jx.test.sift.Fragment_sift;
import com.jx.test.special.Fragment_special;
import com.jx.test.utils.QQSliddingMenu;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.rdb_sift)
    RadioButton rdbSift;
    @BindView(R.id.rdb_special)
    RadioButton rdbSpecial;
    @BindView(R.id.rdb_find)
    RadioButton rdbFind;
    @BindView(R.id.rdb_mine)
    RadioButton rdbMine;
    @BindView(R.id.qqsm)
    QQSliddingMenu qqsm;


    private Fragment_find fragment_find;
    private Fragment_mine fragment_mine;
    private Fragment_sift fragment_sift;
    private Fragment_special fragment_special;
    private QQSliddingMenu qqSliddingMenu;

    FragmentManager fm;
    FragmentTransaction transaction;

    @Override
    protected int getRootView() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        initView();
        rdbSift.setChecked(true);
    }

    public void initView() {
        fragment_find = new Fragment_find();
        fragment_mine = new Fragment_mine();
        fragment_sift = new Fragment_sift();
        fragment_special = new Fragment_special();

        FragmentManager fm1 = getSupportFragmentManager();
        FragmentTransaction ftransaction = fm1.beginTransaction();
        ftransaction.replace(R.id.frame_layout, fragment_sift);
        ftransaction.commit();

    }

    @OnClick({R.id.rdb_sift, R.id.rdb_special, R.id.rdb_find, R.id.rdb_mine})
    public void onViewClicked(View view) {
        fm = getSupportFragmentManager();
        transaction = fm.beginTransaction();

        switch (view.getId()) {
            case R.id.rdb_sift:
                transaction.replace(R.id.frame_layout, fragment_sift);
                break;
            case R.id.rdb_special:
                transaction.replace(R.id.frame_layout, fragment_special);
                break;
            case R.id.rdb_find:
                transaction.replace(R.id.frame_layout, fragment_find);
                break;
            case R.id.rdb_mine:
                transaction.replace(R.id.frame_layout, fragment_mine);
                break;
        }
        transaction.commit();
        Log.d("sss", "onViewClicked: ddd");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void toggleMenu(View view) {
        qqSliddingMenu.toggleMenu();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
