package com.jx.test.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jx.test.R;
import com.jx.test.mine.bean.LoginBean;
import com.jx.test.mine.presenter.LoginPresenter;
import com.jx.test.mine.utils.SharedPreferencesUtils;
import com.jx.test.mine.view.ILoginView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements ILoginView {

    @BindView(R.id.user_login_head)
    ImageView userLoginHead;
    @BindView(R.id.user_login_username)
    EditText username;
    @BindView(R.id.userpass)
    EditText userpass;
    @BindView(R.id.user_login_forgetpassword)
    TextView userLoginForgetpassword;
    @BindView(R.id.user_login_newuserzhuce)
    TextView userLoginNewuserzhuce;
    @BindView(R.id.user_login_btn)
    Button userLoginBtn;

    LoginPresenter userpresenter;

    public static final String LOGIN_URL = "http://120.27.23.105/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        userpresenter = new LoginPresenter(this);



    }

    @OnClick({R.id.user_login_forgetpassword, R.id.user_login_newuserzhuce, R.id.user_login_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user_login_forgetpassword:




                break;
            case R.id.user_login_newuserzhuce:
                Intent intent = new Intent();
                break;
            case R.id.user_login_btn:

                String name = username.getText().toString();
                String pass = userpass.getText().toString();

                Map<String,String> mmap = new HashMap<>();
                mmap.put("mobile",name);
                mmap.put("password",pass);

                userpresenter.getUrl(LOGIN_URL,mmap);
                break;
        }
    }


    @Override
    public void LoginSuccess(LoginBean loginBean) {
        Toast.makeText(this, loginBean.getMsg(), Toast.LENGTH_SHORT).show();

        SharedPreferencesUtils.setParam(LoginActivity.this,"LOGIN_CODE",loginBean.getCode());

        finish();
    }

    @Override
    public void LoginFailed(LoginBean loginBean) {
        Toast.makeText(this, loginBean.getMsg(), Toast.LENGTH_SHORT).show();
    }
}
