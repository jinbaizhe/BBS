package com.parker.bbs.pojo;

import java.sql.Timestamp;

/**
 * Log entity. @author MyEclipse Persistence Tools
 */

public class Log implements java.io.Serializable {

	private Integer id;
	private User user;
	private String loginIP;
	private String operationType;
	private String operationTarget;
	private String operationInfo;
	private String operationArgs;
	private Timestamp createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getLoginIP() {
        return loginIP;
    }

    public void setLoginIP(String loginIP) {
        this.loginIP = loginIP;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getOperationTarget() {
        return operationTarget;
    }

    public void setOperationTarget(String operationTarget) {
        this.operationTarget = operationTarget;
    }

    public String getOperationInfo() {
        return operationInfo;
    }

    public void setOperationInfo(String operationInfo) {
        this.operationInfo = operationInfo;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getOperationArgs() {
        return operationArgs;
    }

    public void setOperationArgs(String operationArgs) {
        this.operationArgs = operationArgs;
    }
}