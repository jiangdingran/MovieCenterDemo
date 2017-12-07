package com.jx.test.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jx.test.R;

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
    }

    @OnClick(R.id.goback)
    public void onViewClicked() {
        finish();
    }
}
