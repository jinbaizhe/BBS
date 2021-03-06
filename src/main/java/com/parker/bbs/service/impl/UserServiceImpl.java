package com.parker.bbs.service.impl;

import com.parker.bbs.annotation.LogAnnotation;
import com.parker.bbs.mapper.RoleMapper;
import com.parker.bbs.mapper.UserMapper;
import com.parker.bbs.pojo.Operation;
import com.parker.bbs.pojo.Role;
import com.parker.bbs.pojo.User;
import com.parker.bbs.service.UserService;
import com.parker.bbs.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RedisTemplate redisTemplate;
    @Value("#{configProperties[redisUserVerifyCodeKeyName]}")
    private String redisUserVerifyCodeKeyName;
    @Value("#{configProperties[redisResetPasswordPrefix]}")
    private String redisResetPasswordPrefix;
    @Override
    public List<Role> getUserRoles(User user) {
        return roleMapper.getUserRoles(user);
    }

    @Override
    public Set<Operation> getUserOperations(User user) {
        Set<Operation> operations = new HashSet<>();
        for (Role role : getUserRoles(user)){
            operations.addAll(roleMapper.getRoleOperations(role));
        }
        return operations;
    }

    @Override
    @LogAnnotation(operationType = OperationType.Select, operationTarget = OperationTarget.User, operationInfo = "登录账号")
    public User validateUserNeedLog(User user) {
        User realUser = userMapper.getUserByUsername(user.getUsername());
        if (realUser != null && realUser.getPassword().equals(user.getPassword())) {
            return realUser;
        }
        return null;
    }

    @Override
    @LogAnnotation(operationType = OperationType.Insert, operationTarget = OperationTarget.User, operationInfo = "注册账号")
    public User registerUserNeedLog(User user) {
        user.setLevel(0);
        user.setStatus(0);
        user.setRegisterTime(new Timestamp(System.currentTimeMillis()));
        String value= AESEncrypt.encrypt(user.getUsername()).substring(0,20);
        user.setActiveKey(value);
        try{
            if (userMapper.insertUser(user) > 0){
                return user;
            }
        }catch (Exception e){
            throw e;
        }
        return null;
    }

    @Override
    public boolean isExistUser(String username) {
        User user = userMapper.getUserByUsername(username);
        if (user != null){
            return true;
        }
        return false;
    }

    @Override
    public List getUsersExceptAdminAndSuperAdmin(Integer currentPage, Integer totalItemsPerPage) {
        Integer beginIndex = (currentPage-1)*totalItemsPerPage;
        String order = "user.id desc";
        return userMapper.getUsersExceptAdminAndSuperAdmin(beginIndex, totalItemsPerPage, order);
    }

    @Override
    public Integer getUsersNumExceptAdminAndSuperAdmin() {
        return userMapper.getUsersNumExceptAdminAndSuperAdmin();
    }

    @Override
    public List getAdmins(Integer currentPage, Integer totalItemsPerPage) {
        Integer beginIndex = (currentPage-1)*totalItemsPerPage;
        String order = "id asc";
        return userMapper.getAdmins(beginIndex, totalItemsPerPage, order);
    }

    @Override
    public Integer getAdminsNum() {
        return userMapper.getAdminsNum();
    }

    @Override
    @LogAnnotation(operationType = OperationType.Update, operationTarget = OperationTarget.User, operationInfo = "修改密码")
    public void updateUserPasswordNeedLog(Integer userId, String oldPassword, String password) throws Exception {
        User user = userMapper.getUserById(userId);
        if (oldPassword.equals(user.getPassword())){
            user.setPassword(password);
            userMapper.updateUser(user);
        }else {
            throw new Exception("旧密码输入不正确");
        }

    }

    @Override
    public void updateUserPassword(Integer userId, String password) {
        User user = userMapper.getUserById(userId);
        user.setPassword(password);
        userMapper.updateUser(user);
    }

    @Override
    @LogAnnotation(operationType = OperationType.Update, operationTarget = OperationTarget.User, operationInfo = "修改个人信息")
    public void updateUserInfoNeedLog(Integer userId, String info, String sex, String email) {
        User user = userMapper.getUserById(userId);
        user.setInfo(info);
        user.setSex(sex);
        user.setEmail(email);
        userMapper.updateUser(user);
    }

    @Override
    @LogAnnotation(operationType = OperationType.Insert, operationTarget = OperationTarget.UserRole, operationInfo = "设置管理员")
    public void setUserAdminNeedLog(Integer userId) {
        userMapper.setUserAdmin(userId);
    }

    @Override
    @LogAnnotation(operationType = OperationType.Delete, operationTarget = OperationTarget.UserRole, operationInfo = "撤销管理员")
    public void unsetUserAdminNeedLog(Integer userId) {
        userMapper.unsetUserAdmin(userId);
    }

    @Override
    public User getUserByid(Integer userId) {
        return userMapper.getUserById(userId);
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    @Override
    public VerifyCode getVerifyCode(String sessionId) {
        String key = redisUserVerifyCodeKeyName+sessionId;
        VerifyCode verifyCode = (VerifyCode) redisTemplate.opsForValue().get(key);
        if (verifyCode == null){
            verifyCode = changeVerifyCode(sessionId);
        }
        return verifyCode;
    }

    @Override
    public VerifyCode changeVerifyCode(String sessionId) {
        VerifyCode verifyCode = new VerifyCode();
        String key = redisUserVerifyCodeKeyName+sessionId;
        redisTemplate.opsForValue().set(key, verifyCode, 3, TimeUnit.MINUTES);
        return verifyCode;
    }

    @Override
    public User verifyResetPasswordKey(String key) {
        Integer userId = (Integer)redisTemplate.opsForValue().get(redisResetPasswordPrefix+key);
        if (userId != null){
            redisTemplate.delete(redisResetPasswordPrefix+key);
            return getUserByid(userId);
        }
        return null;
    }

    @Override
    public void saveResetPasswordKey(Integer userId, String key) {
        redisTemplate.opsForValue().set(redisResetPasswordPrefix+key, userId, 1, TimeUnit.HOURS);
    }

    @Override
    public List<User> getSearchUsers(String key, Integer currentPage, Integer totalItemsPerPage) {
        key = key + "%";
        Integer beginIndex = (currentPage-1)*totalItemsPerPage;
        String order = "id asc";
        return userMapper.getSearchUsers(key, beginIndex, totalItemsPerPage, order);
    }
}
