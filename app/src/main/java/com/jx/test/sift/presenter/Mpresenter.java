package com.jx.test.sift.presenter;


import com.jx.test.sift.bean.MyHome;
import com.jx.test.sift.model.Mmodel;
import com.jx.test.sift.view.Iview;

import java.util.List;

/**
 * Created by 武晓瑞 on 2017/11/24.
 */

public class Mpresenter implements Mmodel.getHomeChuan {
    public final Iview iview;
    public final Mmodel mmodel;

    public Mpresenter(Iview iview) {
        this.iview = iview;
        this.mmodel=new Mmodel();
    }
    public void getHomedaTa(){
        mmodel.sethomedata(this);
        mmodel.getHomeData();
    }

    @Override
    public void getHomeUser(List<MyHome.RetBean.ListBean> list) {
        iview.getHome(list);
    }
}
