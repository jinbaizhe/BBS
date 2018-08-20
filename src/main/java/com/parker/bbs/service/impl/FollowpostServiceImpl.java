package com.parker.bbs.service.impl;

import com.parker.bbs.annotation.LogAnnotation;
import com.parker.bbs.mapper.FollowpostMapper;
import com.parker.bbs.mapper.PostMapper;
import com.parker.bbs.pojo.Followpost;
import com.parker.bbs.pojo.Post;
import com.parker.bbs.pojo.User;
import com.parker.bbs.service.FollowpostService;
import com.parker.bbs.util.OperationTarget;
import com.parker.bbs.util.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class FollowpostServiceImpl implements FollowpostService {

    @Autowired
    private FollowpostMapper followpostMapper;
    @Autowired
    private PostMapper postMapper;

    @Override
    public Followpost getFollowpostById(Integer id) {
        return followpostMapper.getFollowpostById(id);
    }

    @Override
    public List getFollowpostsByPostId(Integer postId, Integer page, Integer num, String order) {
        Integer beginIndex = (page-1)*num;
        order = "send_time "+order;
        return followpostMapper.getFollowpostsByPostId(postId, beginIndex, num, order);
    }

    @Override
    public Integer getFollowpostsNumByPostId(Integer postId) {
        return followpostMapper.getFollowpostsNumByPostId(postId);
    }

    @Override
    @LogAnnotation(operationType = OperationType.Insert, operationTarget = OperationTarget.Followpost, operationInfo = "发表回帖")
    public void insertFollowpostNeedLog(String content, Integer postId, Integer userId) {
        Post post = new Post();
        post.setId(postId);
        User user = new User();
        user.setId(userId);
        Followpost followpost = new Followpost();
        followpost.setContent(content);
        followpost.setPost(post);
        followpost.setUser(user);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        followpost.setSendTime(timestamp);
        followpost.setUpdateTime(timestamp);
        followpostMapper.insertFollowpost(followpost);
        postMapper.updatePostLastReplyTime(postId, timestamp);
    }

    @Override
    @LogAnnotation(operationType = OperationType.Update, operationTarget = OperationTarget.Followpost, operationInfo = "修改回帖")
    public void updateFollowpostNeedLog(Integer followpostId, String content) {
        Followpost followpost = followpostMapper.getFollowpostById(followpostId);
        followpost.setContent(content);
        followpost.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        followpostMapper.updateFollowpost(followpost);
    }

    @Override
    @LogAnnotation(operationType = OperationType.Delete, operationTarget = OperationTarget.Followpost, operationInfo = "删除回帖")
    public void deleteFollowpostNeedLog(Integer followpostId) {
        followpostMapper.deleteFollowpost(followpostId);
    }

    @Override
    public List getFollowpostsByUserId(Integer userId, String order) {
        return followpostMapper.getFollowpostsByUserId(userId, order);
    }
}
