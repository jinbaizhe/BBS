package com.parker.bbs.service.serviceImpl;

import com.parker.bbs.mapper.FollowpostMapper;
import com.parker.bbs.mapper.PostMapper;
import com.parker.bbs.pojo.Followpost;
import com.parker.bbs.pojo.Post;
import com.parker.bbs.pojo.User;
import com.parker.bbs.service.FollowpostService;
import com.parker.bbs.util.Util;
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
    public Followpost getFollowpostById(int id) {
        return followpostMapper.getFollowpostById(id);
    }

    @Override
    public List getFollowpostsByPostId(int postId, int page, int num, String order) {
        int beginIndex = (page-1)*num;
        order = "send_time "+order;
        return followpostMapper.getFollowpostsByPostId(postId, beginIndex, num, order);
    }

    @Override
    public int getFollowpostsNumByPostId(int postId) {
        return followpostMapper.getFollowpostsNumByPostId(postId);
    }

    @Override
    public void insertFollowpost(String content, int postId, int userId) {
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
    public void updateFollowpost(int followpostId, String content) {
        Followpost followpost = followpostMapper.getFollowpostById(followpostId);
        followpost.setContent(content);
        followpost.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        followpostMapper.updateFollowpost(followpost);
    }

    @Override
    public void deleteFollowpost(Followpost followpost) {
        followpostMapper.deleteFollowpost(followpost);
    }

    @Override
    public void deleteFollowpost(int followpostId) {
        Followpost followpost = new Followpost();
        followpostMapper.deleteFollowpost(followpost);
    }

    @Override
    public List getFollowpostsByUserId(int userId, String order) {
        return followpostMapper.getFollowpostsByUserId(userId, order);
    }
}
