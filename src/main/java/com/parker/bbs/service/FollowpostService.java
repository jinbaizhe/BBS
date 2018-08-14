package com.parker.bbs.service;

import com.parker.bbs.pojo.Followpost;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FollowpostService {
     Followpost getFollowpostById(int id);
     List getFollowpostsByPostId(int postId, int page, int num, String order);
     int getFollowpostsNumByPostId(int postId);
     void insertFollowpost(Followpost followpost, int postid, int userid);
     void updateFollowpost(Followpost followpost);
     void deleteFollowpost(Followpost followpost);
     List getFollowpostsByUserId(int userId, String order);
}
