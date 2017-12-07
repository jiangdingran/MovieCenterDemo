package com.jx.test.detail.model;

import android.util.Log;

import com.jx.test.sift.API.Api;
import com.jx.test.sift.API.Apiserver;
import com.jx.test.sift.bean.MyShiPinBean;

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
    public void getShiPinData(String url,Map<String, String> map) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.TYPE_HOME)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        Apiserver apiserver = retrofit.create(Apiserver.class);
        Observable<MyShiPinBean> shipin = apiserver.shipin(url, map);
        shipin.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyShiPinBean>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MyShiPinBean myShiPinBean) {
                        MyShiPinBean.RetBean ret = myShiPinBean.getRet();
                        Log.d("ssssss","sssss"+myShiPinBean.getCode());
                        getshipinchuan.getShiPinUser(ret);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public interface getShiPinChuan{
        void getShiPinUser(MyShiPinBean.RetBean ret);
    }
    public getShiPinChuan getshipinchuan;

    public void setshipindata(getShiPinChuan getshipinchuan) {
        this.getshipinchuan = getshipinchuan;
    }
}
