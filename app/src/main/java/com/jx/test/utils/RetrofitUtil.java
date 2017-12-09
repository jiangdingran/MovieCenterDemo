package com.jx.test.utils;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by admin on 2017/12/5.
 */

public class RetrofitUtil {

    private static Retrofit retrofit;
    private static Object o=new Object();

    public static Retrofit getRetrofit(String baseUrl){

        if(retrofit==null){
            synchronized (o){
                if(retrofit==null){
                    return new Retrofit.Builder()
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .baseUrl(baseUrl)
                            .build();
                }
            }
        }
        return  retrofit;
    }
    public static <T>T getCall(String baseUrl,Class<T> t){
        return getRetrofit(baseUrl).create(t);
    }

}
