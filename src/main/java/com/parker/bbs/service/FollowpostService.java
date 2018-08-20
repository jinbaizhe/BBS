package com.parker.bbs.service;

import com.parker.bbs.pojo.Followpost;

import java.util.List;

public interface FollowpostService {
     Followpost getFollowpostById(Integer id);
     List getFollowpostsByPostId(Integer postId, Integer page, Integer num, String order);
     Integer getFollowpostsNumByPostId(Integer postId);
     void insertFollowpostNeedLog(String content, Integer postid, Integer userId);
     void updateFollowpostNeedLog(Integer followpostId, String content);
     void deleteFollowpostNeedLog(Integer followpostId);
     List getFollowpostsByUserId(Integer userId, String order);
}
