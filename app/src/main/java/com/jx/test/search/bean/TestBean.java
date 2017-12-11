package com.jx.test.search.bean;

/**
 * Created by admin on 2017/12/11.
 */

public class TestBean {
    private String img;
    private String name;

    public TestBean(String img, String name) {
        this.img = img;
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
