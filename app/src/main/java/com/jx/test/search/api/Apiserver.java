package com.jx.test.search.api;


import com.jx.test.search.bean.SearchBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;


/**
 * Created by 武晓瑞 on 2017/11/24.
 */

public interface Apiserver {
    @POST
    Observable<SearchBean> getsear(@Url String url, @QueryMap Map<String, String> map);
}
