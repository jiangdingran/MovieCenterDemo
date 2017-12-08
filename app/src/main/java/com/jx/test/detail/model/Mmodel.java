package com.jx.test.detail.model;

import com.jx.test.detail.bean.MyComment;
import com.jx.test.sift.API.Api;
import com.jx.test.sift.API.Apiserver;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by 武晓瑞 on 2017/11/24.
 */

public class Mmodel implements Imodel {

    @Override
    public void getPingLun(String url, Map<String, String> map) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.TYPE_HOME).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        Apiserver apiserver = retrofit.create(Apiserver.class);
        Observable<MyComment> pinglun = apiserver.pinglun(url, map);
        pinglun.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyComment>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MyComment myComment) {
                        List<MyComment.RetBean.ListBean> list = myComment.getRet().getList();
                        getpingchuan.getPingUser(list);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public interface getPingChuan{
        void getPingUser(List<MyComment.RetBean.ListBean> list);
    }
    public getPingChuan getpingchuan;

    public void setpingdata(getPingChuan getpingchuan) {
        this.getpingchuan = getpingchuan;
    }

}
