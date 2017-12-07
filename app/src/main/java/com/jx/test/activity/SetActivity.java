package com.jx.test.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jx.test.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetActivity extends BaseActivity {

    @BindView(R.id.tj)
    LinearLayout tj;
    @BindView(R.id.clean)
    LinearLayout clean;
    @BindView(R.id.gy)
    LinearLayout gy;
    @BindView(R.id.jy)
    LinearLayout jy;
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
        return R.layout.activity_set;
    }

    @Override
    protected void init() {
        titleBarName.setText("设置");
        settv.setVisibility(View.GONE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tj, R.id.clean, R.id.gy, R.id.jy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tj:
                AlertDialog.Builder builder = new AlertDialog.Builder(SetActivity.this);//当前环境
                builder.setTitle("发现一个看片神器");//提示框标题
                builder.setMessage("https://github.com/jiangdingran/MovieCenterDemo");//提示内容
                builder.setPositiveButton("复制", null);//确定按钮
                builder.setNegativeButton("取消", null);
                builder.create().show();
                break;
            case R.id.clean:
                break;
            case R.id.gy:
                break;
            case R.id.jy:
                break;
        }
    }

    @OnClick(R.id.goback)
    public void onViewClicked() {
        finish();
    }
}
