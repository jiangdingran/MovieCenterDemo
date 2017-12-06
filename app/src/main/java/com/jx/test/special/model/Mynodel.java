package com.jx.test.special.model;

import com.jx.test.special.api.API;
import com.jx.test.special.api.IServise;
import com.jx.test.special.bean.SpecialBean;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by admin on 2017/12/5.
 */

public class Mynodel implements Imodel {
    public interface FinshData{
        void mdata(List<SpecialBean.RetBean.ListBean> list);
    }
    private FinshData finshData;

    public void setFinshData(FinshData finshData) {
        this.finshData = finshData;
    }

    @Override
    public void getmdata(String url) {
        IServise call = RetrofitUtil.getCall(API.specurl, IServise.class);
        Observable<SpecialBean> getspecial = call.getspecial(url);
        getspecial.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SpecialBean>() {

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SpecialBean movieBean) {
                        List<SpecialBean.RetBean.ListBean> list = movieBean.getRet().getList();
                        finshData.mdata(list);
                    }
                });
    }
}
