package com.jx.test.sift.model;

import java.util.Map;

/**
 * Created by 武晓瑞 on 2017/11/24.
 */

public interface Imodel {
    void getHomeData();
    void getShiPinData(String url, Map<String, String> map);
}
