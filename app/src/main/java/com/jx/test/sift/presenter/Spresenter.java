package com.jx.test.sift.presenter;


import com.jx.test.sift.bean.MyShiPinBean;
import com.jx.test.sift.model.Mmodel;
import com.jx.test.sift.view.Sview;

import java.util.Map;

/**
 * Created by 武晓瑞 on 2017/11/28.
 */

public class Spresenter implements Mmodel.getShiPinChuan {
    public final Sview sview;
    public final Mmodel mmodel;

    public Spresenter(Sview sview) {
        this.sview = sview;
        this.mmodel=new Mmodel();
    }

    public void getShiPindaTa(String url,Map<String,String> map){
        mmodel.setshipindata(this);
        mmodel.getShiPinData(url,map);
    }

    @Override
    public void getShiPinUser(MyShiPinBean.RetBean ret) {
        sview.getShiPin(ret);
    }
}
