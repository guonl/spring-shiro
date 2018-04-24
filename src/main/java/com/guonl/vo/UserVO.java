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
}
