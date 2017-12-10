package com.jx.test.find.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by Administrator on 2017/12/10 0010.
 */
@Entity
public class HistroyBean {
    @Id
    private Long id;
    @Property(nameInDb = "MOVIEID")
    private String movieid;
    @Property(nameInDb = "MOVIEHEAD")
    private String moviehead;
    @Property(nameInDb = "MOVIENAME")
    private String moviename;
    @Generated(hash = 351137211)
    public HistroyBean(Long id, String movieid, String moviehead,
            String moviename) {
        this.id = id;
        this.movieid = movieid;
        this.moviehead = moviehead;
        this.moviename = moviename;
    }
    @Generated(hash = 1752986504)
    public HistroyBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMovieid() {
        return this.movieid;
    }
    public void setMovieid(String movieid) {
        this.movieid = movieid;
    }
    public String getMoviehead() {
        return this.moviehead;
    }
    public void setMoviehead(String moviehead) {
        this.moviehead = moviehead;
    }
    public String getMoviename() {
        return this.moviename;
    }
    public void setMoviename(String moviename) {
        this.moviename = moviename;
    }



}
