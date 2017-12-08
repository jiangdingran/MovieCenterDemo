package com.jx.test.mine.presenter;

import android.util.Log;

import com.jx.test.mine.bean.LoginBean;
import com.jx.test.mine.model.LoginModel;
import com.jx.test.mine.view.ILoginView;

import java.util.Map;

/**
 * Created by Administrator on 2017/12/8 0008.
 */

public class LoginPresenter implements LoginModel.OnLoginFinish{
    private final ILoginView userview;
    private final LoginModel usermodel;


    public LoginPresenter(ILoginView userview) {
        this.userview = userview;
        this.usermodel = new LoginModel();
        usermodel.setOnfinish(this);
    }

    public void getUrl(String url, Map<String,String> mmap){
        usermodel.getUrl(url,mmap);
    }

    @Override
    public void onFinishListen(LoginBean list) {
        if(list.getCode().equals("0")){
            Log.d("myMain","------登录码为成功"+list.getCode());
            userview.LoginSuccess(list);
        }else{
            Log.d("myMain","------登录码为失败"+list.getCode());
            userview.LoginFailed(list);
        }
    }
}
