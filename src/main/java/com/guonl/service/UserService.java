package com.guonl.service;

import com.guonl.entity.User;

import java.util.Set;

/**
 * Created by guonl
 * Date 2018/4/24 下午1:22
 * Description:
 */
public interface UserService {

    User getUserByUserName(String userName);

    Set<String> getRolesByUserName(String userName);
}
