package com.parker.bbs.service.serviceImpl;

import com.parker.bbs.mapper.SubForumMapper;
import com.parker.bbs.pojo.SubForum;
import com.parker.bbs.service.SubForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("subForumService")
public class SubForumServiceImpl implements SubForumService {

    @Autowired
    private SubForumMapper subForumMapper;

    @Override
    public List getSubForumsByMainForumId(int mainForumId) {
        return subForumMapper.getSubForumsByMainForumId(mainForumId);
    }

    @Override
    public SubForum getSubForumById(int id) {
        return subForumMapper.getSubForumById(id);
    }

    @Override
    public void insertSubForum(SubForum subForum) {
        subForumMapper.insertSubForum(subForum);
    }

    @Override
    public void updateSubForum(SubForum subForum) {
        subForumMapper.updateSubForum(subForum);
    }

    @Override
    public void deleteSubForum(SubForum subForum) {
        subForumMapper.deleteSubForum(subForum);
    }
}
