package com.parker.bbs.service.serviceImpl;

import com.parker.bbs.mapper.SubForumMapper;
import com.parker.bbs.pojo.MainForum;
import com.parker.bbs.pojo.SubForum;
import com.parker.bbs.service.SubForumService;
import com.parker.bbs.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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
    public void insertSubForum(int mainForumId, String name, String info) {
        SubForum subForum = new SubForum();
        MainForum mainForum = new MainForum();
        mainForum.setId(mainForumId);
        subForum.setMainForum(mainForum);
        subForum.setName(name);
        subForum.setInfo(info);
        subForum.setCreateTime(Timestamp.valueOf(Util.getCurrentDateTime()));
        subForumMapper.insertSubForum(subForum);
    }

    @Override
    public void updateSubForum(int id, String name, String info) {
        SubForum subForum = subForumMapper.getSubForumById(id);
        subForum.setName(name);
        subForum.setInfo(info);
        subForumMapper.updateSubForum(subForum);
    }

    @Override
    public void deleteSubForum(int id) {
        SubForum subForum = new SubForum();
        subForum.setId(id);
        subForumMapper.deleteSubForum(subForum);
    }
}
