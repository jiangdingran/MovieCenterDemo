package com.jx.test.detail.view;

import com.jx.test.detail.bean.MyComment;

import java.util.List;

/**
 * Created by 武晓瑞 on 2017/12/8.
 */

public interface Pview {
    void getPing(List<MyComment.RetBean.ListBean> list);
}
