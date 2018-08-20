package com.parker.bbs.service.impl;

import com.parker.bbs.mapper.LogMapper;
import com.parker.bbs.pojo.Log;
import com.parker.bbs.pojo.User;
import com.parker.bbs.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

@Service("logService")
public class LogServiceImpl implements LogService {
    @Autowired
    private LogMapper logMapper;
    @Override
    public void insertLog(String operationType, String operationTarget, String operationInfo, String operationArgs) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpSession session = attributes.getRequest().getSession();

        User user = (User)session.getAttribute("user");
        if (user == null) {
            user = (User)session.getAttribute("tempUser");
        }
        String ip = attributes.getRequest().getRemoteHost();
        Log log = new Log();
        log.setUser(user);
        log.setLoginIP(ip);
        log.setOperationType(operationType);
        log.setOperationTarget(operationTarget);
        log.setOperationInfo(operationInfo);
        log.setOperationArgs(operationArgs);
        log.setCreateTime(new Timestamp(System.currentTimeMillis()));
        logMapper.insertLog(log);
    }

    @Override
    public void deleteLog(Integer id) {
        logMapper.deleteLog(id);
    }

    @Override
    public Log getLogById(Integer id) {
        return logMapper.getLogById(id);
    }
}
