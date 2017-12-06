package com.jx.test.sift.model;

import android.util.Log;

import com.jx.test.sift.API.Api;
import com.jx.test.sift.API.Apiserver;
import com.jx.test.sift.bean.MyHome;
import com.jx.test.sift.bean.MyShiPinBean;

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
    public void getHomeData() {
        Retrofit build = new Retrofit.Builder().baseUrl(Api.TYPE_HOME).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        Apiserver apiserver = build.create(Apiserver.class);
        Observable<MyHome> homeye = apiserver.homeye();
        homeye.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MyHome>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MyHome myHome) {
                        List<MyHome.RetBean.ListBean> list = myHome.getRet().getList();
                        gethomechuan.getHomeUser(list);
                        Log.d("ssss","sss"+list.get(0).getTitle());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("onError",e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public interface getHomeChuan{
        void getHomeUser(List<MyHome.RetBean.ListBean> list);
    }
    public getHomeChuan gethomechuan;

    public void sethomedata(getHomeChuan gethomechuan) {
        this.gethomechuan = gethomechuan;
    }

    @Override
    public void getShiPinData(String url,Map<String, String> map) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.TYPE_HOME).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
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
