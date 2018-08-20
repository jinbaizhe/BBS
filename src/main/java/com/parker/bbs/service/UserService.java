package com.parker.bbs.service;

import com.parker.bbs.pojo.Operation;
import com.parker.bbs.pojo.Role;
import com.parker.bbs.pojo.User;
import com.parker.bbs.util.VerifyCode;

import java.util.List;
import java.util.Set;

public interface UserService {
    User validateUserNeedLog(User user);
    User registerUserNeedLog(User user);
    boolean isExistUser(String username);
    List getUsersExceptAdminAndSuperAdmin(Integer currentPage, Integer totalItemsPerPage);
    Integer getUsersNumExceptAdminAndSuperAdmin();
    List getAdmins(Integer currentPage, Integer totalItemsPerPage);
    Integer getAdminsNum();
    void updateUserPasswordNeedLog(Integer userId, String oldPassword, String password) throws Exception;
    void updateUserPassword(Integer userId, String password);
    void updateUserInfoNeedLog(Integer userId, String info, String sex, String email);
    void setUserAdminNeedLog(Integer userId);
    void unsetUserAdminNeedLog(Integer userId);
    User getUserByid(Integer userId);
    User getUserByUsername(String username);
    List<Role> getUserRoles(User user);
    Set<Operation> getUserOperations(User user);
    VerifyCode getVerifyCode(String sessionId);
    VerifyCode changeVerifyCode(String sessionId);
    User verifyResetPasswordKey(String key);
    void saveResetPasswordKey(Integer userid, String key);
    List<User> getSearchUsers(String key, Integer currentPage, Integer totalItemsPerPage);
}
