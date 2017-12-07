package com.jx.test.welfare.view;

import com.jx.test.welfare.bean.WelfareBean;

import java.util.ArrayList;

/**
 * Created by w h l on 2017/12/6.
 */

public interface IView {
    //得到P层的网络请求数据
    void getUser(ArrayList<WelfareBean.NewslistBean> list);
}
