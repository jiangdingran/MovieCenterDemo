package com.jx.test.find.utils;

import com.jx.test.find.bean.HomeBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2017/12/5 0005.
 */

public interface ApiService {

    @POST
    Observable<HomeBean> getData(@Url String url, @QueryMap Map<String, String> mmap);


}
