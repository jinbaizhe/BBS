package com.parker.bbs.mapper;

import com.parker.bbs.pojo.Followpost;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FollowpostMapper {
     Followpost getFollowpostById(@Param("id") int id);
     List getFollowpostsByPostId(@Param("postId") int postId, @Param("beginIndex") int beginIndex, @Param("num")int num, @Param("order") String order);
     List getFollowpostsAllByPostId(@Param("postId") int postId);
     int getFollowpostsNumByPostId(@Param("postId") int postId);
     int insertFollowpost(@Param("followpost") Followpost followpost);
     int updateFollowpost(@Param("followpost") Followpost followpost);
     int deleteFollowpost(@Param("followpost") Followpost followpost);
     List getFollowpostsByUserId(@Param("userId") int userId, @Param("order") String order);
}
