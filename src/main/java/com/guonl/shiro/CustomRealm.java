package com.guonl.shiro;

import com.guonl.entity.User;
import com.guonl.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by guonl
 * Date 2018/4/23 下午10:22
 * Description:
 */
@Component
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    Map<String,String> userMap = new HashMap<>();
    {
        userMap.put("guonl","47a42c26f019ef753f68e437fd074c76");
        super.setName("customReal");
    }

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
         // 从数据获取用户的角色
//        Set<String> roles = getRolesByUserName(username);
        Set<String> roles = userService.getRolesByUserName(username);
        // 从数据获取用户的权限
        Set<String> permissions = getPermissionsByUserName(username);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setRoles(roles);
        simpleAuthorizationInfo.setStringPermissions(permissions);
        return simpleAuthorizationInfo;
    }

    private Set<String> getPermissionsByUserName(String username) {
        Set<String> sets = new HashSet<>();
        sets.add("user:select");
        sets.add("user:add");
        sets.add("user:delete");
        return sets;
    }

    /**
     * 模拟获取数据库权限
     * @param username
     * @return
     */
    private Set<String> getRolesByUserName(String username) {
        Set<String> sets = new HashSet<>();
        sets.add("admin");
        sets.add("user");
        return sets;
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        // 1.从主体传过来的认证信息中获取用户名
        String username = (String) authenticationToken.getPrincipal();

        // 2.通过用户名到数据库中获取凭证
//        String password = this.getPasswordByUserName(username);

        User user = userService.getUserByUserName(username);
        if(user == null){
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user.getUsername(),user.getPassword(),"customRealm");
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("guonl"));
        return authenticationInfo;
    }

    /**
     * 模拟获取数据库密码
     * @param username
     * @return
     */
    private String getPasswordByUserName(String username) {
        return userMap.get("guonl");
    }

    /**
     * 计算md5的值
     * @param args
     */
    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("123456","guonl");
        System.out.println(md5Hash.toString());

    }


}
