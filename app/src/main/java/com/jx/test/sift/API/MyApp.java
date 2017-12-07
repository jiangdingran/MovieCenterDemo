package com.jx.test.sift.API;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;


/**
 * Created by 武晓瑞 on 2017/11/24.
 */

public class MyApp extends Application {
    public static MyApp mInstance;
    {
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        UMShareAPI.get(this);
        mInstance = this;
        Fresco.initialize(this);
        ImageLoaderConfiguration aDefault = ImageLoaderConfiguration.createDefault(getApplicationContext());
        ImageLoader.getInstance().init(aDefault);


    }
    public static MyApp getInstance() {
        return mInstance;
    }
}
