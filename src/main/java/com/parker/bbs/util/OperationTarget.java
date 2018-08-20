package com.parker.bbs.util;

public enum OperationTarget {
    Collection("收藏表"),Followpost("回帖表"),Game("比赛表"),GameLink("比赛链接表"),
    Log("日志表"),MainForum("主版块表"),SubForum("子板块表"),Message(""),
    Operation("权限表"),Picture("图片表"),Post("帖子表"),Role("角色表"),
    RoleOperation("角色权限表"),User("用户表"),UserRole("用户角色表"),
    Like("点赞表");

    private String value;
    OperationTarget(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
