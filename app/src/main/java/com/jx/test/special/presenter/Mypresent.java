package com.jx.test.special.presenter;

import com.jx.test.special.bean.SpecialBean;
import com.jx.test.special.model.Mynodel;
import com.jx.test.special.view.Sview;

import java.util.List;

/**
 * Created by admin on 2017/12/6.
 */

public class Mypresent implements Mynodel.FinshData{
    private final Mynodel mynodel;
    private final Sview sview;

    public Mypresent( Sview sview) {
        this.mynodel = new Mynodel();
        this.sview = sview;
    }
    public void setdata(String url){
        mynodel.setFinshData(this);
        mynodel.getmdata(url);
    }
    @Override
    public void mdata(List<SpecialBean.RetBean.ListBean> list) {
        sview.getsview(list);
    }
}
