package com.parker.bbs.mapper;

import com.parker.bbs.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    User getUserById(@Param("id") Integer id);
    Integer insertUser(@Param("user") User user);
    Integer updateUser(@Param("user")User user);
    Integer deleteUser(@Param("user")User user);
    User getUserByUsername(@Param("username") String username);
    List<User> getUsersExceptAdminAndSuperAdmin(@Param("beginIndex") Integer beginIndex, @Param("num") Integer num, @Param("order") String order);
    Integer getUsersNumExceptAdminAndSuperAdmin();
    Integer setUserAdmin(@Param("userId") Integer userId);
    Integer unsetUserAdmin(@Param("userId") Integer userId);
    List<User> getAdmins(@Param("beginIndex") Integer beginIndex, @Param("num") Integer num, @Param("order") String order);
    Integer getAdminsNum();
    List<User> getSearchUsers(@Param("key") String key,@Param("beginIndex") Integer beginIndex, @Param("num") Integer num, @Param("order") String order);
}
