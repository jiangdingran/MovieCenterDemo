package com.jx.test.special.api;

import com.jx.test.special.bean.SpecialBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by admin on 2017/12/5.
 */

public interface IServise {
    @GET
    Observable<SpecialBean> getspecial(@Url String url);
}
