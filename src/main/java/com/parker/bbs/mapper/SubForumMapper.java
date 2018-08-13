package com.parker.bbs.mapper;

import com.parker.bbs.pojo.SubForum;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SubForumMapper {
    List getSubForumsByMainForumId(@Param("mainForumId") Integer mainForumId);
    SubForum getSubForumById(@Param("id") Integer id);
    int insertSubForum(@Param("subForum") SubForum subForum);
    int updateSubForum(@Param("subForum") SubForum subForum);
    int deleteSubForum(@Param("subForum") SubForum subForum);
}
