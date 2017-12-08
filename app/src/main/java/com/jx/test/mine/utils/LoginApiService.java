package com.jx.test.mine.utils;

import com.jx.test.mine.bean.LoginBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2017/12/8 0008.
 */

public interface LoginApiService {
    @POST
    Observable<LoginBean> getLogin(@Url String url, @QueryMap Map<String,String> mmap);
}
