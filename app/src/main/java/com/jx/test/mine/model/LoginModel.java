package com.jx.test.mine.model;

import android.util.Log;

import com.jx.test.mine.bean.LoginBean;
import com.jx.test.mine.utils.LoginApiService;

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
 * Created by Administrator on 2017/12/8 0008.
 */

public class LoginModel implements ILoginModel {

    LoginBean list = new LoginBean();

    private OnLoginFinish onfinish;

    public interface OnLoginFinish{
        void onFinishListen(LoginBean list);
    }

    public void setOnfinish(OnLoginFinish onfinish) {
        this.onfinish = onfinish;
    }

    @Override
    public void getUrl(String url, Map<String,String> mmap) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();

        LoginApiService loginApiService = retrofit.create(LoginApiService.class);
        Observable<LoginBean> login = loginApiService.getLogin("user/login", mmap);

        login.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<LoginBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(LoginBean loginBean) {
                        Log.d("myMain","====="+loginBean.getMsg());
                        list = loginBean;
                        onfinish.onFinishListen(list);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("myMain","onError==="+e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }
}
