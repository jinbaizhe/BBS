package com.parker.bbs.mapper;

import com.parker.bbs.pojo.SubForum;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SubForumMapper {
    List getSubForumsByMainForumId(@Param("mainForumId") Integer mainForumId);
    SubForum getSubForumById(@Param("id") Integer id);
    Integer insertSubForum(@Param("subForum") SubForum subForum);
    Integer updateSubForum(@Param("subForum") SubForum subForum);
    Integer deleteSubForum(@Param("subForum") SubForum subForum);
}
