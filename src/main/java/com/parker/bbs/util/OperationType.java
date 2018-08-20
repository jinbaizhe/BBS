package com.parker.bbs.util;

public enum OperationType {
    Select("查找"),Update("修改"),Delete("删除"),Insert("添加");
    private String value;
    OperationType(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
