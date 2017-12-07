package com.jx.test.find.model;

import com.jx.test.find.bean.HomeBean;
import com.jx.test.find.utils.ApiService;

import java.util.ArrayList;
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
 * Created by Administrator on 2017/12/5 0005.
 */

public class UserModel implements IModel {

    ArrayList<HomeBean.RetBean.ListBean> mlist = new ArrayList<>();
    private OnFinish onFinish;

    public interface OnFinish{
        void onFinishListen(ArrayList<HomeBean.RetBean.ListBean> mlist);
    }

    public void setOnFinish(OnFinish onFinish) {
        this.onFinish = onFinish;
    }

    @Override
    public void getUrl(String url, Map<String, String> mmap) {

        //Log.d("myMain","ID==="+mmap.get("catalogId")+"page==="+mmap.get("pnum"));





        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();

        ApiService apiService = retrofit.create(ApiService.class);
        Observable<HomeBean> data = apiService.getData("front/columns/getVideoList.do",mmap);

        data.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeBean homeBean) {
                        //Log.d("myMain","onNext===="+homeBean.getRet().getList().get(5).getTitle());

                        mlist = (ArrayList<HomeBean.RetBean.ListBean>) homeBean.getRet().getList();

                        onFinish.onFinishListen(mlist);

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
