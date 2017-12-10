package com.jx.test.find.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/12/9 0009.
 */
@Entity
public class CollectBean {
    @Id
    private Long id;
    @Property(nameInDb = "MOVIEID")
    private String userid;
    @Property(nameInDb = "MOVIEPIC")
    private String nickhead;
    @Property(nameInDb = "MOVIENAME")
    private String nickname;
    @Generated(hash = 928475060)
    public CollectBean(Long id, String userid, String nickhead, String nickname) {
        this.id = id;
        this.userid = userid;
        this.nickhead = nickhead;
        this.nickname = nickname;
    }
    @Generated(hash = 420494524)
    public CollectBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUserid() {
        return this.userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getNickhead() {
        return this.nickhead;
    }
    public void setNickhead(String nickhead) {
        this.nickhead = nickhead;
    }
    public String getNickname() {
        return this.nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    
}
