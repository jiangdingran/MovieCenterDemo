package com.jx.test.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.jx.test.R;
import com.jx.test.mine.utils.SharedPreferencesUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeActivity extends AppCompatActivity {

    @BindView(R.id.btn_outlogin)
    Button btnOutlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_outlogin)
    public void onViewClicked() {
        SharedPreferencesUtils.setParam(MeActivity.this,"LOGIN_CODE","3");
        finish();
    }
}
