package com.parker.bbs.service.serviceImpl;

import com.parker.bbs.mapper.FollowpostMapper;
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

    @Override
    public Followpost getFollowpostById(int id) {
        return followpostMapper.getFollowpostById(id);
    }

    @Override
    public List getFollowpostsByPostId(int postId, int page, int num, String order) {
        int beginIndex = (page-1)*num;
        //page*num等价于(page-1)*num+num
        int endIndex = page*num;
        order = "send_time "+order;
        return followpostMapper.getFollowpostsByPostId(postId, beginIndex, endIndex, order);
    }

    @Override
    public int getFollowpostsNumByPostId(int postId) {
        return followpostMapper.getFollowpostsNumByPostId(postId);
    }

    @Override
    public void insertFollowpost(Followpost followpost, int postid, int userid) {
        Post post = new Post();
        post.setId(postid);
        User user = new User();
        user.setId(userid);
        followpost.setPost(post);
        followpost.setUser(user);
        followpost.setSendTime(Timestamp.valueOf(Util.getCurrentDateTime()));
        followpost.setUpdateTime(Timestamp.valueOf(Util.getCurrentDateTime()));
        followpostMapper.insertFollowpost(followpost);
    }

    @Override
    public void updateFollowpost(Followpost followpost) {
        followpostMapper.updateFollowpost(followpost);
    }

    @Override
    public void deleteFollowpost(Followpost followpost) {
        followpostMapper.deleteFollowpost(followpost);
    }

    @Override
    public List getFollowpostsByUserId(int userId, String order) {
        return followpostMapper.getFollowpostsByUserId(userId, order);
    }
}
