package com.parker.bbs.mapper;

import com.parker.bbs.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    User getUserById(@Param("id") int id);
    int insertUser(@Param("user") User user);
    int updateUser(@Param("user")User user);
    int deleteUser(@Param("user")User user);
    User getUserByUsername(@Param("username") String username);
    List<User> getUsersExceptAdminAndSuperAdmin(@Param("beginIndex") int beginIndex, @Param("num") int num, @Param("order") String order);
    int getUsersNumExceptAdminAndSuperAdmin();
    int setUserAdmin(@Param("userId") int userId);
    int unsetUserAdmin(@Param("userId") int userId);
    List<User> getAdmins(@Param("beginIndex") int beginIndex, @Param("num") int num, @Param("order") String order);
    int getAdminsNum();
}
