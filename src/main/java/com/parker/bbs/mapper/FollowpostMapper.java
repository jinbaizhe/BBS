package com.parker.bbs.mapper;

import com.parker.bbs.pojo.Followpost;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FollowpostMapper {
     Followpost getFollowpostById(@Param("id") int id);
     List getFollowpostsByPostId(@Param("postId") int postId, @Param("currentPage") int currentPage, @Param("order") String order);
     int getFollowpostsNumByPostId(@Param("postId") int postId);
     void insertFollowpost(@Param("followpost") Followpost followpost);
     void updateFollowpost(@Param("followpost") Followpost followpost);
     void deleteFollowpost(@Param("followpost") Followpost followpost);
     List getFollowpostsByUserId(@Param("userId") int userId, @Param("order") String order);
}
