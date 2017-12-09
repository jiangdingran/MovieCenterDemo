package com.jx.test.search.present;

import com.jx.test.search.bean.SearchBean;
import com.jx.test.search.model.Seamodel;
import com.jx.test.search.view.SearView;

import java.util.List;
import java.util.Map;

/**
 * Created by admin on 2017/12/8.
 */

public class SearPersent implements Seamodel.Finshser{
    private final Seamodel seamodel;
    private final SearView searView;

    public SearPersent( SearView searView) {
        this.seamodel = new Seamodel();
        this.searView = searView;
    }
    public void setSeardata(String url, Map<String,String> map){
         seamodel.Setsearch(this);
         seamodel.getdata(url,map);
    }
    @Override
    public void setsear(List<SearchBean.RetBean.ListBean> list) {
        searView.getSearView(list);
    }
}
