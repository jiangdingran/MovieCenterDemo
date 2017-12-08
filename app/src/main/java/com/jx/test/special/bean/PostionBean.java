package com.jx.test.special.bean;

/**
 * Created by admin on 2017/12/6.
 */

public class PostionBean {
    private int pos;
    private String name;

    public PostionBean(String name) {
        this.name = name;
    }

    public PostionBean(int pos, String name) {
        this.pos = pos;
        this.name = name;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
