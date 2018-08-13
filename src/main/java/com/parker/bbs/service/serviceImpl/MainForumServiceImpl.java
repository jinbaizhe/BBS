package com.parker.bbs.service.serviceImpl;

import com.parker.bbs.mapper.MainForumMapper;
import com.parker.bbs.pojo.MainForum;
import com.parker.bbs.service.MainForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void insertMainForum(MainForum mainForum) {
        mainForumMapper.insertMainForum(mainForum);
    }

    @Override
    public void updateMainForum(MainForum mainForum) {
        mainForumMapper.updateMainForum(mainForum);
    }

    @Override
    public void deleteMainForum(MainForum mainForum) {
        mainForumMapper.deleteMainForum(mainForum);
    }
}
