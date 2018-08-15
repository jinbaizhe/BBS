package com.parker.bbs.service.serviceImpl;

import com.parker.bbs.mapper.MainForumMapper;
import com.parker.bbs.pojo.MainForum;
import com.parker.bbs.service.MainForumService;
import com.parker.bbs.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service("mainForumService")
public class MainForumServiceImpl implements MainForumService {

    @Autowired
    private MainForumMapper mainForumMapper;

    @Override
    public List getAllMainForums() {
        return mainForumMapper.getAllMainForums();
    }

    @Override
    public MainForum getMainForumById(int id) {
        return mainForumMapper.getMainForumById(id);
    }

    @Override
    public void insertMainForum(String name, String info) {
        MainForum mainForum = new MainForum();
        mainForum.setName(name);
        mainForum.setInfo(info);
        mainForum.setCreateTime(Timestamp.valueOf(Util.getCurrentDateTime()));
        mainForumMapper.insertMainForum(mainForum);
    }

    @Override
    public void updateMainForum(int id, String name, String info) {
        MainForum mainForum = mainForumMapper.getMainForumById(id);
        mainForum.setName(name);
        mainForum.setInfo(info);
        mainForumMapper.updateMainForum(mainForum);
    }

    @Override
    public void deleteMainForum(int id) {
        MainForum mainForum = new MainForum();
        mainForum.setId(id);
        mainForumMapper.deleteMainForum(mainForum);
    }

    @Override
    public void deleteMainForum(MainForum mainForum) {
        mainForumMapper.deleteMainForum(mainForum);
    }
}
