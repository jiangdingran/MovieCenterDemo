package com.jx.test.welfare.presenter;

import android.util.Log;

import com.jx.test.welfare.bean.WelfareBean;
import com.jx.test.welfare.model.WelfareModel;
import com.jx.test.welfare.view.IView;

import java.util.ArrayList;

/**
 * Created by w h l on 2017/12/6.
 */

public class WelfarePresenter implements WelfareModel.OnFinishLisenter{

    private final IView iView;
    private final WelfareModel welfareModel;

    public WelfarePresenter(IView iView) {
        this.iView = iView;
        this.welfareModel = new WelfareModel();
        welfareModel.setWelfareModel(this);
    }

    public void getUrl(String url){
        //得到Model中请求数据的方法
        Log.d("Main","============="+url);
        welfareModel.getJson(url);
    }

    @Override
    public void OnFinish(ArrayList<WelfareBean.NewslistBean> list) {
        //把从M层中传过来的数据通过以下的方法传到V层中
        iView.getUser(list);
    }
}
