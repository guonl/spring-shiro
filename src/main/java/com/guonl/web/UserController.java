package com.guonl.web;

import com.guonl.entity.User;
import com.guonl.service.UserService;
import com.guonl.vo.UserVO;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by guonl
 * Date 2018/4/23 下午4:27
 * Description:
 */
@Controller
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @ResponseBody
    @RequestMapping(value = "/doLogin",method = RequestMethod.POST,
            produces = "application/json;charset=utf-8")
    public String doLogin(UserVO userVO){
        // 获取主体
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userVO.getName(),userVO.getPassword());

        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            logger.error("登录失败：", e.getMessage());
            return e.getMessage();
        }

        User user = userService.getUserByUserName(userVO.getName());
        if(user == null){
            return "该用户不存在";
        }
        if(subject.hasRole("admin")){
            return "有admin权限";
        }
        return "登录成功";
    }

    @RequiresRoles("admin")
    @ResponseBody
    @RequestMapping(value = "/testRole",method = RequestMethod.GET)
    public String testRole(){
        return "testRole success";
    }

    @RequiresRoles("admin1")
    @ResponseBody
    @RequestMapping(value = "/testRole1",method = RequestMethod.GET)
    public String testRole1(){
        return "testRole success";
    }




}
