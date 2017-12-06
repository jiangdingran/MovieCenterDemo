package com.jx.test.sift.API;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;



/**
 * Created by 武晓瑞 on 2017/11/24.
 */

public class MyApp extends Application {
    public static MyApp mInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        ImageLoaderConfiguration aDefault = ImageLoaderConfiguration.createDefault(getApplicationContext());
        ImageLoader.getInstance().init(aDefault);


    }
    public static MyApp getInstance() {
        return mInstance;
    }
}
