package com.jx.test.sift.API;


import com.jx.test.sift.bean.MyHome;
import com.jx.test.sift.bean.MyShiPinBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;


/**
 * Created by 武晓瑞 on 2017/11/24.
 */

public interface Apiserver {
    @GET("homePageApi/homePage.do")
    Observable<MyHome> homeye();

    @POST()
    Observable<MyShiPinBean> shipin(@Url String url, @QueryMap Map<String, String> map);
}
