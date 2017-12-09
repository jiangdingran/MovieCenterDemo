package com.jx.test.search.model;

import com.jx.test.search.api.API;
import com.jx.test.search.api.Apiserver;
import com.jx.test.search.bean.SearchBean;
import com.jx.test.utils.LogUtils;
import com.jx.test.utils.RetrofitUtil;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by admin on 2017/12/8.
 */

public class Seamodel implements Searmodel {
    public interface Finshser{
        void setsear(List<SearchBean.RetBean.ListBean> list);
    }
    private Finshser finshser;

    public void Setsearch(Finshser finshser) {
        this.finshser = finshser;
    }

    @Override
    public void getdata(String url, Map<String,String> map) {
        Apiserver call = RetrofitUtil.getCall(API.searurl, Apiserver.class);
        Observable<SearchBean> getsear = call.getsear(url, map);
        getsear.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SearchBean searchBean) {
                        List<SearchBean.RetBean.ListBean> list = searchBean.getRet().getList();
                        finshser.setsear(list);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d("onError",e);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
