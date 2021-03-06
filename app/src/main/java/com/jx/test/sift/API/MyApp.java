package com.jx.test.sift.API;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.zhy.changeskin.SkinManager;


/**
 * Created by 武晓瑞 on 2017/11/24.
 */

public class MyApp extends Application {
    public static MyApp mInstance;
    {
        //这里只做了QQ，微信，微博，其他平台同理
        PlatformConfig.setWeixin("wx967daebe835fbeac", "5bb696d9ccd75a38c8a0bfe0675559b3");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");
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
        //皮肤
        SkinManager.getInstance().init(this);

    }
    public static MyApp getInstance() {
        return mInstance;
    }
}
