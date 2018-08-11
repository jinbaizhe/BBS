package com.parker.bbs.shiro;

import com.parker.bbs.pojo.Operation;
import com.parker.bbs.pojo.Role;
import com.parker.bbs.pojo.User;
import com.parker.bbs.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component("customRealm")
public class CustomRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //角色、权限验证
        String username = (String)principalCollection.getPrimaryPrincipal();
        User user = userService.getUserByUsername(username);
        List<Role> roleList = userService.getUserRoles(user);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        for(Role role : roleList){
            info.addRole(role.getName());
        }
        Set<Operation> operations = userService.getUserOperations(user);
        Set<String> stringOperations = new HashSet<>();
        for (Operation operation:operations){
            stringOperations.add(operation.getName());
        }
        info.setStringPermissions(stringOperations);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //登录验证
        //使用UsernamePasswordToken对象用来存放提交的登录信息
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        User user = new User();
        user.setUsername(token.getUsername());
        //token.getPassword()返回的是char[]数组
        user.setPassword(new String(token.getPassword()));
        if (userService.validateUser(user) != null)
            return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
        throw new AuthenticationException("用户名或密码错误");
    }

}
