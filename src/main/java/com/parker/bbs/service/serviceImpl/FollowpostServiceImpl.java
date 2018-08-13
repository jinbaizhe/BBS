package com.parker.bbs.service.serviceImpl;

import com.parker.bbs.mapper.FollowpostMapper;
import com.parker.bbs.pojo.Followpost;
import com.parker.bbs.service.FollowpostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public void insertFollowpost(Followpost followpost) {
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
