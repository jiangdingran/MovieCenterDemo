package com.jx.test.find.presenter;

import com.jx.test.find.bean.HomeBean;
import com.jx.test.find.model.UserModel;
import com.jx.test.find.view.IView;

import java.util.ArrayList;
import java.util.Map;



/**
 * Created by Administrator on 2017/12/5 0005.
 */

public class UserPresenter implements UserModel.OnFinish {
    private final IView userview;
    private final UserModel userModel;


    public UserPresenter(IView userview) {
        this.userview = userview;
        this.userModel = new UserModel();
        userModel.setOnFinish(this);
    }
    public void getUrl(String url, Map<String,String> mmap){
        userModel.getUrl(url,mmap);
    }

    @Override
    public void onFinishListen(ArrayList<HomeBean.RetBean.ListBean> mlist) {
        userview.getData(mlist);
    }
}
