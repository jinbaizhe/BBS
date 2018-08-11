package com.parker.bbs.mapper;

import com.parker.bbs.pojo.MainForum;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MainForumMapper {
    List<MainForum> getAllMainForums();
    MainForum getMainForumById(@Param("id") Integer id);
    int insertMainForum(@Param("mainForum") MainForum mainForum);
    int updateMainForum(@Param("mainForum") MainForum mainForum);
    int deleteMainForum(@Param("mainForum") MainForum mainForum);
}
