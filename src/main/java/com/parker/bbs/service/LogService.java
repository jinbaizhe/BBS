package com.parker.bbs.service;

import com.parker.bbs.pojo.Log;

public interface LogService {
    void insertLog(String operationType, String operationTarget, String operationInfo, String operationArgs);
    void deleteLog(Integer id);
    Log getLogById(Integer id);
}
