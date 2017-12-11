package com.jx.test.search.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by admin on 2017/12/10.
 */
@Entity
public class RecodBean {
    @Id(autoincrement = true)
    private Long id;
    @Property(nameInDb = "Searid")
    private String searid;
    @Generated(hash = 1535438777)
    public RecodBean(Long id, String searid) {
        this.id = id;
        this.searid = searid;
    }
    @Generated(hash = 1538585552)
    public RecodBean() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSearid() {
        return searid;
    }

    public void setSearid(String searid) {
        this.searid = searid;
    }
}
