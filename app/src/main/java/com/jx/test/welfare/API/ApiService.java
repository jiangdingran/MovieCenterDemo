package com.jx.test.welfare.API;

import com.jx.test.welfare.bean.WelfareBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by w h l on 2017/12/6.
 */

public interface ApiService {
    @GET("meinv/?key=10858b1c16838d0b07ec806a3698523c&num=10")
    //返回类型是被观察者
    Observable<WelfareBean> getNoParams();
}
