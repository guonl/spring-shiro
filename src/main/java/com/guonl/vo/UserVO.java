package com.guonl.vo;

import java.io.Serializable;

/**
 * Created by guonl
 * Date 2018/4/23 下午4:44
 * Description:
 */
public class UserVO implements Serializable{

    private String name;

    private String password;

    private Boolean rememberMe;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getRememberMe() {
        return rememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}
