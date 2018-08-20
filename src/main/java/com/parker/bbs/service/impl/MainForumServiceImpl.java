package com.parker.bbs.service.impl;

import com.parker.bbs.annotation.LogAnnotation;
import com.parker.bbs.mapper.MainForumMapper;
import com.parker.bbs.pojo.MainForum;
import com.parker.bbs.service.MainForumService;
import com.parker.bbs.util.OperationTarget;
import com.parker.bbs.util.OperationType;
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
    public MainForum getMainForumById(Integer id) {
        return mainForumMapper.getMainForumById(id);
    }

    @Override
    @LogAnnotation(operationType = OperationType.Insert, operationTarget = OperationTarget.MainForum, operationInfo = "增加主版块")
    public void insertMainForumNeedLog(String name, String info) {
        MainForum mainForum = new MainForum();
        mainForum.setName(name);
        mainForum.setInfo(info);
        mainForum.setCreateTime(new Timestamp(System.currentTimeMillis()));
        mainForumMapper.insertMainForum(mainForum);
    }

    @Override
    @LogAnnotation(operationType = OperationType.Update, operationTarget = OperationTarget.MainForum, operationInfo = "修改主版块")
    public void updateMainForumNeedLog(Integer id, String name, String info) {
        MainForum mainForum = mainForumMapper.getMainForumById(id);
        mainForum.setName(name);
        mainForum.setInfo(info);
        mainForumMapper.updateMainForum(mainForum);
    }

    @Override
    @LogAnnotation(operationType = OperationType.Delete, operationTarget = OperationTarget.MainForum, operationInfo = "删除主版块")
    public void deleteMainForumNeedLog(Integer id) {
        MainForum mainForum = new MainForum();
        mainForum.setId(id);
        mainForumMapper.deleteMainForum(mainForum);
    }

}
