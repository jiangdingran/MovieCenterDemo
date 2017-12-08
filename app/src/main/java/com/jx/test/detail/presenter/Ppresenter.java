package com.jx.test.detail.presenter;

import com.jx.test.detail.bean.MyComment;
import com.jx.test.detail.model.Mmodel;
import com.jx.test.detail.view.Pview;

import java.util.List;
import java.util.Map;

/**
 * Created by 武晓瑞 on 2017/12/8.
 */

public class Ppresenter implements Mmodel.getPingChuan {
    public final Pview pview;
    public final Mmodel mmodel;

    public Ppresenter(Pview pview) {
        this.pview = pview;
        this.mmodel=new Mmodel();
    }

    public void setPingdaTa(String url, Map<String,String> map){
        mmodel.setpingdata(this);
        mmodel.getPingLun(url,map);
    }

    @Override
    public void getPingUser(List<MyComment.RetBean.ListBean> list) {
        pview.getPing(list);
    }
}
