package com.guonl.service.impl;

import com.guonl.dao.UserMapper;
import com.guonl.dao.UserRolesMapper;
import com.guonl.entity.User;
import com.guonl.entity.UserExample;
import com.guonl.entity.UserRoles;
import com.guonl.entity.UserRolesExample;
import com.guonl.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by guonl
 * Date 2018/4/24 下午1:23
 * Description:
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRolesMapper userRolesMapper;


    @Override
    public User getUserByUserName(String userName) {
        return this.getUserByName(userName);
    }

    private User getUserByName(String name){
        User user = null;
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(name);
        List<User> users = userMapper.selectByExample(example);
        if(!users.isEmpty()){
            user = users.get(0);
        }
        return user;
    }

    @Override
    public Set<String> getRolesByUserName(String userName) {
        UserRolesExample userRolesExample = new UserRolesExample();
        UserRolesExample.Criteria criteria = userRolesExample.createCriteria();
        criteria.andUsernameEqualTo(userName);
        List<UserRoles> userRoles = userRolesMapper.selectByExample(userRolesExample);
        Set<String> set = new HashSet<>();
        if(!userRoles.isEmpty()){
            for (UserRoles userRole : userRoles) {
                set.add(userRole.getRoleName());
            }
        }
        return set;
    }
}
