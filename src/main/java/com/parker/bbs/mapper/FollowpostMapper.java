package com.parker.bbs.mapper;

import com.parker.bbs.pojo.Followpost;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FollowpostMapper {
     Followpost getFollowpostById(@Param("id") Integer id);
     List getFollowpostsByPostId(@Param("postId") Integer postId, @Param("beginIndex") Integer beginIndex, @Param("num")Integer num, @Param("order") String order);
     List getFollowpostsAllByPostId(@Param("postId") Integer postId);
     Integer getFollowpostsNumByPostId(@Param("postId") Integer postId);
     Integer insertFollowpost(@Param("followpost") Followpost followpost);
     Integer updateFollowpost(@Param("followpost") Followpost followpost);
     Integer deleteFollowpost(@Param("followpostId") Integer followpostId);
     List getFollowpostsByUserId(@Param("userId") Integer userId, @Param("order") String order);
}
