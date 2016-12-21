package com.zhu.entity;

import com.zhu.entity.baseconfig.Document;

/**
 * Created by zhu on 2016/11/3.
 */
public class User extends Document{
    private String nickName;
    private String safeToken;
    private String pwd;
    private String mobile;

    public String getSafeToken() {
        return safeToken;
    }

    public void setSafeToken(String safeToken) {
        this.safeToken = safeToken;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public User() {

    }

    public User(String id, String nickName, String mobile) {
        this.id = id;
        this.nickName = nickName;
        this.mobile = mobile;
    }
}
