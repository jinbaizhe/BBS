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
    boolean isExistUser(String username);
    List getUsersExceptAdminAndSuperAdmin(int currentPage, int totalItemsPerPage);
    int getUsersNumExceptAdminAndSuperAdmin();
    List getAdmins(int currentPage, int totalItemsPerPage);
    int getAdminsNum();
    void updateUserPassword(int userId, String oldPassword, String password) throws Exception;
    void updateUserInfo(int userId, String info, String sex, String email);
    void setUserAdmin(int userId);
    void unsetUserAdmin(int userId);
    User getUserByid(int userId);
    User getUserByUsername(String username);
    List<Role> getUserRoles(User user);
    Set<Operation> getUserOperations(User user);
}
