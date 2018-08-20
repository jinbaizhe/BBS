package com.parker.bbs.mapper;

import com.parker.bbs.pojo.Log;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogMapper {
    Integer insertLog(@Param("log") Log log);
    Integer deleteLog(@Param("id") Integer id);
    Log getLogById(@Param("id") Integer id);
}
