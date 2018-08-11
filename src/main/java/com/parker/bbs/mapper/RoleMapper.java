package com.parker.bbs.mapper;

import com.parker.bbs.pojo.Operation;
import com.parker.bbs.pojo.Role;
import com.parker.bbs.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    List<Role> getUserRoles(@Param("user") User user);
    List<Operation> getRoleOperations(@Param("role")Role role);
}
