package com.parker.bbs.mapper;

import com.parker.bbs.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    User getUser(@Param("id") int id);
    int insertUser(@Param("user") User user);
    int updateUser(@Param("user")User user);
    int deleteUser(@Param("user")User user);
    User getUserByUsername(@Param("username") String username);
    List<User> getAllUsers();
}
