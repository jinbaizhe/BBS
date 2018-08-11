package com.parker.bbs.service;

import com.parker.bbs.pojo.Collection;
import com.parker.bbs.pojo.Operation;
import com.parker.bbs.pojo.Role;
import com.parker.bbs.pojo.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    User validateUser(User user);
    User registerUser(User user);
    void updateUser(User user);
    boolean isExistUser(String username);
    List getAllUsersExceptSelf(User user, int currentPage, int totalItemsPerPage);
    int getAllUsersNumExceptSelf(User user);
    void updateUserPassword(User user);
    void updateUserInfo(User user);
    void setAdmin(int userid);
    void unsetAdmin(int userid);
    User getUserByid(int userid);
    List getCollectionsByUserId(int userid,int currentPage,int totalItemsPerPage,String order);
    void createCollection(Collection collection);
    void deleteCollection(int userid, int postid);
    Collection getCollection(int userid, int postid);
    User getUserByUsername(String username);
    List<Role> getUserRoles(User user);
    Set<Operation> getUserOperations(User user);
}
