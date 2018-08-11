package com.parker.bbs.service.serviceImpl;

import com.parker.bbs.mapper.RoleMapper;
import com.parker.bbs.mapper.UserMapper;
import com.parker.bbs.pojo.Collection;
import com.parker.bbs.pojo.Operation;
import com.parker.bbs.pojo.Role;
import com.parker.bbs.pojo.User;
import com.parker.bbs.service.UserService;
import com.parker.bbs.util.AESEncrypt;
import com.parker.bbs.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RoleMapper roleMapper;

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
    public User validateUser(User user) {
        User realUser = userMapper.getUserByUsername(user.getUsername());
        if (realUser != null && realUser.getPassword().equals(user.getPassword())) {
            return realUser;
        }
        return null;
    }

    @Override
    public User registerUser(User user) {
        user.setLevel(0);
        user.setStatus(0);
        user.setRegisterTime(Timestamp.valueOf(Util.getCurrentDateTime()));
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
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public boolean isExistUser(String username) {
        return false;
    }

    @Override
    public List getAllUsersExceptSelf(User user, int currentPage, int totalItemsPerPage) {
        return null;
    }

    @Override
    public int getAllUsersNumExceptSelf(User user) {
        return 0;
    }

    @Override
    public void updateUserPassword(User user) {

    }

    @Override
    public void updateUserInfo(User user) {

    }

    @Override
    public void setAdmin(int userid) {

    }

    @Override
    public void unsetAdmin(int userid) {

    }

    @Override
    public User getUserByid(int userid) {
        return null;
    }

    @Override
    public List getCollectionsByUserId(int userid, int currentPage, int totalItemsPerPage, String order) {
        return null;
    }

    @Override
    public void createCollection(Collection collection) {

    }

    @Override
    public void deleteCollection(int userid, int postid) {

    }

    @Override
    public Collection getCollection(int userid, int postid) {
        return null;
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }
}
