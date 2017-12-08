package com.jx.test.mine.view;

import com.jx.test.mine.bean.LoginBean;

/**
 * Created by Administrator on 2017/12/8 0008.
 */

public interface ILoginView {
    void LoginSuccess(LoginBean msg);
    void LoginFailed(LoginBean msg);
}
