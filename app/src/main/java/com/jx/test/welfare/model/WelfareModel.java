package com.jx.test.welfare.model;

import com.jx.test.welfare.API.ApiService;
import com.jx.test.welfare.bean.WelfareBean;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by w h l on 2017/12/6.
 */

public class WelfareModel implements IModel{

    ArrayList<WelfareBean.NewslistBean> list=new ArrayList<>();
    private OnFinishLisenter lisenter;

    /*
    * 定义一个公开的接口,可以写成外部接口
    *
    * */
    public interface OnFinishLisenter{
        //得到数据
        void OnFinish(ArrayList<WelfareBean.NewslistBean> list);
    }

    public void setWelfareModel(OnFinishLisenter lisenter) {
        this.lisenter = lisenter;
    }

    @Override
    public void getJson(String url) {
        //创建Retrofit
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        ApiService apiService = retrofit.create(ApiService.class);
        //得到Observable
        Observable<WelfareBean> observable = apiService.getNoParams();//获取数据源
        observable.subscribeOn(Schedulers.io())//IO线程做耗时操作
                .observeOn(AndroidSchedulers.mainThread())//在主线程更新UI
                .subscribe(new Observer<WelfareBean>() {//实例化一个观察者
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(WelfareBean welfareBean) {
                        list = (ArrayList<WelfareBean.NewslistBean>) welfareBean.getNewslist();
                        lisenter.OnFinish(list);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }

                });
    }
}
