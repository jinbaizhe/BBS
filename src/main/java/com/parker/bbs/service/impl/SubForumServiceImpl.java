package com.parker.bbs.service.impl;

import com.parker.bbs.annotation.LogAnnotation;
import com.parker.bbs.mapper.SubForumMapper;
import com.parker.bbs.pojo.MainForum;
import com.parker.bbs.pojo.SubForum;
import com.parker.bbs.service.SubForumService;
import com.parker.bbs.util.OperationTarget;
import com.parker.bbs.util.OperationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service("subForumService")
public class SubForumServiceImpl implements SubForumService {

    @Autowired
    private SubForumMapper subForumMapper;

    @Override
    public List getSubForumsByMainForumId(Integer mainForumId) {
        return subForumMapper.getSubForumsByMainForumId(mainForumId);
    }

    @Override
    public SubForum getSubForumById(Integer id) {
        return subForumMapper.getSubForumById(id);
    }

    @Override
    @LogAnnotation(operationType = OperationType.Insert, operationTarget = OperationTarget.SubForum, operationInfo = "增加子版块")
    public void insertSubForumNeedLog(Integer mainForumId, String name, String info) {
        SubForum subForum = new SubForum();
        MainForum mainForum = new MainForum();
        mainForum.setId(mainForumId);
        subForum.setMainForum(mainForum);
        subForum.setName(name);
        subForum.setInfo(info);
        subForum.setCreateTime(new Timestamp(System.currentTimeMillis()));
        subForumMapper.insertSubForum(subForum);
    }

    @Override
    @LogAnnotation(operationType = OperationType.Update, operationTarget = OperationTarget.SubForum, operationInfo = "修改子版块")
    public void updateSubForumNeedLog(Integer id, String name, String info) {
        SubForum subForum = subForumMapper.getSubForumById(id);
        subForum.setName(name);
        subForum.setInfo(info);
        subForumMapper.updateSubForum(subForum);
    }

    @Override
    @LogAnnotation(operationType = OperationType.Delete, operationTarget = OperationTarget.SubForum, operationInfo = "删除子版块")
    public void deleteSubForumNeedLog(Integer id) {
        SubForum subForum = new SubForum();
        subForum.setId(id);
        subForumMapper.deleteSubForum(subForum);
    }
}
