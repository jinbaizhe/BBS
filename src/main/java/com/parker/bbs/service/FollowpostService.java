package com.parker.bbs.service;

import com.parker.bbs.pojo.Followpost;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FollowpostService {
     Followpost getFollowpostById(int id);
     List getFollowpostsByPostId(int postId, int page, int num, String order);
     int getFollowpostsNumByPostId(int postId);
     void insertFollowpost(String content, int postid, int userId);
     void updateFollowpost(int followpostId, String content);
     void deleteFollowpost(Followpost followpost);
     void deleteFollowpost(int followpostId);
     List getFollowpostsByUserId(int userId, String order);
}
